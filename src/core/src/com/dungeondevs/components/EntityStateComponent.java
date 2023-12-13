package com.dungeondevs.components;

import com.artemis.Component;

public class EntityStateComponent extends Component {
    
    private EntityState currentState;
    private float timeRemainingInCurrentState;

    public EntityState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(EntityState currentState) {
        this.currentState = currentState;
    }

    public float getTimeRemainingInCurrentState() {
        return timeRemainingInCurrentState;
    }

    public void setTimeRemainingInCurrentState(float timeRemainingInCurrentState) {
        this.timeRemainingInCurrentState = timeRemainingInCurrentState;
    }
}
