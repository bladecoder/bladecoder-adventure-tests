package org.bladecoder.venus.actions.scene2;

import java.text.MessageFormat;

import com.badlogic.gdx.graphics.Color;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionProperty;
import com.bladecoder.engine.actions.ActionPropertyDescription;
import com.bladecoder.engine.i18n.I18N;
import com.bladecoder.engine.model.Text;
import com.bladecoder.engine.model.TextManager;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;

@ActionDescription("Credit scene init action")
public class Scene2InitAction implements Action {	
	@ActionProperty
	@ActionPropertyDescription("text to show with the credits")
	String text;
	
	
	private World w;
	
	@Override
	public void setWorld(World w) {
		this.w = w;
	}

	@Override
	public boolean run(VerbRunner cb) {
		//w.setCutMode(true);
		w.showInventory(false);

		String t = MessageFormat.format(I18N.getString(text.substring(1)), (int)(w.getTimeOfGame() / 60000));
		
		w.getCurrentScene().getTextManager().addText(t, TextManager.POS_CENTER, TextManager.POS_CENTER,false, Text.Type.PLAIN, Color.WHITE, null, null, null, cb);
		return true;
	}
}
