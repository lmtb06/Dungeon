package com.dungeondevs.dungeongame.Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.dungeondevs.dungeongame.systems.GlobalContactListener;
import com.dungeondevs.dungeongame.tiled_elements.Level;

public class Player {
    private final int health;
    private final float speedMeterPerSecond;
    private final Body body;
    private int contactDamages;
    private int meleeDamages;
    private Vector2 direction;
    private Body attackBody;
    private float timer;
    private float attackEnd;
    private Level currentLevel;

    private Animation<TextureRegion> animation;

    public Player(Body body, Animation<TextureRegion> animation, Level level) {
        float vitesseMarcheHumainMoyenMetreParSec = 1.67f;
        this.body = body;
        this.speedMeterPerSecond = 4* vitesseMarcheHumainMoyenMetreParSec;
        this.health = 100;
        this.animation = animation;
        this.attackEnd = -1f;
        this.timer = 0f;
        this.currentLevel = level;
    }

    public void move(Vector2 vector){
        // Vecteur normalisé (pas de dépassement de la vitesse lorsqu'on va en diagonal
        body.setLinearVelocity(vector.nor().scl(speedMeterPerSecond));
        if(attackBody != null)
            attackBody.setTransform(body.getPosition().x + 1.2f, body.getPosition().y, 0);
        direction = vector;
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

    public void attack (World world) {

        if(attackBody != null)
            return;

        BodyDef monsterBodyDef = new BodyDef();
        monsterBodyDef.type = BodyDef.BodyType.StaticBody;
        monsterBodyDef.position.set(body.getPosition().x + 1.2f, body.getPosition().y);
        monsterBodyDef.fixedRotation = true;
        attackBody = world.createBody(monsterBodyDef);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(0.5f, 0.5f);

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = boxShape;
        boxFixtureDef.density = 1;

        attackBody.createFixture(boxFixtureDef);
        boxShape.dispose();

        world.setContactListener(new GlobalContactListener(currentLevel));

        attackEnd = Gdx.graphics.getDeltaTime() + 0.1f;
        timer = 0f;
    }

    public void update (World world) {

        if(attackBody != null){

            if (timer > attackEnd) {
                world.destroyBody(attackBody);
                attackBody = null;
            } else {
                timer += Gdx.graphics.getDeltaTime();
            }
        }
    }
}
