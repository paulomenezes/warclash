package com.ufrpe.warcash.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by paulomenezes on 13/05/17.
 */

public class Utility {
    public static final AssetManager assetManager = new AssetManager();

    private static final String TAG = Utility.class.getSimpleName();
    private static final InternalFileHandleResolver filePathResolver = new InternalFileHandleResolver();

    public static void unloadAsset(String assetFilenamePath) {
        if (assetManager.isLoaded(assetFilenamePath)) {
            assetManager.unload(assetFilenamePath);
        } else {
            Gdx.app.debug(TAG, "Asset is not loaded: " + assetFilenamePath);
        }
    }

    public static float loadCompleted() {
        return assetManager.getProgress();
    }

    public static int numberAssetsQueued() {
        return assetManager.getQueuedAssets();
    }

    public static boolean updateAssetLoading() {
        return assetManager.update();
    }

    public static boolean isAssetLoaded(String fileName) {
        return assetManager.isLoaded(fileName);
    }

    public static void loadTextureAsset(String texturePath) {
        if (texturePath == null || texturePath.isEmpty()) {
            return;
        }

        if (filePathResolver.resolve(texturePath).exists()) {
            assetManager.setLoader(Texture.class, new TextureLoader(filePathResolver));
            assetManager.load(texturePath, Texture.class);
            assetManager.finishLoadingAsset(texturePath);
        } else {
            Gdx.app.debug(TAG, "Texture does not exist! " + texturePath);
        }
    }

    public static Texture getTextureAsset(String texturePath) {
        Texture texture = null;

        if (assetManager.isLoaded(texturePath)) {
            texture = assetManager.get(texturePath, Texture.class);
        } else {
            Gdx.app.debug(TAG, "Texture is not loaded: " + texturePath);
        }

        return texture;
    }
}
