package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;

public class TextureDescriptor<T extends GLTexture> implements Comparable<TextureDescriptor<T>> {
    public TextureFilter magFilter;
    public TextureFilter minFilter;
    public T texture;
    public TextureWrap uWrap;
    public TextureWrap vWrap;

    public TextureDescriptor(T texture, TextureFilter minFilter, TextureFilter magFilter, TextureWrap uWrap, TextureWrap vWrap) {
        this.texture = null;
        set(texture, minFilter, magFilter, uWrap, vWrap);
    }

    public TextureDescriptor(T texture) {
        this(texture, null, null, null, null);
    }

    public TextureDescriptor() {
        this.texture = null;
    }

    public void set(T texture, TextureFilter minFilter, TextureFilter magFilter, TextureWrap uWrap, TextureWrap vWrap) {
        this.texture = texture;
        this.minFilter = minFilter;
        this.magFilter = magFilter;
        this.uWrap = uWrap;
        this.vWrap = vWrap;
    }

    public <V extends T> void set(TextureDescriptor<V> other) {
        this.texture = other.texture;
        this.minFilter = other.minFilter;
        this.magFilter = other.magFilter;
        this.uWrap = other.uWrap;
        this.vWrap = other.vWrap;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextureDescriptor)) {
            return false;
        }
        TextureDescriptor<?> other = (TextureDescriptor) obj;
        if (!(other.texture == this.texture && other.minFilter == this.minFilter && other.magFilter == this.magFilter && other.uWrap == this.uWrap && other.vWrap == this.vWrap)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        long result = (811 * ((811 * ((811 * ((811 * ((811 * ((long) this.texture.glTarget)) + ((long) this.texture.getTextureObjectHandle()))) + ((long) this.minFilter.getGLEnum()))) + ((long) this.magFilter.getGLEnum()))) + ((long) this.uWrap.getGLEnum()))) + ((long) this.vWrap.getGLEnum());
        return (int) ((result >> 32) ^ result);
    }

    public int compareTo(TextureDescriptor<T> o) {
        if (o == this) {
            return 0;
        }
        if (this.texture.glTarget != o.texture.glTarget) {
            return this.texture.glTarget - o.texture.glTarget;
        }
        if (this.texture.getTextureObjectHandle() != o.texture.getTextureObjectHandle()) {
            return this.texture.getTextureObjectHandle() - o.texture.getTextureObjectHandle();
        }
        if (this.minFilter != o.minFilter) {
            return this.minFilter.getGLEnum() - o.minFilter.getGLEnum();
        }
        if (this.magFilter != o.magFilter) {
            return this.magFilter.getGLEnum() - o.magFilter.getGLEnum();
        }
        if (this.uWrap != o.uWrap) {
            return this.uWrap.getGLEnum() - o.uWrap.getGLEnum();
        }
        if (this.vWrap != o.vWrap) {
            return this.vWrap.getGLEnum() - o.vWrap.getGLEnum();
        }
        return 0;
    }
}
