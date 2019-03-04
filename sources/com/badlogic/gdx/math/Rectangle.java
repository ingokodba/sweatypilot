package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

public class Rectangle implements Serializable, Shape2D {
    private static final long serialVersionUID = 5733252015138115702L;
    public static final Rectangle tmp = new Rectangle();
    public static final Rectangle tmp2 = new Rectangle();
    public float height;
    public float width;
    /* renamed from: x */
    public float f98x;
    /* renamed from: y */
    public float f99y;

    public Rectangle(float x, float y, float width, float height) {
        this.f98x = x;
        this.f99y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle(Rectangle rect) {
        this.f98x = rect.f98x;
        this.f99y = rect.f99y;
        this.width = rect.width;
        this.height = rect.height;
    }

    public Rectangle set(float x, float y, float width, float height) {
        this.f98x = x;
        this.f99y = y;
        this.width = width;
        this.height = height;
        return this;
    }

    public float getX() {
        return this.f98x;
    }

    public Rectangle setX(float x) {
        this.f98x = x;
        return this;
    }

    public float getY() {
        return this.f99y;
    }

    public Rectangle setY(float y) {
        this.f99y = y;
        return this;
    }

    public float getWidth() {
        return this.width;
    }

    public Rectangle setWidth(float width) {
        this.width = width;
        return this;
    }

    public float getHeight() {
        return this.height;
    }

    public Rectangle setHeight(float height) {
        this.height = height;
        return this;
    }

    public Vector2 getPosition(Vector2 position) {
        return position.set(this.f98x, this.f99y);
    }

    public Rectangle setPosition(Vector2 position) {
        this.f98x = position.f102x;
        this.f99y = position.f103y;
        return this;
    }

    public Rectangle setPosition(float x, float y) {
        this.f98x = x;
        this.f99y = y;
        return this;
    }

    public Rectangle setSize(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Rectangle setSize(float sizeXY) {
        this.width = sizeXY;
        this.height = sizeXY;
        return this;
    }

    public Vector2 getSize(Vector2 size) {
        return size.set(this.width, this.height);
    }

    public boolean contains(float x, float y) {
        return this.f98x <= x && this.f98x + this.width >= x && this.f99y <= y && this.f99y + this.height >= y;
    }

    public boolean contains(Vector2 point) {
        return contains(point.f102x, point.f103y);
    }

    public boolean contains(Rectangle rectangle) {
        float xmin = rectangle.f98x;
        float xmax = xmin + rectangle.width;
        float ymin = rectangle.f99y;
        float ymax = ymin + rectangle.height;
        return xmin > this.f98x && xmin < this.f98x + this.width && xmax > this.f98x && xmax < this.f98x + this.width && ymin > this.f99y && ymin < this.f99y + this.height && ymax > this.f99y && ymax < this.f99y + this.height;
    }

    public boolean overlaps(Rectangle r) {
        return this.f98x < r.f98x + r.width && this.f98x + this.width > r.f98x && this.f99y < r.f99y + r.height && this.f99y + this.height > r.f99y;
    }

    public Rectangle set(Rectangle rect) {
        this.f98x = rect.f98x;
        this.f99y = rect.f99y;
        this.width = rect.width;
        this.height = rect.height;
        return this;
    }

    public Rectangle merge(Rectangle rect) {
        float minX = Math.min(this.f98x, rect.f98x);
        float maxX = Math.max(this.f98x + this.width, rect.f98x + rect.width);
        this.f98x = minX;
        this.width = maxX - minX;
        float minY = Math.min(this.f99y, rect.f99y);
        float maxY = Math.max(this.f99y + this.height, rect.f99y + rect.height);
        this.f99y = minY;
        this.height = maxY - minY;
        return this;
    }

    public Rectangle merge(float x, float y) {
        float minX = Math.min(this.f98x, x);
        float maxX = Math.max(this.f98x + this.width, x);
        this.f98x = minX;
        this.width = maxX - minX;
        float minY = Math.min(this.f99y, y);
        float maxY = Math.max(this.f99y + this.height, y);
        this.f99y = minY;
        this.height = maxY - minY;
        return this;
    }

    public Rectangle merge(Vector2 vec) {
        return merge(vec.f102x, vec.f103y);
    }

    public Rectangle merge(Vector2[] vecs) {
        float minX = this.f98x;
        float maxX = this.f98x + this.width;
        float minY = this.f99y;
        float maxY = this.f99y + this.height;
        for (Vector2 v : vecs) {
            minX = Math.min(minX, v.f102x);
            maxX = Math.max(maxX, v.f102x);
            minY = Math.min(minY, v.f103y);
            maxY = Math.max(maxY, v.f103y);
        }
        this.f98x = minX;
        this.width = maxX - minX;
        this.f99y = minY;
        this.height = maxY - minY;
        return this;
    }

    public float getAspectRatio() {
        return this.height == 0.0f ? Float.NaN : this.width / this.height;
    }

    public Vector2 getCenter(Vector2 vector) {
        vector.f102x = this.f98x + (this.width / 2.0f);
        vector.f103y = this.f99y + (this.height / 2.0f);
        return vector;
    }

    public Rectangle setCenter(float x, float y) {
        setPosition(x - (this.width / 2.0f), y - (this.height / 2.0f));
        return this;
    }

    public Rectangle setCenter(Vector2 position) {
        setPosition(position.f102x - (this.width / 2.0f), position.f103y - (this.height / 2.0f));
        return this;
    }

    public Rectangle fitOutside(Rectangle rect) {
        float ratio = getAspectRatio();
        if (ratio > rect.getAspectRatio()) {
            setSize(rect.height * ratio, rect.height);
        } else {
            setSize(rect.width, rect.width / ratio);
        }
        setPosition((rect.f98x + (rect.width / 2.0f)) - (this.width / 2.0f), (rect.f99y + (rect.height / 2.0f)) - (this.height / 2.0f));
        return this;
    }

    public Rectangle fitInside(Rectangle rect) {
        float ratio = getAspectRatio();
        if (ratio < rect.getAspectRatio()) {
            setSize(rect.height * ratio, rect.height);
        } else {
            setSize(rect.width, rect.width / ratio);
        }
        setPosition((rect.f98x + (rect.width / 2.0f)) - (this.width / 2.0f), (rect.f99y + (rect.height / 2.0f)) - (this.height / 2.0f));
        return this;
    }

    public String toString() {
        return this.f98x + "," + this.f99y + "," + this.width + "," + this.height;
    }

    public float area() {
        return this.width * this.height;
    }

    public float perimeter() {
        return 2.0f * (this.width + this.height);
    }

    public int hashCode() {
        return ((((((NumberUtils.floatToRawIntBits(this.height) + 31) * 31) + NumberUtils.floatToRawIntBits(this.width)) * 31) + NumberUtils.floatToRawIntBits(this.f98x)) * 31) + NumberUtils.floatToRawIntBits(this.f99y);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Rectangle other = (Rectangle) obj;
        if (NumberUtils.floatToRawIntBits(this.height) != NumberUtils.floatToRawIntBits(other.height)) {
            return false;
        }
        if (NumberUtils.floatToRawIntBits(this.width) != NumberUtils.floatToRawIntBits(other.width)) {
            return false;
        }
        if (NumberUtils.floatToRawIntBits(this.f98x) != NumberUtils.floatToRawIntBits(other.f98x)) {
            return false;
        }
        if (NumberUtils.floatToRawIntBits(this.f99y) != NumberUtils.floatToRawIntBits(other.f99y)) {
            return false;
        }
        return true;
    }
}
