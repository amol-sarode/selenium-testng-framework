package com.amol.automation.pages;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amol.automation.base.BasePage;
import com.amol.automation.utils.LoggerUtils;


/**
 * Page Object class for SauceDemo Products Page.
 */
public class HomePage extends BasePage {


    private static final Logger log =
            LoggerUtils.getLogger(HomePage.class);



    @FindBy(className = "title")
    private WebElement pageTitle;



    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;



    /**
     * Verify Products page loaded
     */
    public boolean isHomePageDisplayed() {


        log.info("Checking products page visibility");


        return isDisplayed(pageTitle);

    }



    /**
     * Get page title
     */
    public String getHomePageTitle() {


        log.info("Getting page title");


        return getText(pageTitle);

    }



    /**
     * Verify menu button displayed
     */
    public boolean isMenuDisplayed() {


        log.info("Checking menu visibility");


        return isDisplayed(menuButton);

    }

}