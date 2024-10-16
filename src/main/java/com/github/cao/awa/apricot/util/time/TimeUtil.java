package com.github.cao.awa.apricot.util.time;

import com.github.cao.awa.sinuatum.manipulate.Manipulate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static long millions() {
        return System.currentTimeMillis();
    }

    public static long nano() {
        return System.nanoTime();
    }

    public static long processMillion(long million) {
        return millions() - million;
    }

    public static long processNano(long nano) {
        return nano() - nano;
    }

    public static void sleep(long millions) throws InterruptedException {
        if (millions < 0)
            return;
        Thread.sleep(millions);
    }

    public static void coma(long millions) {
        Manipulate.operation(millions, Thread::sleep);
    }

    public static long nanoId() {
        return nano() / 100;
    }

    public static String format(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }
}
