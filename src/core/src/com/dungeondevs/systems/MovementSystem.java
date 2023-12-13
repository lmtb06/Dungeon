package com.dungeondevs.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.dungeondevs.components.*;
import com.dungeondevs.utils.GameAspects;

import com.badlogic.gdx.utils.Logger; // Assuming you will use LibGDX's logger

public class MovementSystem extends EntityProcessingSystem {
    private static final Logger logger = new Logger(MovementSystem.class.getName(), Logger.DEBUG);

    private StateManagementSystem stateManagementSystem;
    private ComponentMapper<EntityStateComponent> stateMapper;
    private ComponentMapper<PhysicsComponent> physicsMapper;
    private ComponentMapper<VelocityComponent> movementSpecsMapper;
    private ComponentMapper<InputComponent> inputMapper;

    public MovementSystem() {
        super(GameAspects.PLAYER_CHARACTER_ASPECT);
    }

    @Override
    protected void process(Entity e) {
        EntityStateComponent stateComponent = stateMapper.get(e);
        InputComponent inputComponent = inputMapper.get(e);
        PhysicsComponent physicsComponent = physicsMapper.get(e);
        VelocityComponent velocityComponent = movementSpecsMapper.get(e);
        Vector2 movementVector = calculateMovementVector(inputComponent, e);
        boolean shouldMove = !movementVector.isZero();
        switch (stateComponent.getCurrentState()) {
            case IDLE:
                if (shouldMove) {
                    transitionAndRun(e, movementVector, velocityComponent, physicsComponent);
                }
                break;
            case RUNNING:
                if (shouldMove) {
                    transitionAndRun(e, movementVector, velocityComponent, physicsComponent);
                } else {
                    transitionToSlowingDown(e, velocityComponent, physicsComponent);
                }
                break;
            case SLOWING_DOWN:
                if (shouldMove) {
                    transitionAndRun(e, movementVector, velocityComponent, physicsComponent);
                } else {
                    slowDown(e, stateComponent, velocityComponent, physicsComponent);
                }
                break;
            default:
                logger.error("Unhandled state: " + stateComponent.getCurrentState());
                break;
        }
    }

    private Vector2 calculateMovementVector(InputComponent input, Entity e) {
        Vector2 vector = new Vector2();
        if (input.isUp()){
            vector.y += 1;
            e.getComponent(DirectionComponent.class).direction = "haut";
        }
        if (input.isDown()){
            vector.y -= 1;
            e.getComponent(DirectionComponent.class).direction = "bas";
        }
        if (input.isLeft()){
            vector.x -= 1;
            e.getComponent(DirectionComponent.class).direction = "gauche";
        }
        if (input.isRight()){
            vector.x += 1;
            e.getComponent(DirectionComponent.class).direction = "droite";
        }
        return vector;
    }

    private void transitionAndRun(Entity e, Vector2 movementVector, VelocityComponent velocityComponent, PhysicsComponent physicsComponent) {
        if (stateManagementSystem.transition(stateMapper.get(e), EntityState.RUNNING)) {
            physicsComponent.body.setLinearVelocity(movementVector.nor().scl(velocityComponent.getMaxSpeed()));
        } else {
            logger.error("Cannot transition to running state for entity: " + e);
        }
    }

    private void transitionToSlowingDown(Entity e, VelocityComponent velocityComponent, PhysicsComponent physicsComponent) {
        if (stateManagementSystem.transition(stateMapper.get(e), EntityState.SLOWING_DOWN)) {
            velocityComponent.setInitialVelocityAtStartOfDeceleration(physicsComponent.body.getLinearVelocity().cpy());
        } else {
            logger.error("Cannot transition to slowing down state for entity: " + e);
        }
    }

    private void transitionToIdle(Entity e, PhysicsComponent physicsComponent, VelocityComponent velocityComponent) {
        // Ensure the entity is fully stopped
        physicsComponent.body.setLinearVelocity(0, 0);
        // Reset initial velocity at start of deceleration as it is no longer required in the IDLE state
        velocityComponent.getInitialVelocityAtStartOfDeceleration().set(0, 0);
        if (!stateManagementSystem.transition(stateMapper.get(e), EntityState.IDLE)) {
            // Consider using a logger here
            logger.error("Cannot transition to idle state.");
        }
    }

    private void slowDown(Entity e, EntityStateComponent stateComponent, VelocityComponent velocityComponent, PhysicsComponent physicsComponent) {
        Vector2 initialVelocity = velocityComponent.getInitialVelocityAtStartOfDeceleration();
        if (stateComponent.getTimeRemainingInCurrentState() > 0 && initialVelocity != null) {
            Interpolation interpolation = Interpolation.linear;
            float alpha = 1 - stateComponent.getTimeRemainingInCurrentState() / velocityComponent.getDecelerationDuration();
            alpha = Math.min(Math.max(alpha, 0), 1);
            float scale = 1 - interpolation.apply(alpha);
            Vector2 newVelocity = initialVelocity.cpy().scl(scale);
            physicsComponent.body.setLinearVelocity(newVelocity);
        } else {
            transitionToIdle(e, physicsComponent, velocityComponent);
        }
    }
}
