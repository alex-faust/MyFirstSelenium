package com.platform.project.pageObjects;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class LogInPage
{
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/h1[1]")
    WebElement pageTitle;
    @FindBy(xpath = "//input[@name='email_address']")
    WebElement usernameBox;
    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordBox;
    @FindBy(xpath = "//span[contains(text(),'Sign In')]")
    WebElement signInButton;
    @FindBy(xpath = "//td[@class='messageStackError']")
    WebElement loginError;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/div[2]/div[1]/form[1]/p[1]/a[1]")
    WebElement passwordForgottenLink;

    private WebDriver driver;
    private Logger log = Logger.getLogger(LogInPage.class);

    public LogInPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle()
    {
        log.info("Getting title");
        return Commons.getElementText(driver, pageTitle, 3);
    }

    public void enterUserDetails()
    {
        //need to fix this
        log.info("Entering username and password");
        String username = "justagile@test.com";
        String password = "test123";
        log.info("Username is: " + username);
        log.info("Password is: " + password);
        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);
        signInButton.click();
    }

    public void enterUserDetailsFromConfig()
    {
        log.info("Entering username and password");
        String username = ReadPropertyFile.getConfigPropertyVal("username");
        String password = ReadPropertyFile.getConfigPropertyVal("password");
        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);
        signInButton.click();
    }

    public void enterUserDetailsFromExcel()
    {
        log.info("Entering details from excel.");
        String usernameExcel = "", passwordExcel = "";

        for (Row row : Commons.openExcel(0))
        {
            Iterator cellIterator = row.cellIterator();
            usernameExcel = String.valueOf(cellIterator.next());
            passwordExcel = String.valueOf(cellIterator.next());
        }

        log.info("username is: " + usernameExcel);
        log.info("password is: " + passwordExcel);
        usernameBox.sendKeys(usernameExcel);
        passwordBox.sendKeys(passwordExcel);
        signInButton.click();
    }

    public void enterUserDetailsError()
    {
        log.info("Entering username and password");
        String username = "";
        String password = "";
        log.info("Username is: " + username);
        log.info("Password is: " + password);
        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);
        signInButton.click();
    }

    public String getErrorMsg()
    {
        log.info("Getting log in error.");
        return Commons.getElementText(driver, loginError, 3);
    }

    public void passwordForgotten()
    {
        passwordForgottenLink.click();
    }

}
