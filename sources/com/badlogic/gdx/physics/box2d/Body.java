package com.badlogic.gdx.physics.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

public class Body {
    protected long addr;
    private Array<Fixture> fixtures = new Array(2);
    protected Array<JointEdge> joints = new Array(2);
    public final Vector2 linVelLoc = new Vector2();
    public final Vector2 linVelWorld = new Vector2();
    private final Vector2 linearVelocity = new Vector2();
    private final Vector2 localCenter = new Vector2();
    private final Vector2 localPoint = new Vector2();
    public final Vector2 localPoint2 = new Vector2();
    public final Vector2 localVector = new Vector2();
    private final MassData massData = new MassData();
    private final Vector2 position = new Vector2();
    private final float[] tmp = new float[4];
    private final Transform transform = new Transform();
    private Object userData;
    private final World world;
    private final Vector2 worldCenter = new Vector2();
    private final Vector2 worldVector = new Vector2();

    private native void jniApplyAngularImpulse(long j, float f, boolean z);

    private native void jniApplyForce(long j, float f, float f2, float f3, float f4, boolean z);

    private native void jniApplyForceToCenter(long j, float f, float f2, boolean z);

    private native void jniApplyLinearImpulse(long j, float f, float f2, float f3, float f4, boolean z);

    private native void jniApplyTorque(long j, float f, boolean z);

    private native long jniCreateFixture(long j, long j2, float f);

    private native long jniCreateFixture(long j, long j2, float f, float f2, float f3, boolean z, short s, short s2, short s3);

    private native float jniGetAngle(long j);

    private native float jniGetAngularDamping(long j);

    private native float jniGetAngularVelocity(long j);

    private native float jniGetGravityScale(long j);

    private native float jniGetInertia(long j);

    private native float jniGetLinearDamping(long j);

    private native void jniGetLinearVelocity(long j, float[] fArr);

    private native void jniGetLinearVelocityFromLocalPoint(long j, float f, float f2, float[] fArr);

    private native void jniGetLinearVelocityFromWorldPoint(long j, float f, float f2, float[] fArr);

    private native void jniGetLocalCenter(long j, float[] fArr);

    private native void jniGetLocalPoint(long j, float f, float f2, float[] fArr);

    private native void jniGetLocalVector(long j, float f, float f2, float[] fArr);

    private native float jniGetMass(long j);

    private native void jniGetMassData(long j, float[] fArr);

    private native void jniGetPosition(long j, float[] fArr);

    private native void jniGetTransform(long j, float[] fArr);

    private native int jniGetType(long j);

    private native void jniGetWorldCenter(long j, float[] fArr);

    private native void jniGetWorldPoint(long j, float f, float f2, float[] fArr);

    private native void jniGetWorldVector(long j, float f, float f2, float[] fArr);

    private native boolean jniIsActive(long j);

    private native boolean jniIsAwake(long j);

    private native boolean jniIsBullet(long j);

    private native boolean jniIsFixedRotation(long j);

    private native boolean jniIsSleepingAllowed(long j);

    private native void jniResetMassData(long j);

    private native void jniSetActive(long j, boolean z);

    private native void jniSetAngularDamping(long j, float f);

    private native void jniSetAngularVelocity(long j, float f);

    private native void jniSetAwake(long j, boolean z);

    private native void jniSetBullet(long j, boolean z);

    private native void jniSetFixedRotation(long j, boolean z);

    private native void jniSetGravityScale(long j, float f);

    private native void jniSetLinearDamping(long j, float f);

    private native void jniSetLinearVelocity(long j, float f, float f2);

    private native void jniSetMassData(long j, float f, float f2, float f3, float f4);

    private native void jniSetSleepingAllowed(long j, boolean z);

    private native void jniSetTransform(long j, float f, float f2, float f3);

    private native void jniSetType(long j, int i);

    protected Body(World world, long addr) {
        this.world = world;
        this.addr = addr;
    }

    protected void reset(long addr) {
        this.addr = addr;
        this.userData = null;
        for (int i = 0; i < this.fixtures.size; i++) {
            this.world.freeFixtures.free(this.fixtures.get(i));
        }
        this.fixtures.clear();
        this.joints.clear();
    }

    public Fixture createFixture(FixtureDef def) {
        Fixture fixture = (Fixture) this.world.freeFixtures.obtain();
        fixture.reset(this, jniCreateFixture(this.addr, def.shape.addr, def.friction, def.restitution, def.density, def.isSensor, def.filter.categoryBits, def.filter.maskBits, def.filter.groupIndex));
        this.world.fixtures.put(fixture.addr, fixture);
        this.fixtures.add(fixture);
        return fixture;
    }

