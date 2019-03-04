package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.PixmapPacker.Page;
import com.badlogic.gdx.math.Rectangle;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

public class PixmapPackerIO {

    public enum ImageFormat {
        CIM(".cim"),
        PNG(".png");
        
        private final String extension;

        public String getExtension() {
            return this.extension;
        }

        private ImageFormat(String extension) {
            this.extension = extension;
        }
    }

    public static class SaveParameters {
        public ImageFormat format = ImageFormat.PNG;
        public TextureFilter magFilter = TextureFilter.Nearest;
        public TextureFilter minFilter = TextureFilter.Nearest;
    }

    public void save(FileHandle file, PixmapPacker packer) throws IOException {
        save(file, packer, new SaveParameters());
    }

    public void save(FileHandle file, PixmapPacker packer, SaveParameters parameters) throws IOException {
        Writer writer = file.writer(false);
        int index = 0;
        Iterator it = packer.pages.iterator();
        while (it.hasNext()) {
            Page page = (Page) it.next();
            if (page.rects.size > 0) {
                index++;
                FileHandle pageFile = file.sibling(file.nameWithoutExtension() + "_" + index + parameters.format.getExtension());
                switch (parameters.format) {
                    case CIM:
                        PixmapIO.writeCIM(pageFile, page.image);
                        break;
                    case PNG:
                        PixmapIO.writePNG(pageFile, page.image);
                        break;
                }
                writer.write("\n");
                writer.write(pageFile.name() + "\n");
                writer.write("size: " + page.image.getWidth() + "," + page.image.getHeight() + "\n");
                writer.write("format: " + packer.pageFormat.name() + "\n");
                writer.write("filter: " + parameters.minFilter.name() + "," + parameters.magFilter.name() + "\n");
                writer.write("repeat: none\n");
                Iterator i$ = page.rects.keys().iterator();
                while (i$.hasNext()) {
                    String name = (String) i$.next();
                    writer.write(name + "\n");
                    Rectangle rect = (Rectangle) page.rects.get(name);
                    writer.write("rotate: false\n");
                    writer.write("xy: " + ((int) rect.f98x) + "," + ((int) rect.f99y) + "\n");
                    writer.write("size: " + ((int) rect.width) + "," + ((int) rect.height) + "\n");
                    writer.write("orig: " + ((int) rect.width) + "," + ((int) rect.height) + "\n");
                    writer.write("offset: 0, 0\n");
                    writer.write("index: -1\n");
                }
            }
        }
        writer.close();
    }
}
