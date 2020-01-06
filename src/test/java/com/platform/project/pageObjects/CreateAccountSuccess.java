package com.platform.project.pageObjects;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAccountSuccess
{
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/h1[1]")
    WebElement pageTitle;
    @FindBy(xpath = "//div[2]//table[1]//tbody[1]//tr[1]//td[2]//input[1]")
    WebElement genderMale;
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
    @FindBy(xpath = "//body//option[224]")
    WebElement country;
    @FindBy(xpath = "//input[@name='telephone']")
    WebElement phoneNumber;
    @FindBy(xpath = "//input[@name='password']")
    WebElement yourPassword;
    @FindBy(xpath = "//input[@name='confirmation']")
    WebElement yourPasswordConfirm;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[7]/span[1]/span[1]/button[1]/span[2]")
    WebElement continueButton;

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
        return Commons.getElementText(driver, pageTitle, 3);
    }

    public void createAnAccount()
    {
        Date date = new Date();
        String strDate = "hhmmssa";
        DateFormat df = new SimpleDateFormat(strDate);
        String fd = df.format(date);
        String email = fd + ReadPropertyFile.getConfigPropertyVal("emailAddress").trim();
        log.info("Creating an account");
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
        genderMale.click();
        continueButton.click();
    }
}
