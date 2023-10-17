package com.dungeondevs.dungeongame;

import com.badlogic.gdx.Game;
import screens.ScreenManager;
import screens.scenes.PauseScreen;
import screens.scenes.WorldScreen;

public class DungeonGame extends Game {

    private ScreenManager screenManager;

    @Override
    public void create() {
        WorldScreen ws = new WorldScreen();
        PauseScreen ps = new PauseScreen();
        this.screenManager = new ScreenManager(this, ps, ws);
        this.screenManager.changerScreen(ws);
//        dungeon = new Dungeon();
        //setScreen();
    }

    @Override
    public void dispose() {

    }
}
