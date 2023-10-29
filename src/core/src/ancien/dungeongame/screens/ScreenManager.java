package ancien.dungeongame.screens;

import ancien.dungeongame.DungeonGame;
import ancien.dungeongame.screens.scenes.WorldScreen;
import ancien.dungeongame.screens.scenes.PauseScreen;

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
