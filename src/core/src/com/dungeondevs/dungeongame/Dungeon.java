package com.dungeondevs.dungeongame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Dungeon implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private OrthographicCamera camera;

    private Viewport fenetre;



    static final float WORLD_WIDTH = 70;
    static final float WORLD_HEIGHT = 70;

    @Override
    public void show() {



        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/test2.tmx");

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(WORLD_WIDTH,WORLD_HEIGHT * (h/w));


        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0f);
        camera.update();



        renderer = new OrthogonalTiledMapRenderer(map);

        /**
        camera.setToOrtho(false, 160*((Gdx.graphics.getWidth()/2) / (Gdx.graphics.getWidth()/2)), 120*((Gdx.graphics.getWidth()/2) / (Gdx.graphics.getWidth()/2)));
        **/camera.zoom = 0.5f * ((Gdx.graphics.getWidth()/2) / (Gdx.graphics.getWidth()/2));

        fenetre = new FitViewport(800,480,camera);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //renderer.getBatch().setProjectionMatrix(camera.combined);
        //renderer.setView(fenetre);
        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        /**camera.viewportWidth = 30f;
        camera.viewportHeight = 30f * height/width;
        camera.update();**/
        /**fenetre.update(width, height);
        fenetre.apply();**/

        fenetre.update(800,480);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }
}
