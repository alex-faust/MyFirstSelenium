package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.*;
import com.platform.project.pageObjects.LogInPage;
import com.platform.project.pageObjects.WelcomePage;
import org.junit.Ignore;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.platform.project.commons.Commons.assertResult;

public class WelcomePageTest
{
    WebDriver driver, driver1;
    LogInPage logInPage, logInPage1;
    HomePage homePage, homePage1;
    WelcomePage welcomePage, welcomePage1;
    WebDriverManager webDriverManager, webDriverManager1;
    //Commons c;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver("chrome");
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        welcomePage = new WelcomePage(driver);

        // webDriverManager1 = new WebDriverManager();
        //driver1 = webDriverManager1.getDriver("firefox");
        //omePage1 = new HomePage(driver1);
        //logInPage1 = new LogInPage(driver1);
        //welcomePage1 = new WelcomePage(driver1);
    }

    @Test
    public void welcomePageTest()
    {
        homePage.openHomePage();
        homePage.clickLogInText();
        logInPage.enterUserDetails();
        assertResult(driver, welcomePage.getPageTitle(), "Welcome to iBusiness");
        //welcomePage.logOff();
    }


    @Test
    public void enterCredFromConfigTest()
    {
        homePage.openHomePage();
        homePage.clickLogInText();
        logInPage.enterUserDetailsFromConfig();
        assertResult(driver, welcomePage.getPageTitle(), "Welcome to iBusiness");
        //welcomePage.logOff();
    }

    @Test
    public void enterCredFromExcel()
    {
        homePage.openHomePage();
        homePage.clickLogInText();
        logInPage.enterUserDetailsFromExcel();
        assertResult(driver, welcomePage.getPageTitle(), "Welcome to iBusiness");
    }

    @Test
    public void twoUsersLogOnatSameTime()
    {
        homePage.openHomePage();
        //homePage1.openHomePage();

        homePage.clickLogInText();
        //homePage1.clickLogInText();

        logInPage.enterUserDetailsFromConfig();
        //logInPage1.enterUserDetails();

        assertResult(driver, welcomePage.getPageTitle(), "Welcome to iBusiness");
        //Assert.assertEquals(welcomePage1.getPageTitle(), "Welcome to iBusiness");
    }

    @Test
    public void noEmailPasswordMatch()
    {
        homePage.clickLogInText();
        logInPage.enterUserDetailsError();
        assertResult(driver, logInPage.getErrorMsg(), " Error: No match for E-Mail Address and/or Password.");
    }



    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
        //driver1.quit();
    }
}
