package com.dungeondevs.factories.spawnable;

import com.artemis.World;
import com.dungeondevs.configs.spawnable.Items.ItemConfig;
import com.dungeondevs.configs.spawnable.MonsterConfig;
import com.dungeondevs.factories.EntityFactory;

public class ItemFactory implements EntityFactory {
    protected World artemisWorld;
    protected com.badlogic.gdx.physics.box2d.World box2DWorld;
    protected ItemConfig itemConfig;
    public ItemFactory(World artemisWorld, com.badlogic.gdx.physics.box2d.World box2DWorld) {
        this.artemisWorld = artemisWorld;
        this.box2DWorld = box2DWorld;
    }

    public final void setConfig(ItemConfig config){
        this.itemConfig = config;
    }

    @Override
    public void createEntity() {

    }
}
