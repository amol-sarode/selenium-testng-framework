package com.amol.automation.pages;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amol.automation.base.BasePage;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.utils.LoggerUtils;


/**
 * Page Object class for SauceDemo Checkout Overview Page.
 */
public class CheckoutOverviewPage extends BasePage {


    private static final Logger log =
            LoggerUtils.getLogger(CheckoutOverviewPage.class);



    @FindBy(className = "title")
    private WebElement overviewTitle;



    @FindBy(id = "finish")
    private WebElement finishButton;



    /**
     * Get overview page title
     */
    public String getOverviewTitle() {

        log.info("Getting checkout overview title");

        return getText(overviewTitle);

    }



    /**
     * Verify overview page
     */
    public boolean isOverviewPageDisplayed() {

        log.info("Checking checkout overview page");

        return overviewTitle.isDisplayed();

    }



    /**
     * Finish order and navigate to complete page
     */
    public CheckoutCompletePage clickFinish() {


        log.info("Clicking Finish button");


        click(finishButton);


        return PageObjectManager.getCheckoutCompletePage();

    }


}