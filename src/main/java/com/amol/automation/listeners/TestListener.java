package com.amol.automation.listeners;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;
import com.amol.automation.utils.ScreenshotUtils;
import com.aventstack.extentreports.ExtentTest;

public class TestListener implements ITestListener {

	private static final Logger log =
			LoggerUtils.getLogger(TestListener.class);

	@Override
	public void onStart(ITestContext context) {

		log.info("========== Test Execution Started ==========");

		ExtentReportManager.initReports();

	}

	@Override
	public void onTestStart(ITestResult result) {

		String testName =
				result.getTestClass().getRealClass().getSimpleName()
				+ "."
				+ result.getMethod().getMethodName();

		log.info("Starting Test : {}", testName);

		ExtentReportManager.createTest(testName);

		ExtentReportManager.getTest().info("Test Started : " + testName);

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		String testName = result.getMethod().getMethodName();

		log.info("Test Passed : {}", testName);

		ExtentTest test = ExtentReportManager.getTest();

		if (test != null) {

			test.pass("Test Passed Successfully");

		}

	}

	@Override
	public void onTestFailure(ITestResult result) {

		String testName =
				result.getTestClass().getRealClass().getSimpleName()
				+ "_"
				+ result.getMethod().getMethodName();

		log.error("Test Failed : {}", testName);

		ExtentTest test = ExtentReportManager.getTest();

		String screenshotPath = null;

		try {

			screenshotPath =
					ScreenshotUtils.captureScreenshot(testName);

		} catch (Exception e) {

			log.error("Unable to capture screenshot", e);

		}

		if (test != null) {

			test.fail(result.getThrowable());

			if (screenshotPath != null) {

				try {

					test.addScreenCaptureFromPath(screenshotPath);

				} catch (Exception e) {

					log.error("Unable to attach screenshot", e);

				}

			}

		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		log.warn("Test Skipped : {}", result.getMethod().getMethodName());

		ExtentTest test = ExtentReportManager.getTest();

		if (test != null) {

			if (result.getThrowable() != null) {

				test.skip(result.getThrowable());

			} else {

				test.skip("Test Skipped");

			}

		}

	}

	@Override
	public void onFinish(ITestContext context) {

		log.info("========== Test Execution Completed ==========");

		ExtentReportManager.flushReports();

	}

}