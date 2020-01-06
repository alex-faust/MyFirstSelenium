package com.platform.project.test;


import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.HomePage;
import com.platform.project.pageObjects.LogInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogInPageTest
{
    WebDriver driver;
    LogInPage logInPage;
    WebDriverManager webDriverManager;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver
                (Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
        logInPage = new LogInPage(driver);
    }

    @Test
    public void openLoginPage()
    {
        Commons.check(driver, logInPage.getPageTitle().equals("Welcome, Please Sign In"), "openLoginPageFail");
    }

    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }











}
