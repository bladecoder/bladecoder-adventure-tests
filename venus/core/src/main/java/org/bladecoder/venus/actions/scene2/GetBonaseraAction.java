package org.bladecoder.venus.actions.scene2;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.util.Config;

@ActionDescription("Open the Bonasera Store Page")
public class GetBonaseraAction implements Action {

	@Override
	public void init(World w) {
	}

	@Override
	public boolean run(VerbRunner cb) {
		String bonaseraURL = null;

		if (Gdx.app.getType() == ApplicationType.Desktop) {
			bonaseraURL = Config.getInstance().getProperty("bonasera_desktop_url", null);
		} else if (Gdx.app.getType() == ApplicationType.Android) {
			bonaseraURL = Config.getInstance().getProperty("bonasera_android_url", null);
		} else if (Gdx.app.getType() == ApplicationType.iOS) {
			bonaseraURL = Config.getInstance().getProperty("bonasera_ios_url", null);
		}

		if (bonaseraURL != null)
			Gdx.net.openURI(bonaseraURL);

		return false;
	}
}
