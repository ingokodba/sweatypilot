package com.badlogic.gdx.physics.box2d.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;

public class WheelJointDef extends JointDef {
    public float dampingRatio;
    public boolean enableMotor;
    public float frequencyHz;
    public final Vector2 localAnchorA;
    public final Vector2 localAnchorB;
    public final Vector2 localAxisA;
    public float maxMotorTorque;
    public float motorSpeed;

    public WheelJointDef() {
        this.localAnchorA = new Vector2();
        this.localAnchorB = new Vector2();
        this.localAxisA = new Vector2(1.0f, 0.0f);
        this.enableMotor = false;
        this.maxMotorTorque = 0.0f;
        this.motorSpeed = 0.0f;
        this.frequencyHz = 2.0f;
        this.dampingRatio = 0.7f;
        this.type = JointType.WheelJoint;
    }

    public void initialize(Body bodyA, Body bodyB, Vector2 anchor, Vector2 axis) {
        this.bodyA = bodyA;
        this.bodyB = bodyB;
        this.localAnchorA.set(bodyA.getLocalPoint(anchor));
        this.localAnchorB.set(bodyB.getLocalPoint(anchor));
        this.localAxisA.set(bodyA.getLocalVector(axis));
    }
}
