package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.Serializable;

public class Matrix3 implements Serializable {
    public static final int M00 = 0;
    public static final int M01 = 3;
    public static final int M02 = 6;
    public static final int M10 = 1;
    public static final int M11 = 4;
    public static final int M12 = 7;
    public static final int M20 = 2;
    public static final int M21 = 5;
    public static final int M22 = 8;
    private static final long serialVersionUID = 7907569533774959788L;
    private float[] tmp;
    public float[] val;

    public Matrix3() {
        this.val = new float[9];
        this.tmp = new float[9];
        idt();
    }

    public Matrix3(Matrix3 matrix) {
        this.val = new float[9];
        this.tmp = new float[9];
        set(matrix);
    }

    public Matrix3(float[] values) {
        this.val = new float[9];
        this.tmp = new float[9];
        set(values);
    }

    public Matrix3 idt() {
        float[] val = this.val;
        val[0] = 1.0f;
        val[1] = 0.0f;
        val[2] = 0.0f;
        val[3] = 0.0f;
        val[4] = 1.0f;
        val[5] = 0.0f;
        val[6] = 0.0f;
        val[7] = 0.0f;
        val[8] = 1.0f;
        return this;
    }

    public Matrix3 mul(Matrix3 m) {
        float[] val = this.val;
        float v01 = ((val[0] * m.val[3]) + (val[3] * m.val[4])) + (val[6] * m.val[5]);
        float v02 = ((val[0] * m.val[6]) + (val[3] * m.val[7])) + (val[6] * m.val[8]);
        float v10 = ((val[1] * m.val[0]) + (val[4] * m.val[1])) + (val[7] * m.val[2]);
        float v11 = ((val[1] * m.val[3]) + (val[4] * m.val[4])) + (val[7] * m.val[5]);
        float v12 = ((val[1] * m.val[6]) + (val[4] * m.val[7])) + (val[7] * m.val[8]);
        float v20 = ((val[2] * m.val[0]) + (val[5] * m.val[1])) + (val[8] * m.val[2]);
        float v21 = ((val[2] * m.val[3]) + (val[5] * m.val[4])) + (val[8] * m.val[5]);
        float v22 = ((val[2] * m.val[6]) + (val[5] * m.val[7])) + (val[8] * m.val[8]);
        val[0] = ((val[0] * m.val[0]) + (val[3] * m.val[1])) + (val[6] * m.val[2]);
        val[1] = v10;
        val[2] = v20;
        val[3] = v01;
        val[4] = v11;
        val[5] = v21;
        val[6] = v02;
        val[7] = v12;
        val[8] = v22;
        return this;
    }

    public Matrix3 mulLeft(Matrix3 m) {
        float[] val = this.val;
        float v01 = ((m.val[0] * val[3]) + (m.val[3] * val[4])) + (m.val[6] * val[5]);
        float v02 = ((m.val[0] * val[6]) + (m.val[3] * val[7])) + (m.val[6] * val[8]);
        float v10 = ((m.val[1] * val[0]) + (m.val[4] * val[1])) + (m.val[7] * val[2]);
        float v11 = ((m.val[1] * val[3]) + (m.val[4] * val[4])) + (m.val[7] * val[5]);
        float v12 = ((m.val[1] * val[6]) + (m.val[4] * val[7])) + (m.val[7] * val[8]);
        float v20 = ((m.val[2] * val[0]) + (m.val[5] * val[1])) + (m.val[8] * val[2]);
        float v21 = ((m.val[2] * val[3]) + (m.val[5] * val[4])) + (m.val[8] * val[5]);
        float v22 = ((m.val[2] * val[6]) + (m.val[5] * val[7])) + (m.val[8] * val[8]);
        val[0] = ((m.val[0] * val[0]) + (m.val[3] * val[1])) + (m.val[6] * val[2]);
        val[1] = v10;
        val[2] = v20;
        val[3] = v01;
        val[4] = v11;
        val[5] = v21;
        val[6] = v02;
        val[7] = v12;
        val[8] = v22;
        return this;
    }

    public Matrix3 setToRotation(float degrees) {
        return setToRotationRad(0.017453292f * degrees);
    }

