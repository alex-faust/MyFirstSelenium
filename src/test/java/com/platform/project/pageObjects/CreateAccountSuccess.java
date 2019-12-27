package com.platform.project.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountSuccess
{
    @FindBy(xpath = "//h1[contains(text(),'Your Account Has Been Created!')]")
    WebElement pageTitle;

    private WebDriver driver;
    private Logger log = Logger.getLogger(CreateAccountSuccess.class);

    public CreateAccountSuccess(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle()
    {
        log.info("Getting title");
        //WebElement pageTitle = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In')]"));
        String title = pageTitle.getText();
        log.info("Login page title is: " + title);
        return title;
    }
}
