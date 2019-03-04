package com.ingokodba.com.Objekti;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.ingokodba.com.Handlers.Animation;

public class B2DSprites {
    protected Animation animation = new Animation();
    protected Body body;
    protected float height;
    protected float width;

    public B2DSprites(Body body) {
        this.body = body;
    }

    public void setAnimation(TextureRegion[] reg, float delay) {
        this.animation.setFrames(reg, delay);
        this.width = (float) reg[0].getRegionWidth();
        this.height = (float) reg[0].getRegionHeight();
    }

    public void update(float dt) {
        this.animation.update(dt);
    }

    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(this.animation.getFrame(), (this.body.getPosition().f102x * 100.0f) - (this.width / 2.0f), (this.body.getPosition().f103y * 100.0f) - (this.height / 2.0f));
        sb.end();
    }

    public Body getBody() {
        return this.body;
    }

    public Vector2 getPosition() {
        return this.body.getPosition();
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }
}
