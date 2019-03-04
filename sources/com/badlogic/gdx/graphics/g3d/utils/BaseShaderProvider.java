package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

public abstract class BaseShaderProvider implements ShaderProvider {
    protected Array<Shader> shaders = new Array();

    protected abstract Shader createShader(Renderable renderable);

    public Shader getShader(Renderable renderable) {
        Shader suggestedShader = renderable.shader;
        if (suggestedShader != null && suggestedShader.canRender(renderable)) {
            return suggestedShader;
        }
        Shader shader;
        Iterator i$ = this.shaders.iterator();
        while (i$.hasNext()) {
            shader = (Shader) i$.next();
            if (shader.canRender(renderable)) {
                return shader;
            }
        }
        shader = createShader(renderable);
        shader.init();
        this.shaders.add(shader);
        return shader;
    }

    public void dispose() {
        Iterator i$ = this.shaders.iterator();
        while (i$.hasNext()) {
            ((Shader) i$.next()).dispose();
        }
        this.shaders.clear();
    }
}
