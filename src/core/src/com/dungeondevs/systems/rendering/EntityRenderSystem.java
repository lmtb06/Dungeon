package com.dungeondevs.systems.rendering;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeondevs.components.rendering.SpriteComponent;

@All(SpriteComponent.class)
public class EntityRenderSystem extends EntityProcessingSystem{

    private Batch batch;
    private Viewport viewport;

    public EntityRenderSystem (Batch batch, Viewport viewport) {
        this.batch = batch;
        this.viewport = viewport;
    }

    @Override
    protected void process(Entity e) {
        SpriteComponent spriteComponent = e.getComponent(SpriteComponent.class);
        batch.setProjectionMatrix(viewport.getCamera().projection);

        TextureRegion region = spriteComponent.getTextureRegion();

        batch.begin();
        batch.draw(region, spriteComponent.getPosition().x, spriteComponent.getPosition().y, 32, 8);
        batch.end();
    }

}
