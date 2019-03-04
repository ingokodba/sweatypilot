package com.badlogic.gdx.graphics.g2d.freetype;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class FreeTypeFontGeneratorLoader extends SynchronousAssetLoader<FreeTypeFontGenerator, FreeTypeFontGeneratorParameters> {

    public static class FreeTypeFontGeneratorParameters extends AssetLoaderParameters<FreeTypeFontGenerator> {
    }

    public FreeTypeFontGeneratorLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    public FreeTypeFontGenerator load(AssetManager assetManager, String fileName, FileHandle file, FreeTypeFontGeneratorParameters parameter) {
        if (file.extension().equals("gen")) {
            return new FreeTypeFontGenerator(file.sibling(file.nameWithoutExtension()));
        }
        return new FreeTypeFontGenerator(file);
    }

    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, FreeTypeFontGeneratorParameters parameter) {
        return null;
    }
}
