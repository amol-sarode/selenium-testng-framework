package com.amol.automation.listeners;

import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;
import com.amol.automation.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

	private static final Logger log = LoggerUtils.getLogger(TestListener.class);

	@Override
	public void onTestStart(ITestResult result) {

		String testName = result.getTestClass().getRealClass().getSimpleName() + "."
				+ result.getMethod().getMethodName();

		log.info("Test Started : {}", testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		String testName = result.getMethod().getMethodName();

		log.info("Test Passed : {}", testName);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String testName = result.getTestClass().getRealClass().getSimpleName() + "_"
				+ result.getMethod().getMethodName();

		log.error("Test Failed : {}", testName);

		try {

			String screenshotPath = ScreenshotUtils.captureScreenshot(testName);

			/*
			 * Report already exists because it was created directly from the @Test method.
			 */
			ExtentReportManager.getTest().fail(result.getThrowable());

			ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);

		} catch (Exception e) {

			log.error("Unable to capture failure screenshot", e);
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		log.warn("Test Skipped : {}", result.getMethod().getMethodName());
	}
}