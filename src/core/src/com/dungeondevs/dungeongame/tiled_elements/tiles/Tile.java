package com.dungeondevs.dungeongame.tiled_elements.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tile {

    protected int tileSize = 60;

    public abstract void render(SpriteBatch batch, int x, int y);

}
