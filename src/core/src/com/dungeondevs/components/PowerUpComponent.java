package com.dungeondevs.components;

import com.artemis.Component;
import com.dungeondevs.components.PowerUpType;

public class PowerUpComponent extends Component{

    public PowerUpTypeComponent powerUpNTBA = null;
    public long speedLastAppliedTime = 0;

    public long speedDuration = 0;

    public boolean speedNTBApplied = false;

    public float originalSpeed = 0;


    public long attackLastAppliedTime = 0;

    public long attackDuration = 0;

    public boolean attackNTBApplied = false;

    public float originalAttack = 0;

    public long getSpeedLastAppliedTime() {
        return speedLastAppliedTime;
    }

    public void setSpeedLastAppliedTime(long speedLastAppliedTime) {
        this.speedLastAppliedTime = speedLastAppliedTime;
    }

    public boolean getSpeedNTBApplied() {
        return speedNTBApplied;
    }

    public void setSpeedNTBApplied(boolean speedNTBApplied) {
        this.speedNTBApplied = speedNTBApplied;
    }

    public float getOriginalSpeed() {
        return originalSpeed;
    }

    public void setOriginalSpeed(float originalSpeed) {
        this.originalSpeed = originalSpeed;
    }

    public void applyPowerUP(PowerUpTypeComponent powerUpTypeComponent){
        this.powerUpNTBA = powerUpTypeComponent;
    }

}