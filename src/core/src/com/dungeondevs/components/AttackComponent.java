package com.dungeondevs.components;

import com.artemis.Component;

public class AttackComponent extends Component {

    public String arme = "epee";
    private int damages = 4;
    private long attackDelay = 500;

    public int getDamages() {
        return damages;
    }

    public void setDamage(int attackDamages) { this.damages = attackDamages; }

    public long getAttackDelay() {
        return attackDelay;
    }
}
