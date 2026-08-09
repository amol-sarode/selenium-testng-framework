package com.amol.automation.base;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.amol.automation.driver.DriverFactory;
import com.amol.automation.driver.DriverManager;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.ConfigReader;
import com.amol.automation.utils.LoggerUtils;

/**
 * Base class for all test classes.
 *
 * Responsibilities: - Initialize Extent Report - Initialize WebDriver - Open
 * application URL - Cleanup page objects - Close WebDriver - Flush Extent
 * Report
 *
 * Screenshot handling is completely managed by TestListener.
 */
public class BaseTest {

	private static final Logger log = LoggerUtils.getLogger(BaseTest.class);

	// =========================================================
	// Suite Setup
	// =========================================================

	@BeforeSuite(alwaysRun = true)
	public void startReport() {

		log.info("========== Initializing Extent Report ==========");

		ExtentReportManager.initReports();
	}

	// =========================================================
	// Test Setup
	// =========================================================

	@BeforeMethod(alwaysRun = true)
	public void setUp() {

		log.info("========== Starting Test ==========");

		DriverFactory.initializeDriver();

		ConfigReader config = ConfigReader.getInstance();

		String url = config.getProperty("app.url");

		if (url == null || url.trim().isEmpty()) {
			throw new RuntimeException("Application URL is missing");
		}

		log.info("Opening application URL : {}", url);

		DriverManager.getDriver().get(url);
	}

	// =========================================================
	// Test Cleanup
	// =========================================================

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		log.info("========== Test Cleanup Started ==========");

		/*
		 * Screenshot is NOT captured here.
		 *
		 * TestListener captures the screenshot when the test fails, while the browser
		 * is still open.
		 */

		PageObjectManager.unload();

		DriverFactory.quitDriver();

		ExtentReportManager.unload();

		log.info("========== Test Execution Completed ==========");
	}

	// =========================================================
	// Suite Cleanup
	// =========================================================

	@AfterSuite(alwaysRun = true)
	public void finishReport() {

		log.info("========== Flushing Extent Report ==========");

		ExtentReportManager.flushReports();
	}
}