package org.bladecoder.venus.actions.scene1;

import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionProperty;
import com.bladecoder.engine.actions.ActionPropertyDescription;
import com.bladecoder.engine.model.SpriteActor;
import com.bladecoder.engine.model.Text;
import com.bladecoder.engine.model.TextManager;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;

@ActionDescription("Action for using the 'cut_machine' with the 'cable'")
public class UseCutMachineCableAction implements Action {

	@ActionProperty
	@ActionPropertyDescription("Text to show when attach the cable to the cut_machine")
	private String cutCableText;

	@ActionProperty
	@ActionPropertyDescription("Text to show when the cable can not be attached to the cut_machine")
	private String defaultCableText;

	private World w;

	@Override
	public void init(World w) {
		this.w = w;
	}

	@Override
	public boolean run(VerbRunner cb) {
		SpriteActor a = (SpriteActor) w.getCurrentScene().getActor("cutter", true);
		SpriteActor target = (SpriteActor) w.getCurrentScene().getActor("cable", true);

//		EngineLogger.debug("USING " + actor.getDesc() + " IN " + target.getDesc());

		if (target.getState().equals("CUT") && a.getState().equals("NO_BATTERY")) {
			w.getCurrentScene().getTextManager().addText(cutCableText, TextManager.POS_SUBTITLE,
					TextManager.POS_SUBTITLE, true, Text.Type.SUBTITLE, null, null, null, null, null, null);
			a.setState("WITH_CABLE");
			a.startAnimation("cutter.withcable", null);
			w.getCurrentScene().getSoundManager().playSound(a.getId() + "_" + "click");
			w.getInventory().removeItem(target);
			target.dispose();
			w.getScene("limbo").addActor(target);
		} else {
			w.getCurrentScene().getTextManager().addText(defaultCableText, TextManager.POS_SUBTITLE,
					TextManager.POS_SUBTITLE, true, Text.Type.SUBTITLE, null, null, null, null, null, null);
		}

		return false;
	}
}
