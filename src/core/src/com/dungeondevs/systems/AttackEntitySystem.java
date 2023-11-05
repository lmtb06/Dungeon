package com.dungeondevs.systems;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.dungeondevs.components.AttackEntityComponent;
import com.dungeondevs.components.PhysicsComponent;

/**
 * Système qui gère les entités d'attaque (celles créées lorsque le joueur attaque).
 */
@All({AttackEntityComponent.class, PhysicsComponent.class})
public class AttackEntitySystem extends EntityProcessingSystem {

    /**
     * Le monde physique.
     */
    private World box2dWorld;

    /**
     * Constructeur de base qui nécessite le monde physique.
     * @param box2dWorld le monde physique.
     */
    public AttackEntitySystem(World box2dWorld) {
        this.box2dWorld = box2dWorld;
    }

    @Override
    protected void process(Entity e) {

        // On récupère les composants nécessaires au traitement
        AttackEntityComponent attackEntityComponent = e.getComponent(AttackEntityComponent.class);
        PhysicsComponent physicsComponent = e.getComponent(PhysicsComponent.class);

        // On récupère la position du corps à suivre et on bouge le corps du physicComponent pour suivre celui-ci, en fonction du décalage de l'entité.
        Vector2 boundPosition = attackEntityComponent.boundBody.getPosition();
        physicsComponent.body.setTransform(boundPosition.x + attackEntityComponent.offset, boundPosition.y, 0);

        // Si le temps de vie de l'entité d'attaque a expiré, on la supprime.
        if(TimeUtils.timeSinceMillis(attackEntityComponent.startTime) > attackEntityComponent.autoDestroyTime){
            world.deleteEntity(e);
            box2dWorld.destroyBody(e.getComponent(PhysicsComponent.class).body);
        }
    }
}
