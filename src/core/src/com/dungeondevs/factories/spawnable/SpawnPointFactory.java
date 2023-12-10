package com.dungeondevs.factories.spawnable;

import com.artemis.World;
import com.dungeondevs.configs.Level.PlayerConfig;
import com.dungeondevs.configs.Level.SpawnPointConfig;
import com.dungeondevs.factories.EntityFactory;

public class SpawnPointFactory implements EntityFactory {
    protected World artemisWorld;
    protected com.badlogic.gdx.physics.box2d.World box2DWorld;
    protected SpawnPointConfig spawnPointConfig;
    public SpawnPointFactory(World artemisWorld, com.badlogic.gdx.physics.box2d.World box2DWorld) {
        this.artemisWorld = artemisWorld;
        this.box2DWorld = box2DWorld;
    }

    public final void setConfig(SpawnPointConfig config){
        this.spawnPointConfig = config;
    }


    @Override
    public void createEntity() {

    }


}
