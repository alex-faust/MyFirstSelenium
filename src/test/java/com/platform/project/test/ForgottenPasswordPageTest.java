package com.platform.project.test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.ForgottenPasswordPage;
import com.platform.project.pageObjects.HomePage;
import com.platform.project.pageObjects.LogInPage;
import com.platform.project.pageObjects.WelcomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.platform.project.commons.Commons.check;

public class ForgottenPasswordPageTest
{
    WebDriver driver;
    WebDriverManager webDriverManager;
    ForgottenPasswordPage forgottenPasswordPage;

    @BeforeMethod
    public void setUp()
    {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver
                (Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
        forgottenPasswordPage = new ForgottenPasswordPage(driver);

    }

    @Test
    public void forgotPasswordTest()
    {
        Commons.check(driver, forgottenPasswordPage.getNoRecordsFound().equals(" Error: The E-Mail Address was not " +
                        "found in our records, please try again."), "forgotPasswordFail");
    }


    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }

}
