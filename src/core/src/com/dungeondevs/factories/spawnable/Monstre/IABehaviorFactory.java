package com.dungeondevs.factories.spawnable.Monstre;

import com.artemis.World;
import com.dungeondevs.configs.spawnable.MonsterConfig;
import com.dungeondevs.factories.EntityFactory;

public class IABehaviorFactory implements EntityFactory {
    protected World artemisWorld;
    protected com.badlogic.gdx.physics.box2d.World box2DWorld;
    protected IABehaviorFactory iaBehaviorFactory;
    public IABehaviorFactory(World artemisWorld, com.badlogic.gdx.physics.box2d.World box2DWorld) {
        this.artemisWorld = artemisWorld;
        this.box2DWorld = box2DWorld;
    }

    public final void setConfig(IABehaviorFactory config){
        this.iaBehaviorFactory = config;
    }

    @Override
    public void createEntity() {

    }
}
