package com.dungeondevs.dungeongame;

public class Monster extends Entity{
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