    public Matrix3 setToRotationRad(float radians) {
        float cos = (float) Math.cos((double) radians);
        float sin = (float) Math.sin((double) radians);
        float[] val = this.val;
        val[0] = cos;
        val[1] = sin;
        val[2] = 0.0f;
        val[3] = -sin;
        val[4] = cos;
        val[5] = 0.0f;
        val[6] = 0.0f;
        val[7] = 0.0f;
        val[8] = 1.0f;
        return this;
    }

    public Matrix3 setToRotation(Vector3 axis, float degrees) {
        return setToRotation(axis, MathUtils.cosDeg(degrees), MathUtils.sinDeg(degrees));
    }

    public Matrix3 setToRotation(Vector3 axis, float cos, float sin) {
        float[] val = this.val;
        float oc = 1.0f - cos;
        val[0] = ((axis.f107x * oc) * axis.f107x) + cos;
        val[1] = ((axis.f107x * oc) * axis.f108y) - (axis.f109z * sin);
        val[2] = ((axis.f109z * oc) * axis.f107x) + (axis.f108y * sin);
        val[3] = ((axis.f107x * oc) * axis.f108y) + (axis.f109z * sin);
        val[4] = ((axis.f108y * oc) * axis.f108y) + cos;
        val[5] = ((axis.f108y * oc) * axis.f109z) - (axis.f107x * sin);
        val[6] = ((axis.f109z * oc) * axis.f107x) - (axis.f108y * sin);
        val[7] = ((axis.f108y * oc) * axis.f109z) + (axis.f107x * sin);
        val[8] = ((axis.f109z * oc) * axis.f109z) + cos;
        return this;
    }

    public Matrix3 setToTranslation(float x, float y) {
        float[] val = this.val;
        val[0] = 1.0f;
        val[1] = 0.0f;
        val[2] = 0.0f;
        val[3] = 0.0f;
        val[4] = 1.0f;
        val[5] = 0.0f;
        val[6] = x;
        val[7] = y;
        val[8] = 1.0f;
        return this;
    }

    public Matrix3 setToTranslation(Vector2 translation) {
        float[] val = this.val;
        val[0] = 1.0f;
        val[1] = 0.0f;
        val[2] = 0.0f;
        val[3] = 0.0f;
        val[4] = 1.0f;
        val[5] = 0.0f;
        val[6] = translation.f102x;
        val[7] = translation.f103y;
        val[8] = 1.0f;
        return this;
    }

    public Matrix3 setToScaling(float scaleX, float scaleY) {
        float[] val = this.val;
        val[0] = scaleX;
        val[1] = 0.0f;
        val[2] = 0.0f;
        val[3] = 0.0f;
        val[4] = scaleY;
        val[5] = 0.0f;
        val[6] = 0.0f;
        val[7] = 0.0f;
        val[8] = 1.0f;
        return this;
    }

    public Matrix3 setToScaling(Vector2 scale) {
        float[] val = this.val;
        val[0] = scale.f102x;
        val[1] = 0.0f;
        val[2] = 0.0f;
        val[3] = 0.0f;
        val[4] = scale.f103y;
        val[5] = 0.0f;
        val[6] = 0.0f;
        val[7] = 0.0f;
        val[8] = 1.0f;
        return this;
    }

    public String toString() {
        float[] val = this.val;
        return "[" + val[0] + "|" + val[3] + "|" + val[6] + "]\n" + "[" + val[1] + "|" + val[4] + "|" + val[7] + "]\n" + "[" + val[2] + "|" + val[5] + "|" + val[8] + "]";
    }

    public float det() {
        float[] val = this.val;
        return ((((((val[0] * val[4]) * val[8]) + ((val[3] * val[7]) * val[2])) + ((val[6] * val[1]) * val[5])) - ((val[0] * val[7]) * val[5])) - ((val[3] * val[1]) * val[8])) - ((val[6] * val[4]) * val[2]);
    }

