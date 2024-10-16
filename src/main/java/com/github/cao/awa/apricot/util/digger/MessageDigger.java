package com.github.cao.awa.apricot.util.digger;

import com.github.cao.awa.apricot.annotations.Stable;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Stable
public class MessageDigger {
    private static final int BUF_SIZE = 16384;

    public static String digest(String message, DigestAlgorithm sha) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(sha.instanceName());
        digest.update(message.getBytes(StandardCharsets.UTF_8));
        StringBuilder result = new StringBuilder();
        digest(digest, result);
        return result.toString();
    }

    public static String digestFile(File file, DigestAlgorithm sha) throws Exception {
        if (! file.isFile()) {
            return "0";
        }

        RandomAccessFile accessFile = new RandomAccessFile(
                file,
                "r"
        );

        MessageDigest digest = MessageDigest.getInstance(sha.instanceName());

        byte[] buffer = new byte[BUF_SIZE];

        long read = 0;

        long offset = accessFile.length();
        int length;
        while (read < offset) {
            length = (int) (offset - read < BUF_SIZE ? offset - read : BUF_SIZE);
            accessFile.read(
                    buffer,
                    0,
                    length
            );

            digest.update(
                    buffer,
                    0,
                    length
            );

            read += length;
        }

        accessFile.close();

        StringBuilder result = new StringBuilder();

        digest(digest, result);

        return result.toString();
    }

    private static void digest(MessageDigest digest, StringBuilder result) {
        for (byte b : digest.digest()) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() < 2) {
                result.append(0);
            }
            result.append(hex);
        }
    }

    public enum Sha1 implements Sha {
        SHA("SHA-1");

        private final String instance;

        Sha1(String instance) {
            this.instance = instance;
        }

        @Override
        public String instanceName() {
            return instance;
        }
    }

    public enum Sha3 implements Sha {
        SHA_224("SHA3-224"), SHA_256("SHA3-256"), SHA_512("SHA3-512");

        private final String instance;

        Sha3(String instance) {
            this.instance = instance;
        }

        @Override
        public String instanceName() {
            return instance;
        }
    }

    public enum MD4 implements MD {
        MD_4("MD4");

        private final String instance;

        MD4(String instance) {
            this.instance = instance;
        }

        @Override
        public String instanceName() {
            return instance;
        }
    }

    public enum MD5 implements MD {
        MD_5("MD5");

        private final String instance;

        MD5(String instance) {
            this.instance = instance;
        }

        @Override
        public String instanceName() {
            return instance;
        }
    }

    public interface DigestAlgorithm {
        String instanceName();
    }

    public interface Sha extends DigestAlgorithm {
    }

    public interface MD extends DigestAlgorithm {
    }
}

