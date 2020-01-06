package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.CreateAccountSuccess;
import com.platform.project.pageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.platform.project.commons.Commons.check;

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
        driver = webDriverManager.getDriver
                (Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
        homePage = new HomePage(driver);
        cas = new CreateAccountSuccess(driver);
    }

    @Test
    public void createAnAccountTest()
    {
        homePage.openHomePage();
        homePage.clickCreateAccount();
        cas.createAnAccount();
        check(driver, cas.getPageTitle().equals("Your Account Has Been Created!"), "createAnAccountFail");
    }

    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }
}
