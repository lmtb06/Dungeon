package com.dungeondevs.systems;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.dungeondevs.components.EntityState;
import com.dungeondevs.components.EntityStateComponent;
import com.dungeondevs.utils.Constants;

@All(EntityStateComponent.class)
public class StateManagementSystem extends EntityProcessingSystem {
    @Override
    protected void process(Entity e) {
        EntityStateComponent stateComponent = e.getComponent(EntityStateComponent.class);
        stateComponent.timeRemainingInCurrentState = Math.max(stateComponent.timeRemainingInCurrentState - world.getDelta(), 0);
    }

    public boolean transition(EntityStateComponent stateComponent, EntityState state) {
        boolean transitioned = false;
        switch (stateComponent.state) {
            case IDLE:
                if (state == EntityState.RUNNING) {
                    stateComponent.state = EntityState.RUNNING;
                    stateComponent.timeRemainingInCurrentState = 0f;
                    transitioned = true;
                }
                break;
            case RUNNING:
                if (state == EntityState.SLOWING_DOWN) {
                    stateComponent.state = EntityState.SLOWING_DOWN;
                    stateComponent.timeRemainingInCurrentState = Constants.PLAYER_CHAR_DECELERATION_TIME;
                    transitioned = true;
                } else if (state == EntityState.RUNNING) {
                    stateComponent.timeRemainingInCurrentState = 0f;
                    transitioned = true;
                }
                break;
            case SLOWING_DOWN:
                if (state == EntityState.IDLE && stateComponent.timeRemainingInCurrentState <= 0) {
                    stateComponent.state = EntityState.IDLE;
                    stateComponent.timeRemainingInCurrentState = 0f;
                    transitioned = true;
                } else if (state == EntityState.RUNNING) {
                    stateComponent.state = EntityState.RUNNING;
                    stateComponent.timeRemainingInCurrentState = 0f;
                    transitioned = true;
                }
                break;

        }

        return transitioned;
    }
}
