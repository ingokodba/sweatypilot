package com.badlogic.gdx.graphics;

import com.badlogic.gdx.utils.NumberUtils;

public class Color {
    public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f, 1.0f);
    public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f, 1.0f);
    public static final Color CLEAR = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    public static final Color CYAN = new Color(0.0f, 1.0f, 1.0f, 1.0f);
    public static final Color DARK_GRAY = new Color(0.25f, 0.25f, 0.25f, 1.0f);
    public static final Color GRAY = new Color(0.5f, 0.5f, 0.5f, 1.0f);
    public static final Color GREEN = new Color(0.0f, 1.0f, 0.0f, 1.0f);
    public static final Color LIGHT_GRAY = new Color(0.75f, 0.75f, 0.75f, 1.0f);
    public static final Color MAGENTA = new Color(1.0f, 0.0f, 1.0f, 1.0f);
    public static final Color MAROON = new Color(0.5f, 0.0f, 0.0f, 1.0f);
    public static final Color NAVY = new Color(0.0f, 0.0f, 0.5f, 1.0f);
    public static final Color OLIVE = new Color(0.5f, 0.5f, 0.0f, 1.0f);
    public static final Color ORANGE = new Color(1.0f, 0.78f, 0.0f, 1.0f);
    public static final Color PINK = new Color(1.0f, 0.68f, 0.68f, 1.0f);
    public static final Color PURPLE = new Color(0.5f, 0.0f, 0.5f, 1.0f);
    public static final Color RED = new Color(1.0f, 0.0f, 0.0f, 1.0f);
    public static final Color TEAL = new Color(0.0f, 0.5f, 0.5f, 1.0f);
    public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    public static final Color YELLOW = new Color(1.0f, 1.0f, 0.0f, 1.0f);
    /* renamed from: a */
    public float f36a;
    /* renamed from: b */
    public float f37b;
    /* renamed from: g */
    public float f38g;
    /* renamed from: r */
    public float f39r;

    public Color(int rgba8888) {
        rgba8888ToColor(this, rgba8888);
    }

    public Color(float r, float g, float b, float a) {
        this.f39r = r;
        this.f38g = g;
        this.f37b = b;
        this.f36a = a;
        clamp();
    }

    public Color(Color color) {
        set(color);
    }

    public Color set(Color color) {
        this.f39r = color.f39r;
        this.f38g = color.f38g;
        this.f37b = color.f37b;
        this.f36a = color.f36a;
        return this;
    }

    public Color mul(Color color) {
        this.f39r *= color.f39r;
        this.f38g *= color.f38g;
        this.f37b *= color.f37b;
        this.f36a *= color.f36a;
        return clamp();
    }

    public Color mul(float value) {
        this.f39r *= value;
        this.f38g *= value;
        this.f37b *= value;
        this.f36a *= value;
        return clamp();
    }

    public Color add(Color color) {
        this.f39r += color.f39r;
        this.f38g += color.f38g;
        this.f37b += color.f37b;
        this.f36a += color.f36a;
        return clamp();
    }

    public Color sub(Color color) {
        this.f39r -= color.f39r;
        this.f38g -= color.f38g;
        this.f37b -= color.f37b;
        this.f36a -= color.f36a;
        return clamp();
    }

    public Color clamp() {
        if (this.f39r < 0.0f) {
            this.f39r = 0.0f;
        } else if (this.f39r > 1.0f) {
            this.f39r = 1.0f;
        }
        if (this.f38g < 0.0f) {
            this.f38g = 0.0f;
        } else if (this.f38g > 1.0f) {
            this.f38g = 1.0f;
        }
        if (this.f37b < 0.0f) {
            this.f37b = 0.0f;
        } else if (this.f37b > 1.0f) {
            this.f37b = 1.0f;
        }
        if (this.f36a < 0.0f) {
            this.f36a = 0.0f;
        } else if (this.f36a > 1.0f) {
            this.f36a = 1.0f;
        }
        return this;
    }

    public Color set(float r, float g, float b, float a) {
        this.f39r = r;
        this.f38g = g;
        this.f37b = b;
        this.f36a = a;
        return clamp();
    }

    public Color set(int rgba) {
        rgba8888ToColor(this, rgba);
        return this;
    }

    public Color add(float r, float g, float b, float a) {
        this.f39r += r;
        this.f38g += g;
        this.f37b += b;
        this.f36a += a;
        return clamp();
    }

    public Color sub(float r, float g, float b, float a) {
        this.f39r -= r;
        this.f38g -= g;
        this.f37b -= b;
        this.f36a -= a;
        return clamp();
    }

    public Color mul(float r, float g, float b, float a) {
        this.f39r *= r;
        this.f38g *= g;
        this.f37b *= b;
        this.f36a *= a;
        return clamp();
    }

    public Color lerp(Color target, float t) {
        this.f39r += (target.f39r - this.f39r) * t;
        this.f38g += (target.f38g - this.f38g) * t;
        this.f37b += (target.f37b - this.f37b) * t;
        this.f36a += (target.f36a - this.f36a) * t;
        return clamp();
    }

    public Color lerp(float r, float g, float b, float a, float t) {
        this.f39r += (r - this.f39r) * t;
        this.f38g += (g - this.f38g) * t;
        this.f37b += (b - this.f37b) * t;
        this.f36a += (a - this.f36a) * t;
        return clamp();
    }

    public Color premultiplyAlpha() {
        this.f39r *= this.f36a;
        this.f38g *= this.f36a;
        this.f37b *= this.f36a;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (toIntBits() != ((Color) o).toIntBits()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result;
        int floatToIntBits;
        int i = 0;
        if (this.f39r != 0.0f) {
            result = NumberUtils.floatToIntBits(this.f39r);
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.f38g != 0.0f) {
            floatToIntBits = NumberUtils.floatToIntBits(this.f38g);
        } else {
            floatToIntBits = 0;
        }
        i2 = (i2 + floatToIntBits) * 31;
        if (this.f37b != 0.0f) {
            floatToIntBits = NumberUtils.floatToIntBits(this.f37b);
        } else {
            floatToIntBits = 0;
        }
        floatToIntBits = (i2 + floatToIntBits) * 31;
        if (this.f36a != 0.0f) {
            i = NumberUtils.floatToIntBits(this.f36a);
        }
        return floatToIntBits + i;
    }

    public float toFloatBits() {
        return NumberUtils.intToFloatColor((((((int) (this.f36a * 255.0f)) << 24) | (((int) (this.f37b * 255.0f)) << 16)) | (((int) (this.f38g * 255.0f)) << 8)) | ((int) (this.f39r * 255.0f)));
    }

    public int toIntBits() {
        return (((((int) (this.f36a * 255.0f)) << 24) | (((int) (this.f37b * 255.0f)) << 16)) | (((int) (this.f38g * 255.0f)) << 8)) | ((int) (this.f39r * 255.0f));
    }

    public String toString() {
        String value = Integer.toHexString((((((int) (this.f39r * 255.0f)) << 24) | (((int) (this.f38g * 255.0f)) << 16)) | (((int) (this.f37b * 255.0f)) << 8)) | ((int) (this.f36a * 255.0f)));
        while (value.length() < 8) {
            value = "0" + value;
        }
        return value;
    }

    public static Color valueOf(String hex) {
        return new Color(((float) Integer.valueOf(hex.substring(0, 2), 16).intValue()) / 255.0f, ((float) Integer.valueOf(hex.substring(2, 4), 16).intValue()) / 255.0f, ((float) Integer.valueOf(hex.substring(4, 6), 16).intValue()) / 255.0f, ((float) (hex.length() != 8 ? 255 : Integer.valueOf(hex.substring(6, 8), 16).intValue())) / 255.0f);
    }

    public static float toFloatBits(int r, int g, int b, int a) {
        return NumberUtils.intToFloatColor((((a << 24) | (b << 16)) | (g << 8)) | r);
    }

    public static float toFloatBits(float r, float g, float b, float a) {
        return NumberUtils.intToFloatColor((((((int) (255.0f * a)) << 24) | (((int) (255.0f * b)) << 16)) | (((int) (255.0f * g)) << 8)) | ((int) (255.0f * r)));
    }

    public static int toIntBits(int r, int g, int b, int a) {
        return (((a << 24) | (b << 16)) | (g << 8)) | r;
    }

    public static int alpha(float alpha) {
        return (int) (255.0f * alpha);
    }

    public static int luminanceAlpha(float luminance, float alpha) {
        return (((int) (luminance * 255.0f)) << 8) | ((int) (255.0f * alpha));
    }

    public static int rgb565(float r, float g, float b) {
        return ((((int) (r * 31.0f)) << 11) | (((int) (63.0f * g)) << 5)) | ((int) (b * 31.0f));
    }

    public static int rgba4444(float r, float g, float b, float a) {
        return (((((int) (r * 15.0f)) << 12) | (((int) (g * 15.0f)) << 8)) | (((int) (b * 15.0f)) << 4)) | ((int) (a * 15.0f));
    }

    public static int rgb888(float r, float g, float b) {
        return ((((int) (r * 255.0f)) << 16) | (((int) (g * 255.0f)) << 8)) | ((int) (b * 255.0f));
    }

    public static int rgba8888(float r, float g, float b, float a) {
        return (((((int) (r * 255.0f)) << 24) | (((int) (g * 255.0f)) << 16)) | (((int) (b * 255.0f)) << 8)) | ((int) (a * 255.0f));
    }

    public static int argb8888(float a, float r, float g, float b) {
        return (((((int) (a * 255.0f)) << 24) | (((int) (r * 255.0f)) << 16)) | (((int) (g * 255.0f)) << 8)) | ((int) (b * 255.0f));
    }

    public static int rgb565(Color color) {
        return ((((int) (color.f39r * 31.0f)) << 11) | (((int) (color.f38g * 63.0f)) << 5)) | ((int) (color.f37b * 31.0f));
    }

    public static int rgba4444(Color color) {
        return (((((int) (color.f39r * 15.0f)) << 12) | (((int) (color.f38g * 15.0f)) << 8)) | (((int) (color.f37b * 15.0f)) << 4)) | ((int) (color.f36a * 15.0f));
    }

    public static int rgb888(Color color) {
        return ((((int) (color.f39r * 255.0f)) << 16) | (((int) (color.f38g * 255.0f)) << 8)) | ((int) (color.f37b * 255.0f));
    }

    public static int rgba8888(Color color) {
        return (((((int) (color.f39r * 255.0f)) << 24) | (((int) (color.f38g * 255.0f)) << 16)) | (((int) (color.f37b * 255.0f)) << 8)) | ((int) (color.f36a * 255.0f));
    }

    public static int argb8888(Color color) {
        return (((((int) (color.f36a * 255.0f)) << 24) | (((int) (color.f39r * 255.0f)) << 16)) | (((int) (color.f38g * 255.0f)) << 8)) | ((int) (color.f37b * 255.0f));
    }

    public static void rgb565ToColor(Color color, int value) {
        color.f39r = ((float) ((63488 & value) >>> 11)) / 31.0f;
        color.f38g = ((float) ((value & 2016) >>> 5)) / 63.0f;
        color.f37b = ((float) ((value & 31) >>> 0)) / 31.0f;
    }

    public static void rgba4444ToColor(Color color, int value) {
        color.f39r = ((float) ((61440 & value) >>> 12)) / 15.0f;
        color.f38g = ((float) ((value & 3840) >>> 8)) / 15.0f;
        color.f37b = ((float) ((value & 240) >>> 4)) / 15.0f;
        color.f36a = ((float) (value & 15)) / 15.0f;
    }

    public static void rgb888ToColor(Color color, int value) {
        color.f39r = ((float) ((16711680 & value) >>> 16)) / 255.0f;
        color.f38g = ((float) ((65280 & value) >>> 8)) / 255.0f;
        color.f37b = ((float) (value & 255)) / 255.0f;
    }

    public static void rgba8888ToColor(Color color, int value) {
        color.f39r = ((float) ((-16777216 & value) >>> 24)) / 255.0f;
        color.f38g = ((float) ((16711680 & value) >>> 16)) / 255.0f;
        color.f37b = ((float) ((65280 & value) >>> 8)) / 255.0f;
        color.f36a = ((float) (value & 255)) / 255.0f;
    }

    public static void argb8888ToColor(Color color, int value) {
        color.f36a = ((float) ((-16777216 & value) >>> 24)) / 255.0f;
        color.f39r = ((float) ((16711680 & value) >>> 16)) / 255.0f;
        color.f38g = ((float) ((65280 & value) >>> 8)) / 255.0f;
        color.f37b = ((float) (value & 255)) / 255.0f;
    }

    public Color cpy() {
        return new Color(this);
    }
}
