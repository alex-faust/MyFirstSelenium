package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.CreateAccountSuccess;
import com.platform.project.pageObjects.*;
import com.platform.project.pageObjects.LogInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.platform.project.commons.Commons.assertResult;

public class CreateAccountSuccessTest
{
    WebDriver driver;
    HomePage homePage;
    WebDriverManager webDriverManager;
    CreateAccountSuccess cas;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver("chrome");
        homePage = new HomePage(driver);
        cas = new CreateAccountSuccess(driver);
    }

    @Test
    public void createAnAccountTest()
    {
        homePage.openHomePage();
        homePage.clickCreateAccount();
        cas.createAnAccount();
        assertResult(driver, cas.getPageTitle(), "Your Account Has Been Created!");
    }

    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }
}
