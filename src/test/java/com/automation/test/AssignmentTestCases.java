package com.automation.test;

import com.automation.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class AssignmentTestCases extends BaseTest {
    @Test
    public void AlphabeticalSortTest() throws InterruptedException {
        loginPage.openWebsite();
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"),ConfigReader.getConfigValue("login.password"));
        Assert.assertTrue(homePage.isHomePageDisplayed());

        List<String> sortedNames=homePage.getAllProductNames();
        sortedNames.sort(Comparator.reverseOrder());
        homePage.selectOption("Name (Z to A)");
        List<String> products=homePage.getAllProductNames();
        Assert.assertTrue(sortedNames.equals(products));
    }

    @Test
    public void  SortByPriceTest(){
        loginPage.openWebsite();
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"),ConfigReader.getConfigValue("login.password"));
        Assert.assertTrue(homePage.isHomePageDisplayed());

        List<Double> sortedPrices=homePage.getAllProductPrice();
        sortedPrices.sort(Comparator.reverseOrder());
        homePage.selectOption("Price (high to low)");
        List<Double> prices=homePage.getAllProductPrice();
        Assert.assertTrue(sortedPrices.equals(prices));


    }
    @Test
    public void CartValidation() throws InterruptedException {
        loginPage.openWebsite();
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"),ConfigReader.getConfigValue("login.password"));
        Assert.assertTrue(homePage.isHomePageDisplayed());

        homePage.addAllItemsToCart();
        Integer cartQuantity=Integer.parseInt(homePage.getCartIconQty());
        Assert.assertTrue(cartQuantity== homePage.getProductCount(),"Wrong Number on cart span");
        int productCount=homePage.getProductCount();
        homePage.clickOnShoppingCartLink();
        Assert.assertTrue(cartPage.isCartPageDisplayed());
        Assert.assertTrue(cartPage.cartItemCount()==productCount);
    }
    @Test
    public  void TotalPriceValidationTest() throws InterruptedException {
        loginPage.openWebsite();
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"),ConfigReader.getConfigValue("login.password"));
        Assert.assertTrue(homePage.isHomePageDisplayed());

        homePage.addAllItemsToCart();
        Integer cartQuantity=Integer.parseInt(homePage.getCartIconQty());
        Assert.assertTrue(cartQuantity== homePage.getProductCount(),"Wrong Number on cart span");
        int productCount=homePage.getProductCount();
        homePage.clickOnShoppingCartLink();
        Assert.assertTrue(cartPage.cartItemCount()==productCount);
        List <String> cartItemNames=cartPage.cartItemNames();
        Double totalPriceOnCart= cartPage.totalPriceOfCartItems(cartPage.cartItemsPrice());
        Collections.sort(cartItemNames);
        cartPage.clickOnCheckoutBtn();
        checkoutPage.fillShippingInfo();
        checkoutPage.clickOnContinueBtn();
        Double totalPriceOnCheckedoutPage=reviewPage.totalPriceOfCheckedoutItems(reviewPage.checkedoutItemsPrice());
        Assert.assertTrue(Objects.equals(totalPriceOnCart, totalPriceOnCheckedoutPage),"sun price of items on cart and checkout do not match");
        List<String> checkedoutItemNames=reviewPage.checkedoutItemNames();
        Collections.sort(checkedoutItemNames);
        Assert.assertTrue(cartItemNames.equals(checkedoutItemNames),"The items in cart and checkout do not match");
        Double totalPrice=totalPriceOnCheckedoutPage+reviewPage.getTax();
        Assert.assertTrue(totalPrice.equals(reviewPage.getTotalPrice()),"Total Price does not match!");
    }

    @Test
    public void checkRemoveBtn() throws InterruptedException {
        loginPage.openWebsite();
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"),ConfigReader.getConfigValue("login.password"));
        Assert.assertTrue(homePage.isHomePageDisplayed());

        homePage.addAllItemsToCart();
        Integer cartQuantity=Integer.parseInt(homePage.getCartIconQty());
        Assert.assertTrue(cartQuantity== homePage.getProductCount(),"Wrong Number on cart span");
        int productCount=homePage.getProductCount();
        homePage.clickOnShoppingCartLink();
        Assert.assertTrue(cartPage.cartItemCount()==productCount);
        List <String> cartItemNames=cartPage.cartItemNames();
        Double totalPriceOnCart= cartPage.totalPriceOfCartItems(cartPage.cartItemsPrice());
        Collections.sort(cartItemNames);
        cartPage.clickOnCheckoutBtn();
        checkoutPage.fillShippingInfo();
        checkoutPage.clickOnContinueBtn();
        Double totalPriceOnCheckedoutPage=reviewPage.totalPriceOfCheckedoutItems(reviewPage.checkedoutItemsPrice());
        Assert.assertTrue(Objects.equals(totalPriceOnCart, totalPriceOnCheckedoutPage),"sun price of items on cart and checkout do not match");
        List<String> checkedoutItemNames=reviewPage.checkedoutItemNames();
        Collections.sort(checkedoutItemNames);
        Assert.assertTrue(cartItemNames.equals(checkedoutItemNames),"The items in cart and checkout do not match");
        Double totalPrice=totalPriceOnCheckedoutPage+reviewPage.getTax();
        Assert.assertTrue(totalPrice.equals(reviewPage.getTotalPrice()),"Total Price does not match!");
        reviewPage.clickOnFinishBtn();
        orderConfirmationPage.returnHome();
        Assert.assertTrue(!homePage.isRemoveBtnPresent(),"Remove button was found after order confirmation");

    }

//@DataProvider(name = "sortDataProvider")
//public Object[][] sortDataProvider() {
//    return new Object[][]{
//            { "Name", "Name (Z to A)" },
//            { "Name", "Name (A to Z)" },
//            { "Price", "Price (high to low)" },
//            { "Price", "Price (low to high)" }
//    };
//}
//
//    @Test(dataProvider = "sortDataProvider")
//    public void sortingTest(String sortingType, String sortOption) throws InterruptedException {
//        loginPage.openWebsite();
//        loginPage.doLogin(ConfigReader.getConfigValue("login.username"), ConfigReader.getConfigValue("login.password"));
//        Assert.assertTrue(homePage.isHomePageDisplayed());
//
//        if ("Name".equalsIgnoreCase(sortingType)) {
//            List<String> sortedNames = homePage.getAllProductNames();
//            if ("Name (Z to A)".equals(sortOption)) {
//                sortedNames.sort(Comparator.reverseOrder());
//            } else {
//                sortedNames.sort(Comparator.naturalOrder());
//            }
//            homePage.selectOption(sortOption);
//            List<String> products = homePage.getAllProductNames();
//            Assert.assertTrue(sortedNames.equals(products), "Names did not sort correctly");
//        }
//
//        if ("Price".equalsIgnoreCase(sortingType)) {
//            List<Double> sortedPrices = homePage.getAllProductPrice();
//            if ("Price (high to low)".equals(sortOption)) {
//                sortedPrices.sort(Comparator.reverseOrder());
//            } else {
//                sortedPrices.sort(Comparator.naturalOrder());
//            }
//            homePage.selectOption(sortOption);
//            List<Double> prices = homePage.getAllProductPrice();
//            Assert.assertTrue(sortedPrices.equals(prices), "Prices did not sort correctly");
//        }
//    }
}
