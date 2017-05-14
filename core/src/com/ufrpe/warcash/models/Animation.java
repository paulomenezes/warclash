
package com.ufrpe.warcash.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Animation {
    private String texturePath;
    private String animationType;
    private ArrayList<Direction> directions = null;

    public String getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }

    public String getAnimationType() {
        return animationType;
    }

    public void setAnimationType(String animationType) {
        this.animationType = animationType;
    }

    public ArrayList<Direction> getDirections() {
        return directions;
    }

    public void setDirections(ArrayList<Direction> directions) {
        this.directions = directions;
    }
}
