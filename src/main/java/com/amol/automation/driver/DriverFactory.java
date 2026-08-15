package com.amol.automation.driver;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.amol.automation.enums.BrowserType;
import com.amol.automation.utils.ConfigReader;
import com.amol.automation.utils.LoggerUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Factory responsible for creating and configuring WebDriver.
 *
 * Supports: - Chrome - Firefox - Edge
 *
 * Configuration is read from config.properties.
 */
public final class DriverFactory {

	private DriverFactory() {
		// Prevent object creation
	}

	private static final Logger log = LoggerUtils.getLogger(DriverFactory.class);

	// =========================================================
	// Driver Initialization
	// =========================================================

	/**
	 * Initializes WebDriver for the current execution thread.
	 */
	public static void initializeDriver() {

		if (DriverManager.isDriverInitialized()) {

			log.warn("WebDriver is already initialized for current thread");

			return;
		}

		ConfigReader config = ConfigReader.getInstance();

		BrowserType browser = getBrowser(config);

		boolean headless = config.getBooleanProperty("headless");

		int implicitWait = config.getIntProperty("implicit.wait");

		int pageLoadTimeout = config.getIntProperty("page.load.timeout");

		log.info("Initializing browser : {}", browser);

		log.info("Headless mode : {}", headless);

		WebDriver driver = createDriver(browser, headless);

		configureDriver(driver, headless, implicitWait, pageLoadTimeout);

		DriverManager.setDriver(driver);

		log.info("{} browser initialized successfully", browser);
	}

	// =========================================================
	// Browser Configuration
	// =========================================================

	
	/**
	 * Gets browser from configuration.
	 */
	private static BrowserType getBrowser(ConfigReader config) {

		String browserName = config.getProperty("browser");

		try {

			return BrowserType.valueOf(browserName.trim().toUpperCase());

		} catch (Exception e) {

			throw new IllegalArgumentException(
					"Unsupported browser: " + browserName + ". Supported browsers: " + "CHROME, FIREFOX, EDGE", e);
		}
	}

	// =========================================================
	// Driver Creation
	// =========================================================

	/**
	 * Creates WebDriver based on configured browser.
	 */
	private static WebDriver createDriver(BrowserType browser, boolean headless) {

		switch (browser) {

		case CHROME:
			return createChromeDriver(headless);

		case FIREFOX:
			return createFirefoxDriver(headless);

		case EDGE:
			return createEdgeDriver(headless);

		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
	}

	/**
	 * Creates ChromeDriver.
	 */
	private static WebDriver createChromeDriver(boolean headless) {

		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();

		if (headless) {

			options.addArguments("--headless=new");
		}

		return new ChromeDriver(options);
	}

	/**
	 * Creates FirefoxDriver.
	 */
	private static WebDriver createFirefoxDriver(boolean headless) {

		WebDriverManager.firefoxdriver().setup();

		FirefoxOptions options = new FirefoxOptions();

		if (headless) {

			options.addArguments("-headless");
		}

		return new FirefoxDriver(options);
	}

	/**
	 * Creates EdgeDriver.
	 */
	private static WebDriver createEdgeDriver(boolean headless) {

		WebDriverManager.edgedriver().setup();

		EdgeOptions options = new EdgeOptions();

		if (headless) {

			options.addArguments("--headless=new");
		}

		return new EdgeDriver(options);
	}

	// =========================================================
	// Common Driver Configuration
	// =========================================================

	/**
	 * Applies common WebDriver configuration.
	 */
	private static void configureDriver(WebDriver driver, boolean headless, int implicitWait, int pageLoadTimeout) {

		/*
		 * Maximize only when running with a visible browser. Headless browsers do not
		 * require maximize().
		 */
		if (!headless) {

			driver.manage().window().maximize();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
	}

	// =========================================================
	// Driver Cleanup
	// =========================================================

	/**
	 * Quits WebDriver for the current execution thread.
	 */
	public static void quitDriver() {

		if (!DriverManager.isDriverInitialized()) {

			log.info("No WebDriver found for current thread");

			return;
		}

		try {

			log.info("Closing browser");

			DriverManager.getDriver().quit();

			log.info("Browser closed successfully");

		} catch (Exception e) {

			log.error("Error while closing browser", e);

		} finally {

			DriverManager.unload();
		}
	}
}