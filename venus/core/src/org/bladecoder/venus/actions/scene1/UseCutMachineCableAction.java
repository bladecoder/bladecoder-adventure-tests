package org.bladecoder.venus.actions.scene1;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionCallback;
import com.bladecoder.engine.actions.Param;
import com.bladecoder.engine.actions.Param.Type;
import com.bladecoder.engine.model.BaseActor;
import com.bladecoder.engine.model.SpriteActor;
import com.bladecoder.engine.model.Text;
import com.bladecoder.engine.model.TextManager;
import com.bladecoder.engine.model.World;

public class UseCutMachineCableAction implements Action {
	public static final String INFO = "Action for using the 'cut_machine' with the 'cable'";
	public static final Param[] PARAMS = {
		new Param("cut_cable_text", "Text to show when attach the cable to the cut_machine", Type.STRING),
		new Param("default_cable_text", "Text to show when the cable can not be attached to the cut_machine", Type.STRING)
		};
	
	private String cutCableText;
	private String defaultCableText;
	private String actorId;

	@Override
	public boolean run(ActionCallback cb) {
		SpriteActor actor = (SpriteActor)World.getInstance().getCurrentScene().getActor(actorId, true);
		BaseActor target = World.getInstance().getCurrentScene().getActor("cable", true);

//		EngineLogger.debug("USING " + actor.getDesc() + " IN " + target.getDesc());

		if (target.getState().equals("CUT") && actor.getState().equals("NO_BATTERY")) {
			World.getInstance().getTextManager().addSubtitle(cutCableText, TextManager.POS_SUBTITLE,
					TextManager.POS_SUBTITLE, true, Text.Type.RECTANGLE, Color.BLACK, null);
			actor.setState("WITH_CABLE");
			actor.startFrameAnimation("cutter.withcable", null);
			actor.playSound("click");
			World.getInstance().getInventory().removeItem((SpriteActor) target);
		} else {
			World.getInstance().getTextManager().addSubtitle(defaultCableText, TextManager.POS_SUBTITLE,
					TextManager.POS_SUBTITLE, true, Text.Type.RECTANGLE, Color.BLACK, null);
		}
		
		return false;
	}

	@Override
	public void setParams(HashMap<String, String> params) {
		actorId = params.get("actor");

		cutCableText = params.get("cut_cable_text");
		defaultCableText = params.get("default_cable_text");
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
