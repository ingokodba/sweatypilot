package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.NumberUtils;

public class Decal {
    public static final int C1 = 3;
    public static final int C2 = 9;
    public static final int C3 = 15;
    public static final int C4 = 21;
    public static final int SIZE = 24;
    public static final int U1 = 4;
    public static final int U2 = 10;
    public static final int U3 = 16;
    public static final int U4 = 22;
    public static final int V1 = 5;
    public static final int V2 = 11;
    public static final int V3 = 17;
    public static final int V4 = 23;
    private static final int VERTEX_SIZE = 6;
    public static final int X1 = 0;
    public static final int X2 = 6;
    public static final int X3 = 12;
    public static final int X4 = 18;
    public static final int Y1 = 1;
    public static final int Y2 = 7;
    public static final int Y3 = 13;
    public static final int Y4 = 19;
    public static final int Z1 = 2;
    public static final int Z2 = 8;
    public static final int Z3 = 14;
    public static final int Z4 = 20;
    static final Vector3 dir = new Vector3();
    protected static Quaternion rotator = new Quaternion(0.0f, 0.0f, 0.0f, 0.0f);
    private static Vector3 tmp = new Vector3();
    private static Vector3 tmp2 = new Vector3();
    protected Color color = new Color();
    protected Vector2 dimensions = new Vector2();
    protected DecalMaterial material = new DecalMaterial();
    protected Vector3 position = new Vector3();
    protected Quaternion rotation = new Quaternion();
    protected Vector2 scale = new Vector2(1.0f, 1.0f);
    public Vector2 transformationOffset = null;
    protected boolean updated = false;
    public int value;
    protected float[] vertices = new float[24];

    public void setColor(float r, float g, float b, float a) {
        this.color.set(r, g, b, a);
        float color = NumberUtils.intToFloatColor((((((int) (255.0f * a)) << 24) | (((int) (255.0f * b)) << 16)) | (((int) (255.0f * g)) << 8)) | ((int) (255.0f * r)));
        this.vertices[3] = color;
        this.vertices[9] = color;
        this.vertices[15] = color;
        this.vertices[21] = color;
    }

    public void setColor(Color tint) {
        this.color.set(tint);
        float color = tint.toFloatBits();
        this.vertices[3] = color;
        this.vertices[9] = color;
        this.vertices[15] = color;
        this.vertices[21] = color;
    }

    public void setColor(float color) {
        this.color.set(NumberUtils.floatToIntColor(color));
        this.vertices[3] = color;
        this.vertices[9] = color;
        this.vertices[15] = color;
        this.vertices[21] = color;
    }

    public void setRotationX(float angle) {
        this.rotation.set(Vector3.f104X, angle);
        this.updated = false;
    }

    public void setRotationY(float angle) {
        this.rotation.set(Vector3.f105Y, angle);
        this.updated = false;
    }

    public void setRotationZ(float angle) {
        this.rotation.set(Vector3.f106Z, angle);
        this.updated = false;
    }

    public void rotateX(float angle) {
        rotator.set(Vector3.f104X, angle);
        this.rotation.mul(rotator);
        this.updated = false;
    }

    public void rotateY(float angle) {
        rotator.set(Vector3.f105Y, angle);
        this.rotation.mul(rotator);
        this.updated = false;
    }

    public void rotateZ(float angle) {
        rotator.set(Vector3.f106Z, angle);
        this.rotation.mul(rotator);
        this.updated = false;
    }

    public void setRotation(float yaw, float pitch, float roll) {
        this.rotation.setEulerAngles(yaw, pitch, roll);
        this.updated = false;
    }

    public void setRotation(Vector3 dir, Vector3 up) {
        tmp.set(up).crs(dir).nor();
        tmp2.set(dir).crs(tmp).nor();
        this.rotation.setFromAxes(tmp.f107x, tmp2.f107x, dir.f107x, tmp.f108y, tmp2.f108y, dir.f108y, tmp.f109z, tmp2.f109z, dir.f109z);
        this.updated = false;
    }

    public void setRotation(Quaternion q) {
        this.rotation.set(q);
        this.updated = false;
    }

    public Quaternion getRotation() {
        return this.rotation;
    }

    public void translateX(float units) {
        Vector3 vector3 = this.position;
        vector3.f107x += units;
        this.updated = false;
    }

