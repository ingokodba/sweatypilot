package com.badlogic.gdx.physics.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape.Type;

public class CircleShape extends Shape {
    private final Vector2 position;
    private final float[] tmp;

    private native void jniGetPosition(long j, float[] fArr);

    private native void jniSetPosition(long j, float f, float f2);

    private native long newCircleShape();

    public CircleShape() {
        this.tmp = new float[2];
        this.position = new Vector2();
        this.addr = newCircleShape();
    }

    protected CircleShape(long addr) {
        this.tmp = new float[2];
        this.position = new Vector2();
        this.addr = addr;
    }

    public Type getType() {
        return Type.Circle;
    }

    public Vector2 getPosition() {
        jniGetPosition(this.addr, this.tmp);
        this.position.f102x = this.tmp[0];
        this.position.f103y = this.tmp[1];
        return this.position;
    }

    public void setPosition(Vector2 position) {
        jniSetPosition(this.addr, position.f102x, position.f103y);
    }
}
