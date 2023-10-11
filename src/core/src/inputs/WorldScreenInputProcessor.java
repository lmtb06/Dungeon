package inputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import screens.WorldScreen;

public class WorldScreenInputProcessor implements InputProcessor {
    private WorldScreen worldScreen;
    public WorldScreenInputProcessor(WorldScreen worldScreen) {
        this.worldScreen = worldScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode)
        {
            case Input.Keys.LEFT:
                worldScreen.setMoveLeft(true);
                break;
            case Input.Keys.RIGHT:
                worldScreen.setMoveRight(true);
                break;
            case Input.Keys.UP:
                worldScreen.setMoveUp(true);
                break;
            case Input.Keys.DOWN:
                worldScreen.setMoveDown(true);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode)
        {
            case Input.Keys.LEFT:
                worldScreen.setMoveLeft(false);
                break;
            case Input.Keys.RIGHT:
                worldScreen.setMoveRight(false);
                break;
            case Input.Keys.UP:
                worldScreen.setMoveUp(false);
                break;
            case Input.Keys.DOWN:
                worldScreen.setMoveDown(false);
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
