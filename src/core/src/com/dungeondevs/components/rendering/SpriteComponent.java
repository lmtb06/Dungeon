package com.dungeondevs.components.rendering;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent extends Component {

    private Sprite sprite;

    public void setSprite(String textureFile) {
        setSprite(textureFile, 16, 16, 0);
    }

    public void setSprite(String textureFile, int width, int height, float rotation) {
        Texture texture = new Texture(textureFile);
        this.sprite = new Sprite(texture, width, height);
        this.sprite.setRotation(rotation);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
