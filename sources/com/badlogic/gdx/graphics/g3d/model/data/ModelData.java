package com.badlogic.gdx.graphics.g3d.model.data;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.Iterator;

public class ModelData {
    public final Array<ModelAnimation> animations = new Array();
    public String id;
    public final Array<ModelMaterial> materials = new Array();
    public final Array<ModelMesh> meshes = new Array();
    public final Array<ModelNode> nodes = new Array();
    public final short[] version = new short[2];

    public void addMesh(ModelMesh mesh) {
        Iterator i$ = this.meshes.iterator();
        while (i$.hasNext()) {
            ModelMesh other = (ModelMesh) i$.next();
            if (other.id.equals(mesh.id)) {
                throw new GdxRuntimeException("Mesh with id '" + other.id + "' already in model");
            }
        }
        this.meshes.add(mesh);
    }
}
