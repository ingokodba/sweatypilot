package com.badlogic.gdx.physics.box2d.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;

public class RevoluteJointDef extends JointDef {
    public boolean enableLimit;
    public boolean enableMotor;
    public final Vector2 localAnchorA;
    public final Vector2 localAnchorB;
    public float lowerAngle;
    public float maxMotorTorque;
    public float motorSpeed;
    public float referenceAngle;
    public float upperAngle;

    public RevoluteJointDef() {
        this.localAnchorA = new Vector2();
        this.localAnchorB = new Vector2();
        this.referenceAngle = 0.0f;
        this.enableLimit = false;
        this.lowerAngle = 0.0f;
        this.upperAngle = 0.0f;
        this.enableMotor = false;
        this.motorSpeed = 0.0f;
        this.maxMotorTorque = 0.0f;
        this.type = JointType.RevoluteJoint;
    }

    public void initialize(Body bodyA, Body bodyB, Vector2 anchor) {
        this.bodyA = bodyA;
        this.bodyB = bodyB;
        this.localAnchorA.set(bodyA.getLocalPoint(anchor));
        this.localAnchorB.set(bodyB.getLocalPoint(anchor));
        this.referenceAngle = bodyB.getAngle() - bodyA.getAngle();
    }
}
