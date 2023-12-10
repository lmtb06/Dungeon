package com.dungeondevs.systems.rendering;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.dungeondevs.components.rendering.AnimationComponent;
import com.dungeondevs.components.rendering.SpriteComponent;

/**
 *  Système permettant la mise à jour des animations en fonction de leur temps d'état, et l'application du bon sprite au SpriteComponent correspondant.
 */
@All({AnimationComponent.class, SpriteComponent.class})
public class AnimationSystem extends EntityProcessingSystem {

    @Override
    protected void process(Entity e) {

        // On récupère les composants
        AnimationComponent animationComponent = e.getComponent(AnimationComponent.class);
        SpriteComponent spriteComponent = e.getComponent(SpriteComponent.class);

        // On incrémente le temps d'état de l'animation
        animationComponent.setStateTime(animationComponent.getStateTime() + world.delta);
        // On modifie le sprite du SpriteComponent pour qu'il soit à jour avec l'animation
        spriteComponent.setTextureRegion(animationComponent.getAnimation().getKeyFrame(animationComponent.getStateTime()));
    }
}
