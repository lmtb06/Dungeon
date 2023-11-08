package com.dungeondevs;

import com.badlogic.gdx.Game;
import com.dungeondevs.screens.GameOverScreen;
import com.dungeondevs.screens.GameScreen;

public class DungeonGame extends Game {

    private GameScreen gameScreen;
    private GameOverScreen gameOverScreen;
    @Override
    public void create() {
        gameScreen = new GameScreen(this);
        gameOverScreen = new GameOverScreen(this);
        this.setScreen(gameScreen);
    }

    @Override
    public void render() {
        super.render();
    }

    public void restartGame() {
        gameScreen.reinitialize();
        setScreen(gameScreen);
    }

    public void gameOver() {
        gameOverScreen.reinitialize();
        setScreen(gameOverScreen);
    }

}
