package com.amol.automation.utils;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtils {

	private JsonUtils() {

	}

	private static final ObjectMapper mapper = new ObjectMapper();

	private static final Logger log = LoggerUtils.getLogger(JsonUtils.class);

	public static JsonNode readJson(String filePath) {

		log.info("Reading JSON file : {}", filePath);

		try {

			return mapper.readTree(new File(filePath));

		} catch (IOException e) {

			log.error("Unable to read JSON file : {}", filePath);

			throw new RuntimeException("Unable to read JSON file : " + filePath, e);

		}

	}

	public static String getValue(String filePath, String objectName, String key) {

		JsonNode node = readJson(filePath);

		JsonNode objectNode = node.get(objectName);

		if (objectNode == null) {

			throw new RuntimeException("Object not found in JSON : " + objectName);

		}

		JsonNode valueNode = objectNode.get(key);

		if (valueNode == null) {

			throw new RuntimeException("Key not found in JSON : " + key);

		}

		String value = valueNode.asText();

		log.info("JSON value fetched for key : {}", key);

		return value;

	}

}