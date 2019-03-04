package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.OrderedMap;
import java.util.Iterator;

public class PixmapPacker implements Disposable {
    Page currPage;
    boolean disposed;
    final boolean duplicateBorder;
    final int padding;
    final Format pageFormat;
    final int pageHeight;
    final int pageWidth;
    final Array<Page> pages = new Array();

    static final class Node {
        public String leaveName;
        public Node leftChild;
        public Rectangle rect;
        public Node rightChild;

        public Node(int x, int y, int width, int height, Node leftChild, Node rightChild, String leaveName) {
            this.rect = new Rectangle((float) x, (float) y, (float) width, (float) height);
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.leaveName = leaveName;
        }

        public Node() {
            this.rect = new Rectangle();
        }
    }

    public class Page {
        final Array<String> addedRects = new Array();
        Pixmap image;
        OrderedMap<String, Rectangle> rects;
        Node root;
        Texture texture;

        public Pixmap getPixmap() {
            return this.image;
        }

        public OrderedMap<String, Rectangle> getRects() {
            return this.rects;
        }
    }

    public PixmapPacker(int width, int height, Format format, int padding, boolean duplicateBorder) {
        this.pageWidth = width;
        this.pageHeight = height;
        this.pageFormat = format;
        this.padding = padding;
        this.duplicateBorder = duplicateBorder;
        newPage();
    }

    public synchronized Rectangle pack(String name, Pixmap image) {
        Rectangle rectangle;
        if (this.disposed) {
            rectangle = null;
        } else if (getRect(name) != null) {
            throw new RuntimeException("Key with name '" + name + "' is already in map");
        } else {
            int borderPixels = (this.padding + (this.duplicateBorder ? 1 : 0)) << 1;
            Rectangle rectangle2 = new Rectangle(0.0f, 0.0f, (float) (image.getWidth() + borderPixels), (float) (image.getHeight() + borderPixels));
            if (rectangle2.getWidth() > ((float) this.pageWidth) || rectangle2.getHeight() > ((float) this.pageHeight)) {
                throw new GdxRuntimeException("page size for '" + name + "' to small");
            }
            Node node = insert(this.currPage.root, rectangle2);
            if (node == null) {
                newPage();
                rectangle = pack(name, image);
            } else {
                node.leaveName = name;
                rectangle2 = new Rectangle(node.rect);
                rectangle2.width -= (float) borderPixels;
                rectangle2.height -= (float) borderPixels;
                borderPixels >>= 1;
                rectangle2.f98x += (float) borderPixels;
                rectangle2.f99y += (float) borderPixels;
                this.currPage.rects.put(name, rectangle2);
                Blending blending = Pixmap.getBlending();
                Pixmap.setBlending(Blending.None);
                this.currPage.image.drawPixmap(image, (int) rectangle2.f98x, (int) rectangle2.f99y);
                if (this.duplicateBorder) {
                    int imageWidth = image.getWidth();
                    int imageHeight = image.getHeight();
                    this.currPage.image.drawPixmap(image, 0, 0, 1, 1, ((int) rectangle2.f98x) - 1, ((int) rectangle2.f99y) - 1, 1, 1);
                    this.currPage.image.drawPixmap(image, imageWidth - 1, 0, 1, 1, ((int) rectangle2.width) + ((int) rectangle2.f98x), ((int) rectangle2.f99y) - 1, 1, 1);
                    this.currPage.image.drawPixmap(image, 0, imageHeight - 1, 1, 1, ((int) rectangle2.f98x) - 1, ((int) rectangle2.height) + ((int) rectangle2.f99y), 1, 1);
                    this.currPage.image.drawPixmap(image, imageWidth - 1, imageHeight - 1, 1, 1, ((int) rectangle2.width) + ((int) rectangle2.f98x), ((int) rectangle2.height) + ((int) rectangle2.f99y), 1, 1);
                    this.currPage.image.drawPixmap(image, 0, 0, imageWidth, 1, (int) rectangle2.f98x, ((int) rectangle2.f99y) - 1, (int) rectangle2.width, 1);
                    this.currPage.image.drawPixmap(image, 0, imageHeight - 1, imageWidth, 1, (int) rectangle2.f98x, ((int) rectangle2.f99y) + ((int) rectangle2.height), (int) rectangle2.width, 1);
                    this.currPage.image.drawPixmap(image, 0, 0, 1, imageHeight, ((int) rectangle2.f98x) - 1, (int) rectangle2.f99y, 1, (int) rectangle2.height);
                    this.currPage.image.drawPixmap(image, imageWidth - 1, 0, 1, imageHeight, ((int) rectangle2.f98x) + ((int) rectangle2.width), (int) rectangle2.f99y, 1, (int) rectangle2.height);
                }
                Pixmap.setBlending(blending);
                this.currPage.addedRects.add(name);
            }
        }
        return rectangle;
    }

    private void newPage() {
        Page page = new Page();
        page.image = new Pixmap(this.pageWidth, this.pageHeight, this.pageFormat);
        page.root = new Node(0, 0, this.pageWidth, this.pageHeight, null, null, null);
        page.rects = new OrderedMap();
        this.pages.add(page);
        this.currPage = page;
    }

