package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;

public class FloatFrameBuffer extends FrameBuffer {
    public FloatFrameBuffer(int width, int height, boolean hasDepth) {
        super(null, width, height, hasDepth);
    }

    protected void setupTexture() {
        this.colorTexture = new Texture(new FloatTextureData(this.width, this.height));
        if (Gdx.app.getType() == ApplicationType.Desktop || Gdx.app.getType() == ApplicationType.Applet) {
            this.colorTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        } else {
            this.colorTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        }
        this.colorTexture.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
    }
}
