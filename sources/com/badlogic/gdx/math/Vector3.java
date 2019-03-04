package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

public class Vector3 implements Serializable, Vector<Vector3> {
    /* renamed from: X */
    public static final Vector3 f104X = new Vector3(1.0f, 0.0f, 0.0f);
    /* renamed from: Y */
    public static final Vector3 f105Y = new Vector3(0.0f, 1.0f, 0.0f);
    /* renamed from: Z */
    public static final Vector3 f106Z = new Vector3(0.0f, 0.0f, 1.0f);
    public static final Vector3 Zero = new Vector3(0.0f, 0.0f, 0.0f);
    private static final long serialVersionUID = 3840054589595372522L;
    private static final Matrix4 tmpMat = new Matrix4();
    /* renamed from: x */
    public float f107x;
    /* renamed from: y */
    public float f108y;
    /* renamed from: z */
    public float f109z;

    public Vector3(float x, float y, float z) {
        set(x, y, z);
    }

    public Vector3(Vector3 vector) {
        set(vector);
    }

    public Vector3(float[] values) {
        set(values[0], values[1], values[2]);
    }

    public Vector3(Vector2 vector, float z) {
        set(vector.f102x, vector.f103y, z);
    }

    public Vector3 set(float x, float y, float z) {
        this.f107x = x;
        this.f108y = y;
        this.f109z = z;
        return this;
    }

    public Vector3 set(Vector3 vector) {
        return set(vector.f107x, vector.f108y, vector.f109z);
    }

    public Vector3 set(float[] values) {
        return set(values[0], values[1], values[2]);
    }

    public Vector3 set(Vector2 vector, float z) {
        return set(vector.f102x, vector.f103y, z);
    }

    public Vector3 cpy() {
        return new Vector3(this);
    }

    public Vector3 add(Vector3 vector) {
        return add(vector.f107x, vector.f108y, vector.f109z);
    }

    public Vector3 add(float x, float y, float z) {
        return set(this.f107x + x, this.f108y + y, this.f109z + z);
    }

    public Vector3 add(float values) {
        return set(this.f107x + values, this.f108y + values, this.f109z + values);
    }

    public Vector3 sub(Vector3 a_vec) {
        return sub(a_vec.f107x, a_vec.f108y, a_vec.f109z);
    }

    public Vector3 sub(float x, float y, float z) {
        return set(this.f107x - x, this.f108y - y, this.f109z - z);
    }

    public Vector3 sub(float value) {
        return set(this.f107x - value, this.f108y - value, this.f109z - value);
    }

    public Vector3 scl(float scalar) {
        return set(this.f107x * scalar, this.f108y * scalar, this.f109z * scalar);
    }

    public Vector3 scl(Vector3 other) {
        return set(this.f107x * other.f107x, this.f108y * other.f108y, this.f109z * other.f109z);
    }

    public Vector3 scl(float vx, float vy, float vz) {
        return set(this.f107x * vx, this.f108y * vy, this.f109z * vz);
    }

    public Vector3 mulAdd(Vector3 vec, float scalar) {
        this.f107x += vec.f107x * scalar;
        this.f108y += vec.f108y * scalar;
        this.f109z += vec.f109z * scalar;
        return this;
    }

    public Vector3 mulAdd(Vector3 vec, Vector3 mulVec) {
        this.f107x += vec.f107x * mulVec.f107x;
        this.f108y += vec.f108y * mulVec.f108y;
        this.f109z += vec.f109z * mulVec.f109z;
        return this;
    }

    public static float len(float x, float y, float z) {
        return (float) Math.sqrt((double) (((x * x) + (y * y)) + (z * z)));
    }

    public float len() {
        return (float) Math.sqrt((double) (((this.f107x * this.f107x) + (this.f108y * this.f108y)) + (this.f109z * this.f109z)));
    }

    public static float len2(float x, float y, float z) {
        return ((x * x) + (y * y)) + (z * z);
    }

    public float len2() {
        return ((this.f107x * this.f107x) + (this.f108y * this.f108y)) + (this.f109z * this.f109z);
    }

    public boolean idt(Vector3 vector) {
        return this.f107x == vector.f107x && this.f108y == vector.f108y && this.f109z == vector.f109z;
    }

    public static float dst(float x1, float y1, float z1, float x2, float y2, float z2) {
        float a = x2 - x1;
        float b = y2 - y1;
        float c = z2 - z1;
        return (float) Math.sqrt((double) (((a * a) + (b * b)) + (c * c)));
    }

