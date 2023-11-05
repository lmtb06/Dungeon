package com.dungeondevs.screens;

import com.artemis.World;
import com.artemis.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeondevs.DungeonGame;
import com.dungeondevs.components.*;
import com.dungeondevs.systems.*;
import com.dungeondevs.utils.Constants;
import com.dungeondevs.utils.GameArchetypes;

public class GameScreen implements Screen {
    private final DungeonGame game;
    private final Viewport viewport;
    private final World artemisWorld;
    private final com.badlogic.gdx.physics.box2d.World box2dWorld;
    private final Box2DDebugRenderer debugRenderer;

    public GameScreen(DungeonGame game) {
        this.game = game;
        this.viewport = new FitViewport(10, 10);
        box2dWorld = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();

        // Get the set foreground frame rate
        float tempsParFrame = 1f / Constants.FOREGROUND_FRAME_RATE;

        // Viewport FitViewport
        viewport.getCamera().position.set(0,0,0);
        viewport.getCamera().update();

        // Monde box2d
        //Create a default box 1meter tall and 0.3 meter large and a mass of 10 kg for the box2d world
        BodyDef playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBodyDef.position.set(0, 0);
        Body playerBody = box2dWorld.createBody(playerBodyDef);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(0.2f, 0.2f);

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = boxShape;
        boxFixtureDef.density = 1;

        playerBody.createFixture(boxFixtureDef);
        boxShape.dispose();

        // Monde Artemis
        WorldConfiguration setup = new WorldConfigurationBuilder()
                .with(new InputSystem())
                .with(new MovementSystem())
                .with(new PhysicsSystem(box2dWorld, tempsParFrame))
                .with(new StateManagementSystem())
                .with(new CollisionSystem(box2dWorld))
                .with(new AttackSystem(box2dWorld))
                .with(new AttackEntitySystem(box2dWorld))
                .build();

        artemisWorld = new World(setup);

        Archetype playerArchetype = GameArchetypes.PLAYER_CHARACTER_ARCHETYPE
                        .build(artemisWorld);

        Entity player = artemisWorld.createEntity(playerArchetype);
        player.getComponent(EntityStateComponent.class).state = EntityState.IDLE;
        player.getComponent(PhysicsComponent.class).body = playerBody;
        player.getComponent(InputComponent.class).left = false;
        player.getComponent(InputComponent.class).right = false;
        player.getComponent(InputComponent.class).up = false;
        player.getComponent(InputComponent.class).down = false;
        player.getComponent(MovementComponent.class).maxSpeedInMeterPerSecond = Constants.PLAYER_CHAR_MAX_VELOCITY;
        player.getComponent(MovementComponent.class).decelerationTimeInSeconds = Constants.PLAYER_CHAR_DECELERATION_TIME;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Wipe the screen clear
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Mise à jour de la caméra
        Camera camera = viewport.getCamera();
        camera.update();

        // rendu debug du monde box2d
        debugRenderer.render(box2dWorld, camera.combined);

        // Mise à jour du monde Artemis
        artemisWorld.setDelta(delta);
        artemisWorld.process();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        artemisWorld.dispose();
        box2dWorld.dispose();
    }
}
