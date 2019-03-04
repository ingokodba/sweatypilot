package com.badlogic.gdx.math.collision;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import java.io.Serializable;
import java.util.List;

public class BoundingBox implements Serializable {
    private static final long serialVersionUID = -1286036817192127343L;
    private static final Vector3 tmpVector = new Vector3();
    private final Vector3 cnt = new Vector3();
    private final Vector3 dim = new Vector3();
    public final Vector3 max = new Vector3();
    public final Vector3 min = new Vector3();

    public Vector3 getCenter(Vector3 out) {
        return out.set(this.cnt);
    }

    public float getCenterX() {
        return this.cnt.f107x;
    }

    public float getCenterY() {
        return this.cnt.f108y;
    }

    public float getCenterZ() {
        return this.cnt.f109z;
    }

    public Vector3 getCorner000(Vector3 out) {
        return out.set(this.min.f107x, this.min.f108y, this.min.f109z);
    }

    public Vector3 getCorner001(Vector3 out) {
        return out.set(this.min.f107x, this.min.f108y, this.max.f109z);
    }

    public Vector3 getCorner010(Vector3 out) {
        return out.set(this.min.f107x, this.max.f108y, this.min.f109z);
    }

    public Vector3 getCorner011(Vector3 out) {
        return out.set(this.min.f107x, this.max.f108y, this.max.f109z);
    }

    public Vector3 getCorner100(Vector3 out) {
        return out.set(this.max.f107x, this.min.f108y, this.min.f109z);
    }

    public Vector3 getCorner101(Vector3 out) {
        return out.set(this.max.f107x, this.min.f108y, this.max.f109z);
    }

    public Vector3 getCorner110(Vector3 out) {
        return out.set(this.max.f107x, this.max.f108y, this.min.f109z);
    }

    public Vector3 getCorner111(Vector3 out) {
        return out.set(this.max.f107x, this.max.f108y, this.max.f109z);
    }

    public Vector3 getDimensions(Vector3 out) {
        return out.set(this.dim);
    }

    public float getWidth() {
        return this.dim.f107x;
    }

    public float getHeight() {
        return this.dim.f108y;
    }

    public float getDepth() {
        return this.dim.f109z;
    }

    public Vector3 getMin(Vector3 out) {
        return out.set(this.min);
    }

    public Vector3 getMax(Vector3 out) {
        return out.set(this.max);
    }

    public BoundingBox() {
        clr();
    }

    public BoundingBox(BoundingBox bounds) {
        set(bounds);
    }

    public BoundingBox(Vector3 minimum, Vector3 maximum) {
        set(minimum, maximum);
    }

    public BoundingBox set(BoundingBox bounds) {
        return set(bounds.min, bounds.max);
    }

    public BoundingBox set(Vector3 minimum, Vector3 maximum) {
        this.min.set(minimum.f107x < maximum.f107x ? minimum.f107x : maximum.f107x, minimum.f108y < maximum.f108y ? minimum.f108y : maximum.f108y, minimum.f109z < maximum.f109z ? minimum.f109z : maximum.f109z);
        this.max.set(minimum.f107x > maximum.f107x ? minimum.f107x : maximum.f107x, minimum.f108y > maximum.f108y ? minimum.f108y : maximum.f108y, minimum.f109z > maximum.f109z ? minimum.f109z : maximum.f109z);
        this.cnt.set(this.min).add(this.max).scl(0.5f);
        this.dim.set(this.max).sub(this.min);
        return this;
    }

    public BoundingBox set(Vector3[] points) {
        inf();
        for (Vector3 l_point : points) {
            ext(l_point);
        }
        return this;
    }

    public BoundingBox set(List<Vector3> points) {
        inf();
        for (Vector3 l_point : points) {
            ext(l_point);
        }
        return this;
    }

