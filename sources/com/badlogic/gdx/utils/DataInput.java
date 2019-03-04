package com.badlogic.gdx.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataInput extends DataInputStream {
    private char[] chars = new char[32];

    public DataInput(InputStream in) {
        super(in);
    }

    public int readInt(boolean optimizePositive) throws IOException {
        int b = read();
        int result = b & 127;
        if ((b & 128) != 0) {
            b = read();
            result |= (b & 127) << 7;
            if ((b & 128) != 0) {
                b = read();
                result |= (b & 127) << 14;
                if ((b & 128) != 0) {
                    b = read();
                    result |= (b & 127) << 21;
                    if ((b & 128) != 0) {
                        result |= (read() & 127) << 28;
                    }
                }
            }
        }
        return optimizePositive ? result : (result >>> 1) ^ (-(result & 1));
    }

    public String readString() throws IOException {
        int charCount = readInt(true);
        switch (charCount) {
            case 0:
                return null;
            case 1:
                return "";
            default:
                charCount--;
                if (this.chars.length < charCount) {
                    this.chars = new char[charCount];
                }
                char[] chars = this.chars;
                int b = 0;
                int i = 0;
                while (i < charCount) {
                    b = read();
                    if (b > 127) {
                        if (i < charCount) {
                            readUtf8_slow(charCount, i, b);
                        }
                        return new String(chars, 0, charCount);
                    }
                    int charIndex = i + 1;
                    chars[i] = (char) b;
                    i = charIndex;
                }
                if (i < charCount) {
                    readUtf8_slow(charCount, i, b);
                }
                return new String(chars, 0, charCount);
        }
    }

    private void readUtf8_slow(int charCount, int charIndex, int b) throws IOException {
        char[] chars = this.chars;
        while (true) {
            switch (b >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    chars[charIndex] = (char) b;
                    break;
                case 12:
                case 13:
                    chars[charIndex] = (char) (((b & 31) << 6) | (read() & 63));
                    break;
                case 14:
                    chars[charIndex] = (char) ((((b & 15) << 12) | ((read() & 63) << 6)) | (read() & 63));
                    break;
            }
            charIndex++;
            if (charIndex < charCount) {
                b = read() & 255;
            } else {
                return;
            }
        }
    }
}
