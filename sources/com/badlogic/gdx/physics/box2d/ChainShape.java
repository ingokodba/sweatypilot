package com.badlogic.gdx.physics.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape.Type;

public class ChainShape extends Shape {
    private static float[] verts = new float[2];
    boolean isLooped;

    private native void jniCreateChain(long j, float[] fArr, int i);

    private native void jniCreateLoop(long j, float[] fArr, int i);

    private native void jniGetVertex(long j, int i, float[] fArr);

    private native int jniGetVertexCount(long j);

    private native void jniSetNextVertex(long j, float f, float f2);

    private native void jniSetPrevVertex(long j, float f, float f2);

    private native long newChainShape();

    public ChainShape() {
        this.isLooped = false;
        this.addr = newChainShape();
    }

    ChainShape(long addr) {
        this.isLooped = false;
        this.addr = addr;
    }

    public Type getType() {
        return Type.Chain;
    }

    public void createLoop(float[] vertices) {
        jniCreateLoop(this.addr, vertices, vertices.length / 2);
        this.isLooped = true;
    }

    public void createLoop(Vector2[] vertices) {
        float[] verts = new float[(vertices.length * 2)];
        int i = 0;
        int j = 0;
        while (i < vertices.length * 2) {
            verts[i] = vertices[j].f102x;
            verts[i + 1] = vertices[j].f103y;
            i += 2;
            j++;
        }
        jniCreateLoop(this.addr, verts, verts.length / 2);
        this.isLooped = true;
    }

    public void createChain(float[] vertices) {
        jniCreateChain(this.addr, vertices, vertices.length / 2);
        this.isLooped = false;
    }

    public void createChain(Vector2[] vertices) {
        float[] verts = new float[(vertices.length * 2)];
        int i = 0;
        int j = 0;
        while (i < vertices.length * 2) {
            verts[i] = vertices[j].f102x;
            verts[i + 1] = vertices[j].f103y;
            i += 2;
            j++;
        }
        createChain(verts);
    }

    public void setPrevVertex(Vector2 prevVertex) {
        setPrevVertex(prevVertex.f102x, prevVertex.f103y);
    }

    public void setPrevVertex(float prevVertexX, float prevVertexY) {
        jniSetPrevVertex(this.addr, prevVertexX, prevVertexY);
    }

    public void setNextVertex(Vector2 nextVertex) {
        setNextVertex(nextVertex.f102x, nextVertex.f103y);
    }

    public void setNextVertex(float nextVertexX, float nextVertexY) {
        jniSetNextVertex(this.addr, nextVertexX, nextVertexY);
    }

    public int getVertexCount() {
        return jniGetVertexCount(this.addr);
    }

    public void getVertex(int index, Vector2 vertex) {
        jniGetVertex(this.addr, index, verts);
        vertex.f102x = verts[0];
        vertex.f103y = verts[1];
    }

    public boolean isLooped() {
        return this.isLooped;
    }
}