    public float dst(Vector3 vector) {
        float a = vector.f107x - this.f107x;
        float b = vector.f108y - this.f108y;
        float c = vector.f109z - this.f109z;
        return (float) Math.sqrt((double) (((a * a) + (b * b)) + (c * c)));
    }

    public float dst(float x, float y, float z) {
        float a = x - this.f107x;
        float b = y - this.f108y;
        float c = z - this.f109z;
        return (float) Math.sqrt((double) (((a * a) + (b * b)) + (c * c)));
    }

    public static float dst2(float x1, float y1, float z1, float x2, float y2, float z2) {
        float a = x2 - x1;
        float b = y2 - y1;
        float c = z2 - z1;
        return ((a * a) + (b * b)) + (c * c);
    }

    public float dst2(Vector3 point) {
        float a = point.f107x - this.f107x;
        float b = point.f108y - this.f108y;
        float c = point.f109z - this.f109z;
        return ((a * a) + (b * b)) + (c * c);
    }

    public float dst2(float x, float y, float z) {
        float a = x - this.f107x;
        float b = y - this.f108y;
        float c = z - this.f109z;
        return ((a * a) + (b * b)) + (c * c);
    }

    public Vector3 nor() {
        float len2 = len2();
        return (len2 == 0.0f || len2 == 1.0f) ? this : scl(1.0f / ((float) Math.sqrt((double) len2)));
    }

    public static float dot(float x1, float y1, float z1, float x2, float y2, float z2) {
        return ((x1 * x2) + (y1 * y2)) + (z1 * z2);
    }

    public float dot(Vector3 vector) {
        return ((this.f107x * vector.f107x) + (this.f108y * vector.f108y)) + (this.f109z * vector.f109z);
    }

    public float dot(float x, float y, float z) {
        return ((this.f107x * x) + (this.f108y * y)) + (this.f109z * z);
    }

    public Vector3 crs(Vector3 vector) {
        return set((this.f108y * vector.f109z) - (this.f109z * vector.f108y), (this.f109z * vector.f107x) - (this.f107x * vector.f109z), (this.f107x * vector.f108y) - (this.f108y * vector.f107x));
    }

    public Vector3 crs(float x, float y, float z) {
        return set((this.f108y * z) - (this.f109z * y), (this.f109z * x) - (this.f107x * z), (this.f107x * y) - (this.f108y * x));
    }

    public Vector3 mul4x3(float[] matrix) {
        return set((((this.f107x * matrix[0]) + (this.f108y * matrix[3])) + (this.f109z * matrix[6])) + matrix[9], (((this.f107x * matrix[1]) + (this.f108y * matrix[4])) + (this.f109z * matrix[7])) + matrix[10], (((this.f107x * matrix[2]) + (this.f108y * matrix[5])) + (this.f109z * matrix[8])) + matrix[11]);
    }

    public Vector3 mul(Matrix4 matrix) {
        float[] l_mat = matrix.val;
        return set((((this.f107x * l_mat[0]) + (this.f108y * l_mat[4])) + (this.f109z * l_mat[8])) + l_mat[12], (((this.f107x * l_mat[1]) + (this.f108y * l_mat[5])) + (this.f109z * l_mat[9])) + l_mat[13], (((this.f107x * l_mat[2]) + (this.f108y * l_mat[6])) + (this.f109z * l_mat[10])) + l_mat[14]);
    }

    public Vector3 traMul(Matrix4 matrix) {
        float[] l_mat = matrix.val;
        return set((((this.f107x * l_mat[0]) + (this.f108y * l_mat[1])) + (this.f109z * l_mat[2])) + l_mat[3], (((this.f107x * l_mat[4]) + (this.f108y * l_mat[5])) + (this.f109z * l_mat[6])) + l_mat[7], (((this.f107x * l_mat[8]) + (this.f108y * l_mat[9])) + (this.f109z * l_mat[10])) + l_mat[11]);
    }

    public Vector3 mul(Matrix3 matrix) {
        float[] l_mat = matrix.val;
        return set(((this.f107x * l_mat[0]) + (this.f108y * l_mat[3])) + (this.f109z * l_mat[6]), ((this.f107x * l_mat[1]) + (this.f108y * l_mat[4])) + (this.f109z * l_mat[7]), ((this.f107x * l_mat[2]) + (this.f108y * l_mat[5])) + (this.f109z * l_mat[8]));
    }

