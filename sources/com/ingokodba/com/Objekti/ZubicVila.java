package com.ingokodba.com.Objekti;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.ingokodba.com.PilotGame;
import com.ingokodba.com.States.Play;

public class ZubicVila {
    public static float FUCKING_SPEED = 0.05f;
    public static float MAX_CENTER = 1.5f;
    public static float MAX_DOWN = 0.5f;
    public static float MAX_UP = 2.5f;
    public float acceleration;
    Body badi;
    boolean center;
    boolean crash;
    public float distance;
    boolean down;
    protected float height;
    float lastpos;
    public float pastvelocity;
    int rotation;
    Sprite sprajt;
    TextureRegion[] sprites;
    public float tajming;
    public float timeFromStart;
    boolean up;
    public float velocity;
    protected float width;

    public ZubicVila(Body body) {
        this.badi = body;
        Texture tex = PilotGame.res.getTexture("airplane");
        this.sprajt = new Sprite(tex);
        this.width = (float) tex.getWidth();
        this.height = (float) tex.getHeight();
    }

    public void render(SpriteBatch sb) {
        if (Play.crashed) {
            this.tajming = (float) (((double) this.tajming) + 0.1d);
            this.sprajt.setPosition(this.lastpos - this.tajming, (this.badi.getPosition().f103y * 100.0f) - 80.0f);
        } else {
            this.lastpos = (this.badi.getPosition().f102x * 100.0f) - 130.0f;
            this.sprajt.setPosition(this.lastpos, (this.badi.getPosition().f103y * 100.0f) - 80.0f);
        }
        this.sprajt.setScale(0.35f, 0.35f);
        this.sprajt.setRotation((float) this.rotation);
        sb.begin();
        this.sprajt.draw(sb);
        sb.end();
    }

    public void doCrash() {
        this.crash = true;
        this.down = false;
        this.up = false;
        this.center = false;
    }

    public void goCenter() {
        this.crash = false;
        this.down = false;
        this.up = false;
        this.center = true;
    }

    public void goUp() {
        this.distance = MAX_UP - getY();
        this.up = true;
        this.down = false;
        this.pastvelocity = this.velocity;
        this.center = false;
    }

    public void goDown() {
        this.distance = getY() - MAX_DOWN;
        this.down = true;
        this.up = false;
        this.pastvelocity = this.velocity;
        this.center = false;
    }

    public void goBack() {
        this.distance = getY();
        if (((double) this.distance) > ((double) MAX_CENTER) + 0.3d) {
            this.distance -= MAX_CENTER;
        }
        if (((double) this.distance) < ((double) MAX_CENTER) - 0.3d) {
            this.distance = MAX_CENTER - this.distance;
        }
        this.down = false;
        this.up = false;
        this.pastvelocity = this.velocity;
        this.center = false;
    }

    public void setTiming(float tim) {
        this.tajming = tim;
    }

    public void update(float dt) {
        float badiy = getY();
        float badix = getX();
        if (this.crash) {
            if (badiy > -5.0f) {
                this.badi.setTransform(badix, badiy - FUCKING_SPEED, 0.0f);
                this.rotation = -15;
            } else {
                this.badi.setLinearVelocity(0.0f, 0.0f);
                this.crash = false;
            }
        }
        if (!(this.up || this.down || this.crash)) {
            this.rotation = 0;
        }
        if (this.center) {
            if (badiy < MAX_CENTER - 0.2f) {
                this.badi.setTransform(badix, FUCKING_SPEED + badiy, 0.0f);
                this.rotation = 15;
            } else if (badiy > MAX_CENTER + 0.2f) {
                this.badi.setTransform(badix, badiy - FUCKING_SPEED, 0.0f);
                this.rotation = -15;
            } else {
                this.rotation = 0;
            }
        }
        if (this.up && !this.down) {
            if (badiy < MAX_UP) {
                System.out.print("helloou");
                this.badi.setTransform(badix, FUCKING_SPEED + badiy, 0.0f);
                this.rotation = 15;
            } else {
                this.rotation = 0;
            }
        }
        if (this.down && !this.up) {
            if (badiy > MAX_DOWN) {
                System.out.print("helloou");
                this.badi.setTransform(badix, badiy - FUCKING_SPEED, 0.0f);
                this.rotation = -15;
                return;
            }
            this.rotation = 0;
        }
    }

    public Body getBody() {
        return this.badi;
    }

    private float getY() {
        return this.badi.getPosition().f103y;
    }

    private float getX() {
        return this.badi.getPosition().f102x;
    }

    public void collectCrystals() {
    }
}
