package com.badlogic.gdx.physics.box2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;
import com.badlogic.gdx.physics.box2d.Shape.Type;
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import java.util.Iterator;

public class Box2DDebugRenderer implements Disposable {
    private static Vector2 axis = new Vector2();
    private static final Array<Body> bodies = new Array();
    private static final Array<Joint> joints = new Array();
    private static final Vector2 lower = new Vector2();
    /* renamed from: t */
    private static Vector2 f110t = new Vector2();
    private static final Vector2 upper = new Vector2();
    private static final Vector2[] vertices = new Vector2[1000];
    public final Color AABB_COLOR;
    public final Color JOINT_COLOR;
    public final Color SHAPE_AWAKE;
    public final Color SHAPE_KINEMATIC;
    public final Color SHAPE_NOT_ACTIVE;
    public final Color SHAPE_NOT_AWAKE;
    public final Color SHAPE_STATIC;
    public final Color VELOCITY_COLOR;
    private boolean drawAABBs;
    private boolean drawBodies;
    private boolean drawContacts;
    private boolean drawInactiveBodies;
    private boolean drawJoints;
    private boolean drawVelocities;
    /* renamed from: f */
    private final Vector2 f111f;
    private final Vector2 lv;
    protected ShapeRenderer renderer;
    /* renamed from: v */
    private final Vector2 f112v;

    public Box2DDebugRenderer() {
        this(true, true, false, true, false, true);
    }

