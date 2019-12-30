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
    WebDriver driver;
    LogInPage logInPage;
    HomePage homePage;
    WelcomePage welcomePage;
    WebDriverManager webDriverManager;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver("chrome");
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        welcomePage = new WelcomePage(driver);
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
