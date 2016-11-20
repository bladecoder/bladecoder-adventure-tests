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
package org.bladecoder.venus;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.bladecoder.engine.assets.EngineAssetManager;
import com.bladecoder.engine.i18n.I18N;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.MenuScreen;
import com.bladecoder.engine.ui.UI;
import com.bladecoder.engine.util.Config;
import com.bladecoder.engine.util.DPIUtils;
import com.bladecoder.engine.util.EngineLogger;

public class VenusMenuScreen extends MenuScreen {
	String PREFS_FILENAME = "prefs.properties";

	int currentLanguage = 0;
	String languages[];

	// Preferences
	final Properties prefs = new Properties();

	String bonaseraURL = null;

	@Override
	public void show() {

		setPrefLang();

		super.show();

		saveGameIOSFix();
		
		addLangButton();

		addLinkButtons();
	}

	private void saveGameIOSFix() {
		if (Gdx.app.getType() == ApplicationType.iOS && World.getInstance().getCurrentScene() != null) {

			try {
				World.getInstance().saveGameState();
			} catch (IOException e) {
				EngineLogger.error(e.getMessage());
			}

		}
	}

	private void setPrefLang() {

		// Parse languages
		String languageProp = Config.getProperty("languages", null);

		if (EngineAssetManager.getInstance().getUserFile(PREFS_FILENAME).exists()) {
			try {
				prefs.load(EngineAssetManager.getInstance().getUserFile(PREFS_FILENAME).reader());
			} catch (IOException e) {
				EngineLogger.error("ERROR LOADING PREFERENCES: " + e.getMessage());
			}
		}

		if (languageProp != null) {
			if (languages == null)
				languages = languageProp.split(",");

			// show the current language
			String current = prefs.getProperty("lang", I18N.getCurrentLocale().getLanguage());

			for (int i = 0; i < languages.length; i++) {
				if (languages[i].trim().equals(current)) {
					currentLanguage = i;
					break;
				}
			}

			Locale l = Locale.ROOT;

			if (currentLanguage != 0)
				l = new Locale(languages[currentLanguage].trim());

			I18N.setLocale(l);
		}

	}

	private void addLangButton() {
		if (languages != null && languages.length > 0) {

			Button languageButton = new Button(
					getUI().getSkin().getDrawable("lang_" + languages[currentLanguage].trim()), null);

			languageButton.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {

					currentLanguage = (currentLanguage + 1) % languages.length;

					Locale l = Locale.ROOT;

					if (currentLanguage != 0)
						l = new Locale(languages[currentLanguage].trim());

					prefs.setProperty("lang", l.getLanguage());
					try {
						prefs.store(EngineAssetManager.getInstance().getUserFile(PREFS_FILENAME).writer(false), null);
					} catch (IOException e) {
						EngineLogger.error("ERROR SAVING PREFERENCES: " + e.getMessage());
					}

					getUI().setCurrentScreen(UI.Screens.MENU_SCREEN);
				}
			});

			getIconStackTable().row();
			getIconStackTable().add(languageButton);
		}
	}
	
	private void addLinkButtons() {
		
		if (Gdx.app.getType() == ApplicationType.Desktop) {
			bonaseraURL = Config.getProperty("bonasera_desktop_url", null);
		} else if (Gdx.app.getType() == ApplicationType.Android) {
			bonaseraURL = Config.getProperty("bonasera_android_url", null);
			
			// Delete exit button
			getMenuButtonTable().getCells().get(getMenuButtonTable().getCells().size - 1).pad(0).clearActor();
		} else if (Gdx.app.getType() == ApplicationType.iOS) {
			bonaseraURL = Config.getProperty("bonasera_ios_url", null);
			
			// Delete exit button
			getMenuButtonTable().getCells().get(getMenuButtonTable().getCells().size - 1).pad(0).clearActor();
		}

		// Show Johnny Bonasera button
		if (bonaseraURL != null) {

			// LEFT TABLE
			Table leftTable = new Table();
			leftTable.defaults().pad(DPIUtils.getSpacing(), 0, DPIUtils.getSpacing(), 0);
			leftTable.pad(0, DPIUtils.getSpacing(), DPIUtils.getSpacing(), 0);
			leftTable.center().bottom();
			leftTable.setFillParent(true);

			Table bonaseraButton = new Table();
			bonaseraButton.defaults().pad(0, DPIUtils.getMarginSize() * .25f, 0, DPIUtils.getMarginSize() * .25f);

			Image image = new Image(getUI().getSkin().getDrawable("bonasera_icon"));
			image.setScaling(Scaling.fit);
			bonaseraButton.add(image).size(DPIUtils.getPrefButtonSize() * 1.4f);
			bonaseraButton.add(new Label("[YELLOW] FULL FEATURED ADVENTURE!\nThe Revenge of Johnny Bonasera[]", getUI().getSkin(), "ui-dialog"));

			bonaseraButton.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					Gdx.net.openURI(bonaseraURL);
				}
			});

//			leftTable.add(bonaseraButton);
			getMenuButtonTable().add(bonaseraButton);

			leftTable.pack();
			getMenuButtonTable().getStage().addActor(leftTable);
		}
		
	}
}
