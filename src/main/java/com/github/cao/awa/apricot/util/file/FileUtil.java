package com.github.cao.awa.apricot.util.file;

import java.io.File;

public class FileUtil {
    public static void mkdirs(String file) {
        mkdirs(new File(file));
    }

    public static void mkdirs(File file) {
        if (! file.exists()) {
            file.mkdirs();
        }
    }

    public static void mkdirsParent(File file) {
        file = file.getParentFile();
        if (! file.exists()) {
            file.mkdirs();
        }
    }

    public static void delete(File file) {
        if (file.exists()) {
            file.delete();
        }
    }
}
