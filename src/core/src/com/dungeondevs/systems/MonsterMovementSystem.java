package com.dungeondevs.systems;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import com.dungeondevs.components.*;

@All({MonsterMovementComponent.class})
public class MonsterMovementSystem extends EntityProcessingSystem {

    @Override
    protected void process(Entity e) {
        PhysicsComponent monsterPhysicComponent = e.getComponent(PhysicsComponent.class);
        MonsterMovementComponent monsterMovementComponent = e.getComponent(MonsterMovementComponent.class);
        switch(monsterMovementComponent.movementType){
            case MOVE_TOWARD_PLAYER_IGNORE_COLLISION:
                    Vector2 playerVector = monsterMovementComponent.player.getComponent(PhysicsComponent.class).body.getPosition();
                    monsterPhysicComponent.body.setLinearVelocity(getVectorTowardPlayer(playerVector, monsterPhysicComponent.body.getPosition(),monsterMovementComponent.speed));
                break;
        }

    }

    private Vector2 getVectorTowardPlayer(Vector2 playerPosition, Vector2 monsterPosition, float speed){
        return playerPosition.sub(monsterPosition).setLength(speed);
    }

}
