package com.platform.project.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage
{
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/h1[1]")
    WebElement pageTitle;

    private WebDriver driver;
    private Logger log = Logger.getLogger(ShoppingCartPage.class);

    public ShoppingCartPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle()
    {
        String title = pageTitle.getText();
        log.info("Cart age title is: " + title);
        return title;
    }
}
