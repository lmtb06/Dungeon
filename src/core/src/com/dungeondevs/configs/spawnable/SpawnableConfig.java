package com.dungeondevs.configs.spawnable;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.dungeondevs.configs.Level.EntityType;

public interface SpawnableConfig {
    public BodyDef getBodyDef();
    public TextureRegion getTextureRegion();
    public EntityType getEntityType();
}
