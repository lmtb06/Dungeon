package com.dungeondevs.dungeongame;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import level.Level;

public class DamageCollisionListener implements ContactListener {

    private Level level;

    public DamageCollisionListener (Level level) {
        this.level = level;
    }

    @Override
    public void beginContact(Contact contact) {
        Monster m = (Monster) level.getEntityByBody(contact.getFixtureB().getBody());
        //if()
            System.out.println(level.isAnAttackBody(contact.getFixtureA().getBody())); //m.damage(2);
            System.out.println(level.isAnAttackBody(contact.getFixtureB().getBody())); //m.damage(2);
        if(m != null) {

        }
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
}
