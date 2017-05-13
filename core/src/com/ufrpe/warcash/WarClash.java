package com.ufrpe.warcash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ufrpe.warcash.screens.PlayScreen;

public class WarClash extends Game {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 800;

	public SpriteBatch spriteBatch;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
        Gdx.graphics.setWindowedMode(WIDTH, HEIGHT);

		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
        super.render();
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
	}
}
