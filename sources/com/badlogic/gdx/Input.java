package com.badlogic.gdx;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.ObjectIntMap;

public interface Input {

    public static class Buttons {
        public static final int BACK = 3;
        public static final int FORWARD = 4;
        public static final int LEFT = 0;
        public static final int MIDDLE = 2;
        public static final int RIGHT = 1;
    }

    public static class Keys {
        /* renamed from: A */
        public static final int f4A = 29;
        public static final int ALT_LEFT = 57;
        public static final int ALT_RIGHT = 58;
        public static final int ANY_KEY = -1;
        public static final int APOSTROPHE = 75;
        public static final int AT = 77;
        /* renamed from: B */
        public static final int f5B = 30;
        public static final int BACK = 4;
        public static final int BACKSLASH = 73;
        public static final int BACKSPACE = 67;
        public static final int BUTTON_A = 96;
        public static final int BUTTON_B = 97;
        public static final int BUTTON_C = 98;
        public static final int BUTTON_CIRCLE = 255;
        public static final int BUTTON_L1 = 102;
        public static final int BUTTON_L2 = 104;
        public static final int BUTTON_MODE = 110;
        public static final int BUTTON_R1 = 103;
        public static final int BUTTON_R2 = 105;
        public static final int BUTTON_SELECT = 109;
        public static final int BUTTON_START = 108;
        public static final int BUTTON_THUMBL = 106;
        public static final int BUTTON_THUMBR = 107;
        public static final int BUTTON_X = 99;
        public static final int BUTTON_Y = 100;
        public static final int BUTTON_Z = 101;
        /* renamed from: C */
        public static final int f6C = 31;
        public static final int CALL = 5;
        public static final int CAMERA = 27;
        public static final int CENTER = 23;
        public static final int CLEAR = 28;
        public static final int COLON = 243;
        public static final int COMMA = 55;
        public static final int CONTROL_LEFT = 129;
        public static final int CONTROL_RIGHT = 130;
        /* renamed from: D */
        public static final int f7D = 32;
        public static final int DEL = 67;
        public static final int DOWN = 20;
        public static final int DPAD_CENTER = 23;
        public static final int DPAD_DOWN = 20;
        public static final int DPAD_LEFT = 21;
        public static final int DPAD_RIGHT = 22;
        public static final int DPAD_UP = 19;
        /* renamed from: E */
        public static final int f8E = 33;
        public static final int END = 132;
        public static final int ENDCALL = 6;
        public static final int ENTER = 66;
        public static final int ENVELOPE = 65;
        public static final int EQUALS = 70;
        public static final int ESCAPE = 131;
        public static final int EXPLORER = 64;
        /* renamed from: F */
        public static final int f9F = 34;
        public static final int F1 = 244;
        public static final int F10 = 253;
        public static final int F11 = 254;
        public static final int F12 = 255;
        public static final int F2 = 245;
        public static final int F3 = 246;
        public static final int F4 = 247;
        public static final int F5 = 248;
        public static final int F6 = 249;
        public static final int F7 = 250;
        public static final int F8 = 251;
        public static final int F9 = 252;
        public static final int FOCUS = 80;
        public static final int FORWARD_DEL = 112;
        /* renamed from: G */
        public static final int f10G = 35;
        public static final int GRAVE = 68;
        /* renamed from: H */
        public static final int f11H = 36;
        public static final int HEADSETHOOK = 79;
        public static final int HOME = 3;
        /* renamed from: I */
        public static final int f12I = 37;
        public static final int INSERT = 133;
        /* renamed from: J */
        public static final int f13J = 38;
        /* renamed from: K */
        public static final int f14K = 39;
        /* renamed from: L */
        public static final int f15L = 40;
        public static final int LEFT = 21;
        public static final int LEFT_BRACKET = 71;
        /* renamed from: M */
        public static final int f16M = 41;
        public static final int MEDIA_FAST_FORWARD = 90;
        public static final int MEDIA_NEXT = 87;
        public static final int MEDIA_PLAY_PAUSE = 85;
        public static final int MEDIA_PREVIOUS = 88;
        public static final int MEDIA_REWIND = 89;
        public static final int MEDIA_STOP = 86;
        public static final int MENU = 82;
        public static final int META_ALT_LEFT_ON = 16;
        public static final int META_ALT_ON = 2;
        public static final int META_ALT_RIGHT_ON = 32;
        public static final int META_SHIFT_LEFT_ON = 64;
        public static final int META_SHIFT_ON = 1;
        public static final int META_SHIFT_RIGHT_ON = 128;
        public static final int META_SYM_ON = 4;
        public static final int MINUS = 69;
        public static final int MUTE = 91;
        /* renamed from: N */
        public static final int f17N = 42;
        public static final int NOTIFICATION = 83;
        public static final int NUM = 78;
        public static final int NUMPAD_0 = 144;
        public static final int NUMPAD_1 = 145;
        public static final int NUMPAD_2 = 146;
        public static final int NUMPAD_3 = 147;
        public static final int NUMPAD_4 = 148;
        public static final int NUMPAD_5 = 149;
        public static final int NUMPAD_6 = 150;
        public static final int NUMPAD_7 = 151;
        public static final int NUMPAD_8 = 152;
        public static final int NUMPAD_9 = 153;
        public static final int NUM_0 = 7;
        public static final int NUM_1 = 8;
        public static final int NUM_2 = 9;
        public static final int NUM_3 = 10;
        public static final int NUM_4 = 11;
        public static final int NUM_5 = 12;
        public static final int NUM_6 = 13;
        public static final int NUM_7 = 14;
        public static final int NUM_8 = 15;
        public static final int NUM_9 = 16;
        /* renamed from: O */
        public static final int f18O = 43;
        /* renamed from: P */
        public static final int f19P = 44;
        public static final int PAGE_DOWN = 93;
        public static final int PAGE_UP = 92;
        public static final int PERIOD = 56;
        public static final int PICTSYMBOLS = 94;
        public static final int PLUS = 81;
        public static final int POUND = 18;
        public static final int POWER = 26;
        /* renamed from: Q */
        public static final int f20Q = 45;
        /* renamed from: R */
        public static final int f21R = 46;
        public static final int RIGHT = 22;
        public static final int RIGHT_BRACKET = 72;
        /* renamed from: S */
        public static final int f22S = 47;
        public static final int SEARCH = 84;
        public static final int SEMICOLON = 74;
        public static final int SHIFT_LEFT = 59;
        public static final int SHIFT_RIGHT = 60;
        public static final int SLASH = 76;
        public static final int SOFT_LEFT = 1;
        public static final int SOFT_RIGHT = 2;
        public static final int SPACE = 62;
        public static final int STAR = 17;
        public static final int SWITCH_CHARSET = 95;
        public static final int SYM = 63;
        /* renamed from: T */
        public static final int f23T = 48;
        public static final int TAB = 61;
        /* renamed from: U */
        public static final int f24U = 49;
        public static final int UNKNOWN = 0;
        public static final int UP = 19;
        /* renamed from: V */
        public static final int f25V = 50;
        public static final int VOLUME_DOWN = 25;
        public static final int VOLUME_UP = 24;
        /* renamed from: W */
        public static final int f26W = 51;
        /* renamed from: X */
        public static final int f27X = 52;
        /* renamed from: Y */
        public static final int f28Y = 53;
        /* renamed from: Z */
        public static final int f29Z = 54;
        private static ObjectIntMap<String> keyNames;

