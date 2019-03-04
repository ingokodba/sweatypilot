package com.badlogic.gdx.physics.box2d.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;

public class PulleyJointDef extends JointDef {
    private static final float minPulleyLength = 2.0f;
    public final Vector2 groundAnchorA;
    public final Vector2 groundAnchorB;
    public float lengthA;
    public float lengthB;
    public final Vector2 localAnchorA;
    public final Vector2 localAnchorB;
    public float ratio;

    public PulleyJointDef() {
        this.groundAnchorA = new Vector2(-1.0f, 1.0f);
        this.groundAnchorB = new Vector2(1.0f, 1.0f);
        this.localAnchorA = new Vector2(-1.0f, 0.0f);
        this.localAnchorB = new Vector2(1.0f, 0.0f);
        this.lengthA = 0.0f;
        this.lengthB = 0.0f;
        this.ratio = 1.0f;
        this.type = JointType.PulleyJoint;
        this.collideConnected = true;
    }

    public void initialize(Body bodyA, Body bodyB, Vector2 groundAnchorA, Vector2 groundAnchorB, Vector2 anchorA, Vector2 anchorB, float ratio) {
        this.bodyA = bodyA;
        this.bodyB = bodyB;
        this.groundAnchorA.set(groundAnchorA);
        this.groundAnchorB.set(groundAnchorB);
        this.localAnchorA.set(bodyA.getLocalPoint(anchorA));
        this.localAnchorB.set(bodyB.getLocalPoint(anchorB));
        this.lengthA = anchorA.dst(groundAnchorA);
        this.lengthB = anchorB.dst(groundAnchorB);
        this.ratio = ratio;
        float C = this.lengthA + (this.lengthB * ratio);
    }
}
