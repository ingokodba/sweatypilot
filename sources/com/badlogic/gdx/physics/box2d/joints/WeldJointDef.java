package com.badlogic.gdx.physics.box2d.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;

public class WeldJointDef extends JointDef {
    public float dampingRatio;
    public float frequencyHz;
    public final Vector2 localAnchorA;
    public final Vector2 localAnchorB;
    public float referenceAngle;

    public WeldJointDef() {
        this.localAnchorA = new Vector2();
        this.localAnchorB = new Vector2();
        this.referenceAngle = 0.0f;
        this.frequencyHz = 0.0f;
        this.dampingRatio = 0.0f;
        this.type = JointType.WeldJoint;
    }

    public void initialize(Body body1, Body body2, Vector2 anchor) {
        this.bodyA = body1;
        this.bodyB = body2;
        this.localAnchorA.set(body1.getLocalPoint(anchor));
        this.localAnchorB.set(body2.getLocalPoint(anchor));
        this.referenceAngle = body2.getAngle() - body1.getAngle();
    }
}
