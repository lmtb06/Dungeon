package com.dungeondevs.factories.Level;

import com.artemis.World;
import com.dungeondevs.configs.Level.RoomConfig;
import com.dungeondevs.configs.Level.SpawnPointConfig;
import com.dungeondevs.factories.EntityFactory;

public class RoomFactory implements EntityFactory {
    protected World artemisWorld;
    protected com.badlogic.gdx.physics.box2d.World box2DWorld;
    protected RoomConfig roomConfig;
    public RoomFactory(World artemisWorld, com.badlogic.gdx.physics.box2d.World box2DWorld) {
        this.artemisWorld = artemisWorld;
        this.box2DWorld = box2DWorld;
    }

    public final void setConfig(RoomConfig config){
        this.roomConfig = config;
    }


    @Override
    public void createEntity() {

    }
}
