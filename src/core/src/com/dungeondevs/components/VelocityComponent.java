package com.dungeondevs.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Component qui contient les
 */
public class VelocityComponent extends Component {
    private float maxSpeed;
    private float decelerationDuration;
    private float timeRemainingTillStop;
    private Vector2 initialVelocityAtStartOfDeceleration;

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getDecelerationDuration() {
        return decelerationDuration;
    }

    public void setDecelerationDuration(float decelerationDuration) {
        this.decelerationDuration = decelerationDuration;
    }

    public float getTimeRemainingTillStop() {
        return timeRemainingTillStop;
    }

    public void setTimeRemainingTillStop(float timeRemainingTillStop) {
        this.timeRemainingTillStop = timeRemainingTillStop;
    }

    public Vector2 getInitialVelocityAtStartOfDeceleration() {
        return initialVelocityAtStartOfDeceleration;
    }

    public void setInitialVelocityAtStartOfDeceleration(Vector2 initialVelocityAtStartOfDeceleration) {
        this.initialVelocityAtStartOfDeceleration = initialVelocityAtStartOfDeceleration;
    }
}
