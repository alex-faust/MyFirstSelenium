package com.platform.project.pageObjects;

import com.platform.project.commons.ReadPropertyFile;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class HomePage
{
    //List of elements i'm going to use for PageFactory
    @FindBy(xpath = "//*[@id=\"bodyContent\"]/h1")
    WebElement pageTitle;
    @FindBy(xpath = "//u[contains(text(),'log yourself in')]")
    WebElement logYourselfIn;
    @FindBy(xpath = "//u[contains(text(),'create an account')]")
    WebElement createAccount;

    private WebDriver driver;
    private Logger logger = Logger.getLogger(HomePage.class);
    int statusCode;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openHomePage()
    {
        logger.info("Opening Homepage");
        driver.get(ReadPropertyFile.getConfigPropertyVal("homePageUrl"));
    }

    public String getPageTitle()
    {
        logger.info("Getting title");
        //WebElement pageTitle = driver.findElement(By.xpath("//*[@id=\"bodyContent\"]/h1"));
        String title = pageTitle.getText();
        logger.info("Home page title is: " + title);
        return title;
    }

    public void clickLogInText()
    {
        logger.info("Logging in");
        //driver.findElement(By.xpath("//u[contains(text(),'log yourself in')]")).click();
        logYourselfIn.click();
    }

    public void clickCreateAccount()
    {
        createAccount.click();
    }
    public void checkAllLinks()
    {
        HttpURLConnection huc = null;
        String homePage = ReadPropertyFile.getConfigPropertyVal("homePageUrl");
        int respCode = 200;
        String url = "";

        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        Iterator<WebElement> it = allLinks.iterator();

        logger.info("All the web links are: ");

        while (it.hasNext())
        {
            url = it.next().getAttribute("href");
            System.out.println(url);
            if (url == null || url.isEmpty())
            {
                System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }

            if (!url.startsWith(homePage))
            {
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }

            try
            {
                huc = (HttpURLConnection) (new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();

                if (respCode >= 400)
                {
                    System.out.println(url + " is a broken link.");
                } else
                {
                    System.out.println(url + " is a valid link.");
                }
            } catch (MalformedURLException mue)
            {
                mue.printStackTrace();
            } catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }

    }




}