    public Fixture createFixture(Shape shape, float density) {
        Fixture fixture = (Fixture) this.world.freeFixtures.obtain();
        fixture.reset(this, jniCreateFixture(this.addr, shape.addr, density));
        this.world.fixtures.put(fixture.addr, fixture);
        this.fixtures.add(fixture);
        return fixture;
    }

    public void destroyFixture(Fixture fixture) {
        this.world.destroyFixture(this, fixture);
        fixture.setUserData(null);
        this.world.fixtures.remove(fixture.addr);
        this.fixtures.removeValue(fixture, true);
        this.world.freeFixtures.free(fixture);
    }

    public void setTransform(Vector2 position, float angle) {
        jniSetTransform(this.addr, position.f102x, position.f103y, angle);
    }

    public void setTransform(float x, float y, float angle) {
        jniSetTransform(this.addr, x, y, angle);
    }

    public Transform getTransform() {
        jniGetTransform(this.addr, this.transform.vals);
        return this.transform;
    }

    public Vector2 getPosition() {
        jniGetPosition(this.addr, this.tmp);
        this.position.f102x = this.tmp[0];
        this.position.f103y = this.tmp[1];
        return this.position;
    }

    public float getAngle() {
        return jniGetAngle(this.addr);
    }

    public Vector2 getWorldCenter() {
        jniGetWorldCenter(this.addr, this.tmp);
        this.worldCenter.f102x = this.tmp[0];
        this.worldCenter.f103y = this.tmp[1];
        return this.worldCenter;
    }

    public Vector2 getLocalCenter() {
        jniGetLocalCenter(this.addr, this.tmp);
        this.localCenter.f102x = this.tmp[0];
        this.localCenter.f103y = this.tmp[1];
        return this.localCenter;
    }

    public void setLinearVelocity(Vector2 v) {
        jniSetLinearVelocity(this.addr, v.f102x, v.f103y);
    }

    public void setLinearVelocity(float vX, float vY) {
        jniSetLinearVelocity(this.addr, vX, vY);
    }

    public Vector2 getLinearVelocity() {
        jniGetLinearVelocity(this.addr, this.tmp);
        this.linearVelocity.f102x = this.tmp[0];
        this.linearVelocity.f103y = this.tmp[1];
        return this.linearVelocity;
    }

    public void setAngularVelocity(float omega) {
        jniSetAngularVelocity(this.addr, omega);
    }

    public float getAngularVelocity() {
        return jniGetAngularVelocity(this.addr);
    }

    public void applyForce(Vector2 force, Vector2 point, boolean wake) {
        jniApplyForce(this.addr, force.f102x, force.f103y, point.f102x, point.f103y, wake);
    }

    public void applyForce(float forceX, float forceY, float pointX, float pointY, boolean wake) {
        jniApplyForce(this.addr, forceX, forceY, pointX, pointY, wake);
    }

    public void applyForceToCenter(Vector2 force, boolean wake) {
        jniApplyForceToCenter(this.addr, force.f102x, force.f103y, wake);
    }

    public void applyForceToCenter(float forceX, float forceY, boolean wake) {
        jniApplyForceToCenter(this.addr, forceX, forceY, wake);
    }

    public void applyTorque(float torque, boolean wake) {
        jniApplyTorque(this.addr, torque, wake);
    }

    public void applyLinearImpulse(Vector2 impulse, Vector2 point, boolean wake) {
        jniApplyLinearImpulse(this.addr, impulse.f102x, impulse.f103y, point.f102x, point.f103y, wake);
    }

    public void applyLinearImpulse(float impulseX, float impulseY, float pointX, float pointY, boolean wake) {
        jniApplyLinearImpulse(this.addr, impulseX, impulseY, pointX, pointY, wake);
    }

    public void applyAngularImpulse(float impulse, boolean wake) {
        jniApplyAngularImpulse(this.addr, impulse, wake);
    }

    public float getMass() {
        return jniGetMass(this.addr);
    }

    public float getInertia() {
        return jniGetInertia(this.addr);
    }

    public MassData getMassData() {
        jniGetMassData(this.addr, this.tmp);
        this.massData.mass = this.tmp[0];
        this.massData.center.f102x = this.tmp[1];
        this.massData.center.f103y = this.tmp[2];
        this.massData.f72I = this.tmp[3];
        return this.massData;
    }

