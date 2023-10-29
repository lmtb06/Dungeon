package ancien.dungeongame.systems;

import ancien.dungeongame.Elements.Monster;
import ancien.dungeongame.tiled_elements.Level;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class GlobalContactListener implements ContactListener {

    private Level level;

    public GlobalContactListener(Level level) {
        this.level = level;
    }

    @Override
    public void beginContact(Contact contact) {
        Monster m = (Monster) level.getEntityByBody(contact.getFixtureB().getBody());
        if(m != null) System.out.println("Touché mob");
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
