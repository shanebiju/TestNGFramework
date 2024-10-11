package com.automation.test;

import com.automation.utils.ConfigReader;
import com.automation.utils.DriverManager;
import com.automation.utils.ExcelUtils;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class LoginLogoutTest extends BaseTest {

    @Test(dataProvider = "getValidCredentials")
    public void verifyUserCanLoginWithValidCred(String username, String password) {
        loginPage.openWebsite();
        loginPage.doLogin(username, password);
        Allure.addAttachment("screenshot", DriverManager.takeScreenshotAsInputStream());
        Assert.assertTrue(homePage.isHomePageDisplayed());
    }

    @DataProvider
    public Object[][] getValidCredentials() throws IOException {
        ExcelUtils excelUtils = new ExcelUtils("score.xlsx", "Sheet1");
        List<List<String>> tableData = excelUtils.getData();
        Object[][] data = new Object[tableData.size()][tableData.get(0).size()];

        for (int i = 0; i < tableData.size(); i++) {
            List<String> row = tableData.get(i);
            for (int j = 0; j < row.size(); j++) {
                data[i][j] = tableData.get(i).get(j);
            }
        }

        return data;
    }

    @Test
    public void verifyApplicationSavesStateForUser() {
        loginPage.openWebsite();
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"), ConfigReader.getConfigValue("login.password"));
        Assert.assertTrue(homePage.isHomePageDisplayed());
        homePage.clickOnAddToCartOfFirstItem();
        Assert.assertEquals(homePage.getCartIconQty(), "1");
        homePage.clickOnHamburgerMenu();
        homePage.clickOnLogoutLink();
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
        loginPage.doLogin(ConfigReader.getConfigValue("login.username"), ConfigReader.getConfigValue("login.password"));
        Assert.assertTrue(homePage.isHomePageDisplayed());
        Assert.assertEquals(homePage.getCartIconQty(), "1");
    }

}
