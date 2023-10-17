package screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public abstract class GameScreen implements Screen {

    public abstract InputProcessor getInputProcessor();

    public abstract void setInputProcessor(InputProcessor ip);

}
