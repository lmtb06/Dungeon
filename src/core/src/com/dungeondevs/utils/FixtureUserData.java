package com.dungeondevs.utils;

import com.artemis.Entity;

public class FixtureUserData {

    private EntityTypes entityType;
    private Entity entity;

    public enum EntityTypes { Monster, Player, Attack, Porte, PowerUp, Trap, Teleporteur, Arme};

    public FixtureUserData(EntityTypes entityType, Entity entity) {
        this.entityType = entityType;
        this.entity = entity;
    }

    public EntityTypes getEntityType() {
        return entityType;
    }

    public Entity getEntity() {
        return entity;
    }
}
