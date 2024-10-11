package com.automation.test;

import com.automation.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;


public class OrderConfirmationTest extends BaseTest {

    @Test
    public void verifyUserCanPlaceOrder() {
        loginPage.openWebsite();
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"), ConfigReader.getConfigValue("login.password"));

        Assert.assertTrue(homePage.isHomePageDisplayed());
        homePage.clickOnAddToCartOfFirstItem();
        homePage.clickOnShoppingCartLink();

        Assert.assertTrue(cartPage.isCartPageDisplayed());
        cartPage.clickOnCheckoutBtn();

        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed());
        checkoutPage.fillShippingInfo();
        checkoutPage.clickOnContinueBtn();

        Assert.assertTrue(reviewPage.isReviewPageDisplayed());
        reviewPage.clickOnFinishBtn();

        Assert.assertTrue(orderConfirmationPage.isOrderConfirmationMsgDisplayed());
    }

}
