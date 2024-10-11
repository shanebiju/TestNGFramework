package com.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class DriverManager {

    static WebDriver driver;

    public static void createDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static  ByteArrayInputStream takeScreenshotAsInputStream(){
        TakesScreenshot ts=(TakesScreenshot) DriverManager.getDriver();
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        return new ByteArrayInputStream(screenshot);
    }

    public static String takeScreenshotAsFile(){
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        String screenshotFolderPath = "report/";
        String screenshotName = "screenshot.png";
        try {
            FileUtils.copyFile(screenshot, new File(screenshotFolderPath + screenshotName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenshotName;
    }
    public static String takeScreenshotAsBase64() {
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        String screenshot = ts.getScreenshotAs(OutputType.BASE64);
        return screenshot;
    }

}
