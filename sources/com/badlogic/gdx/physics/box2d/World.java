package com.badlogic.gdx.physics.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.FrictionJoint;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;
import com.badlogic.gdx.physics.box2d.joints.GearJoint;
import com.badlogic.gdx.physics.box2d.joints.GearJointDef;
import com.badlogic.gdx.physics.box2d.joints.MotorJoint;
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint;
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJoint;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.LongMap;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import java.util.Iterator;

public final class World implements Disposable {
    protected final long addr;
    protected final LongMap<Body> bodies = new LongMap(100);
    private final Contact contact = new Contact(this, 0);
    private long[] contactAddrs = new long[HttpStatus.SC_OK];
    protected ContactFilter contactFilter = null;
    protected ContactListener contactListener = null;
    private final Array<Contact> contacts = new Array();
    protected final LongMap<Fixture> fixtures = new LongMap(100);
    protected final Pool<Body> freeBodies = new Pool<Body>(100, HttpStatus.SC_OK) {
        protected Body newObject() {
            return new Body(World.this, 0);
        }
    };
    private final Array<Contact> freeContacts = new Array();
    protected final Pool<Fixture> freeFixtures = new Pool<Fixture>(100, HttpStatus.SC_OK) {
        protected Fixture newObject() {
            return new Fixture(null, 0);
        }
    };
    final Vector2 gravity = new Vector2();
    private final ContactImpulse impulse = new ContactImpulse(this, 0);
    protected final LongMap<Joint> joints = new LongMap(100);
    private final Manifold manifold = new Manifold(0);
    private QueryCallback queryCallback = null;
    private RayCastCallback rayCastCallback = null;
    private Vector2 rayNormal = new Vector2();
    private Vector2 rayPoint = new Vector2();
    final float[] tmpGravity = new float[2];

    public static native float getVelocityThreshold();

    private native void jniClearForces(long j);

    private native long jniCreateBody(long j, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, float f9);

    private native long jniCreateDistanceJoint(long j, long j2, long j3, boolean z, float f, float f2, float f3, float f4, float f5, float f6, float f7);

    private native long jniCreateFrictionJoint(long j, long j2, long j3, boolean z, float f, float f2, float f3, float f4, float f5, float f6);

    private native long jniCreateGearJoint(long j, long j2, long j3, boolean z, long j4, long j5, float f);

    private native long jniCreateMotorJoint(long j, long j2, long j3, boolean z, float f, float f2, float f3, float f4, float f5, float f6);

    private native long jniCreateMouseJoint(long j, long j2, long j3, boolean z, float f, float f2, float f3, float f4, float f5);

    private native long jniCreatePrismaticJoint(long j, long j2, long j3, boolean z, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z2, float f8, float f9, boolean z3, float f10, float f11);

    private native long jniCreatePulleyJoint(long j, long j2, long j3, boolean z, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11);

    private native long jniCreateRevoluteJoint(long j, long j2, long j3, boolean z, float f, float f2, float f3, float f4, float f5, boolean z2, float f6, float f7, boolean z3, float f8, float f9);

    private native long jniCreateRopeJoint(long j, long j2, long j3, boolean z, float f, float f2, float f3, float f4, float f5);

    private native long jniCreateWeldJoint(long j, long j2, long j3, boolean z, float f, float f2, float f3, float f4, float f5, float f6, float f7);

    private native long jniCreateWheelJoint(long j, long j2, long j3, boolean z, float f, float f2, float f3, float f4, float f5, float f6, boolean z2, float f7, float f8, float f9, float f10);

    private native void jniDeactivateBody(long j, long j2);

    private native void jniDestroyBody(long j, long j2);

    private native void jniDestroyFixture(long j, long j2, long j3);

    private native void jniDestroyJoint(long j, long j2);

    private native void jniDispose(long j);

    private native boolean jniGetAutoClearForces(long j);

    private native int jniGetBodyCount(long j);

    private native int jniGetContactCount(long j);

    private native void jniGetContactList(long j, long[] jArr);

    private native void jniGetGravity(long j, float[] fArr);

    private native int jniGetJointcount(long j);

    private native int jniGetProxyCount(long j);

