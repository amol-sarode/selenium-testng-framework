package com.amol.automation.reports;

import java.io.IOException;
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

/**
 * Manages Extent Reports.
 *
 * ThreadLocal is used so the framework remains safe for
 * parallel test execution.
 */
public final class ExtentReportManager {

    private ExtentReportManager() {
    }

    private static ExtentReports extent;

    private static final ThreadLocal<ExtentTest> currentTest =
            new ThreadLocal<>();

    private static final ThreadLocal<ExtentTest> currentNode =
            new ThreadLocal<>();

    private static final Logger log =
            LoggerUtils.getLogger(ExtentReportManager.class);

    // =========================================================
    // Report Initialization
    // =========================================================

    public static synchronized void initReports() {

        if (extent != null) {
            return;
        }

        try {

            Path reportFolder =
                    Paths.get(
                            System.getProperty("user.dir"),
                            "reports"
                    );

            Files.createDirectories(reportFolder);

            String timestamp =
                    LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern(
                                    "yyyyMMdd_HHmmss"
                            )
                    );

            String reportPath =
                    reportFolder
                            .resolve(
                                    "AutomationReport_"
                                            + timestamp
                                            + ".html"
                            )
                            .toString();

            ExtentHtmlReporter htmlReporter =
                    new ExtentHtmlReporter(reportPath);

            htmlReporter.config()
                    .setDocumentTitle(
                            "Automation Test Report"
                    );

            htmlReporter.config()
                    .setReportName(
                            "Selenium Java TestNG Framework"
                    );

            extent = new ExtentReports();

            extent.attachReporter(htmlReporter);

            extent.setSystemInfo(
                    "Framework",
                    "Selenium + Java + TestNG"
            );

            extent.setSystemInfo(
                    "Author",
                    "Amol"
            );

            extent.setSystemInfo(
                    "Environment",
                    "QA"
            );

            log.info(
                    "Extent Report Created : {}",
                    reportPath
            );

        } catch (Exception e) {

            log.error(
                    "Extent Report initialization failed",
                    e
            );

            throw new RuntimeException(
                    "Extent Report initialization failed",
                    e
            );
        }
    }

    // =========================================================
    // Test Management
    // =========================================================

    public static ExtentTest createTest(String testName) {

        if (extent == null) {
            initReports();
        }

        ExtentTest test =
                extent.createTest(testName);

        currentTest.set(test);

        currentNode.remove();

        return test;
    }

    public static ExtentTest getTest() {

        ExtentTest test =
                currentTest.get();

        if (test == null) {
            throw new IllegalStateException(
                    "Extent Test is not created."
            );
        }

        return test;
    }

    public static boolean hasTest() {

        return currentTest.get() != null;
    }

    // =========================================================
    // Node Management
    // =========================================================

    public static ExtentTest createNode(String nodeName) {

        ExtentTest node;

        if (currentNode.get() != null) {

            node =
                    currentNode.get()
                            .createNode(nodeName);

        } else {

            node =
                    getTest()
                            .createNode(nodeName);
        }

        currentNode.set(node);

        return node;
    }

    public static ExtentTest getCurrentNode() {

        ExtentTest node =
                currentNode.get();

        return node != null
                ? node
                : getTest();
    }

    // =========================================================
    // Logging
    // =========================================================

    public static void info(String message) {

        getCurrentNode()
                .log(Status.INFO, message);
    }

    public static void pass(String message) {

        getCurrentNode()
                .log(Status.PASS, message);
    }

    public static void fail(String message) {

        getTest()
                .log(Status.FAIL, message);
    }

    public static void warning(String message) {

        getCurrentNode()
                .log(Status.WARNING, message);
    }

    // =========================================================
    // Screenshot
    // =========================================================

    /**
     * Attaches screenshot to the current Extent test.
     *
     * Screenshot is called by TestListener only.
     */
    public static void addScreenshot(
            String screenshotPath) {

        try {
			getTest()
			        .addScreenCaptureFromPath(
			                screenshotPath
			        );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // =========================================================
    // Cleanup
    // =========================================================

    public static void unload() {

        currentNode.remove();
        currentTest.remove();
    }

    // =========================================================
    // Report Flush
    // =========================================================

    public static synchronized void flushReports() {

        if (extent != null) {

            extent.flush();

            log.info(
                    "Extent Report flushed"
            );
        }
    }
}