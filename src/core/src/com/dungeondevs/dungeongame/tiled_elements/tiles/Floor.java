package com.dungeondevs.dungeongame.tiled_elements.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
public class Floor extends Crossable {

    @Override
    public void render(SpriteBatch batch, int x, int y) {
        batch.draw(new Texture("badlogic.jpg"), this.tileSize * x, this.tileSize * y, this.tileSize, this.tileSize);
    }
}
