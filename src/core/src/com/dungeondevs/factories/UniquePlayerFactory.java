package com.dungeondevs.factories;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.dungeondevs.components.PlayerCharacterComponent;

/**
 * Factory qui construit l'entité player et ses components à partir de la config.
 * Elle s'assure qu'il n'y ait qu'un seul player dans le monde.
 */
public class UniquePlayerFactory extends PlayerFactory{
    private ComponentMapper<PlayerCharacterComponent> playerCharacterComponentComponentMapper;

    public UniquePlayerFactory(World artemisWorld, com.badlogic.gdx.physics.box2d.World box2DWorld) {
        super(artemisWorld, box2DWorld);
        this.playerCharacterComponentComponentMapper = artemisWorld.getMapper(PlayerCharacterComponent.class);
    }

    private boolean playerExists(){
        return !artemisWorld.getAspectSubscriptionManager().get(Aspect.all(PlayerCharacterComponent.class)).getEntities().isEmpty();
    }

    /**
     * {@inheritDoc}
     * Elle s'assure qu'il n'y ait qu'un seul player dans le monde.
     */
    @Override
    public void createEntity() {
        // Vérifie qu'il n'y ait pas déjà un player dans le monde
        if(!playerExists()){
            Entity playerEntity = artemisWorld.createEntity();
            addComponent(playerEntity);
        }
        // sinon ne fait rien
    }

    @Override
    protected void addStateComponent(Entity entity) {

    }

    @Override
    protected void addPhysicsComponent(Entity entity) {

    }

    @Override
    protected void addInputComponent(Entity entity) {

    }

    @Override
    protected void addHealthComponent(Entity entity) {

    }

    @Override
    protected void addMovementComponent(Entity entity) {

    }

    @Override
    protected void addAnimationComponent(Entity entity) {

    }

    @Override
    protected void addAttackComponent(Entity entity) {

    }

    @Override
    protected void addSpriteComponent(Entity entity) {

    }
}
