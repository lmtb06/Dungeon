package com.dungeondevs.systems;

import com.artemis.BaseSystem;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.physics.box2d.*;

public class CollisionSystem extends BaseSystem implements ContactListener {

    public CollisionSystem (World box2dWorld) {
        box2dWorld.setContactListener(this);
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println("Coucou");
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    @Override
    protected void processSystem() {

    }
}
