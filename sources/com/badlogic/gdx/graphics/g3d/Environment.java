package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.g3d.environment.BaseLight;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.environment.ShadowMap;
import com.badlogic.gdx.graphics.g3d.environment.SpotLight;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.Iterator;

public class Environment extends Attributes {
    public final Array<DirectionalLight> directionalLights = new Array();
    public final Array<PointLight> pointLights = new Array();
    public ShadowMap shadowMap;
    public final Array<SpotLight> spotLights = new Array();

    public Environment add(BaseLight... lights) {
        for (BaseLight light : lights) {
            add(light);
        }
        return this;
    }

    public Environment add(Array<BaseLight> lights) {
        Iterator i$ = lights.iterator();
        while (i$.hasNext()) {
            add((BaseLight) i$.next());
        }
        return this;
    }

    public Environment add(BaseLight light) {
        if (light instanceof DirectionalLight) {
            this.directionalLights.add((DirectionalLight) light);
        } else if (light instanceof PointLight) {
            this.pointLights.add((PointLight) light);
        } else if (light instanceof SpotLight) {
            this.spotLights.add((SpotLight) light);
        } else {
            throw new GdxRuntimeException("Unknown light type");
        }
        return this;
    }

    public Environment remove(BaseLight... lights) {
        for (BaseLight light : lights) {
            remove(light);
        }
        return this;
    }

    public Environment remove(Array<BaseLight> lights) {
        Iterator i$ = lights.iterator();
        while (i$.hasNext()) {
            remove((BaseLight) i$.next());
        }
        return this;
    }

    public Environment remove(BaseLight light) {
        if (light instanceof DirectionalLight) {
            this.directionalLights.removeValue((DirectionalLight) light, false);
        } else if (light instanceof PointLight) {
            this.pointLights.removeValue((PointLight) light, false);
        } else if (light instanceof SpotLight) {
            this.spotLights.removeValue((SpotLight) light, false);
        } else {
            throw new GdxRuntimeException("Unknown light type");
        }
        return this;
    }

    public void clear() {
        super.clear();
        this.directionalLights.clear();
        this.pointLights.clear();
        this.spotLights.clear();
    }
}
