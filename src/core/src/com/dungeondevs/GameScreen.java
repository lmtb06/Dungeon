package com.dungeondevs;

import com.artemis.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeondevs.components.InputComponent;
import com.dungeondevs.components.Maps.LoadMapComponent;
import com.dungeondevs.components.Maps.MapStateComponent;
import com.dungeondevs.systems.ChangeurDeSalleSystem;
import com.dungeondevs.systems.InputSystem;
import com.dungeondevs.systems.MapRendererSystem;
import com.dungeondevs.systems.MapsLoaderSystem;

public class GameScreen implements Screen {
    private World world;

    private Viewport viewport;

    public static final float MIN_DELTA = 1 / 15f;
    @Override
    public void show() {
        // 1. Register any plugins, setup the world.
        WorldConfiguration setup = new WorldConfigurationBuilder()
                .with(new InputSystem())
                .with(new MapsLoaderSystem())
                .with(new MapRendererSystem())
                .with(new ChangeurDeSalleSystem())
                .build();

        // 2. Create the world.
        world = new World(setup);

        Archetype playerArchetype =
                new ArchetypeBuilder()
                        .add(InputComponent.class)
                        .build(world);

        Archetype mapArchetype =
                new ArchetypeBuilder()
                        .add(MapStateComponent.class)
                        .add(LoadMapComponent.class)
                        .build(world);



        // 3. Create entity. You can do it here or inside systems.


        System.out.println(this.viewport);

        Entity player = world.createEntity(playerArchetype);
        Entity map = world.createEntity(mapArchetype);

        // 4. Run the world. HelloWorldSystem should print the hello world message.

        world.process();

        // Informations nécessaires au bon fonctionnement du screen
        this.viewport = world.getSystem(MapRendererSystem.class).getViewport();
    }

    @Override
    public void render(float delta) {
        world.setDelta(delta);
        world.process();

    }

    @Override
    public void resize(int width, int height) {
        if (this.viewport != null){
            this.viewport.update(width, height);
        }
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

    }
}
