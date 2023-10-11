package level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeondevs.dungeongame.Monster;
import com.dungeondevs.dungeongame.Player;
import sun.tools.jconsole.JConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Level {
    private final World world;
    private final Viewport viewport;
    private Player player;
    private List<Room> rooms;

    private Room roomActuel;

    private float worldWidth;
    private float worldHeight;
    private OrthographicCamera cam;
    private List<Monster> monsters;



    public Level(float viewportWidthInMeters, float viewportHeightInMeters) {

        //création de salle temporaire
        roomActuel = new Room("maps/test2.tmx");

        cam = new OrthographicCamera(25,25);

        cam.update();

        this.world = new World(new Vector2(0,0), true);
        worldWidth = 20;
        worldHeight = 20;
        this.world.setContactListener(new WorldContactListener());

        this.viewport = new FitViewport(viewportWidthInMeters, viewportHeightInMeters, cam);

        this.rooms = new ArrayList<Room>(1);
        loadPlayerCharacter();
        loadRoom();
        viewport.getCamera().position.set(player.getPosition(),0);
    }

    private void loadPlayerCharacter(){
        // Create a box body
        BodyDef playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBodyDef.position.set(3,3);
        playerBodyDef.fixedRotation = true;
        Body playerBody = world.createBody(playerBodyDef);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(.6f, .6f);

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = boxShape;
        boxFixtureDef.density = 1;

        playerBody.createFixture(boxFixtureDef);
        boxShape.dispose();

        Texture walkSheet = new Texture(Gdx.files.internal("characterAndTileset/player_idle.png"));
        int spritesheetHeight = 1;
        int spritesheetWidth = 2;
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / spritesheetWidth,
                walkSheet.getHeight() / spritesheetHeight);

        TextureRegion[] walkFrames = new TextureRegion[spritesheetWidth * spritesheetHeight];
        int index = 0;
        for (int i = 0; i < spritesheetHeight; i++) {
            for (int j = 0; j < spritesheetWidth; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        Animation<TextureRegion> walkAnimation = new Animation<TextureRegion>(0.5f, walkFrames);

        this.player = new Player(playerBody, walkAnimation, this);
    }

    private void loadRoom() {
        float boundaryThickness = 0.5f;  // Set as desired

        TiledMapTileLayer layerCollision = this.roomActuel.getMapLayer(1);

        System.out.println(layerCollision.getWidth());
        System.out.println(layerCollision.getHeight());

        MapObjects so = this.roomActuel.getMap().getLayers().get(2).getObjects();


        float facteur = 1.25f;

        //mise en place des bordure de collisions
        for (int i = 0; i < layerCollision.getWidth(); i++) {
            for (int j = 0; j < layerCollision.getHeight(); j++) {
                if (layerCollision.getCell(i,j) != null){
                    createBoundary(i*facteur + 0.59f, j*facteur + 0.59f, facteur,facteur);
                }
            }
        }

        this.monsters = new ArrayList<>();
        //mise en place des monstres et objets
        for (int i = 0; i < so.getCount(); i++) {
            System.out.println("siu:" +Float.parseFloat(so.get(i).getProperties().get("x spawn").toString()));

            switch (so.get(i).getProperties().get("categorie").toString()){
                case "monstre":
                    Monster m = new Monster(Integer.parseInt(so.get(i).getProperties().get("health").toString()),
                            Float.parseFloat(so.get(i).getProperties().get("speed").toString()),
                            Integer.parseInt(so.get(i).getProperties().get("damages").toString()),
                            new Vector2(Float.parseFloat(so.get(i).getProperties().get("x spawn").toString())*facteur,
                                    Float.parseFloat(so.get(i).getProperties().get("y spawn").toString())*facteur
                            ),
                            new Vector2(0.6f,0.6f),
                            this.world

                    );
                    break;
                case "powerUps":
                    PowerUp pu = new PowerUp(
                            new Vector2(Float.parseFloat(so.get(i).getProperties().get("x spawn").toString())*facteur,
                                    Float.parseFloat(so.get(i).getProperties().get("y spawn").toString())*facteur
                            ),
                            new Vector2(0.6f,0.6f),
                            this.world,
                            this
                    );
                    world.setContactListener(pu);
                    break;
            }



        }
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
        player.update(world);

        // Mise à jour de la position de la caméra
        Camera camera = viewport.getCamera();
        camera.position.set(playerPos, 0);
        camera.update();
    }

    public World getWorld() {
        return world;
    }

    public Entity getEntityByBody (Body b) {
        Entity res = null;
        for (Monster m: monsters) {
            if(m.getBody() == b)
                res = m;
        }
        return res;
    }

    public boolean isAnAttackBody (Body b) {
        return  b == player.getAttackBody();
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

    public Room getRoomActuel() {
        return roomActuel;
    }

    public void dispose() {
        world.dispose();
    }
}
