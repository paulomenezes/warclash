package com.ufrpe.warcash.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ufrpe.warcash.WarClash;
import com.ufrpe.warcash.map.Map;
import com.ufrpe.warcash.units.Entity;
import com.ufrpe.warcash.units.EntityFactory;
import com.ufrpe.warcash.units.UnitController;
import com.ufrpe.warcash.units.humans.Peasant;

/**
 * Created by paulomenezes on 12/05/17.
 */

public class PlayScreen implements Screen {
    private static final String TAG = PlayScreen.class.getSimpleName();

    private static class VIEWPORT {
        static float viewportWidth;
        static float viewportHeight;
        static float virtualWidth;
        static float virtualHeight;
        static float physicalWidth;
        static float physicalHeight;
        static float aspectRatio;
    }

    private Entity unit;
    private UnitController unitController;
    private TextureRegion currentPlayerFrame;
    private Sprite currentPlayerSprite;

    private OrthographicCamera camera;

    private Map map;

    private SpriteBatch spriteBatch;

    public PlayScreen() {
        map = new Map();
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {
        setupViewport(10, 10);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT.viewportWidth, VIEWPORT.viewportHeight);

        unit = EntityFactory.getEntity(EntityFactory.EntityType.PEASANT);
        unit.init(200, 200);

        currentPlayerSprite = unit.getFrameSprite();

        unitController = new UnitController(unit);
        Gdx.input.setInputProcessor(unitController);
    }

    private void setupViewport(int width, int height) {
        VIEWPORT.virtualWidth = width;
        VIEWPORT.virtualHeight = height;

        VIEWPORT.viewportWidth = VIEWPORT.virtualWidth;
        VIEWPORT.viewportHeight = VIEWPORT.virtualHeight;

        VIEWPORT.physicalWidth = Gdx.graphics.getWidth();
        VIEWPORT.physicalHeight = Gdx.graphics.getHeight();

        VIEWPORT.aspectRatio = VIEWPORT.virtualWidth / VIEWPORT.virtualHeight;

        if (VIEWPORT.physicalWidth / VIEWPORT.physicalHeight >= VIEWPORT.aspectRatio) {
            VIEWPORT.viewportWidth = VIEWPORT.viewportHeight * (VIEWPORT.physicalWidth / VIEWPORT.physicalHeight);
            VIEWPORT.viewportHeight = VIEWPORT.virtualHeight;
        } else {
            VIEWPORT.viewportWidth = VIEWPORT.virtualWidth;
            VIEWPORT.viewportHeight = VIEWPORT.viewportWidth * (VIEWPORT.physicalHeight / VIEWPORT.physicalWidth);
        }

        Gdx.app.debug(TAG, "WorldRenderer: virtual: (" +
                VIEWPORT.virtualWidth + "," + VIEWPORT.virtualHeight + ")" );
        Gdx.app.debug(TAG, "WorldRenderer: viewport: (" +
                VIEWPORT.viewportWidth + "," + VIEWPORT.viewportHeight + ")");
        Gdx.app.debug(TAG, "WorldRenderer: physical: (" +
                VIEWPORT.physicalWidth + "," + VIEWPORT.physicalHeight + ")");
    }

    public void update(float delta) {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //camera.position.set(currentPlayerSprite.getX(), currentPlayerSprite.getY(), 0f);
        camera.update();

        unit.update(delta);
        unit.setDirection(Entity.Direction.LEFT, delta);

        currentPlayerFrame = unit.getFrame();
        unitController.update(delta);

        map.render(spriteBatch);

        unit.render(spriteBatch);
    }

    @Override
    public void resize(int width, int height) {

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
