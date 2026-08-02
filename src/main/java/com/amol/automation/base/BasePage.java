package com.amol.automation.base;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amol.automation.driver.DriverManager;
import com.amol.automation.utils.LoggerUtils;

public class BasePage {

	private final WebDriverWait wait;

	private static final Logger log = LoggerUtils.getLogger(BasePage.class);

	public BasePage() {

		PageFactory.initElements(DriverManager.getDriver(), this);
		wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));

	}

	protected void click(WebElement element) {

		try {

			log.info("Clicking element : {}", element);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			log.info("Clicked on "+element);

		} catch (Exception e) {

			log.error("Unable to click element", e);

			throw e;

		}

	}

	protected void enterText(WebElement element, String text) {

		try {

			log.info("Entering text : {}", text);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(text);

		} catch (Exception e) {

			log.error("Unable to enter text", e);
			throw e;
		}

	}

	protected String getText(WebElement element) {

		try {

			wait.until(ExpectedConditions.visibilityOf(element));

			String value = element.getText();

			log.info("Text fetched : {}", value);

			return value;

		} catch (Exception e) {

			log.error("Unable to get text from element", e);

			throw e;

		}

	}

	protected boolean isDisplayed(WebElement element) {

		try {

			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();

		} catch (Exception e) {

			log.warn("Element is not displayed");
			return false;

		}

	}

	protected void jsClick(WebElement element) {

		log.info("Javascript click");
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].click();", element);

	}

	protected void scrollIntoView(WebElement element) {

		log.info("Scrolling element into view");
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", element);

	}

	protected String getAttribute(WebElement element, String attribute) {

		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getAttribute(attribute);

	}

	protected void waitForPageLoad() {

		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20))
				.until(driver -> js.executeScript("return document.readyState").equals("complete"));

	}

}