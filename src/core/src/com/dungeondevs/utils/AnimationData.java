package com.dungeondevs.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationData {

    private int rows;
    private int columns;
    private Animation<TextureRegion> animation;
    private float stateTime;

    public AnimationData (int rows, int columns, String textureFileName, float frameDuration) {
        Texture texture = new Texture(textureFileName);
        TextureRegion[][] textureRegionSplit = TextureRegion.split(texture, texture.getWidth() / columns, texture.getHeight() / rows);
        TextureRegion[] textureRegion = new TextureRegion[rows * columns];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                textureRegion[index++] = textureRegionSplit[i][j];
            }
        }
        animation = new Animation<TextureRegion>(frameDuration, textureRegion);
    }

    public AnimationData (int rows, int columns, String textureFileName) {
        new AnimationData(rows, columns, textureFileName, 0.025f);
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public void addToStateTime (float time) {
        this.stateTime += time;
    }

    public float getStateTime() {
        return stateTime;
    }
}
