package com.dungeondevs.systems;

import ancien.dungeongame.systems.GlobalContactListener;
import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.TimeUtils;
import com.dungeondevs.components.AttackComponent;
import com.dungeondevs.components.InputComponent;

@All({AttackComponent.class, InputComponent.class})
public class AttackSystem extends EntityProcessingSystem {

    private long lastAttack = 0;
    private Body attackBody;

    @Override
    protected void process(Entity e) {
        InputComponent inputComponent = e.getComponent(InputComponent.class);
        long elapsedTime = TimeUtils.timeSinceMillis(lastAttack);
        if(inputComponent.space && elapsedTime > 500f){
            AttackComponent attackComponent = e.getComponent(AttackComponent.class);
            int dmg = attackComponent.getDamages();
            System.out.println("Attacking with " + dmg + " damages");
            lastAttack = TimeUtils.millis();

            /*if(attackBody != null)
                return;

            BodyDef attackBodyDef = new BodyDef();
            attackBodyDef.type = BodyDef.BodyType.StaticBody;
            attackBodyDef.position.set(body.getPosition().x + 1.2f, body.getPosition().y);
            attackBodyDef.fixedRotation = true;
            attackBody = world.createBody(attackBodyDef);

            PolygonShape boxShape = new PolygonShape();
            boxShape.setAsBox(0.5f, 0.5f);

            FixtureDef boxFixtureDef = new FixtureDef();
            boxFixtureDef.shape = boxShape;
            boxFixtureDef.density = 1;

            attackBody.createFixture(boxFixtureDef);
            boxShape.dispose();

            world.setContactListener(new GlobalContactListener(currentLevel));
            */
        }
    }
}
