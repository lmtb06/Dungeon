package com.dungeondevs.factories;

import com.artemis.Entity;
/**
 * Factory qui construit une entité et ses components
 */
public interface EntityFactory {
    /**
     * Crée l'entité et ses components.
     */
    public void createEntity();
}
