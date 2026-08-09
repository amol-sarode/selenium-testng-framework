package com.amol.automation.utils;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON Utility class.
 *
 * Responsible for reading JSON files and retrieving values from JSON objects.
 */
public final class JsonUtils {

	private JsonUtils() {
	}

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private static final Logger log = LoggerUtils.getLogger(JsonUtils.class);

	/**
	 * Reads a JSON file and returns its root JsonNode.
	 *
	 * @param filePath JSON file path
	 * @return root JsonNode
	 */
	public static JsonNode readJson(String filePath) {

		log.info("Reading JSON file: {}", filePath);

		if (filePath == null || filePath.trim().isEmpty()) {
			throw new IllegalArgumentException("JSON file path cannot be null or empty");
		}

		File file = new File(filePath);

		if (!file.exists()) {
			throw new RuntimeException("JSON file not found: " + filePath);
		}

		if (!file.isFile()) {
			throw new RuntimeException("JSON path is not a file: " + filePath);
		}

		try {

			JsonNode rootNode = MAPPER.readTree(file);

			if (rootNode == null) {
				throw new RuntimeException("JSON file is empty: " + filePath);
			}

			log.info("JSON file loaded successfully: {}", filePath);

			return rootNode;

		} catch (IOException e) {

			log.error("Unable to read JSON file: {}", filePath, e);

			throw new RuntimeException("Unable to read JSON file: " + filePath, e);
		}
	}

	/**
	 * Retrieves a value from a JSON object.
	 *
	 * Example:
	 *
	 * { "validUser": { "username": "standard_user" } }
	 *
	 * getValue(filePath, "validUser", "username")
	 *
	 * @param filePath   JSON file path
	 * @param objectName JSON object name
	 * @param key        key inside the object
	 * @return value as String
	 */
	public static String getValue(String filePath, String objectName, String key) {

		if (objectName == null || objectName.trim().isEmpty()) {
			throw new IllegalArgumentException("JSON object name cannot be null or empty");
		}

		if (key == null || key.trim().isEmpty()) {
			throw new IllegalArgumentException("JSON key cannot be null or empty");
		}

		JsonNode rootNode = readJson(filePath);

		JsonNode objectNode = rootNode.get(objectName);

		if (objectNode == null || objectNode.isNull()) {

			throw new RuntimeException("JSON object not found: " + objectName);
		}

		JsonNode valueNode = objectNode.get(key);

		if (valueNode == null || valueNode.isNull()) {

			throw new RuntimeException("JSON key not found: " + key + " in object: " + objectName);
		}

		String value = valueNode.asText();

		log.info("JSON value retrieved successfully. Object: {}, Key: {}", objectName, key);

		return value;
	}
}