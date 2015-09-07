package org.bladecoder.venus.actions.scene1;

import java.util.HashMap;

import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionCallback;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionPropertyType;
import com.bladecoder.engine.actions.Param.Type;
import com.bladecoder.engine.model.InteractiveActor;
import com.bladecoder.engine.model.SpriteActor;
import com.bladecoder.engine.model.Text;
import com.bladecoder.engine.model.TextManager;
import com.bladecoder.engine.model.World;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@ActionDescription("Action for using the 'cut_machine' with the 'cable'")
public class UseCutMachineCableAction implements Action {
	
	@JsonProperty
	@JsonPropertyDescription("Text to show when attach the cable to the cut_machine")
	@ActionPropertyType(Type.STRING)
	private String cutCableText;
	
	@JsonProperty
	@JsonPropertyDescription("Text to show when the cable can not be attached to the cut_machine")
	@ActionPropertyType(Type.STRING)
	private String defaultCableText;
	
	
	private String actorId;

	@Override
	public boolean run(ActionCallback cb) {
		SpriteActor actor = (SpriteActor)World.getInstance().getCurrentScene().getActor(actorId, true);
		InteractiveActor target = (InteractiveActor)World.getInstance().getCurrentScene().getActor("cable", true);

//		EngineLogger.debug("USING " + actor.getDesc() + " IN " + target.getDesc());

		if (target.getState().equals("CUT") && actor.getState().equals("NO_BATTERY")) {
			World.getInstance().getTextManager().addText(cutCableText, TextManager.POS_SUBTITLE,
					TextManager.POS_SUBTITLE, true, Text.Type.RECTANGLE, null, null, null);
			actor.setState("WITH_CABLE");
			actor.startAnimation("cutter.withcable", null);
			actor.playSound("click");
			World.getInstance().getInventory().removeItem((SpriteActor) target);
		} else {
			World.getInstance().getTextManager().addText(defaultCableText, TextManager.POS_SUBTITLE,
					TextManager.POS_SUBTITLE, true, Text.Type.RECTANGLE, null, null, null);
		}
		
		return false;
	}

	@Override
	public void setParams(HashMap<String, String> params) {
		actorId = params.get("actor");

		cutCableText = params.get("cut_cable_text");
		defaultCableText = params.get("default_cable_text");
	}
}
