package com.dungeondevs.components;

import com.artemis.Component;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Composant ayant pour rôle de gérer l'entité créée lorsque le joueur attaque.
 */
public class AttackEntityComponent extends Component {

    /**
     * Moment auquel a été crée l'entité d'attaque.
     */
    public long startTime = 0;

    /**
     * Temps avant que l'entité d'attaque s'autodétruise (en millisecondes).
     */
    public long autoDestroyTime = 3000;

    /**
     * Le corps auquel est lié l'entité d'attaque (typiquement le joueur) pour qu'il puisse suivre sa position.
     */
    public Body boundBody;

    /**
     * Décalage à laquelle l'attaque est effectuée et doit suivre le joueur.
     */
    public float offset = 0.55f;

    public boolean projectile = false;
    public boolean dejaLance = false;
    public boolean adetruire = false;
}