    public void setX(float x) {
        this.position.f107x = x;
        this.updated = false;
    }

    public float getX() {
        return this.position.f107x;
    }

    public void translateY(float units) {
        Vector3 vector3 = this.position;
        vector3.f108y += units;
        this.updated = false;
    }

    public void setY(float y) {
        this.position.f108y = y;
        this.updated = false;
    }

    public float getY() {
        return this.position.f108y;
    }

    public void translateZ(float units) {
        Vector3 vector3 = this.position;
        vector3.f109z += units;
        this.updated = false;
    }

    public void setZ(float z) {
        this.position.f109z = z;
        this.updated = false;
    }

    public float getZ() {
        return this.position.f109z;
    }

    public void translate(float x, float y, float z) {
        this.position.add(x, y, z);
        this.updated = false;
    }

    public void translate(Vector3 trans) {
        this.position.add(trans);
        this.updated = false;
    }

    public void setPosition(float x, float y, float z) {
        this.position.set(x, y, z);
        this.updated = false;
    }

    public void setPosition(Vector3 pos) {
        this.position.set(pos);
        this.updated = false;
    }

    public Color getColor() {
        return this.color;
    }

    public Vector3 getPosition() {
        return this.position;
    }

    public void setScaleX(float scale) {
        this.scale.f102x = scale;
        this.updated = false;
    }

    public float getScaleX() {
        return this.scale.f102x;
    }

    public void setScaleY(float scale) {
        this.scale.f103y = scale;
        this.updated = false;
    }

    public float getScaleY() {
        return this.scale.f103y;
    }

    public void setScale(float scaleX, float scaleY) {
        this.scale.set(scaleX, scaleY);
        this.updated = false;
    }

    public void setScale(float scale) {
        this.scale.set(scale, scale);
        this.updated = false;
    }

    public void setWidth(float width) {
        this.dimensions.f102x = width;
        this.updated = false;
    }

    public float getWidth() {
        return this.dimensions.f102x;
    }

    public void setHeight(float height) {
        this.dimensions.f103y = height;
        this.updated = false;
    }

    public float getHeight() {
        return this.dimensions.f103y;
    }

    public void setDimensions(float width, float height) {
        this.dimensions.set(width, height);
        this.updated = false;
    }

    public float[] getVertices() {
        return this.vertices;
    }

    protected void update() {
        if (!this.updated) {
            resetVertices();
            transformVertices();
        }
    }

