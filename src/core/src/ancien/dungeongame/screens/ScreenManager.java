package ancien.dungeongame.screens;

import ancien.dungeongame.DungeonGame;
import ancien.dungeongame.screens.scenes.WorldScreenOld;
import ancien.dungeongame.screens.scenes.PauseScreenOld;

public class ScreenManager {

    private DungeonGame game;

    private OldGameScreen currentScreen;

    private WorldScreenOld worldScreen;

    private PauseScreenOld pauseScreen;

    public ScreenManager(DungeonGame dg){


        this.game = dg;
    }

    public void changerScreen(OldGameScreen gs){
        this.currentScreen = gs;
        this.game.setScreen(gs);
    }

    public void setWorldScreen(WorldScreenOld ws) {
        this.worldScreen = ws;
    }

    public void setPauseScreen(PauseScreenOld ps) {
        this.pauseScreen = ps;

    }

    public void afficherVuePause(){
        this.changerScreen(pauseScreen);
    }

    public void afficherVueWorldScreen(){
        this.changerScreen(worldScreen);
    }
}
