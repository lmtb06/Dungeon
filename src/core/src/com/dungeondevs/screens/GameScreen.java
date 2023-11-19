package com.dungeondevs.screens;

import com.artemis.World;
import com.artemis.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeondevs.DungeonGame;
import com.dungeondevs.components.*;
import com.dungeondevs.components.Level.SalleAssocieeComponent;
import com.dungeondevs.components.rendering.AnimationListComponent;
import com.dungeondevs.systems.*;
import com.dungeondevs.systems.Map.MapsLoaderSystem;
import com.dungeondevs.systems.Map.RoomIntializerSystem;
import com.dungeondevs.systems.rendering.EntityRenderSystem;
import com.dungeondevs.systems.rendering.WorldRenderSystem;
import com.dungeondevs.utils.AnimationData;
import com.dungeondevs.utils.Constants;
import com.dungeondevs.utils.FixtureUserData;
import com.dungeondevs.utils.GameArchetypes;

public class GameScreen implements Screen, DungeonGameScreen {
    private final DungeonGame game;
    private final Viewport worldViewport;
    private World artemisWorld;
    private com.badlogic.gdx.physics.box2d.World box2dWorld;
    private final Box2DDebugRenderer debugRenderer;
    private HudSystem hudSystem;

    public GameScreen(DungeonGame game) {
        this.game = game;
        this.worldViewport = new ExtendViewport(10, 10);

        box2dWorld = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();

        // Get the set foreground frame rate
        float tempsParFrame = 1f / Constants.FOREGROUND_FRAME_RATE;

        // Viewport FitViewport
        worldViewport.getCamera().position.set(0,0,0);
        worldViewport.getCamera().update();

        // Monde box2d
        //Create a default box 1meter tall and 0.3 meter large and a mass of 10 kg for the box2d world
        BodyDef playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBodyDef.position.set(2f, 2f);
        playerBodyDef.fixedRotation = true;
        Body playerBody = box2dWorld.createBody(playerBodyDef);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(0.2f, 0.2f);

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = boxShape;
        boxFixtureDef.density = 1;

        Fixture fixture = playerBody.createFixture(boxFixtureDef);

        boxShape.dispose();

        // Monde Artemis
        WorldConfiguration setup = new WorldConfigurationBuilder()
                .with(new MapsLoaderSystem())
                .with(new RoomIntializerSystem(box2dWorld))
                .with(new PhysicsSystem(box2dWorld, tempsParFrame))
                .with(new InputSystem())
                .with(new StateManagementSystem())
                .with(new MovementSystem())
                .with(new HealthSystem(box2dWorld))
                .with(new InvincibilitySystem())
                .with(new AttackSystem(box2dWorld))
                .with(new AttackEntitySystem(box2dWorld))
                .with(new CollisionSystem(box2dWorld))
                .with(new GameOverSystem(game))
                .with(new PowerUpSystem(box2dWorld))
                .with(new TrapExtinctionSystem(box2dWorld))
                .with(new TeleportationSystem())
                .with(new HudSystem())
                .with(new WorldRenderSystem(worldViewport))
                .with(new EntityRenderSystem(worldViewport))
                .build();

        artemisWorld = new World(setup);
        this.hudSystem = artemisWorld.getSystem(HudSystem.class);

        Archetype playerArchetype = GameArchetypes.PLAYER_CHARACTER_ARCHETYPE
                        .build(artemisWorld);

        Entity player = artemisWorld.createEntity(playerArchetype);
        fixture.setUserData(new FixtureUserData(FixtureUserData.EntityTypes.Player, player));
        player.getComponent(EntityStateComponent.class).state = EntityState.IDLE;
        player.getComponent(PhysicsComponent.class).body = playerBody;
        player.getComponent(InputComponent.class).left = false;
        player.getComponent(InputComponent.class).right = false;
        player.getComponent(InputComponent.class).up = false;
        player.getComponent(InputComponent.class).down = false;
        player.getComponent(MovementComponent.class).maxSpeedInMeterPerSecond = Constants.PLAYER_CHAR_MAX_VELOCITY;
        player.getComponent(MovementComponent.class).decelerationTimeInSeconds = Constants.PLAYER_CHAR_DECELERATION_TIME;
        player.getComponent(SalleAssocieeComponent.class).idMap = 0;
        player.getComponent(AnimationListComponent.class).addAnimationData(new AnimationData(1, 2, "characterAndTileset/player_idle.png", 0.5f));
        player.getComponent(AnimationListComponent.class).addAnimationData(new AnimationData(1, 4, "characterAndTileset/player_walk.png", 0.5f));
        player.getComponent(AnimationListComponent.class).setCurrentAnimation(1);

        Archetype mapArchetype = GameArchetypes.MAP_ARCHETYPE
                .build(artemisWorld);

        Entity map1 = artemisWorld.createEntity(mapArchetype);
        Entity map2 = artemisWorld.createEntity(mapArchetype);
        //artemisWorld.getSystem(MapsLoaderSystem.class).process();

        //artemisWorld.getSystem(ChangeurDeSalleSystem.class).setJoueur(player);
        artemisWorld.getSystem(RoomIntializerSystem.class).setJoueur(player);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Wipe the screen clear
        ScreenUtils.clear(0.145f, 0.075f, 0.102f, 1);

        // Mise à jour de la caméra
        Camera camera = worldViewport.getCamera();
        camera.update();

        // Mise à jour du monde Artemis
        artemisWorld.setDelta(delta);
        artemisWorld.process();

        // rendu debug du monde box2d
        //debugRenderer.render(box2dWorld, camera.combined);

    }

