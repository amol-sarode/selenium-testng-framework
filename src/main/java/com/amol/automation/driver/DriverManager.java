package com.amol.automation.driver;

import org.openqa.selenium.WebDriver;

/**
 * DriverManager
 *
 * Manages WebDriver instances using ThreadLocal.
 *
 * ThreadLocal allows each parallel test thread to have its own independent
 * WebDriver instance.
 *
 * Responsibilities: - Store WebDriver for current thread - Retrieve WebDriver
 * for current thread - Remove WebDriver after execution
 */
public final class DriverManager {

	private DriverManager() {
		// Prevent object creation
	}

	/**
	 * ThreadLocal WebDriver.
	 *
	 * Each test thread gets its own WebDriver instance.
	 */
	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

	/**
	 * Stores WebDriver for the current thread.
	 *
	 * @param driver WebDriver instance
	 */
	public static void setDriver(WebDriver driver) {

		if (driver == null) {

			throw new IllegalArgumentException("WebDriver cannot be null");
		}

		DRIVER.set(driver);
	}

	/**
	 * Returns WebDriver associated with the current thread.
	 *
	 * @return current thread's WebDriver
	 */
	public static WebDriver getDriver() {

		WebDriver driver = DRIVER.get();

		if (driver == null) {

			throw new IllegalStateException("WebDriver is not initialized for the current thread");
		}

		return driver;
	}

	/**
	 * Checks whether a WebDriver exists for the current thread.
	 *
	 * @return true if driver exists
	 */
	public static boolean isDriverInitialized() {

		return DRIVER.get() != null;
	}

	/**
	 * Removes WebDriver from the current thread.
	 *
	 * This is important for ThreadLocal cleanup and prevents thread-local memory
	 * leaks.
	 */
	public static void unload() {

		DRIVER.remove();
	}
}