
package com.ufrpe.warcash.models;

import java.util.HashMap;
import java.util.Map;

public class EntityConfig {
    private String entityID;
    private String state;
    private String direction;
    private AnimationConfig animationConfig;

    public String getEntityID() {
        return entityID;
    }

    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public AnimationConfig getAnimationConfig() {
        return animationConfig;
    }

    public void setAnimationConfig(AnimationConfig animationConfig) {
        this.animationConfig = animationConfig;
    }
}
