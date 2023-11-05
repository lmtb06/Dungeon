package com.dungeondevs.components;

import com.artemis.Component;

public class HealthComponent extends Component {

    private int health;

    public HealthComponent (int startHealth) {
        super();
        this.health = startHealth;
    }
}
