package com.dungeondevs;

import com.badlogic.gdx.Game;
import com.dungeondevs.screens.GameScreen;

public class DungeonGame extends Game {

    @Override
    public void create() {
        this.setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

}
