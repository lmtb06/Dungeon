package com.dungeondevs.factories;

import com.artemis.Entity;
import com.artemis.World;
import com.dungeondevs.configs.Level.PlayerConfig;

/**
 * Factory qui construit l'entité player et ses components à partir de la config.
 */
public abstract class PlayerFactory implements EntityFactory{

    protected World artemisWorld;
    protected com.badlogic.gdx.physics.box2d.World box2DWorld;
    protected PlayerConfig playerConfig;

    public PlayerFactory(World artemisWorld, com.badlogic.gdx.physics.box2d.World box2DWorld) {
        this.artemisWorld = artemisWorld;
        this.box2DWorld = box2DWorld;
    }

    public final void setConfig(PlayerConfig config){
        this.playerConfig = config;
    }

    /**
     * {@inheritDoc}
     * Cette méthode crée l'entité player et ses components à partir de la config.
     *
     */
    @Override
    public void createEntity() {
        Entity playerEntity = artemisWorld.createEntity();
        addComponent(playerEntity);
    }

    public final void addComponent(Entity entity){
        addStateComponent(entity);
        addPhysicsComponent(entity);
        addInputComponent(entity);
        addHealthComponent(entity);
        addMovementComponent(entity);
        addAnimationComponent(entity);
        addAttackComponent(entity);
        addSpriteComponent(entity);
    }

    protected abstract void addStateComponent(Entity entity);
    protected abstract void addPhysicsComponent(Entity entity);
    protected abstract void addInputComponent(Entity entity);
    protected abstract void addHealthComponent(Entity entity);
    protected abstract void addMovementComponent(Entity entity);
    protected abstract void addAnimationComponent(Entity entity);
    protected abstract void addAttackComponent(Entity entity);
    protected abstract void addSpriteComponent(Entity entity);
}
