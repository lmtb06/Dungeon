package com.dungeondevs.factories.spawnable.Monstre;

import com.artemis.World;
import com.dungeondevs.configs.spawnable.MonsterConfig;
import com.dungeondevs.configs.spawnable.WeaponConfig;
import com.dungeondevs.factories.EntityFactory;

public class MonsterFactory implements EntityFactory {
    protected World artemisWorld;
    protected com.badlogic.gdx.physics.box2d.World box2DWorld;
    protected MonsterConfig monsterConfig;
    public MonsterFactory(World artemisWorld, com.badlogic.gdx.physics.box2d.World box2DWorld) {
        this.artemisWorld = artemisWorld;
        this.box2DWorld = box2DWorld;
    }

    public final void setConfig(MonsterConfig config){
        this.monsterConfig = config;
    }

    @Override
    public void createEntity() {

    }
}
