package com.amol.automation.utils;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for framework logging.
 *
 * Provides centralized Log4j2 logger creation.
 */
public final class LoggerUtils {

    private LoggerUtils() {
        // Prevent object creation
    }

    /**
     * Returns a Log4j2 logger for the specified class.
     *
     * @param clazz class requiring logger
     * @return Logger instance
     */
    public static Logger getLogger(Class<?> clazz) {

        Objects.requireNonNull(
                clazz,
                "Logger class cannot be null");

        return LogManager.getLogger(clazz);
    }
}