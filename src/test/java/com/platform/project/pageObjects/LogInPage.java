package com.platform.project.pageObjects;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

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
    WebElement genderMale;
    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement continueButton;

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
        //WebElement pageTitle = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In')]"));
        String title = pageTitle.getText();
        log.info("Login page title is: " + title);
        return title;
    }

    public void enterUserDetails()
    {
        log.info("Entering username and password");
        String username = "justagile@test.com";
        String password ="test123";
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
        try
        {
            FileInputStream file = new FileInputStream(
                    new File("C:\\Users\\abcle\\IdeaProjects\\MyFirstSelenium\\files\\credentials.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row: sheet)
            {
                Iterator cellIterator = row.cellIterator();
                usernameExcel = String.valueOf(cellIterator.next());
                passwordExcel = String.valueOf(cellIterator.next());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
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
        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);
        signInButton.click();
    }

    public String getErrorMsg()
    {
        return loginError.getText();
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
