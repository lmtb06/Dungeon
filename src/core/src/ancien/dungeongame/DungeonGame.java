package ancien.dungeongame;

import com.artemis.*;
import com.badlogic.gdx.Game;
import ancien.dungeongame.screens.ScreenManager;
import ancien.dungeongame.screens.scenes.PauseScreen;
import ancien.dungeongame.screens.scenes.WorldScreen;
import com.badlogic.gdx.Gdx;
import com.dungeondevs.components.AttackComponent;
import com.dungeondevs.components.InputComponent;
import com.dungeondevs.systems.AttackSystem;
import com.dungeondevs.systems.InputSystem;

public class DungeonGame extends Game {

    private ScreenManager screenManager;
    private World world;

    @Override
    public void create() {
        //instanciation du screenmanager et des screen qu'il aura à gérer. Tous les screens ne sont pas encore prêts
        this.screenManager = new ScreenManager(this);
        WorldScreen ws = new WorldScreen(this.screenManager);
        PauseScreen ps = new PauseScreen(this.screenManager, ws);
        this.screenManager.setWorldScreen(ws);
        this.screenManager.setPauseScreen(ps);

        // 1. Register any plugins, setup the world.
        WorldConfiguration setup = new WorldConfigurationBuilder()
                .with(new InputSystem())
                .with(new AttackSystem())
                .build();

        // 2. Create the world.
        world = new World(setup);

        Archetype playerArchetype =
                new ArchetypeBuilder()
                        .add(InputComponent.class)
                        .add(AttackComponent.class)
                        .build(world);

        // 3. Create entity. You can do it here or inside systems.
        Entity player = world.createEntity(playerArchetype);

        // 4. Run the world. HelloWorldSystem should print the hello world message.
        world.process();

        //on met le worldscreen par défaut pour le moment, on changera pour un écran de démarrage pour le moment
        this.screenManager.changerScreen(ws);
    }

    @Override
    public void render() {
        world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
        super.render();
    }

    @Override
    public void dispose() {

    }
}
