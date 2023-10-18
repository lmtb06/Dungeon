package com.dungeondevs.dungeongame.screens.input_processors;

import com.badlogic.gdx.InputProcessor;
import com.dungeondevs.dungeongame.screens.ScreenManager;
import com.dungeondevs.dungeongame.screens.scenes.WorldScreen;

public class PauseInputProcessor implements InputProcessor {

    private WorldScreen world;
    private ScreenManager screenManager;

    public PauseInputProcessor(WorldScreen ws){
        super();
        this.world = ws;
        this.screenManager = this.world.getScreenManager();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case 111:
                System.out.println("wllh");
                //this.screenManager.afficherVuePause();
                break;
        }


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
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

