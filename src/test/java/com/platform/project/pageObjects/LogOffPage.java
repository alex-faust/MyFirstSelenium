package com.platform.project.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOffPage
{
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/h1[1]")
    WebElement pageTitle;

    private WebDriver driver;
    private Logger log = Logger.getLogger(WelcomePage.class);

    public LogOffPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getPageTitle()
    {

        String text = pageTitle.getText();
        log.info("Page title is: " + text);
        return text;
    }
}
