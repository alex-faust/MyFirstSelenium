package com.platform.project.test;

import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.*;
import com.platform.project.pageObjects.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.platform.project.commons.Commons.assertResult;

public class LogOffPageTest
{
    WebDriver driver, driver1;
    LogInPage logInPage, logInPage1;
    HomePage homePage, homePage1;
    WelcomePage welcomePage, welcomePage1;
    WebDriverManager webDriverManager, webDriverManager1;
    LogOffPage logOff, logOff1;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver("chrome");
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        welcomePage = new WelcomePage(driver);
        logOff = new LogOffPage(driver);

        webDriverManager1 = new WebDriverManager();
        driver1 = webDriverManager1.getDriver("firefox");
        homePage1 = new HomePage(driver1);
        logInPage1 = new LogInPage(driver1);
        welcomePage1 = new WelcomePage(driver1);
        logOff1 = new LogOffPage(driver1);
    }

    @Test
    public void LogOffPageTest()
    {
        homePage.openHomePage();
        homePage.clickLogInText();
        logInPage.enterUserDetails();
        welcomePage.logOff();
        assertResult(driver, logOff.getPageTitle(), "Log Off");
    }

    @Test
    public void enterCredFromConfigTest()
    {
        homePage.openHomePage();
        homePage.clickLogInText();
        logInPage.enterUserDetailsFromConfig();
        welcomePage.logOff();
        assertResult(driver, logOff.getPageTitle(), "Log Off");
    }

    @Test
    public void enterCredFromExcel()
    {
        homePage.openHomePage();
        homePage.clickLogInText();
        logInPage.enterUserDetailsFromExcel();
        welcomePage.logOff();
        assertResult(driver, logOff.getPageTitle(), "Log Off");
    }

    @Test
    public void twoUsersLogOnAtSameTime()
    {
        homePage.openHomePage();
        homePage1.openHomePage();

        homePage.clickLogInText();
        homePage1.clickLogInText();

        logInPage.enterUserDetailsFromConfig();
        logInPage1.enterUserDetails();

        welcomePage.logOff();
        welcomePage1.logOff();

        assertResult(driver, logOff.getPageTitle(), "Log Off");
        assertResult(driver, logOff1.getPageTitle(), "Log Off");
    }


    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
        driver1.quit();
    }
}