    private Node insert(Node node, Rectangle rect) {
        if (node.leaveName == null && node.leftChild != null && node.rightChild != null) {
            Node newNode = insert(node.leftChild, rect);
            return newNode == null ? insert(node.rightChild, rect) : newNode;
        } else if (node.leaveName != null) {
            return null;
        } else {
            if (node.rect.width == rect.width && node.rect.height == rect.height) {
                return node;
            }
            if (node.rect.width < rect.width || node.rect.height < rect.height) {
                return null;
            }
            node.leftChild = new Node();
            node.rightChild = new Node();
            if (((int) node.rect.width) - ((int) rect.width) > ((int) node.rect.height) - ((int) rect.height)) {
                node.leftChild.rect.f98x = node.rect.f98x;
                node.leftChild.rect.f99y = node.rect.f99y;
                node.leftChild.rect.width = rect.width;
                node.leftChild.rect.height = node.rect.height;
                node.rightChild.rect.f98x = node.rect.f98x + rect.width;
                node.rightChild.rect.f99y = node.rect.f99y;
                node.rightChild.rect.width = node.rect.width - rect.width;
                node.rightChild.rect.height = node.rect.height;
            } else {
                node.leftChild.rect.f98x = node.rect.f98x;
                node.leftChild.rect.f99y = node.rect.f99y;
                node.leftChild.rect.width = node.rect.width;
                node.leftChild.rect.height = rect.height;
                node.rightChild.rect.f98x = node.rect.f98x;
                node.rightChild.rect.f99y = node.rect.f99y + rect.height;
                node.rightChild.rect.width = node.rect.width;
                node.rightChild.rect.height = node.rect.height - rect.height;
            }
            return insert(node.leftChild, rect);
        }
    }

    public Array<Page> getPages() {
        return this.pages;
    }

    public synchronized Rectangle getRect(String name) {
        Rectangle rect;
        Iterator i$ = this.pages.iterator();
        while (i$.hasNext()) {
            rect = (Rectangle) ((Page) i$.next()).rects.get(name);
            if (rect != null) {
                break;
            }
        }
        rect = null;
        return rect;
    }

    public synchronized Page getPage(String name) {
        Page page;
        Iterator i$ = this.pages.iterator();
        while (i$.hasNext()) {
            page = (Page) i$.next();
            if (((Rectangle) page.rects.get(name)) != null) {
                break;
            }
        }
        page = null;
        return page;
    }

    public synchronized int getPageIndex(String name) {
        int i;
        i = 0;
        while (i < this.pages.size) {
            if (((Rectangle) ((Page) this.pages.get(i)).rects.get(name)) != null) {
                break;
            }
            i++;
        }
        i = -1;
        return i;
    }

    public synchronized void dispose() {
        Iterator i$ = this.pages.iterator();
        while (i$.hasNext()) {
            ((Page) i$.next()).image.dispose();
        }
        this.disposed = true;
    }

    public synchronized TextureAtlas generateTextureAtlas(TextureFilter minFilter, TextureFilter magFilter, boolean useMipMaps) {
        TextureAtlas atlas;
        atlas = new TextureAtlas();
        Iterator it = this.pages.iterator();
        while (it.hasNext()) {
            Page page = (Page) it.next();
            if (page.rects.size != 0) {
                Texture texture = new Texture(new PixmapTextureData(page.image, page.image.getFormat(), useMipMaps, false, true)) {
                    public void dispose() {
                        super.dispose();
                        getTextureData().consumePixmap().dispose();
                    }
                };
                texture.setFilter(minFilter, magFilter);
                Iterator i$ = page.rects.keys().iterator();
                while (i$.hasNext()) {
                    String name = (String) i$.next();
                    Rectangle rect = (Rectangle) page.rects.get(name);
                    atlas.addRegion(name, new TextureRegion(texture, (int) rect.f98x, (int) rect.f99y, (int) rect.width, (int) rect.height));
                }
            }
        }
        return atlas;
    }

    public synchronized void updateTextureAtlas(TextureAtlas atlas, TextureFilter minFilter, TextureFilter magFilter, boolean useMipMaps) {
        Iterator it = this.pages.iterator();
        while (it.hasNext()) {
            Page page = (Page) it.next();
            Iterator i$;
            String name;
            Rectangle rect;
            if (page.texture == null) {
                if (page.rects.size != 0 && page.addedRects.size > 0) {
                    page.texture = new Texture(new PixmapTextureData(page.image, page.image.getFormat(), useMipMaps, false, true)) {
                        public void dispose() {
                            super.dispose();
                            getTextureData().consumePixmap().dispose();
                        }
                    };
                    page.texture.setFilter(minFilter, magFilter);
                    i$ = page.addedRects.iterator();
                    while (i$.hasNext()) {
                        name = (String) i$.next();
                        rect = (Rectangle) page.rects.get(name);
                        atlas.addRegion(name, new TextureRegion(page.texture, (int) rect.f98x, (int) rect.f99y, (int) rect.width, (int) rect.height));
                    }
                    page.addedRects.clear();
                }
            } else if (page.addedRects.size > 0) {
                page.texture.load(page.texture.getTextureData());
                i$ = page.addedRects.iterator();
                while (i$.hasNext()) {
                    name = (String) i$.next();
                    rect = (Rectangle) page.rects.get(name);
                    atlas.addRegion(name, new TextureRegion(page.texture, (int) rect.f98x, (int) rect.f99y, (int) rect.width, (int) rect.height));
                }
                page.addedRects.clear();
            }
        }
    }

    public int getPageWidth() {
        return this.pageWidth;
    }

    public int getPageHeight() {
        return this.pageHeight;
    }

    public int getPadding() {
        return this.padding;
    }

    public boolean duplicateBorder() {
        return this.duplicateBorder;
    }
}
