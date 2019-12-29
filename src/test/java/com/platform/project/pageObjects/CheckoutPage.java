package com.platform.project.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage
{
    @FindBy(xpath = "//h1[contains(text(),'Delivery Information')]")
    WebElement deliveryInfo;
    @FindBy(xpath = "//h1[contains(text(),'Payment Information')]")
    WebElement paymentInfo;
    @FindBy(xpath = "//h1[contains(text(),'Order Confirmation')]")
    WebElement orderConfirmation;
    @FindBy(xpath = "//h1[contains(text(),'Your Order Has Been Processed!')]")
    WebElement processedOrder;
    @FindBy(xpath = "//tr[@class='moduleRowSelected']//input[@name='payment']")
    WebElement cashOnDeliveryBtn;
    @FindBy(xpath = "//tr[@class='moduleRow']//input[@name='payment']")
    WebElement paypalBtn;
    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement continueButton;
    @FindBy(xpath = "//span[contains(text(),'Confirm Order')]")
    WebElement confirmOrder;


    private WebDriver driver;
    private Logger log = Logger.getLogger(CheckoutPage.class);

    public CheckoutPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getDeliveryInfoTitle()
    {
        String text = deliveryInfo.getText();
        log.info("Delivery Info: " + text);
        return text;
    }

    public String getPaymentInfoTitle()
    {
        String text = paymentInfo.getText();
        log.info("Payment Info: " + text);
        return text;
    }

    public String getProcessOrderTitle()
    {
        String text = processedOrder.getText();
        log.info("Processed order: " + text);
        return text;
    }

    public String getOrderConfTitle()
    {
        String text = orderConfirmation.getText();
        //String text = driver.findElement(By.xpath("//h1[contains(text(),'Order Confirmation')]"));
        log.info("Order Confirmation: " + text);
        return text;
    }
}