    public Vector3 traMul(Matrix3 matrix) {
        float[] l_mat = matrix.val;
        return set(((this.f107x * l_mat[0]) + (this.f108y * l_mat[1])) + (this.f109z * l_mat[2]), ((this.f107x * l_mat[3]) + (this.f108y * l_mat[4])) + (this.f109z * l_mat[5]), ((this.f107x * l_mat[6]) + (this.f108y * l_mat[7])) + (this.f109z * l_mat[8]));
    }

    public Vector3 mul(Quaternion quat) {
        return quat.transform(this);
    }

    public Vector3 prj(Matrix4 matrix) {
        float[] l_mat = matrix.val;
        float l_w = 1.0f / ((((this.f107x * l_mat[3]) + (this.f108y * l_mat[7])) + (this.f109z * l_mat[11])) + l_mat[15]);
        return set(((((this.f107x * l_mat[0]) + (this.f108y * l_mat[4])) + (this.f109z * l_mat[8])) + l_mat[12]) * l_w, ((((this.f107x * l_mat[1]) + (this.f108y * l_mat[5])) + (this.f109z * l_mat[9])) + l_mat[13]) * l_w, ((((this.f107x * l_mat[2]) + (this.f108y * l_mat[6])) + (this.f109z * l_mat[10])) + l_mat[14]) * l_w);
    }

    public Vector3 rot(Matrix4 matrix) {
        float[] l_mat = matrix.val;
        return set(((this.f107x * l_mat[0]) + (this.f108y * l_mat[4])) + (this.f109z * l_mat[8]), ((this.f107x * l_mat[1]) + (this.f108y * l_mat[5])) + (this.f109z * l_mat[9]), ((this.f107x * l_mat[2]) + (this.f108y * l_mat[6])) + (this.f109z * l_mat[10]));
    }

    public Vector3 unrotate(Matrix4 matrix) {
        float[] l_mat = matrix.val;
        return set(((this.f107x * l_mat[0]) + (this.f108y * l_mat[1])) + (this.f109z * l_mat[2]), ((this.f107x * l_mat[4]) + (this.f108y * l_mat[5])) + (this.f109z * l_mat[6]), ((this.f107x * l_mat[8]) + (this.f108y * l_mat[9])) + (this.f109z * l_mat[10]));
    }

    public Vector3 untransform(Matrix4 matrix) {
        float[] l_mat = matrix.val;
        this.f107x -= l_mat[12];
        this.f108y -= l_mat[12];
        this.f109z -= l_mat[12];
        return set(((this.f107x * l_mat[0]) + (this.f108y * l_mat[1])) + (this.f109z * l_mat[2]), ((this.f107x * l_mat[4]) + (this.f108y * l_mat[5])) + (this.f109z * l_mat[6]), ((this.f107x * l_mat[8]) + (this.f108y * l_mat[9])) + (this.f109z * l_mat[10]));
    }

    public Vector3 rotate(float degrees, float axisX, float axisY, float axisZ) {
        return mul(tmpMat.setToRotation(axisX, axisY, axisZ, degrees));
    }

    public Vector3 rotateRad(float radians, float axisX, float axisY, float axisZ) {
        return mul(tmpMat.setToRotationRad(axisX, axisY, axisZ, radians));
    }

    public Vector3 rotate(Vector3 axis, float degrees) {
        tmpMat.setToRotation(axis, degrees);
        return mul(tmpMat);
    }

    public Vector3 rotateRad(Vector3 axis, float radians) {
        tmpMat.setToRotationRad(axis, radians);
        return mul(tmpMat);
    }

    public boolean isUnit() {
        return isUnit(1.0E-9f);
    }

    public boolean isUnit(float margin) {
        return Math.abs(len2() - 1.0f) < margin;
    }

    public boolean isZero() {
        return this.f107x == 0.0f && this.f108y == 0.0f && this.f109z == 0.0f;
    }

    public boolean isZero(float margin) {
        return len2() < margin;
    }

    public boolean isOnLine(Vector3 other, float epsilon) {
        return len2((this.f108y * other.f109z) - (this.f109z * other.f108y), (this.f109z * other.f107x) - (this.f107x * other.f109z), (this.f107x * other.f108y) - (this.f108y * other.f107x)) <= epsilon;
    }

    public boolean isOnLine(Vector3 other) {
        return len2((this.f108y * other.f109z) - (this.f109z * other.f108y), (this.f109z * other.f107x) - (this.f107x * other.f109z), (this.f107x * other.f108y) - (this.f108y * other.f107x)) <= 1.0E-6f;
    }

