package com.platform.project.test;

import com.platform.project.commons.*;
import com.platform.project.pageObjects.*;
import com.platform.project.pageObjects.LogInPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.platform.project.commons.Commons.assertResult;

public class LogInPageTest
{
    WebDriver driver;
    LogInPage logInPage;
    HomePage homePage;
    WebDriverManager webDriverManager;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver("chrome");
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
    }

    @Test
    public void openLoginPage()
    {
        homePage.openHomePage();
        homePage.clickLogInText();
        assertResult(driver, logInPage.getPageTitle(), "Welcome, Please Sign In");
    }

    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }











}
