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
            .add(AttackComponent.class)
            .add(MovementComponent.class);

    public static final ArchetypeBuilder ATTACK_ENTITY_ARCHETYPE = new ArchetypeBuilder()
            .add(PhysicsComponent.class)
            .add(ContactDamageComponent.class)
            .add(AttackEntityComponent.class)
            .add(RenderingComponent.class);
}
