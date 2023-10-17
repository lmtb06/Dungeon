package screens;

import com.dungeondevs.dungeongame.DungeonGame;
import screens.scenes.PauseScreen;
import screens.scenes.WorldScreen;

public class ScreenManager {

    private DungeonGame game;

    private GameScreen currentScreen;

    private WorldScreen worldScreen;

    private PauseScreen pauseScreen;

    public ScreenManager(DungeonGame dg, PauseScreen ps, WorldScreen ws){
        this.pauseScreen = ps;
        this.worldScreen = ws;
        this.game = dg;
    }

    public void changerScreen(GameScreen gs){
        this.currentScreen = gs;
        this.game.setScreen(gs);
    }
}
