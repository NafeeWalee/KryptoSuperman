package com.pureinternational.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pureinternational.game.KryptoSuperman;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = KryptoSuperman.WIDTH;
        config.height = KryptoSuperman.HEIGHT;
        config.title = KryptoSuperman.TITLE;
        new LwjglApplication(new KryptoSuperman(), config);
    }
}
