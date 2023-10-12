package com.dungeondevs.dungeongame;

import com.badlogic.gdx.Game;
import screens.WorldScreen;

public class Jeu extends Game {

    @Override
    public void create() {
//        dungeon = new Dungeon();
        setScreen(new WorldScreen());
    }

    @Override
    public void dispose() {

    }
}
