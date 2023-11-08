package com.dungeondevs.systems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.physics.box2d.*;
import com.dungeondevs.components.ContactDamageComponent;
import com.dungeondevs.components.HealthComponent;
import com.dungeondevs.components.PowerUpUserComponent;
import com.dungeondevs.utils.FixtureUserData;

public class CollisionSystem extends BaseSystem implements ContactListener {

    public CollisionSystem (World box2dWorld) {
        box2dWorld.setContactListener(this);
    }

    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureA().getUserData() == null || contact.getFixtureB().getUserData() == null){
            return;
        }

        FixtureUserData fixtureUserDataA = (FixtureUserData) contact.getFixtureA().getUserData();
        FixtureUserData fixtureUserDataB = (FixtureUserData) contact.getFixtureB().getUserData();

        if(fixtureUserDataB.getEntityType() == FixtureUserData.EntityTypes.Monster){
            if(fixtureUserDataA.getEntityType() == FixtureUserData.EntityTypes.Player){
                System.out.println("AA");
                fixtureUserDataA.getEntity().getComponent(HealthComponent.class).damage(
                        fixtureUserDataB.getEntity().getComponent(ContactDamageComponent.class).getDamages()
                );
            }
        }

        if(fixtureUserDataB.getEntityType() == FixtureUserData.EntityTypes.Attack){
            if(fixtureUserDataA.getEntityType() == FixtureUserData.EntityTypes.Monster){
                fixtureUserDataA.getEntity().getComponent(HealthComponent.class).damage(
                        fixtureUserDataB.getEntity().getComponent(ContactDamageComponent.class).getDamages()
                );
            }
        }

        if(fixtureUserDataB.getEntityType() == FixtureUserData.EntityTypes.PowerUp){
            if(fixtureUserDataA.getEntityType() == FixtureUserData.EntityTypes.Player){
                fixtureUserDataA.getEntity().getComponent(PowerUpUserComponent.class).applyPowerUP(fixtureUserDataB.getEntity());
            }
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

    @Override
    protected void processSystem() {

    }
}
