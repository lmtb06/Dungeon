package com.dungeondevs.systems;

import com.artemis.Aspect;
import com.artemis.systems.IntervalEntitySystem;
import com.badlogic.gdx.physics.box2d.World;


public class PhysicsSystem extends IntervalEntitySystem {
    private World box2dWorld;
    private float timeStep;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;

    public PhysicsSystem(World box2dWorld, float timeStep) {
        super(Aspect.all(),timeStep);
        this.box2dWorld = box2dWorld;
        this.timeStep = timeStep;
    }



    @Override
    protected void processSystem() {
        box2dWorld.step(timeStep, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }
}
