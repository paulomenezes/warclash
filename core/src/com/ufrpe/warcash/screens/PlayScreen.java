package com.ufrpe.warcash.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ufrpe.warcash.WarClash;
import com.ufrpe.warcash.map.Map;

/**
 * Created by paulomenezes on 12/05/17.
 */

public class PlayScreen implements Screen {
    private WarClash game;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Map map;

    public PlayScreen(WarClash game) {
        this.game = game;

        camera = new OrthographicCamera(WarClash.WIDTH, WarClash.HEIGHT);
        camera.setToOrtho(false);

        viewport = new ScreenViewport(camera);

        map = new Map();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.spriteBatch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        map.render(game.spriteBatch);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
    }
}
