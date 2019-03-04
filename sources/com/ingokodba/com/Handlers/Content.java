package com.ingokodba.com.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;

public class Content {
    private HashMap<String, Texture> textures = new HashMap();

    public void loadTexture(String path, String key) {
        this.textures.put(key, new Texture(Gdx.files.internal(path)));
    }

    public Texture getTexture(String key) {
        return (Texture) this.textures.get(key);
    }

    public void disposeTexture(String key) {
        Texture tex = (Texture) this.textures.get(key);
        if (tex != null) {
            tex.dispose();
        }
    }
}
