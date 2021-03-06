package com.ufrpe.warcash.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ufrpe.warcash.WarClash;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = WarClash.WIDTH;
        config.height = WarClash.HEIGHT;

		new LwjglApplication(new WarClash(), config);
	}
}
