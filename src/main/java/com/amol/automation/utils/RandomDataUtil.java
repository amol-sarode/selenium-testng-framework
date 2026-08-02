package com.amol.automation.utils;

import java.security.SecureRandom;
import java.util.UUID;

public final class RandomDataUtil {

	private RandomDataUtil() {

	}

	private static final SecureRandom RANDOM = new SecureRandom();

	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public static int getRandomNumber(int min, int max) {

		if (min > max) {

			throw new IllegalArgumentException("Minimum value cannot be greater than maximum value");

		}

		return RANDOM.nextInt(max - min + 1) + min;

	}

	public static String getRandomString(int length) {

		validateLength(length);

		return generateRandomData(ALPHABET, length);

	}

	public static String getRandomAlphaNumeric(int length) {

		validateLength(length);

		return generateRandomData(ALPHANUMERIC, length);

	}

	private static String generateRandomData(String characters, int length) {

		StringBuilder builder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {

			builder.append(characters.charAt(RANDOM.nextInt(characters.length())));

		}

		return builder.toString();

	}

	public static String getRandomEmail() {

		return "user" + getRandomNumber(1000, 9999) + "@test.com";

	}

	public static String getRandomMobileNumber() {

		return "9" + getRandomNumber(100000000, 999999999);

	}

	public static String getUUID() {

		return UUID.randomUUID().toString();

	}

	private static void validateLength(int length) {

		if (length <= 0) {

			throw new IllegalArgumentException("Length must be greater than zero");

		}

	}

}