    private native boolean jniIsLocked(long j);

    private native void jniQueryAABB(long j, float f, float f2, float f3, float f4);

    private native void jniRayCast(long j, float f, float f2, float f3, float f4);

    private native void jniSetAutoClearForces(long j, boolean z);

    private native void jniSetContiousPhysics(long j, boolean z);

    private native void jniSetGravity(long j, float f, float f2);

    private native void jniSetWarmStarting(long j, boolean z);

    private native void jniStep(long j, float f, int i, int i2);

    private native long newWorld(float f, float f2, boolean z);

    private native void setUseDefaultContactFilter(boolean z);

    public static native void setVelocityThreshold(float f);

    static {
        new SharedLibraryLoader().load("gdx-box2d");
    }

    public World(Vector2 gravity, boolean doSleep) {
        this.addr = newWorld(gravity.f102x, gravity.f103y, doSleep);
        this.contacts.ensureCapacity(this.contactAddrs.length);
        this.freeContacts.ensureCapacity(this.contactAddrs.length);
        for (int i = 0; i < this.contactAddrs.length; i++) {
            this.freeContacts.add(new Contact(this, 0));
        }
    }

    public void setDestructionListener(DestructionListener listener) {
    }

    public void setContactFilter(ContactFilter filter) {
        this.contactFilter = filter;
        setUseDefaultContactFilter(filter == null);
    }

    public void setContactListener(ContactListener listener) {
        this.contactListener = listener;
    }

    public Body createBody(BodyDef def) {
        Body body = (Body) this.freeBodies.obtain();
        body.reset(jniCreateBody(this.addr, def.type.getValue(), def.position.f102x, def.position.f103y, def.angle, def.linearVelocity.f102x, def.linearVelocity.f103y, def.angularVelocity, def.linearDamping, def.angularDamping, def.allowSleep, def.awake, def.fixedRotation, def.bullet, def.active, def.gravityScale));
        this.bodies.put(body.addr, body);
        return body;
    }

    public void destroyBody(Body body) {
        Array<JointEdge> jointList = body.getJointList();
        while (jointList.size > 0) {
            destroyJoint(((JointEdge) body.getJointList().get(0)).joint);
        }
        jniDestroyBody(this.addr, body.addr);
        body.setUserData(null);
        this.bodies.remove(body.addr);
        Array<Fixture> fixtureList = body.getFixtureList();
        while (fixtureList.size > 0) {
            ((Fixture) this.fixtures.remove(((Fixture) fixtureList.removeIndex(0)).addr)).setUserData(null);
        }
        this.freeBodies.free(body);
    }

    void destroyFixture(Body body, Fixture fixture) {
        jniDestroyFixture(this.addr, body.addr, fixture.addr);
    }

    void deactivateBody(Body body) {
        jniDeactivateBody(this.addr, body.addr);
    }

    public Joint createJoint(JointDef def) {
        long jointAddr = createProperJoint(def);
        Joint joint = null;
        if (def.type == JointType.DistanceJoint) {
            joint = new DistanceJoint(this, jointAddr);
        }
        if (def.type == JointType.FrictionJoint) {
            joint = new FrictionJoint(this, jointAddr);
        }
        if (def.type == JointType.GearJoint) {
            joint = new GearJoint(this, jointAddr, ((GearJointDef) def).joint1, ((GearJointDef) def).joint2);
        }
        if (def.type == JointType.MotorJoint) {
            joint = new MotorJoint(this, jointAddr);
        }
        if (def.type == JointType.MouseJoint) {
            joint = new MouseJoint(this, jointAddr);
        }
        if (def.type == JointType.PrismaticJoint) {
            joint = new PrismaticJoint(this, jointAddr);
        }
        if (def.type == JointType.PulleyJoint) {
            joint = new PulleyJoint(this, jointAddr);
        }
        if (def.type == JointType.RevoluteJoint) {
            joint = new RevoluteJoint(this, jointAddr);
        }
        if (def.type == JointType.RopeJoint) {
            joint = new RopeJoint(this, jointAddr);
        }
        if (def.type == JointType.WeldJoint) {
            joint = new WeldJoint(this, jointAddr);
        }
        if (def.type == JointType.WheelJoint) {
            joint = new WheelJoint(this, jointAddr);
        }
        if (joint != null) {
            this.joints.put(joint.addr, joint);
        }
        JointEdge jointEdgeA = new JointEdge(def.bodyB, joint);
        JointEdge jointEdgeB = new JointEdge(def.bodyA, joint);
        joint.jointEdgeA = jointEdgeA;
        joint.jointEdgeB = jointEdgeB;
        def.bodyA.joints.add(jointEdgeA);
        def.bodyB.joints.add(jointEdgeB);
        return joint;
    }

