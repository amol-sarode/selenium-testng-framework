package com.amol.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

import com.amol.automation.constants.FrameworkConstants;

public final class ConfigReader {

	private static volatile ConfigReader instance;

	private final Properties properties;

	private static final Logger log = LoggerUtils.getLogger(ConfigReader.class);

	private ConfigReader() {

		properties = new Properties();

		try (FileInputStream file = new FileInputStream(FrameworkConstants.CONFIG_FILE_PATH)) {

			log.info("Loading config.properties file");

			properties.load(file);

			log.info("Configuration loaded successfully");

		} catch (IOException e) {

			log.error("Unable to load config.properties file");

			throw new RuntimeException("Unable to load config.properties file", e);

		}

	}

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

	public String getProperty(String key) {

		String value = properties.getProperty(key);

		if (value == null || value.trim().isEmpty()) {

			throw new RuntimeException("Property not found : " + key);

		}

		return value.trim();

	}

}