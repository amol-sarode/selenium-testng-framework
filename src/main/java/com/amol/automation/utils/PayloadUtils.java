
package com.amol.automation.utils;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class PayloadUtils {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private PayloadUtils() {
	}

	public static <T> T readJson(String fileName, Class<T> type) {

		String resourcePath = fileName.startsWith("/") ? fileName.substring(1) : fileName;

		try (InputStream input = PayloadUtils.class.getClassLoader().getResourceAsStream(resourcePath)) {

			if (input == null) {
				throw new IllegalArgumentException("Test data file not found on classpath: " + resourcePath);
			}

			return MAPPER.readValue(input, type);

		} catch (Exception e) {
			throw new RuntimeException("Unable to read JSON test data: " + resourcePath, e);
		}
	}
}
