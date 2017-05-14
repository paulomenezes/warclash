
package com.ufrpe.warcash.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationConfig {
    private double frameDuration;
    private ArrayList<Animation> animations = null;

    public double getFrameDuration() {
        return frameDuration;
    }

    public void setFrameDuration(double frameDuration) {
        this.frameDuration = frameDuration;
    }

    public ArrayList<Animation> getAnimations() {
        return animations;
    }

    public void setAnimations(ArrayList<Animation> animations) {
        this.animations = animations;
    }
}