    private long createProperJoint(JointDef def) {
        if (def.type == JointType.DistanceJoint) {
            DistanceJointDef d = (DistanceJointDef) def;
            return jniCreateDistanceJoint(this.addr, d.bodyA.addr, d.bodyB.addr, d.collideConnected, d.localAnchorA.f102x, d.localAnchorA.f103y, d.localAnchorB.f102x, d.localAnchorB.f103y, d.length, d.frequencyHz, d.dampingRatio);
        } else if (def.type == JointType.FrictionJoint) {
            FrictionJointDef d2 = (FrictionJointDef) def;
            return jniCreateFrictionJoint(this.addr, d2.bodyA.addr, d2.bodyB.addr, d2.collideConnected, d2.localAnchorA.f102x, d2.localAnchorA.f103y, d2.localAnchorB.f102x, d2.localAnchorB.f103y, d2.maxForce, d2.maxTorque);
        } else if (def.type == JointType.GearJoint) {
            GearJointDef d3 = (GearJointDef) def;
            return jniCreateGearJoint(this.addr, d3.bodyA.addr, d3.bodyB.addr, d3.collideConnected, d3.joint1.addr, d3.joint2.addr, d3.ratio);
        } else if (def.type == JointType.MotorJoint) {
            MotorJointDef d4 = (MotorJointDef) def;
            return jniCreateMotorJoint(this.addr, d4.bodyA.addr, d4.bodyB.addr, d4.collideConnected, d4.linearOffset.f102x, d4.linearOffset.f103y, d4.angularOffset, d4.maxForce, d4.maxTorque, d4.correctionFactor);
        } else if (def.type == JointType.MouseJoint) {
            MouseJointDef d5 = (MouseJointDef) def;
            return jniCreateMouseJoint(this.addr, d5.bodyA.addr, d5.bodyB.addr, d5.collideConnected, d5.target.f102x, d5.target.f103y, d5.maxForce, d5.frequencyHz, d5.dampingRatio);
        } else if (def.type == JointType.PrismaticJoint) {
            PrismaticJointDef d6 = (PrismaticJointDef) def;
            return jniCreatePrismaticJoint(this.addr, d6.bodyA.addr, d6.bodyB.addr, d6.collideConnected, d6.localAnchorA.f102x, d6.localAnchorA.f103y, d6.localAnchorB.f102x, d6.localAnchorB.f103y, d6.localAxisA.f102x, d6.localAxisA.f103y, d6.referenceAngle, d6.enableLimit, d6.lowerTranslation, d6.upperTranslation, d6.enableMotor, d6.maxMotorForce, d6.motorSpeed);
        } else if (def.type == JointType.PulleyJoint) {
            PulleyJointDef d7 = (PulleyJointDef) def;
            return jniCreatePulleyJoint(this.addr, d7.bodyA.addr, d7.bodyB.addr, d7.collideConnected, d7.groundAnchorA.f102x, d7.groundAnchorA.f103y, d7.groundAnchorB.f102x, d7.groundAnchorB.f103y, d7.localAnchorA.f102x, d7.localAnchorA.f103y, d7.localAnchorB.f102x, d7.localAnchorB.f103y, d7.lengthA, d7.lengthB, d7.ratio);
        } else if (def.type == JointType.RevoluteJoint) {
            RevoluteJointDef d8 = (RevoluteJointDef) def;
            return jniCreateRevoluteJoint(this.addr, d8.bodyA.addr, d8.bodyB.addr, d8.collideConnected, d8.localAnchorA.f102x, d8.localAnchorA.f103y, d8.localAnchorB.f102x, d8.localAnchorB.f103y, d8.referenceAngle, d8.enableLimit, d8.lowerAngle, d8.upperAngle, d8.enableMotor, d8.motorSpeed, d8.maxMotorTorque);
        } else if (def.type == JointType.RopeJoint) {
            RopeJointDef d9 = (RopeJointDef) def;
            return jniCreateRopeJoint(this.addr, d9.bodyA.addr, d9.bodyB.addr, d9.collideConnected, d9.localAnchorA.f102x, d9.localAnchorA.f103y, d9.localAnchorB.f102x, d9.localAnchorB.f103y, d9.maxLength);
        } else if (def.type == JointType.WeldJoint) {
            WeldJointDef d10 = (WeldJointDef) def;
            return jniCreateWeldJoint(this.addr, d10.bodyA.addr, d10.bodyB.addr, d10.collideConnected, d10.localAnchorA.f102x, d10.localAnchorA.f103y, d10.localAnchorB.f102x, d10.localAnchorB.f103y, d10.referenceAngle, d10.frequencyHz, d10.dampingRatio);
        } else if (def.type != JointType.WheelJoint) {
            return 0;
        } else {
            WheelJointDef d11 = (WheelJointDef) def;
            return jniCreateWheelJoint(this.addr, d11.bodyA.addr, d11.bodyB.addr, d11.collideConnected, d11.localAnchorA.f102x, d11.localAnchorA.f103y, d11.localAnchorB.f102x, d11.localAnchorB.f103y, d11.localAxisA.f102x, d11.localAxisA.f103y, d11.enableMotor, d11.maxMotorTorque, d11.motorSpeed, d11.frequencyHz, d11.dampingRatio);
        }
    }

