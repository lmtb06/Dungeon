package com.dungeondevs.dungeongame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import screens.WorldScreen;

public class Jeu extends Game {

    @Override
    public void create() {
//        dungeon = new Dungeon();
        WorldScreen ws = new WorldScreen();
        setScreen(ws);
        Gdx.input.setInputProcessor(ws.getInputProcessor());
    }

    @Override
    public void dispose() {

    }
}
