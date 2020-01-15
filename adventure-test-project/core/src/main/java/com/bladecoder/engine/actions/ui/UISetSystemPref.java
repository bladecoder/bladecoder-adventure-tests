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

import com.bladecoder.engine.BladeEngine;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionProperty;
import com.bladecoder.engine.actions.ActionPropertyDescription;
import com.bladecoder.engine.model.MusicManager;
import com.bladecoder.engine.model.SceneSoundManager;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.VoiceManager;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.UI;
import com.bladecoder.engine.util.Config;

@ActionDescription("Sets some preference value for a system.")
public class UISetSystemPref implements Action {
	private enum System {
		MUSIC_VOLUME, EFFECTS_VOLUME, VOICES_VOLUME
	};

	@ActionProperty(required = true)
	@ActionPropertyDescription("The system.")
	private System system = System.MUSIC_VOLUME;

	@ActionProperty(required = true)
	@ActionPropertyDescription("The value.")
	private String value;

	World uiWorld;

	@Override
	public void init(World w) {
		uiWorld = w;
	}

	@Override
	public boolean run(VerbRunner cb) {

		UI ui = BladeEngine.getAppUI();
		World gameWorld = ui.getWorld();

		if (system == System.MUSIC_VOLUME) {
			MusicManager.VOLUME_MULTIPLIER = Float.parseFloat(value);

			uiWorld.getMusicManager().setVolume(uiWorld.getMusicManager().getVolume());
			gameWorld.getMusicManager().setVolume(gameWorld.getMusicManager().getVolume());

		} else if (system == System.EFFECTS_VOLUME) {
			SceneSoundManager.VOLUME_MULTIPLIER = Float.parseFloat(value);
		} else if (system == System.VOICES_VOLUME) {
			VoiceManager.VOLUME_MULTIPLIER = Float.parseFloat(value);

			uiWorld.getCurrentScene().getTextManager().getVoiceManager()
					.setVolume(uiWorld.getCurrentScene().getTextManager().getVoiceManager().getVolume());
			gameWorld.getCurrentScene().getTextManager().getVoiceManager()
					.setVolume(gameWorld.getCurrentScene().getTextManager().getVoiceManager().getVolume());
		}

		Config.getInstance().setPref(system.name(), value);
		Config.getInstance().savePrefs();

		return false;
	}
}
