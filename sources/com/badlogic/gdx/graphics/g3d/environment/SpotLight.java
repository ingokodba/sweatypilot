package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class SpotLight extends BaseLight {
    public float cutoffAngle;
    public final Vector3 direction = new Vector3();
    public float intensity;
    public final Vector3 position = new Vector3();

    public SpotLight set(SpotLight copyFrom) {
        return set(copyFrom.color, copyFrom.position, copyFrom.direction, copyFrom.intensity, copyFrom.cutoffAngle);
    }

    public SpotLight set(Color color, Vector3 position, Vector3 direction, float intensity, float cutoffAngle) {
        if (color != null) {
            this.color.set(color);
        }
        if (position != null) {
            this.position.set(position);
        }
        if (direction != null) {
            this.direction.set(direction).nor();
        }
        this.intensity = intensity;
        this.cutoffAngle = cutoffAngle;
        return this;
    }

    public SpotLight set(float r, float g, float b, Vector3 position, Vector3 direction, float intensity, float cutoffAngle) {
        this.color.set(r, g, b, 1.0f);
        if (position != null) {
            this.position.set(position);
        }
        if (direction != null) {
            this.direction.set(direction).nor();
        }
        this.intensity = intensity;
        this.cutoffAngle = cutoffAngle;
        return this;
    }

    public SpotLight set(Color color, float posX, float posY, float posZ, float dirX, float dirY, float dirZ, float intensity, float cutoffAngle) {
        if (color != null) {
            this.color.set(color);
        }
        this.position.set(posX, posY, posZ);
        this.direction.set(dirX, dirY, dirZ).nor();
        this.intensity = intensity;
        this.cutoffAngle = cutoffAngle;
        return this;
    }

    public SpotLight set(float r, float g, float b, float posX, float posY, float posZ, float dirX, float dirY, float dirZ, float intensity, float cutoffAngle) {
        this.color.set(r, g, b, 1.0f);
        this.position.set(posX, posY, posZ);
        this.direction.set(dirX, dirY, dirZ).nor();
        this.intensity = intensity;
        this.cutoffAngle = cutoffAngle;
        return this;
    }

    public SpotLight setTarget(Vector3 target) {
        this.direction.set(target).sub(this.position).nor();
        return this;
    }

    public boolean equals(Object obj) {
        return obj instanceof SpotLight ? equals((SpotLight) obj) : false;
    }

    public boolean equals(SpotLight other) {
        return other != null && (other == this || (this.color.equals(other.color) && this.position.equals(other.position) && this.direction.equals(other.direction) && MathUtils.isEqual(this.intensity, other.intensity) && MathUtils.isEqual(this.cutoffAngle, other.cutoffAngle)));
    }
}
