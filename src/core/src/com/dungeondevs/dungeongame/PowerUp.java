package com.dungeondevs.dungeongame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import level.Level;

public class PowerUp extends GameObject{

    public Level niveauPresent;

    public PowerUp(Vector2 position, Vector2 size, World world, Level lvl) {
        super(position, size, world, null, null);
        this.niveauPresent = lvl;
    }

    /**@Override
    public void beginContact(Contact contact) {
        System.out.println(this.niveauPresent.getPlayer().getMeleeDamages());
        this.niveauPresent.getPlayer().setMeleeDamages(this.niveauPresent.getPlayer().getMeleeDamages()+1);
        System.out.println(this.niveauPresent.getPlayer().getMeleeDamages());
        //this.monde.destroyBody(this.body);
    }**/

    /**@Override
    public void endContact(Contact contact) {

    }**/

    /**@Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }**/

    /**@Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }**/
}
