package com.dungeondevs.effect;

import com.dungeondevs.components.HealthComponent;

public abstract class HealthEffect implements Effect{

    public HealthEffect (HealthComponent receiver){

    }

    public final void apply () {

    }

    public final void unapply () {

    }

    protected abstract int newHealth (int previousHealth);
    protected abstract int newMaxHealth (int previousMaxHealth);
    protected abstract boolean newInvincible (boolean previousInvincible);

}
