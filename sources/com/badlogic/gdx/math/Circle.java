package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

public class Circle implements Serializable, Shape2D {
    public float radius;
    /* renamed from: x */
    public float f90x;
    /* renamed from: y */
    public float f91y;

    public Circle(float x, float y, float radius) {
        this.f90x = x;
        this.f91y = y;
        this.radius = radius;
    }

    public Circle(Vector2 position, float radius) {
        this.f90x = position.f102x;
        this.f91y = position.f103y;
        this.radius = radius;
    }

    public Circle(Circle circle) {
        this.f90x = circle.f90x;
        this.f91y = circle.f91y;
        this.radius = circle.radius;
    }

    public Circle(Vector2 center, Vector2 edge) {
        this.f90x = center.f102x;
        this.f91y = center.f103y;
        this.radius = Vector2.len(center.f102x - edge.f102x, center.f103y - edge.f103y);
    }

    public void set(float x, float y, float radius) {
        this.f90x = x;
        this.f91y = y;
        this.radius = radius;
    }

    public void set(Vector2 position, float radius) {
        this.f90x = position.f102x;
        this.f91y = position.f103y;
        this.radius = radius;
    }

    public void set(Circle circle) {
        this.f90x = circle.f90x;
        this.f91y = circle.f91y;
        this.radius = circle.radius;
    }

    public void set(Vector2 center, Vector2 edge) {
        this.f90x = center.f102x;
        this.f91y = center.f103y;
        this.radius = Vector2.len(center.f102x - edge.f102x, center.f103y - edge.f103y);
    }

    public void setPosition(Vector2 position) {
        this.f90x = position.f102x;
        this.f91y = position.f103y;
    }

    public void setPosition(float x, float y) {
        this.f90x = x;
        this.f91y = y;
    }

    public void setX(float x) {
        this.f90x = x;
    }

    public void setY(float y) {
        this.f91y = y;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public boolean contains(float x, float y) {
        x = this.f90x - x;
        y = this.f91y - y;
        return (x * x) + (y * y) <= this.radius * this.radius;
    }

    public boolean contains(Vector2 point) {
        float dx = this.f90x - point.f102x;
        float dy = this.f91y - point.f103y;
        return (dx * dx) + (dy * dy) <= this.radius * this.radius;
    }

    public boolean contains(Circle c) {
        float dx = this.f90x - c.f90x;
        float dy = this.f91y - c.f91y;
        return ((dx * dx) + (dy * dy)) + (c.radius * c.radius) <= this.radius * this.radius;
    }

    public boolean overlaps(Circle c) {
        float dx = this.f90x - c.f90x;
        float dy = this.f91y - c.f91y;
        float radiusSum = this.radius + c.radius;
        return (dx * dx) + (dy * dy) < radiusSum * radiusSum;
    }

    public String toString() {
        return this.f90x + "," + this.f91y + "," + this.radius;
    }

    public float circumference() {
        return this.radius * 6.2831855f;
    }

    public float area() {
        return (this.radius * this.radius) * 3.1415927f;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Circle c = (Circle) o;
        if (this.f90x == c.f90x && this.f91y == c.f91y && this.radius == c.radius) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((NumberUtils.floatToRawIntBits(this.radius) + 41) * 41) + NumberUtils.floatToRawIntBits(this.f90x)) * 41) + NumberUtils.floatToRawIntBits(this.f91y);
    }
}
