package com.amol.automation.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

	private DateUtil() {

	}

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

	public static String getCurrentDate() {

		return LocalDate.now().format(DATE_FORMAT);

	}

	public static String getCurrentTime() {

		return LocalTime.now().format(TIME_FORMAT);

	}

	public static String getCurrentDateTime() {

		return LocalDateTime.now().format(DATE_TIME_FORMAT);

	}

	public static String getTimeStamp() {

		return LocalDateTime.now().format(TIMESTAMP_FORMAT);

	}

}