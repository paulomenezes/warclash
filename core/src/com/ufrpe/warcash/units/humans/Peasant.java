package com.ufrpe.warcash.units.humans;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.ufrpe.warcash.units.Entity;
import com.ufrpe.warcash.utils.Utility;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by paulomenezes on 13/05/17.
 */

public class Peasant extends Entity {
    public Peasant() {
        super("Peasant_walking.png");
    }

    @Override
    protected void loadDefaultSprite() {
        Texture texture = Utility.getTextureAsset(assetName);
        TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_HEIGHT);

        frameSprite = new Sprite(textureFrames[0][0].getTexture(), 0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        currentFrame = textureFrames[0][0];
    }

    @Override
    protected void loadAllAnimations() {
        Texture texture = Utility.getTextureAsset(assetName);
        Array<Array<TextureRegion>> textureFrames = new Array<>();

        getEntityConfig().getAnimationConfig().getAnimations().forEach(animation -> {
            animation.getDirections().forEach(direction -> {
                Array<TextureRegion> regions = new Array<>();

                direction.getFrames().forEach(frame -> {
                    regions.add(new TextureRegion(texture, frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight()));
                });

                textureFrames.add(regions);
            });
        });

        frames = new HashMap<>();
        frames.put("walkingUp", textureFrames.get(0));
        frames.put("walkingUpRight", textureFrames.get(1));
        frames.put("walkingRight", textureFrames.get(2));
        frames.put("walkingDownRight", textureFrames.get(3));
        frames.put("walkingDown", textureFrames.get(4));

        Animation<TextureRegion> animationUp = new Animation<>(0.1f, frames.get("walkingUp"), Animation.PlayMode.LOOP);
        Animation<TextureRegion> animationUpRight = new Animation<>(0.1f, frames.get("walkingUpRight"), Animation.PlayMode.LOOP);
        Animation<TextureRegion> animationRight = new Animation<>(0.1f, frames.get("walkingRight"), Animation.PlayMode.LOOP);
        Animation<TextureRegion> animationDownRight = new Animation<>(0.1f, frames.get("walkingDownRight"), Animation.PlayMode.LOOP);
        Animation<TextureRegion> animationDown = new Animation<>(0.1f, frames.get("walkingDown"), Animation.PlayMode.LOOP);

        animations = new HashMap<>();
        animations.put("walkingUp", animationUp);
        animations.put("walkingUpRight", animationUpRight);
        animations.put("walkingRight", animationRight);
        animations.put("walkingDownRight", animationDownRight);
        animations.put("walkingDown", animationDown);
    }
}
