package com.dungeondevs.components;

import com.artemis.Component;

public class ContactDamageComponent extends Component {

    private int damages = 4;

    public int getDamages() {
        return damages;
    }

    public void setDamages(int damages) {
        this.damages = damages;
    }
}
