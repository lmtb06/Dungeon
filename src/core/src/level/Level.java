package level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeondevs.dungeongame.Player;

import java.util.ArrayList;
import java.util.List;


public class Level {
    private final World world;
    private final Viewport viewport;
    private Player player;
    private List<Room> rooms;
    private float worldWidth;
    private float worldHeight;
    private OrthographicCamera cam;


    public Level(float viewportWidthInMeters, float viewportHeightInMeters) {

        cam = new OrthographicCamera(25,25);

        cam.update();

        this.world = new World(new Vector2(0,0), true);
        worldWidth = 20;
        worldHeight = 20;
        this.viewport = new FitViewport(viewportWidthInMeters, viewportHeightInMeters, cam);

        this.rooms = new ArrayList<Room>(1);
        loadPlayerCharacter();
        loadRoom(null);
        viewport.getCamera().position.set(player.getPosition(),0);
    }

    private void loadPlayerCharacter(){
        // Create a box body
        BodyDef playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBodyDef.position.set(worldWidth/2, worldHeight/2);
        Body playerBody = world.createBody(playerBodyDef);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(0.2f, 0.2f);

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = boxShape;
        boxFixtureDef.density = 1;

        playerBody.createFixture(boxFixtureDef);
        boxShape.dispose();

        Texture walkSheet = new Texture(Gdx.files.internal("characterAndTileset/player_walk.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / 4,
                walkSheet.getHeight() / 1);

        TextureRegion[] walkFrames = new TextureRegion[4 * 1];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        Animation<TextureRegion> walkAnimation = new Animation<TextureRegion>(0.05f, walkFrames);

        this.player = new Player(playerBody, walkAnimation);
    }

    private void loadRoom(Room roomToLoad) {
        float boundaryThickness = 0.5f;  // Set as desired

        // Bottom boundary
        createBoundary(worldWidth / 2, 0, worldWidth, boundaryThickness);
        // Top boundary
        createBoundary(worldWidth / 2, worldHeight, worldWidth, boundaryThickness);
        // Left boundary
        createBoundary(0, worldHeight / 2, boundaryThickness, worldHeight);
        // Right boundary
        createBoundary(worldWidth, worldHeight / 2, boundaryThickness, worldHeight);
    }

    private void createBoundary(float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        Body body = world.createBody(bodyDef);
        body.createFixture(shape, 0);

        shape.dispose();
    }

    public void update(float delta) {
        world.step(delta, 6, 2);
        Vector2 playerPos = player.getPosition();

        // Mise à jour de la position de la caméra
        Camera camera = viewport.getCamera();
        camera.position.set(playerPos, 0);
        camera.update();
    }

    public World getWorld() {
        return world;
    }

    public OrthographicCamera getCam () {
        return cam;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public Player getPlayer() {
        return player;
    }

    public void dispose() {
        world.dispose();
    }
}
