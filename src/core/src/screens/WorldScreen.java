package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import inputs.WorldScreenInputProcessor;
import level.Level;

public class WorldScreen extends GameScreen {
    private final Level currentLevel;
    private final Box2DDebugRenderer debugRenderer;

    private boolean moveLeft;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveDown;

    public WorldScreen() {
        super();
        setInputProcessor(new WorldScreenInputProcessor(this));
        debugRenderer = new Box2DDebugRenderer();
        currentLevel = new Level(5,5);
        this.moveLeft = false;
        this.moveRight = false;
        this.moveUp = false;
        this.moveDown = false;
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
        if (moveLeft) vecteurDirection.x = -1;
        if (moveRight) vecteurDirection.x = 1;
        if (moveUp) vecteurDirection.y = 1;
        if (moveDown) vecteurDirection.y = -1;

        currentLevel.getPlayer().move(vecteurDirection);
    }

    public void setMoveLeft(boolean state) {
        if (moveRight && state) moveRight = false;
        this.moveLeft = state;
    }

    public void setMoveRight(boolean state) {
        if (moveLeft && state) moveLeft = false;
        this.moveRight = state;
    }

    public void setMoveUp(boolean state) {
        if (moveDown && state) moveDown = false;
        this.moveUp = state;
    }

    public void setMoveDown(boolean state) {
        if (moveUp && state) moveUp = false;
        this.moveDown = state;
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