    public void destroyJoint(Joint joint) {
        joint.setUserData(null);
        this.joints.remove(joint.addr);
        joint.jointEdgeA.other.joints.removeValue(joint.jointEdgeB, true);
        joint.jointEdgeB.other.joints.removeValue(joint.jointEdgeA, true);
        jniDestroyJoint(this.addr, joint.addr);
    }

    public void step(float timeStep, int velocityIterations, int positionIterations) {
        jniStep(this.addr, timeStep, velocityIterations, positionIterations);
    }

    public void clearForces() {
        jniClearForces(this.addr);
    }

    public void setWarmStarting(boolean flag) {
        jniSetWarmStarting(this.addr, flag);
    }

    public void setContinuousPhysics(boolean flag) {
        jniSetContiousPhysics(this.addr, flag);
    }

    public int getProxyCount() {
        return jniGetProxyCount(this.addr);
    }

    public int getBodyCount() {
        return jniGetBodyCount(this.addr);
    }

    public int getFixtureCount() {
        return this.fixtures.size;
    }

    public int getJointCount() {
        return jniGetJointcount(this.addr);
    }

    public int getContactCount() {
        return jniGetContactCount(this.addr);
    }

    public void setGravity(Vector2 gravity) {
        jniSetGravity(this.addr, gravity.f102x, gravity.f103y);
    }

    public Vector2 getGravity() {
        jniGetGravity(this.addr, this.tmpGravity);
        this.gravity.f102x = this.tmpGravity[0];
        this.gravity.f103y = this.tmpGravity[1];
        return this.gravity;
    }

    public boolean isLocked() {
        return jniIsLocked(this.addr);
    }

    public void setAutoClearForces(boolean flag) {
        jniSetAutoClearForces(this.addr, flag);
    }

    public boolean getAutoClearForces() {
        return jniGetAutoClearForces(this.addr);
    }

    public void QueryAABB(QueryCallback callback, float lowerX, float lowerY, float upperX, float upperY) {
        this.queryCallback = callback;
        jniQueryAABB(this.addr, lowerX, lowerY, upperX, upperY);
    }

