package com.dungeondevs.systems;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.dungeondevs.DungeonGame;
import com.dungeondevs.components.HealthComponent;
import com.dungeondevs.components.PlayerCharacterComponent;

@All({PlayerCharacterComponent.class})
public class WinningSystem extends EntityProcessingSystem {

    private final DungeonGame dungeonGame;

    public WinningSystem(DungeonGame dungeonGame) {
        this.dungeonGame = dungeonGame;
    }

    @Override
    protected void process(Entity entity) {
        if (entity.getComponent(PlayerCharacterComponent.class).aGagne){
            dungeonGame.Wingame();
        }
    }
}
