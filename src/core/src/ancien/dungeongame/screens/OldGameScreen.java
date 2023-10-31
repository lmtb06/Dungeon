package ancien.dungeongame.screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public abstract class OldGameScreen implements Screen {

    public abstract InputProcessor getInputProcessor();

    public abstract void setInputProcessor(InputProcessor ip);

}
