package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.BitmapFont.Glyph;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class GlyphLayout implements Poolable {
    private static final Array<Color> colorStack = new Array(4);
    public float height;
    public final Array<GlyphRun> runs = new Array();
    public float width;

    public static class GlyphRun implements Poolable {
        public final Color color = new Color();
        public final Array<Glyph> glyphs = new Array();
        public float width;
        /* renamed from: x */
        public float f82x;
        public final FloatArray xAdvances = new FloatArray();
        /* renamed from: y */
        public float f83y;

        public void reset() {
            this.glyphs.clear();
            this.xAdvances.clear();
            this.width = 0.0f;
        }

        public String toString() {
            StringBuilder buffer = new StringBuilder(this.glyphs.size);
            Array<Glyph> glyphs = this.glyphs;
            int n = glyphs.size;
            for (int i = 0; i < n; i++) {
                buffer.append((char) ((Glyph) glyphs.get(i)).id);
            }
            buffer.append(", #");
            buffer.append(this.color);
            buffer.append(", ");
            buffer.append(this.f82x);
            buffer.append(", ");
            buffer.append(this.f83y);
            buffer.append(", ");
            buffer.append(this.width);
            return buffer.toString();
        }
    }

    public GlyphLayout(BitmapFont font, CharSequence str) {
        setText(font, str);
    }

    public GlyphLayout(BitmapFont font, CharSequence str, Color color, float targetWidth, int halign, boolean wrap) {
        setText(font, str, color, targetWidth, halign, wrap);
    }

    public GlyphLayout(BitmapFont font, CharSequence str, int start, int end, Color color, float targetWidth, int halign, boolean wrap, String truncate) {
        setText(font, str, start, end, color, targetWidth, halign, wrap, truncate);
    }

    public void setText(BitmapFont font, CharSequence str) {
        setText(font, str, 0, str.length(), font.getColor(), 0.0f, 8, false, null);
    }

    public void setText(BitmapFont font, CharSequence str, Color color, float targetWidth, int halign, boolean wrap) {
        setText(font, str, 0, str.length(), color, targetWidth, halign, wrap, null);
    }

    public void setText(BitmapFont font, CharSequence str, int start, int end, Color color, float targetWidth, int halign, boolean wrap, String truncate) {
        if (targetWidth == 0.0f) {
            wrap = false;
        }
        if (truncate != null) {
            wrap = true;
        }
        BitmapFontData fontData = font.data;
        boolean markupEnabled = fontData.markupEnabled;
        Pool<GlyphRun> glyphRunPool = Pools.get(GlyphRun.class);
        glyphRunPool.freeAll(this.runs);
        this.runs.clear();
        float x = 0.0f;
        float y = 0.0f;
        float width = 0.0f;
        int lines = 0;
        Color nextColor = color;
        colorStack.add(color);
        Pool<Color> colorPool = Pools.get(Color.class);
        int runStart = start;
        int start2 = start;
        while (true) {
            GlyphRun run;
            int i;
            int i2;
            int runEnd = -1;
            boolean newline = false;
            FloatArray xAdvances;
            float xAdvance;
            if (start2 != end) {
                start = start2 + 1;
                switch (str.charAt(start2)) {
                    case '\n':
                        runEnd = start - 1;
                        newline = true;
                        break;
                    case Keys.MUTE /*91*/:
                        if (markupEnabled) {
                            int length = parseColorMarkup(str, start, end, colorPool);
                            if (length >= 0) {
                                runEnd = start - 1;
                                start += length + 1;
                                nextColor = (Color) colorStack.peek();
                                break;
                            }
                        }
                        break;
                    default:
                        break;
                }
                if (runEnd != -1) {
                    run = (GlyphRun) glyphRunPool.obtain();
                    this.runs.add(run);
                    run.color.set(color);
                    run.f82x = x;
                    run.f83y = y;
                    fontData.getGlyphs(run, str, runStart, runEnd);
                    xAdvances = run.xAdvances;
                    i = 0;
                    i2 = xAdvances.size;
                    while (i < i2) {
                        xAdvance = xAdvances.get(i);
                        x += xAdvance;
                        if (wrap) {
                        }
                        run.width += xAdvance;
                        i++;
                    }
                    if (newline) {
                        width = Math.max(width, x);
                        x = 0.0f;
                        y += fontData.down;
                        lines++;
                    }
                    runStart = start;
                    color = nextColor;
                }
                start2 = start;
            } else if (runStart == end) {
                start = start2;
            } else {
                runEnd = end;
                start = start2;
                if (runEnd != -1) {
                    run = (GlyphRun) glyphRunPool.obtain();
                    this.runs.add(run);
                    run.color.set(color);
                    run.f82x = x;
                    run.f83y = y;
                    fontData.getGlyphs(run, str, runStart, runEnd);
                    xAdvances = run.xAdvances;
                    i = 0;
                    i2 = xAdvances.size;
                    while (i < i2) {
                        xAdvance = xAdvances.get(i);
                        x += xAdvance;
                        if (wrap || x <= targetWidth || i <= 0 || x - (xAdvance - ((float) ((Glyph) run.glyphs.get(i)).width)) <= targetWidth) {
                            run.width += xAdvance;
                        } else if (truncate != null) {
                            truncate(fontData, run, targetWidth, truncate, i, glyphRunPool);
                        } else {
                            GlyphRun next = (GlyphRun) glyphRunPool.obtain();
                            this.runs.add(next);
                            wrap(fontData, run, next, Math.max(1, fontData.getWrapIndex(run.glyphs, i)), i);
                            width = Math.max(width, run.width);
                            x = 0.0f;
                            y += fontData.down;
                            lines++;
                            next.f82x = 0.0f;
                            next.f83y = y;
                            i = -1;
                            i2 = next.xAdvances.size;
                            xAdvances = next.xAdvances;
                            run = next;
                        }
                        i++;
                    }
                    if (newline) {
                        width = Math.max(width, x);
                        x = 0.0f;
                        y += fontData.down;
                        lines++;
                    }
                    runStart = start;
                    color = nextColor;
                }
                start2 = start;
            }
            width = Math.max(width, x);
            i2 = colorStack.size;
            for (i = 1; i < i2; i++) {
                colorPool.free(colorStack.get(i));
            }
            colorStack.clear();
            if ((halign & 8) == 0) {
                float shift;
                int lineStart;
                GlyphRun glyphRun;
                boolean center = (halign & 1) != 0;
                float lineWidth = 0.0f;
                float lineY = -2.14748365E9f;
                int lineStart2 = 0;
                i2 = this.runs.size;
                for (i = 0; i < i2; i++) {
                    run = (GlyphRun) this.runs.get(i);
                    if (run.f83y != lineY) {
                        lineY = run.f83y;
                        shift = targetWidth - lineWidth;
                        if (center) {
                            shift /= 2.0f;
                            lineStart = lineStart2;
                        } else {
                            lineStart = lineStart2;
                        }
                        while (lineStart < i) {
                            lineStart2 = lineStart + 1;
                            glyphRun = (GlyphRun) this.runs.get(lineStart);
                            glyphRun.f82x += shift;
                            lineStart = lineStart2;
                        }
                        lineWidth = 0.0f;
                        lineStart2 = lineStart;
                    }
                    lineWidth += run.width;
                }
                shift = targetWidth - lineWidth;
                if (center) {
                    shift /= 2.0f;
                    lineStart = lineStart2;
                } else {
                    lineStart = lineStart2;
                }
                while (lineStart < i2) {
                    lineStart2 = lineStart + 1;
                    glyphRun = (GlyphRun) this.runs.get(lineStart);
                    glyphRun.f82x += shift;
                    lineStart = lineStart2;
                }
            }
            this.width = width;
            this.height = fontData.capHeight + (((float) lines) * fontData.lineHeight);
            return;
        }
    }

    private void truncate(BitmapFontData fontData, GlyphRun run, float targetWidth, String truncate, int widthIndex, Pool<GlyphRun> glyphRunPool) {
        GlyphRun truncateRun = (GlyphRun) glyphRunPool.obtain();
        fontData.getGlyphs(truncateRun, truncate, 0, truncate.length());
        float truncateWidth = targetWidth;
        for (int i = 0; i < truncateRun.glyphs.size; i++) {
            truncateWidth -= truncateRun.xAdvances.get(i);
        }
        while (run.width > truncateWidth) {
            widthIndex--;
            run.width -= run.xAdvances.get(widthIndex);
        }
        run.glyphs.truncate(widthIndex);
        run.xAdvances.truncate(widthIndex);
        run.glyphs.addAll(truncateRun.glyphs);
        run.xAdvances.addAll(truncateRun.xAdvances);
        run.width += truncateWidth;
        glyphRunPool.free(truncateRun);
    }

    private int parseColorMarkup(CharSequence str, int start, int end, Pool<Color> colorPool) {
        if (start == end) {
            return -1;
        }
        int i;
        Color color;
        switch (str.charAt(start)) {
            case Keys.f10G /*35*/:
                int colorInt = 0;
                i = start + 1;
                while (i < end) {
                    char ch = str.charAt(i);
                    if (ch != ']') {
                        if (ch >= '0' && ch <= '9') {
                            colorInt = (colorInt * 16) + (ch - 48);
                        } else if (ch >= 'a' && ch <= 'f') {
                            colorInt = (colorInt * 16) + (ch - 87);
                        } else if (ch < 'A' || ch > 'F') {
                            return -1;
                        } else {
                            colorInt = (colorInt * 16) + (ch - 55);
                        }
                        i++;
                    } else if (i < start + 2 || i > start + 9) {
                        return -1;
                    } else {
                        color = (Color) colorPool.obtain();
                        colorStack.add(color);
                        Color.rgb888ToColor(color, colorInt);
                        if (i <= start + 7) {
                            color.f36a = 1.0f;
                        }
                        return i - start;
                    }
                }
                return -1;
            case Keys.MUTE /*91*/:
                return -1;
            case Keys.PAGE_DOWN /*93*/:
                if (colorStack.size > 1) {
                    colorPool.free(colorStack.pop());
                }
                return 0;
            default:
                int colorStart = start;
                i = start + 1;
                while (i < end) {
                    if (str.charAt(i) != ']') {
                        i++;
                    } else {
                        Color namedColor = Colors.get(str.subSequence(colorStart, i).toString());
                        if (namedColor == null) {
                            return -1;
                        }
                        color = (Color) colorPool.obtain();
                        colorStack.add(color);
                        color.set(namedColor);
                        return i - start;
                    }
                }
                return -1;
        }
    }

    private void wrap(BitmapFontData fontData, GlyphRun first, GlyphRun second, int wrapIndex, int widthIndex) {
        second.color.set(first.color);
        int widthIndex2 = widthIndex;
        while (true) {
            widthIndex = widthIndex2 - 1;
            if (widthIndex2 <= wrapIndex) {
                break;
            }
            first.width -= first.xAdvances.get(widthIndex);
            widthIndex2 = widthIndex;
        }
        second.glyphs.addAll(first.glyphs, wrapIndex, first.glyphs.size - wrapIndex);
        second.xAdvances.addAll(first.xAdvances, wrapIndex, first.xAdvances.size - wrapIndex);
        first.glyphs.truncate(wrapIndex);
        first.xAdvances.truncate(wrapIndex);
        int i = wrapIndex - 1;
        while (i >= 0) {
            if (fontData.isWhitespace((char) ((Glyph) first.glyphs.get(i)).id)) {
                first.width -= first.xAdvances.get(i);
                i--;
            } else if (i > 0) {
                first.glyphs.truncate(i + 1);
                first.xAdvances.truncate(i + 1);
                return;
            } else {
                return;
            }
        }
    }

    public void reset() {
        Pools.get(GlyphRun.class).freeAll(this.runs);
        this.runs.clear();
        this.width = 0.0f;
        this.height = 0.0f;
    }

    public String toString() {
        if (this.runs.size == 0) {
            return "";
        }
        StringBuilder buffer = new StringBuilder(128);
        int n = this.runs.size;
        for (int i = 0; i < n; i++) {
            buffer.append(((GlyphRun) this.runs.get(i)).toString());
            buffer.append('\n');
        }
        buffer.setLength(buffer.length() - 1);
        return buffer.toString();
    }
}
