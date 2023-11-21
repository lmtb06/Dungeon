package com.dungeondevs.components.rendering;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent extends Component {

    private Sprite sprite;

    public void setSprite(String textureFile) {
        Texture texture = new Texture(textureFile);
        this.sprite = new Sprite(texture, 16, 16);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
