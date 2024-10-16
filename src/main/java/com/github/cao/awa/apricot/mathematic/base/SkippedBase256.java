package com.github.cao.awa.apricot.mathematic.base;

import com.github.cao.awa.apricot.util.io.bytes.BytesReader;
import com.github.cao.awa.apricot.util.io.bytes.BytesUtil;

public class SkippedBase256 {
    public static long readLong(BytesReader reader, int length) {
        return Base256.longFromBuf(reader.reverseRound(8,
                                                       length
        ));
    }

    public static long readLong(BytesReader reader) {
        int length = reader.read();
        if (length == - 1) {
            return Base256.longFromBuf(reader.read(8));
        } else if (length == 0) {
            return 0;
        }
        return Base256.longFromBuf(reader.reverseRound(8,
                                                       length
        ));
    }

    public static int readInt(BytesReader reader) {
        int length = reader.read();
        if (length == - 1) {
            return Base256.intFromBuf(reader.read(4));
        } else if (length == 0) {
            return 0;
        }
        return Base256.intFromBuf(reader.reverseRound(4,
                                                      length
        ));
    }

    public static byte[] longToBuf(long l) {
        return skip(Base256.longToBuf(l));
    }

    public static byte[] intToBuf(int l) {
        return skip(Base256.intToBuf(l));
    }

    public static byte[] tagToBuf(int l) {
        return skip(Base256.tagToBuf(l));
    }

    public static byte[] skip(byte[] buf) {
        if (buf[0] == 0 && buf[1] == 0) {
            byte[] skd = BytesUtil.skp(buf,
                                       (byte) 0
            );
            byte[] result = new byte[skd.length + 1];
            result[0] = (byte) skd.length;
            System.arraycopy(skd,
                             0,
                             result,
                             1,
                             skd.length
            );
            return result;
        }
        return BytesUtil.concat(new byte[]{- 1},
                                buf);
    }
}