        public static String toString(int keycode) {
            if (keycode < 0) {
                throw new IllegalArgumentException("keycode cannot be negative, keycode: " + keycode);
            } else if (keycode > 255) {
                throw new IllegalArgumentException("keycode cannot be greater than 255, keycode: " + keycode);
            } else {
                switch (keycode) {
                    case 0:
                        return "Unknown";
                    case 1:
                        return "Soft Left";
                    case 2:
                        return "Soft Right";
                    case 3:
                        return "Home";
                    case 4:
                        return "Back";
                    case 5:
                        return "Call";
                    case 6:
                        return "End Call";
                    case 7:
                        return "0";
                    case 8:
                        return "1";
                    case 9:
                        return "2";
                    case 10:
                        return "3";
                    case 11:
                        return "4";
                    case 12:
                        return "5";
                    case 13:
                        return "6";
                    case 14:
                        return "7";
                    case 15:
                        return "8";
                    case 16:
                        return "9";
                    case 17:
                        return "*";
                    case 18:
                        return "#";
                    case 19:
                        return "Up";
                    case 20:
                        return "Down";
                    case 21:
                        return "Left";
                    case 22:
                        return "Right";
                    case 23:
                        return "Center";
                    case 24:
                        return "Volume Up";
                    case VOLUME_DOWN /*25*/:
                        return "Volume Down";
                    case POWER /*26*/:
                        return "Power";
                    case CAMERA /*27*/:
                        return "Camera";
                    case CLEAR /*28*/:
                        return "Clear";
                    case f4A /*29*/:
                        return "A";
                    case f5B /*30*/:
                        return "B";
                    case f6C /*31*/:
                        return "C";
                    case 32:
                        return "D";
                    case f8E /*33*/:
                        return "E";
                    case f9F /*34*/:
                        return "F";
                    case f10G /*35*/:
                        return "G";
                    case f11H /*36*/:
                        return "H";
                    case f12I /*37*/:
                        return "I";
                    case f13J /*38*/:
                        return "J";
                    case f14K /*39*/:
                        return "K";
                    case f15L /*40*/:
                        return "L";
                    case f16M /*41*/:
                        return "M";
                    case f17N /*42*/:
                        return "N";
                    case f18O /*43*/:
                        return "O";
                    case f19P /*44*/:
                        return "P";
                    case f20Q /*45*/:
                        return "Q";
                    case f21R /*46*/:
                        return "R";
                    case f22S /*47*/:
                        return "S";
                    case f23T /*48*/:
                        return "T";
                    case f24U /*49*/:
                        return "U";
                    case f25V /*50*/:
                        return "V";
                    case f26W /*51*/:
                        return "W";
                    case f27X /*52*/:
                        return "X";
                    case f28Y /*53*/:
                        return "Y";
                    case f29Z /*54*/:
                        return "Z";
                    case COMMA /*55*/:
                        return ",";
                    case PERIOD /*56*/:
                        return ".";
                    case ALT_LEFT /*57*/:
                        return "L-Alt";
                    case ALT_RIGHT /*58*/:
                        return "R-Alt";
                    case SHIFT_LEFT /*59*/:
                        return "L-Shift";
                    case SHIFT_RIGHT /*60*/:
                        return "R-Shift";
                    case TAB /*61*/:
                        return "Tab";
                    case SPACE /*62*/:
                        return "Space";
                    case SYM /*63*/:
                        return "SYM";
                    case 64:
                        return "Explorer";
                    case ENVELOPE /*65*/:
                        return "Envelope";
                    case ENTER /*66*/:
                        return "Enter";
                    case 67:
                        return "Delete";
                    case GRAVE /*68*/:
                        return "`";
                    case MINUS /*69*/:
                        return "-";
                    case EQUALS /*70*/:
                        return "=";
                    case LEFT_BRACKET /*71*/:
                        return "[";
                    case RIGHT_BRACKET /*72*/:
                        return "]";
                    case BACKSLASH /*73*/:
                        return "\\";
                    case SEMICOLON /*74*/:
                        return ";";
                    case APOSTROPHE /*75*/:
                        return "'";
                    case SLASH /*76*/:
                        return "/";
                    case AT /*77*/:
                        return "@";
                    case NUM /*78*/:
                        return "Num";
                    case HEADSETHOOK /*79*/:
                        return "Headset Hook";
                    case FOCUS /*80*/:
                        return "Focus";
                    case PLUS /*81*/:
                        return "Plus";
                    case MENU /*82*/:
                        return "Menu";
                    case NOTIFICATION /*83*/:
                        return "Notification";
                    case SEARCH /*84*/:
                        return "Search";
                    case MEDIA_PLAY_PAUSE /*85*/:
                        return "Play/Pause";
                    case MEDIA_STOP /*86*/:
                        return "Stop Media";
                    case MEDIA_NEXT /*87*/:
                        return "Next Media";
                    case MEDIA_PREVIOUS /*88*/:
                        return "Prev Media";
                    case MEDIA_REWIND /*89*/:
                        return "Rewind";
                    case MEDIA_FAST_FORWARD /*90*/:
                        return "Fast Forward";
                    case MUTE /*91*/:
                        return "Mute";
                    case PAGE_UP /*92*/:
                        return "Page Up";
                    case PAGE_DOWN /*93*/:
                        return "Page Down";
                    case PICTSYMBOLS /*94*/:
                        return "PICTSYMBOLS";
                    case SWITCH_CHARSET /*95*/:
                        return "SWITCH_CHARSET";
                    case BUTTON_A /*96*/:
                        return "A Button";
                    case BUTTON_B /*97*/:
                        return "B Button";
                    case BUTTON_C /*98*/:
                        return "C Button";
                    case BUTTON_X /*99*/:
                        return "X Button";
                    case 100:
                        return "Y Button";
                    case 101:
                        return "Z Button";
                    case 102:
                        return "L1 Button";
                    case BUTTON_R1 /*103*/:
                        return "R1 Button";
                    case BUTTON_L2 /*104*/:
                        return "L2 Button";
                    case BUTTON_R2 /*105*/:
                        return "R2 Button";
                    case BUTTON_THUMBL /*106*/:
                        return "Left Thumb";
                    case BUTTON_THUMBR /*107*/:
                        return "Right Thumb";
                    case BUTTON_START /*108*/:
                        return "Start";
                    case BUTTON_SELECT /*109*/:
                        return "Select";
                    case BUTTON_MODE /*110*/:
                        return "Button Mode";
                    case FORWARD_DEL /*112*/:
                        return "Forward Delete";
                    case CONTROL_LEFT /*129*/:
                        return "L-Ctrl";
                    case CONTROL_RIGHT /*130*/:
                        return "R-Ctrl";
                    case ESCAPE /*131*/:
                        return "Escape";
                    case END /*132*/:
                        return "End";
                    case INSERT /*133*/:
                        return "Insert";
                    case NUMPAD_0 /*144*/:
                        return "Numpad 0";
                    case NUMPAD_1 /*145*/:
                        return "Numpad 1";
                    case NUMPAD_2 /*146*/:
                        return "Numpad 2";
                    case NUMPAD_3 /*147*/:
                        return "Numpad 3";
                    case NUMPAD_4 /*148*/:
                        return "Numpad 4";
                    case NUMPAD_5 /*149*/:
                        return "Numpad 5";
                    case NUMPAD_6 /*150*/:
                        return "Numpad 6";
                    case NUMPAD_7 /*151*/:
                        return "Numpad 7";
                    case NUMPAD_8 /*152*/:
                        return "Numpad 8";
                    case NUMPAD_9 /*153*/:
                        return "Numpad 9";
                    case COLON /*243*/:
                        return ":";
                    case F1 /*244*/:
                        return "F1";
                    case F2 /*245*/:
                        return "F2";
                    case F3 /*246*/:
                        return "F3";
                    case F4 /*247*/:
                        return "F4";
                    case F5 /*248*/:
                        return "F5";
                    case F6 /*249*/:
                        return "F6";
                    case F7 /*250*/:
                        return "F7";
                    case F8 /*251*/:
                        return "F8";
                    case F9 /*252*/:
                        return "F9";
                    case F10 /*253*/:
                        return "F10";
                    case F11 /*254*/:
                        return "F11";
                    case 255:
                        return "F12";
                    default:
                        return null;
                }
            }
        }

