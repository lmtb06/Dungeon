package com.dungeondevs.systems;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.dungeondevs.components.HealthComponent;
import com.dungeondevs.components.PhysicsComponent;
import com.dungeondevs.components.PiegeActifComponent;

@All(PiegeActifComponent.class)
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
        PiegeActifComponent piegeActifComponent = e.getComponent(PiegeActifComponent.class);
        if(!piegeActifComponent.action){
            PhysicsComponent physicsComponent = e.getComponent(PhysicsComponent.class);
            box2DWorld.destroyBody(physicsComponent.body);
            world.deleteEntity(e);
        }
    }
}
