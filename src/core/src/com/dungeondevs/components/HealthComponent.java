package com.dungeondevs.components;

import com.artemis.Component;

/**
 * Component qui gére la vie de l'entité.
 */
public class HealthComponent extends Component {

    private int health;
    private int maxHealth;
    private boolean invincible;

    /**
     * Constructeur.
     * @param maxHealth le nombre de hp max que l'entité peut avoir
     * @param health la vie de l'entité
     * @param invincible true si l'entité est invincible
     */
    public HealthComponent(int maxHealth, int health, boolean invincible) {
        this.setMaxHealth(maxHealth);
        this.setHealth(health);
        this.setInvincible(invincible);
    }


    /**
     * Retourne la vie de l'entité.
     * @return la vie de l'entité
     */
    public int getHealth() {
        return health;
    }

    /**
     * Définit la vie de l'entité.
     * @param health la vie de l'entité
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Retourne le nombre de hp max que l'entité peut avoir.
     * @return le maxHealth
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Définit le maxHealth.
     * @param maxHealth le maxHealth
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Retourne si l'entité est invincible.
     * @return true si l'entité est invincible
     */
    public boolean isInvincible() {
        return invincible;
    }

    /**
     * Définit si l'entité est invincible.
     * @param invincible true si l'entité est invincible
     */
    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    /**
     * Inflige des dégâts à l'entité.
     * La vie ne peut pas être négative.
     * @param damage le nombre de dégâts à infliger
     */
    public void inflictDamage(int damage) {
        if (!this.isInvincible()) { // Inflige des dégâts seulement si l'entité n'est pas invincible
            this.setHealth(
                    Math.max(0, // On ne peut pas avoir de hp négatif
                            this.getHealth() - damage
                    )
            );
        }
    }
    /**
     * Soigne l'entité.
     * La vie ne peut pas dépasser le maxHealth.
     * @param hpAmount le nombre de hp à ajouter
     */
    public void heal(int hpAmount){
        this.setHealth(
                Math.min(this.getMaxHealth(), // On ne peut pas avoir plus de hp que le max
                        this.getHealth() + hpAmount
                )
        );
    }
}
