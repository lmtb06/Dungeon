package com.dungeondevs.systems;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.dungeondevs.components.PhysicsComponent;
import com.dungeondevs.components.ActifSalleActuelleComponent;

@All(ActifSalleActuelleComponent.class)
public class TrapExtinctionSystem extends EntityProcessingSystem {
    /**
     * Le monde physique.
     */
    private World box2DWorld;

    /**
     * Constructeur de base qui utilise un monde physique.
     * @param box2DWorld le monde physique.
     */
    public TrapExtinctionSystem(World box2DWorld) {
        this.box2DWorld = box2DWorld;
    }
    @Override
    protected void process(Entity e) {
        ActifSalleActuelleComponent piegeActifComponent = e.getComponent(ActifSalleActuelleComponent.class);
        if(!piegeActifComponent.action){
            PhysicsComponent physicsComponent = e.getComponent(PhysicsComponent.class);
            box2DWorld.destroyBody(physicsComponent.body);
            world.deleteEntity(e);
        }
    }
}
