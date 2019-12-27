package com.platform.project.test;

import com.platform.project.commons.*;
import com.platform.project.pageObjects.ForgottenPasswordPage;
import com.platform.project.pageObjects.HomePage;
import com.platform.project.pageObjects.LogInPage;
import com.platform.project.pageObjects.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        homePage.clickLogInText();
        logInPage.passwordForgotten();

        //forgottenPasswordPage.getNoRecordsFound();
        Assert.assertEquals(forgottenPasswordPage.getNoRecordsFound(),
                " Error: The E-Mail Address was not " +
                        "found in our records, please try again.");

    }


    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
        //driver1.quit();
    }

}
