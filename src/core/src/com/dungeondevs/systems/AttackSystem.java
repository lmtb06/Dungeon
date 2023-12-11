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

        boolean bomb = inputComponent.bomb;

        // Si on appuie sur espace et que le temps passé depuis la dernière attaque est supérieur au délai d'attaque
        if((inputComponent.space || bomb) && elapsedTime > attackComponent.getAttackDelay()){

            PhysicsComponent physicsComponent = e.getComponent(PhysicsComponent.class);
            float offset = .5f;
            lastAttack = TimeUtils.millis();

            // Création du body def
            BodyDef attackBodyDef = new BodyDef();
            attackBodyDef.type = BodyDef.BodyType.DynamicBody;
            Vector2 playerPosition = physicsComponent.body.getPosition();
            attackBodyDef.position.set(playerPosition.x + offset, playerPosition.y); // On récupère la position du joueur et on place l'attaque avec un décalage
            Body attackBody = box2dWorld.createBody(attackBodyDef);


            PolygonShape boxShape = new PolygonShape();
            float longueurAxeX = 0.1f;
            float longueurAxeY = 0.1f;
            int damageArme = 0;


            if (bomb){
                e.getComponent(AttackComponent.class).setAttackDelay(1000);
                longueurAxeX = 0.1f;
                longueurAxeY = 0.1f;
            }else {
                /** On change la hitbox et les caractéristiques de l'attaque en fonction des armes ici **/
                if (e.getComponent(AttackComponent.class).arme.equals("epee")){
                    damageArme = 4;
                    e.getComponent(AttackComponent.class).setAttackDelay(500);
                    longueurAxeX = 0.2f;
                    longueurAxeY = 0.2f;
                } else if (e.getComponent(AttackComponent.class).arme.equals("couteau")) {
                    damageArme = 2;
                    e.getComponent(AttackComponent.class).setAttackDelay(300);
                    longueurAxeX = 0.1f;
                    longueurAxeY = 0.1f;
                } else if (e.getComponent(AttackComponent.class).arme.equals("lance")) {
                    damageArme = 8;
                    e.getComponent(AttackComponent.class).setAttackDelay(1500);
                    longueurAxeX = 0.3f;
                    longueurAxeY = 0.1f;
                }
            }


            /** on inverse l'axe x et y si l'attaque est orientée vers le haut **/
            if (e.getComponent(DirectionComponent.class).direction.equals("haut") || e.getComponent(DirectionComponent.class).direction.equals("bas")){
                float tmp = longueurAxeX;
                longueurAxeX = longueurAxeY;
                longueurAxeY = tmp;
            }

            //e.getComponent(ContactDamageComponent.class).setDamages(damageArme);

            boxShape.setAsBox(longueurAxeX, longueurAxeY);

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
            if (bomb){
                attack.getComponent(AttackEntityComponent.class).autoDestroyTime = 10000;
                attack.getComponent(AttackEntityComponent.class).projectile = true;
                attack.getComponent(ContactDamageComponent.class).setDamages(2);
                e.getComponent(AttackComponent.class).setAttackDelay(500);
            }else {
                attack.getComponent(AttackEntityComponent.class).autoDestroyTime = 100;
                attack.getComponent(ContactDamageComponent.class).setDamages(damageArme);
            }

            attack.getComponent(AttackEntityComponent.class).boundBody = physicsComponent.body;
            attack.getComponent(AttackEntityComponent.class).offset = offset;

        }
    }
}
