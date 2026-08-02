package com.amol.automation.pages;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amol.automation.base.BasePage;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.utils.LoggerUtils;



/**
 * Page Object class for SauceDemo Checkout Complete Page.
 */
public class CheckoutCompletePage extends BasePage {


    private static final Logger log =
            LoggerUtils.getLogger(CheckoutCompletePage.class);



    @FindBy(className = "title")
    private WebElement completeTitle;



    @FindBy(className = "complete-header")
    private WebElement thankYouMessage;



    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;



    /**
     * Get complete page title
     */
    public String getCompletePageTitle() {

        log.info("Getting complete page title");

        return getText(completeTitle);

    }



    /**
     * Verify order completion page
     */
    public boolean isOrderCompletePageDisplayed(){

        log.info("Checking order complete page");

        return completeTitle.isDisplayed();

    }



    /**
     * Get thank you message
     */
    public String getThankYouMessage() {

        log.info("Getting thank you message");

        return getText(thankYouMessage);

    }



    /**
     * Navigate back to products page
     */
    public ProductPage clickBackHome() {

        log.info("Clicking Back Home button");

        click(backHomeButton);

        return PageObjectManager.getProductPage();

    }


}