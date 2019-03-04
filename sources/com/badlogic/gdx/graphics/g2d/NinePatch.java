package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class NinePatch {
    public static final int BOTTOM_CENTER = 7;
    public static final int BOTTOM_LEFT = 6;
    public static final int BOTTOM_RIGHT = 8;
    public static final int MIDDLE_CENTER = 4;
    public static final int MIDDLE_LEFT = 3;
    public static final int MIDDLE_RIGHT = 5;
    public static final int TOP_CENTER = 1;
    public static final int TOP_LEFT = 0;
    public static final int TOP_RIGHT = 2;
    private static final Color tmpDrawColor = new Color();
    private int bottomCenter;
    private float bottomHeight;
    private int bottomLeft;
    private int bottomRight;
    private final Color color;
    private int idx;
    private float leftWidth;
    private int middleCenter;
    private float middleHeight;
    private int middleLeft;
    private int middleRight;
    private float middleWidth;
    private int padBottom;
    private int padLeft;
    private int padRight;
    private int padTop;
    private float rightWidth;
    private Texture texture;
    private int topCenter;
    private float topHeight;
    private int topLeft;
    private int topRight;
    private float[] vertices;

    public NinePatch(Texture texture, int left, int right, int top, int bottom) {
        this(new TextureRegion(texture), left, right, top, bottom);
    }

    public NinePatch(TextureRegion region, int left, int right, int top, int bottom) {
        this.bottomLeft = -1;
        this.bottomCenter = -1;
        this.bottomRight = -1;
        this.middleLeft = -1;
        this.middleCenter = -1;
        this.middleRight = -1;
        this.topLeft = -1;
        this.topCenter = -1;
        this.topRight = -1;
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1;
        this.padRight = -1;
        this.padTop = -1;
        this.padBottom = -1;
        if (region == null) {
            throw new IllegalArgumentException("region cannot be null.");
        }
        int middleWidth = (region.getRegionWidth() - left) - right;
        int middleHeight = (region.getRegionHeight() - top) - bottom;
        TextureRegion[] patches = new TextureRegion[9];
        if (top > 0) {
            if (left > 0) {
                patches[0] = new TextureRegion(region, 0, 0, left, top);
            }
            if (middleWidth > 0) {
                patches[1] = new TextureRegion(region, left, 0, middleWidth, top);
            }
            if (right > 0) {
                patches[2] = new TextureRegion(region, left + middleWidth, 0, right, top);
            }
        }
        if (middleHeight > 0) {
            if (left > 0) {
                patches[3] = new TextureRegion(region, 0, top, left, middleHeight);
            }
            if (middleWidth > 0) {
                patches[4] = new TextureRegion(region, left, top, middleWidth, middleHeight);
            }
            if (right > 0) {
                patches[5] = new TextureRegion(region, left + middleWidth, top, right, middleHeight);
            }
        }
        if (bottom > 0) {
            if (left > 0) {
                patches[6] = new TextureRegion(region, 0, top + middleHeight, left, bottom);
            }
            if (middleWidth > 0) {
                patches[7] = new TextureRegion(region, left, top + middleHeight, middleWidth, bottom);
            }
            if (right > 0) {
                patches[8] = new TextureRegion(region, left + middleWidth, top + middleHeight, right, bottom);
            }
        }
        if (left == 0 && middleWidth == 0) {
            patches[1] = patches[2];
            patches[4] = patches[5];
            patches[7] = patches[8];
            patches[2] = null;
            patches[5] = null;
            patches[8] = null;
        }
        if (top == 0 && middleHeight == 0) {
            patches[3] = patches[6];
            patches[4] = patches[7];
            patches[5] = patches[8];
            patches[6] = null;
            patches[7] = null;
            patches[8] = null;
        }
        load(patches);
    }

    public NinePatch(Texture texture, Color color) {
        this(texture);
        setColor(color);
    }

    public NinePatch(Texture texture) {
        this(new TextureRegion(texture));
    }

    public NinePatch(TextureRegion region, Color color) {
        this(region);
        setColor(color);
    }

    public NinePatch(TextureRegion region) {
        this.bottomLeft = -1;
        this.bottomCenter = -1;
        this.bottomRight = -1;
        this.middleLeft = -1;
        this.middleCenter = -1;
        this.middleRight = -1;
        this.topLeft = -1;
        this.topCenter = -1;
        this.topRight = -1;
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1;
        this.padRight = -1;
        this.padTop = -1;
        this.padBottom = -1;
        load(new TextureRegion[]{null, null, null, null, region, null, null, null, null});
    }

    public NinePatch(TextureRegion... patches) {
        this.bottomLeft = -1;
        this.bottomCenter = -1;
        this.bottomRight = -1;
        this.middleLeft = -1;
        this.middleCenter = -1;
        this.middleRight = -1;
        this.topLeft = -1;
        this.topCenter = -1;
        this.topRight = -1;
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1;
        this.padRight = -1;
        this.padTop = -1;
        this.padBottom = -1;
        if (patches == null || patches.length != 9) {
            throw new IllegalArgumentException("NinePatch needs nine TextureRegions");
        }
        load(patches);
        float leftWidth = getLeftWidth();
        if ((patches[0] == null || ((float) patches[0].getRegionWidth()) == leftWidth) && ((patches[3] == null || ((float) patches[3].getRegionWidth()) == leftWidth) && (patches[6] == null || ((float) patches[6].getRegionWidth()) == leftWidth))) {
            float rightWidth = getRightWidth();
            if ((patches[2] == null || ((float) patches[2].getRegionWidth()) == rightWidth) && ((patches[5] == null || ((float) patches[5].getRegionWidth()) == rightWidth) && (patches[8] == null || ((float) patches[8].getRegionWidth()) == rightWidth))) {
                float bottomHeight = getBottomHeight();
                if ((patches[6] == null || ((float) patches[6].getRegionHeight()) == bottomHeight) && ((patches[7] == null || ((float) patches[7].getRegionHeight()) == bottomHeight) && (patches[8] == null || ((float) patches[8].getRegionHeight()) == bottomHeight))) {
                    float topHeight = getTopHeight();
                    if ((patches[0] != null && ((float) patches[0].getRegionHeight()) != topHeight) || ((patches[1] != null && ((float) patches[1].getRegionHeight()) != topHeight) || (patches[2] != null && ((float) patches[2].getRegionHeight()) != topHeight))) {
                        throw new GdxRuntimeException("Top side patches must have the same height");
                    }
                    return;
                }
                throw new GdxRuntimeException("Bottom side patches must have the same height");
            }
            throw new GdxRuntimeException("Right side patches must have the same width");
        }
        throw new GdxRuntimeException("Left side patches must have the same width");
    }

    public NinePatch(NinePatch ninePatch) {
        this(ninePatch, ninePatch.color);
    }

    public NinePatch(NinePatch ninePatch, Color color) {
        this.bottomLeft = -1;
        this.bottomCenter = -1;
        this.bottomRight = -1;
        this.middleLeft = -1;
        this.middleCenter = -1;
        this.middleRight = -1;
        this.topLeft = -1;
        this.topCenter = -1;
        this.topRight = -1;
        this.vertices = new float[180];
        this.color = new Color(Color.WHITE);
        this.padLeft = -1;
        this.padRight = -1;
        this.padTop = -1;
        this.padBottom = -1;
        this.texture = ninePatch.texture;
        this.bottomLeft = ninePatch.bottomLeft;
        this.bottomCenter = ninePatch.bottomCenter;
        this.bottomRight = ninePatch.bottomRight;
        this.middleLeft = ninePatch.middleLeft;
        this.middleCenter = ninePatch.middleCenter;
        this.middleRight = ninePatch.middleRight;
        this.topLeft = ninePatch.topLeft;
        this.topCenter = ninePatch.topCenter;
        this.topRight = ninePatch.topRight;
        this.leftWidth = ninePatch.leftWidth;
        this.rightWidth = ninePatch.rightWidth;
        this.middleWidth = ninePatch.middleWidth;
        this.middleHeight = ninePatch.middleHeight;
        this.topHeight = ninePatch.topHeight;
        this.bottomHeight = ninePatch.bottomHeight;
        this.padLeft = ninePatch.padLeft;
        this.padTop = ninePatch.padTop;
        this.padBottom = ninePatch.padBottom;
        this.padRight = ninePatch.padRight;
        this.vertices = new float[ninePatch.vertices.length];
        System.arraycopy(ninePatch.vertices, 0, this.vertices, 0, ninePatch.vertices.length);
        this.idx = ninePatch.idx;
        this.color.set(color);
    }

    private void load(TextureRegion[] patches) {
        float color = Color.WHITE.toFloatBits();
        if (patches[6] != null) {
            this.bottomLeft = add(patches[6], color, false, false);
            this.leftWidth = (float) patches[6].getRegionWidth();
            this.bottomHeight = (float) patches[6].getRegionHeight();
        }
        if (patches[7] != null) {
            this.bottomCenter = add(patches[7], color, true, false);
            this.middleWidth = Math.max(this.middleWidth, (float) patches[7].getRegionWidth());
            this.bottomHeight = Math.max(this.bottomHeight, (float) patches[7].getRegionHeight());
        }
        if (patches[8] != null) {
            this.bottomRight = add(patches[8], color, false, false);
            this.rightWidth = Math.max(this.rightWidth, (float) patches[8].getRegionWidth());
            this.bottomHeight = Math.max(this.bottomHeight, (float) patches[8].getRegionHeight());
        }
        if (patches[3] != null) {
            this.middleLeft = add(patches[3], color, false, true);
            this.leftWidth = Math.max(this.leftWidth, (float) patches[3].getRegionWidth());
            this.middleHeight = Math.max(this.middleHeight, (float) patches[3].getRegionHeight());
        }
        if (patches[4] != null) {
            this.middleCenter = add(patches[4], color, true, true);
            this.middleWidth = Math.max(this.middleWidth, (float) patches[4].getRegionWidth());
            this.middleHeight = Math.max(this.middleHeight, (float) patches[4].getRegionHeight());
        }
        if (patches[5] != null) {
            this.middleRight = add(patches[5], color, false, true);
            this.rightWidth = Math.max(this.rightWidth, (float) patches[5].getRegionWidth());
            this.middleHeight = Math.max(this.middleHeight, (float) patches[5].getRegionHeight());
        }
        if (patches[0] != null) {
            this.topLeft = add(patches[0], color, false, false);
            this.leftWidth = Math.max(this.leftWidth, (float) patches[0].getRegionWidth());
            this.topHeight = Math.max(this.topHeight, (float) patches[0].getRegionHeight());
        }
        if (patches[1] != null) {
            this.topCenter = add(patches[1], color, true, false);
            this.middleWidth = Math.max(this.middleWidth, (float) patches[1].getRegionWidth());
            this.topHeight = Math.max(this.topHeight, (float) patches[1].getRegionHeight());
        }
        if (patches[2] != null) {
            this.topRight = add(patches[2], color, false, false);
            this.rightWidth = Math.max(this.rightWidth, (float) patches[2].getRegionWidth());
            this.topHeight = Math.max(this.topHeight, (float) patches[2].getRegionHeight());
        }
        if (this.idx < this.vertices.length) {
            float[] newVertices = new float[this.idx];
            System.arraycopy(this.vertices, 0, newVertices, 0, this.idx);
            this.vertices = newVertices;
        }
    }

    private int add(TextureRegion region, float color, boolean isStretchW, boolean isStretchH) {
        if (this.texture == null) {
            this.texture = region.getTexture();
        } else if (this.texture != region.getTexture()) {
            throw new IllegalArgumentException("All regions must be from the same texture.");
        }
        float u = region.f51u;
        float v = region.v2;
        float u2 = region.u2;
        float v2 = region.f52v;
        if (isStretchW) {
            float halfTexelWidth = 0.5f / ((float) this.texture.getWidth());
            u += halfTexelWidth;
            u2 -= halfTexelWidth;
        }
        if (isStretchH) {
            float halfTexelHeight = 0.5f / ((float) this.texture.getHeight());
            v -= halfTexelHeight;
            v2 += halfTexelHeight;
        }
        float[] vertices = this.vertices;
        this.idx += 2;
        int i = this.idx;
        this.idx = i + 1;
        vertices[i] = color;
        i = this.idx;
        this.idx = i + 1;
        vertices[i] = u;
        vertices[this.idx] = v;
        this.idx += 3;
        i = this.idx;
        this.idx = i + 1;
        vertices[i] = color;
        i = this.idx;
        this.idx = i + 1;
        vertices[i] = u;
        vertices[this.idx] = v2;
        this.idx += 3;
        i = this.idx;
        this.idx = i + 1;
        vertices[i] = color;
        i = this.idx;
        this.idx = i + 1;
        vertices[i] = u2;
        vertices[this.idx] = v2;
        this.idx += 3;
        i = this.idx;
        this.idx = i + 1;
        vertices[i] = color;
        i = this.idx;
        this.idx = i + 1;
        vertices[i] = u2;
        i = this.idx;
        this.idx = i + 1;
        vertices[i] = v;
        return this.idx - 20;
    }

    private void set(int idx, float x, float y, float width, float height, float color) {
        float fx2 = x + width;
        float fy2 = y + height;
        float[] vertices = this.vertices;
        int idx2 = idx + 1;
        vertices[idx] = x;
        idx = idx2 + 1;
        vertices[idx2] = y;
        vertices[idx] = color;
        idx += 3;
        idx2 = idx + 1;
        vertices[idx] = x;
        idx = idx2 + 1;
        vertices[idx2] = fy2;
        vertices[idx] = color;
        idx += 3;
        idx2 = idx + 1;
        vertices[idx] = fx2;
        idx = idx2 + 1;
        vertices[idx2] = fy2;
        vertices[idx] = color;
        idx += 3;
        idx2 = idx + 1;
        vertices[idx] = fx2;
        idx = idx2 + 1;
        vertices[idx2] = y;
        vertices[idx] = color;
    }

    public void draw(Batch batch, float x, float y, float width, float height) {
        float centerColumnX = x + this.leftWidth;
        float rightColumnX = (x + width) - this.rightWidth;
        float middleRowY = y + this.bottomHeight;
        float topRowY = (y + height) - this.topHeight;
        float c = tmpDrawColor.set(this.color).mul(batch.getColor()).toFloatBits();
        if (this.bottomLeft != -1) {
            set(this.bottomLeft, x, y, centerColumnX - x, middleRowY - y, c);
        }
        if (this.bottomCenter != -1) {
            set(this.bottomCenter, centerColumnX, y, rightColumnX - centerColumnX, middleRowY - y, c);
        }
        if (this.bottomRight != -1) {
            set(this.bottomRight, rightColumnX, y, (x + width) - rightColumnX, middleRowY - y, c);
        }
        if (this.middleLeft != -1) {
            set(this.middleLeft, x, middleRowY, centerColumnX - x, topRowY - middleRowY, c);
        }
        if (this.middleCenter != -1) {
            set(this.middleCenter, centerColumnX, middleRowY, rightColumnX - centerColumnX, topRowY - middleRowY, c);
        }
        if (this.middleRight != -1) {
            set(this.middleRight, rightColumnX, middleRowY, (x + width) - rightColumnX, topRowY - middleRowY, c);
        }
        if (this.topLeft != -1) {
            float f = x;
            float f2 = topRowY;
            set(this.topLeft, f, f2, centerColumnX - x, (y + height) - topRowY, c);
        }
        if (this.topCenter != -1) {
            f = centerColumnX;
            f2 = topRowY;
            set(this.topCenter, f, f2, rightColumnX - centerColumnX, (y + height) - topRowY, c);
        }
        if (this.topRight != -1) {
            set(this.topRight, rightColumnX, topRowY, (x + width) - rightColumnX, (y + height) - topRowY, c);
        }
        batch.draw(this.texture, this.vertices, 0, this.idx);
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public Color getColor() {
        return this.color;
    }

    public float getLeftWidth() {
        return this.leftWidth;
    }

    public void setLeftWidth(float leftWidth) {
        this.leftWidth = leftWidth;
    }

    public float getRightWidth() {
        return this.rightWidth;
    }

    public void setRightWidth(float rightWidth) {
        this.rightWidth = rightWidth;
    }

    public float getTopHeight() {
        return this.topHeight;
    }

    public void setTopHeight(float topHeight) {
        this.topHeight = topHeight;
    }

    public float getBottomHeight() {
        return this.bottomHeight;
    }

    public void setBottomHeight(float bottomHeight) {
        this.bottomHeight = bottomHeight;
    }

    public float getMiddleWidth() {
        return this.middleWidth;
    }

    public void setMiddleWidth(float middleWidth) {
        this.middleWidth = middleWidth;
    }

    public float getMiddleHeight() {
        return this.middleHeight;
    }

    public void setMiddleHeight(float middleHeight) {
        this.middleHeight = middleHeight;
    }

    public float getTotalWidth() {
        return (this.leftWidth + this.middleWidth) + this.rightWidth;
    }

    public float getTotalHeight() {
        return (this.topHeight + this.middleHeight) + this.bottomHeight;
    }

    public void setPadding(int left, int right, int top, int bottom) {
        this.padLeft = left;
        this.padRight = right;
        this.padTop = top;
        this.padBottom = bottom;
    }

    public float getPadLeft() {
        if (this.padLeft == -1) {
            return getLeftWidth();
        }
        return (float) this.padLeft;
    }

    public void setPadLeft(int left) {
        this.padLeft = left;
    }

    public float getPadRight() {
        if (this.padRight == -1) {
            return getRightWidth();
        }
        return (float) this.padRight;
    }

    public void setPadRight(int right) {
        this.padRight = right;
    }

    public float getPadTop() {
        if (this.padTop == -1) {
            return getTopHeight();
        }
        return (float) this.padTop;
    }

    public void setPadTop(int top) {
        this.padTop = top;
    }

    public float getPadBottom() {
        if (this.padBottom == -1) {
            return getBottomHeight();
        }
        return (float) this.padBottom;
    }

    public void setPadBottom(int bottom) {
        this.padBottom = bottom;
    }

    public void scale(float scaleX, float scaleY) {
        this.leftWidth *= scaleX;
        this.rightWidth *= scaleX;
        this.topHeight *= scaleY;
        this.bottomHeight *= scaleY;
        this.padLeft = (int) (((float) this.padLeft) * scaleX);
        this.padRight = (int) (((float) this.padRight) * scaleX);
        this.padTop = (int) (((float) this.padTop) * scaleY);
        this.padBottom = (int) (((float) this.padBottom) * scaleY);
    }

    public Texture getTexture() {
        return this.texture;
    }
}
