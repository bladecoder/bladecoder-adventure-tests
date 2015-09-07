package org.bladecoder.venus.actions.scene2;

import java.text.MessageFormat;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionCallback;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionPropertyType;
import com.bladecoder.engine.actions.Param.Type;
import com.bladecoder.engine.i18n.I18N;
import com.bladecoder.engine.model.Text;
import com.bladecoder.engine.model.TextManager;
import com.bladecoder.engine.model.World;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@ActionDescription("Credit scene init action")
public class Scene2InitAction implements Action {	
	@JsonProperty
	@JsonPropertyDescription("text to show with the credits")
	@ActionPropertyType(Type.STRING)
	String text;

	@Override
	public void setParams(HashMap<String, String> params) {
		text = params.get("text");
	}

	@Override
	public boolean run(ActionCallback cb) {
		World w = World.getInstance();
		//w.setCutMode(true);
		w.showInventory(false);

		String t = MessageFormat.format(I18N.getString(text.substring(1)), (int)(w.getTimeOfGame()/60));
		
		World.getInstance().getTextManager().addText(t, TextManager.POS_CENTER, TextManager.POS_CENTER,false, Text.Type.PLAIN, Color.WHITE, null, null);
		return false;
	}
}
