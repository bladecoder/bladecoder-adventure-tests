package org.bladecoder.venus.actions.scene2;

import java.util.HashMap;

import org.bladecoder.engine.BladeEngine;
import org.bladecoder.engine.actions.Action;
import org.bladecoder.engine.actions.ActionCallback;
import org.bladecoder.engine.actions.Param;
import org.bladecoder.engine.model.World;
import org.bladecoder.engine.ui.UI;

public class LeaveAction implements Action {
	public static final String INFO = "Restarts the game";
	public static final Param[] PARAMS = {};

	@Override
	public void setParams(HashMap<String, String> params) {
	}

	@Override
	public void run() {
		World.getInstance().newGame();

		UI ui = BladeEngine.getAppUI();

		World.getInstance().pause();

		ui.setScreen(UI.State.CREDIT_SCREEN);
	}

	@Override
	public String getInfo() {
		return INFO;
	}

	@Override
	public Param[] getParams() {
		return PARAMS;
	}

	@Override
	public boolean waitForFinish(ActionCallback cb) {
		return false;
	}
}
