package com.dungeondevs.factories.spawnable;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.dungeondevs.components.PhysicsComponent;
import com.dungeondevs.components.rendering.SpriteComponent;
import com.dungeondevs.configs.spawnable.WeaponConfig;
import com.dungeondevs.factories.EntityFactory;

public class WeaponFactory implements EntityFactory {

    protected World artemisWorld;
    protected com.badlogic.gdx.physics.box2d.World box2DWorld;
    protected WeaponConfig weaponConfig;

    public WeaponFactory(World artemisWorld, com.badlogic.gdx.physics.box2d.World box2DWorld) {
        this.artemisWorld = artemisWorld;
        this.box2DWorld = box2DWorld;
    }

    public final void setConfig(WeaponConfig config){
        this.weaponConfig = config;
    }


    @Override
    public void createEntity() {
        Entity weaponEntity = artemisWorld.createEntity();
        addComponent(weaponEntity);
        System.out.println(weaponEntity.getComponent(PhysicsComponent.class).body.getPosition());
    }

    public final void addComponent(Entity entity){

        addPhysicsComponent(entity);
        addSpriteComponent(entity);
    }

    protected void addPhysicsComponent(Entity entity){
        Body weaponBody = box2DWorld.createBody(weaponConfig.getBodyDef());

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(1, 1);

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = boxShape;
        boxFixtureDef.density = 1;

        Fixture fixture = weaponBody.createFixture(boxFixtureDef);

        boxShape.dispose();

        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.body = weaponBody;

        entity.edit().add(physicsComponent);
    }
    protected void addSpriteComponent(Entity entity){
        SpriteComponent spriteComponent = new SpriteComponent();
        spriteComponent.setTextureRegion(weaponConfig.getTextureRegion());
        spriteComponent.setPosition(new Vector3(0,-5,0));
        spriteComponent.setRotation(0);
        spriteComponent.setVisible(true);
        entity.edit().add(spriteComponent);
    }
}
