package com.ingokodba.com.Handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
    private int currentFrame;
    private float delay;
    private TextureRegion[] frames;
    private float time;
    private int timesPlayed;

    public Animation(TextureRegion[] frames) {
        this(frames, 0.083333336f);
    }

    public Animation(TextureRegion[] frames, float delay) {
        setFrames(frames, delay);
    }

    public void setFrames(TextureRegion[] frames, float delay) {
        this.frames = frames;
        this.delay = delay;
        this.time = 0.0f;
        this.currentFrame = 0;
        this.timesPlayed = 0;
    }

    public void update(float dt) {
        if (this.delay > 0.0f) {
            this.time += dt;
            while (this.time >= this.delay) {
                step();
            }
        }
    }

    private void step() {
        this.time -= this.delay;
        this.currentFrame++;
        if (this.currentFrame == this.frames.length) {
            this.currentFrame = 0;
            this.timesPlayed++;
        }
    }

    public TextureRegion getFrame() {
        return this.frames[this.currentFrame];
    }

    public int getTimesPlayed() {
        return this.timesPlayed;
    }
}
