package com.ingokodba.com.Objekti;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.ingokodba.com.PilotGame;

public class Crystal extends B2DSprites {
    Body badi;
    TextureRegion[] sprites = TextureRegion.split(this.tex, 16, 16)[0];
    Texture tex = PilotGame.res.getTexture("crystal");

    public Crystal(Body body) {
        super(body);
        this.badi = body;
        setAnimation(this.sprites, 0.083333336f);
    }
}
