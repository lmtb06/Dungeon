package com.dungeondevs.systems;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.dungeondevs.components.PhysicsComponent;
import com.dungeondevs.components.TeleportationComponent;

/**
 * System qui va boucler sur les entités ayant le componant de téléportation et si le booléen de téléportation "doitetrefait" est à true, alors l'entité sera téléportée aux coordonnées que le composant possède.
 */
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
