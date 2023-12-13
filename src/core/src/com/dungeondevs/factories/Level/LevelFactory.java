package com.dungeondevs.factories.Level;

import com.artemis.World;
import com.dungeondevs.configs.Level.LevelConfig;
import com.dungeondevs.configs.Level.RoomConfig;
import com.dungeondevs.factories.EntityFactory;

public class LevelFactory implements EntityFactory {
    protected World artemisWorld;
    protected com.badlogic.gdx.physics.box2d.World box2DWorld;
    protected LevelConfig levelConfig;
    public LevelFactory(World artemisWorld, com.badlogic.gdx.physics.box2d.World box2DWorld) {
        this.artemisWorld = artemisWorld;
        this.box2DWorld = box2DWorld;
    }

    public final void setConfig(LevelConfig config){
        this.levelConfig = config;
    }


    @Override
    public void createEntity() {

    }
}
