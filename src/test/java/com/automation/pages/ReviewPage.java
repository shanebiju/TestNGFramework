package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ReviewPage extends BasePage {

    @FindBy(id = "finish")
    WebElement finishBtn;

    @FindBy(xpath = "//div[@class='cart_item']")
    List<WebElement> checkedoutItems;

    @FindBy(xpath = "//div[@class='summary_tax_label']")
    WebElement taxElement;

    @FindBy(xpath="//div[@class='summary_total_label']")
    WebElement totalElement;

    public boolean isReviewPageDisplayed() {
        return finishBtn.isDisplayed();
    }

    public void clickOnFinishBtn() {
        finishBtn.click();
    }

    public int checkedoutItemsCount(){return checkedoutItems.size();}


    public List<String> checkedoutItemNames(){
        List<String> names=new ArrayList<>();
        for(WebElement checkedoutItem:checkedoutItems){
            String name=checkedoutItem.findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
            names.add(name);
        }
        return  names;
    }

    public List<Double> checkedoutItemsPrice(){
        List<Double> checkedoutItemsPrice=new ArrayList<>();
        for(WebElement checkedoutItem:checkedoutItems){
            String price=checkedoutItem.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText();
            price = price.replace("$", "").trim();
            checkedoutItemsPrice.add(Double.parseDouble(price));
        }
        return checkedoutItemsPrice;
    }
    public Double totalPriceOfCheckedoutItems(List<Double> prices){
        Double sum= 0.0;
        for(Double price:prices){
            sum+=price;
        }
        return sum;
    }

    public  Double getTax(){
        String tax=taxElement.getText();
        tax = tax.replace("Tax: $", "").trim();
        return Double.parseDouble(tax);
    }

    public Double getTotalPrice(){
        String total=totalElement.getText();
        total = total.replace("Total: $", "").trim();
        return Double.parseDouble(total);
    }
}
