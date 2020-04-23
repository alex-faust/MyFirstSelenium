package com.platform.project.commons;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtil
{
    private static Logger log = Logger.getLogger(Commons.class);

    public static void flash(WebElement element, WebDriver driver)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String bgColor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 500; i++)
        {
            changeColor("#000000", element, driver); //1
            changeColor(bgColor, element, driver); //2
        }
    }

    public static void changeColor(String color, WebElement element, WebDriver driver)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

        try
        {
            Thread.sleep(20);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void drawBorder(WebElement element, WebDriver driver)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public static void generateAlert(WebDriver driver, String message)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        log.info("generating alert");
        js.executeScript("alert('" + message + "')");
    }

    public static void refreshBrowserByJS(WebDriver driver)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("history.go(0)");
    }

    public static void scrollIntoView(WebElement element, WebDriver driver)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
