package com.dungeondevs.utils;

import com.artemis.ArchetypeBuilder;
import com.dungeondevs.components.*;

public class GameArchetypes {
    public static final ArchetypeBuilder PLAYER_CHARACTER_ARCHETYPE = new ArchetypeBuilder()
            .add(EntityStateComponent.class)
            .add(PhysicsComponent.class)
            .add(RenderingComponent.class)
            .add(AnimationComponent.class)
            .add(InputComponent.class)
            .add(MovementComponent.class);
}
