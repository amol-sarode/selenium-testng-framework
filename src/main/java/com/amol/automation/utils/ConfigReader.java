package com.amol.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

import com.amol.automation.constants.FrameworkConstants;

/**
 * ConfigReader
 *
 * Responsible for loading and reading framework configuration from
 * config.properties.
 *
 * Design: - Singleton - Thread-safe initialization - Configuration loaded only
 * once - Provides typed property access
 */
public final class ConfigReader {

	private static volatile ConfigReader instance;

	private final Properties properties;

	private static final Logger log = LoggerUtils.getLogger(ConfigReader.class);

	/**
	 * Private constructor to prevent external object creation.
	 */
	private ConfigReader() {

		properties = new Properties();

		loadProperties();
	}

	/**
	 * Returns the single ConfigReader instance.
	 *
	 * @return ConfigReader instance
	 */
	public static ConfigReader getInstance() {

		if (instance == null) {

			synchronized (ConfigReader.class) {

				if (instance == null) {

					instance = new ConfigReader();
				}
			}
		}

		return instance;
	}

	/**
	 * Loads config.properties from the classpath.
	 */
	private void loadProperties() {

		log.info("Loading configuration file : {}", FrameworkConstants.CONFIG_FILE_PATH);

		try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {

			if (inputStream == null) {

				throw new RuntimeException("Configuration file not found: " + FrameworkConstants.CONFIG_FILE_PATH);
			}

			properties.load(inputStream);

			log.info("Configuration file loaded successfully");

		} catch (IOException e) {

			log.error("Unable to load configuration file", e);

			throw new RuntimeException("Unable to load configuration file: " + FrameworkConstants.CONFIG_FILE_PATH, e);
		}
	}

	/**
	 * Returns a configuration value as String.
	 *
	 * @param key configuration key
	 * @return configuration value
	 */
	public String getProperty(String key) {

		validateKey(key);

		String value = properties.getProperty(key);

		if (value == null || value.trim().isEmpty()) {

			throw new RuntimeException("Configuration property is missing or empty: " + key);
		}

		return value.trim();
	}

	/**
	 * Returns a configuration value as Integer.
	 *
	 * @param key configuration key
	 * @return integer value
	 */
	public int getIntProperty(String key) {

		String value = getProperty(key);

		try {

			return Integer.parseInt(value);

		} catch (NumberFormatException e) {

			throw new RuntimeException("Configuration property must be a valid integer: " + key + " = " + value, e);
		}
	}

	/**
	 * Returns a configuration value as Boolean.
	 *
	 * @param key configuration key
	 * @return boolean value
	 */
	public boolean getBooleanProperty(String key) {

		String value = getProperty(key);

		if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {

			throw new RuntimeException("Configuration property must be true or false: " + key + " = " + value);
		}

		return Boolean.parseBoolean(value);
	}

	/**
	 * Checks whether a configuration property exists.
	 *
	 * @param key configuration key
	 * @return true if property exists and is not empty
	 */
	public boolean hasProperty(String key) {

		if (key == null || key.trim().isEmpty()) {

			return false;
		}

		String value = properties.getProperty(key);

		return value != null && !value.trim().isEmpty();
	}

	/**
	 * Validates configuration key.
	 *
	 * @param key configuration key
	 */
	private void validateKey(String key) {

		if (key == null || key.trim().isEmpty()) {

			throw new IllegalArgumentException("Configuration key cannot be null or empty");
		}
	}
}