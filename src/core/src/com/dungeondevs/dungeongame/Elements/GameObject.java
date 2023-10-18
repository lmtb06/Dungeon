package com.dungeondevs.dungeongame.Elements;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.dungeondevs.dungeongame.Animation.GameObjectAnimation;

public abstract class GameObject {
    protected Body body;

    protected GameObjectAnimation animations;

    protected World monde;

    public GameObject (Vector2 position, Vector2 size, World world) {


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
