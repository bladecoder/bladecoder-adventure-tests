package com.bladecoder.engine.android;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.bladecoder.engine.BladeEngine;

import android.os.Bundle;
import android.view.WindowManager;

public class AndroidLauncher extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        cfg.useImmersiveMode = true;
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        initialize(new BladeEngine(), cfg);
    }
}