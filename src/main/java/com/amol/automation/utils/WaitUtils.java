package com.amol.automation.utils;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amol.automation.driver.DriverManager;

/**
 * Utility class for explicit waits.
 *
 * Responsibilities:
 * - Wait for element visibility
 * - Wait for element clickability
 * - Wait for element invisibility
 *
 * Element locator based interactions remain in ElementUtils.
 */
public final class WaitUtils {

    private WaitUtils() {
        // Prevent object creation
    }

    private static final Logger log =
            LoggerUtils.getLogger(WaitUtils.class);

    private static final int DEFAULT_WAIT_TIME = 20;

    // =========================================================
    // WebDriverWait
    // =========================================================

    /**
     * Creates WebDriverWait for current thread's driver.
     */
    private static WebDriverWait getWait() {

        int waitTime =
                getWaitTime();

        return new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(waitTime));
    }

    /**
     * Gets configured explicit wait timeout.
     *
     * If configuration is unavailable or invalid,
     * default value of 20 seconds is used.
     */
    private static int getWaitTime() {

        try {

            int configuredWait =
                    ConfigReader.getInstance()
                            .getIntProperty(
                                    "explicit.wait");

            return configuredWait > 0
                    ? configuredWait
                    : DEFAULT_WAIT_TIME;

        } catch (Exception e) {

            log.debug(
                    "Using default explicit wait: {} seconds",
                    DEFAULT_WAIT_TIME);

            return DEFAULT_WAIT_TIME;
        }
    }

    // =========================================================
    // Visibility
    // =========================================================

    /**
     * Waits until WebElement is visible.
     *
     * @param element WebElement
     */
    public static void waitForVisibility(
            WebElement element) {

        validateElement(element);

        log.debug(
                "Waiting for element visibility");

        getWait()
                .until(
                        ExpectedConditions
                                .visibilityOf(element));

        log.debug(
                "Element is visible");
    }

    // =========================================================
    // Clickable
    // =========================================================

    /**
     * Waits until WebElement is clickable.
     *
     * @param element WebElement
     */
    public static void waitForClickable(
            WebElement element) {

        validateElement(element);

        log.debug(
                "Waiting for element to be clickable");

        getWait()
                .until(
                        ExpectedConditions
                                .elementToBeClickable(element));

        log.debug(
                "Element is clickable");
    }

    // =========================================================
    // Invisibility
    // =========================================================

    /**
     * Waits until WebElement becomes invisible.
     *
     * @param element WebElement
     */
    public static void waitForInvisibility(
            WebElement element) {

        validateElement(element);

        log.debug(
                "Waiting for element invisibility");

        getWait()
                .until(
                        ExpectedConditions
                                .invisibilityOf(element));

        log.debug(
                "Element is invisible");
    }

    // =========================================================
    // Validation
    // =========================================================

    private static void validateElement(
            WebElement element) {

        if (element == null) {

            throw new IllegalArgumentException(
                    "WebElement cannot be null");
        }

        if (!DriverManager.isDriverInitialized()) {

            throw new IllegalStateException(
                    "WebDriver is not initialized for "
                            + "current thread");
        }
    }
}