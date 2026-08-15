package com.amol.automation.reports;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.Logger;

import com.amol.automation.enums.BrowserType;
import com.amol.automation.enums.Environment;
import com.amol.automation.utils.LoggerUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/**
 * Central manager for Extent Reports.
 *
 * Reporting hierarchy:
 *
 * Test Title | +-- Business Node | +-- INFO +-- PASS +-- FAIL
 *
 * Responsibilities: - Initialize Extent Report - Create parent test - Create
 * business nodes - Log page-level INFO/PASS/FAIL - Log final test
 * PASS/FAIL/SKIP - Attach screenshots - Manage ThreadLocal objects - Flush
 * report
 *
 * Assertions remain in Test classes.
 */
public final class ExtentReportManager {

	private ExtentReportManager() {
		// Prevent object creation
	}

	private static final Logger log = LoggerUtils.getLogger(ExtentReportManager.class);

	private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

	private static ExtentReports extent;

	private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();

	private static final ThreadLocal<ExtentTest> CURRENT_NODE = new ThreadLocal<>();

	// =========================================================
	// Report Initialization
	// =========================================================

	public static synchronized void initReports() {

		if (extent != null) {
			return;
		}

		try {

			// -------------------------------------------------
			// Report Folder
			// -------------------------------------------------

			Path reportFolder = Paths.get(System.getProperty("user.dir"), "reports");

			Files.createDirectories(reportFolder);

			// -------------------------------------------------
			// Report File
			// -------------------------------------------------

			String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);

			Path reportPath = reportFolder.resolve("AutomationReport_" + timestamp + ".html");

			// -------------------------------------------------
			// Extent HTML Reporter
			// -------------------------------------------------

			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath.toString());

			htmlReporter.config().setDocumentTitle("Automation Test Report");

			htmlReporter.config().setReportName("Selenium Java TestNG Framework");

			// -------------------------------------------------
			// Extent Reports
			// -------------------------------------------------

			extent = new ExtentReports();

			extent.attachReporter(htmlReporter);

			// -------------------------------------------------
			// System Information
			// -------------------------------------------------

			extent.setSystemInfo("Framework", "Selenium + Java + TestNG");

			extent.setSystemInfo("Environment", Environment.QA.name());

			extent.setSystemInfo("Browser", System.getProperty("browser", BrowserType.CHROME.name()));

			extent.setSystemInfo("Engineer", "Amol Sarode");

			log.info("Extent Report initialized : {}", reportPath.toAbsolutePath());

			log.info("Execution Environment : {}", Environment.QA.name());

		} catch (IllegalArgumentException e) {

			log.error("Invalid environment configured", e);

			throw new RuntimeException("Invalid environment. Use QA, UAT or PROD.", e);

		} catch (Exception e) {

			log.error("Extent Report initialization failed", e);

			throw new RuntimeException("Extent Report initialization failed", e);
		}
	}

	// =========================================================
	// Test
	// =========================================================

	/**
	 * Creates parent Test.
	 *
	 * Called by TestListener.
	 */
	public static void createTest(String testName) {

		validateMessage(testName, "Test name");

		if (extent == null) {
			initReports();
		}

		ExtentTest test = extent.createTest(testName);

		CURRENT_TEST.set(test);

		CURRENT_NODE.remove();

		log.debug("Extent Test created : {}", testName);
	}

	/**
	 * Returns current parent test.
	 */
	public static ExtentTest getTest() {

		ExtentTest test = CURRENT_TEST.get();

		if (test == null) {

			throw new IllegalStateException("Extent Test is not available " + "for current thread");
		}

		return test;
	}

	/**
	 * Checks whether parent test exists.
	 */
	public static boolean hasTest() {

		return CURRENT_TEST.get() != null;
	}

	// =========================================================
	// Node
	// =========================================================

	/**
	 * Creates business node under current Test.
	 *
	 * Action classes use this method.
	 */
	public static ExtentTest createNode(String nodeName) {

		validateMessage(nodeName, "Node name");

		ExtentTest test = getTest();

		ExtentTest node = test.createNode(nodeName);

		CURRENT_NODE.set(node);

		log.debug("Extent Node created : {}", nodeName);

		return node;
	}

	/**
	 * Returns current logging node.
	 */
	private static ExtentTest getCurrentLogger() {

		ExtentTest node = CURRENT_NODE.get();

		if (node == null) {

			throw new IllegalStateException("Extent Node is not available " + "for current thread. "
					+ "Action must create a node " + "before Page Object reporting.");
		}

		return node;
	}

	// =========================================================
	// Page INFO
	// =========================================================

	public static void info(String message) {

		validateMessage(message, "Information message");

		getCurrentLogger().log(Status.INFO, message);
	}

	// =========================================================
	// Page PASS
	// =========================================================

	public static void pass(String message) {

		validateMessage(message, "Pass message");

		getCurrentLogger().log(Status.PASS, message);
	}

	// =========================================================
	// Page FAIL
	// =========================================================

	public static void fail(String message) {

		validateMessage(message, "Fail message");

		getCurrentLogger().log(Status.FAIL, message);
	}

	// =========================================================
	// Test PASS
	// =========================================================

	public static void testPass(String message) {

		validateMessage(message, "Test pass message");

		getTest().log(Status.PASS, message);
	}

	// =========================================================
	// Test FAIL
	// =========================================================

	public static void testFail(String message) {

		validateMessage(message, "Test fail message");

		getTest().log(Status.FAIL, message);
	}

	// =========================================================
	// Test SKIP
	// =========================================================

	public static void skip(String message) {

		validateMessage(message, "Skip message");

		getTest().log(Status.SKIP, message);
	}

	// =========================================================
	// Warning
	// =========================================================

	public static void warning(String message) {

		validateMessage(message, "Warning message");

		getCurrentLogger().log(Status.WARNING, message);
	}

	// =========================================================
	// Screenshot
	// =========================================================

	public static void addScreenshot(String screenshotPath) {

		validateMessage(screenshotPath, "Screenshot path");

		try {

			getTest().addScreenCaptureFromPath(screenshotPath);

			log.debug("Screenshot attached : {}", screenshotPath);

		} catch (IOException e) {

			log.error("Unable to attach screenshot", e);
		}
	}

	// =========================================================
	// Cleanup
	// =========================================================

	public static void unload() {

		CURRENT_NODE.remove();
		CURRENT_TEST.remove();

		log.debug("Extent ThreadLocal objects unloaded");
	}

	// =========================================================
	// Flush
	// =========================================================

	public static synchronized void flushReports() {

		if (extent != null) {

			extent.flush();

			log.info("Extent Report flushed successfully");
		}
	}

	// =========================================================
	// Validation
	// =========================================================

	private static void validateMessage(String message, String fieldName) {

		if (message == null || message.trim().isEmpty()) {

			throw new IllegalArgumentException(fieldName + " cannot be null or empty");
		}
	}
}