package com.badlogic.gdx.math;

public class GridPoint3 {
    /* renamed from: x */
    public int f59x;
    /* renamed from: y */
    public int f60y;
    /* renamed from: z */
    public int f61z;

    public GridPoint3(int x, int y, int z) {
        this.f59x = x;
        this.f60y = y;
        this.f61z = z;
    }

    public GridPoint3(GridPoint3 point) {
        this.f59x = point.f59x;
        this.f60y = point.f60y;
        this.f61z = point.f61z;
    }

    public GridPoint3 set(GridPoint3 point) {
        this.f59x = point.f59x;
        this.f60y = point.f60y;
        this.f61z = point.f61z;
        return this;
    }

    public GridPoint3 set(int x, int y, int z) {
        this.f59x = x;
        this.f60y = y;
        this.f61z = z;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        GridPoint3 g = (GridPoint3) o;
        if (this.f59x == g.f59x && this.f60y == g.f60y && this.f61z == g.f61z) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f59x + 17) * 17) + this.f60y) * 17) + this.f61z;
    }
}
