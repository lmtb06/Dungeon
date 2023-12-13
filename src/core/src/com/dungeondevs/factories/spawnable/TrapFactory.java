package com.dungeondevs.factories.spawnable;

import com.artemis.World;
import com.dungeondevs.configs.spawnable.MonsterConfig;
import com.dungeondevs.factories.EntityFactory;

public class TrapFactory implements EntityFactory {
    protected World artemisWorld;
    protected com.badlogic.gdx.physics.box2d.World box2DWorld;
    protected TrapFactory trapFactory;
    public TrapFactory(World artemisWorld, com.badlogic.gdx.physics.box2d.World box2DWorld) {
        this.artemisWorld = artemisWorld;
        this.box2DWorld = box2DWorld;
    }

    public final void setConfig(TrapFactory config){
        this.trapFactory = config;
    }

    @Override
    public void createEntity() {

    }
}
