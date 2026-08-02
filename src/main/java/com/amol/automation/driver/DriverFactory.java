package com.amol.automation.driver;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amol.automation.enums.BrowserType;
import com.amol.automation.utils.ConfigReader;
import com.amol.automation.utils.LoggerUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverFactory {

	private DriverFactory() {

	}

	private static final Logger log = LoggerUtils.getLogger(DriverFactory.class);

	public static void initializeDriver() {

		ConfigReader config = ConfigReader.getInstance();

		BrowserType browser = BrowserType.valueOf(config.getProperty("browser").toUpperCase());

		boolean headless = Boolean.parseBoolean(config.getProperty("headless"));

		WebDriver driver;

		log.info("Initializing browser : {}", browser);

		switch (browser) {

		case CHROME:

			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();

			if (headless) {

				options.addArguments("--headless");

			}

			driver = new ChromeDriver(options);

			break;

		case FIREFOX:

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();

			break;

		case EDGE:

			WebDriverManager.edgedriver().setup();

			driver = new EdgeDriver();

			break;

		default:

			throw new IllegalArgumentException("Unsupported browser : " + browser);

		}

		driver.manage().window().maximize();

		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));

		driver.manage().timeouts()
				.pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(config.getProperty("page.load.timeout"))));

		DriverManager.setDriver(driver);

		log.info("{} browser launched successfully", browser);

	}

	public static void quitDriver() {

		WebDriver driver = DriverManager.getDriver();

		if (driver != null) {

			log.info("Closing browser");

			driver.quit();

			DriverManager.unload();

			log.info("Browser closed successfully");

		}

	}

}