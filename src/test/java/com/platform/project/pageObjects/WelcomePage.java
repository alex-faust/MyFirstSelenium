package com.platform.project.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage
{
    @FindBy(xpath = "//h1[contains(text(),'Welcome to iBusiness')]")
    WebElement pageTitle;
    @FindBy(xpath = "//span[contains(text(),'Log Off')]")
    WebElement logOffButton;

    private WebDriver driver;
    private Logger logger = Logger.getLogger(WelcomePage.class);

    public WelcomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle()
    {
        logger.info("Getting title");
        String title = pageTitle.getText();
        logger.info("Welcome page title is: " + title);
        return title;
    }

}
