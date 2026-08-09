package com.amol.automation.utils;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amol.automation.driver.DriverManager;

/**
 * Element Utility class.
 *
 * Provides reusable methods for common Selenium WebElement interactions using
 * explicit waits.
 */
public final class ElementUtils {

	private ElementUtils() {
	}

	private static final Logger log = LoggerUtils.getLogger(ElementUtils.class);

	private static final int WAIT_TIME = 20;

	/**
	 * Creates WebDriverWait using the current thread's driver.
	 */
	private static WebDriverWait getWait() {

		return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_TIME));
	}

	/**
	 * Clicks an element after waiting until it is clickable.
	 *
	 * @param locator element locator
	 */
	public static void click(By locator) {

		log.debug("Clicking element : {}", locator);

		WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));

		element.click();

		log.debug("Element clicked successfully : {}", locator);
	}

	/**
	 * Enters text into an input field after waiting until the element is visible.
	 *
	 * @param locator element locator
	 * @param text    text to enter
	 */
	public static void enterText(By locator, String text) {

		log.debug("Entering text into element : {}", locator);

		WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));

		element.clear();
		element.sendKeys(text);

		log.debug("Text entered successfully : {}", locator);
	}

	/**
	 * Gets visible text from an element.
	 *
	 * @param locator element locator
	 * @return element text
	 */
	public static String getText(By locator) {

		log.debug("Getting text from element : {}", locator);

		WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));

		String text = element.getText();

		log.debug("Text retrieved : {}", text);

		return text;
	}

	/**
	 * Checks whether an element is displayed.
	 *
	 * @param locator element locator
	 * @return true if displayed, otherwise false
	 */
	public static boolean isDisplayed(By locator) {

		try {

			WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));

			return element.isDisplayed();

		} catch (Exception e) {

			log.debug("Element is not displayed : {}", locator);

			return false;
		}
	}
}