    protected void transformVertices() {
        float tx;
        float ty;
        if (this.transformationOffset != null) {
            tx = -this.transformationOffset.f102x;
            ty = -this.transformationOffset.f103y;
        } else {
            ty = 0.0f;
            tx = 0.0f;
        }
        float x = (this.vertices[0] + tx) * this.scale.f102x;
        float y = (this.vertices[1] + ty) * this.scale.f103y;
        float z = this.vertices[2];
        this.vertices[0] = ((this.rotation.f66w * x) + (this.rotation.f68y * z)) - (this.rotation.f69z * y);
        this.vertices[1] = ((this.rotation.f66w * y) + (this.rotation.f69z * x)) - (this.rotation.f67x * z);
        this.vertices[2] = ((this.rotation.f66w * z) + (this.rotation.f67x * y)) - (this.rotation.f68y * x);
        float w = (((-this.rotation.f67x) * x) - (this.rotation.f68y * y)) - (this.rotation.f69z * z);
        this.rotation.conjugate();
        x = this.vertices[0];
        y = this.vertices[1];
        z = this.vertices[2];
        this.vertices[0] = (((this.rotation.f67x * w) + (this.rotation.f66w * x)) + (this.rotation.f69z * y)) - (this.rotation.f68y * z);
        this.vertices[1] = (((this.rotation.f68y * w) + (this.rotation.f66w * y)) + (this.rotation.f67x * z)) - (this.rotation.f69z * x);
        this.vertices[2] = (((this.rotation.f69z * w) + (this.rotation.f66w * z)) + (this.rotation.f68y * x)) - (this.rotation.f67x * y);
        this.rotation.conjugate();
        float[] fArr = this.vertices;
        fArr[0] = fArr[0] + (this.position.f107x - tx);
        fArr = this.vertices;
        fArr[1] = fArr[1] + (this.position.f108y - ty);
        fArr = this.vertices;
        fArr[2] = fArr[2] + this.position.f109z;
        x = (this.vertices[6] + tx) * this.scale.f102x;
        y = (this.vertices[7] + ty) * this.scale.f103y;
        z = this.vertices[8];
        this.vertices[6] = ((this.rotation.f66w * x) + (this.rotation.f68y * z)) - (this.rotation.f69z * y);
        this.vertices[7] = ((this.rotation.f66w * y) + (this.rotation.f69z * x)) - (this.rotation.f67x * z);
        this.vertices[8] = ((this.rotation.f66w * z) + (this.rotation.f67x * y)) - (this.rotation.f68y * x);
        w = (((-this.rotation.f67x) * x) - (this.rotation.f68y * y)) - (this.rotation.f69z * z);
        this.rotation.conjugate();
        x = this.vertices[6];
        y = this.vertices[7];
        z = this.vertices[8];
        this.vertices[6] = (((this.rotation.f67x * w) + (this.rotation.f66w * x)) + (this.rotation.f69z * y)) - (this.rotation.f68y * z);
        this.vertices[7] = (((this.rotation.f68y * w) + (this.rotation.f66w * y)) + (this.rotation.f67x * z)) - (this.rotation.f69z * x);
        this.vertices[8] = (((this.rotation.f69z * w) + (this.rotation.f66w * z)) + (this.rotation.f68y * x)) - (this.rotation.f67x * y);
        this.rotation.conjugate();
        fArr = this.vertices;
        fArr[6] = fArr[6] + (this.position.f107x - tx);
        fArr = this.vertices;
        fArr[7] = fArr[7] + (this.position.f108y - ty);
        fArr = this.vertices;
        fArr[8] = fArr[8] + this.position.f109z;
        x = (this.vertices[12] + tx) * this.scale.f102x;
        y = (this.vertices[13] + ty) * this.scale.f103y;
        z = this.vertices[14];
        this.vertices[12] = ((this.rotation.f66w * x) + (this.rotation.f68y * z)) - (this.rotation.f69z * y);
        this.vertices[13] = ((this.rotation.f66w * y) + (this.rotation.f69z * x)) - (this.rotation.f67x * z);
        this.vertices[14] = ((this.rotation.f66w * z) + (this.rotation.f67x * y)) - (this.rotation.f68y * x);
        w = (((-this.rotation.f67x) * x) - (this.rotation.f68y * y)) - (this.rotation.f69z * z);
        this.rotation.conjugate();
        x = this.vertices[12];
        y = this.vertices[13];
        z = this.vertices[14];
        this.vertices[12] = (((this.rotation.f67x * w) + (this.rotation.f66w * x)) + (this.rotation.f69z * y)) - (this.rotation.f68y * z);
        this.vertices[13] = (((this.rotation.f68y * w) + (this.rotation.f66w * y)) + (this.rotation.f67x * z)) - (this.rotation.f69z * x);
        this.vertices[14] = (((this.rotation.f69z * w) + (this.rotation.f66w * z)) + (this.rotation.f68y * x)) - (this.rotation.f67x * y);
        this.rotation.conjugate();
        fArr = this.vertices;
        fArr[12] = fArr[12] + (this.position.f107x - tx);
        fArr = this.vertices;
        fArr[13] = fArr[13] + (this.position.f108y - ty);
        fArr = this.vertices;
        fArr[14] = fArr[14] + this.position.f109z;
        x = (this.vertices[18] + tx) * this.scale.f102x;
        y = (this.vertices[19] + ty) * this.scale.f103y;
        z = this.vertices[20];
        this.vertices[18] = ((this.rotation.f66w * x) + (this.rotation.f68y * z)) - (this.rotation.f69z * y);
        this.vertices[19] = ((this.rotation.f66w * y) + (this.rotation.f69z * x)) - (this.rotation.f67x * z);
        this.vertices[20] = ((this.rotation.f66w * z) + (this.rotation.f67x * y)) - (this.rotation.f68y * x);
        w = (((-this.rotation.f67x) * x) - (this.rotation.f68y * y)) - (this.rotation.f69z * z);
        this.rotation.conjugate();
        x = this.vertices[18];
        y = this.vertices[19];
        z = this.vertices[20];
        this.vertices[18] = (((this.rotation.f67x * w) + (this.rotation.f66w * x)) + (this.rotation.f69z * y)) - (this.rotation.f68y * z);
        this.vertices[19] = (((this.rotation.f68y * w) + (this.rotation.f66w * y)) + (this.rotation.f67x * z)) - (this.rotation.f69z * x);
        this.vertices[20] = (((this.rotation.f69z * w) + (this.rotation.f66w * z)) + (this.rotation.f68y * x)) - (this.rotation.f67x * y);
        this.rotation.conjugate();
        fArr = this.vertices;
        fArr[18] = fArr[18] + (this.position.f107x - tx);
        fArr = this.vertices;
        fArr[19] = fArr[19] + (this.position.f108y - ty);
        fArr = this.vertices;
        fArr[20] = fArr[20] + this.position.f109z;
        this.updated = true;
    }

