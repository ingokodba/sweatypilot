package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class ScissorStack {
    private static Array<Rectangle> scissors = new Array();
    static Vector3 tmp = new Vector3();
    static final Rectangle viewport = new Rectangle();

    public static boolean pushScissors(Rectangle scissor) {
        fix(scissor);
        if (scissors.size != 0) {
            Rectangle parent = (Rectangle) scissors.get(scissors.size - 1);
            float minX = Math.max(parent.f98x, scissor.f98x);
            float maxX = Math.min(parent.f98x + parent.width, scissor.f98x + scissor.width);
            if (maxX - minX < 1.0f) {
                return false;
            }
            float minY = Math.max(parent.f99y, scissor.f99y);
            float maxY = Math.min(parent.f99y + parent.height, scissor.f99y + scissor.height);
            if (maxY - minY < 1.0f) {
                return false;
            }
            scissor.f98x = minX;
            scissor.f99y = minY;
            scissor.width = maxX - minX;
            scissor.height = Math.max(1.0f, maxY - minY);
        } else if (scissor.width < 1.0f || scissor.height < 1.0f) {
            return false;
        } else {
            Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
        }
        scissors.add(scissor);
        Gdx.gl.glScissor((int) scissor.f98x, (int) scissor.f99y, (int) scissor.width, (int) scissor.height);
        return true;
    }

    public static Rectangle popScissors() {
        Rectangle old = (Rectangle) scissors.pop();
        if (scissors.size == 0) {
            Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
        } else {
            Rectangle scissor = (Rectangle) scissors.peek();
            Gdx.gl.glScissor((int) scissor.f98x, (int) scissor.f99y, (int) scissor.width, (int) scissor.height);
        }
        return old;
    }

    public static Rectangle peekScissors() {
        return (Rectangle) scissors.peek();
    }

    private static void fix(Rectangle rect) {
        rect.f98x = (float) Math.round(rect.f98x);
        rect.f99y = (float) Math.round(rect.f99y);
        rect.width = (float) Math.round(rect.width);
        rect.height = (float) Math.round(rect.height);
        if (rect.width < 0.0f) {
            rect.width = -rect.width;
            rect.f98x -= rect.width;
        }
        if (rect.height < 0.0f) {
            rect.height = -rect.height;
            rect.f99y -= rect.height;
        }
    }

    public static void calculateScissors(Camera camera, Matrix4 batchTransform, Rectangle area, Rectangle scissor) {
        calculateScissors(camera, 0.0f, 0.0f, (float) Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight(), batchTransform, area, scissor);
    }

    public static void calculateScissors(Camera camera, float viewportX, float viewportY, float viewportWidth, float viewportHeight, Matrix4 batchTransform, Rectangle area, Rectangle scissor) {
        tmp.set(area.f98x, area.f99y, 0.0f);
        tmp.mul(batchTransform);
        camera.project(tmp, viewportX, viewportY, viewportWidth, viewportHeight);
        scissor.f98x = tmp.f107x;
        scissor.f99y = tmp.f108y;
        tmp.set(area.f98x + area.width, area.f99y + area.height, 0.0f);
        tmp.mul(batchTransform);
        camera.project(tmp, viewportX, viewportY, viewportWidth, viewportHeight);
        scissor.width = tmp.f107x - scissor.f98x;
        scissor.height = tmp.f108y - scissor.f99y;
    }

    public static Rectangle getViewport() {
        if (scissors.size == 0) {
            viewport.set(0.0f, 0.0f, (float) Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight());
            return viewport;
        }
        viewport.set((Rectangle) scissors.peek());
        return viewport;
    }
}
