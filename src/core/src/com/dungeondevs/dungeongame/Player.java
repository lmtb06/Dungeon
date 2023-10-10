package com.dungeondevs.dungeongame;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Player {
    private int health;
    private float speedMeterPerSecond;
    private Body body;
    private int contactDamages;
    private int meleeDamages;

    public Player(Body body, Animation<TextureRegion> animation) {
        this.body = body;

    }
}
