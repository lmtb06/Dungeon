package screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public abstract class GameScreen implements Screen {
    private InputProcessor inputProcessor;

    public GameScreen() {
    }

    public InputProcessor getInputProcessor() {
        return inputProcessor;
    }

    public void setInputProcessor(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }
}
