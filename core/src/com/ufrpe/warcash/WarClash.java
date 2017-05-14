package com.ufrpe.warcash;

import com.badlogic.gdx.Game;
import com.ufrpe.warcash.screens.PlayScreen;

public class WarClash extends Game {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 800;

	private PlayScreen playScreen;

	@Override
	public void create () {
		playScreen = new PlayScreen();

		setScreen(playScreen);
	}

	@Override
	public void dispose () {
		playScreen.dispose();
	}
}
