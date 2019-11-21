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
import com.bladecoder.engine.assets.EngineAssetManager;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.model.World.AssetState;
import com.bladecoder.engine.ui.UI;
import com.bladecoder.engine.ui.defaults.DefaultSceneScreen;
import com.bladecoder.engine.util.EngineLogger;

public class SceneMenuScreen extends DefaultSceneScreen {

	private World uiWorld;

	@Override
	public void setUI(final UI ui) {
		uiWorld = new World();

		try {
			uiWorld.loadWorldDesc();
			uiWorld.loadChapter("ui", null, false);
		} catch (Exception e) {
			// dispose();
			EngineLogger.error("EXITING: " + e.getMessage());
			Gdx.app.exit();
		}

		super.setUI(ui);

		getMenuButton().setVisible(false);
	}

	@Override
	public World getWorld() {
		return uiWorld;
	}

	@Override
	public void render(float delta) {
		final World world = getWorld();

		if (world.getAssetState() != AssetState.LOADED) {
			// Load assets to avoid change to the LOADING_SCREEN
			EngineAssetManager.getInstance().finishLoading();
		}

		super.render(delta);
	}

}
