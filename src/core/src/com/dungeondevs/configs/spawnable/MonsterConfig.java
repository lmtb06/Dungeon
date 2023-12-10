package com.dungeondevs.configs.spawnable;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.dungeondevs.configs.Level.EntityType;

public class MonsterConfig implements SpawnableConfig{
    protected BodyDef bodyDef;
    protected int health;
    protected int maxHealth;
    protected float maxSpeed;
    protected TextureRegion idleAnimation;
    protected TextureRegion runAnimation;
    protected WeaponConfig equipedWeapon;


    @Override
    public BodyDef getBodyDef() {
        return null;
    }

    @Override
    public TextureRegion getTextureRegion() {
        return null;
    }

    @Override
    public EntityType getEntityType() {
        return null;
    }
}
