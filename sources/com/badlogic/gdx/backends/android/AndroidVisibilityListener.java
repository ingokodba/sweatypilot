package com.badlogic.gdx.backends.android;

import android.view.View.OnSystemUiVisibilityChangeListener;

public class AndroidVisibilityListener {
    public void createListener(final AndroidApplicationBase application) {
        try {
            application.getApplicationWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener() {

                /* renamed from: com.badlogic.gdx.backends.android.AndroidVisibilityListener$1$1 */
                class C00201 implements Runnable {
                    C00201() {
                    }

                    public void run() {
                        application.useImmersiveMode(true);
                    }
                }

                public void onSystemUiVisibilityChange(int arg0) {
                    application.getHandler().post(new C00201());
                }
            });
        } catch (Throwable t) {
            application.log("AndroidApplication", "Can't create OnSystemUiVisibilityChangeListener, unable to use immersive mode.", t);
        }
    }
}
