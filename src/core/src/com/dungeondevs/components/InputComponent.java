package com.dungeondevs.components;

import com.artemis.Component;

/**
 * Component où sont stockées les entrées de l'entité.
 */
public class InputComponent extends Component {
    /**
     * l'entité veut aller à gauche.
     */
    private boolean left;
    /**
     * l'entité veut aller à droite.
     */
    private boolean right;
    /**
     * l'entité veut aller en haut.
     */
    private boolean up;
    /**
     * l'entité veut aller en bas.
     */
    private boolean down;
    /**
     * l'entité veut attaquer.
     */
    private boolean attack;
    /**
     * l'entité veut attaquer à distance.
     */
    private boolean rangedAttack;

    /**
     * getter pour savoir si l'entité veut aller à gauche.
     * @return true si l'entité veut aller à gauche.
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * setter pour dire si l'entité veut aller à gauche.
     * @param left true si l'entité veut aller à gauche.
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * getter pour savoir si l'entité veut aller à droite.
     * @return true si l'entité veut aller à droite.
     */
    public boolean isRight() {
        return right;
    }

    /**
     * setter pour dire si l'entité veut aller à droite.
     * @param right true si l'entité veut aller à droite.
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * getter pour savoir si l'entité veut aller en haut.
     * @return true si l'entité veut aller en haut.
     */
    public boolean isUp() {
        return up;
    }

    /**
     * setter pour dire si l'entité veut aller en haut.
     * @param up true si l'entité veut aller en haut.
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * getter pour savoir si l'entité veut aller en bas.
     * @return true si l'entité veut aller en bas.
     */
    public boolean isDown() {
        return down;
    }

    /**
     * setter pour dire si l'entité veut aller en bas.
     * @param down true si l'entité veut aller en bas.
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * getter pour savoir si l'entité veut attaquer.
     * @return true si l'entité veut attaquer.
     */
    public boolean isAttack() {
        return attack;
    }

    /**
     * setter pour dire si l'entité veut attaquer.
     * @param attack true si l'entité veut attaquer.
     */
    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    /**
     * getter pour savoir si l'entité veut attaquer à distance.
     * @return true si l'entité veut attaquer à distance.
     */
    public boolean isRangedAttack() {
        return rangedAttack;
    }

    /**
     * setter pour dire si l'entité veut attaquer à distance.
     * @param rangedAttack true si l'entité veut attaquer à distance.
     */
    public void setRangedAttack(boolean rangedAttack) {
        this.rangedAttack = rangedAttack;
    }
}
