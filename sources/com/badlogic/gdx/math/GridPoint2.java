package com.badlogic.gdx.math;

public class GridPoint2 {
    /* renamed from: x */
    public int f57x;
    /* renamed from: y */
    public int f58y;

    public GridPoint2(int x, int y) {
        this.f57x = x;
        this.f58y = y;
    }

    public GridPoint2(GridPoint2 point) {
        this.f57x = point.f57x;
        this.f58y = point.f58y;
    }

    public GridPoint2 set(GridPoint2 point) {
        this.f57x = point.f57x;
        this.f58y = point.f58y;
        return this;
    }

    public GridPoint2 set(int x, int y) {
        this.f57x = x;
        this.f58y = y;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        GridPoint2 g = (GridPoint2) o;
        if (this.f57x == g.f57x && this.f58y == g.f58y) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.f57x + 53) * 53) + this.f58y;
    }
}
