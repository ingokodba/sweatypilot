package com.badlogic.gdx.physics.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;

public abstract class Joint {
    protected long addr;
    private final Vector2 anchorA = new Vector2();
    private final Vector2 anchorB = new Vector2();
    protected JointEdge jointEdgeA;
    protected JointEdge jointEdgeB;
    private final Vector2 reactionForce = new Vector2();
    private final float[] tmp = new float[2];
    private Object userData;
    private final World world;

    private native void jniGetAnchorA(long j, float[] fArr);

    private native void jniGetAnchorB(long j, float[] fArr);

    private native long jniGetBodyA(long j);

    private native long jniGetBodyB(long j);

    private native boolean jniGetCollideConnected(long j);

    private native void jniGetReactionForce(long j, float f, float[] fArr);

    private native float jniGetReactionTorque(long j, float f);

    private native int jniGetType(long j);

    private native boolean jniIsActive(long j);

    protected Joint(World world, long addr) {
        this.world = world;
        this.addr = addr;
    }

    public JointType getType() {
        int type = jniGetType(this.addr);
        if (type <= 0 || type >= JointType.valueTypes.length) {
            return JointType.Unknown;
        }
        return JointType.valueTypes[type];
    }

    public Body getBodyA() {
        return (Body) this.world.bodies.get(jniGetBodyA(this.addr));
    }

    public Body getBodyB() {
        return (Body) this.world.bodies.get(jniGetBodyB(this.addr));
    }

    public Vector2 getAnchorA() {
        jniGetAnchorA(this.addr, this.tmp);
        this.anchorA.f102x = this.tmp[0];
        this.anchorA.f103y = this.tmp[1];
        return this.anchorA;
    }

    public Vector2 getAnchorB() {
        jniGetAnchorB(this.addr, this.tmp);
        this.anchorB.f102x = this.tmp[0];
        this.anchorB.f103y = this.tmp[1];
        return this.anchorB;
    }

    public boolean getCollideConnected() {
        return jniGetCollideConnected(this.addr);
    }

    public Vector2 getReactionForce(float inv_dt) {
        jniGetReactionForce(this.addr, inv_dt, this.tmp);
        this.reactionForce.f102x = this.tmp[0];
        this.reactionForce.f103y = this.tmp[1];
        return this.reactionForce;
    }

    public float getReactionTorque(float inv_dt) {
        return jniGetReactionTorque(this.addr, inv_dt);
    }

    public Object getUserData() {
        return this.userData;
    }

    public void setUserData(Object userData) {
        this.userData = userData;
    }

    public boolean isActive() {
        return jniIsActive(this.addr);
    }
}
