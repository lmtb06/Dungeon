package com.dungeondevs.utils;

import com.artemis.ArchetypeBuilder;
import com.dungeondevs.components.*;
import com.dungeondevs.components.Level.PorteComponent;
import com.dungeondevs.components.Level.SalleAssocieeComponent;
import com.dungeondevs.components.Maps.ActiveEntity;
import com.dungeondevs.components.Maps.LoadMapComponent;
import com.dungeondevs.components.ArmeComponent;

public class GameArchetypes {
    public static final ArchetypeBuilder PLAYER_CHARACTER_ARCHETYPE = new ArchetypeBuilder()
            .add(EntityStateComponent.class)
            .add(PhysicsComponent.class)
            .add(RenderingComponent.class)
            .add(AnimationComponent.class)
            .add(InputComponent.class)
            .add(SalleAssocieeComponent.class)
            .add(AttackComponent.class)
            .add(HealthComponent.class)
            .add(MovementComponent.class)
            .add(PowerUpUserComponent.class)
            .add(MovementComponent.class)
            .add(PlayerCharacterComponent.class)
            .add(TeleportationComponent.class)
            .add(MovementComponent.class)
            .add(DirectionComponent.class);

    public static final ArchetypeBuilder MAP_ARCHETYPE = new ArchetypeBuilder()
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
            .add(RenderingComponent.class)
            .add(AnimationComponent.class)
            .add(SalleAssocieeComponent.class)
            .add(ContactDamageComponent.class)
            .add(HealthComponent.class)
            .add(ActiveEntity.class);

    public static final ArchetypeBuilder ATTACK_ENTITY_ARCHETYPE = new ArchetypeBuilder()
            .add(PhysicsComponent.class)
            .add(ContactDamageComponent.class)
            .add(AttackEntityComponent.class)
            .add(RenderingComponent.class);

    public static final ArchetypeBuilder TRAP_ENTITY_ARCHETYPE = new ArchetypeBuilder()
            .add(PhysicsComponent.class)
            .add(ContactDamageComponent.class)
            .add(SalleAssocieeComponent.class)
            .add(ActifSalleActuelleComponent.class)
            .add(RenderingComponent.class);

    public static final ArchetypeBuilder TELEPORTEUR_ENTITY_ARCHETYPE = new ArchetypeBuilder()
            .add(PhysicsComponent.class)
            .add(SalleAssocieeComponent.class)
            .add(InformationTPComponent.class)
            .add(RenderingComponent.class);

    public static final ArchetypeBuilder POWER_UP_ARCHETYPE = new ArchetypeBuilder()
            .add(PhysicsComponent.class)
            .add(SalleAssocieeComponent.class)
            .add(ActiveEntity.class)
            .add(PowerUpTypeComponent.class);

    public static final ArchetypeBuilder ARME_ARCHETYPE = new ArchetypeBuilder()
            .add(PhysicsComponent.class)
            .add(SalleAssocieeComponent.class)
            .add(ActiveEntity.class)
            .add(ActifSalleActuelleComponent.class)
            .add(ArmeComponent.class);
}