    public Array<Contact> getContactList() {
        int i;
        int numContacts = getContactCount();
        if (numContacts > this.contactAddrs.length) {
            int newSize = numContacts * 2;
            this.contactAddrs = new long[newSize];
            this.contacts.ensureCapacity(newSize);
            this.freeContacts.ensureCapacity(newSize);
        }
        if (numContacts > this.freeContacts.size) {
            int freeConts = this.freeContacts.size;
            for (i = 0; i < numContacts - freeConts; i++) {
                this.freeContacts.add(new Contact(this, 0));
            }
        }
        jniGetContactList(this.addr, this.contactAddrs);
        this.contacts.clear();
        for (i = 0; i < numContacts; i++) {
            Contact contact = (Contact) this.freeContacts.get(i);
            contact.addr = this.contactAddrs[i];
            this.contacts.add(contact);
        }
        return this.contacts;
    }

    public void getBodies(Array<Body> bodies) {
        bodies.clear();
        bodies.ensureCapacity(this.bodies.size);
        Iterator<Body> iter = this.bodies.values();
        while (iter.hasNext()) {
            bodies.add(iter.next());
        }
    }

    public void getFixtures(Array<Fixture> fixtures) {
        fixtures.clear();
        fixtures.ensureCapacity(this.fixtures.size);
        Iterator<Fixture> iter = this.fixtures.values();
        while (iter.hasNext()) {
            fixtures.add(iter.next());
        }
    }

    public void getJoints(Array<Joint> joints) {
        joints.clear();
        joints.ensureCapacity(this.joints.size);
        Iterator<Joint> iter = this.joints.values();
        while (iter.hasNext()) {
            joints.add(iter.next());
        }
    }

    public void dispose() {
        jniDispose(this.addr);
    }

    private boolean contactFilter(long fixtureA, long fixtureB) {
        if (this.contactFilter != null) {
            return this.contactFilter.shouldCollide((Fixture) this.fixtures.get(fixtureA), (Fixture) this.fixtures.get(fixtureB));
        }
        Filter filterA = ((Fixture) this.fixtures.get(fixtureA)).getFilterData();
        Filter filterB = ((Fixture) this.fixtures.get(fixtureB)).getFilterData();
        if (filterA.groupIndex == filterB.groupIndex && filterA.groupIndex != (short) 0) {
            return filterA.groupIndex > (short) 0;
        } else {
            boolean collide;
            if ((filterA.maskBits & filterB.categoryBits) == 0 || (filterA.categoryBits & filterB.maskBits) == 0) {
                collide = false;
            } else {
                collide = true;
            }
            return collide;
        }
    }

    private void beginContact(long contactAddr) {
        this.contact.addr = contactAddr;
        if (this.contactListener != null) {
            this.contactListener.beginContact(this.contact);
        }
    }

    private void endContact(long contactAddr) {
        this.contact.addr = contactAddr;
        if (this.contactListener != null) {
            this.contactListener.endContact(this.contact);
        }
    }

    private void preSolve(long contactAddr, long manifoldAddr) {
        this.contact.addr = contactAddr;
        this.manifold.addr = manifoldAddr;
        if (this.contactListener != null) {
            this.contactListener.preSolve(this.contact, this.manifold);
        }
    }

    private void postSolve(long contactAddr, long impulseAddr) {
        this.contact.addr = contactAddr;
        this.impulse.addr = impulseAddr;
        if (this.contactListener != null) {
            this.contactListener.postSolve(this.contact, this.impulse);
        }
    }

    private boolean reportFixture(long addr) {
        if (this.queryCallback != null) {
            return this.queryCallback.reportFixture((Fixture) this.fixtures.get(addr));
        }
        return false;
    }

    public void rayCast(RayCastCallback callback, Vector2 point1, Vector2 point2) {
        this.rayCastCallback = callback;
        jniRayCast(this.addr, point1.f102x, point1.f103y, point2.f102x, point2.f103y);
    }

    private float reportRayFixture(long addr, float pX, float pY, float nX, float nY, float fraction) {
        if (this.rayCastCallback == null) {
            return 0.0f;
        }
        this.rayPoint.f102x = pX;
        this.rayPoint.f103y = pY;
        this.rayNormal.f102x = nX;
        this.rayNormal.f103y = nY;
        return this.rayCastCallback.reportRayFixture((Fixture) this.fixtures.get(addr), this.rayPoint, this.rayNormal, fraction);
    }
}
