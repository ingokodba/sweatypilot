package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

public class Vector2 implements Serializable, Vector<Vector2> {
    /* renamed from: X */
    public static final Vector2 f100X = new Vector2(1.0f, 0.0f);
    /* renamed from: Y */
    public static final Vector2 f101Y = new Vector2(0.0f, 1.0f);
    public static final Vector2 Zero = new Vector2(0.0f, 0.0f);
    private static final long serialVersionUID = 913902788239530931L;
    /* renamed from: x */
    public float f102x;
    /* renamed from: y */
    public float f103y;

    public Vector2(float x, float y) {
        this.f102x = x;
        this.f103y = y;
    }

    public Vector2(Vector2 v) {
        set(v);
    }

    public Vector2 cpy() {
        return new Vector2(this);
    }

    public static float len(float x, float y) {
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    public float len() {
        return (float) Math.sqrt((double) ((this.f102x * this.f102x) + (this.f103y * this.f103y)));
    }

    public static float len2(float x, float y) {
        return (x * x) + (y * y);
    }

    public float len2() {
        return (this.f102x * this.f102x) + (this.f103y * this.f103y);
    }

    public Vector2 set(Vector2 v) {
        this.f102x = v.f102x;
        this.f103y = v.f103y;
        return this;
    }

    public Vector2 set(float x, float y) {
        this.f102x = x;
        this.f103y = y;
        return this;
    }

    public Vector2 sub(Vector2 v) {
        this.f102x -= v.f102x;
        this.f103y -= v.f103y;
        return this;
    }

    public Vector2 sub(float x, float y) {
        this.f102x -= x;
        this.f103y -= y;
        return this;
    }

    public Vector2 nor() {
        float len = len();
        if (len != 0.0f) {
            this.f102x /= len;
            this.f103y /= len;
        }
        return this;
    }

    public Vector2 add(Vector2 v) {
        this.f102x += v.f102x;
        this.f103y += v.f103y;
        return this;
    }

    public Vector2 add(float x, float y) {
        this.f102x += x;
        this.f103y += y;
        return this;
    }

    public static float dot(float x1, float y1, float x2, float y2) {
        return (x1 * x2) + (y1 * y2);
    }

    public float dot(Vector2 v) {
        return (this.f102x * v.f102x) + (this.f103y * v.f103y);
    }

    public float dot(float ox, float oy) {
        return (this.f102x * ox) + (this.f103y * oy);
    }

    public Vector2 scl(float scalar) {
        this.f102x *= scalar;
        this.f103y *= scalar;
        return this;
    }

    public Vector2 scl(float x, float y) {
        this.f102x *= x;
        this.f103y *= y;
        return this;
    }

    public Vector2 scl(Vector2 v) {
        this.f102x *= v.f102x;
        this.f103y *= v.f103y;
        return this;
    }

    public Vector2 mulAdd(Vector2 vec, float scalar) {
        this.f102x += vec.f102x * scalar;
        this.f103y += vec.f103y * scalar;
        return this;
    }

    public Vector2 mulAdd(Vector2 vec, Vector2 mulVec) {
        this.f102x += vec.f102x * mulVec.f102x;
        this.f103y += vec.f103y * mulVec.f103y;
        return this;
    }

    public static float dst(float x1, float y1, float x2, float y2) {
        float x_d = x2 - x1;
        float y_d = y2 - y1;
        return (float) Math.sqrt((double) ((x_d * x_d) + (y_d * y_d)));
    }

    public float dst(Vector2 v) {
        float x_d = v.f102x - this.f102x;
        float y_d = v.f103y - this.f103y;
        return (float) Math.sqrt((double) ((x_d * x_d) + (y_d * y_d)));
    }

    public float dst(float x, float y) {
        float x_d = x - this.f102x;
        float y_d = y - this.f103y;
        return (float) Math.sqrt((double) ((x_d * x_d) + (y_d * y_d)));
    }

    public static float dst2(float x1, float y1, float x2, float y2) {
        float x_d = x2 - x1;
        float y_d = y2 - y1;
        return (x_d * x_d) + (y_d * y_d);
    }

    public float dst2(Vector2 v) {
        float x_d = v.f102x - this.f102x;
        float y_d = v.f103y - this.f103y;
        return (x_d * x_d) + (y_d * y_d);
    }

    public float dst2(float x, float y) {
        float x_d = x - this.f102x;
        float y_d = y - this.f103y;
        return (x_d * x_d) + (y_d * y_d);
    }

    public Vector2 limit(float limit) {
        return limit2(limit * limit);
    }

    public Vector2 limit2(float limit2) {
        float len2 = len2();
        if (len2 > limit2) {
            return scl((float) Math.sqrt((double) (limit2 / len2)));
        }
        return this;
    }

    public Vector2 clamp(float min, float max) {
        float len2 = len2();
        if (len2 == 0.0f) {
            return this;
        }
        float max2 = max * max;
        if (len2 > max2) {
            return scl((float) Math.sqrt((double) (max2 / len2)));
        }
        float min2 = min * min;
        if (len2 < min2) {
            return scl((float) Math.sqrt((double) (min2 / len2)));
        }
        return this;
    }

    public Vector2 setLength(float len) {
        return setLength2(len * len);
    }

    public Vector2 setLength2(float len2) {
        float oldLen2 = len2();
        return (oldLen2 == 0.0f || oldLen2 == len2) ? this : scl((float) Math.sqrt((double) (len2 / oldLen2)));
    }

    public String toString() {
        return "[" + this.f102x + ":" + this.f103y + "]";
    }

    public Vector2 mul(Matrix3 mat) {
        float y = ((this.f102x * mat.val[1]) + (this.f103y * mat.val[4])) + mat.val[7];
        this.f102x = ((this.f102x * mat.val[0]) + (this.f103y * mat.val[3])) + mat.val[6];
        this.f103y = y;
        return this;
    }

    public float crs(Vector2 v) {
        return (this.f102x * v.f103y) - (this.f103y * v.f102x);
    }

    public float crs(float x, float y) {
        return (this.f102x * y) - (this.f103y * x);
    }

    public float angle() {
        float angle = ((float) Math.atan2((double) this.f103y, (double) this.f102x)) * 57.295776f;
        if (angle < 0.0f) {
            return angle + 360.0f;
        }
        return angle;
    }

    public float angle(Vector2 reference) {
        return ((float) Math.atan2((double) crs(reference), (double) dot(reference))) * 57.295776f;
    }

    public float angleRad() {
        return (float) Math.atan2((double) this.f103y, (double) this.f102x);
    }

    public float angleRad(Vector2 reference) {
        return (float) Math.atan2((double) crs(reference), (double) dot(reference));
    }

    public Vector2 setAngle(float degrees) {
        return setAngleRad(0.017453292f * degrees);
    }

    public Vector2 setAngleRad(float radians) {
        set(len(), 0.0f);
        rotateRad(radians);
        return this;
    }

    public Vector2 rotate(float degrees) {
        return rotateRad(0.017453292f * degrees);
    }

    public Vector2 rotateRad(float radians) {
        float cos = (float) Math.cos((double) radians);
        float sin = (float) Math.sin((double) radians);
        float newY = (this.f102x * sin) + (this.f103y * cos);
        this.f102x = (this.f102x * cos) - (this.f103y * sin);
        this.f103y = newY;
        return this;
    }

    public Vector2 rotate90(int dir) {
        float x = this.f102x;
        if (dir >= 0) {
            this.f102x = -this.f103y;
            this.f103y = x;
        } else {
            this.f102x = this.f103y;
            this.f103y = -x;
        }
        return this;
    }

    public Vector2 lerp(Vector2 target, float alpha) {
        float invAlpha = 1.0f - alpha;
        this.f102x = (this.f102x * invAlpha) + (target.f102x * alpha);
        this.f103y = (this.f103y * invAlpha) + (target.f103y * alpha);
        return this;
    }

    public Vector2 interpolate(Vector2 target, float alpha, Interpolation interpolation) {
        return lerp(target, interpolation.apply(alpha));
    }

    public int hashCode() {
        return ((NumberUtils.floatToIntBits(this.f102x) + 31) * 31) + NumberUtils.floatToIntBits(this.f103y);
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
        Vector2 other = (Vector2) obj;
        if (NumberUtils.floatToIntBits(this.f102x) != NumberUtils.floatToIntBits(other.f102x)) {
            return false;
        }
        if (NumberUtils.floatToIntBits(this.f103y) != NumberUtils.floatToIntBits(other.f103y)) {
            return false;
        }
        return true;
    }

    public boolean epsilonEquals(Vector2 other, float epsilon) {
        if (other != null && Math.abs(other.f102x - this.f102x) <= epsilon && Math.abs(other.f103y - this.f103y) <= epsilon) {
            return true;
        }
        return false;
    }

    public boolean epsilonEquals(float x, float y, float epsilon) {
        if (Math.abs(x - this.f102x) <= epsilon && Math.abs(y - this.f103y) <= epsilon) {
            return true;
        }
        return false;
    }

    public boolean isUnit() {
        return isUnit(1.0E-9f);
    }

    public boolean isUnit(float margin) {
        return Math.abs(len2() - 1.0f) < margin;
    }

    public boolean isZero() {
        return this.f102x == 0.0f && this.f103y == 0.0f;
    }

    public boolean isZero(float margin) {
        return len2() < margin;
    }

    public boolean isOnLine(Vector2 other) {
        return MathUtils.isZero((this.f102x * other.f103y) - (this.f103y * other.f102x));
    }

    public boolean isOnLine(Vector2 other, float epsilon) {
        return MathUtils.isZero((this.f102x * other.f103y) - (this.f103y * other.f102x), epsilon);
    }

    public boolean isCollinear(Vector2 other, float epsilon) {
        return isOnLine(other, epsilon) && dot(other) > 0.0f;
    }

    public boolean isCollinear(Vector2 other) {
        return isOnLine(other) && dot(other) > 0.0f;
    }

    public boolean isCollinearOpposite(Vector2 other, float epsilon) {
        return isOnLine(other, epsilon) && dot(other) < 0.0f;
    }

    public boolean isCollinearOpposite(Vector2 other) {
        return isOnLine(other) && dot(other) < 0.0f;
    }

    public boolean isPerpendicular(Vector2 vector) {
        return MathUtils.isZero(dot(vector));
    }

    public boolean isPerpendicular(Vector2 vector, float epsilon) {
        return MathUtils.isZero(dot(vector), epsilon);
    }

    public boolean hasSameDirection(Vector2 vector) {
        return dot(vector) > 0.0f;
    }

    public boolean hasOppositeDirection(Vector2 vector) {
        return dot(vector) < 0.0f;
    }

    public Vector2 setZero() {
        this.f102x = 0.0f;
        this.f103y = 0.0f;
        return this;
    }
}
