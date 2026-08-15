package com.amol.automation.base;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.amol.automation.driver.DriverFactory;
import com.amol.automation.driver.DriverManager;
import com.amol.automation.factory.ActionObjectManager;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ConfigReader;
import com.amol.automation.utils.LoggerUtils;

/**
 * Base class for all TestNG test classes.
 *
 * Responsibilities: - Initialize WebDriver - Open application URL - Cleanup
 * Action Objects - Cleanup Page Objects - Cleanup Extent ThreadLocal objects -
 * Quit WebDriver
 *
 * Reporting and failure screenshots are handled by TestListener.
 *
 * Reporting flow:
 *
 * TestNG @Test(description) | v TestListener | v ExtentReportManager | v Test |
 * v Node | v INFO / PASS / FAIL
 */
public class BaseTest {

	private static final Logger log = LoggerUtils.getLogger(BaseTest.class);

	// =========================================================
	// Test Setup
	// =========================================================

	/**
	 * Initializes WebDriver and opens the application.
	 *
	 * Executed before every @Test method.
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp() {

		log.info("========== Starting Test Setup ==========");

		try {

			// -------------------------------------------------
			// Initialize WebDriver
			// -------------------------------------------------

			DriverFactory.initializeDriver();

			log.info("WebDriver initialized successfully");

			// -------------------------------------------------
			// Open Application
			// -------------------------------------------------

			String url = ConfigReader.getInstance().getProperty("app.url");

			if (url == null || url.trim().isEmpty()) {

				throw new IllegalStateException("Application URL 'app.url' is not configured.");
			}

			log.info("Opening application URL : {}", url);

			DriverManager.getDriver().get(url);

			log.info("Application opened successfully");

		} catch (Exception e) {

			log.error("Test setup failed", e);

			/*
			 * If setup fails, make sure the driver does not remain active.
			 */
			try {

				DriverFactory.quitDriver();

			} catch (Exception cleanupException) {

				log.error("Unable to cleanup driver after setup failure", cleanupException);
			}

			throw e;
		}
	}

	// =========================================================
	// Test Cleanup
	// =========================================================

	/**
	 * Cleans up resources after every @Test method.
	 *
	 * Important:
	 *
	 * TestListener.onTestSuccess() TestListener.onTestFailure()
	 * TestListener.onTestSkipped()
	 *
	 * execute before @AfterMethod cleanup.
	 *
	 * Therefore the listener can still access:
	 *
	 * - Extent Test - Extent Node - WebDriver
	 *
	 * before these resources are unloaded.
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		log.info("========== Test Cleanup Started ==========");

		try {

			// -------------------------------------------------
			// Cleanup Action Objects
			// -------------------------------------------------

			try {

				ActionObjectManager.unload();

				log.debug("Action objects unloaded successfully");

			} catch (Exception e) {

				log.error("Unable to unload Action objects", e);
			}

			// -------------------------------------------------
			// Cleanup Page Objects
			// -------------------------------------------------

			try {

				PageObjectManager.unload();

				log.debug("Page objects unloaded successfully");

			} catch (Exception e) {

				log.error("Unable to unload Page objects", e);
			}

			// -------------------------------------------------
			// Cleanup Extent ThreadLocal
			// -------------------------------------------------

			try {

				ExtentReportManager.unload();

				log.debug("Extent ThreadLocal objects unloaded successfully");

			} catch (Exception e) {

				log.error("Unable to unload Extent ThreadLocal objects", e);
			}

		} finally {

			// -------------------------------------------------
			// Quit WebDriver
			// -------------------------------------------------

			try {

				DriverFactory.quitDriver();

				log.info("WebDriver quit successfully");

			} catch (Exception e) {

				log.error("Unable to quit WebDriver", e);
			}
		}

		log.info("========== Test Cleanup Completed ==========");
	}
}