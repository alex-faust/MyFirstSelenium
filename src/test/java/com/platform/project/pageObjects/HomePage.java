package com.platform.project.pageObjects;

import com.platform.project.commons.ReadPropertyFile;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        openHomePage();
        logger.info("Logging in");
        //driver.findElement(By.xpath("//u[contains(text(),'log yourself in')]")).click();
        logYourselfIn.click();
    }

    public void clickCreateAccount()
    {
        //openHomePage();
        createAccount.click();
    }
    public void getAllLinks()
    {
        openHomePage();
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        logger.info("All the web links are: ");

        /*for(WebElement we: allLinks)
        {
            //openHomePage();

            logger.info(we.getText() + " - " + we.getAttribute("href"));
            logger.info(allLinks.size());
        }*/
    }




}
