package com.dungeondevs.factories.Level;

import com.artemis.World;
import com.dungeondevs.configs.Level.DoorConfig;
import com.dungeondevs.configs.Level.RoomConfig;
import com.dungeondevs.factories.EntityFactory;

public class DoorFactory implements EntityFactory {
    protected World artemisWorld;
    protected com.badlogic.gdx.physics.box2d.World box2DWorld;
    protected DoorConfig doorConfig;
    public DoorFactory(World artemisWorld, com.badlogic.gdx.physics.box2d.World box2DWorld) {
        this.artemisWorld = artemisWorld;
        this.box2DWorld = box2DWorld;
    }

    public final void setConfig(DoorConfig config){
        this.doorConfig = config;
    }


    @Override
    public void createEntity() {

    }
}
