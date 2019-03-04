package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import java.util.Iterator;

public final class ParticleSystem implements RenderableProvider {
    private static ParticleSystem instance;
    private Array<ParticleBatch<?>> batches = new Array();
    private Array<ParticleEffect> effects = new Array();

    public static ParticleSystem get() {
        if (instance == null) {
            instance = new ParticleSystem();
        }
        return instance;
    }

    private ParticleSystem() {
    }

    public void add(ParticleBatch<?> batch) {
        this.batches.add(batch);
    }

    public void add(ParticleEffect effect) {
        this.effects.add(effect);
    }

    public void remove(ParticleEffect effect) {
        this.effects.removeValue(effect, true);
    }

    public void removeAll() {
        this.effects.clear();
    }

    public void update() {
        Iterator i$ = this.effects.iterator();
        while (i$.hasNext()) {
            ((ParticleEffect) i$.next()).update();
        }
    }

    public void updateAndDraw() {
        Iterator i$ = this.effects.iterator();
        while (i$.hasNext()) {
            ParticleEffect effect = (ParticleEffect) i$.next();
            effect.update();
            effect.draw();
        }
    }

    public void begin() {
        Iterator i$ = this.batches.iterator();
        while (i$.hasNext()) {
            ((ParticleBatch) i$.next()).begin();
        }
    }

    public void draw() {
        Iterator i$ = this.effects.iterator();
        while (i$.hasNext()) {
            ((ParticleEffect) i$.next()).draw();
        }
    }

    public void end() {
        Iterator i$ = this.batches.iterator();
        while (i$.hasNext()) {
            ((ParticleBatch) i$.next()).end();
        }
    }

    public void getRenderables(Array<Renderable> renderables, Pool<Renderable> pool) {
        Iterator i$ = this.batches.iterator();
        while (i$.hasNext()) {
            ((ParticleBatch) i$.next()).getRenderables(renderables, pool);
        }
    }

    public Array<ParticleBatch<?>> getBatches() {
        return this.batches;
    }
}
