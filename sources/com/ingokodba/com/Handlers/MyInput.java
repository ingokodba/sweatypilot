package com.ingokodba.com.Handlers;

public class MyInput {
    public static final int BUTTON1 = 0;
    public static final int BUTTON2 = 1;
    public static final int MUSIC = 6;
    public static final int NUM_KEYS = 2;
    public static final int RESTART_PLAY = 4;
    public static final int SOUND = 5;
    public static final int START_PLAY = 3;
    public static boolean[] keys = new boolean[7];
    public static boolean[] pkeys = new boolean[7];

    public static void update() {
        for (int i = 0; i < 7; i++) {
            pkeys[i] = keys[i];
        }
    }

    public static void setKey(int i, boolean b) {
        keys[i] = b;
    }

    public static boolean isDown(int i) {
        return keys[i];
    }

    public static boolean isPressed(int i) {
        return keys[i] && !pkeys[i];
    }
}
