package com.dungeondevs.configs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class PlayerConfig {
    private BodyDef bodyDef;
    private int health;
    private int maxHealth;

    private float maxSpeed;
    private float decelerationTime;

    private TextureRegion idleAnimation;

    private TextureRegion runAnimation;

    private TextureRegion slowDownAnimation;

    //private WeaponConfig weaponConfig;
}
