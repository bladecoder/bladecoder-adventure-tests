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

import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.model.MusicManager;
import com.bladecoder.engine.model.SceneSoundManager;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.VoiceManager;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.util.Config;

@ActionDescription("Sets the volume based on preferences.")
public class UIInitPrefs implements Action {

	@Override
	public void init(World w) {
	}

	@Override
	public boolean run(VerbRunner cb) {

		MusicManager.VOLUME_MULTIPLIER = Float.parseFloat(Config.getInstance().getPref("MUSIC_VOLUME", "1"));
		SceneSoundManager.VOLUME_MULTIPLIER = Float.parseFloat(Config.getInstance().getPref("EFFECTS_VOLUME", "1"));
		VoiceManager.VOLUME_MULTIPLIER = Float.parseFloat(Config.getInstance().getPref("VOICES_VOLUME", "1"));

		return false;
	}
}
