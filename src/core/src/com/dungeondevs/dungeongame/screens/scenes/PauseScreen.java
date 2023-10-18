package com.dungeondevs.dungeongame.screens.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.dungeondevs.dungeongame.screens.GameScreen;
import com.dungeondevs.dungeongame.screens.ScreenManager;
import com.dungeondevs.dungeongame.screens.input_processors.PauseInputProcessor;

public class PauseScreen extends GameScreen {

    protected ScreenManager screenManager;

    protected PauseInputProcessor pauseScreenInput;

    public PauseScreen(ScreenManager screenManager, WorldScreen ws) {
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
