package com.dungeondevs.components;

import com.artemis.Component;

public class AttackComponent extends Component {

    public String arme = "epee";
    private int damages = 0;
    private long attackDelay = 500;

    public int getDamages() {
        return damages;
    }

    public void setDamage(int attackDamages) { this.damages = attackDamages; }

    public long getAttackDelay() {
        return attackDelay;
    }

    public void setAttackDelay(long attackDelay) {
        this.attackDelay = attackDelay;
    }
}
