package screens;

import com.badlogic.gdx.Screen;
import level.Level;

public class WorldScreen implements Screen {
    private Level currentLevel;
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        currentLevel.update(delta);
        currentLevel.render();
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
