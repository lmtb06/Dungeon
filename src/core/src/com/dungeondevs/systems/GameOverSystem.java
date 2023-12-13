package com.dungeondevs.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.dungeondevs.DungeonGame;
import com.dungeondevs.components.HealthComponent;
import com.dungeondevs.components.PlayerCharacterComponent;

/**
 * Système qui gère la fin de partie si le joueur meurt
 * Le joueur meurt si sa santé est à zéro
 */
@All({PlayerCharacterComponent.class, HealthComponent.class})
public class GameOverSystem extends EntityProcessingSystem {
    private ComponentMapper<HealthComponent> healthMapper;
    private final DungeonGame dungeonGame;

    public GameOverSystem(DungeonGame dungeonGame) {
        this.dungeonGame = dungeonGame;
    }

    @Override
    protected void process(Entity e) {
        HealthComponent health = healthMapper.get(e);

        if (health.getHealth() <= 0) {
            // La santé est à zéro, le joueur est mort.
            dungeonGame.gameOver();
        }
    }

}
