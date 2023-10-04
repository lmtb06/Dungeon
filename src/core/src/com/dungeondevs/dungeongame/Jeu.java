package com.dungeondevs.dungeongame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class Jeu extends Game {
    @Override
    public void create() {
        setScreen(new Dungeon());
    }

    @Override
    public void resize(int width, int height) {

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
