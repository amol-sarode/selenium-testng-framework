package com.amol.automation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.amol.automation.driver.DriverManager;

public final class JavaScriptUtil {

	private JavaScriptUtil() {

	}

	private static JavascriptExecutor getJsExecutor() {

		WebDriver driver = DriverManager.getDriver();

		if (driver == null) {

			throw new IllegalStateException("Driver is not initialized");

		}

		return (JavascriptExecutor) driver;

	}

	public static void click(WebElement element) {

		getJsExecutor().executeScript("arguments[0].click();", element);

	}

	public static void scrollIntoView(WebElement element) {

		getJsExecutor().executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", element);

	}

	public static void highlightElement(WebElement element) {

		getJsExecutor().executeScript("arguments[0].style.border='3px solid red';", element);

	}

	public static void setValue(WebElement element, String value) {

		getJsExecutor().executeScript(
				"arguments[0].value=arguments[1];" + "arguments[0].dispatchEvent(new Event('change'));", element,
				value);

	}

	public static String getPageTitle() {

		return (String) getJsExecutor().executeScript("return document.title;");

	}

	public static String getCurrentUrl() {

		return (String) getJsExecutor().executeScript("return document.URL;");

	}

	public static void scrollToTop() {

		getJsExecutor().executeScript("window.scrollTo(0,0);");

	}

	public static void scrollToBottom() {

		getJsExecutor().executeScript("window.scrollTo(0,document.body.scrollHeight);");

	}

	public static void refreshPage() {

		getJsExecutor().executeScript("history.go(0);");

	}

}