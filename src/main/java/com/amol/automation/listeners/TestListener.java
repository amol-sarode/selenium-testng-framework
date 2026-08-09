package com.amol.automation.listeners;

import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.amol.automation.driver.DriverManager;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;
import com.amol.automation.utils.ScreenshotUtils;

/**
 * TestNG listener.
 *
 * Responsibilities: - Log test execution status - Capture screenshot on test
 * failure - Attach screenshot to Extent Report - Log failure reason
 *
 * Screenshot logic is centralized here.
 */
public class TestListener implements ITestListener {

	private static final Logger log = LoggerUtils.getLogger(TestListener.class);

	// =========================================================
	// Test Start
	// =========================================================

	@Override
	public void onTestStart(ITestResult result) {

		log.info("Test Started : {}", getTestName(result));
	}

	// =========================================================
	// Test Success
	// =========================================================

	@Override
	public void onTestSuccess(ITestResult result) {

		log.info("Test Passed : {}", getTestName(result));
	}

	// =========================================================
	// Test Failure
	// =========================================================

	@Override
	public void onTestFailure(ITestResult result) {

		String testName = getTestName(result);

		log.error("Test Failed : {}", testName);

		Throwable throwable = result.getThrowable();

		if (throwable != null) {
			log.error("Failure Reason : {}", throwable.getMessage());
		}

		/*
		 * IMPORTANT:
		 *
		 * At this point the failed test method has completed, but BaseTest @AfterMethod
		 * has not closed the browser yet.
		 *
		 * Therefore the screenshot captures the CURRENT browser state.
		 *
		 * For invalid login this should contain the red SauceDemo error message
		 * displayed after clicking Login.
		 */
		if (!DriverManager.isDriverInitialized()) {

			log.error("WebDriver is not available. Screenshot cannot be captured.");

			return;
		}

		try {

			String screenshotPath = ScreenshotUtils.captureScreenshot(testName);

			log.info("Failure screenshot captured : {}", screenshotPath);

			/*
			 * Add failure information to Extent Report.
			 */
			if (ExtentReportManager.hasTest()) {

				String failureMessage = throwable != null ? throwable.getMessage() : "Test failed";

				ExtentReportManager.fail("Test Failed : " + failureMessage);

				ExtentReportManager.addScreenshot(screenshotPath);

				log.info("Failure screenshot attached to Extent Report");
			}

		} catch (Exception e) {

			log.error("Unable to capture failure screenshot", e);
		}
	}

	// =========================================================
	// Test Skipped
	// =========================================================

	@Override
	public void onTestSkipped(ITestResult result) {

		log.warn("Test Skipped : {}", getTestName(result));
	}

	// =========================================================
	// Utility
	// =========================================================

	private String getTestName(ITestResult result) {

		return result.getTestClass().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
	}
}