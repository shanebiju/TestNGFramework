package com.automation.pages;

import com.automation.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//span[@class='title'][contains(text(),'Checkout')]")
    WebElement checkoutPageTitle;

    @FindBy(id = "continue")
    WebElement continueBtn;

    @FindBy(id = "postal-code")
    WebElement zipCodeInput;

    @FindBy(id = "last-name")
    WebElement lastNameInput;

    @FindBy(id = "first-name")
    WebElement firstNameInput;

    public boolean isCheckoutPageDisplayed() {
        return checkoutPageTitle.isDisplayed();
    }

    public void fillShippingInfo() {
        firstNameInput.sendKeys(ConfigReader.getConfigValue("shipping.firstname"));
        lastNameInput.sendKeys(ConfigReader.getConfigValue("shipping.lastname"));
        zipCodeInput.sendKeys(ConfigReader.getConfigValue("shipping.zipcode"));
    }

    public void clickOnContinueBtn() {
        continueBtn.click();
    }
}
