package com.dungeondevs.configs.spawnable.Items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.dungeondevs.configs.Level.EntityType;
import com.dungeondevs.configs.spawnable.SpawnableConfig;

public class PowerUpConfig implements SpawnableConfig {
    protected ItemConfig itemConfig;
    protected int healthBonus;
    protected float speedBonus;
    protected float damageBonus;
    protected int maxHealthBonus;
    protected float maxSpeedBonus;
    protected float maxDamageBonus;

    @Override
    public BodyDef getBodyDef() {
        return itemConfig.bodyDef;
    }

    @Override
    public TextureRegion getTextureRegion() {
        return itemConfig.texture;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.POWERUP;
    }
}
