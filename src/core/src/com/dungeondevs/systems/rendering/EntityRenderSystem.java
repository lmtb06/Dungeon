package com.dungeondevs.systems.rendering;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.annotations.One;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeondevs.components.PhysicsComponent;
import com.dungeondevs.components.rendering.AnimationListComponent;
import com.dungeondevs.components.rendering.SpriteComponent;

/**
 * Système responsable du rendu des composants de sprite et d'animation.
 */
@One({SpriteComponent.class, AnimationListComponent.class})
@All(PhysicsComponent.class)
public class EntityRenderSystem extends EntityProcessingSystem {

    /**
     * Le SpriteBatch utilisé pour le rendu.
     */
    private SpriteBatch batch;

    /**
     * Le viewport sur lequel se baser pour afficher correctement les sprites.
     */
    private Viewport viewport;

    /**
     * Crée un EntityRenderSystem utilisant le Viewport fourni.
     * @param viewport Le viewport sur lequel se baser pour afficher correctement les sprites.
     */
    public EntityRenderSystem(Viewport viewport) {
        batch = new SpriteBatch();
        this.viewport = viewport;
    }

    @Override
    protected void process(Entity e) {

        // Pour chaque entité nécessitant un rendu, on récupère son body (pour sa position) et un composant sprite.
        Body body = e.getComponent(PhysicsComponent.class).body;
        SpriteComponent spriteComponent = e.getComponent(SpriteComponent.class);

        // On fait afficher le batch sur la matrice de rendu du viewport, pour ne pas avoir des images étirées lors du
        // redimensionnement de la fenêtre.
        batch.setProjectionMatrix(viewport.getCamera().projection);
        float unitScale = 1 / 32f;

        // S'il n'y a pas de composant SpriteComponent ...
        if(spriteComponent == null){

            // Alors c'est une animation, donc on récupère le composant nécessaire.
            AnimationListComponent animationListComponent = e.getComponent(AnimationListComponent.class);
            Animation<TextureRegion> currentanimation = animationListComponent.getCurrentAnimationData().getAnimation();

            // Si les animations ne sont pas initialisées, on ne fait rien.
            if(currentanimation == null)
                return;

            // On gère le temps d'animation
            animationListComponent.getCurrentAnimationData().addToStateTime(getWorld().delta);
            TextureRegion currentFrame = currentanimation.getKeyFrame(animationListComponent.getCurrentAnimationData().getStateTime(), true);

            batch.begin();
            batch.draw(currentFrame, body.getPosition().x-2.5f - (currentFrame.getRegionWidth() * unitScale / 2), body.getPosition().y-1.75f- (currentFrame.getRegionHeight() * unitScale / 2), unitScale * 16f, unitScale * 16f);
            batch.end();
        }else{
            // Sinon c'est un simple Sprite qu'on fait afficher.
            Sprite sprite = spriteComponent.getSprite();
            //sprite.setSize(16,  16);
            sprite.setScale(unitScale);

            sprite.setPosition(body.getPosition().x-10.25f - (sprite.getWidth() * unitScale / 2) , body.getPosition().y-9.5f - (sprite.getHeight() * unitScale / 2) );

            batch.begin();
            sprite.draw(batch);
            batch.end();
        }

    }
}
