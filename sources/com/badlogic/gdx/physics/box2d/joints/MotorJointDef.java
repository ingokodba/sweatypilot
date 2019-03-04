package com.badlogic.gdx.physics.box2d.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;

public class MotorJointDef extends JointDef {
    public float angularOffset;
    public float correctionFactor;
    public final Vector2 linearOffset;
    public float maxForce;
    public float maxTorque;

    public MotorJointDef() {
        this.linearOffset = new Vector2();
        this.angularOffset = 0.0f;
        this.maxForce = 1.0f;
        this.maxTorque = 1.0f;
        this.correctionFactor = 0.3f;
        this.type = JointType.MotorJoint;
    }

    public void initialize(Body body1, Body body2) {
        this.bodyA = body1;
        this.bodyB = body2;
        this.linearOffset.set(this.bodyA.getLocalPoint(this.bodyB.getPosition()));
        this.angularOffset = this.bodyB.getAngle() - this.bodyA.getAngle();
    }
}