    public void setMassData(MassData data) {
        jniSetMassData(this.addr, data.mass, data.center.f102x, data.center.f103y, data.f72I);
    }

    public void resetMassData() {
        jniResetMassData(this.addr);
    }

    public Vector2 getWorldPoint(Vector2 localPoint) {
        jniGetWorldPoint(this.addr, localPoint.f102x, localPoint.f103y, this.tmp);
        this.localPoint.f102x = this.tmp[0];
        this.localPoint.f103y = this.tmp[1];
        return this.localPoint;
    }

    public Vector2 getWorldVector(Vector2 localVector) {
        jniGetWorldVector(this.addr, localVector.f102x, localVector.f103y, this.tmp);
        this.worldVector.f102x = this.tmp[0];
        this.worldVector.f103y = this.tmp[1];
        return this.worldVector;
    }

    public Vector2 getLocalPoint(Vector2 worldPoint) {
        jniGetLocalPoint(this.addr, worldPoint.f102x, worldPoint.f103y, this.tmp);
        this.localPoint2.f102x = this.tmp[0];
        this.localPoint2.f103y = this.tmp[1];
        return this.localPoint2;
    }

    public Vector2 getLocalVector(Vector2 worldVector) {
        jniGetLocalVector(this.addr, worldVector.f102x, worldVector.f103y, this.tmp);
        this.localVector.f102x = this.tmp[0];
        this.localVector.f103y = this.tmp[1];
        return this.localVector;
    }

    public Vector2 getLinearVelocityFromWorldPoint(Vector2 worldPoint) {
        jniGetLinearVelocityFromWorldPoint(this.addr, worldPoint.f102x, worldPoint.f103y, this.tmp);
        this.linVelWorld.f102x = this.tmp[0];
        this.linVelWorld.f103y = this.tmp[1];
        return this.linVelWorld;
    }

    public Vector2 getLinearVelocityFromLocalPoint(Vector2 localPoint) {
        jniGetLinearVelocityFromLocalPoint(this.addr, localPoint.f102x, localPoint.f103y, this.tmp);
        this.linVelLoc.f102x = this.tmp[0];
        this.linVelLoc.f103y = this.tmp[1];
        return this.linVelLoc;
    }

    public float getLinearDamping() {
        return jniGetLinearDamping(this.addr);
    }

    public void setLinearDamping(float linearDamping) {
        jniSetLinearDamping(this.addr, linearDamping);
    }

    public float getAngularDamping() {
        return jniGetAngularDamping(this.addr);
    }

    public void setAngularDamping(float angularDamping) {
        jniSetAngularDamping(this.addr, angularDamping);
    }

    public void setType(BodyType type) {
        jniSetType(this.addr, type.getValue());
    }

    public BodyType getType() {
        int type = jniGetType(this.addr);
        if (type == 0) {
            return BodyType.StaticBody;
        }
        if (type == 1) {
            return BodyType.KinematicBody;
        }
        if (type == 2) {
            return BodyType.DynamicBody;
        }
        return BodyType.StaticBody;
    }

    public void setBullet(boolean flag) {
        jniSetBullet(this.addr, flag);
    }

    public boolean isBullet() {
        return jniIsBullet(this.addr);
    }

    public void setSleepingAllowed(boolean flag) {
        jniSetSleepingAllowed(this.addr, flag);
    }

    public boolean isSleepingAllowed() {
        return jniIsSleepingAllowed(this.addr);
    }

    public void setAwake(boolean flag) {
        jniSetAwake(this.addr, flag);
    }

    public boolean isAwake() {
        return jniIsAwake(this.addr);
    }

    public void setActive(boolean flag) {
        if (flag) {
            jniSetActive(this.addr, flag);
        } else {
            this.world.deactivateBody(this);
        }
    }

    public boolean isActive() {
        return jniIsActive(this.addr);
    }

    public void setFixedRotation(boolean flag) {
        jniSetFixedRotation(this.addr, flag);
    }

    public boolean isFixedRotation() {
        return jniIsFixedRotation(this.addr);
    }

    public Array<Fixture> getFixtureList() {
        return this.fixtures;
    }

    public Array<JointEdge> getJointList() {
        return this.joints;
    }

    public float getGravityScale() {
        return jniGetGravityScale(this.addr);
    }

    public void setGravityScale(float scale) {
        jniSetGravityScale(this.addr, scale);
    }

    public World getWorld() {
        return this.world;
    }

    public Object getUserData() {
        return this.userData;
    }

    public void setUserData(Object userData) {
        this.userData = userData;
    }
}
