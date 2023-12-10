package com.dungeondevs.factories.Level;

import com.artemis.World;
import com.dungeondevs.configs.Level.RoomConfig;
import com.dungeondevs.configs.Level.StairConfig;
import com.dungeondevs.factories.EntityFactory;

public class StairFactory implements EntityFactory {
    protected World artemisWorld;
    protected com.badlogic.gdx.physics.box2d.World box2DWorld;
    protected StairConfig stairConfig;
    public StairFactory(World artemisWorld, com.badlogic.gdx.physics.box2d.World box2DWorld) {
        this.artemisWorld = artemisWorld;
        this.box2DWorld = box2DWorld;
    }

    public final void setConfig(StairConfig config){
        this.stairConfig = config;
    }

    @Override
    public void createEntity() {

    }

}
