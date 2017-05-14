
package com.ufrpe.warcash.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Direction {
    private String name;
    private String animationType;
    private ArrayList<Frame> frames = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimationType() {
        return animationType;
    }

    public void setAnimationType(String animationType) {
        this.animationType = animationType;
    }

    public ArrayList<Frame> getFrames() {
        return frames;
    }

    public void setFrames(ArrayList<Frame> frames) {
        this.frames = frames;
    }
}
