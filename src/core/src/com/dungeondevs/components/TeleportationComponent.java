package com.dungeondevs.components;

import com.artemis.Component;

/**
 * Component ayant pour but d'être lu par le system de téléportation dans le but de téléporter le joueur a une position donnée
 */
public class TeleportationComponent extends Component {

    /**
     * position X de l'endroit où le joueur doit être téléorté
     */
    public float X = 0;

    /**
     * position Y de l'endroit où le joueur doit être téléorté
     */
    public float Y = 0;

    /**
     * booléen qui désigne si une téléportation est en attente
     */
    public boolean doitEtreFait = false;
}
