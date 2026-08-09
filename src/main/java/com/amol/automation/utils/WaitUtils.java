package com.amol.automation.utils;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amol.automation.driver.DriverManager;

/**
 * Wait Utility class.
 *
 * Provides reusable explicit wait methods for Selenium WebElements.
 */
public final class WaitUtils {

	private WaitUtils() {
	}

	private static final Logger log = LoggerUtils.getLogger(WaitUtils.class);

	private static final int WAIT_TIME = 20;

	/**
	 * Creates WebDriverWait using the current thread's WebDriver.
	 */
	private static WebDriverWait getWait() {

		return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_TIME));
	}

	/**
	 * Waits until the element is visible.
	 *
	 * @param element WebElement to wait for
	 */
	public static void waitForVisibility(WebElement element) {

		log.debug("Waiting for element visibility");

		getWait().until(ExpectedConditions.visibilityOf(element));

		log.debug("Element is visible");
	}

	/**
	 * Waits until the element is clickable.
	 *
	 * @param element WebElement to wait for
	 */
	public static void waitForClickable(WebElement element) {

		log.debug("Waiting for element to be clickable");

		getWait().until(ExpectedConditions.elementToBeClickable(element));

		log.debug("Element is clickable");
	}

	/**
	 * Waits until the element becomes invisible.
	 *
	 * @param element WebElement to wait for
	 */
	public static void waitForInvisibility(WebElement element) {

		log.debug("Waiting for element invisibility");

		getWait().until(ExpectedConditions.invisibilityOf(element));

		log.debug("Element is invisible");
	}
}