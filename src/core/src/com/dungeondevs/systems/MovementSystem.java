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
    private ComponentMapper<MovementComponent> movementSpecsMapper;
    private ComponentMapper<InputComponent> inputMapper;

    public MovementSystem() {
        super(GameAspects.PLAYER_CHARACTER_ASPECT);
    }

    @Override
    protected void process(Entity e) {
        EntityStateComponent stateComponent = stateMapper.get(e);
        InputComponent inputComponent = inputMapper.get(e);
        PhysicsComponent physicsComponent = physicsMapper.get(e);
        MovementComponent movementComponent = movementSpecsMapper.get(e);
        Vector2 movementVector = calculateMovementVector(inputComponent);
        boolean shouldMove = !movementVector.isZero();

        switch (stateComponent.state) {
            case IDLE:
                if (shouldMove) {
                    transitionAndRun(e, movementVector, movementComponent, physicsComponent);
                }
                break;
            case RUNNING:
                if (shouldMove) {
                    transitionAndRun(e, movementVector, movementComponent, physicsComponent);
                } else {
                    transitionToSlowingDown(e, movementComponent, physicsComponent);
                }
                break;
            case SLOWING_DOWN:
                if (shouldMove) {
                    transitionAndRun(e, movementVector, movementComponent, physicsComponent);
                } else {
                    slowDown(e, stateComponent, movementComponent, physicsComponent);
                }
                break;
            default:
                logger.error("Unhandled state: " + stateComponent.state);
                break;
        }
    }

    private Vector2 calculateMovementVector(InputComponent input) {
        Vector2 vector = new Vector2();
        if (input.up) vector.y += 1;
        if (input.down) vector.y -= 1;
        if (input.left) vector.x -= 1;
        if (input.right) vector.x += 1;
        return vector;
    }

    private void transitionAndRun(Entity e, Vector2 movementVector, MovementComponent movementComponent, PhysicsComponent physicsComponent) {
        if (stateManagementSystem.transition(stateMapper.get(e), EntityState.RUNNING)) {
            physicsComponent.body.setLinearVelocity(movementVector.nor().scl(movementComponent.maxSpeedInMeterPerSecond));
        } else {
            logger.error("Cannot transition to running state for entity: " + e);
        }
    }

    private void transitionToSlowingDown(Entity e, MovementComponent movementComponent, PhysicsComponent physicsComponent) {
        if (stateManagementSystem.transition(stateMapper.get(e), EntityState.SLOWING_DOWN)) {
            movementComponent.initialVelocityAtStartOfDeceleration = physicsComponent.body.getLinearVelocity().cpy();
        } else {
            logger.error("Cannot transition to slowing down state for entity: " + e);
        }
    }

    private void transitionToIdle(Entity e, PhysicsComponent physicsComponent, MovementComponent movementComponent) {
        // Ensure the entity is fully stopped
        physicsComponent.body.setLinearVelocity(0, 0);
        // Reset initial velocity at start of deceleration as it is no longer required in the IDLE state
        movementComponent.initialVelocityAtStartOfDeceleration.set(0, 0);
        if (!stateManagementSystem.transition(stateMapper.get(e), EntityState.IDLE)) {
            // Consider using a logger here
            logger.error("Cannot transition to idle state.");
        }
    }

    private void slowDown(Entity e, EntityStateComponent stateComponent, MovementComponent movementComponent, PhysicsComponent physicsComponent) {
        Vector2 initialVelocity = movementComponent.initialVelocityAtStartOfDeceleration;
        if (stateComponent.timeRemainingInCurrentState > 0 && initialVelocity != null) {
            Interpolation interpolation = Interpolation.linear;
            float alpha = 1 - stateComponent.timeRemainingInCurrentState / movementComponent.decelerationTimeInSeconds;
            alpha = Math.min(Math.max(alpha, 0), 1);
            float scale = 1 - interpolation.apply(alpha);
            Vector2 newVelocity = initialVelocity.cpy().scl(scale);
            physicsComponent.body.setLinearVelocity(newVelocity);
        } else {
            transitionToIdle(e, physicsComponent, movementComponent);
        }
    }
}
