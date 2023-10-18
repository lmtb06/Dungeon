package com.dungeondevs.dungeongame.screens;

import com.dungeondevs.dungeongame.DungeonGame;
import com.dungeondevs.dungeongame.screens.scenes.PauseScreen;
import com.dungeondevs.dungeongame.screens.scenes.WorldScreen;

public class ScreenManager {

    private DungeonGame game;

    private GameScreen currentScreen;

    private WorldScreen worldScreen;

    private PauseScreen pauseScreen;

    public ScreenManager(DungeonGame dg){


        this.game = dg;
    }

    public void changerScreen(GameScreen gs){
        this.currentScreen = gs;
        this.game.setScreen(gs);
    }

    public void setWorldScreen(WorldScreen ws) {
        this.worldScreen = ws;
    }

    public void setPauseScreen(PauseScreen ps) {
        this.pauseScreen = ps;

    }

    public void afficherVuePause(){
        this.changerScreen(pauseScreen);
    }

    public void afficherVueWorldScreen(){
        this.changerScreen(worldScreen);
    }
}
