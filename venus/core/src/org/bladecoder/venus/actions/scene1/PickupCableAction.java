package org.bladecoder.venus.actions.scene1;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionCallback;
import com.bladecoder.engine.actions.Param;
import com.bladecoder.engine.actions.Param.Type;
import com.bladecoder.engine.anim.Tween;
import com.bladecoder.engine.model.SpriteActor;
import com.bladecoder.engine.model.Text;
import com.bladecoder.engine.model.TextManager;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.util.EngineLogger;

public class PickupCableAction implements Action, ActionCallback {
	public static final String INFO = "Action for pickup the cable";
	public static final Param[] PARAMS = {
		new Param("connect_text", "The text to show when connect the cable", Type.STRING),
		new Param("disconnect_text", "The text to show when disconnect the cable", Type.STRING)
		};		

	private String connectText;
	private String disconnectText;
	
	private boolean goTo = false;

	@Override
	public void run() {
		SpriteActor actor = (SpriteActor)World.getInstance().getCurrentScene().getActor("cable", false);

		EngineLogger.debug("PICKUP " + actor.getDesc());

		SpriteActor player = World.getInstance().getCurrentScene().getPlayer();

		player.goTo(new Vector2(actor.getX() + actor.getWidth(), actor.getY()), this);
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
				player.startFrameAnimation("crouch.left", Tween.FROM_FA, 1, this);
			}
			
		} else {  // 2. PICKUP THE CABLE

			SpriteActor cabinet_off = (SpriteActor) World.getInstance().getCurrentScene()
					.getActor("cabinet_off", false);

			if (actor.getState().equals("CONNECTED")) {
				World.getInstance().getTextManager().addSubtitle(disconnectText, TextManager.POS_SUBTITLE,
						TextManager.POS_SUBTITLE, true, Text.Type.RECTANGLE, Color.BLACK, null);
				actor.setState("DISCONNECTED");
				actor.playSound("switch");
				actor.startFrameAnimation("cable.disconnected", null);
				player.startFrameAnimation("crouch.left", Tween.REVERSE, 1, null);
				cabinet_off.setVisible(true);
			} else if (actor.getState().equals("DISCONNECTED")) {
				World.getInstance().getTextManager().addSubtitle(connectText, TextManager.POS_SUBTITLE,
						TextManager.POS_SUBTITLE, true, Text.Type.RECTANGLE, Color.BLACK, null);
				actor.setState("CONNECTED");
				actor.playSound("switch");
				actor.startFrameAnimation("cable.connected", null);
				player.startFrameAnimation("crouch.left", Tween.REVERSE, 1, null);
				cabinet_off.setVisible(false);
			}
		}
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
