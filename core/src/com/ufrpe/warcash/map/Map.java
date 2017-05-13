package com.ufrpe.warcash.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ufrpe.warcash.WarClash;
import com.ufrpe.warcash.utils.SimplexNoise;

import java.util.Arrays;
import java.util.List;

/**
 * Created by paulomenezes on 12/05/17.
 */

public class Map {
    private Texture texture;
    private Tile[][] tiles;

    private double[][] value;

    public Map() {
        texture = new Texture("Summer Tiles.png");

        value = new double[WarClash.WIDTH / (texture.getWidth() / 19)][WarClash.HEIGHT / (texture.getHeight() / 20)];

        for (int y = 0; y < value.length; y++) {
            for (int x = 0; x < value[y].length; x++) {
                double nx = (x * 1.0) / WarClash.WIDTH - 0.5;
                double ny = (y * 1.0) / WarClash.HEIGHT - 0.5;
                value[y][x] = noise(nx, ny);
            }
        }

        tiles = new Tile[WarClash.WIDTH / (texture.getWidth() / 19)][WarClash.HEIGHT / (texture.getHeight() / 20)];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(texture, i, j, value[i][j] > 0.5 ? TileType.WATER : TileType.GRASS);
            }
        }
    }

    public void render(SpriteBatch spriteBatch) {
        Arrays.stream(tiles).forEach(tile -> Arrays.stream(tile).forEach(t -> t.render(spriteBatch)));
    }

    public void dispose() {
        texture.dispose();
    }

    private double noise(double nx, double ny) {
        // Rescale from -1.0:+1.0 to 0.0:1.0
        return SimplexNoise.noise(nx, ny) / 2.0 + 0.5;
    }
}
