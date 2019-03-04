package com.ingokodba.com.Handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.net.HttpStatus;

public class MyInputProcessor extends InputAdapter {
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (x < 1150) {
            MyInput.setKey(0, true);
        }
        if (x > 1150) {
            MyInput.setKey(1, true);
        }
        if (x > 700 && x < 1300 && y < 800 && y > HttpStatus.SC_BAD_REQUEST) {
            MyInput.setKey(3, true);
        }
        if (x > 700 && x < 1250 && y > 450 && y < 700) {
            MyInput.setKey(4, true);
        }
        if (x > 100 && x < 1050 && y < 950 && y > 800) {
            MyInput.setKey(5, true);
        }
        if (x > 1050 && x < 1820 && y < 950 && y > 800) {
            MyInput.setKey(6, true);
        }
        return super.touchDown(x, y, pointer, button);
    }

    public boolean touchUp(int x, int y, int pointer, int button) {
        if (x < 1150) {
            MyInput.setKey(0, false);
        }
        if (x > 1150) {
            MyInput.setKey(1, false);
        }
        if (x > 700 && x < 1300 && y > 10 && y < 600) {
            MyInput.setKey(3, false);
        }
        if (x > 700 && x < 1250 && y > 450 && y < 700) {
            MyInput.setKey(4, false);
        }
        if (x > 100 && x < 1050 && y < 950 && y > 800) {
            MyInput.setKey(5, false);
        }
        if (x > 1050 && x < 1820 && y < 950 && y > 800) {
            MyInput.setKey(6, false);
        }
        return super.touchUp(x, y, pointer, button);
    }
}
