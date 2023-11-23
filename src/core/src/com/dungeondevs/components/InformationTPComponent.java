package com.dungeondevs.components;

import com.artemis.Component;

/**
 * Composant ayant pour rôle de gérer la téléportation.
 */
public class InformationTPComponent extends Component {

    /**
     * id du teleporteur AU SEIN D'UNE SALLE
     */
    public int idTeleporteur;

    /**
     * id du teleporteur vers lequel il teleporte
     */
    public int idTeleporteurAssocie;

    /**
     * String qui désigne la direction de laquelle on va sortir du téléporteur
     */
    public String directionDeSortie;

    /**
     * position X vers laquelle le joueur est téléporté
     */
    public Float TPVersLaPositionX;

    /**
     * position Y vers laquelle le joueur est téléporté
     */
    public Float TPVersLaPositionY;

    /**
     * position X du téléporteur
     */
    public Float coordonneeX;

    /**
     * position Y du téléporteur
     */
    public Float coordonneeY;
}
