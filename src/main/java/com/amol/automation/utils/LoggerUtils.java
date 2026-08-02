package com.amol.automation.utils;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoggerUtils {

	private LoggerUtils() {

	}

	public static Logger getLogger(Class<?> clazz) {

		Objects.requireNonNull(clazz, "Logger class cannot be null");
		return LogManager.getLogger(clazz);

	}

}