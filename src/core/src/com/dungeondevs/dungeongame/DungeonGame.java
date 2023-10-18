package com.dungeondevs.dungeongame;

import com.badlogic.gdx.Game;
import com.dungeondevs.dungeongame.screens.ScreenManager;
import com.dungeondevs.dungeongame.screens.scenes.PauseScreen;
import com.dungeondevs.dungeongame.screens.scenes.WorldScreen;

public class DungeonGame extends Game {

    private ScreenManager screenManager;

    @Override
    public void create() {
        //instanciation du screenmanager et des screen qu'il aura à gérer. Tous les screens ne sont pas encore prêts
        this.screenManager = new ScreenManager(this);
        WorldScreen ws = new WorldScreen(this.screenManager);
        PauseScreen ps = new PauseScreen(this.screenManager, ws);
        this.screenManager.setWorldScreen(ws);
        this.screenManager.setPauseScreen(ps);



        //on met le worldscreen par défaut pour le moment, on changera pour un écran de démarrage pour le moment
        this.screenManager.changerScreen(ws);
    }

    @Override
    public void dispose() {

    }
}
