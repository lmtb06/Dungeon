package com.dungeondevs.components;

import com.artemis.Component;

public class HealthComponent extends Component {

    public int health = 10;

    public void damage (int amount) {
        this.health -= amount;
        System.out.println("Une entite a pris " + amount + "pts de degets (" + health + " PV restant(s))");
    }

    public int getHealth() {
        return health;
    }
}
