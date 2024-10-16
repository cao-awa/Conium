package com.github.cao.awa.apricot.util.io.bytes;

public class BytesUtil {
    public static final byte[] EMPTY = new byte[0];

    public static void xor(byte[] target, byte[] xor) {
        for (int i = 0; i < target.length; i++) {
            target[i] ^= xor[i];
        }
    }

    public static void reverse(byte[] bytes) {
        for (int start = 0, end = bytes.length - 1; start < end; start++, end--) {
            bytes[end] ^= bytes[start];
            bytes[start] ^= bytes[end];
            bytes[end] ^= bytes[start];
        }
    }

    public static byte[] skp(byte[] bytes, byte target) {
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == target) {
                continue;
            }
            byte[] result = new byte[bytes.length - i];
            System.arraycopy(bytes,
                             i,
                             result,
                             0,
                             result.length
            );
            return result;
        }

        return new byte[0];
    }

    public static void skd(byte[] bytes, byte target) {
        skd(bytes,
            target,
            (byte) 0
        );
    }

    public static void skd(byte[] bytes, byte target, byte fill) {
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == target) {
                continue;
            }
            for (int i1 = 0; i1 < bytes.length; i1++) {
                if (i < bytes.length) {
                    bytes[i1] = bytes[i++];
                } else {
                    bytes[i1] = fill;
                }
            }
        }
    }

    public static byte[] concat(byte[]... arrays) {
        int length = 0;
        for (byte[] array : arrays) {
            length += array.length;
        }
        byte[] result = new byte[length];
        int cur = 0;
        for (byte[] array : arrays) {
            if (array.length == 0) {
                continue;
            }
            System.arraycopy(array,
                             0,
                             result,
                             cur,
                             array.length
            );
            cur += array.length;
        }
        return result;
    }

    public static byte[] arrau(byte... bytes) {
        return bytes;
    }

    public static byte[] arrau(int... bytes) {
        byte[] result = new byte[bytes.length];
        int index = 0;
        for (int i : bytes) {
            result[index++] = (byte) i;
        }
        return result;
    }
}
