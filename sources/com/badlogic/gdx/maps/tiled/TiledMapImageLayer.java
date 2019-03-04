package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;

public class TiledMapImageLayer extends MapLayer {
    private TextureRegion region;
    /* renamed from: x */
    private int f88x;
    /* renamed from: y */
    private int f89y;

    public TiledMapImageLayer(TextureRegion region, int x, int y) {
        this.region = region;
        this.f88x = x;
        this.f89y = y;
    }

    public int getX() {
        return this.f88x;
    }

    public int getY() {
        return this.f89y;
    }

    public TextureRegion getTextureRegion() {
        return this.region;
    }
}
