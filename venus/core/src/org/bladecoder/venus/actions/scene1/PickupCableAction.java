package org.bladecoder.venus.actions.scene1;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionCallback;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionPropertyType;
import com.bladecoder.engine.actions.Param.Type;
import com.bladecoder.engine.anim.Tween;
import com.bladecoder.engine.model.CharacterActor;
import com.bladecoder.engine.model.SpriteActor;
import com.bladecoder.engine.model.Text;
import com.bladecoder.engine.model.TextManager;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.util.EngineLogger;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@ActionDescription("Action for conecting/disconecting the cable")
public class PickupCableAction implements Action, ActionCallback {
	@JsonProperty
	@JsonPropertyDescription("The text to show when connect the cable")
	@ActionPropertyType(Type.STRING)
	private String connectText;
	
	@JsonProperty
	@JsonPropertyDescription("The text to show when disconnect the cable")
	@ActionPropertyType(Type.STRING)
	private String disconnectText;
	
	private boolean goTo = false;

	@Override
	public boolean run(ActionCallback cb) {
		SpriteActor actor = (SpriteActor)World.getInstance().getCurrentScene().getActor("cable", false);

		EngineLogger.debug("PICKUP " + actor.getDesc());

		CharacterActor player = World.getInstance().getCurrentScene().getPlayer();

		goTo = false;
		player.goTo(new Vector2(actor.getX() + actor.getWidth(), actor.getY()), this);
		
		return false;
	}

	@Override
	public void setParams(HashMap<String, String> params) {
		connectText = params.get("connect_text");
		disconnectText = params.get("disconnect_text");
	}

	@Override
	public void resume() {
		SpriteActor player = World.getInstance().getCurrentScene().getPlayer();
		SpriteActor actor = (SpriteActor) World.getInstance().getCurrentScene().getActor("cable", false);

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
				World.getInstance().getTextManager().addText(disconnectText, TextManager.POS_SUBTITLE,
						TextManager.POS_SUBTITLE, true, Text.Type.RECTANGLE, Color.BLACK, null, null);
				actor.setState("DISCONNECTED");
				actor.playSound("switch");
				actor.startAnimation("cable.disconnected", null);
				player.startAnimation("crouch.left", Tween.Type.REVERSE, 1, null);
				cabinet_off.setVisible(true);
			} else if (actor.getState().equals("DISCONNECTED")) {
				World.getInstance().getTextManager().addText(connectText, TextManager.POS_SUBTITLE,
						TextManager.POS_SUBTITLE, true, Text.Type.RECTANGLE, Color.BLACK, null, null);
				actor.setState("CONNECTED");
				actor.playSound("switch");
				actor.startAnimation("cable.connected", null);
				player.startAnimation("crouch.left", Tween.Type.REVERSE, 1, null);
				cabinet_off.setVisible(false);
			}
		}
	}
}
