package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.HomePage;
import com.platform.project.pageObjects.LogInPage;
import com.platform.project.pageObjects.WelcomePage;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WelcomePageTest
{
    WebDriver driver, driver1;
    LogInPage logInPage, logInPage1;
    HomePage homePage, homePage1;
    WelcomePage welcomePage, welcomePage1;
    WebDriverManager webDriverManager, webDriverManager1;
    Commons c;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver("chrome");
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        welcomePage = new WelcomePage(driver);
        c = new Commons();

        // webDriverManager1 = new WebDriverManager();
        //driver1 = webDriverManager1.getDriver("firefox");
        //omePage1 = new HomePage(driver1);
        //logInPage1 = new LogInPage(driver1);
        //welcomePage1 = new WelcomePage(driver1);
    }

    @Test
    public void welcomePageTest()
    {
        homePage.clickLogInText();
        logInPage.enterUserDetails();
        c.assertResult(driver, welcomePage.getPageTitle(), "Welcome to iBusiness");
        welcomePage.logOff();
    }


    @Test
    public void enterCredFromConfigTest()
    {
        homePage.clickLogInText();
        logInPage.enterUserDetailsFromConfig();
        c.assertResult(driver, welcomePage.getPageTitle(), "Welcome to iBusiness");
        welcomePage.logOff();
    }

    @Ignore
    public void enterCredFromExcel()
    {

    }

    @Test
    public void twoUsersLogOnatSameTime()
    {
        homePage.clickLogInText();
        //homePage1.clickLogInText();

        logInPage.enterUserDetailsFromConfig();
        //logInPage1.enterUserDetails();

        c.assertResult(driver, welcomePage.getPageTitle(), "Welcome to iBusiness");
        //Assert.assertEquals(welcomePage1.getPageTitle(), "Welcome to iBusiness");

        welcomePage.logOff();
        //welcomePage1.logOff();
    }

    @Test
    public void noEmailPasswordMatch()
    {
        homePage.clickLogInText();
        logInPage.enterUserDetailsError();
        c.assertResult(driver, logInPage.getErrorMsg(), " Error: No match for E-Mail Address and/or Password.");
    }



    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
        //driver1.quit();
    }
}
