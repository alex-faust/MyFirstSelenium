package com.platform.project.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgottenPasswordPage
{
    @FindBy(xpath = "//input[@name='email_address']")
    WebElement emailAddress;
    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement continueButton;
    @FindBy(xpath = "//td[@class='messageStackError']")
    WebElement errorMessage;

    private WebDriver driver;
    private Logger logger = Logger.getLogger(ForgottenPasswordPage.class);

    public ForgottenPasswordPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getNoRecordsFound()
    {
        emailAddress.click();
        continueButton.click();
        String title = errorMessage.getText();
        return title;
    }
}
