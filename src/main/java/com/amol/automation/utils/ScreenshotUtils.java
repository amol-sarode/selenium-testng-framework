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

import com.amol.automation.driver.DriverManager;

public final class ScreenshotUtils {

	private ScreenshotUtils() {

	}

	private static final Logger log = LoggerUtils.getLogger(ScreenshotUtils.class);

	public static String captureScreenshot(String testName) {

		String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

		String fileName = testName + "_" + timeStamp + ".png";

		Path screenshotFolder = Paths.get(System.getProperty("user.dir"), "screenshots");

		Path destination = screenshotFolder.resolve(fileName);

		try {

			Files.createDirectories(screenshotFolder);

			WebDriver driver = DriverManager.getDriver();

			if (driver == null) {

				throw new IllegalStateException("Driver is null. Screenshot cannot be captured");

			}

			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

			log.info("Screenshot captured successfully : {}", destination);

		} catch (IOException e) {

			log.error("Unable to capture screenshot", e);

			throw new RuntimeException("Screenshot capture failed", e);

		}

		return destination.toAbsolutePath().toString();

	}

}