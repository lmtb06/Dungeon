package com.dungeondevs.dungeongame;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Player {
    private final int health;
    private final float speedMeterPerSecond;
    private final Body body;
    private int contactDamages;
    private int meleeDamages;

    private Animation<TextureRegion> animation;

    public Player(Body body, Animation<TextureRegion> animation) {
        float vitesseMarcheHumainMoyenMetreParSec = 1.67f;
        this.body = body;
        this.speedMeterPerSecond = 4* vitesseMarcheHumainMoyenMetreParSec;
        this.health = 100;
        this.animation = animation;
    }

    public void move(Vector2 vector){
        // Vecteur normalisé (pas de dépassement de la vitesse lorsqu'on va en diagonal
        body.setLinearVelocity(vector.nor().scl(speedMeterPerSecond));
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Body getBody() {
        return body;
    }

    public int getHealth() {
        return health;
    }

    public float getSpeedMeterPerSecond() {
        return speedMeterPerSecond;
    }

    public int getContactDamages() {
        return contactDamages;
    }

    public int getMeleeDamages() {
        return meleeDamages;
    }

    public void setContactDamages(int contactDamages) {
        this.contactDamages = contactDamages;
    }

    public void setMeleeDamages(int meleeDamages) {
        this.meleeDamages = meleeDamages;
    }

    public Animation<TextureRegion> getAnimation () {
        return animation;
    }
}
