package com.amol.automation.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for date and time operations.
 *
 * Provides reusable methods for:
 * - Current date
 * - Current time
 * - Current date and time
 * - Timestamp generation
 */
public final class DateUtil {

    private DateUtil() {
        // Prevent object creation
    }

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private static final DateTimeFormatter TIMESTAMP_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    /**
     * Returns the current date.
     *
     * Format: dd-MM-yyyy
     *
     * @return current date
     */
    public static String getCurrentDate() {
        return LocalDate.now().format(DATE_FORMAT);
    }

    /**
     * Returns the current time.
     *
     * Format: HH:mm:ss
     *
     * @return current time
     */
    public static String getCurrentTime() {
        return LocalTime.now().format(TIME_FORMAT);
    }

    /**
     * Returns the current date and time.
     *
     * Format: dd-MM-yyyy HH:mm:ss
     *
     * @return current date and time
     */
    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DATE_TIME_FORMAT);
    }

    /**
     * Returns a timestamp suitable for file and report names.
     *
     * Format: yyyyMMdd_HHmmss
     *
     * @return timestamp
     */
    public static String getTimestamp() {
        return LocalDateTime.now().format(TIMESTAMP_FORMAT);
    }
}