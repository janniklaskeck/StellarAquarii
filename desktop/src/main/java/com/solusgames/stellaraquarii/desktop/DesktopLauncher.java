package com.solusgames.stellaraquarii.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.solusgames.stellaraquarii.StellarAquariiMain;

public class DesktopLauncher {

    private DesktopLauncher() {
    }

    public static void main(String[] arg) {

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 960;
        config.height = 540;
        config.vSyncEnabled = true;
        config.resizable = false;
        config.title = "Stellar Aquarii";
        new LwjglApplication(new StellarAquariiMain(), config);// NOSONAR
    }
}
