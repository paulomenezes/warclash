package com.ufrpe.warcash.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ufrpe.warcash.WarClash;
import com.ufrpe.warcash.utils.SimplexNoise;

import java.util.Arrays;

public class Map {
    private Texture texture;
    private Tile[][] tiles;

    public Map() {
        texture = new Texture("Summer Tiles.png");

        int w = WarClash.WIDTH / (texture.getWidth() / 19);
        int h = WarClash.HEIGHT / (texture.getHeight() / 20);

        double[][] value = new double[w][h];

        for (int y = 0; y < value.length; y++) {
            for (int x = 0; x < value[y].length; x++) {
                double nx = (x * 1.0) / 32 - 0.5;
                double ny = (y * 1.0) / 32 - 0.5;
                value[y][x] = noise(nx, ny);
            }
        }

        tiles = new Tile[w][h];
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
