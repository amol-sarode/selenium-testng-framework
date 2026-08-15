package com.amol.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

import com.amol.automation.constants.FrameworkConstants;

/**
 * Central configuration manager.
 *
 * Responsibilities:
 * - Load config.properties once
 * - Provide String configuration values
 * - Provide typed configuration values
 * - Validate configuration keys and values
 */
public final class ConfigReader {

    private ConfigReader() {
        loadProperties();
    }

    private static volatile ConfigReader instance;

    private final Properties properties =
            new Properties();

    private static final Logger log =
            LoggerUtils.getLogger(ConfigReader.class);

    // =========================================================
    // Singleton
    // =========================================================

    /**
     * Returns the singleton ConfigReader instance.
     *
     * Uses double-checked locking for thread-safe lazy
     * initialization.
     */
    public static ConfigReader getInstance() {

        if (instance == null) {

            synchronized (ConfigReader.class) {

                if (instance == null) {

                    instance =
                            new ConfigReader();
                }
            }
        }

        return instance;
    }

    // =========================================================
    // Property Loading
    // =========================================================

    /**
     * Loads config.properties from the classpath.
     */
    private void loadProperties() {

        log.info(
                "Loading configuration file : {}",
                FrameworkConstants.CONFIG_FILE_PATH);

        try (InputStream inputStream =
                     ConfigReader.class
                             .getClassLoader()
                             .getResourceAsStream(
                                     FrameworkConstants.CONFIG_FILE_PATH)) {

            if (inputStream == null) {

                throw new IllegalStateException(
                        "Configuration file not found: "
                                + FrameworkConstants.CONFIG_FILE_PATH);
            }

            properties.load(inputStream);

            log.info(
                    "Configuration file loaded successfully");

        } catch (IOException e) {

            log.error(
                    "Unable to load configuration file",
                    e);

            throw new IllegalStateException(
                    "Unable to load configuration file: "
                            + FrameworkConstants.CONFIG_FILE_PATH,
                    e);
        }
    }

    // =========================================================
    // String Property
    // =========================================================

    /**
     * Returns a configuration value as String.
     *
     * @param key configuration key
     * @return configuration value
     */
    public String getProperty(String key) {

        validateKey(key);

        String value =
                properties.getProperty(key);

        if (value == null ||
                value.trim().isEmpty()) {

            throw new IllegalStateException(
                    "Configuration property is missing or empty: "
                            + key);
        }

        return value.trim();
    }

    // =========================================================
    // Integer Property
    // =========================================================

    /**
     * Returns a configuration value as integer.
     *
     * @param key configuration key
     * @return integer configuration value
     */
    public int getIntProperty(String key) {

        String value =
                getProperty(key);

        try {

            return Integer.parseInt(value);

        } catch (NumberFormatException e) {

            throw new IllegalStateException(
                    "Configuration property must be a valid integer: "
                            + key
                            + " = "
                            + value,
                    e);
        }
    }

    // =========================================================
    // Boolean Property
    // =========================================================

    /**
     * Returns a configuration value as boolean.
     *
     * Only true or false are accepted.
     *
     * @param key configuration key
     * @return boolean configuration value
     */
    public boolean getBooleanProperty(String key) {

        String value =
                getProperty(key);

        if (!value.equalsIgnoreCase("true")
                && !value.equalsIgnoreCase("false")) {

            throw new IllegalStateException(
                    "Configuration property must be "
                            + "true or false: "
                            + key
                            + " = "
                            + value);
        }

        return Boolean.parseBoolean(value);
    }

    // =========================================================
    // Property Existence
    // =========================================================

    /**
     * Checks whether a property exists and contains
     * a non-empty value.
     *
     * @param key configuration key
     * @return true if property exists
     */
    public boolean hasProperty(String key) {

        if (key == null ||
                key.trim().isEmpty()) {

            return false;
        }

        String value =
                properties.getProperty(
                        key.trim());

        return value != null &&
                !value.trim().isEmpty();
    }

    // =========================================================
    // Validation
    // =========================================================

    /**
     * Validates configuration key.
     */
    private void validateKey(String key) {

        if (key == null ||
                key.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Configuration key cannot be null or empty");
        }
    }
}