package com.ingokodba.com.Handlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.ingokodba.com.States.Play;

public class MyContactListener implements ContactListener {
    private Array<Body> bodiesToRemove = new Array();
    private int numFootContacts = 1;
    Play pupika;

    public MyContactListener(Play pa) {
        this.pupika = pa;
    }

    public void beginContact(Contact c) {
        Fixture fa = c.getFixtureA();
        Fixture fb = c.getFixtureB();
        if (fb != null && fa != null) {
            if (fa.getUserData() != null && fa.getUserData().equals("oblak")) {
                crash();
            }
            if (fb.getUserData() != null && fb.getUserData().equals("oblak")) {
                crash();
            }
            if (fb.getUserData() != null && fb.getUserData().equals("crystal")) {
                this.bodiesToRemove.add(fb.getBody());
            }
            if (fa.getUserData() != null && fa.getUserData().equals("crystal")) {
                this.bodiesToRemove.add(fa.getBody());
            }
        }
    }

    private void crash() {
        this.pupika.whatwhat();
    }

    public void endContact(Contact c) {
    }

    public boolean isPlayerOnGround() {
        return this.numFootContacts > 0;
    }

    public Array<Body> getBodiesToRemove() {
        return this.bodiesToRemove;
    }

    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