    public BoundingBox inf() {
        this.min.set(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
        this.max.set(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
        this.cnt.set(0.0f, 0.0f, 0.0f);
        this.dim.set(0.0f, 0.0f, 0.0f);
        return this;
    }

    public BoundingBox ext(Vector3 point) {
        return set(this.min.set(min(this.min.f107x, point.f107x), min(this.min.f108y, point.f108y), min(this.min.f109z, point.f109z)), this.max.set(Math.max(this.max.f107x, point.f107x), Math.max(this.max.f108y, point.f108y), Math.max(this.max.f109z, point.f109z)));
    }

    public BoundingBox clr() {
        return set(this.min.set(0.0f, 0.0f, 0.0f), this.max.set(0.0f, 0.0f, 0.0f));
    }

    public boolean isValid() {
        return this.min.f107x < this.max.f107x && this.min.f108y < this.max.f108y && this.min.f109z < this.max.f109z;
    }

    public BoundingBox ext(BoundingBox a_bounds) {
        return set(this.min.set(min(this.min.f107x, a_bounds.min.f107x), min(this.min.f108y, a_bounds.min.f108y), min(this.min.f109z, a_bounds.min.f109z)), this.max.set(max(this.max.f107x, a_bounds.max.f107x), max(this.max.f108y, a_bounds.max.f108y), max(this.max.f109z, a_bounds.max.f109z)));
    }

    public BoundingBox ext(BoundingBox bounds, Matrix4 transform) {
        ext(tmpVector.set(bounds.min.f107x, bounds.min.f108y, bounds.min.f109z).mul(transform));
        ext(tmpVector.set(bounds.min.f107x, bounds.min.f108y, bounds.max.f109z).mul(transform));
        ext(tmpVector.set(bounds.min.f107x, bounds.max.f108y, bounds.min.f109z).mul(transform));
        ext(tmpVector.set(bounds.min.f107x, bounds.max.f108y, bounds.max.f109z).mul(transform));
        ext(tmpVector.set(bounds.max.f107x, bounds.min.f108y, bounds.min.f109z).mul(transform));
        ext(tmpVector.set(bounds.max.f107x, bounds.min.f108y, bounds.max.f109z).mul(transform));
        ext(tmpVector.set(bounds.max.f107x, bounds.max.f108y, bounds.min.f109z).mul(transform));
        ext(tmpVector.set(bounds.max.f107x, bounds.max.f108y, bounds.max.f109z).mul(transform));
        return this;
    }

    public BoundingBox mul(Matrix4 transform) {
        float x0 = this.min.f107x;
        float y0 = this.min.f108y;
        float z0 = this.min.f109z;
        float x1 = this.max.f107x;
        float y1 = this.max.f108y;
        float z1 = this.max.f109z;
        inf();
        ext(tmpVector.set(x0, y0, z0).mul(transform));
        ext(tmpVector.set(x0, y0, z1).mul(transform));
        ext(tmpVector.set(x0, y1, z0).mul(transform));
        ext(tmpVector.set(x0, y1, z1).mul(transform));
        ext(tmpVector.set(x1, y0, z0).mul(transform));
        ext(tmpVector.set(x1, y0, z1).mul(transform));
        ext(tmpVector.set(x1, y1, z0).mul(transform));
        ext(tmpVector.set(x1, y1, z1).mul(transform));
        return this;
    }

    public boolean contains(BoundingBox b) {
        return !isValid() || (this.min.f107x <= b.min.f107x && this.min.f108y <= b.min.f108y && this.min.f109z <= b.min.f109z && this.max.f107x >= b.max.f107x && this.max.f108y >= b.max.f108y && this.max.f109z >= b.max.f109z);
    }

    public boolean intersects(BoundingBox b) {
        if (!isValid()) {
            return false;
        }
        float lx = Math.abs(this.cnt.f107x - b.cnt.f107x);
        float sumx = (this.dim.f107x / 2.0f) + (b.dim.f107x / 2.0f);
        float ly = Math.abs(this.cnt.f108y - b.cnt.f108y);
        float sumy = (this.dim.f108y / 2.0f) + (b.dim.f108y / 2.0f);
        float lz = Math.abs(this.cnt.f109z - b.cnt.f109z);
        float sumz = (this.dim.f109z / 2.0f) + (b.dim.f109z / 2.0f);
        if (lx > sumx || ly > sumy || lz > sumz) {
            return false;
        }
        return true;
    }

    public boolean contains(Vector3 v) {
        return this.min.f107x <= v.f107x && this.max.f107x >= v.f107x && this.min.f108y <= v.f108y && this.max.f108y >= v.f108y && this.min.f109z <= v.f109z && this.max.f109z >= v.f109z;
    }

    public String toString() {
        return "[" + this.min + "|" + this.max + "]";
    }

    public BoundingBox ext(float x, float y, float z) {
        return set(this.min.set(min(this.min.f107x, x), min(this.min.f108y, y), min(this.min.f109z, z)), this.max.set(max(this.max.f107x, x), max(this.max.f108y, y), max(this.max.f109z, z)));
    }

    static final float min(float a, float b) {
        return a > b ? b : a;
    }

    static final float max(float a, float b) {
        return a > b ? a : b;
    }
}
