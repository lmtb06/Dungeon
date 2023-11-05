package com.dungeondevs.systems;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeondevs.components.Maps.LoadMapComponent;

@All(LoadMapComponent.class)
public class MapRendererSystem extends EntityProcessingSystem {

    private OrthogonalTiledMapRenderer renderer;

    private OrthographicCamera cam;
    private Viewport viewport;

    @Override
    protected void process(Entity e) {

        /**if (e.getComponent(MapStateComponent.class).etat.equals(LevelState.actuelleAttenteAffichage)){

            TmxMapLoader loader = new TmxMapLoader();
            //map = loader.load("maps/test2.tmx");
            TiledMap map = loader.load(e.getComponent(LoadMapComponent.class).lienAsset);

            System.out.println("attente : "+map);


            //OrthographicCamera oc = new OrthographicCamera();

            cam = new OrthographicCamera(25,25);

            cam.update();

            renderer = new OrthogonalTiledMapRenderer(map,25/(map.getProperties().get("width",Integer.class)*32f));
            renderer.setView(cam);

            this.viewport = new FitViewport(25, 25, cam);

            e.getComponent(MapStateComponent.class).etat = LevelState.commencee;

        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        renderer.render();
    **/
    }

    public Viewport getViewport() {
        return viewport;
    }
}
