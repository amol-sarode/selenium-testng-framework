package com.amol.automation.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

	private DriverManager() {

	}

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static void setDriver(WebDriver webDriver) {

		driver.set(webDriver);

	}

	public static WebDriver getDriver() {

		if (driver.get() == null) {

			throw new IllegalStateException("WebDriver is not initialized. Please initialize driver first.");

		}

		return driver.get();

	}

	public static void unload() {

		driver.remove();

	}

}