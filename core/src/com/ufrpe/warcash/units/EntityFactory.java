package com.ufrpe.warcash.units;

import com.badlogic.gdx.utils.Json;
import com.ufrpe.warcash.units.humans.Peasant;

/**
 * Created by paulomenezes on 14/05/17.
 */

public class EntityFactory {
    private static Json json = new Json();

    public static enum EntityType {
        PEASANT
    }

    public static Entity getEntity(EntityType entityType) {
        Entity entity = null;

        switch (entityType) {
            case PEASANT:
                entity = new Peasant();
                entity.setEntityConfig(Entity.getEntityConfig("scripts/peasant.json"));
                entity.loadAllAnimations();
                break;
        }

        return entity;
    }
}
