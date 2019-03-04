package com.badlogic.gdx.physics.box2d.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;

public class DistanceJointDef extends JointDef {
    public float dampingRatio;
    public float frequencyHz;
    public float length;
    public final Vector2 localAnchorA;
    public final Vector2 localAnchorB;

    public DistanceJointDef() {
        this.localAnchorA = new Vector2();
        this.localAnchorB = new Vector2();
        this.length = 1.0f;
        this.frequencyHz = 0.0f;
        this.dampingRatio = 0.0f;
        this.type = JointType.DistanceJoint;
    }

    public void initialize(Body bodyA, Body bodyB, Vector2 anchorA, Vector2 anchorB) {
        this.bodyA = bodyA;
        this.bodyB = bodyB;
        this.localAnchorA.set(bodyA.getLocalPoint(anchorA));
        this.localAnchorB.set(bodyB.getLocalPoint(anchorB));
        this.length = anchorA.dst(anchorB);
    }
}
