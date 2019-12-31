package com.platform.project.test;

import com.platform.project.commons.*;
import com.platform.project.pageObjects.ForgottenPasswordPage;
import com.platform.project.pageObjects.*;
import com.platform.project.pageObjects.LogInPage;
import com.platform.project.pageObjects.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.platform.project.commons.Commons.assertResult;

public class ForgottenPasswordPageTest
{
    WebDriver driver;
    LogInPage logInPage;
    HomePage homePage;
    WelcomePage welcomePage;
    WebDriverManager webDriverManager;
    ForgottenPasswordPage forgottenPasswordPage;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver("chrome");
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        welcomePage = new WelcomePage(driver);
        forgottenPasswordPage = new ForgottenPasswordPage(driver);

    }

    @Test
    public void forgotPasswordTest()
    {
        homePage.openHomePage();
        homePage.clickLogInText();
        logInPage.passwordForgotten();
        assertResult(driver, forgottenPasswordPage.getNoRecordsFound(),
                " Error: The E-Mail Address was not " +
                        "found in our records, please try again.");
    }


    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }

}
