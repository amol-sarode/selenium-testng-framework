package com.amol.automation.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amol.automation.driver.DriverManager;

public final class ElementUtils {

	private static final int WAIT_TIME = 20;

	private ElementUtils() {
	}

	private static WebDriverWait getWait() {

		return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_TIME));
	}

	public static void click(By locator) {

		WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));

		element.click();
	}

	public static void enterText(By locator, String text) {

		WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));

		element.clear();

		element.sendKeys(text);
	}

	public static String getText(By locator) {

		WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));

		return element.getText();
	}

	public static boolean isDisplayed(By locator) {

		try {

			WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));

			return element.isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}
}