package com.dungeondevs.factories.spawnable;

import com.artemis.World;
import com.dungeondevs.configs.spawnable.Items.PowerUpConfig;
import com.dungeondevs.configs.spawnable.MonsterConfig;
import com.dungeondevs.factories.EntityFactory;

public class PowerUpFactory implements EntityFactory {
    protected World artemisWorld;
    protected com.badlogic.gdx.physics.box2d.World box2DWorld;
    protected PowerUpConfig powerUpConfig;
    public PowerUpFactory(World artemisWorld, com.badlogic.gdx.physics.box2d.World box2DWorld) {
        this.artemisWorld = artemisWorld;
        this.box2DWorld = box2DWorld;
    }

    public final void setConfig(PowerUpConfig config){
        this.powerUpConfig = config;
    }

    @Override
    public void createEntity() {

    }
}
