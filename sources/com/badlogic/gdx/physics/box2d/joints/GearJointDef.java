package com.badlogic.gdx.physics.box2d.joints;

import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;

public class GearJointDef extends JointDef {
    public Joint joint1;
    public Joint joint2;
    public float ratio;

    public GearJointDef() {
        this.joint1 = null;
        this.joint2 = null;
        this.ratio = 1.0f;
        this.type = JointType.GearJoint;
    }
}
