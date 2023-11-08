package com.dungeondevs.systems;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.dungeondevs.components.ContactDamageComponent;
import com.dungeondevs.components.HealthComponent;
import com.dungeondevs.components.PhysicsComponent;

/**
 * Système de santé pour détecter la mort des entités.
 */
@All({HealthComponent.class, ContactDamageComponent.class, PhysicsComponent.class})
public class HealthSystem extends EntityProcessingSystem {

    /**
     * Le monde physique.
     */
    private World box2DWorld;

    /**
     * Constructeur de base qui utilise un monde physique.
     * @param box2DWorld le monde physique.
     */
    public HealthSystem(World box2DWorld) {
        this.box2DWorld = box2DWorld;
    }

    @Override
    protected void process(Entity e) {
        HealthComponent healthComponent = e.getComponent(HealthComponent.class);
        if(healthComponent.getHealth() <= 0){
            PhysicsComponent physicsComponent = e.getComponent(PhysicsComponent.class);
            box2DWorld.destroyBody(physicsComponent.body);
            world.deleteEntity(e);
        }
    }
}
