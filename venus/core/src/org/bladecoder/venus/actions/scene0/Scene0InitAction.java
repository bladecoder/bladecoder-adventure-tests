package org.bladecoder.venus.actions.scene0;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionCallback;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionProperty;
import com.bladecoder.engine.actions.ActionPropertyDescription;
import com.bladecoder.engine.model.InteractiveActor;
import com.bladecoder.engine.model.Text;
import com.bladecoder.engine.model.TextManager;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.util.EngineLogger;

@ActionDescription("Init action for the intro scene")
public class Scene0InitAction implements Serializable, Action, ActionCallback {	

	private static final float TITLE_TIME = 6f;
	
	private static final int INIT_STATE = 0;
	private static final int LEAVE_STATE = 2;

	@ActionProperty
	@ActionPropertyDescription("The text to show")
	String text;
	
	int state = INIT_STATE;

	@Override
	public boolean run(VerbRunner cb) {
		EngineLogger.debug("SCENE0 INIT ");
		
		World.getInstance().showInventory(false);
		
		World.getInstance().getTextManager().addText(text, TextManager.POS_CENTER, TextManager.POS_CENTER, false, Text.Type.PLAIN, Color.WHITE, null, this);
		
		return false;
	}

	@Override
	public void resume() {
		switch(state) {
		case INIT_STATE:
			
			((InteractiveActor)World.getInstance().getCurrentScene().getActor("forward", false))
			.setInteraction(false);
			
			World.getInstance().setCutMode(true);
			
			World.getInstance().getCurrentScene().getActor("title", false).setVisible(true);
							
			World.getInstance().addTimer(TITLE_TIME, this);
			state = LEAVE_STATE;
			break;
		case LEAVE_STATE:
			World.getInstance().setCutMode(false);
			World.getInstance().getCurrentScene().runVerb("leave");
			break;
		}		
	}
	
	@Override
	public void write(Json json) {
		json.writeValue("state", state);
	}

	@Override
	public void read (Json json, JsonValue jsonData) {
		state = json.readValue("state", Integer.class, jsonData);
	}	
}
