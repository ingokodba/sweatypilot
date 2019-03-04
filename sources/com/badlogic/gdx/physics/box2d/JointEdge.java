package com.badlogic.gdx.physics.box2d;

public class JointEdge {
    public final Joint joint;
    public final Body other;

    protected JointEdge(Body other, Joint joint) {
        this.other = other;
        this.joint = joint;
    }
}
