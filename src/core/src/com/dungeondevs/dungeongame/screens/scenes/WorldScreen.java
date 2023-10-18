package com.dungeondevs.dungeongame.screens.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeondevs.dungeongame.tiled_elements.Level;
import com.dungeondevs.dungeongame.screens.ScreenManager;
import com.dungeondevs.dungeongame.screens.input_processors.WorldScreenInputProcessor;
import com.dungeondevs.dungeongame.screens.GameScreen;

public class WorldScreen extends GameScreen {

    private ScreenManager screenManager;
    private final Level currentLevel;
    private final Box2DDebugRenderer debugRenderer;

    private WorldScreenInputProcessor worldScreenInputProcessor;

    private SpriteBatch spriteBatch;
    private float state;


    private OrthogonalTiledMapRenderer renderer;

    public WorldScreen(ScreenManager sm) {

        this.screenManager = sm;

        this.worldScreenInputProcessor = new WorldScreenInputProcessor(this);
        Gdx.input.setInputProcessor(this.worldScreenInputProcessor);

        debugRenderer = new Box2DDebugRenderer();
        currentLevel = new Level(25,25);

        spriteBatch = new SpriteBatch();

        TmxMapLoader loader = new TmxMapLoader();

        //map = loader.load("maps/test2.tmx");



        renderer = new OrthogonalTiledMapRenderer(this.currentLevel.getRoomActuel().getMap(),25/(this.currentLevel.getRoomActuel().getMap().getProperties().get("width",Integer.class)*32f));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        state += Gdx.graphics.getDeltaTime();
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentLevel.update(delta);

        // Gestion des inputs
        handleInput();

        Viewport viewport = currentLevel.getViewport();

        TextureRegion playerAnimationFrame = currentLevel.getPlayer().getAnimation().getKeyFrame(state, true);

        Vector2 playerPosition = currentLevel.getPlayer().getPosition();
        OrthographicCamera cam = currentLevel.getCam();

        renderer.setView(cam);
        renderer.render();

        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.draw(playerAnimationFrame, playerPosition.x - 0.5f, playerPosition.y - 0.5f, 1f, 1f);
        spriteBatch.end();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        // rendu debug du monde box2d
        debugRenderer.render(currentLevel.getWorld(), cam.combined);

    }

    private void handleInput() {
        Vector2 vecteurDirection = new Vector2();
        if (Gdx.input.isKeyPressed(Input.Keys.A)) vecteurDirection.x = -1;
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) vecteurDirection.x = 1;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) vecteurDirection.y = 1;
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) vecteurDirection.y = -1;

        currentLevel.getPlayer().move(vecteurDirection);

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            currentLevel.getPlayer().attack(currentLevel.getWorld());
    }

    @Override
    public void resize(int width, int height) {
        currentLevel.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        currentLevel.dispose();
        debugRenderer.dispose();
    }

    @Override
    public InputProcessor getInputProcessor() {
        return null;
    }

    @Override
    public void setInputProcessor(InputProcessor ip) {

    }

    public ScreenManager getScreenManager() {
        return screenManager;
    }

    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }
}
