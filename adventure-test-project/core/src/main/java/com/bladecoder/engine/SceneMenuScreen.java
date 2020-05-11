/*******************************************************************************
 * Copyright 2014 Rafael Garcia Moreno.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.bladecoder.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Peripheral;
import com.bladecoder.engine.assets.EngineAssetManager;
import com.bladecoder.engine.model.InteractiveActor;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.model.World.AssetState;
import com.bladecoder.engine.ui.UI;
import com.bladecoder.engine.ui.defaults.DefaultSceneScreen;
import com.bladecoder.engine.util.EngineLogger;

public class SceneMenuScreen extends DefaultSceneScreen {
	private static final String POINTER_ENTER_VERB = "pointer-enter";
	private static final String POINTER_EXIT_VERB = "pointer-exit";

	private World uiWorld;

	private InteractiveActor pointerInActor = null;

	@Override
	public void setUI(final UI ui) {
		uiWorld = new World();

		super.setUI(ui);
		getMenuButton().setVisible(false);
		setShowHotspotsFeature(false);
	}

	@Override
	public World getWorld() {
		return uiWorld;
	}

	@Override
	public void render(float delta) {
		final World world = getWorld();

		if (world.getAssetState() == AssetState.LOAD_ASSETS
				|| world.getAssetState() == AssetState.LOAD_ASSETS_AND_INIT_SCENE) {
			// Load assets to avoid change to the LOADING_SCREEN
			world.update(delta);

			if (world.getAssetState() != AssetState.LOADED) {
				EngineAssetManager.getInstance().finishLoading();
			}
		}

		super.render(delta);

		// call pointer-enter/pointer-exit
		if (world.getAssetState() != AssetState.LOADED || Gdx.input.isPeripheralAvailable(Peripheral.MultitouchScreen)
				|| world.inCutMode() || world.hasDialogOptions() || world.isPaused()) {
			return;
		}

		InteractiveActor newPointerInActor = getWorld().getInteractiveActorAtInput(getViewport(), 0);

		if (newPointerInActor != pointerInActor) {
			if (pointerInActor != null && world.getCurrentScene().getActor(pointerInActor.getId(), true) != null
					&& pointerInActor.getVerb(POINTER_EXIT_VERB) != null) {
				pointerInActor.runVerb(POINTER_EXIT_VERB, null);
			}

			pointerInActor = newPointerInActor;

			if (pointerInActor != null && pointerInActor.getVerb(POINTER_ENTER_VERB) != null) {
				pointerInActor.runVerb(POINTER_ENTER_VERB, null);
			}
		}
	}

	@Override
	public void hide() {
		getWorld().dispose();
	}

	@Override
	public void show() {
		try {
			uiWorld.loadWorldDesc();
			uiWorld.loadChapter("ui", null, false);
		} catch (Exception e) {
			// dispose();
			EngineLogger.error("EXITING: " + e.getMessage());
			Gdx.app.exit();
		}

		super.show();
	}
}
