package ancien.dungeongame.screens.scenes;

import ancien.dungeongame.screens.ScreenManager;
import ancien.dungeongame.screens.input_processors.PauseInputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import ancien.dungeongame.screens.OldGameScreen;

public class PauseScreenOld extends OldGameScreen {

    protected ScreenManager screenManager;

    protected PauseInputProcessor pauseScreenInput;

    public PauseScreenOld(ScreenManager screenManager, WorldScreenOld ws) {
        super();
        this.screenManager = screenManager;
        this.pauseScreenInput = new PauseInputProcessor(ws);
        Gdx.input.setInputProcessor(this.pauseScreenInput);
    }

    @Override
    public InputProcessor getInputProcessor() {
        return null;
    }

    @Override
    public void setInputProcessor(InputProcessor ip) {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
