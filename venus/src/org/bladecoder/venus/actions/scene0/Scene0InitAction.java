package org.bladecoder.venus.actions.scene0;

import java.util.HashMap;

import org.bladecoder.engine.actions.Action;
import org.bladecoder.engine.actions.ActionCallback;
import org.bladecoder.engine.actions.Param;
import org.bladecoder.engine.actions.Param.Type;
import org.bladecoder.engine.model.OverlayImage;
import org.bladecoder.engine.model.Text;
import org.bladecoder.engine.model.TextManager;
import org.bladecoder.engine.model.World;
import org.bladecoder.engine.util.EngineLogger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Scene0InitAction implements Action, ActionCallback {
	
	public static final String INFO = "Init action for the intro scene";
	public static final Param[] PARAMS = {
		new Param("text", "The text to show", Type.STRING)
		};		

	private static final float TITLE_TIME = 0.2f;
	private static final float BLACK_SCREEN_TIME = 6f;
	
	private static final int INIT_STATE = 0;
	private static final int TITLE_STATE = 1;
	private static final int LEAVE_STATE = 2;

	String text;
	String titleFilename;
	
	int state = INIT_STATE;

	@Override
	public void setParams(HashMap<String, String> params) {
		if(params.get("text") != null && params.get("title_filename") != null ) {
			text = params.get("text");
			titleFilename = params.get("title_filename");
		} else {
			EngineLogger.debug("Scene0InitAction: some parameter missing");
		}
	}

	@Override
	public void run() {	
		EngineLogger.debug("SCENE0 INIT ");
		
		World.getInstance().showInventory(false);
		
		World.getInstance().getTextManager().addSubtitle(text, TextManager.POS_CENTER, TextManager.POS_CENTER, false, Text.Type.PLAIN, Color.WHITE, this);
	}

	@Override
	public void onEvent() {
		switch(state) {
		case INIT_STATE:
			
			World.getInstance().getCurrentScene().getActor("forward")
			.setInteraction(false);			
			
			OverlayImage o = new OverlayImage();
			
			o.create(titleFilename, new Vector2(0,0), 20, false, null);
			
			World.getInstance().getCurrentScene().setOverlay(o);
					
			World.getInstance().addTimer(TITLE_TIME, this);
			state = TITLE_STATE;
			break;
		case TITLE_STATE:
			
			World.getInstance().addTimer(BLACK_SCREEN_TIME, this);
			state = LEAVE_STATE;
			
			break;
		case LEAVE_STATE:
			World.getInstance().getCurrentScene().runVerb("leave");
			break;
		}		
	}

	@Override
	public String getInfo() {
		return INFO;
	}

	@Override
	public Param[] getParams() {
		return PARAMS;
	}
}
