package com.dungeondevs.utils;

import com.artemis.ArchetypeBuilder;
import com.dungeondevs.components.*;
import com.dungeondevs.components.Level.PorteComponent;
import com.dungeondevs.components.Level.SalleAssocieeComponent;
import com.dungeondevs.components.Maps.ActiveEntity;
import com.dungeondevs.components.Maps.LoadMapComponent;
import com.dungeondevs.components.rendering.AnimationListComponent;
import com.dungeondevs.components.rendering.RoomRendererComponent;
import com.dungeondevs.components.rendering.SpriteComponent;

public class GameArchetypes {
    public static final ArchetypeBuilder PLAYER_CHARACTER_ARCHETYPE = new ArchetypeBuilder()
            .add(EntityStateComponent.class)
            .add(PhysicsComponent.class)
            .add(InputComponent.class)
            .add(SalleAssocieeComponent.class)
            .add(AttackComponent.class)
            .add(HealthComponent.class)
            .add(MovementComponent.class)
            .add(PowerUpUserComponent.class)
            .add(MovementComponent.class)
            .add(PlayerCharacterComponent.class)
            .add(TeleportationComponent.class)
            .add(AnimationListComponent.class)
            .add(MovementComponent.class);

    public static final ArchetypeBuilder MAP_ARCHETYPE = new ArchetypeBuilder()
            .add(RoomRendererComponent.class)
            .add(LoadMapComponent.class);


    public static final ArchetypeBuilder PORTE_ARCHETYPE = new ArchetypeBuilder()
            .add(PhysicsComponent.class)
            .add(SalleAssocieeComponent.class)
            .add(PorteComponent.class)
            .add(ActiveEntity.class);

    public static final ArchetypeBuilder MUR_ARCHETYPE = new ArchetypeBuilder()
            .add(PhysicsComponent.class)
            .add(SalleAssocieeComponent.class)
            .add(ActiveEntity.class);

    public static final ArchetypeBuilder MONSTRE_ARCHETYPE = new ArchetypeBuilder()
            .add(PhysicsComponent.class)
            .add(SalleAssocieeComponent.class)
            .add(ContactDamageComponent.class)
            .add(HealthComponent.class)
            .add(AnimationListComponent.class)
            .add(ActiveEntity.class);

    public static final ArchetypeBuilder ATTACK_ENTITY_ARCHETYPE = new ArchetypeBuilder()
            .add(PhysicsComponent.class)
            .add(ContactDamageComponent.class)
            .add(AttackEntityComponent.class);

    public static final ArchetypeBuilder TRAP_ENTITY_ARCHETYPE = new ArchetypeBuilder()
            .add(PhysicsComponent.class)
            .add(ContactDamageComponent.class)
            .add(SalleAssocieeComponent.class)
            .add(PiegeActifComponent.class)
            .add(SpriteComponent.class);

    public static final ArchetypeBuilder TELEPORTEUR_ENTITY_ARCHETYPE = new ArchetypeBuilder()
            .add(PhysicsComponent.class)
            .add(SalleAssocieeComponent.class)
            .add(SpriteComponent.class)
            .add(InformationTPComponent.class);

    public static final ArchetypeBuilder POWER_UP_ARCHETYPE = new ArchetypeBuilder()
            .add(PhysicsComponent.class)
            .add(SalleAssocieeComponent.class)
            .add(ActiveEntity.class)
            .add(PowerUpTypeComponent.class)
            .add(SpriteComponent.class);
}
