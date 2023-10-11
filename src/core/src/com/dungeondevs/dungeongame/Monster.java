package com.dungeondevs.dungeongame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Monster extends Entity{

    public Monster(int health, double speed, int damages, Vector2 position, Vector2 size, World world) {
        super(health, speed, damages, position, size, world);
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isMonster() {
        return true;
    }

    @Override
    public void move(int x, int y) {

    }
}
