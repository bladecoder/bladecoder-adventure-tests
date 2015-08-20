package org.bladecoder.venus.actions.scene2;

import java.util.HashMap;

import com.bladecoder.engine.BladeEngine;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionCallback;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.UI;

@ActionDescription("Restarts the game")
public class LeaveAction implements Action {
	@Override
	public void setParams(HashMap<String, String> params) {
	}

	@Override
	public boolean run(ActionCallback cb) {
		World.getInstance().newGame();

		UI ui = BladeEngine.getAppUI();

		World.getInstance().pause();

		ui.setCurrentScreen(UI.Screens.CREDIT_SCREEN);
		
		return false;
	}
}
