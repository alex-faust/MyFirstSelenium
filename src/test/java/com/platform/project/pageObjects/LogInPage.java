package com.platform.project.pageObjects;

import com.platform.project.commons.ReadPropertyFile;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.spi.CalendarDataProvider;


public class LogInPage
{
    @FindBy(xpath = "//h1[contains(text(),'Welcome, Please Sign In')]")
    WebElement pageTitle;
    @FindBy(xpath = "//input[@name='email_address']")
    WebElement usernameBox;
    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordBox;
    @FindBy(xpath = "//span[contains(text(),'Sign In')]")
    WebElement signInButton;
    @FindBy(xpath = "//td[@class='messageStackError']")
    WebElement loginError;
    @FindBy(xpath = "//a[contains(text(),'Password forgotten? Click here.')]")
    WebElement passwordForgottenLink;
    @FindBy(xpath = "//input[@name='firstname']")
    WebElement firstName;
    @FindBy(xpath = "//input[@name='lastname']")
    WebElement lastName;
    @FindBy(xpath = "//input[@id='dob']")
    WebElement dateOfBirth;
    @FindBy(xpath = "//input[@name='email_address']")
    WebElement emailAddress;
    @FindBy(xpath = "//input[@name='street_address']")
    WebElement streetAddress;
    @FindBy(xpath = "//input[@name='postcode']")
    WebElement postCode;
    @FindBy(xpath = "//input[@name='city']")
    WebElement city;
    @FindBy(xpath = "//input[@name='state']")
    WebElement state;
    @FindBy(xpath = "//input[@name='telephone']")
    WebElement phoneNumber;
    @FindBy(xpath = "//input[@name='password']")
    WebElement yourPassword;
    @FindBy(xpath = "//input[@name='confirmation']")
    WebElement yourPasswordConfirm;
    @FindBy(xpath = "//body//option[224]")
    WebElement country;
    @FindBy(xpath = "//div[2]//table[1]//tbody[1]//tr[1]//td[2]//input[1]")
    WebElement gender;
    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement continueButton;

    private WebDriver driver;
    private Logger logger = Logger.getLogger(LogInPage.class);
    Long time;

    public LogInPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle()
    {
        logger.info("Getting title");
        //WebElement pageTitle = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In')]"));
        String title = pageTitle.getText();
        logger.info("Login page title is: " + title);
        return title;
    }

    public void enterUserDetails()
    {
        logger.info("Entering username and password");
        String username = "justagile@test.com";
        String password ="test123";
        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);
        signInButton.click();
    }

    public void enterUserDetailsFromConfig()
    {
        logger.info("Entering username and password");
        String username = ReadPropertyFile.getConfigPropertyVal("username");
        String password = ReadPropertyFile.getConfigPropertyVal("password");
        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);
        signInButton.click();
    }

    public void enterUserDetailsError()
    {
        logger.info("Entering username and password");
        String username = "";
        String password = "";
        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);
        signInButton.click();
    }

    public String getErrorMsg()
    {
        String message = loginError.getText();
        return message;
    }

    public void passwordForgotten()
    {
        passwordForgottenLink.click();
    }

    public void createAnAccount()
    {
        Date date = new Date();
        String strDate = "hhmmssa";
        DateFormat df = new SimpleDateFormat(strDate);
        String fd = df.format(date);
        String email = fd + ReadPropertyFile.getConfigPropertyVal("emailAddress").trim();
        logger.info("Creating an account");
        firstName.sendKeys(ReadPropertyFile.getConfigPropertyVal("firstname"));
        lastName.sendKeys(ReadPropertyFile.getConfigPropertyVal("lastname"));
        dateOfBirth.sendKeys(ReadPropertyFile.getConfigPropertyVal("dateOfBirth"));
        emailAddress.sendKeys(email);
        streetAddress.sendKeys(ReadPropertyFile.getConfigPropertyVal("streetAddress"));
        postCode.sendKeys(ReadPropertyFile.getConfigPropertyVal("postCode"));
        city.sendKeys(ReadPropertyFile.getConfigPropertyVal("city"));
        state.sendKeys(ReadPropertyFile.getConfigPropertyVal("state"));
        phoneNumber.sendKeys(ReadPropertyFile.getConfigPropertyVal("phoneNumber"));
        yourPassword.sendKeys(ReadPropertyFile.getConfigPropertyVal("yourPassword"));
        yourPasswordConfirm.sendKeys(ReadPropertyFile.getConfigPropertyVal("yourPasswordConfirm"));
        country.click();
        gender.click();
        continueButton.click();

    }
}