    public Box2DDebugRenderer(boolean drawBodies, boolean drawJoints, boolean drawAABBs, boolean drawInactiveBodies, boolean drawVelocities, boolean drawContacts) {
        this.SHAPE_NOT_ACTIVE = new Color(0.5f, 0.5f, 0.3f, 1.0f);
        this.SHAPE_STATIC = new Color(0.5f, 0.9f, 0.5f, 1.0f);
        this.SHAPE_KINEMATIC = new Color(0.5f, 0.5f, 0.9f, 1.0f);
        this.SHAPE_NOT_AWAKE = new Color(0.6f, 0.6f, 0.6f, 1.0f);
        this.SHAPE_AWAKE = new Color(0.9f, 0.7f, 0.7f, 1.0f);
        this.JOINT_COLOR = new Color(0.5f, 0.8f, 0.8f, 1.0f);
        this.AABB_COLOR = new Color(1.0f, 0.0f, 1.0f, 1.0f);
        this.VELOCITY_COLOR = new Color(1.0f, 0.0f, 0.0f, 1.0f);
        this.f111f = new Vector2();
        this.f112v = new Vector2();
        this.lv = new Vector2();
        this.renderer = new ShapeRenderer();
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vector2();
        }
        this.drawBodies = drawBodies;
        this.drawJoints = drawJoints;
        this.drawAABBs = drawAABBs;
        this.drawInactiveBodies = drawInactiveBodies;
        this.drawVelocities = drawVelocities;
        this.drawContacts = drawContacts;
    }

    public void render(World world, Matrix4 projMatrix) {
        this.renderer.setProjectionMatrix(projMatrix);
        renderBodies(world);
    }

    private void renderBodies(World world) {
        this.renderer.begin(ShapeType.Line);
        if (this.drawBodies || this.drawAABBs) {
            world.getBodies(bodies);
            Iterator<Body> iter = bodies.iterator();
            while (iter.hasNext()) {
                Body body = (Body) iter.next();
                if (body.isActive() || this.drawInactiveBodies) {
                    renderBody(body);
                }
            }
        }
        if (this.drawJoints) {
            world.getJoints(joints);
            Iterator<Joint> iter2 = joints.iterator();
            while (iter2.hasNext()) {
                drawJoint((Joint) iter2.next());
            }
        }
        this.renderer.end();
        if (this.drawContacts) {
            this.renderer.begin(ShapeType.Point);
            Iterator i$ = world.getContactList().iterator();
            while (i$.hasNext()) {
                drawContact((Contact) i$.next());
            }
            this.renderer.end();
        }
    }

    protected void renderBody(Body body) {
        Transform transform = body.getTransform();
        Iterator i$ = body.getFixtureList().iterator();
        while (i$.hasNext()) {
            Fixture fixture = (Fixture) i$.next();
            if (this.drawBodies) {
                drawShape(fixture, transform, getColorByBody(body));
                if (this.drawVelocities) {
                    Vector2 position = body.getPosition();
                    drawSegment(position, body.getLinearVelocity().add(position), this.VELOCITY_COLOR);
                }
            }
            if (this.drawAABBs) {
                drawAABB(fixture, transform);
            }
        }
    }

    private Color getColorByBody(Body body) {
        if (!body.isActive()) {
            return this.SHAPE_NOT_ACTIVE;
        }
        if (body.getType() == BodyType.StaticBody) {
            return this.SHAPE_STATIC;
        }
        if (body.getType() == BodyType.KinematicBody) {
            return this.SHAPE_KINEMATIC;
        }
        if (body.isAwake()) {
            return this.SHAPE_AWAKE;
        }
        return this.SHAPE_NOT_AWAKE;
    }

    private void drawAABB(Fixture fixture, Transform transform) {
        if (fixture.getType() == Type.Circle) {
            CircleShape shape = (CircleShape) fixture.getShape();
            float radius = shape.getRadius();
            vertices[0].set(shape.getPosition());
            transform.mul(vertices[0]);
            lower.set(vertices[0].f102x - radius, vertices[0].f103y - radius);
            upper.set(vertices[0].f102x + radius, vertices[0].f103y + radius);
            vertices[0].set(lower.f102x, lower.f103y);
            vertices[1].set(upper.f102x, lower.f103y);
            vertices[2].set(upper.f102x, upper.f103y);
            vertices[3].set(lower.f102x, upper.f103y);
            drawSolidPolygon(vertices, 4, this.AABB_COLOR, true);
        } else if (fixture.getType() == Type.Polygon) {
            PolygonShape shape2 = (PolygonShape) fixture.getShape();
            int vertexCount = shape2.getVertexCount();
            shape2.getVertex(0, vertices[0]);
            lower.set(transform.mul(vertices[0]));
            upper.set(lower);
            for (int i = 1; i < vertexCount; i++) {
                shape2.getVertex(i, vertices[i]);
                transform.mul(vertices[i]);
                lower.f102x = Math.min(lower.f102x, vertices[i].f102x);
                lower.f103y = Math.min(lower.f103y, vertices[i].f103y);
                upper.f102x = Math.max(upper.f102x, vertices[i].f102x);
                upper.f103y = Math.max(upper.f103y, vertices[i].f103y);
            }
            vertices[0].set(lower.f102x, lower.f103y);
            vertices[1].set(upper.f102x, lower.f103y);
            vertices[2].set(upper.f102x, upper.f103y);
            vertices[3].set(lower.f102x, upper.f103y);
            drawSolidPolygon(vertices, 4, this.AABB_COLOR, true);
        }
    }

    private void drawShape(Fixture fixture, Transform transform, Color color) {
        if (fixture.getType() == Type.Circle) {
            CircleShape circle = (CircleShape) fixture.getShape();
            f110t.set(circle.getPosition());
            transform.mul(f110t);
            drawSolidCircle(f110t, circle.getRadius(), axis.set(transform.vals[2], transform.vals[3]), color);
        } else if (fixture.getType() == Type.Edge) {
            EdgeShape edge = (EdgeShape) fixture.getShape();
            edge.getVertex1(vertices[0]);
            edge.getVertex2(vertices[1]);
            transform.mul(vertices[0]);
            transform.mul(vertices[1]);
            drawSolidPolygon(vertices, 2, color, true);
        } else if (fixture.getType() == Type.Polygon) {
            PolygonShape chain = (PolygonShape) fixture.getShape();
            vertexCount = chain.getVertexCount();
            for (i = 0; i < vertexCount; i++) {
                chain.getVertex(i, vertices[i]);
                transform.mul(vertices[i]);
            }
            drawSolidPolygon(vertices, vertexCount, color, true);
        } else if (fixture.getType() == Type.Chain) {
            ChainShape chain2 = (ChainShape) fixture.getShape();
            vertexCount = chain2.getVertexCount();
            for (i = 0; i < vertexCount; i++) {
                chain2.getVertex(i, vertices[i]);
                transform.mul(vertices[i]);
            }
            drawSolidPolygon(vertices, vertexCount, color, false);
        }
    }

    private void drawSolidCircle(Vector2 center, float radius, Vector2 axis, Color color) {
        float angle = 0.0f;
        this.renderer.setColor(color.f39r, color.f38g, color.f37b, color.f36a);
        int i = 0;
        while (i < 20) {
            this.f112v.set((((float) Math.cos((double) angle)) * radius) + center.f102x, (((float) Math.sin((double) angle)) * radius) + center.f103y);
            if (i == 0) {
                this.lv.set(this.f112v);
                this.f111f.set(this.f112v);
            } else {
                this.renderer.line(this.lv.f102x, this.lv.f103y, this.f112v.f102x, this.f112v.f103y);
                this.lv.set(this.f112v);
            }
            i++;
            angle += 0.31415927f;
        }
        this.renderer.line(this.f111f.f102x, this.f111f.f103y, this.lv.f102x, this.lv.f103y);
        this.renderer.line(center.f102x, center.f103y, 0.0f, center.f102x + (axis.f102x * radius), center.f103y + (axis.f103y * radius), 0.0f);
    }

    private void drawSolidPolygon(Vector2[] vertices, int vertexCount, Color color, boolean closed) {
        this.renderer.setColor(color.f39r, color.f38g, color.f37b, color.f36a);
        this.lv.set(vertices[0]);
        this.f111f.set(vertices[0]);
        for (int i = 1; i < vertexCount; i++) {
            Vector2 v = vertices[i];
            this.renderer.line(this.lv.f102x, this.lv.f103y, v.f102x, v.f103y);
            this.lv.set(v);
        }
        if (closed) {
            this.renderer.line(this.f111f.f102x, this.f111f.f103y, this.lv.f102x, this.lv.f103y);
        }
    }

    private void drawJoint(Joint joint) {
        Body bodyA = joint.getBodyA();
        Body bodyB = joint.getBodyB();
        Transform xf1 = bodyA.getTransform();
        Transform xf2 = bodyB.getTransform();
        Vector2 x1 = xf1.getPosition();
        Vector2 x2 = xf2.getPosition();
        Vector2 p1 = joint.getAnchorA();
        Vector2 p2 = joint.getAnchorB();
        if (joint.getType() == JointType.DistanceJoint) {
            drawSegment(p1, p2, this.JOINT_COLOR);
        } else if (joint.getType() == JointType.PulleyJoint) {
            PulleyJoint pulley = (PulleyJoint) joint;
            Vector2 s1 = pulley.getGroundAnchorA();
            Vector2 s2 = pulley.getGroundAnchorB();
            drawSegment(s1, p1, this.JOINT_COLOR);
            drawSegment(s2, p2, this.JOINT_COLOR);
            drawSegment(s1, s2, this.JOINT_COLOR);
        } else if (joint.getType() == JointType.MouseJoint) {
            drawSegment(joint.getAnchorA(), joint.getAnchorB(), this.JOINT_COLOR);
        } else {
            drawSegment(x1, p1, this.JOINT_COLOR);
            drawSegment(p1, p2, this.JOINT_COLOR);
            drawSegment(x2, p2, this.JOINT_COLOR);
        }
    }

    private void drawSegment(Vector2 x1, Vector2 x2, Color color) {
        this.renderer.setColor(color);
        this.renderer.line(x1.f102x, x1.f103y, x2.f102x, x2.f103y);
    }

    private void drawContact(Contact contact) {
        WorldManifold worldManifold = contact.getWorldManifold();
        if (worldManifold.getNumberOfContactPoints() != 0) {
            Vector2 point = worldManifold.getPoints()[0];
            this.renderer.setColor(getColorByBody(contact.getFixtureA().getBody()));
            this.renderer.point(point.f102x, point.f103y, 0.0f);
        }
    }

    public boolean isDrawBodies() {
        return this.drawBodies;
    }

    public void setDrawBodies(boolean drawBodies) {
        this.drawBodies = drawBodies;
    }

    public boolean isDrawJoints() {
        return this.drawJoints;
    }

    public void setDrawJoints(boolean drawJoints) {
        this.drawJoints = drawJoints;
    }

    public boolean isDrawAABBs() {
        return this.drawAABBs;
    }

    public void setDrawAABBs(boolean drawAABBs) {
        this.drawAABBs = drawAABBs;
    }

    public boolean isDrawInactiveBodies() {
        return this.drawInactiveBodies;
    }

    public void setDrawInactiveBodies(boolean drawInactiveBodies) {
        this.drawInactiveBodies = drawInactiveBodies;
    }

    public boolean isDrawVelocities() {
        return this.drawVelocities;
    }

    public void setDrawVelocities(boolean drawVelocities) {
        this.drawVelocities = drawVelocities;
    }

    public boolean isDrawContacts() {
        return this.drawContacts;
    }

    public void setDrawContacts(boolean drawContacts) {
        this.drawContacts = drawContacts;
    }

    public static Vector2 getAxis() {
        return axis;
    }

    public static void setAxis(Vector2 axis) {
        axis = axis;
    }

    public void dispose() {
        this.renderer.dispose();
    }
}
