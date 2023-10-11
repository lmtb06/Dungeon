package com.dungeondevs.dungeongame;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {

    protected int health;
    protected double speed;
    protected Body body;
    protected int damages;

    /**
     * Récupère la santé actuelle de l'entité.
     * @return le nombre de points de vie de l'entité
     */
    public int getHealth() {
        return health;
    }

    /**
     * Cause des dégâts à l'entité.
     * @param damages la quantité de dégâts à appliquer
     */
    public void damage (int damages) {
        this.health -= damages;
    }

    /**
     * L'entité est-elle le joueur?
     * @return
     */
    public abstract boolean isPlayer();

    /**
     * L'entité est-elle un monstre?
     * @return
     */
    public abstract boolean isMonster();

    /**
     * Déplace l'entité dans une certaine direction.
     * @param x
     * @param y
     */
    public abstract void move(int x, int y);
}
