package com.platform.project.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage
{
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[2]/div[2]/span[1]/span[1]/button[1]/span[2]")
    WebElement addToCartButton;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/h1[2]")
    WebElement productName;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/h1[1]")
    WebElement productPrice;



    private WebDriver driver;
    private Logger log = Logger.getLogger(ProductInfoPage.class);

    public ProductInfoPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addToCart()
    {
        addToCartButton.click();
    }
}
