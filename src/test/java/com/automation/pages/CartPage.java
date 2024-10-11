package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    WebElement checkOutBtn;

    @FindBy(xpath = "//div[@class='cart_item']")
    List<WebElement> cartItems;

    public boolean isCartPageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(checkOutBtn));
        return checkOutBtn.isDisplayed();
    }

    public void clickOnCheckoutBtn() {
        checkOutBtn.click();
    }

    public int cartItemCount(){ return cartItems.size();}

    public List<String> cartItemNames(){
        List<String> names=new ArrayList<>();
        for(WebElement cartItem:cartItems){
            String name=cartItem.findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
            names.add(name);
        }
        return  names;
    }

    public List<Double> cartItemsPrice(){
        List<Double> cartItemsPrice=new ArrayList<>();
        for(WebElement cartItem:cartItems){
            String price=cartItem.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText();
            price = price.replace("$", "").trim();
            cartItemsPrice.add(Double.parseDouble(price));
        }
        return cartItemsPrice;
    }

    public Double totalPriceOfCartItems(List<Double> prices){
        Double sum= 0.0;
        for(Double price:prices){
            sum+=price;
        }
        return sum;
    }
}
