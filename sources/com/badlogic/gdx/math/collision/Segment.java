package com.badlogic.gdx.math.collision;

import com.badlogic.gdx.math.Vector3;
import java.io.Serializable;

public class Segment implements Serializable {
    private static final long serialVersionUID = 2739667069736519602L;
    /* renamed from: a */
    public final Vector3 f70a = new Vector3();
    /* renamed from: b */
    public final Vector3 f71b = new Vector3();

    public Segment(Vector3 a, Vector3 b) {
        this.f70a.set(a);
        this.f71b.set(b);
    }

    public Segment(float aX, float aY, float aZ, float bX, float bY, float bZ) {
        this.f70a.set(aX, aY, aZ);
        this.f71b.set(bX, bY, bZ);
    }

    public float len() {
        return this.f70a.dst(this.f71b);
    }

    public float len2() {
        return this.f70a.dst2(this.f71b);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Segment s = (Segment) o;
        if (this.f70a.equals(s.f70a) && this.f71b.equals(s.f71b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.f70a.hashCode() + 71) * 71) + this.f71b.hashCode();
    }
}
