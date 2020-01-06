package com.platform.project.pageObjects;

import com.platform.project.commons.Commons;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage
{
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/h1[1]")
    WebElement pageTitle;
    @FindBy(xpath = "//span[contains(text(),'Log Off')]")
    WebElement logOffButton;

    private WebDriver driver;
    private Logger log = Logger.getLogger(WelcomePage.class);

    public WelcomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle()
    {
        log.info("Getting title");
        return Commons.getElementText(driver, pageTitle, 3);
    }

    public void logOff()
    {
        logOffButton.click();
    }
}
