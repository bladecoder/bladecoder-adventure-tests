package org.bladecoder.venus.actions.scene0;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionCallback;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionPropertyType;
import com.bladecoder.engine.actions.Param.Type;
import com.bladecoder.engine.model.InteractiveActor;
import com.bladecoder.engine.model.Text;
import com.bladecoder.engine.model.TextManager;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.util.EngineLogger;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@ActionDescription("Init action for the intro scene")
public class Scene0InitAction implements Action, ActionCallback {	

	private static final float TITLE_TIME = 6f;
	
	private static final int INIT_STATE = 0;
	private static final int LEAVE_STATE = 2;

	@JsonProperty
	@JsonPropertyDescription("The text to show")
	@ActionPropertyType(Type.STRING)
	String text;
	
	int state = INIT_STATE;

	@Override
	public void setParams(HashMap<String, String> params) {
		if(params.get("text") != null ) {
			text = params.get("text");
		} else {
			EngineLogger.debug("Scene0InitAction: some parameter missing");
		}
	}

	@Override
	public boolean run(ActionCallback cb) {
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
			
			World.getInstance().getCurrentScene().getActor("title", false).setVisible(true);
							
			World.getInstance().addTimer(TITLE_TIME, this);
			state = LEAVE_STATE;
			break;
		case LEAVE_STATE:
			World.getInstance().getCurrentScene().runVerb("leave");
			break;
		}		
	}
}