    public Matrix3 inv() {
        float det = det();
        if (det == 0.0f) {
            throw new GdxRuntimeException("Can't invert a singular matrix");
        }
        float inv_det = 1.0f / det;
        float[] tmp = this.tmp;
        float[] val = this.val;
        tmp[0] = (val[4] * val[8]) - (val[5] * val[7]);
        tmp[1] = (val[2] * val[7]) - (val[1] * val[8]);
        tmp[2] = (val[1] * val[5]) - (val[2] * val[4]);
        tmp[3] = (val[5] * val[6]) - (val[3] * val[8]);
        tmp[4] = (val[0] * val[8]) - (val[2] * val[6]);
        tmp[5] = (val[2] * val[3]) - (val[0] * val[5]);
        tmp[6] = (val[3] * val[7]) - (val[4] * val[6]);
        tmp[7] = (val[1] * val[6]) - (val[0] * val[7]);
        tmp[8] = (val[0] * val[4]) - (val[1] * val[3]);
        val[0] = tmp[0] * inv_det;
        val[1] = tmp[1] * inv_det;
        val[2] = tmp[2] * inv_det;
        val[3] = tmp[3] * inv_det;
        val[4] = tmp[4] * inv_det;
        val[5] = tmp[5] * inv_det;
        val[6] = tmp[6] * inv_det;
        val[7] = tmp[7] * inv_det;
        val[8] = tmp[8] * inv_det;
        return this;
    }

    public Matrix3 set(Matrix3 mat) {
        System.arraycopy(mat.val, 0, this.val, 0, this.val.length);
        return this;
    }

    public Matrix3 set(Affine2 affine) {
        float[] val = this.val;
        val[0] = affine.m00;
        val[1] = affine.m10;
        val[2] = 0.0f;
        val[3] = affine.m01;
        val[4] = affine.m11;
        val[5] = 0.0f;
        val[6] = affine.m02;
        val[7] = affine.m12;
        val[8] = 1.0f;
        return this;
    }

    public Matrix3 set(Matrix4 mat) {
        float[] val = this.val;
        val[0] = mat.val[0];
        val[1] = mat.val[1];
        val[2] = mat.val[2];
        val[3] = mat.val[4];
        val[4] = mat.val[5];
        val[5] = mat.val[6];
        val[6] = mat.val[8];
        val[7] = mat.val[9];
        val[8] = mat.val[10];
        return this;
    }

    public Matrix3 set(float[] values) {
        System.arraycopy(values, 0, this.val, 0, this.val.length);
        return this;
    }

    public Matrix3 trn(Vector2 vector) {
        float[] fArr = this.val;
        fArr[6] = fArr[6] + vector.f102x;
        fArr = this.val;
        fArr[7] = fArr[7] + vector.f103y;
        return this;
    }

    public Matrix3 trn(float x, float y) {
        float[] fArr = this.val;
        fArr[6] = fArr[6] + x;
        fArr = this.val;
        fArr[7] = fArr[7] + y;
        return this;
    }

    public Matrix3 trn(Vector3 vector) {
        float[] fArr = this.val;
        fArr[6] = fArr[6] + vector.f107x;
        fArr = this.val;
        fArr[7] = fArr[7] + vector.f108y;
        return this;
    }

    public Matrix3 translate(float x, float y) {
        float[] val = this.val;
        this.tmp[0] = 1.0f;
        this.tmp[1] = 0.0f;
        this.tmp[2] = 0.0f;
        this.tmp[3] = 0.0f;
        this.tmp[4] = 1.0f;
        this.tmp[5] = 0.0f;
        this.tmp[6] = x;
        this.tmp[7] = y;
        this.tmp[8] = 1.0f;
        mul(val, this.tmp);
        return this;
    }

    public Matrix3 translate(Vector2 translation) {
        float[] val = this.val;
        this.tmp[0] = 1.0f;
        this.tmp[1] = 0.0f;
        this.tmp[2] = 0.0f;
        this.tmp[3] = 0.0f;
        this.tmp[4] = 1.0f;
        this.tmp[5] = 0.0f;
        this.tmp[6] = translation.f102x;
        this.tmp[7] = translation.f103y;
        this.tmp[8] = 1.0f;
        mul(val, this.tmp);
        return this;
    }

    public Matrix3 rotate(float degrees) {
        return rotateRad(0.017453292f * degrees);
    }

    public Matrix3 rotateRad(float radians) {
        if (radians != 0.0f) {
            float cos = (float) Math.cos((double) radians);
            float sin = (float) Math.sin((double) radians);
            float[] tmp = this.tmp;
            tmp[0] = cos;
            tmp[1] = sin;
            tmp[2] = 0.0f;
            tmp[3] = -sin;
            tmp[4] = cos;
            tmp[5] = 0.0f;
            tmp[6] = 0.0f;
            tmp[7] = 0.0f;
            tmp[8] = 1.0f;
            mul(this.val, tmp);
        }
        return this;
    }

