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
package com.bladecoder.engine.actions.ui;

import com.badlogic.gdx.Gdx;
import com.bladecoder.engine.BladeEngine;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.UI;
import com.bladecoder.engine.ui.UI.Screens;

@ActionDescription("Continue.")
public class UIContinue implements Action {

	@Override
	public void init(World w) {

	}

	@Override
	public boolean run(VerbRunner cb) {
		UI ui = BladeEngine.getAppUI();
		World world = ui.getWorld();

		if (world.getCurrentScene() == null) {
			try {
				world.load();
			} catch (Exception e) {
				Gdx.app.exit();
			}
		}

		ui.setCurrentScreen(Screens.SCENE_SCREEN);

		return false;
	}
}
