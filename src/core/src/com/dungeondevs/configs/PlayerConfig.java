package com.dungeondevs.configs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.dungeondevs.configs.spawnable.WeaponConfig;

public class PlayerConfig {
    protected BodyDef bodyDef;
    protected int health;
    protected int maxHealth;
    protected float maxSpeed;
    protected float decelerationTime;
    protected TextureRegion idleAnimation;
    protected TextureRegion runAnimation;
    protected TextureRegion slowDownAnimation;
    protected WeaponConfig weaponConfig;
}
