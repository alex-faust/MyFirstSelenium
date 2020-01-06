package com.platform.project.commons;

import com.google.common.base.Function;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


import static java.lang.Thread.currentThread;

public class Commons
{
    private static Logger log = Logger.getLogger(Commons.class);

    private static void takeSnapShot(WebDriver webDriver, String javaClass, String methodName)
    {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss_").format(new Date());
        String fileName = timeStamp + javaClass + "_" + methodName + ".png";
        log.info(fileName);
        File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

        try
        {
            FileUtils.copyFile(srcFile, new File("./screenshots/" + fileName));
            log.info("Screenshot " + fileName + " taken.");
        } catch (IOException e)
        {
            e.printStackTrace();
            log.info("Unable to take screenshot" + fileName);
        }
    }

    public static void check(WebDriver driver, boolean condition, String failMessage)
    {
        if (condition)
        {
            log.info("Check condition is true.");
            Assert.assertTrue(true);
        } else
        {
            log.info(failMessage);
            takeSnapShot(driver, currentThread().getStackTrace()[2].getClassName(), currentThread().getStackTrace()[2].getMethodName());
            Assert.fail();
            //Assert.assertTrue(false);
        }
    }

    public static String createEnvVariable(String envVariableName, String defaultValue)
    {
        String variableValue = System.getProperty(envVariableName);
        log.info("Environment value for " + envVariableName + " is equal to " + variableValue);
        return variableValue != null ? variableValue : defaultValue;
    }

    //Explicit wait
    //not a good idea to use void, change to boolean. better to know if you're waiting, if you found the item
    //seconds is the max it will wait, it can find it sooner and if it does, it will continue
    public static boolean waitForElement(WebDriver driver, WebElement element, int seconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        try
        {
            wait.until(ExpectedConditions.visibilityOf(element));
            log.info(element.getText() + " is visible.");
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            log.info("element is not visible."); //can't do getText() because no element was found
            return false;
        }
    }

    //Explicit Wait = Fluent wait
    public static boolean isElementVisible(WebDriver driver, WebElement el, int seconds)
    {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(seconds, TimeUnit.SECONDS) //you can change time unit
                .pollingEvery(10, TimeUnit.MILLISECONDS); //waiting every 10 milliseconds

        //gonna wait 2 seconds
        Function<WebDriver, Boolean> isElementFound = arg0 -> {
            try
            {
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
                el.getSize();
              return true;
            } catch (NoSuchElementException nsee) {
                return false;
            }
        };

        try
        {
            wait.until(isElementFound); //wait 1 millisecond, if it's not there, wait 10 milliseconds
            log.info(el.getText() + " is visible.");
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            log.info("element is not visible."); //can't do getText() because no element was found
            return false;
        }
    }

    /**
     * these next two methods retrieves the text of a web element
     * as well as using waits to monitor element load times
     */
    public static String getElementText(WebDriver driver, WebElement el)
    {
        return getElementText(driver, el, 3);
    }

    public static String getElementText(WebDriver driver, WebElement el, int seconds)
    {
        if (isElementVisible(driver, el, seconds))
        {
            String elementText = el.getText();
            log.info("ELement text is: " + elementText);
            return elementText;
        } else {
            return "";
        }
    }


    public static XSSFSheet openExcel(String fileName, int sheetNum)
    {
        log.info("Opening excel sheet.");

        XSSFSheet sheet = null;
        try
        {
            FileInputStream file = new FileInputStream(
                    new File("C:\\Users\\abcle\\IdeaProjects\\MyFirstSelenium\\files\\"+ fileName + ".xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheetAt(sheetNum);

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return sheet;
    }
}
