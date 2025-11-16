package io.gitctrlx.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * Utility class containing various helper methods.
 */
public class Tools {
    // Date format
    public static final String SDF = "EEE MMM dd yyyy HH:mm:ss 'GMT'Z";

    // DateTime formatter
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    /**
     * Makes the current thread sleep for the specified number of seconds.
     *
     * @param second The number of seconds to sleep.
     */
    public static void sleep(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException ignore) {
        }
    }

    /**
     * Gets the current timestamp.
     *
     * @return The current timestamp.
     */
    public static String date() {
        return new SimpleDateFormat(SDF).format(new Date());
    }

    /**
     * Gets the current local date and time.
     *
     * @return The current local date and time.
     */
    public static String localDateTime() {
        return LocalDateTime.now().format(DTF);
    }

    /**
     * Generates a random ID.
     *
     * @return The random ID.
     */
    public static String getRandomId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
