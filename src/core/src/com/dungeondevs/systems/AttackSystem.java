package com.dungeondevs.systems;

import com.artemis.Archetype;
import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.TimeUtils;
import com.dungeondevs.components.*;
import com.dungeondevs.utils.FixtureUserData;
import com.dungeondevs.utils.GameArchetypes;

/**
 * Système permettant aux entités possédant le composant AttackComponent d'attaquer.
 */
@All({AttackComponent.class, InputComponent.class, PhysicsComponent.class})
public class AttackSystem extends EntityProcessingSystem {

    /**
     * Moment auquel a été effectué la dernière attaque. Permet de calculer la prochaine attaque.
     */
    private long lastAttack = 0;

    /**
     * Le monde physique.
     */
    private World box2dWorld;

    /**
     * Constructeur de base qui nécessite le monde physique.
     * @param box2dWorld le monde physique.
     */
    public AttackSystem(World box2dWorld) {
        this.box2dWorld = box2dWorld;
    }

    @Override
    protected void process(Entity e) {
        // On récupère les composants utiles pour la condition suivante.
        InputComponent inputComponent = e.getComponent(InputComponent.class);
        AttackComponent attackComponent = e.getComponent(AttackComponent.class);

        // Durée depuis la dernière attaque
        long elapsedTime = TimeUtils.timeSinceMillis(lastAttack);

        // Si on appuie sur espace et que le temps passé depuis la dernière attaque est supérieur au délai d'attaque
        if(inputComponent.space && elapsedTime > attackComponent.getAttackDelay()){

            PhysicsComponent physicsComponent = e.getComponent(PhysicsComponent.class);
            int dmg = attackComponent.getDamages();
            float offset = .5f;
            lastAttack = TimeUtils.millis();

            // Création du body def
            BodyDef attackBodyDef = new BodyDef();
            attackBodyDef.type = BodyDef.BodyType.StaticBody;
            Vector2 playerPosition = physicsComponent.body.getPosition();
            attackBodyDef.position.set(playerPosition.x + offset, playerPosition.y); // On récupère la position du joueur et on place l'attaque avec un décalage
            Body attackBody = box2dWorld.createBody(attackBodyDef);

            PolygonShape boxShape = new PolygonShape();
            boxShape.setAsBox(0.2f, 0.2f);

            FixtureDef boxFixtureDef = new FixtureDef();
            boxFixtureDef.shape = boxShape;
            boxFixtureDef.density = 1;
            boxFixtureDef.isSensor = true;

            Fixture fixture = attackBody.createFixture(boxFixtureDef);
            boxShape.dispose();

            Archetype attackArchetype = GameArchetypes.ATTACK_ENTITY_ARCHETYPE
                    .build(world);

            Entity attack = world.createEntity(attackArchetype);
            fixture.setUserData(new FixtureUserData(FixtureUserData.EntityTypes.Attack, attack));
            attack.getComponent(PhysicsComponent.class).body = attackBody;
            attack.getComponent(AttackEntityComponent.class).startTime = TimeUtils.millis();
            attack.getComponent(AttackEntityComponent.class).autoDestroyTime = 100;
            attack.getComponent(AttackEntityComponent.class).boundBody = physicsComponent.body;
            attack.getComponent(AttackEntityComponent.class).offset = offset;
        }
    }
}
