package com.dungeondevs.dungeongame;

import com.badlogic.gdx.Game;

public class Jeu extends Game {

    private Dungeon dungeon;

    @Override
    public void create() {
        dungeon = new Dungeon();
        setScreen(dungeon);
    }

    @Override
    public void resize(int width, int height) {
        dungeon.resize(width, height);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {

    }
}
