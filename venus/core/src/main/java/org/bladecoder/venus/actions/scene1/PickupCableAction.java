package org.bladecoder.venus.actions.scene1;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionCallback;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionProperty;
import com.bladecoder.engine.actions.ActionPropertyDescription;
import com.bladecoder.engine.anim.Tween;
import com.bladecoder.engine.model.CharacterActor;
import com.bladecoder.engine.model.SpriteActor;
import com.bladecoder.engine.model.Text;
import com.bladecoder.engine.model.TextManager;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.util.EngineLogger;

@ActionDescription("Action for conecting/disconecting the cable")
public class PickupCableAction implements Action, ActionCallback, Serializable {
	@ActionProperty
	@ActionPropertyDescription("The text to show when connect the cable")
	private String connectText;
	
	@ActionProperty
	@ActionPropertyDescription("The text to show when disconnect the cable")
	private String disconnectText;
	
	private boolean goTo = false;
	
	
	private World w;
	
	@Override
	public void setWorld(World w) {
		this.w = w;
	}

	@Override
	public boolean run(VerbRunner cb) {
		SpriteActor actor = (SpriteActor)w.getCurrentScene().getActor("cable", false);

		EngineLogger.debug("PICKUP " + actor.getDesc());

		CharacterActor player = w.getCurrentScene().getPlayer();

		goTo = false;
		player.goTo(new Vector2(actor.getX() + actor.getWidth(), actor.getY()), this, false);
		
		return false;
	}

	@Override
	public void resume() {
		SpriteActor player = w.getCurrentScene().getPlayer();
		SpriteActor actor = (SpriteActor) w.getCurrentScene().getActor("cable", false);

		if (!goTo) { // 1. GO TO THE CABLE
			goTo = true;
			
			Vector2 p = new Vector2(actor.getX(), actor.getY());

			if (Math.abs(p.x - player.getBBox().getBoundingRectangle().x) < player.getWidth()) {
				player.startAnimation("crouch.left", Tween.Type.SPRITE_DEFINED, 1, this);
			}
			
		} else {  // 2. PICKUP THE CABLE

			SpriteActor cabinet_off = (SpriteActor) World.getInstance().getCurrentScene()
					.getActor("cabinet_off", false);

			if (actor.getState().equals("CONNECTED")) {
				w.getCurrentScene().getTextManager().addText(disconnectText, TextManager.POS_SUBTITLE,
						TextManager.POS_SUBTITLE, true, Text.Type.SUBTITLE, Color.BLACK, null, null, null, null);
				actor.setState("DISCONNECTED");
				w.getCurrentScene().getSoundManager().playSound(actor.getId() + "_" +  "switch");
				actor.startAnimation("cable.disconnected", null);
				player.startAnimation("crouch.left", Tween.Type.REVERSE, 1, null);
				cabinet_off.setVisible(true);
			} else if (actor.getState().equals("DISCONNECTED")) {
				w.getCurrentScene().getTextManager().addText(connectText, TextManager.POS_SUBTITLE,
						TextManager.POS_SUBTITLE, true, Text.Type.SUBTITLE, Color.BLACK, null, null, null, null);
				actor.setState("CONNECTED");
				w.getCurrentScene().getSoundManager().playSound(actor.getId() + "_" +  "switch");
				actor.startAnimation("cable.connected", null);
				player.startAnimation("crouch.left", Tween.Type.REVERSE, 1, null);
				cabinet_off.setVisible(false);
			}
		}
	}
	
	@Override
	public void write(Json json) {
		json.writeValue("goTo", goTo);
	}

	@Override
	public void read (Json json, JsonValue jsonData) {
		goTo = json.readValue("goTo", Boolean.class, jsonData);
	}
}
