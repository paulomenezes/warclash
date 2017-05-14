package com.ufrpe.warcash.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.ufrpe.warcash.models.EntityConfig;
import com.ufrpe.warcash.utils.Utility;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by paulomenezes on 13/05/17.
 */

public abstract class Entity {
    private static final String TAG = Entity.class.getSimpleName();

    private Json json;
    private EntityConfig entityConfig;

    protected String assetName;

    private Vector2 velocity;
    private String entityID;

    private Direction currentDirection = Direction.LEFT;
    private Direction previousDirection = Direction.UP;

    protected HashMap<String, Animation> animations;
    protected HashMap<String, Array<TextureRegion>> frames;

    protected Vector2 nextPosition;
    protected Vector2 currentPosition;

    protected State state = State.IDLE;

    protected float frameTime = 0f;
    protected Sprite frameSprite = null;
    protected TextureRegion currentFrame = null;

    private boolean rotate = false;

    public final int FRAME_WIDTH = 32;
    public final int FRAME_HEIGHT = 32;

    public static Rectangle boundingBox;

    public enum State { IDLE, WALKING }
    public enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT;

        public Direction getOpposite() {
            if (this == LEFT) return RIGHT;
            else if (this == RIGHT) return LEFT;
            else if (this == UP) return DOWN;
            else return UP;
        }
    }
    public static enum AnimationType {
        WALK_LEFT, WALK_RIGHT, WALK_UP, WALK_DOWN
    }

    public Entity(String spritePath) {
        this.assetName = spritePath;
        this.entityID = UUID.randomUUID().toString();
        this.nextPosition = new Vector2();
        this.currentPosition = new Vector2();
        this.boundingBox = new Rectangle();
        this.velocity = new Vector2(2f, 2f);

        entityConfig = new EntityConfig();
        json = new Json();

        Utility.loadTextureAsset(spritePath);
        loadDefaultSprite();
    }

    public void update(float delta) {
        frameTime = (frameTime + delta) % 5;
        boundingBox = new Rectangle(nextPosition.x, nextPosition.y, FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        if (!rotate) {
            spriteBatch.draw(getFrame(), getCurrentPosition().x, getCurrentPosition().y);
        } else {
            spriteBatch.draw(getFrame(), getCurrentPosition().x + getFrame().getRegionWidth(), getCurrentPosition().y, -getFrame().getRegionWidth(), getFrame().getRegionHeight());
        }
        spriteBatch.end();
    }

    public void init(float startX, float startY) {
        this.currentPosition.x = startX;
        this.currentPosition.y = startY;

        this.nextPosition.x = startX;
        this.nextPosition.y = startY;
    }

    protected abstract void loadDefaultSprite();
    protected abstract void loadAllAnimations();

    public void dispose() {
        Utility.unloadAsset(assetName);
    }

    public void setState(State state) {
        this.state = state;
    }

    public Sprite getFrameSprite() {
        return frameSprite;
    }

    public TextureRegion getFrame() {
        return currentFrame;
    }

    public Vector2 getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(float posX, float posY) {
        frameSprite.setX(posX);
        frameSprite.setY(posY);

        currentPosition.x = posX;
        currentPosition.y = posY;
    }

    public void setDirection(Direction direction, float deltaTime) {
        previousDirection = this.currentDirection;
        currentDirection = direction;

        switch (currentDirection) {
            case DOWN:
                currentFrame = (TextureRegion) animations.get("walkingDown").getKeyFrame(frameTime);
                rotate = false;
                break;
            case LEFT:
                currentFrame = (TextureRegion) animations.get("walkingRight").getKeyFrame(frameTime);
                rotate = true;
                break;
            case RIGHT:
                currentFrame = (TextureRegion) animations.get("walkingRight").getKeyFrame(frameTime);
                rotate = false;
                break;
            case UP:
                currentFrame = (TextureRegion) animations.get("walkingUp").getKeyFrame(frameTime);
                rotate = false;
                break;
        }
    }

    public void setNextPosition() {
        setCurrentPosition(nextPosition.x, nextPosition.y);
    }

    public void calculateNextPosition(Direction currentDirection, float deltaTime) {
        float testX = currentPosition.x;
        float testY = currentPosition.y;

        velocity.scl(deltaTime);

        switch (currentDirection) {
            case LEFT:
                testX -= velocity.x;
                break;
            case RIGHT:
                testX += velocity.x;
                break;
            case UP:
                testY += velocity.y;
                break;
            case DOWN:
                testY -= velocity.y;
                break;
        }

        nextPosition.x = testX;
        nextPosition.y = testY;

        velocity.scl(1 / deltaTime);
    }

    public EntityConfig getEntityConfig() {
        return entityConfig;
    }

    public void setEntityConfig(EntityConfig entityConfig) {
        this.entityConfig = entityConfig;
    }

    public static EntityConfig getEntityConfig(String configPath) {
        Json json = new Json();
        return json.fromJson(EntityConfig.class, Gdx.files.internal(configPath));
    }
}
