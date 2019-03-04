package com.ingokodba.com.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.ingokodba.com.PilotGame;

public class AndroidLauncher extends AndroidApplication {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new PilotGame(), new AndroidApplicationConfiguration());
    }

    protected void onPause() {
        super.onPause();
        super.onStop();
    }
}
