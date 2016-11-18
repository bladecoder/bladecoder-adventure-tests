package org.bladecoder.venus;


import com.badlogic.gdx.ScreenAdapter;
import com.bladecoder.engine.ui.BladeScreen;
import com.bladecoder.engine.ui.UI;
import com.bladecoder.engine.ui.UI.Screens;

public class InitScreen extends ScreenAdapter implements BladeScreen {
	
	private UI ui;

	@Override
	public void setUI(UI ui) {
		this.ui = ui;
	}
	
	@Override
	public void resize(int width, int height) {
		ui.setCurrentScreen(Screens.MENU_SCREEN);
	}

}
