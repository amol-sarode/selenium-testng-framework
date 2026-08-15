package com.amol.automation.utils;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amol.automation.driver.DriverManager;

/**
 * Utility class for common Selenium WebElement interactions.
 *
 * Responsibilities:
 * - Click elements
 * - Enter text
 * - Read element text
 * - Check element visibility
 *
 * Assertions do not belong here.
 */
public final class ElementUtils {

    private ElementUtils() {
    }

    private static final Logger log =
            LoggerUtils.getLogger(ElementUtils.class);

    private static final int WAIT_TIME = 20;

    private static WebDriverWait getWait() {

        return new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(WAIT_TIME));
    }

    // =========================================================
    // Click
    // =========================================================

    public static void click(By locator) {

        validateLocator(locator);

        log.debug("Clicking element : {}", locator);

        WebElement element =
                getWait().until(
                        ExpectedConditions.elementToBeClickable(locator));

        element.click();

        log.debug("Element clicked successfully : {}", locator);
    }

    // =========================================================
    // Enter Text
    // =========================================================

    public static void enterText(By locator, String text) {

        validateLocator(locator);

        if (text == null) {
            throw new IllegalArgumentException(
                    "Text cannot be null");
        }

        log.debug("Entering text into element : {}", locator);

        WebElement element =
                getWait().until(
                        ExpectedConditions.visibilityOfElementLocated(
                                locator));

        element.clear();
        element.sendKeys(text);

        log.debug(
                "Text entered successfully : {}",
                locator);
    }

    // =========================================================
    // Get Input Value
    // =========================================================

    public static String getInputValue(By locator) {

        validateLocator(locator);

        WebElement element =
                getWait().until(
                        ExpectedConditions.visibilityOfElementLocated(
                                locator));

        return element.getAttribute("value");
    }

    // =========================================================
    // Get Text
    // =========================================================

    public static String getText(By locator) {

        validateLocator(locator);

        WebElement element =
                getWait().until(
                        ExpectedConditions.visibilityOfElementLocated(
                                locator));

        return element.getText().trim();
    }

    // =========================================================
    // Is Displayed
    // =========================================================

    public static boolean isDisplayed(By locator) {

        validateLocator(locator);

        try {

            WebElement element =
                    getWait().until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    locator));

            return element.isDisplayed();

        } catch (Exception e) {

            log.debug(
                    "Element is not displayed : {}",
                    locator);

            return false;
        }
    }

    // =========================================================
    // Validation
    // =========================================================

    private static void validateLocator(By locator) {

        if (locator == null) {
            throw new IllegalArgumentException(
                    "Locator cannot be null");
        }

        if (!DriverManager.isDriverInitialized()) {
            throw new IllegalStateException(
                    "WebDriver is not initialized for current thread");
        }
    }
}