    public boolean isCollinear(Vector3 other, float epsilon) {
        return isOnLine(other, epsilon) && hasSameDirection(other);
    }

    public boolean isCollinear(Vector3 other) {
        return isOnLine(other) && hasSameDirection(other);
    }

    public boolean isCollinearOpposite(Vector3 other, float epsilon) {
        return isOnLine(other, epsilon) && hasOppositeDirection(other);
    }

    public boolean isCollinearOpposite(Vector3 other) {
        return isOnLine(other) && hasOppositeDirection(other);
    }

    public boolean isPerpendicular(Vector3 vector) {
        return MathUtils.isZero(dot(vector));
    }

    public boolean isPerpendicular(Vector3 vector, float epsilon) {
        return MathUtils.isZero(dot(vector), epsilon);
    }

    public boolean hasSameDirection(Vector3 vector) {
        return dot(vector) > 0.0f;
    }

    public boolean hasOppositeDirection(Vector3 vector) {
        return dot(vector) < 0.0f;
    }

    public Vector3 lerp(Vector3 target, float alpha) {
        this.f107x += (target.f107x - this.f107x) * alpha;
        this.f108y += (target.f108y - this.f108y) * alpha;
        this.f109z += (target.f109z - this.f109z) * alpha;
        return this;
    }

    public Vector3 interpolate(Vector3 target, float alpha, Interpolation interpolator) {
        return lerp(target, interpolator.apply(0.0f, 1.0f, alpha));
    }

    public Vector3 slerp(Vector3 target, float alpha) {
        float dot = dot(target);
        if (((double) dot) > 0.9995d || ((double) dot) < -0.9995d) {
            return lerp(target, alpha);
        }
        float theta = ((float) Math.acos((double) dot)) * alpha;
        float tx = target.f107x - (this.f107x * dot);
        float ty = target.f108y - (this.f108y * dot);
        float tz = target.f109z - (this.f109z * dot);
        float l2 = ((tx * tx) + (ty * ty)) + (tz * tz);
        float dl = ((float) Math.sin((double) theta)) * (l2 < 1.0E-4f ? 1.0f : 1.0f / ((float) Math.sqrt((double) l2)));
        return scl((float) Math.cos((double) theta)).add(tx * dl, ty * dl, tz * dl).nor();
    }

    public String toString() {
        return "[" + this.f107x + ", " + this.f108y + ", " + this.f109z + "]";
    }

    public Vector3 limit(float limit) {
        return limit2(limit * limit);
    }

    public Vector3 limit2(float limit2) {
        float len2 = len2();
        if (len2 > limit2) {
            scl((float) Math.sqrt((double) (limit2 / len2)));
        }
        return this;
    }

    public Vector3 setLength(float len) {
        return setLength2(len * len);
    }

    public Vector3 setLength2(float len2) {
        float oldLen2 = len2();
        return (oldLen2 == 0.0f || oldLen2 == len2) ? this : scl((float) Math.sqrt((double) (len2 / oldLen2)));
    }

    public Vector3 clamp(float min, float max) {
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

    public int hashCode() {
        return ((((NumberUtils.floatToIntBits(this.f107x) + 31) * 31) + NumberUtils.floatToIntBits(this.f108y)) * 31) + NumberUtils.floatToIntBits(this.f109z);
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
        Vector3 other = (Vector3) obj;
        if (NumberUtils.floatToIntBits(this.f107x) != NumberUtils.floatToIntBits(other.f107x)) {
            return false;
        }
        if (NumberUtils.floatToIntBits(this.f108y) != NumberUtils.floatToIntBits(other.f108y)) {
            return false;
        }
        if (NumberUtils.floatToIntBits(this.f109z) != NumberUtils.floatToIntBits(other.f109z)) {
            return false;
        }
        return true;
    }

    public boolean epsilonEquals(Vector3 other, float epsilon) {
        if (other != null && Math.abs(other.f107x - this.f107x) <= epsilon && Math.abs(other.f108y - this.f108y) <= epsilon && Math.abs(other.f109z - this.f109z) <= epsilon) {
            return true;
        }
        return false;
    }

    public boolean epsilonEquals(float x, float y, float z, float epsilon) {
        if (Math.abs(x - this.f107x) <= epsilon && Math.abs(y - this.f108y) <= epsilon && Math.abs(z - this.f109z) <= epsilon) {
            return true;
        }
        return false;
    }

    public Vector3 setZero() {
        this.f107x = 0.0f;
        this.f108y = 0.0f;
        this.f109z = 0.0f;
        return this;
    }
}
