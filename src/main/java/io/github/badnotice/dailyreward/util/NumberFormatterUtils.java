package io.github.badnotice.dailyreward.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NumberFormatterUtils {

    public static String format(long time) {
        long timer = time - System.currentTimeMillis();

        int dias = (int) TimeUnit.MILLISECONDS.toDays(timer);
        int hour = (int) TimeUnit.MILLISECONDS.toHours(timer) % 24;
        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(timer) % 60;
        int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(timer) % 60;

        StringBuilder sb = new StringBuilder();
        if (dias > 0L) sb.append(dias).append((dias == 1L) ? "d" : "d");
        if (hour > 0L)
            sb.append((dias > 0L) ? ((minutes > 0L) ? ", " : " e ") : "").append(hour).append((hour == 1L) ? "h" : "h");
        if (minutes > 0L)
            sb.append((dias > 0L || hour > 0L) ? ((seconds > 0L) ? ", " : " e ") : "").append(minutes).append((minutes == 1L) ? "m" : "m");
        if (seconds > 0L)
            sb.append((dias > 0L || hour > 0L || minutes > 0L) ? " e " : ((sb.length() > 0) ? ", " : "")).append(seconds).append((seconds == 1L) ? "s" : "s");

        String s = sb.toString();
        return s.isEmpty() ? "" : s;
    }

}