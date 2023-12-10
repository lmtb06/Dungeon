package com.dungeondevs.effect;

import com.dungeondevs.components.HealthComponent;

public class InflictDefinedAmountDamage extends HealthEffect{

    public InflictDefinedAmountDamage (HealthComponent receiver, int amount) {
        super(receiver);
        receiver.damage(amount);
    }

    @Override
    protected int newHealth(int previousHealth) {
        return 0;
    }

    @Override
    protected int newMaxHealth(int previousMaxHealth) {
        return 0;
    }

    @Override
    protected boolean newInvincible(boolean previousInvincible) {
        return false;
    }
}
