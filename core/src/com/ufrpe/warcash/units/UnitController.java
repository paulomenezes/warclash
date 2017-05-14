package com.ufrpe.warcash.units;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paulomenezes on 13/05/17.
 */

public class UnitController implements InputProcessor {
    enum Mouse {
        SELECT, DOACTION
    }

    private static Map<Mouse, Boolean> mouseButtons = new HashMap<>();
    private Vector3 lastMosueCoordinates;

    static {
        mouseButtons.put(Mouse.SELECT, false);
        mouseButtons.put(Mouse.DOACTION, false);
    }

    private Entity entity;

    public UnitController(Entity entity) {
        this.lastMosueCoordinates = new Vector3();
        this.entity = entity;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT || button == Input.Buttons.RIGHT) {
            this.setClickedMouseCoordinates(screenX, screenY);
        }

        if (button == Input.Buttons.LEFT) {
            this.selectMouseButtonPressed(screenX, screenY);
        }

        if (button == Input.Buttons.RIGHT) {
            this.doActionMouseButtonPressed(screenX, screenY);
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            this.selectMouseButtonRelease(screenX, screenY);
        }

        if (button == Input.Buttons.RIGHT) {
            this.doActionMouseButtonRelease(screenX, screenY);
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private void setClickedMouseCoordinates(int screenX, int screenY) {
        lastMosueCoordinates.set(screenX, screenY, 0);
    }

    private void selectMouseButtonPressed(int screenX, int screenY) {
        mouseButtons.put(Mouse.SELECT, true);
    }

    private void doActionMouseButtonPressed(int screenX, int screenY) {
        mouseButtons.put(Mouse.DOACTION, true);
    }

    private void selectMouseButtonRelease(int screenX, int screenY) {
        mouseButtons.put(Mouse.DOACTION, false);
    }

    private void doActionMouseButtonRelease(int screenX, int screenY) {
        mouseButtons.put(Mouse.DOACTION, false);
    }

    public void update(float delta) {
        proccessInput(delta);
    }

    private void proccessInput(float delta) {
        if (mouseButtons.get(Mouse.SELECT)) {
            mouseButtons.put(Mouse.SELECT, false);
        }
    }
}
