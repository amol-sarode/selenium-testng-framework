package com.amol.automation.utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.amol.automation.driver.DriverManager;

/**
 * JavaScript Utility class.
 *
 * Provides reusable JavaScript-based browser operations.
 */
public final class JavaScriptUtil {

	private JavaScriptUtil() {
	}

	private static final Logger log = LoggerUtils.getLogger(JavaScriptUtil.class);

	/**
	 * Returns JavascriptExecutor for the current thread's WebDriver.
	 */
	private static JavascriptExecutor getJsExecutor() {

		WebDriver driver = DriverManager.getDriver();

		if (!(driver instanceof JavascriptExecutor)) {

			throw new IllegalStateException("Current WebDriver does not support JavaScript execution");
		}

		return (JavascriptExecutor) driver;
	}

	/**
	 * Clicks an element using JavaScript.
	 *
	 * @param element WebElement to click
	 */
	public static void click(WebElement element) {

		log.debug("Clicking element using JavaScript");

		getJsExecutor().executeScript("arguments[0].click();", element);

		log.debug("JavaScript click completed");
	}

	/**
	 * Scrolls the element into the center of the viewport.
	 *
	 * @param element WebElement to scroll to
	 */
	public static void scrollIntoView(WebElement element) {

		log.debug("Scrolling element into view");

		getJsExecutor().executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", element);
	}

	/**
	 * Highlights an element with a red border.
	 *
	 * @param element WebElement to highlight
	 */
	public static void highlightElement(WebElement element) {

		log.debug("Highlighting element");

		getJsExecutor().executeScript("arguments[0].style.border='3px solid red';", element);
	}

	/**
	 * Sets a value using JavaScript and triggers a change event.
	 *
	 * @param element WebElement
	 * @param value   value to set
	 */
	public static void setValue(WebElement element, String value) {

		log.debug("Setting element value using JavaScript");

		getJsExecutor().executeScript(
				"arguments[0].value=arguments[1];" + "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
				element, value);
	}

	/**
	 * Returns the current browser page title.
	 *
	 * @return page title
	 */
	public static String getPageTitle() {

		return (String) getJsExecutor().executeScript("return document.title;");
	}

	/**
	 * Returns the current browser URL.
	 *
	 * @return current URL
	 */
	public static String getCurrentUrl() {

		return (String) getJsExecutor().executeScript("return document.URL;");
	}

	/**
	 * Scrolls the page to the top.
	 */
	public static void scrollToTop() {

		log.debug("Scrolling page to top");

		getJsExecutor().executeScript("window.scrollTo(0, 0);");
	}

	/**
	 * Scrolls the page to the bottom.
	 */
	public static void scrollToBottom() {

		log.debug("Scrolling page to bottom");

		getJsExecutor().executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	/**
	 * Refreshes the current page using JavaScript.
	 */
	public static void refreshPage() {

		log.debug("Refreshing page using JavaScript");

		getJsExecutor().executeScript("window.location.reload();");
	}
}