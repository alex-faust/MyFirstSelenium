package com.platform.project.commons;

import com.platform.project.pageObjects.HomePage;
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


    public static void takeSnapShot(WebDriver webdriver, String name)
    {
        try
        {
            //convert web driver object to TakeScreenShot
            TakesScreenshot screenshot = (TakesScreenshot) webdriver;
            //Call getScreenshotAs method to create image file
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            //move image file to new destination
            File destinationFile = new File(fileFolder+ "/" + name + ".png");
            //copy file at destination
            FileUtils.copyFile(srcFile, destinationFile);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void assertResult(WebDriver driver, String result, String expectedResult)
    {
        if(result.equals(expectedResult))
        {
            Assert.assertEquals(result, expectedResult);
        } else {
            Assert.assertEquals(result, expectedResult);
            takeSnapShot(driver, expectedResult);
        }
    }
}
