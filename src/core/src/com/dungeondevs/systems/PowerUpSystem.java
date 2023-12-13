package com.dungeondevs.systems;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.dungeondevs.components.*;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Système faisant fonctionner les power ups
 */
@All({VelocityComponent.class})
public class PowerUpSystem extends EntityProcessingSystem {

    /**
     * Le monde physique.
     */
    private World box2DWorld;

    /**
     * Constructeur de base qui utilise un monde physique.
     * @param box2DWorld le monde physique.
     */
    public PowerUpSystem(World box2DWorld) {
        this.box2DWorld = box2DWorld;
    }

    @Override
    protected void process(Entity e) {
        PowerUpUserComponent powerUpUserComponent = e.getComponent(PowerUpUserComponent.class);
        VelocityComponent velocityComponent = e.getComponent(VelocityComponent.class);
        AttackComponent attackComponent = e.getComponent(AttackComponent.class);
        HealthComponent healthComponent = e.getComponent(HealthComponent.class);
       powerUpUserComponent.getSpeedLastAppliedTime();
        if(powerUpUserComponent.powerUpNTBA!=null){
            switch(powerUpUserComponent.powerUpNTBA.getComponent(PowerUpTypeComponent.class).powerUpType){
                case SPEED_DEFIN:
                    velocityComponent.setMaxSpeed(velocityComponent.getMaxSpeed() + powerUpUserComponent.powerUpNTBA.getComponent(PowerUpTypeComponent.class).value);
                    break;
                case SPEED_TEMPO:
                    powerUpUserComponent.speedDuration= powerUpUserComponent.powerUpNTBA.getComponent(PowerUpTypeComponent.class).duration;
                    if(powerUpUserComponent.getSpeedLastAppliedTime()==0){
                        powerUpUserComponent.setOriginalSpeed(velocityComponent.getMaxSpeed());
                        velocityComponent.setMaxSpeed(velocityComponent.getMaxSpeed() + powerUpUserComponent.powerUpNTBA.getComponent(PowerUpTypeComponent.class).value);
                    }
                    powerUpUserComponent.setSpeedLastAppliedTime(TimeUtils.millis());
                    break;

                case ATTACK_DEFIN:
                    attackComponent.setDamage(attackComponent.getDamages()+(int) powerUpUserComponent.powerUpNTBA.getComponent(PowerUpTypeComponent.class).value);
                    break;

                case ATTACK_TEMPO:
                    powerUpUserComponent.attackDuration= powerUpUserComponent.powerUpNTBA.getComponent(PowerUpTypeComponent.class).duration;
                    if(powerUpUserComponent.attackLastAppliedTime==0){
                        powerUpUserComponent.originalAttack=attackComponent.getDamages();
                        attackComponent.setDamage(attackComponent.getDamages()+(int) powerUpUserComponent.powerUpNTBA.getComponent(PowerUpTypeComponent.class).value);
                    }
                    powerUpUserComponent.attackLastAppliedTime=TimeUtils.millis();
                    break;
                case HEALTH_DEFIN:
                    healthComponent.setMaxHealth(healthComponent.getMaxHealth()+(int) powerUpUserComponent.powerUpNTBA.getComponent(PowerUpTypeComponent.class).value);
                    healthComponent.heal(healthComponent.getMaxHealth()); //Remet le joueur full life
                    break;

                case HEALTH_HEAL:
                    healthComponent.heal((int) powerUpUserComponent.powerUpNTBA.getComponent(PowerUpTypeComponent.class).value);
                    break;
            }
            PhysicsComponent physicsComponent = powerUpUserComponent.powerUpNTBA.getComponent(PhysicsComponent.class);
            box2DWorld.destroyBody(physicsComponent.body);
            world.deleteEntity(powerUpUserComponent.powerUpNTBA);
            powerUpUserComponent.powerUpNTBA=null;
        }

        //Retrait du bonus de vitesse une fois que la durée du powerUp est passée
        if((powerUpUserComponent.getSpeedLastAppliedTime()!=0)&&(TimeUtils.timeSinceMillis(powerUpUserComponent.getSpeedLastAppliedTime())> powerUpUserComponent.speedDuration)){
            velocityComponent.setMaxSpeed(powerUpUserComponent.getOriginalSpeed());
            powerUpUserComponent.setOriginalSpeed(1);
            powerUpUserComponent.setSpeedLastAppliedTime(0);
        }

        //Retrait du bonus d'attack une fois que la durée du powerUp est passée
        if((powerUpUserComponent.attackLastAppliedTime!=0)&&(TimeUtils.timeSinceMillis(powerUpUserComponent.attackLastAppliedTime)> powerUpUserComponent.attackDuration)){
            attackComponent.setDamage((int) powerUpUserComponent.originalAttack);
            powerUpUserComponent.originalAttack = 1;
            powerUpUserComponent.attackLastAppliedTime = 0;

        }
    }
}
