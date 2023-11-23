package com.dungeondevs.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.artemis.Entity;

public class MonsterMovementComponent extends Component {
    public Entity player;
    public MonsterMovementType movementType;

    public float speed = 1f;

    public void setPlayer(Entity player){
        this.player=player;
    }

    public void setMovementType(MonsterMovementType movementType){
        this.movementType=movementType;
    }


}
