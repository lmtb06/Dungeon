package com.dungeondevs.dungeongame;

import Animation.AnimationManager;
import Events.EventManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class GameObject {
    protected Body body;

    protected EventManager eventManager;
    protected AnimationManager animationManager;

    World monde;

    public GameObject (Vector2 position, Vector2 size, World world, EventManager em, AnimationManager am) {
        this.eventManager = em;
        this.animationManager =  am;

        this.monde = world;

        BodyDef powerBody = new BodyDef();
        powerBody.type = BodyDef.BodyType.StaticBody;
        powerBody.position.set(position.x, position.y);
        powerBody.fixedRotation = true;
        this.body = world.createBody(powerBody);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(size.x, size.y);

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = boxShape;
        boxFixtureDef.density = 1;

        this.body.createFixture(boxFixtureDef);
        boxShape.dispose();
    }

}
