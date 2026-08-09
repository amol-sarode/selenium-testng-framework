package com.amol.automation.utils;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Logger Utility class.
 *
 * Provides Log4j2 logger instances throughout the framework.
 */
public final class LoggerUtils {

	private LoggerUtils() {
	}

	/**
	 * Returns a Log4j2 logger for the specified class.
	 *
	 * @param clazz class for which logger is required
	 * @return Logger instance
	 */
	public static Logger getLogger(Class<?> clazz) {

		Objects.requireNonNull(clazz, "Logger class cannot be null");

		return LogManager.getLogger(clazz);
	}
}