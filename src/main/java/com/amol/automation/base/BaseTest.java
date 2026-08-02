package com.amol.automation.base;

import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.amol.automation.driver.DriverFactory;
import com.amol.automation.driver.DriverManager;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.utils.ConfigReader;
import com.amol.automation.utils.LoggerUtils;

public class BaseTest {

	private static final Logger log = LoggerUtils.getLogger(BaseTest.class);

	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method) {

		log.info("========== Starting Test : {} ==========", method.getName());

		DriverFactory.initializeDriver();

		ConfigReader config = ConfigReader.getInstance();

		String url = config.getProperty("app.url");

		if (url == null || url.isEmpty()) {

			throw new RuntimeException("Application URL is missing");

		}

		log.info("Opening application URL : {}", url);

		DriverManager.getDriver().get(url);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		log.info("Cleaning test execution");

		PageObjectManager.unload();

		DriverFactory.quitDriver();

		log.info("========== Test Execution Completed ==========");

	}

}