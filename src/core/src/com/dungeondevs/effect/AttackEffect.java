package com.dungeondevs.effect;

import com.dungeondevs.components.AttackComponent;

public abstract class AttackEffect implements Effect {

    public AttackEffect (AttackComponent receiver) {

    }

    public final void apply () {

    }

    public final void unapply () {

    }

    protected abstract int newDamage(int previousDamage);
    protected abstract float newAttackSpeed(float previousAttackSpeed);
    protected abstract float newLastAttackTime(float previousLastAttackTime);
    protected abstract String newWeaponName(String previousWeaponName);
    protected abstract float newAttackCooldown (float previousAttackCooldown);

}
