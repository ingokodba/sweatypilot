package com.badlogic.gdx.graphics.g2d.freetype;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Array;

public class FreetypeFontLoader extends AsynchronousAssetLoader<BitmapFont, FreeTypeFontLoaderParameter> {

    public static class FreeTypeFontLoaderParameter extends AssetLoaderParameters<BitmapFont> {
        public String fontFileName;
        public FreeTypeFontParameter fontParameters = new FreeTypeFontParameter();
    }

    public FreetypeFontLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    public void loadAsync(AssetManager manager, String fileName, FileHandle file, FreeTypeFontLoaderParameter parameter) {
        if (parameter == null) {
            throw new RuntimeException("FreetypeFontParameter must be set in AssetManager#load to point at a TTF file!");
        }
    }

    public BitmapFont loadSync(AssetManager manager, String fileName, FileHandle file, FreeTypeFontLoaderParameter parameter) {
        if (parameter == null) {
            throw new RuntimeException("FreetypeFontParameter must be set in AssetManager#load to point at a TTF file!");
        }
        BitmapFontData data = ((FreeTypeFontGenerator) manager.get(parameter.fontFileName + ".gen", FreeTypeFontGenerator.class)).generateData(parameter.fontParameters);
        BitmapFont font = new BitmapFont(data, data.regions, false);
        font.setOwnsTexture(true);
        return font;
    }

    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, FreeTypeFontLoaderParameter parameter) {
        Array<AssetDescriptor> deps = new Array();
        deps.add(new AssetDescriptor(parameter.fontFileName + ".gen", FreeTypeFontGenerator.class));
        return deps;
    }
}
