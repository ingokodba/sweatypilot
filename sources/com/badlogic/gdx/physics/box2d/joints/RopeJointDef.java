package com.badlogic.gdx.physics.box2d.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;

public class RopeJointDef extends JointDef {
    public final Vector2 localAnchorA;
    public final Vector2 localAnchorB;
    public float maxLength;

    public RopeJointDef() {
        this.localAnchorA = new Vector2(-1.0f, 0.0f);
        this.localAnchorB = new Vector2(1.0f, 0.0f);
        this.maxLength = 0.0f;
        this.type = JointType.RopeJoint;
    }
}
