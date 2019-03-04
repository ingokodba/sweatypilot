package com.badlogic.gdx.graphics.g2d.freetype;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.BitmapFont.Glyph;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.PixmapPacker.Page;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.Bitmap;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.Face;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.GlyphMetrics;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.GlyphSlot;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.Library;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.SizeMetrics;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType.Stroker;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class FreeTypeFontGenerator implements Disposable {
    public static final String DEFAULT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890\"!`?'.,;:()[]{}<>|/@\\^$-%+=#_&~* ¡¢£¤¥¦§¨©ª«¬­®¯°±²³´µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ";
    public static final int NO_MAXIMUM = -1;
    private static int maxTextureSize = GL20.GL_STENCIL_BUFFER_BIT;
    boolean bitmapped = false;
    final Face face;
    final Library library;
    final String name;
    private int pixelHeight;
    private int pixelWidth;

    public static class FreeTypeFontParameter {
        public Color borderColor = Color.BLACK;
        public boolean borderStraight = false;
        public float borderWidth = 0.0f;
        public String characters = FreeTypeFontGenerator.DEFAULT_CHARS;
        public Color color = Color.WHITE;
        public boolean flip = false;
        public boolean genMipMaps = false;
        public boolean incremental;
        public boolean kerning = true;
        public TextureFilter magFilter = TextureFilter.Nearest;
        public TextureFilter minFilter = TextureFilter.Nearest;
        public PixmapPacker packer = null;
        public Color shadowColor = new Color(0.0f, 0.0f, 0.0f, 0.75f);
        public int shadowOffsetX = 0;
        public int shadowOffsetY = 0;
        public int size = 16;
    }

    public class GlyphAndBitmap {
        public Bitmap bitmap;
        public Glyph glyph;
    }

    public static class FreeTypeBitmapFontData extends BitmapFontData implements Disposable {
        FreeTypeFontGenerator generator;
        Array<Glyph> glyphs;
        String packPrefix;
        PixmapPacker packer;
        FreeTypeFontParameter parameter;
        Array<TextureRegion> regions;
        Stroker stroker;

        public Glyph getGlyph(char ch) {
            Glyph glyph = super.getGlyph(ch);
            if (!(glyph != null || this.generator == null || ch == '\u0000')) {
                this.generator.setPixelSizes(0, this.parameter.size);
                glyph = this.generator.createGlyph(ch, this, this.parameter, this.stroker, (this.ascent + this.capHeight) / this.scaleY, this.packPrefix, this.packer);
                if (glyph == null) {
                    return null;
                }
                setGlyph(ch, glyph);
                setGlyphRegion(glyph, (TextureRegion) this.regions.get(glyph.page));
                this.glyphs.add(glyph);
                if (this.parameter.kerning) {
                    Face face = this.generator.face;
                    int glyphIndex = face.getCharIndex(ch);
                    int n = this.glyphs.size;
                    for (int i = 0; i < n; i++) {
                        Glyph other = (Glyph) this.glyphs.get(i);
                        int otherIndex = face.getCharIndex(other.id);
                        int kerning = face.getKerning(glyphIndex, otherIndex, 0);
                        if (kerning != 0) {
                            glyph.setKerning(other.id, FreeType.toInt(kerning));
                        }
                        kerning = face.getKerning(otherIndex, glyphIndex, 0);
                        if (kerning != 0) {
                            other.setKerning(ch, FreeType.toInt(kerning));
                        }
                    }
                }
            }
            return glyph;
        }

        public void dispose() {
            if (this.stroker != null) {
                this.stroker.dispose();
            }
            if (this.packer != null) {
                this.packer.dispose();
            }
        }
    }

    public FreeTypeFontGenerator(FileHandle font) {
        this.name = font.pathWithoutExtension();
        int fileSize = (int) font.length();
        this.library = FreeType.initFreeType();
        if (this.library == null) {
            throw new GdxRuntimeException("Couldn't initialize FreeType");
        }
        ByteBuffer buffer;
        InputStream input = font.read();
        if (fileSize == 0) {
            try {
                byte[] data = StreamUtils.copyStreamToByteArray(input, fileSize > 0 ? (int) (((float) fileSize) * 1.5f) : GL20.GL_COLOR_BUFFER_BIT);
                buffer = BufferUtils.newUnsafeByteBuffer(data.length);
                BufferUtils.copy(data, 0, (Buffer) buffer, data.length);
            } catch (Throwable ex) {
                throw new GdxRuntimeException(ex);
            } catch (Throwable th) {
                StreamUtils.closeQuietly(input);
            }
        } else {
            buffer = BufferUtils.newUnsafeByteBuffer(fileSize);
            StreamUtils.copyStream(input, buffer);
        }
        StreamUtils.closeQuietly(input);
        this.face = this.library.newMemoryFace(buffer, 0);
        if (this.face == null) {
            throw new GdxRuntimeException("Couldn't create face for font: " + font);
        } else if (!checkForBitmapFont()) {
            setPixelSizes(0, 15);
        }
    }

    private boolean checkForBitmapFont() {
        if ((this.face.getFaceFlags() & FreeType.FT_FACE_FLAG_FIXED_SIZES) == FreeType.FT_FACE_FLAG_FIXED_SIZES && (this.face.getFaceFlags() & FreeType.FT_FACE_FLAG_HORIZONTAL) == FreeType.FT_FACE_FLAG_HORIZONTAL && this.face.loadChar(32, FreeType.FT_LOAD_DEFAULT) && this.face.getGlyph().getFormat() == 1651078259) {
            this.bitmapped = true;
        }
        return this.bitmapped;
    }

    public BitmapFont generateFont(int size, String characters, boolean flip) {
        BitmapFontData data = generateData(size, characters, flip, null);
        BitmapFont font = new BitmapFont(data, data.regions, false);
        font.setOwnsTexture(true);
        return font;
    }

    public BitmapFont generateFont(int size) {
        return generateFont(size, DEFAULT_CHARS, false);
    }

    public BitmapFont generateFont(FreeTypeFontParameter parameter) {
        return generateFont(parameter, new FreeTypeBitmapFontData());
    }

    public BitmapFont generateFont(FreeTypeFontParameter parameter, FreeTypeBitmapFontData data) {
        generateData(parameter, data);
        BitmapFont font = new BitmapFont((BitmapFontData) data, data.regions, false);
        font.setOwnsTexture(true);
        return font;
    }

    public int scaleForPixelHeight(int height) {
        setPixelSizes(0, height);
        SizeMetrics fontMetrics = this.face.getSize().getMetrics();
        return (height * height) / (FreeType.toInt(fontMetrics.getAscender()) - FreeType.toInt(fontMetrics.getDescender()));
    }

    public int scaleForPixelWidth(int width, int numChars) {
        SizeMetrics fontMetrics = this.face.getSize().getMetrics();
        int height = ((FreeType.toInt(fontMetrics.getAscender()) - FreeType.toInt(fontMetrics.getDescender())) * width) / (FreeType.toInt(fontMetrics.getMaxAdvance()) * numChars);
        setPixelSizes(0, height);
        return height;
    }

    public int scaleToFitSquare(int width, int height, int numChars) {
        return Math.min(scaleForPixelHeight(height), scaleForPixelWidth(width, numChars));
    }

    public GlyphAndBitmap generateGlyphAndBitmap(int c, int size, boolean flip) {
        setPixelSizes(0, size);
        int baseline = FreeType.toInt(this.face.getSize().getMetrics().getAscender());
        if (this.face.getCharIndex(c) == 0) {
            return null;
        }
        if (this.face.loadChar(c, FreeType.FT_LOAD_DEFAULT)) {
            Bitmap bitmap;
            GlyphSlot slot = this.face.getGlyph();
            if (this.bitmapped) {
                bitmap = slot.getBitmap();
            } else if (slot.renderGlyph(FreeType.FT_RENDER_MODE_LIGHT)) {
                bitmap = slot.getBitmap();
            } else {
                bitmap = null;
            }
            GlyphMetrics metrics = slot.getMetrics();
            Glyph glyph = new Glyph();
            if (bitmap != null) {
                glyph.width = bitmap.getWidth();
                glyph.height = bitmap.getRows();
            } else {
                glyph.width = 0;
                glyph.height = 0;
            }
            glyph.xoffset = slot.getBitmapLeft();
            glyph.yoffset = flip ? (-slot.getBitmapTop()) + baseline : (-(glyph.height - slot.getBitmapTop())) - baseline;
            glyph.xadvance = FreeType.toInt(metrics.getHoriAdvance());
            glyph.srcX = 0;
            glyph.srcY = 0;
            glyph.id = c;
            GlyphAndBitmap result = new GlyphAndBitmap();
            result.glyph = glyph;
            result.bitmap = bitmap;
            return result;
        }
        throw new GdxRuntimeException("Unable to load character!");
    }

    public FreeTypeBitmapFontData generateData(int size) {
        return generateData(size, DEFAULT_CHARS, false, null);
    }

    public FreeTypeBitmapFontData generateData(int size, String characters, boolean flip) {
        return generateData(size, characters, flip, null);
    }

    public FreeTypeBitmapFontData generateData(int size, String characters, boolean flip, PixmapPacker packer) {
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        parameter.characters = characters;
        parameter.flip = flip;
        parameter.packer = packer;
        return generateData(parameter);
    }

    public FreeTypeBitmapFontData generateData(FreeTypeFontParameter parameter) {
        return generateData(parameter, new FreeTypeBitmapFontData());
    }

    void setPixelSizes(int pixelWidth, int pixelHeight) {
        this.pixelWidth = pixelWidth;
        this.pixelHeight = pixelHeight;
        if (!this.bitmapped && !this.face.setPixelSizes(pixelWidth, pixelHeight)) {
            throw new GdxRuntimeException("Couldn't set size for font");
        }
    }

    public FreeTypeBitmapFontData generateData(FreeTypeFontParameter parameter, FreeTypeBitmapFontData data) {
        if (parameter == null) {
            parameter = new FreeTypeFontParameter();
        }
        String characters = parameter.characters;
        int charactersLength = characters.length();
        boolean incremental = parameter.incremental;
        setPixelSizes(0, parameter.size);
        SizeMetrics fontMetrics = this.face.getSize().getMetrics();
        data.flipped = parameter.flip;
        data.ascent = (float) FreeType.toInt(fontMetrics.getAscender());
        data.descent = (float) FreeType.toInt(fontMetrics.getDescender());
        data.lineHeight = (float) FreeType.toInt(fontMetrics.getHeight());
        float baseLine = data.ascent;
        if (this.bitmapped && data.lineHeight == 0.0f) {
            for (int c = 32; c < this.face.getNumGlyphs() + 32; c++) {
                if (this.face.loadChar(c, FreeType.FT_LOAD_DEFAULT)) {
                    int lh = FreeType.toInt(this.face.getGlyph().getMetrics().getHeight());
                    data.lineHeight = ((float) lh) > data.lineHeight ? (float) lh : data.lineHeight;
                }
            }
        }
        if (this.face.loadChar(32, FreeType.FT_LOAD_DEFAULT)) {
            data.spaceWidth = (float) FreeType.toInt(this.face.getGlyph().getMetrics().getHoriAdvance());
        } else {
            data.spaceWidth = (float) this.face.getMaxAdvanceWidth();
        }
        Glyph spaceGlyph = new Glyph();
        spaceGlyph.xadvance = (int) data.spaceWidth;
        spaceGlyph.id = 32;
        data.setGlyph(32, spaceGlyph);
        for (char loadChar : data.xChars) {
            if (this.face.loadChar(loadChar, FreeType.FT_LOAD_DEFAULT)) {
                data.xHeight = (float) FreeType.toInt(this.face.getGlyph().getMetrics().getHeight());
                break;
            }
        }
        if (data.xHeight == 0.0f) {
            throw new GdxRuntimeException("No x-height character found in font");
        }
        for (char capChar : data.capChars) {
            if (this.face.loadChar(capChar, FreeType.FT_LOAD_DEFAULT)) {
                data.capHeight = (float) FreeType.toInt(this.face.getGlyph().getMetrics().getHeight());
                break;
            }
        }
        if (this.bitmapped || data.capHeight != 1.0f) {
            String packPrefix;
            int i;
            data.ascent -= data.capHeight;
            data.down = -data.lineHeight;
            if (parameter.flip) {
                data.ascent = -data.ascent;
                data.down = -data.down;
            }
            boolean ownsAtlas = false;
            PixmapPacker packer = parameter.packer;
            if (packer == null) {
                int size;
                if (incremental) {
                    size = maxTextureSize;
                } else {
                    int maxGlyphHeight = (int) Math.ceil((double) data.lineHeight);
                    size = MathUtils.nextPowerOfTwo((int) Math.sqrt((double) ((maxGlyphHeight * maxGlyphHeight) * charactersLength)));
                    if (maxTextureSize > 0) {
                        size = Math.min(size, maxTextureSize);
                    }
                }
                ownsAtlas = true;
                packer = new PixmapPacker(size, size, Format.RGBA8888, 2, false);
            }
            if (ownsAtlas) {
                packPrefix = "";
            } else {
                packPrefix = this.name + '_' + parameter.size + (parameter.flip ? "_flip_" : Character.valueOf('_'));
            }
            Stroker stroker = null;
            if (parameter.borderWidth > 0.0f) {
                int i2;
                stroker = this.library.createStroker();
                int i3 = (int) (parameter.borderWidth * 64.0f);
                int i4 = parameter.borderStraight ? FreeType.FT_STROKER_LINECAP_BUTT : FreeType.FT_STROKER_LINECAP_ROUND;
                if (parameter.borderStraight) {
                    i2 = FreeType.FT_STROKER_LINEJOIN_MITER_FIXED;
                } else {
                    i2 = FreeType.FT_STROKER_LINEJOIN_ROUND;
                }
                stroker.set(i3, i4, i2, 0);
            }
            if (incremental) {
                data.generator = this;
                data.parameter = parameter;
                data.stroker = stroker;
                data.packPrefix = packPrefix;
                data.packer = packer;
                data.glyphs = new Array(charactersLength + 32);
            }
            for (i = 0; i < charactersLength; i++) {
                char c2 = characters.charAt(i);
                Glyph glyph = createGlyph(c2, data, parameter, stroker, baseLine, packPrefix, packer);
                if (glyph != null) {
                    data.setGlyph(c2, glyph);
                    if (incremental) {
                        data.glyphs.add(glyph);
                    }
                }
            }
            if (!(stroker == null || incremental)) {
                stroker.dispose();
            }
            if (parameter.kerning) {
                for (i = 0; i < charactersLength; i++) {
                    char firstChar = characters.charAt(i);
                    Glyph first = data.getGlyph(firstChar);
                    if (first != null) {
                        int firstIndex = this.face.getCharIndex(firstChar);
                        for (int j = i; j < charactersLength; j++) {
                            char secondChar = characters.charAt(j);
                            Glyph second = data.getGlyph(secondChar);
                            if (second != null) {
                                int secondIndex = this.face.getCharIndex(secondChar);
                                int kerning = this.face.getKerning(firstIndex, secondIndex, 0);
                                if (kerning != 0) {
                                    first.setKerning(secondChar, FreeType.toInt(kerning));
                                }
                                kerning = this.face.getKerning(secondIndex, firstIndex, 0);
                                if (kerning != 0) {
                                    second.setKerning(firstChar, FreeType.toInt(kerning));
                                }
                            }
                        }
                    }
                }
            }
            if (ownsAtlas) {
                Array<Page> pages = packer.getPages();
                data.regions = new Array(pages.size);
                for (i = 0; i < pages.size; i++) {
                    data.regions.add(createRegion((Page) pages.get(i), parameter));
                }
            }
            return data;
        }
        throw new GdxRuntimeException("No cap character found in font");
    }

    private TextureRegion createRegion(Page p, FreeTypeFontParameter parameter) {
        Texture tex = new Texture(new PixmapTextureData(p.getPixmap(), p.getPixmap().getFormat(), parameter.genMipMaps, false, true)) {
            public void dispose() {
                super.dispose();
                getTextureData().consumePixmap().dispose();
            }
        };
        tex.setFilter(parameter.minFilter, parameter.magFilter);
        return new TextureRegion(tex);
    }

    Glyph createGlyph(char c, FreeTypeBitmapFontData data, FreeTypeFontParameter parameter, Stroker stroker, float baseLine, String packPrefix, PixmapPacker packer) {
        Glyph glyph;
        boolean missing = this.face.getCharIndex(c) == 0;
        if (missing) {
            glyph = data.getGlyph('\u0000');
            if (glyph != null) {
                return glyph;
            }
        }
        if (this.face.loadChar(c, FreeType.FT_LOAD_DEFAULT)) {
            GlyphSlot slot = this.face.getGlyph();
            FreeType.Glyph mainGlyph = slot.getGlyph();
            try {
                mainGlyph.toBitmap(FreeType.FT_RENDER_MODE_NORMAL);
                Bitmap mainBitmap = mainGlyph.getBitmap();
                Pixmap mainPixmap = mainBitmap.getPixmap(Format.RGBA8888, parameter.color);
                if (!(parameter.borderWidth <= 0.0f && parameter.shadowOffsetX == 0 && parameter.shadowOffsetY == 0)) {
                    FreeType.Glyph borderGlyph = mainGlyph;
                    Bitmap borderBitmap = mainBitmap;
                    if (parameter.borderWidth > 0.0f) {
                        borderGlyph = slot.getGlyph();
                        borderGlyph.strokeBorder(stroker, false);
                        borderGlyph.toBitmap(FreeType.FT_RENDER_MODE_NORMAL);
                        borderBitmap = borderGlyph.getBitmap();
                        Pixmap borderPixmap = borderBitmap.getPixmap(Format.RGBA8888, parameter.borderColor);
                        borderPixmap.drawPixmap(mainPixmap, mainGlyph.getLeft() - borderGlyph.getLeft(), -(mainGlyph.getTop() - borderGlyph.getTop()));
                        mainPixmap.dispose();
                        mainGlyph.dispose();
                        mainPixmap = borderPixmap;
                        mainGlyph = borderGlyph;
                    }
                    if (!(parameter.shadowOffsetX == 0 && parameter.shadowOffsetY == 0)) {
                        Pixmap shadowPixmapSrc = borderBitmap.getPixmap(Format.RGBA8888, parameter.shadowColor);
                        Pixmap pixmap = new Pixmap(shadowPixmapSrc.getWidth() + Math.abs(parameter.shadowOffsetX), shadowPixmapSrc.getHeight() + Math.abs(parameter.shadowOffsetY), Format.RGBA8888);
                        Blending blending = Pixmap.getBlending();
                        Pixmap.setBlending(Blending.None);
                        pixmap.drawPixmap(shadowPixmapSrc, Math.max(parameter.shadowOffsetX, 0), Math.max(parameter.shadowOffsetY, 0));
                        Pixmap.setBlending(blending);
                        pixmap.drawPixmap(mainPixmap, Math.max(-parameter.shadowOffsetX, 0), Math.max(-parameter.shadowOffsetY, 0));
                        mainPixmap.dispose();
                        mainPixmap = pixmap;
                    }
                }
                GlyphMetrics metrics = slot.getMetrics();
                glyph = new Glyph();
                glyph.id = c;
                glyph.width = mainPixmap.getWidth();
                glyph.height = mainPixmap.getHeight();
                glyph.xoffset = mainGlyph.getLeft();
                glyph.yoffset = parameter.flip ? (-mainGlyph.getTop()) + ((int) baseLine) : (-(glyph.height - mainGlyph.getTop())) - ((int) baseLine);
                glyph.xadvance = FreeType.toInt(metrics.getHoriAdvance()) + ((int) parameter.borderWidth);
                if (this.bitmapped) {
                    mainPixmap.setColor(Color.CLEAR);
                    mainPixmap.fill();
                    ByteBuffer buf = mainBitmap.getBuffer();
                    int whiteIntBits = Color.WHITE.toIntBits();
                    int clearIntBits = Color.CLEAR.toIntBits();
                    for (int h = 0; h < glyph.height; h++) {
                        int idx = h * mainBitmap.getPitch();
                        for (int w = 0; w < glyph.width + glyph.xoffset; w++) {
                            int i;
                            if (((buf.get((w / 8) + idx) >>> (7 - (w % 8))) & 1) == 1) {
                                i = whiteIntBits;
                            } else {
                                i = clearIntBits;
                            }
                            mainPixmap.drawPixel(w, h, i);
                        }
                    }
                }
                String name = packPrefix + c;
                Rectangle rect = packer.pack(name, mainPixmap);
                int pageIndex = packer.getPageIndex(name);
                if (pageIndex == -1) {
                    throw new IllegalStateException("Packer was not able to insert glyph into a page: " + name);
                }
                glyph.page = pageIndex;
                glyph.srcX = (int) rect.f98x;
                glyph.srcY = (int) rect.f99y;
                if (data.regions != null) {
                    if (pageIndex >= data.regions.size) {
                        data.regions.add(createRegion((Page) packer.getPages().get(pageIndex), parameter));
                    } else {
                        Texture texture = ((TextureRegion) data.regions.get(pageIndex)).getTexture();
                        texture.bind();
                        Gdx.gl.glTexSubImage2D(texture.glTarget, 0, glyph.srcX, glyph.srcY, glyph.width, glyph.height, mainPixmap.getGLFormat(), mainPixmap.getGLType(), mainPixmap.getPixels());
                    }
                }
                mainPixmap.dispose();
                mainGlyph.dispose();
                if (!missing) {
                    return glyph;
                }
                data.setGlyph(0, glyph);
                return glyph;
            } catch (GdxRuntimeException e) {
                mainGlyph.dispose();
                Gdx.app.log("FreeTypeFontGenerator", "Couldn't render char '" + c + "'");
                return null;
            }
        }
        Gdx.app.log("FreeTypeFontGenerator", "Couldn't load char '" + c + "'");
        return null;
    }

    public void dispose() {
        this.face.dispose();
        this.library.dispose();
    }

    public static void setMaxTextureSize(int texSize) {
        maxTextureSize = texSize;
    }

    public static int getMaxTextureSize() {
        return maxTextureSize;
    }
}
