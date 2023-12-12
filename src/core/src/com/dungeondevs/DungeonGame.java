package com.dungeondevs;

import com.badlogic.gdx.Game;
import com.dungeondevs.screens.GameOverScreen;
import com.dungeondevs.screens.GameScreen;
import com.dungeondevs.screens.WinScreen;

public class DungeonGame extends Game {

    private GameScreen gameScreen;
    private GameOverScreen gameOverScreen;

    private WinScreen winScreen;
    @Override
    public void create() {
        gameScreen = new GameScreen(this);
        gameOverScreen = new GameOverScreen(this);
        winScreen = new WinScreen(this);
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

    public void Wingame() {
        winScreen.reinitialize();
        setScreen(winScreen);
    }

}
