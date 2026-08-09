package com.amol.automation.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Date and Time Utility class.
 *
 * Provides reusable methods for current date, time, date-time, and timestamp
 * generation.
 */
public final class DateUtil {

	private DateUtil() {
	}

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

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
	 * Returns a timestamp suitable for file/report names.
	 *
	 * Format: yyyyMMdd_HHmmss
	 *
	 * @return timestamp
	 */
	public static String getTimeStamp() {

		return LocalDateTime.now().format(TIMESTAMP_FORMAT);
	}
}