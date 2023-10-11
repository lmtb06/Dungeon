package com.dungeondevs.dungeongame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Entity {

    protected int health;
    protected double speed;
    protected Body body;
    protected int damages;

    public Entity (int health, double speed, int damages, Vector2 position, Vector2 size, World world) {
        this.health = health;
        this.speed = speed;
        this.damages = damages;

        BodyDef monsterBodyDef = new BodyDef();
        monsterBodyDef.type = BodyDef.BodyType.DynamicBody;
        monsterBodyDef.position.set(position.x, position.y);
        monsterBodyDef.fixedRotation = true;
        this.body = world.createBody(monsterBodyDef);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(size.x, size.y);

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = boxShape;
        boxFixtureDef.density = 1;

        this.body.createFixture(boxFixtureDef);
        boxShape.dispose();
    }

    /**
     * Récupère la santé actuelle de l'entité.
     * @return le nombre de points de vie de l'entité
     */
    public int getHealth() {
        return health;
    }

    /**
     * Cause des dégâts à l'entité.
     * @param damages la quantité de dégâts à appliquer
     */
    public void damage (int damages) {
        this.health -= damages;
    }

    /**
     * L'entité est-elle le joueur?
     * @return
     */
    public abstract boolean isPlayer();

    /**
     * L'entité est-elle un monstre?
     * @return
     */
    public abstract boolean isMonster();

    /**
     * Déplace l'entité dans une certaine direction.
     * @param x
     * @param y
     */
    public abstract void move(int x, int y);
}
