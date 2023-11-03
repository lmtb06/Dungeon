package com.dungeondevs.components;

import com.artemis.Component;

public class AttackComponent extends Component {

    private int damages = 4;
    private long attackDelay = 500;

    public int getDamages() {
        return damages;
    }

    public long getAttackDelay() {
        return attackDelay;
    }
}
