package com.badlogic.gdx.physics.box2d.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;

public class FrictionJointDef extends JointDef {
    public final Vector2 localAnchorA;
    public final Vector2 localAnchorB;
    public float maxForce;
    public float maxTorque;

    public FrictionJointDef() {
        this.localAnchorA = new Vector2();
        this.localAnchorB = new Vector2();
        this.maxForce = 0.0f;
        this.maxTorque = 0.0f;
        this.type = JointType.FrictionJoint;
    }

    public void initialize(Body bodyA, Body bodyB, Vector2 anchor) {
        this.bodyA = bodyA;
        this.bodyB = bodyB;
        this.localAnchorA.set(bodyA.getLocalPoint(anchor));
        this.localAnchorB.set(bodyB.getLocalPoint(anchor));
    }
}
