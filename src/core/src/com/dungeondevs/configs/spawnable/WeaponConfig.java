package com.dungeondevs.configs.spawnable;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.dungeondevs.configs.Level.EntityType;

public class WeaponConfig implements SpawnableConfig{
    protected String name;
    protected float damage;
    protected float cooldown;
    protected TextureRegion texture;
    protected BodyDef bodyDef;

    public WeaponConfig(String name, float damage, float cooldown, TextureRegion texture, BodyDef bodyDef) {
        this.name = name;
        this.damage = damage;
        this.cooldown = cooldown;
        this.texture = texture;
        this.bodyDef = bodyDef;
    }

    @Override
    public BodyDef getBodyDef() {
        return bodyDef;
    }

    @Override
    public TextureRegion getTextureRegion() {
        return texture;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.WEAPON;
    }
}
