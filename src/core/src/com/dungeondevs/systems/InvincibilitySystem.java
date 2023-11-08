package com.dungeondevs.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.dungeondevs.components.InvincibilityComponent;

@All(InvincibilityComponent.class)
public class InvincibilitySystem extends EntityProcessingSystem {
    private ComponentMapper<InvincibilityComponent> invincibilityComponentMapper;
    @Override
    protected void process(Entity e) {
        InvincibilityComponent invincibilityComponent = invincibilityComponentMapper.get(e);
        invincibilityComponent.timeRemaining -= world.getDelta();
        if (invincibilityComponent.timeRemaining <= 0) {
            // Delete the invincibility component from the entity
            e.edit().remove(InvincibilityComponent.class);
        }
    }
}
