package com.amol.automation.listeners;

import org.apache.logging.log4j.Logger;
import org.testng.IExecutionListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.amol.automation.driver.DriverManager;
import com.amol.automation.reports.ExtentReportManager;
import com.amol.automation.utils.LoggerUtils;
import com.amol.automation.utils.ScreenshotUtils;

/**
 * Central TestNG Listener.
 *
 * Extent Reporting Flow:
 *
 * TestNG @Test(description)
 *          |
 *          v
 *     Test Title
 *          |
 *          v
 *    Action createNode()
 *          |
 *          v
 *     Page info()
 *          |
 *          v
 * Listener final PASS / FAIL
 *
 * Responsibilities:
 * - Initialize Extent Report
 * - Create Extent Test automatically from @Test description
 * - Assign TestNG groups as Extent categories
 * - Handle test execution status
 * - Capture failure screenshots
 * - Attach screenshots to Extent Report
 * - Flush Extent Report
 *
 * ThreadLocal cleanup is handled by BaseTest.tearDown().
 */
public final class TestListener
        implements ITestListener, IExecutionListener {

    private static final Logger log =
            LoggerUtils.getLogger(TestListener.class);

    // =========================================================
    // Execution Lifecycle
    // =========================================================

    /**
     * Called once before complete TestNG execution.
     */
    @Override
    public void onExecutionStart() {

        log.info(
                "========== Test Execution Started ==========");

        ExtentReportManager.initReports();
    }

    /**
     * Called once after complete TestNG execution.
     */
    @Override
    public void onExecutionFinish() {

        log.info(
                "========== Test Execution Finished ==========");

        ExtentReportManager.flushReports();
    }

    // =========================================================
    // Test Start
    // =========================================================

    /**
     * Creates the parent Extent Test automatically.
     *
     * Priority:
     *
     * @Test(description)
     *        |
     *        v
     * Extent Test Title
     *
     * If description is unavailable,
     * Java method name is used.
     */
    @Override
    public void onTestStart(
            ITestResult result) {

        String testTitle =
                getTestTitle(result);

        String technicalTestName =
                getTestName(result);

        log.info(
                "Test Started : {}",
                technicalTestName);

        // -----------------------------------------------------
        // Create Test Title
        // -----------------------------------------------------

        ExtentReportManager.createTest(
                testTitle);

        // -----------------------------------------------------
        // Assign TestNG Groups
        // -----------------------------------------------------

        String[] groups =
                result.getMethod()
                        .getGroups();

        if (groups != null &&
                groups.length > 0) {

            for (String group : groups) {

                ExtentReportManager
                        .getTest()
                        .assignCategory(group);
            }
        }

        log.info(
                "Extent Test Created : {}",
                testTitle);
    }

    // =========================================================
    // Test Success
    // =========================================================

    /**
     * Marks the complete test as PASS.
     *
     * Final PASS is controlled centrally by Listener.
     */
    @Override
    public void onTestSuccess(
            ITestResult result) {

        String testName =
                getTestName(result);

        log.info(
                "Test Passed : {}",
                testName);

        if (ExtentReportManager.hasTest()) {

            ExtentReportManager.testPass(
                    "Test Passed Successfully");
        }

        /*
         * ThreadLocal cleanup is handled by
         * BaseTest.tearDown().
         */
    }

    // =========================================================
    // Test Failure
    // =========================================================

    /**
     * Marks the complete test as FAIL and captures
     * a failure screenshot.
     */
    @Override
    public void onTestFailure(
            ITestResult result) {

        String testName =
                getTestName(result);

        log.error(
                "Test Failed : {}",
                testName);

        Throwable throwable =
                result.getThrowable();

        String failureMessage =
                getFailureMessage(throwable);

        // -----------------------------------------------------
        // Final FAIL
        // -----------------------------------------------------

        if (ExtentReportManager.hasTest()) {

            ExtentReportManager.testFail(
                    "Test Failed : "
                            + failureMessage);
        }

        // -----------------------------------------------------
        // Failure Screenshot
        // -----------------------------------------------------

        if (!DriverManager.isDriverInitialized()) {

            log.warn(
                    "WebDriver is not available. "
                            + "Failure screenshot cannot be captured.");

            return;
        }

        try {

            String screenshotPath =
                    ScreenshotUtils.captureScreenshot(
                            testName);

            log.info(
                    "Failure screenshot captured : {}",
                    screenshotPath);

            if (ExtentReportManager.hasTest()) {

                ExtentReportManager.addScreenshot(
                        screenshotPath);

                log.info(
                        "Failure screenshot attached to Extent Report");
            }

        } catch (Exception e) {

            log.error(
                    "Unable to capture failure screenshot",
                    e);
        }

        /*
         * ThreadLocal cleanup is handled by
         * BaseTest.tearDown().
         */
    }

    // =========================================================
    // Test Skipped
    // =========================================================

    /**
     * Marks the complete test as SKIPPED.
     */
    @Override
    public void onTestSkipped(
            ITestResult result) {

        String testName =
                getTestName(result);

        log.warn(
                "Test Skipped : {}",
                testName);

        if (ExtentReportManager.hasTest()) {

            Throwable throwable =
                    result.getThrowable();

            String message =
                    getFailureMessage(throwable);

            ExtentReportManager.skip(
                    "Test Skipped : "
                            + message);
        }

        /*
         * ThreadLocal cleanup is handled by
         * BaseTest.tearDown().
         */
    }

    // =========================================================
    // Success Percentage
    // =========================================================

    @Override
    public void onTestFailedButWithinSuccessPercentage(
            ITestResult result) {

        log.warn(
                "Test failed but within success percentage : {}",
                getTestName(result));
    }

    // =========================================================
    // Test Title
    // =========================================================

    /**
     * Gets Extent Test title from TestNG @Test description.
     *
     * Example:
     *
     * @Test(
     *     description =
     *     "Verify product add to cart with valid user"
     * )
     *
     * Extent Test Title:
     *
     * Verify product add to cart with valid user
     */
    private String getTestTitle(
            ITestResult result) {

        String description =
                result.getMethod()
                        .getDescription();

        if (description != null &&
                !description.trim().isEmpty()) {

            return description.trim();
        }

        return result.getMethod()
                .getMethodName();
    }

    // =========================================================
    // Technical Test Name
    // =========================================================

    /**
     * Returns:
     *
     * ClassName.methodName
     */
    private String getTestName(
            ITestResult result) {

        return result.getTestClass()
                .getRealClass()
                .getSimpleName()
                + "."
                + result.getMethod()
                .getMethodName();
    }

    // =========================================================
    // Failure Message
    // =========================================================

    /**
     * Safely extracts failure/skip message.
     */
    private String getFailureMessage(
            Throwable throwable) {

        if (throwable == null) {

            return "No failure details available";
        }

        String message =
                throwable.getMessage();

        if (message == null ||
                message.trim().isEmpty()) {

            return throwable
                    .getClass()
                    .getSimpleName();
        }

        return message;
    }
}