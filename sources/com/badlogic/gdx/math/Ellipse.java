package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

public class Ellipse implements Serializable, Shape2D {
    private static final long serialVersionUID = 7381533206532032099L;
    public float height;
    public float width;
    /* renamed from: x */
    public float f92x;
    /* renamed from: y */
    public float f93y;

    public Ellipse(Ellipse ellipse) {
        this.f92x = ellipse.f92x;
        this.f93y = ellipse.f93y;
        this.width = ellipse.width;
        this.height = ellipse.height;
    }

    public Ellipse(float x, float y, float width, float height) {
        this.f92x = x;
        this.f93y = y;
        this.width = width;
        this.height = height;
    }

    public Ellipse(Vector2 position, float width, float height) {
        this.f92x = position.f102x;
        this.f93y = position.f103y;
        this.width = width;
        this.height = height;
    }

    public Ellipse(Vector2 position, Vector2 size) {
        this.f92x = position.f102x;
        this.f93y = position.f103y;
        this.width = size.f102x;
        this.height = size.f103y;
    }

    public Ellipse(Circle circle) {
        this.f92x = circle.f90x;
        this.f93y = circle.f91y;
        this.width = circle.radius;
        this.height = circle.radius;
    }

    public boolean contains(float x, float y) {
        x -= this.f92x;
        y -= this.f93y;
        return ((x * x) / (((this.width * 0.5f) * this.width) * 0.5f)) + ((y * y) / (((this.height * 0.5f) * this.height) * 0.5f)) <= 1.0f;
    }

    public boolean contains(Vector2 point) {
        return contains(point.f102x, point.f103y);
    }

    public void set(float x, float y, float width, float height) {
        this.f92x = x;
        this.f93y = y;
        this.width = width;
        this.height = height;
    }

    public void set(Ellipse ellipse) {
        this.f92x = ellipse.f92x;
        this.f93y = ellipse.f93y;
        this.width = ellipse.width;
        this.height = ellipse.height;
    }

    public void set(Circle circle) {
        this.f92x = circle.f90x;
        this.f93y = circle.f91y;
        this.width = circle.radius;
        this.height = circle.radius;
    }

    public void set(Vector2 position, Vector2 size) {
        this.f92x = position.f102x;
        this.f93y = position.f103y;
        this.width = size.f102x;
        this.height = size.f103y;
    }

    public Ellipse setPosition(Vector2 position) {
        this.f92x = position.f102x;
        this.f93y = position.f103y;
        return this;
    }

    public Ellipse setPosition(float x, float y) {
        this.f92x = x;
        this.f93y = y;
        return this;
    }

    public Ellipse setSize(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public float area() {
        return (3.1415927f * (this.width * this.height)) / 4.0f;
    }

    public float circumference() {
        float a = this.width / 2.0f;
        float b = this.height / 2.0f;
        if (a * 3.0f > b || b * 3.0f > a) {
            return (float) (3.1415927410125732d * (((double) ((a + b) * 3.0f)) - Math.sqrt((double) (((3.0f * a) + b) * ((3.0f * b) + a)))));
        }
        return (float) (6.2831854820251465d * Math.sqrt((double) (((a * a) + (b * b)) / 2.0f)));
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Ellipse e = (Ellipse) o;
        if (this.f92x == e.f92x && this.f93y == e.f93y && this.width == e.width && this.height == e.height) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((NumberUtils.floatToRawIntBits(this.height) + 53) * 53) + NumberUtils.floatToRawIntBits(this.width)) * 53) + NumberUtils.floatToRawIntBits(this.f92x)) * 53) + NumberUtils.floatToRawIntBits(this.f93y);
    }
}
