package io.gitctrlx.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * 工具类包含各种实用方法。
 * Utility class containing various helper methods.
 */
public class Tools {
    // 日期格式
    // Date format
    public static final String SDF = "EEE MMM dd yyyy HH:mm:ss 'GMT'Z";

    // 日期时间格式化器
    // DateTime formatter
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    /**
     * 使当前线程休眠指定的秒数。
     * Makes the current thread sleep for the specified number of seconds.
     *
     * @param second 要休眠的秒数 The number of seconds to sleep.
     */
    public static void sleep(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException ignore) {
        }
    }

    /**
     * 获取当前时间戳。
     * Gets the current timestamp.
     *
     * @return 当前时间戳 The current timestamp.
     */
    public static String date() {
        return new SimpleDateFormat(SDF).format(new Date());
    }

    /**
     * 获取当前本地日期时间。
     * Gets the current local date and time.
     *
     * @return 当前本地日期时间 The current local date and time.
     */
    public static String localDateTime() {
        return LocalDateTime.now().format(DTF);
    }

    /**
     * 生成一个随机ID。
     * Generates a random ID.
     *
     * @return 随机ID The random ID.
     */
    public static String getRandomId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
