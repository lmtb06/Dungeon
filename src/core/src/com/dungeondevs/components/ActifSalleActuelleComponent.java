package com.dungeondevs.components;

import com.artemis.Component;

/**
 * Composant ayant pour rôle de désigner si une entité est active ou non dans la salle active
 */
public class ActifSalleActuelleComponent extends Component {

    /**
     * booleen désignant le fait de devoir réapparaitre ou non dans la salle
     */
    public boolean action = true;
}
