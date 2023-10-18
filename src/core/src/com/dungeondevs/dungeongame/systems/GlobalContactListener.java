package com.dungeondevs.dungeongame.systems;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.dungeondevs.dungeongame.Elements.Monster;
import com.dungeondevs.dungeongame.tiled_elements.Level;

public class GlobalContactListener implements ContactListener {

    private Level level;

    public GlobalContactListener(Level level) {
        this.level = level;
    }

    @Override
    public void beginContact(Contact contact) {
        Monster m = (Monster) level.getEntityByBody(contact.getFixtureB().getBody());
        if(m != null) System.out.println("Touché mob");
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
