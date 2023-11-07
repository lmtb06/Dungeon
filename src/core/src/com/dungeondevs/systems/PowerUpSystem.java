package com.dungeondevs.systems;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.dungeondevs.components.PowerUpComponent;
import com.dungeondevs.components.MovementComponent;
import com.dungeondevs.components.AttackComponent;
import com.dungeondevs.components.HealthComponent;
import com.dungeondevs.utils.Constants;
import com.badlogic.gdx.utils.TimeUtils;
import com.dungeondevs.components.PowerUpType;

/**
 * Système faisant fonctionner les power ups
 */
@All({MovementComponent.class})
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
        PowerUpComponent powerUpComponent = e.getComponent(PowerUpComponent.class);
        MovementComponent movementComponent = e.getComponent(MovementComponent.class);
        AttackComponent attackComponent = e.getComponent(AttackComponent.class);
        HealthComponent healthComponent = e.getComponent(HealthComponent.class);
       powerUpComponent.getSpeedLastAppliedTime();
       System.out.println(healthComponent.getHealth());
        if(powerUpComponent.powerUpNTBA!=null){
            switch(powerUpComponent.powerUpNTBA.powerUpType){
                case SPEED_DEFIN:
                    movementComponent.maxSpeedInMeterPerSecond+=powerUpComponent.powerUpNTBA.value;
                    powerUpComponent.powerUpNTBA=null;
                    break;
                case SPEED_TEMPO:
                    powerUpComponent.speedDuration=powerUpComponent.powerUpNTBA.duration;
                    if(powerUpComponent.getSpeedLastAppliedTime()==0){
                        powerUpComponent.setOriginalSpeed(movementComponent.maxSpeedInMeterPerSecond);
                        movementComponent.maxSpeedInMeterPerSecond += powerUpComponent.powerUpNTBA.value;
                    }
                    powerUpComponent.setSpeedLastAppliedTime(TimeUtils.millis());
                    powerUpComponent.powerUpNTBA=null;
                    break;

                case ATTACK_DEFIN:
                    attackComponent.setDamage(attackComponent.getDamages()+(int)powerUpComponent.powerUpNTBA.value);
                    powerUpComponent.powerUpNTBA=null;
                    break;

                case ATTACK_TEMPO:
                    powerUpComponent.attackDuration=powerUpComponent.powerUpNTBA.duration;
                    if(powerUpComponent.attackLastAppliedTime==0){
                        powerUpComponent.originalAttack=attackComponent.getDamages();
                        attackComponent.setDamage(attackComponent.getDamages()+(int)powerUpComponent.powerUpNTBA.value);
                    }
                    powerUpComponent.attackLastAppliedTime=TimeUtils.millis();
                    powerUpComponent.powerUpNTBA=null;
                    break;
                case HEALTH_DEFIN:
                    healthComponent.setMaxHealth(healthComponent.getMaxHealth()+(int)powerUpComponent.powerUpNTBA.value);
                    powerUpComponent.powerUpNTBA=null;
                    break;

                case HEALTH_HEAL:
                    healthComponent.heal((int)powerUpComponent.powerUpNTBA.value);
                    powerUpComponent.powerUpNTBA=null;
                    break;
            }
        }
        /*if(powerUpComponent.getSpeedNTBApplied()){
            baseSpeed=movementComponent.maxSpeedInMeterPerSecond;
            if(powerUpComponent.getSpeedLastAppliedTime()==0){
                powerUpComponent.setOriginalSpeed(movementComponent.maxSpeedInMeterPerSecond);
                movementComponent.maxSpeedInMeterPerSecond += Constants.POWER_UP_TEMPORARY_SPEED_VALUE;
            }
            System.out.println(movementComponent.maxSpeedInMeterPerSecond);
            powerUpComponent.setSpeedNTBApplied(false);
            powerUpComponent.setSpeedLastAppliedTime(TimeUtils.millis());

            }*/
        if((powerUpComponent.getSpeedLastAppliedTime()!=0)&&(TimeUtils.timeSinceMillis(powerUpComponent.getSpeedLastAppliedTime())>powerUpComponent.speedDuration)){
            movementComponent.maxSpeedInMeterPerSecond= powerUpComponent.getOriginalSpeed();
            powerUpComponent.setOriginalSpeed(1);
            powerUpComponent.setSpeedLastAppliedTime(0);
        }
        if((powerUpComponent.attackLastAppliedTime!=0)&&(TimeUtils.timeSinceMillis(powerUpComponent.attackLastAppliedTime)>powerUpComponent.attackDuration)){
            attackComponent.setDamage((int)powerUpComponent.originalAttack);
            powerUpComponent.originalAttack = 1;
            powerUpComponent.attackLastAppliedTime = 0;
        }
    }
}
