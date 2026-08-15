package com.amol.automation.driver;

import org.openqa.selenium.WebDriver;

/**
 * Manages WebDriver instances using ThreadLocal.
 *
 * ThreadLocal ensures that each parallel test thread
 * has its own independent WebDriver instance.
 *
 * Responsibilities:
 * - Store WebDriver for current thread
 * - Retrieve WebDriver for current thread
 * - Check driver initialization
 * - Remove WebDriver after execution
 */
public final class DriverManager {

    private DriverManager() {
        // Prevent object creation
    }

    // =========================================================
    // ThreadLocal WebDriver
    // =========================================================

    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    // =========================================================
    // Driver Management
    // =========================================================

    /**
     * Stores WebDriver for the current execution thread.
     *
     * @param driver WebDriver instance
     */
    public static void setDriver(WebDriver driver) {

        if (driver == null) {

            throw new IllegalArgumentException(
                    "WebDriver cannot be null");
        }

        DRIVER.set(driver);
    }

    /**
     * Returns WebDriver associated with the current thread.
     *
     * @return current thread's WebDriver
     */
    public static WebDriver getDriver() {

        WebDriver driver =
                DRIVER.get();

        if (driver == null) {

            throw new IllegalStateException(
                    "WebDriver is not initialized "
                    + "for the current thread");
        }

        return driver;
    }

    /**
     * Checks whether WebDriver is initialized
     * for the current execution thread.
     *
     * @return true if driver exists
     */
    public static boolean isDriverInitialized() {

        return DRIVER.get() != null;
    }

    // =========================================================
    // Cleanup
    // =========================================================

    /**
     * Removes WebDriver from the current thread.
     *
     * Important for:
     * - ThreadLocal cleanup
     * - Preventing stale driver references
     * - Preventing ThreadLocal memory leaks
     */
    public static void unload() {

        DRIVER.remove();
    }
}