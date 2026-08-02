package com.amol.automation.utils;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amol.automation.driver.DriverManager;

public final class WaitUtils {

	private WaitUtils() {
	}

	private static WebDriverWait getWait() {

		return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));
	}

	public static void waitForVisibility(WebElement element) {

		getWait().until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForClickable(WebElement element) {

		getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForInvisibility(WebElement element) {

		getWait().until(ExpectedConditions.invisibilityOf(element));
	}

}