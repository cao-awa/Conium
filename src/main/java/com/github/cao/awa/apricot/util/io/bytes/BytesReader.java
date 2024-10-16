package com.github.cao.awa.apricot.util.io.bytes;

public class BytesReader {
    private int flag = 0;
    private byte[] bytes;
    private int cursor = 0;

    private BytesReader(byte[] bytes) {
        this.bytes = bytes == null ? BytesUtil.EMPTY : bytes;
    }

    public static BytesReader of(byte[] bytes) {
        return new BytesReader(bytes);
    }

    public int getCursor() {
        return this.cursor;
    }

    public byte[] reverseRound(int round, int length) {
        byte[] bytes = read(length);
        byte[] result = new byte[round];

        if (length >= 0) {
            System.arraycopy(
                    bytes,
                    0,
                    result,
                    round - length,
                    length
            );
        }
        return result;
    }

    public byte[] read(int length) {
        if (length == 0) {
            return BytesUtil.EMPTY;
        }
        if (length + this.cursor > this.bytes.length) {
            return new byte[length];
        } else {
            byte[] result = new byte[length];
            System.arraycopy(
                    this.bytes,
                    this.cursor,
                    result,
                    0,
                    result.length
            );
            this.cursor += length;
            return result;
        }
    }

    public BytesReader next(byte target) {
        while (read() == target) {
        }
        this.cursor--;
        return this;
    }

    public BytesReader skip(int length) {
        this.cursor += length;
        return this;
    }

    public boolean readable(int length) {
        return readable() >= length;
    }

    public byte read() {
        return this.bytes.length > this.cursor ? this.bytes[this.cursor++] : - 1;
    }

    public BytesReader reset() {
        this.cursor = 0;
        this.flag = 0;
        return this;
    }

    public BytesReader reset(byte[] newBytes) {
        this.bytes = newBytes;
        return reset();
    }

    public BytesReader back(int range) {
        this.cursor -= range;
        return this;
    }

    public BytesReader back() {
        this.cursor = this.flag;
        return this;
    }

    public byte[] all() {
        return read(this.bytes.length - this.cursor);
    }

    public byte[] non() {
        return BytesUtil.EMPTY;
    }

    public int flag() {
        this.flag = this.cursor;
        return this.flag;
    }

    public int readable() {
        return this.bytes.length - this.cursor;
    }

    public int length() {
        return this.bytes.length;
    }
}