    @Override
    public void resize(int width, int height) {
        worldViewport.update(width, height);
        hudSystem.resize(width, height);
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

    @Override
    public void reinitialize() {
        // 1. Réinitialiser le monde Box2D
        box2dWorld.dispose();
        box2dWorld = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);

        // Vous devrez recréer les corps et les fixations ici.

        // 2. Réinitialiser le monde Artemis
        artemisWorld.dispose();
        // Recrée la configuration du monde Artemis
        // Get the set foreground frame rate
        float tempsParFrame = 1f / Constants.FOREGROUND_FRAME_RATE;

        // Viewport FitViewport
        worldViewport.getCamera().position.set(0,0,0);
        worldViewport.getCamera().update();

        // Monde box2d
        //Create a default box 1meter tall and 0.3 meter large and a mass of 10 kg for the box2d world
        BodyDef playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBodyDef.position.set(2, 2);
        playerBodyDef.fixedRotation = true;
        Body playerBody = box2dWorld.createBody(playerBodyDef);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(0.2f, 0.2f);

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = boxShape;
        boxFixtureDef.density = 1;

        Fixture fixture = playerBody.createFixture(boxFixtureDef);

        boxShape.dispose();

        // Monde Artemis
        WorldConfiguration setup = new WorldConfigurationBuilder()
                .with(new MapsLoaderSystem())
                .with(new InputSystem())
                .with(new MovementSystem())
                .with(new PhysicsSystem(box2dWorld, tempsParFrame))
                .with(new StateManagementSystem())
                .with(new CollisionSystem(box2dWorld))
                .with(new AttackSystem(box2dWorld))
                .with(new AttackEntitySystem(box2dWorld))
                .with(new RoomIntializerSystem(box2dWorld))
                .with(new HealthSystem(box2dWorld))
                .with(new HudSystem())
                .with(new GameOverSystem(game))
                .with(new PowerUpSystem(box2dWorld))
                .with(new TrapExtinctionSystem(box2dWorld))
                .with(new TeleportationSystem())
                .with(new InvincibilitySystem())
                .with(new WorldRenderSystem(worldViewport))
                .with(new EntityRenderSystem(worldViewport))
                .build();

        artemisWorld = new World(setup);
        this.hudSystem = artemisWorld.getSystem(HudSystem.class);

        Archetype playerArchetype = GameArchetypes.PLAYER_CHARACTER_ARCHETYPE
                .build(artemisWorld);

        Entity player = artemisWorld.createEntity(playerArchetype);
        fixture.setUserData(new FixtureUserData(FixtureUserData.EntityTypes.Player, player));
        player.getComponent(EntityStateComponent.class).state = EntityState.IDLE;
        player.getComponent(PhysicsComponent.class).body = playerBody;
        player.getComponent(InputComponent.class).left = false;
        player.getComponent(InputComponent.class).right = false;
        player.getComponent(InputComponent.class).up = false;
        player.getComponent(InputComponent.class).down = false;
        player.getComponent(MovementComponent.class).maxSpeedInMeterPerSecond = Constants.PLAYER_CHAR_MAX_VELOCITY;
        player.getComponent(MovementComponent.class).decelerationTimeInSeconds = Constants.PLAYER_CHAR_DECELERATION_TIME;
        player.getComponent(SalleAssocieeComponent.class).idMap = 0;
        player.getComponent(AnimationListComponent.class).addAnimationData(new AnimationData(1, 2, "characterAndTileset/player_idle.png", 0.5f));
        player.getComponent(AnimationListComponent.class).addAnimationData(new AnimationData(1, 4, "characterAndTileset/player_walk.png", 0.5f));

        Archetype mapArchetype = GameArchetypes.MAP_ARCHETYPE
                .build(artemisWorld);

        Entity map1 = artemisWorld.createEntity(mapArchetype);
        Entity map2 = artemisWorld.createEntity(mapArchetype);
        //artemisWorld.getSystem(MapsLoaderSystem.class).process();

        //artemisWorld.getSystem(ChangeurDeSalleSystem.class).setJoueur(player);
        artemisWorld.getSystem(RoomIntializerSystem.class).setJoueur(player);
    }
}
