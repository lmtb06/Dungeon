package ancien.dungeongame;

import com.badlogic.gdx.Game;
import ancien.dungeongame.screens.ScreenManager;
import com.badlogic.gdx.Gdx;
import com.dungeondevs.GameScreen;

import java.util.ArrayList;

public class DungeonGame extends Game {

    private ScreenManager screenManager;

    private GameScreen gs;

    private ArrayList<String> listeMapExistante;


    @Override
    public void create() {

        /**
        //instanciation du screenmanager et des screen qu'il aura à gérer. Tous les screens ne sont pas encore prêts
        this.screenManager = new ScreenManager(this);
        WorldScreen ws = new WorldScreen(this.screenManager);
        PauseScreen ps = new PauseScreen(this.screenManager, ws);
        this.screenManager.setWorldScreen(ws);
        this.screenManager.setPauseScreen(ps);
        **/

        gs = new GameScreen();
        setScreen(gs);
        //on met le worldscreen par défaut pour le moment, on changera pour un écran de démarrage pour le moment
        //this.screenManager.changerScreen(ws);
    }

    private void chargerMaps() {
    }

    @Override
    public void render() {
        gs.render(Gdx.graphics.getDeltaTime());
        /**world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
        super.render();**/
    }

    @Override
    public void dispose() {

    }
}
