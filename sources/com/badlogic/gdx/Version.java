package com.badlogic.gdx;

import com.badlogic.gdx.utils.GdxRuntimeException;

public class Version {
    public static final int MAJOR;
    public static final int MINOR;
    public static final int REVISION;
    public static final String VERSION = "1.5.6";

    static {
        int i = 0;
        try {
            String[] v = VERSION.split("\\.");
            MAJOR = v.length < 1 ? 0 : Integer.valueOf(v[0]).intValue();
            MINOR = v.length < 2 ? 0 : Integer.valueOf(v[1]).intValue();
            if (v.length >= 3) {
                i = Integer.valueOf(v[2]).intValue();
            }
            REVISION = i;
        } catch (Throwable t) {
            GdxRuntimeException gdxRuntimeException = new GdxRuntimeException("Invalid version 1.5.6", t);
        }
    }

    public static boolean isHigher(int major, int minor, int revision) {
        return isHigherEqual(major, minor, revision + 1);
    }

    public static boolean isHigherEqual(int major, int minor, int revision) {
        if (MAJOR != major) {
            if (MAJOR > major) {
                return true;
            }
            return false;
        } else if (MINOR != minor) {
            if (MINOR <= minor) {
                return false;
            }
            return true;
        } else if (REVISION < revision) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isLower(int major, int minor, int revision) {
        return isLowerEqual(major, minor, revision - 1);
    }

    public static boolean isLowerEqual(int major, int minor, int revision) {
        if (MAJOR != major) {
            if (MAJOR < major) {
                return true;
            }
            return false;
        } else if (MINOR != minor) {
            if (MINOR >= minor) {
                return false;
            }
            return true;
        } else if (REVISION > revision) {
            return false;
        } else {
            return true;
        }
    }
}
