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

import java.util.Locale;

import com.bladecoder.engine.BladeEngine;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.assets.EngineAssetManager;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.UI;
import com.bladecoder.engine.util.Config;
import com.bladecoder.engine.util.EngineLogger;

@ActionDescription("Sets the volume based on preferences.")
public class UISetLang implements Action {
	World uiWorld;

	@Override
	public void init(World w) {
		uiWorld = w;
	}

	@Override
	public boolean run(VerbRunner cb) {
		// Parse languages
		UI ui = BladeEngine.getAppUI();
		World w = ui.getWorld();
		String languageProp = Config.getInstance().getProperty("languages", null);

		if (languageProp != null) {
			String[] languages = languageProp.split(",");

			// show the current language
			String current = Config.getInstance().getPref("lang", w.getI18N().getCurrentLocale().getLanguage());

			int currentLanguage = 0;
			for (int i = 0; i < languages.length; i++) {
				if (languages[i].trim().equals(current)) {
					currentLanguage = i;
					break;
				}
			}

			Locale l = Locale.ROOT;

			if (currentLanguage != 0)
				l = new Locale(languages[currentLanguage].trim());

			w.getI18N().setLocale(l);
			w.getInkManager().loadI18NBundle();

			uiWorld.getI18N().setLocale(l);

			uiWorld.getCurrentScene().dispose();
			uiWorld.getCurrentScene().loadAssets();
			EngineAssetManager.getInstance().finishLoading();
			uiWorld.getCurrentScene().retrieveAssets();
		} else {
			EngineLogger.error("Missing 'languages' property from BladeEngine.properties.");
		}

		return false;
	}
}
