package org.bladecoder.venus.actions.scene2;

import com.bladecoder.engine.BladeEngine;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionCallback;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.UI;
import com.bladecoder.engine.util.EngineLogger;

@ActionDescription("Restarts the game")
public class LeaveAction implements Action {

	@Override
	public boolean run(ActionCallback cb) {
		try {
			World.getInstance().newGame();
		} catch (Exception e) {
			EngineLogger.error(e.getMessage());
		}

		UI ui = BladeEngine.getAppUI();

		World.getInstance().pause();

		ui.setCurrentScreen(UI.Screens.CREDIT_SCREEN);
		
		return false;
	}
}
