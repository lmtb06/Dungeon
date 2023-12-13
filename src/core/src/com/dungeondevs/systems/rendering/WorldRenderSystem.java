package com.dungeondevs.systems.rendering;

import com.artemis.BaseSystem;
import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeondevs.components.Maps.LoadMapComponent;
import com.dungeondevs.components.rendering.RoomRendererComponent;

@All({RoomRendererComponent.class, LoadMapComponent.class})
public class WorldRenderSystem extends EntityProcessingSystem {

    private OrthogonalTiledMapRenderer renderer;
    private Viewport viewport;

    public WorldRenderSystem(Viewport viewport) {
        this.viewport = viewport;
    }

    @Override
    protected void process(Entity e) {

        LoadMapComponent loadMapComponent = e.getComponent(LoadMapComponent.class);
        if(!loadMapComponent.loaded)
            return;

        float unitScale = 1 / 32f;
        TiledMap map = loadMapComponent.map;
        MapProperties mapProperties = map.getProperties();

        //map.getLayers().remove(map.getLayers().get("collision"));
        this.renderer = new OrthogonalTiledMapRenderer(map, unitScale);

        OrthographicCamera oc = (OrthographicCamera) viewport.getCamera();
        oc.position.set(mapProperties.get("width", Integer.class)/4f, mapProperties.get("height", Integer.class)/4f, 0);
        //oc.update();
        oc.zoom = 0.75f;
        renderer.setView(oc);
        renderer.render();
    }



}
