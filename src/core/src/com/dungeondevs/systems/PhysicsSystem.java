package com.dungeondevs.systems;

import com.artemis.Aspect;
import com.artemis.systems.IntervalEntitySystem;
import com.badlogic.gdx.physics.box2d.World;


public class PhysicsSystem extends IntervalEntitySystem {
    private final World box2dWorld;
    private final float timeStep;
    private final int velocityIterations;
    private final int positionIterations;

    /**
     * Crée un nouveau PhysicsSystem.
     * @param timeStep le pas de temps
     * @param velocityIterations le nombre d'itérations pour la vitesse
     * @param positionIterations le nombre d'itérations pour la position
     */
    public PhysicsSystem(float timeStep, int velocityIterations, int positionIterations, World box2dWorld) {
        super(Aspect.all(),timeStep);
        this.timeStep = timeStep;
        this.velocityIterations = velocityIterations;
        this.positionIterations = positionIterations;
        this.box2dWorld = box2dWorld;
    }


    /**
     * Met à jour le monde box2d.
     */
    @Override
    protected void processSystem() {
        box2dWorld.step(timeStep, velocityIterations, positionIterations);
    }
}
