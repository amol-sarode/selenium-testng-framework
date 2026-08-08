package com.amol.automation.reports;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.Logger;

import com.amol.automation.utils.LoggerUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public final class ExtentReportManager {

	private ExtentReportManager() {
	}

	private static ExtentReports extent;

	private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

	private static final ThreadLocal<ExtentTest> currentNode = new ThreadLocal<>();

	private static final Logger log = LoggerUtils.getLogger(ExtentReportManager.class);

	/**
	 * Initialize Extent Report.
	 */
	public static synchronized void initReports() {

		if (extent != null) {
			return;
		}

		try {

			Path folder = Paths.get(System.getProperty("user.dir"), "reports");

			Files.createDirectories(folder);

			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

			String path = folder.resolve("AutomationReport_" + timestamp + ".html").toString();

			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);

			htmlReporter.config().setDocumentTitle("Automation Test Report");

			htmlReporter.config().setReportName("Selenium Java TestNG Framework");

			extent = new ExtentReports();

			extent.attachReporter(htmlReporter);

			extent.setSystemInfo("Framework", "Selenium + Java + TestNG");

			extent.setSystemInfo("Author", "Amol");

			extent.setSystemInfo("Environment", "QA");

			log.info("Extent Report Created : {}", path);

		} catch (Exception e) {

			log.error("Extent Report initialization failed", e);

			throw new RuntimeException("Extent Report initialization failed", e);
		}
	}

	/**
	 * Create main test.
	 */
	public static ExtentTest createTest(String testName) {

		if (extent == null) {
			initReports();
		}

		ExtentTest test = extent.createTest(testName);

		currentTest.set(test);

		return test;
	}

	/**
	 * Get current test.
	 */
	public static ExtentTest getTest() {

		ExtentTest test = currentTest.get();

		if (test == null) {

			throw new IllegalStateException("Extent Test is not created. " + "Call createTest() from @Test method.");
		}

		return test;
	}

	/**
	 * Create business-flow node.
	 *
	 * Example:
	 *
	 * Login Process
	 */
	public static ExtentTest createNode(String nodeName) {

		ExtentTest node;

		if (currentNode.get() != null) {

			node = currentNode.get().createNode(nodeName);

		} else {

			node = getTest().createNode(nodeName);
		}

		currentNode.set(node);

		return node;
	}

	/**
	 * Get current node.
	 */
	public static ExtentTest getCurrentNode() {

		ExtentTest node = currentNode.get();

		if (node == null) {
			return getTest();
		}

		return node;
	}

	/**
	 * INFO log.
	 */
	public static void info(String message) {

		getCurrentNode().log(Status.INFO, message);
	}

	/**
	 * PASS log.
	 */
	public static void pass(String message) {

		getCurrentNode().log(Status.PASS, message);
	}

	/**
	 * FAIL log.
	 */
	public static void fail(String message) {

		getCurrentNode().log(Status.FAIL, message);
	}

	/**
	 * WARNING log.
	 */
	public static void warning(String message) {

		getCurrentNode().log(Status.WARNING, message);
	}

	/**
	 * Clear current test and node.
	 */
	public static void unload() {

		currentNode.remove();

		currentTest.remove();
	}

	/**
	 * Flush report.
	 */
	public static synchronized void flushReports() {

		if (extent != null) {

			extent.flush();

			log.info("Extent Report flushed");
		}
	}
}