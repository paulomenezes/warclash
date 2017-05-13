package com.ufrpe.warcash.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by paulomenezes on 12/05/17.
 */

public class Tile {
    private TextureRegion texture;
    private int x;
    private int y;

    public Tile(Texture texture, int x, int y, TileType type) {
        this.x = x;
        this.y = y;

        int offsetX = type == TileType.GRASS ? 5 : 14;
        int offsetY = type == TileType.GRASS ? 17 : 18;

        this.texture = new TextureRegion(texture, offsetX * 33, offsetY * 33, 32, 32);
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(texture, x * 32, y * 32, 32, 32);
        spriteBatch.end();
    }
}
