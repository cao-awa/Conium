package com.github.cao.awa.apricot.mathematic.base;

import com.github.cao.awa.apricot.annotations.Stable;

/**
 * An util using to convert identifier and bytes.
 *
 * @author InkerBot
 * @author cao_awa
 * @since 1.0.0
 */
@Stable
public class Base256 {
    /**
     * Convert a long to 8 bytes.
     *
     * @param l
     *         Long input
     * @return Bytes result
     *
     * @author cao_awa
     * @since 1.0.0
     */
    public static byte[] longToBuf(long l) {
        return longToBuf(
                l,
                new byte[8]
        );
    }

    /**
     * Convert a long to 8 bytes.
     *
     * @param l
     *         Long input
     * @param buf
     *         Bytes output
     * @return Bytes result
     *
     * @author InkerBot
     * @author cao_awa
     * @since 1.0.0
     */
    public static byte[] longToBuf(long l, byte[] buf) {
        buf[0] = (byte) (l >>> 56);
        buf[1] = (byte) (l >>> 48);
        buf[2] = (byte) (l >>> 40);
        buf[3] = (byte) (l >>> 32);
        buf[4] = (byte) (l >>> 24);
        buf[5] = (byte) (l >>> 16);
        buf[6] = (byte) (l >>> 8);
        buf[7] = (byte) (l);
        return buf;
    }

    /**
     * Convert 8 bytes to a long.
     *
     * @param buf
     *         Bytes input
     * @return Long result
     *
     * @author InkerBot
     * @author cao_awa
     * @since 1.0.0
     */
    public static long longFromBuf(byte[] buf) {
        return ((buf[0] & 0xFFL) << 56) +
               ((buf[1] & 0xFFL) << 48) +
               ((buf[2] & 0xFFL) << 40) +
               ((buf[3] & 0xFFL) << 32) +
               ((buf[4] & 0xFFL) << 24) +
               ((buf[5] & 0xFFL) << 16) +
               ((buf[6] & 0xFFL) << 8) +
               ((buf[7] & 0xFFL));
    }

    /**
     * Convert an integer to 4 bytes.
     *
     * @param i
     *         Integer input
     * @return Bytes result
     *
     * @author cao_awa
     * @since 1.0.0
     */
    public static byte[] intToBuf(int i) {
        return intToBuf(
                i,
                new byte[4]
        );
    }

    /**
     * Convert an integer to 4 bytes.
     *
     * @param i
     *         Integer input
     * @param buf
     *         Bytes output
     * @return Bytes result
     *
     * @author InkerBot
     * @author cao_awa
     * @since 1.0.0
     */
    public static byte[] intToBuf(int i, byte[] buf) {
        buf[0] = (byte) (i >>> 24);
        buf[1] = (byte) (i >>> 16);
        buf[2] = (byte) (i >>> 8);
        buf[3] = (byte) (i);
        return buf;
    }

    /**
     * Convert 4 bytes to an integer.
     *
     * @param buf
     *         Bytes input
     * @return Integer result
     *
     * @author InkerBot
     * @author cao_awa
     * @since 1.0.0
     */
    public static int intFromBuf(byte[] buf) {
        return (((buf[0] & 0xFF) << 24) +
                ((buf[1] & 0xFF) << 16) +
                ((buf[2] & 0xFF) << 8) +
                ((buf[3] & 0xFF)));
    }

    /**
     * Convert 2 bytes to an integer.
     *
     * @param buf
     *         Bytes input
     * @return Integer result
     *
     * @author cao_awa
     * @since 1.0.0
     */
    public static int tagFromBuf(byte[] buf) {
        return (((buf[0] & 0xFF) << 8) +
                ((buf[1] & 0xFF)));
    }

    /**
     * Convert an integer to 2 bytes.
     *
     * @param i
     *         Integer input
     * @return Bytes result
     *
     * @author cao_awa
     * @since 1.0.0
     */
    public static byte[] tagToBuf(int i) {
        return tagToBuf(
                i,
                new byte[2]
        );
    }

    /**
     * Convert an integer to 2 bytes.
     *
     * @param i
     *         Integer input
     * @param buf
     *         Bytes output
     * @return Bytes result
     *
     * @author cao_awa
     * @since 1.0.0
     */
    public static byte[] tagToBuf(int i, byte[] buf) {
        buf[0] = (byte) (i >>> 8);
        buf[1] = (byte) (i);
        return buf;
    }
}

