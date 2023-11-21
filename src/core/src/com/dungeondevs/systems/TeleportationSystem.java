package com.dungeondevs.systems;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.dungeondevs.components.PhysicsComponent;
import com.dungeondevs.components.TeleportationComponent;

@All(TeleportationComponent.class)
public class TeleportationSystem extends EntityProcessingSystem {
    @Override
    protected void process(Entity e) {
        //System.out.println(e.getComponent(TeleportationComponent.class).doitEtreFait);
        if (e.getComponent(TeleportationComponent.class).doitEtreFait){
            e.getComponent(PhysicsComponent.class).body.setTransform(e.getComponent(TeleportationComponent.class).X,e.getComponent(TeleportationComponent.class).Y, 0);
            e.getComponent(TeleportationComponent.class).doitEtreFait = false;
        }
    }
}
