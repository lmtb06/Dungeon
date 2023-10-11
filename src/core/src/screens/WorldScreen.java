package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import level.Level;

public class WorldScreen implements Screen {
    private final Level currentLevel;
    private final Box2DDebugRenderer debugRenderer;
    public WorldScreen() {
        debugRenderer = new Box2DDebugRenderer();
        currentLevel = new Level(25,25);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentLevel.update(delta);

        // Gestion des inputs
        handleInput();

        Viewport viewport = currentLevel.getViewport();

        // rendu debug du monde box2d
        debugRenderer.render(currentLevel.getWorld(), viewport.getCamera().combined);

    }

    private void handleInput() {
        Vector2 vecteurDirection = new Vector2();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) vecteurDirection.x = -1;
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) vecteurDirection.x = 1;

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) vecteurDirection.y = 1;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) vecteurDirection.y = -1;

        currentLevel.getPlayer().move(vecteurDirection);
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
}
