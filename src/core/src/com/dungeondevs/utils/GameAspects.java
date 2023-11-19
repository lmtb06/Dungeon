package com.dungeondevs.utils;

import com.artemis.Aspect;
import com.dungeondevs.components.*;

public class GameAspects {
    public static final Aspect.Builder PLAYER_CHARACTER_ASPECT = Aspect.all(EntityStateComponent.class
            ,PhysicsComponent.class
            ,InputComponent.class
            , MovementComponent.class);
}
