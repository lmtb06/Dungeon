package com.dungeondevs.components;

import com.badlogic.gdx.math.Vector2;

/**
 * Classe qui permet de faire des actions en fonction de l'état de l'entité.
 */
public abstract class State {

    /**
     * Fait courir l'entité dans la direction donnée.
     * @param direction la direction dans laquelle courir
     * @param velocityComponent le component de vitesse de l'entité
     * @param physicsComponent le component de physique de l'entité
     */
    public void run(Vector2 direction, VelocityComponent velocityComponent, PhysicsComponent physicsComponent) {
        velocityComponent.setMaxSpeed(velocityComponent.getMaxSpeed());
        velocityComponent.setDecelerationDuration(0);
        velocityComponent.setTimeRemainingTillStop(0);
        velocityComponent.setInitialVelocityAtStartOfDeceleration(velocityComponent.getInitialVelocityAtStartOfDeceleration());
        // TODO
    }

    /**
     * Ralentit l'entité.
     * @param velocityComponent le component de vitesse de l'entité
     * @param physicsComponent le component de physique de l'entité
     */
    public void slowDown(VelocityComponent velocityComponent, PhysicsComponent physicsComponent) {
        velocityComponent.setMaxSpeed(0);
        velocityComponent.setDecelerationDuration(velocityComponent.getDecelerationDuration());
        velocityComponent.setTimeRemainingTillStop(velocityComponent.getTimeRemainingTillStop());
        velocityComponent.setInitialVelocityAtStartOfDeceleration(velocityComponent.getInitialVelocityAtStartOfDeceleration());
        // TODO
    }

    /**
     * Arrête l'entité.
     * @param velocityComponent le component de vitesse de l'entité
     * @param physicsComponent le component de physique de l'entité
     */
    public void idle(VelocityComponent velocityComponent, PhysicsComponent physicsComponent) {
        velocityComponent.setMaxSpeed(0);
        velocityComponent.setDecelerationDuration(0);
        velocityComponent.setTimeRemainingTillStop(0);
        velocityComponent.setInitialVelocityAtStartOfDeceleration(velocityComponent.getInitialVelocityAtStartOfDeceleration());
        // TODO
    }

    /**
     * Fait attaquer l'entité avec une attaque de mêlée.
     * @param attackComponent
     */
    public void meleeAttack(AttackComponent attackComponent) {
        // TODO
    }

    /**
     * Fait attaquer l'entité avec une attaque à distance.
     * @param attackComponent
     */
    public void rangedAttack(AttackComponent attackComponent) {
        // TODO
    }
}