        public static int valueOf(String keyname) {
            if (keyNames == null) {
                initializeKeyNames();
            }
            return keyNames.get(keyname, -1);
        }

        private static void initializeKeyNames() {
            keyNames = new ObjectIntMap();
            for (int i = 0; i < 256; i++) {
                String name = toString(i);
                if (name != null) {
                    keyNames.put(name, i);
                }
            }
        }
    }

    public enum Orientation {
        Landscape,
        Portrait
    }

    public enum Peripheral {
        HardwareKeyboard,
        OnscreenKeyboard,
        MultitouchScreen,
        Accelerometer,
        Compass,
        Vibrator
    }

    public interface TextInputListener {
        void canceled();

        void input(String str);
    }

    void cancelVibrate();

    float getAccelerometerX();

    float getAccelerometerY();

    float getAccelerometerZ();

    float getAzimuth();

    long getCurrentEventTime();

    int getDeltaX();

    int getDeltaX(int i);

    int getDeltaY();

    int getDeltaY(int i);

    InputProcessor getInputProcessor();

    Orientation getNativeOrientation();

    float getPitch();

    float getRoll();

    int getRotation();

    void getRotationMatrix(float[] fArr);

    void getTextInput(TextInputListener textInputListener, String str, String str2, String str3);

    int getX();

    int getX(int i);

    int getY();

    int getY(int i);

    boolean isButtonPressed(int i);

    boolean isCatchBackKey();

    boolean isCursorCatched();

    boolean isKeyJustPressed(int i);

    boolean isKeyPressed(int i);

    boolean isPeripheralAvailable(Peripheral peripheral);

    boolean isTouched();

    boolean isTouched(int i);

    boolean justTouched();

    void setCatchBackKey(boolean z);

    void setCatchMenuKey(boolean z);

    void setCursorCatched(boolean z);

    void setCursorImage(Pixmap pixmap, int i, int i2);

    void setCursorPosition(int i, int i2);

    void setInputProcessor(InputProcessor inputProcessor);

    void setOnscreenKeyboardVisible(boolean z);

    void vibrate(int i);

    void vibrate(long[] jArr, int i);
}
