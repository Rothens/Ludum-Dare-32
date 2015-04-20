package hu.afi.ld32.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import hu.afi.ld32.entities.Entity;

/**
 * Created by zsomkovacs on 2015.04.20..
 */
public class CollisionHandler implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Entity e1 = (Entity) contact.getFixtureA().getUserData();
        Entity e2 = (Entity) contact.getFixtureB().getUserData();
        System.out.println(e1.getType());
        System.out.println(e2.getType());
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

        Entity e1 = (Entity) contact.getFixtureA().getUserData();
        Entity e2 = (Entity) contact.getFixtureB().getUserData();
        if(e1 != null)
        System.out.println(e1.getType());
        if(e2 != null)
        System.out.println(e2.getType());
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
