package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private float animationDuration;
    private float frameDuration;
    final TextureRegion[] keyFrames;
    private int lastFrameNumber;
    private float lastStateTime;
    private PlayMode playMode = PlayMode.NORMAL;

    public enum PlayMode {
        NORMAL,
        REVERSED,
        LOOP,
        LOOP_REVERSED,
        LOOP_PINGPONG,
        LOOP_RANDOM
    }

    public Animation(float frameDuration, Array<? extends TextureRegion> keyFrames) {
        this.frameDuration = frameDuration;
        this.animationDuration = ((float) keyFrames.size) * frameDuration;
        this.keyFrames = new TextureRegion[keyFrames.size];
        int n = keyFrames.size;
        for (int i = 0; i < n; i++) {
            this.keyFrames[i] = (TextureRegion) keyFrames.get(i);
        }
        this.playMode = PlayMode.NORMAL;
    }

    public Animation(float frameDuration, Array<? extends TextureRegion> keyFrames, PlayMode playMode) {
        this.frameDuration = frameDuration;
        this.animationDuration = ((float) keyFrames.size) * frameDuration;
        this.keyFrames = new TextureRegion[keyFrames.size];
        int n = keyFrames.size;
        for (int i = 0; i < n; i++) {
            this.keyFrames[i] = (TextureRegion) keyFrames.get(i);
        }
        this.playMode = playMode;
    }

    public Animation(float frameDuration, TextureRegion... keyFrames) {
        this.frameDuration = frameDuration;
        this.animationDuration = ((float) keyFrames.length) * frameDuration;
        this.keyFrames = keyFrames;
        this.playMode = PlayMode.NORMAL;
    }

    public TextureRegion getKeyFrame(float stateTime, boolean looping) {
        PlayMode oldPlayMode = this.playMode;
        if (looping && (this.playMode == PlayMode.NORMAL || this.playMode == PlayMode.REVERSED)) {
            if (this.playMode == PlayMode.NORMAL) {
                this.playMode = PlayMode.LOOP;
            } else {
                this.playMode = PlayMode.LOOP_REVERSED;
            }
        } else if (!(looping || this.playMode == PlayMode.NORMAL || this.playMode == PlayMode.REVERSED)) {
            if (this.playMode == PlayMode.LOOP_REVERSED) {
                this.playMode = PlayMode.REVERSED;
            } else {
                this.playMode = PlayMode.LOOP;
            }
        }
        TextureRegion frame = getKeyFrame(stateTime);
        this.playMode = oldPlayMode;
        return frame;
    }

    public TextureRegion getKeyFrame(float stateTime) {
        return this.keyFrames[getKeyFrameIndex(stateTime)];
    }

    public int getKeyFrameIndex(float stateTime) {
        if (this.keyFrames.length == 1) {
            return 0;
        }
        int frameNumber = (int) (stateTime / this.frameDuration);
        switch (this.playMode) {
            case NORMAL:
                frameNumber = Math.min(this.keyFrames.length - 1, frameNumber);
                break;
            case LOOP:
                frameNumber %= this.keyFrames.length;
                break;
            case LOOP_PINGPONG:
                frameNumber %= (this.keyFrames.length * 2) - 2;
                if (frameNumber >= this.keyFrames.length) {
                    frameNumber = (this.keyFrames.length - 2) - (frameNumber - this.keyFrames.length);
                    break;
                }
                break;
            case LOOP_RANDOM:
                if (((int) (this.lastStateTime / this.frameDuration)) == frameNumber) {
                    frameNumber = this.lastFrameNumber;
                    break;
                }
                frameNumber = MathUtils.random(this.keyFrames.length - 1);
                break;
            case REVERSED:
                frameNumber = Math.max((this.keyFrames.length - frameNumber) - 1, 0);
                break;
            case LOOP_REVERSED:
                frameNumber = (this.keyFrames.length - (frameNumber % this.keyFrames.length)) - 1;
                break;
        }
        this.lastFrameNumber = frameNumber;
        this.lastStateTime = stateTime;
        return frameNumber;
    }

    public TextureRegion[] getKeyFrames() {
        return this.keyFrames;
    }

    public PlayMode getPlayMode() {
        return this.playMode;
    }

    public void setPlayMode(PlayMode playMode) {
        this.playMode = playMode;
    }

    public boolean isAnimationFinished(float stateTime) {
        return this.keyFrames.length + -1 < ((int) (stateTime / this.frameDuration));
    }

    public void setFrameDuration(float frameDuration) {
        this.frameDuration = frameDuration;
        this.animationDuration = ((float) this.keyFrames.length) * frameDuration;
    }

    public float getFrameDuration() {
        return this.frameDuration;
    }

    public float getAnimationDuration() {
        return this.animationDuration;
    }
}
