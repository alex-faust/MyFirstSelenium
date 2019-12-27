package com.platform.project.test;

import com.platform.project.commons.*;
import com.platform.project.pageObjects.HomePage;
import com.platform.project.pageObjects.LogInPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogInPageTest
{
    WebDriver driver;
    LogInPage logInPage;
    HomePage homePage;
    WebDriverManager webDriverManager;
    Commons c;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver("chrome");
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        c = new Commons();
    }

    @Test
    public void openLoginPage()
    {
        homePage.clickLogInText();
        c.assertResult(driver, logInPage.getPageTitle(), "Welcome, Please Sign In");
    }

    /*@Test
    public void enterUserDetails()
    {
        openLoginPage();
        logInPage.enterUserDetails();
        c.assertResult();
    }*/

    /*public void enterUserDetailsfromConfig()
    {
        openLoginPage();
        logInPage.enterUserDetailsFromConfig();
    }*/



    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }











}
