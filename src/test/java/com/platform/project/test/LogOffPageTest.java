package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.LogInPage;
import com.platform.project.pageObjects.LogOffPage;
import com.platform.project.pageObjects.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogOffPageTest
{
    WebDriver driver, driver1;
    LogInPage logInPage, logInPage1;
    WelcomePage welcomePage, welcomePage1;
    WebDriverManager webDriverManager, webDriverManager1;
    LogOffPage logOff, logOff1;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver
                (Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
        logInPage = new LogInPage(driver);
        welcomePage = new WelcomePage(driver);
        logOff = new LogOffPage(driver);

        webDriverManager1 = new WebDriverManager();
        driver1 = webDriverManager1.getDriver("firefox");
        logInPage1 = new LogInPage(driver1);
        welcomePage1 = new WelcomePage(driver1);
        logOff1 = new LogOffPage(driver1);
    }

    @Test
    public void LogOffPageTest()
    {
        logInPage.enterUserDetails();
        welcomePage.logOff();
        Commons.check(driver, logOff.getPageTitle().equals("Log Off"), "Failed to reach LogOffPage");
    }

    @Test
    public void enterCredFromConfigTest()
    {
        logInPage.enterUserDetailsFromConfig();
        welcomePage.logOff();
        Commons.check(driver, logOff.getPageTitle().equals("Log Off"), "enterCredFromConfigFail");
    }

    @Test
    public void enterCredFromExcel()
    {
        logInPage.enterUserDetailsFromExcel();
        welcomePage.logOff();
        Commons.check(driver, logOff.getPageTitle().equals("Log Off"), "enterCredFromExcel");
    }

    @Test
    public void twoUsersLogOnAtSameTime()
    {
        //need to change this to make sure both are correct instead of just one
        //checking the first one, the second one wont be checked

        logInPage.enterUserDetailsFromConfig();
        logInPage1.enterUserDetails();

        welcomePage.logOff();
        welcomePage1.logOff();

        Commons.check(driver, logOff.getPageTitle().equals("Log Off"), "firstOfTwoUsersFail");
        Commons.check(driver1, logOff1.getPageTitle().equals("Log Off"), "secondOfTwoUsersFail");
    }


    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
        driver1.quit();
    }
}
