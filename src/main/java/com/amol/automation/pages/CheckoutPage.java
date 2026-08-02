package com.amol.automation.pages;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amol.automation.base.BasePage;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.utils.LoggerUtils;



public class CheckoutPage extends BasePage {


    private static final Logger log =
            LoggerUtils.getLogger(CheckoutPage.class);



    @FindBy(className = "title")
    private WebElement checkoutTitle;



    @FindBy(id = "first-name")
    private WebElement firstName;



    @FindBy(id = "last-name")
    private WebElement lastName;



    @FindBy(id = "postal-code")
    private WebElement postalCode;



    @FindBy(id = "continue")
    private WebElement continueButton;



    public String getCheckoutTitle() {

        log.info("Getting checkout title");

        return getText(checkoutTitle);

    }



    public CheckoutOverviewPage checkout(
            String fname,
            String lname,
            String zip) {


        log.info("Entering checkout information");


        type(firstName, fname);

        type(lastName, lname);

        type(postalCode, zip);


        click(continueButton);


        return PageObjectManager.getCheckoutOverviewPage();

    }

}