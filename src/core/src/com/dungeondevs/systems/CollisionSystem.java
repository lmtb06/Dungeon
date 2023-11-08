package com.dungeondevs.systems;

import com.artemis.BaseSystem;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.physics.box2d.*;
import com.dungeondevs.components.*;
import com.dungeondevs.components.Level.PorteComponent;
import com.dungeondevs.components.Level.SalleAssocieeComponent;
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

        if(fixtureUserDataB.getEntityType() == FixtureUserData.EntityTypes.Porte){
            if(fixtureUserDataA.getEntityType() == FixtureUserData.EntityTypes.Player){
                fixtureUserDataA.getEntity().getComponent(SalleAssocieeComponent.class).idMap = fixtureUserDataB.getEntity().getComponent(PorteComponent.class).idMapVersLaquelleElleMene;
            }
        }

        if(fixtureUserDataA.getEntityType() == FixtureUserData.EntityTypes.Attack){
            if(fixtureUserDataB.getEntityType() == FixtureUserData.EntityTypes.Monster){
                fixtureUserDataB.getEntity().getComponent(HealthComponent.class).damage(
                        fixtureUserDataA.getEntity().getComponent(ContactDamageComponent.class).getDamages()
                );
            }
        }

        if(fixtureUserDataA.getEntityType() == FixtureUserData.EntityTypes.Player){
            if(fixtureUserDataB.getEntityType() == FixtureUserData.EntityTypes.Trap){
                //TODO Appliquer les deats
                System.out.println("piege");
                fixtureUserDataB.getEntity().getComponent(PiegeActifComponent.class).action = false;
            }
        }

        if(fixtureUserDataA.getEntityType() == FixtureUserData.EntityTypes.Player){
            if(fixtureUserDataB.getEntityType() == FixtureUserData.EntityTypes.Teleporteur){
                //TODO Appliquer les deats
                System.out.println("teleporteur");
                System.out.println("se teleporte en x : "+fixtureUserDataB.getEntity().getComponent(InformationTPComponent.class).TPVersLaPositionX);
                System.out.println("se teleporte en y : "+fixtureUserDataB.getEntity().getComponent(InformationTPComponent.class).TPVersLaPositionY);
                System.out.println("allo : "+fixtureUserDataA.getEntity().getComponent(TeleportationComponent.class).X);
                fixtureUserDataA.getEntity().getComponent(TeleportationComponent.class).X = fixtureUserDataB.getEntity().getComponent(InformationTPComponent.class).TPVersLaPositionX;
                fixtureUserDataA.getEntity().getComponent(TeleportationComponent.class).Y = fixtureUserDataB.getEntity().getComponent(InformationTPComponent.class).TPVersLaPositionY;
                fixtureUserDataA.getEntity().getComponent(TeleportationComponent.class).doitEtreFait = true;
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
