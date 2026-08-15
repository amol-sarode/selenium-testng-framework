package com.amol.automation.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.amol.automation.constants.FrameworkConstants;
import com.amol.automation.driver.DriverManager;

/**
 * Utility class for screenshot handling.
 *
 * Responsibilities:
 * - Capture current browser screen
 * - Save screenshot in screenshots folder
 *
 * Screenshot handling is centralized here.
 */
public final class ScreenshotUtils {

    private ScreenshotUtils() {
        // Prevent object creation
    }

    private static final Logger log =
            LoggerUtils.getLogger(ScreenshotUtils.class);

    private static final DateTimeFormatter TIMESTAMP_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    /**
     * Captures the current browser screen.
     *
     * @param testName test name
     * @return absolute screenshot path
     */
    public static String captureScreenshot(String testName) {

        if (testName == null ||
                testName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Test name cannot be null or empty");
        }

        WebDriver driver =
                DriverManager.getDriver();

        if (!(driver instanceof TakesScreenshot)) {

            throw new IllegalStateException(
                    "WebDriver does not support screenshots");
        }

        String timestamp =
                LocalDateTime.now()
                        .format(TIMESTAMP_FORMAT);

        String fileName =
                sanitizeFileName(testName)
                + "_"
                + timestamp
                + ".png";

        Path screenshotFolder =
                Paths.get(
                        FrameworkConstants.SCREENSHOT_FOLDER);

        Path destination =
                screenshotFolder.resolve(fileName);

        try {

            Files.createDirectories(
                    screenshotFolder);

            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(
                                    OutputType.FILE);

            Files.copy(
                    source.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING);

            String screenshotPath =
                    destination
                            .toAbsolutePath()
                            .toString();

            log.info(
                    "Screenshot saved : {}",
                    screenshotPath);

            return screenshotPath;

        } catch (IOException e) {

            log.error(
                    "Unable to save screenshot",
                    e);

            throw new RuntimeException(
                    "Screenshot capture failed",
                    e);
        }
    }

    /**
     * Makes test name safe for a file name.
     *
     * @param fileName original test name
     * @return sanitized file name
     */
    private static String sanitizeFileName(
            String fileName) {

        return fileName
                .trim()
                .replaceAll(
                        "[\\\\/:*?\"<>|]",
                        "_")
                .replaceAll(
                        "\\s+",
                        "_");
    }
}