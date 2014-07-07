package org.bladecoder.venus.actions.scene2;

import java.text.MessageFormat;
import java.util.HashMap;

import org.bladecoder.engine.actions.Action;
import org.bladecoder.engine.actions.Param;
import org.bladecoder.engine.actions.Param.Type;
import org.bladecoder.engine.i18n.I18N;
import org.bladecoder.engine.model.Text;
import org.bladecoder.engine.model.TextManager;
import org.bladecoder.engine.model.World;
import org.bladecoder.engine.util.EngineLogger;

import com.badlogic.gdx.graphics.Color;

public class Scene2InitAction implements Action {
	public static final String INFO = "Credit scene init action";
	public static final Param[] PARAMS = {
		new Param("text", "text to show with the credits", Type.STRING)
		};		
	
	String text;

	@Override
	public void setParams(HashMap<String, String> params) {
			text = params.get("text");
	}

	@Override
	public void run() {	
		EngineLogger.debug("SCENE2 INIT ");

		World w = World.getInstance();
		//w.setCutMode(true);
		w.showInventory(false);

		String t = MessageFormat.format(I18N.getString(text.substring(1)), (int)(w.getTimeOfGame()/60));
		
		World.getInstance().getTextManager().addSubtitle(t, TextManager.POS_CENTER, TextManager.POS_CENTER,false, Text.Type.PLAIN, Color.WHITE, null);

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
