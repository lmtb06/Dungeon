package com.dungeondevs.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * Component qui contient le body de l'entité.
 * Il est utilisé pour la gestion de la physique.
 */
public class PhysicsComponent extends Component {
    /**
     * Le body de l'entité.
     */
    public Body body; // TODO: Rendre le body privé et ajouter des méthodes pour le manipuler

    /**
     * Constructeur.
     * @param body le body de l'entité
     */
    public PhysicsComponent(Body body) {
        this.body = body;
    }

    /**
     * applique une force au body
     * la force est appliquée au centre du body
     * la différence entre applyForce et applyImpulse est que applyForce applique une force continue
     * @param force la force à appliquer
     */
    public void applyForce(Vector2 force){
        body.applyForceToCenter(force, true);
    }

    /**
     * applique une impulsion au body
     * l'impulsion est appliquée au centre du body
     * la différence entre applyForce et applyImpulse est que applyImpulse applique une force instantanée
     * @param impulse l'impulsion à appliquer
     */
    public void applyImpulse(Vector2 impulse){
        body.applyLinearImpulse(impulse, body.getWorldCenter(), true);
    }

    /**
     * Set la vitesse linéaire du body.
     * La vitesse est appliquée au centre du body.
     * La différence entre setLinearVelocity et applyForce est que setLinearVelocity met la vitesse à la valeur donnée.
     * @param velocity la vitesse linéaire à appliquer
     */
    public void setLinearVelocity(Vector2 velocity){
        body.setLinearVelocity(velocity);
    }

    /**
     * Get la vitesse linéaire du body.
     * @return la vitesse linéaire du body.
     */
    public Vector2 getLinearVelocity(){
        return body.getLinearVelocity();
    }

    /**
     * Set la position du body.
     * @param position la position à appliquer
     */
    public void setPosition(Vector2 position){
        body.setTransform(position, body.getAngle());
    }

    /**
     * Get la position du body.
     * @return la position du body.
     */
    public Vector2 getPosition(){
        return body.getPosition();
    }

    /**
     * Set la rotation du body.
     * La rotation est appliquée au centre du body.
     * @param rotation la rotation à appliquer
     */
    public void setRotation(float rotation){
        body.setTransform(body.getPosition(), rotation);
    }

    /**
     * Get la rotation du body.
     * @return la rotation du body.
     */
    public float getRotation() {
        return body.getAngle();
    }

    /**
     * Destroy le body.
     * Il faut appeler cette méthode avant de supprimer le component.
     */
    public void destroy() {
        body.getWorld().destroyBody(body);
    }
}
