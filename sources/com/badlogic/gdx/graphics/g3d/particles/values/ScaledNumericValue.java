package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class ScaledNumericValue extends RangedNumericValue {
    private float highMax;
    private float highMin;
    private boolean relative = false;
    private float[] scaling = new float[]{1.0f};
    public float[] timeline = new float[]{0.0f};

    public float newHighValue() {
        return this.highMin + ((this.highMax - this.highMin) * MathUtils.random());
    }

    public void setHigh(float value) {
        this.highMin = value;
        this.highMax = value;
    }

    public void setHigh(float min, float max) {
        this.highMin = min;
        this.highMax = max;
    }

    public float getHighMin() {
        return this.highMin;
    }

    public void setHighMin(float highMin) {
        this.highMin = highMin;
    }

    public float getHighMax() {
        return this.highMax;
    }

    public void setHighMax(float highMax) {
        this.highMax = highMax;
    }

    public float[] getScaling() {
        return this.scaling;
    }

    public void setScaling(float[] values) {
        this.scaling = values;
    }

    public float[] getTimeline() {
        return this.timeline;
    }

    public void setTimeline(float[] timeline) {
        this.timeline = timeline;
    }

    public boolean isRelative() {
        return this.relative;
    }

    public void setRelative(boolean relative) {
        this.relative = relative;
    }

    public float getScale(float percent) {
        int endIndex = -1;
        int n = this.timeline.length;
        for (int i = 1; i < n; i++) {
            if (this.timeline[i] > percent) {
                endIndex = i;
                break;
            }
        }
        if (endIndex == -1) {
            return this.scaling[n - 1];
        }
        int startIndex = endIndex - 1;
        float startValue = this.scaling[startIndex];
        float startTime = this.timeline[startIndex];
        return ((this.scaling[endIndex] - startValue) * ((percent - startTime) / (this.timeline[endIndex] - startTime))) + startValue;
    }

    public void load(ScaledNumericValue value) {
        super.load(value);
        this.highMax = value.highMax;
        this.highMin = value.highMin;
        this.scaling = new float[value.scaling.length];
        System.arraycopy(value.scaling, 0, this.scaling, 0, this.scaling.length);
        this.timeline = new float[value.timeline.length];
        System.arraycopy(value.timeline, 0, this.timeline, 0, this.timeline.length);
        this.relative = value.relative;
    }

    public void write(Json json) {
        super.write(json);
        json.writeValue("highMin", Float.valueOf(this.highMin));
        json.writeValue("highMax", Float.valueOf(this.highMax));
        json.writeValue("relative", Boolean.valueOf(this.relative));
        json.writeValue("scaling", this.scaling);
        json.writeValue("timeline", this.timeline);
    }

    public void read(Json json, JsonValue jsonData) {
        super.read(json, jsonData);
        this.highMin = ((Float) json.readValue("highMin", Float.TYPE, jsonData)).floatValue();
        this.highMax = ((Float) json.readValue("highMax", Float.TYPE, jsonData)).floatValue();
        this.relative = ((Boolean) json.readValue("relative", Boolean.TYPE, jsonData)).booleanValue();
        this.scaling = (float[]) json.readValue("scaling", float[].class, jsonData);
        this.timeline = (float[]) json.readValue("timeline", float[].class, jsonData);
    }
}