    public Matrix3 scale(float scaleX, float scaleY) {
        float[] tmp = this.tmp;
        tmp[0] = scaleX;
        tmp[1] = 0.0f;
        tmp[2] = 0.0f;
        tmp[3] = 0.0f;
        tmp[4] = scaleY;
        tmp[5] = 0.0f;
        tmp[6] = 0.0f;
        tmp[7] = 0.0f;
        tmp[8] = 1.0f;
        mul(this.val, tmp);
        return this;
    }

    public Matrix3 scale(Vector2 scale) {
        float[] tmp = this.tmp;
        tmp[0] = scale.f102x;
        tmp[1] = 0.0f;
        tmp[2] = 0.0f;
        tmp[3] = 0.0f;
        tmp[4] = scale.f103y;
        tmp[5] = 0.0f;
        tmp[6] = 0.0f;
        tmp[7] = 0.0f;
        tmp[8] = 1.0f;
        mul(this.val, tmp);
        return this;
    }

    public float[] getValues() {
        return this.val;
    }

    public Vector2 getTranslation(Vector2 position) {
        position.f102x = this.val[6];
        position.f103y = this.val[7];
        return position;
    }

    public Vector2 getScale(Vector2 scale) {
        float[] val = this.val;
        scale.f102x = (float) Math.sqrt((double) ((val[0] * val[0]) + (val[3] * val[3])));
        scale.f103y = (float) Math.sqrt((double) ((val[1] * val[1]) + (val[4] * val[4])));
        return scale;
    }

    public float getRotation() {
        return 57.295776f * ((float) Math.atan2((double) this.val[1], (double) this.val[0]));
    }

    public float getRotationRad() {
        return (float) Math.atan2((double) this.val[1], (double) this.val[0]);
    }

    public Matrix3 scl(float scale) {
        float[] fArr = this.val;
        fArr[0] = fArr[0] * scale;
        fArr = this.val;
        fArr[4] = fArr[4] * scale;
        return this;
    }

    public Matrix3 scl(Vector2 scale) {
        float[] fArr = this.val;
        fArr[0] = fArr[0] * scale.f102x;
        fArr = this.val;
        fArr[4] = fArr[4] * scale.f103y;
        return this;
    }

    public Matrix3 scl(Vector3 scale) {
        float[] fArr = this.val;
        fArr[0] = fArr[0] * scale.f107x;
        fArr = this.val;
        fArr[4] = fArr[4] * scale.f108y;
        return this;
    }

    public Matrix3 transpose() {
        float[] val = this.val;
        float v01 = val[1];
        float v02 = val[2];
        float v10 = val[3];
        float v12 = val[5];
        float v20 = val[6];
        float v21 = val[7];
        val[3] = v01;
        val[6] = v02;
        val[1] = v10;
        val[7] = v12;
        val[2] = v20;
        val[5] = v21;
        return this;
    }

    private static void mul(float[] mata, float[] matb) {
        float v01 = ((mata[0] * matb[3]) + (mata[3] * matb[4])) + (mata[6] * matb[5]);
        float v02 = ((mata[0] * matb[6]) + (mata[3] * matb[7])) + (mata[6] * matb[8]);
        float v10 = ((mata[1] * matb[0]) + (mata[4] * matb[1])) + (mata[7] * matb[2]);
        float v11 = ((mata[1] * matb[3]) + (mata[4] * matb[4])) + (mata[7] * matb[5]);
        float v12 = ((mata[1] * matb[6]) + (mata[4] * matb[7])) + (mata[7] * matb[8]);
        float v20 = ((mata[2] * matb[0]) + (mata[5] * matb[1])) + (mata[8] * matb[2]);
        float v21 = ((mata[2] * matb[3]) + (mata[5] * matb[4])) + (mata[8] * matb[5]);
        float v22 = ((mata[2] * matb[6]) + (mata[5] * matb[7])) + (mata[8] * matb[8]);
        mata[0] = ((mata[0] * matb[0]) + (mata[3] * matb[1])) + (mata[6] * matb[2]);
        mata[1] = v10;
        mata[2] = v20;
        mata[3] = v01;
        mata[4] = v11;
        mata[5] = v21;
        mata[6] = v02;
        mata[7] = v12;
        mata[8] = v22;
    }
}
