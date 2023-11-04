package com.dungeondevs.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class MovementComponent extends Component {
    public float maxSpeedInMeterPerSecond;
    public float decelerationTimeInSeconds;
    public Vector2 initialVelocityAtStartOfDeceleration;
}