    protected void resetVertices() {
        float left = (-this.dimensions.f102x) / 2.0f;
        float right = left + this.dimensions.f102x;
        float top = this.dimensions.f103y / 2.0f;
        float bottom = top - this.dimensions.f103y;
        this.vertices[0] = left;
        this.vertices[1] = top;
        this.vertices[2] = 0.0f;
        this.vertices[6] = right;
        this.vertices[7] = top;
        this.vertices[8] = 0.0f;
        this.vertices[12] = left;
        this.vertices[13] = bottom;
        this.vertices[14] = 0.0f;
        this.vertices[18] = right;
        this.vertices[19] = bottom;
        this.vertices[20] = 0.0f;
        this.updated = false;
    }

    protected void updateUVs() {
        TextureRegion tr = this.material.textureRegion;
        this.vertices[4] = tr.getU();
        this.vertices[5] = tr.getV();
        this.vertices[10] = tr.getU2();
        this.vertices[11] = tr.getV();
        this.vertices[16] = tr.getU();
        this.vertices[17] = tr.getV2();
        this.vertices[22] = tr.getU2();
        this.vertices[23] = tr.getV2();
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.material.textureRegion = textureRegion;
        updateUVs();
    }

    public TextureRegion getTextureRegion() {
        return this.material.textureRegion;
    }

    public void setBlending(int srcBlendFactor, int dstBlendFactor) {
        this.material.srcBlendFactor = srcBlendFactor;
        this.material.dstBlendFactor = dstBlendFactor;
    }

    public DecalMaterial getMaterial() {
        return this.material;
    }

    public void lookAt(Vector3 position, Vector3 up) {
        dir.set(position).sub(this.position).nor();
        setRotation(dir, up);
    }

    public static Decal newDecal(TextureRegion textureRegion) {
        return newDecal((float) textureRegion.getRegionWidth(), (float) textureRegion.getRegionHeight(), textureRegion, -1, -1);
    }

    public static Decal newDecal(TextureRegion textureRegion, boolean hasTransparency) {
        int i = -1;
        float regionWidth = (float) textureRegion.getRegionWidth();
        float regionHeight = (float) textureRegion.getRegionHeight();
        int i2 = hasTransparency ? GL20.GL_SRC_ALPHA : -1;
        if (hasTransparency) {
            i = GL20.GL_ONE_MINUS_SRC_ALPHA;
        }
        return newDecal(regionWidth, regionHeight, textureRegion, i2, i);
    }

    public static Decal newDecal(float width, float height, TextureRegion textureRegion) {
        return newDecal(width, height, textureRegion, -1, -1);
    }

    public static Decal newDecal(float width, float height, TextureRegion textureRegion, boolean hasTransparency) {
        int i = -1;
        int i2 = hasTransparency ? GL20.GL_SRC_ALPHA : -1;
        if (hasTransparency) {
            i = GL20.GL_ONE_MINUS_SRC_ALPHA;
        }
        return newDecal(width, height, textureRegion, i2, i);
    }

    public static Decal newDecal(float width, float height, TextureRegion textureRegion, int srcBlendFactor, int dstBlendFactor) {
        Decal decal = new Decal();
        decal.setTextureRegion(textureRegion);
        decal.setBlending(srcBlendFactor, dstBlendFactor);
        decal.dimensions.f102x = width;
        decal.dimensions.f103y = height;
        decal.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        return decal;
    }
}
