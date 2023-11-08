package com.dungeondevs.components;

import com.artemis.Component;
import com.artemis.Entity;

public class PowerUpUserComponent extends Component{

    //PowerUp avec lequel le joueur est entrée en collision qui doit être traité
    public Entity powerUpNTBA = null;

    //Permet de calculer depuis combien de temps le powerUp de vitesse à été ramasser pour l'arreter quand la durée est
    //atteinte.
    public long speedLastAppliedTime = 0;

    //Duree avant que l'effet de speed soit retiré.
    public long speedDuration = 0;

    //Permet de remetrre la vitesse du joueur à la vitesse correcte à la fin du powerUp de vitesse
    public float originalSpeed = 0;

    //Meme fonctionnement pour le bonus d'attaque
    public long attackLastAppliedTime = 0;

    public long attackDuration = 0;

    public float originalAttack = 0;

    public long getSpeedLastAppliedTime() {
        return speedLastAppliedTime;
    }

    public void setSpeedLastAppliedTime(long speedLastAppliedTime) {
        this.speedLastAppliedTime = speedLastAppliedTime;
    }

    public float getOriginalSpeed() {
        return originalSpeed;
    }

    public void setOriginalSpeed(float originalSpeed) {
        this.originalSpeed = originalSpeed;
    }

    public void applyPowerUP(Entity PowerUp){
        this.powerUpNTBA = PowerUp;
    }

}