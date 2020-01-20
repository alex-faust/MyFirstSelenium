package com.platform.project.pageObjects;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;

public class CheckOutPage
{
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/h1[1]")
    WebElement orderConfirmation;
    @FindBy(xpath = "//table[1]//tbody[1]//tr[1]//td[2]//input[1]")
    WebElement cashOnDeliveryBtn;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[6]/div[2]/span[1]/button[1]/span[2]")
    WebElement continueButton;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[3]/div[2]/span[1]/button[1]/span[2]")
    WebElement confirmOrder;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[2]/span[1]/span[1]/a[1]/span[2]")
    WebElement checkOutBtn;
    //@FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/p[1]/strong[1]")
    //WebElement subTotal;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/table[1]/tbody[1]/tr[3]/td[2]/strong[1]")
    WebElement priceTotal;

    private WebDriver driver;
    private Logger log = Logger.getLogger(CheckOutPage.class);

    public CheckOutPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String verifyCart()
    {
        Commons.clickOnElement(driver, checkOutBtn);
        Commons.clickOnElement(driver, continueButton);
        Commons.clickOnElement(driver, cashOnDeliveryBtn);

        Commons.clickOnElement(driver, continueButton);
        String total = Commons.getElementText(driver, priceTotal).substring(1);

        log.info("The Text is: " + total);


        if (ReadPropertyFile.getConfigPropertyVal("priceTotal").equals(total))
        {
            Commons.clickOnElement(driver, confirmOrder);
            return Commons.getElementText(driver, orderConfirmation);
        } else
        {
            log.info("The totals to not match up.");
            return "Info is not correct.";
        }
    }
}
