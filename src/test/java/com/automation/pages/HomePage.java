package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement shoppingCartLink;

    @FindBy(xpath = "//button[contains(@id,'add-to-cart')]")
    List<WebElement> addToCartBtnList;

    @FindBy(id = "react-burger-menu-btn")
    WebElement hamBurgerMenu;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    WebElement selectSpan;

    @FindBy(xpath = "//div[@class='inventory_item_description']")
    List<WebElement> products;

    public boolean isHomePageDisplayed() {
        return shoppingCartLink.isDisplayed();
    }

    public void clickOnAddToCartOfFirstItem() {
        addToCartBtnList.get(0).click();
    }

    public void clickOnShoppingCartLink() {
        shoppingCartLink.click();
    }

    public String getCartIconQty() {
        return shoppingCartLink.findElement(By.xpath("./span")).getText();
    }

    public void clickOnHamburgerMenu() {
        hamBurgerMenu.click();
    }

    public void clickOnLogoutLink() {
        logoutLink.click();
    }

    public void selectOption(String text){
        Select select=new Select(selectSpan);
        select.selectByVisibleText(text);
    }

    public int getProductCount(){return products.size();}

    public List<String> getAllProductNames(){
        List<String> productNames=new ArrayList<>();
        for(WebElement product:products){
            String name=product.findElement(By.xpath(".//div[@class='inventory_item_name ']")).getText();
            productNames.add(name);
        }
        return productNames;
    }

    public List<Double> getAllProductPrice(){
        List<Double> productPrice=new ArrayList<>();
        for(WebElement product:products){
            String price=product.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText();
            price = price.replace("$", "").trim();
            productPrice.add(Double.parseDouble(price));
        }
        return productPrice;
    }
    public void addAllItemsToCart(){
        for(WebElement product:products){
            WebElement addToCart=product.findElement(By.xpath(".//button[@class='btn btn_primary btn_small btn_inventory ']"));
            addToCart.click();
        }
    }

    public boolean isRemoveBtnPresent(){
        for(WebElement product:products){
                WebElement btn=product.findElement(By.xpath(".//button"));
                if(btn.getText().equals("Remove")){
                    return true;
                }
        }
        return false;
    }

}
