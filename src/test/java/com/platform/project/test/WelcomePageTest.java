package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.HomePage;
import com.platform.project.pageObjects.LogInPage;
import com.platform.project.pageObjects.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        driver = webDriverManager.getDriver
                (Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
        homePage = new HomePage(driver);
        logInPage = new LogInPage(driver);
        welcomePage = new WelcomePage(driver);
    }

    @Test
    public void noEmailPasswordMatch()
    {
        homePage.openHomePage();
        homePage.clickLogInText();
        logInPage.enterUserDetailsError();
        Commons.check(driver, logInPage.getErrorMsg().equals(" Error: No match for E-Mail Address and/or Password."),
                "noEmailPasswordMatchFail");
    }

    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }
}
