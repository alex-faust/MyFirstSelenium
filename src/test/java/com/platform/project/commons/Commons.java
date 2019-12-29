package com.platform.project.commons;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class Commons
{
    //private static WebDriver driver;
    private static String fileFolder = "./screenshots/";
    private Logger logger = Logger.getLogger(Commons.class);


    public static void takeSnapShot(WebDriver webdriver, Object name)
    {
        try
        {
            //convert web driver object to TakeScreenShot
            TakesScreenshot screenshot = (TakesScreenshot) webdriver;
            //Call getScreenshotAs method to create image file
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            //move image file to new destination
            File destinationFile = new File(fileFolder+ "/" + String.valueOf(name) + ".png");
            //copy file at destination
            FileUtils.copyFile(srcFile, destinationFile);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void assertResult(WebDriver driver, Object actualResult, Object expectedResult)
    {
        if(actualResult == expectedResult)
        {
            Assert.assertEquals(actualResult, expectedResult);
        } else {
            takeSnapShot(driver, expectedResult);
            Assert.assertEquals(actualResult, expectedResult);
        }
    }
}
