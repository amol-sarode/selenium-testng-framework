package com.amol.automation.pages;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amol.automation.base.BasePage;
import com.amol.automation.factory.PageObjectManager;
import com.amol.automation.utils.LoggerUtils;



/**
 * Page Object class for SauceDemo Cart Page.
 */
public class CartPage extends BasePage {


    private static final Logger log =
            LoggerUtils.getLogger(CartPage.class);



    @FindBy(className = "title")
    private WebElement cartTitle;



    @FindBy(className = "inventory_item_name")
    private WebElement productName;



    @FindBy(id = "checkout")
    private WebElement checkoutButton;



    /**
     * Get cart title
     */
    public String getCartTitle() {

        log.info("Getting cart title");

        return getText(cartTitle);

    }



    /**
     * Get product name from cart
     */
    public String getProductName() {

        log.info("Getting product name");

        return getText(productName);

    }



    /**
     * Click checkout
     */
    public CheckoutPage clickCheckout() {

        log.info("Clicking checkout button");

        click(checkoutButton);

        return PageObjectManager.getCheckoutPage();

    }


}