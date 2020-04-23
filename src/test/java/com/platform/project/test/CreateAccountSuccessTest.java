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
    WebDriverManager webDriverManager;
    CreateAccountSuccess cas;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver
                (Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
        cas = new CreateAccountSuccess(driver);
    }

    @Test
    public void createAnAccountTest()
    {
        cas.createAnAccount();
        check(driver, cas.getPageTitle().equals("Your Account Has Been Created!"), "createAnAccountFail");
    }

    @Test
    public void alertTests()
    {
        cas.handleAlerts();
        cas.createAnAccount();
        check(driver, cas.getPageTitle().equals("Your Account Has Been Created!"), "createAnAccountFail");
    }

    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }
}
