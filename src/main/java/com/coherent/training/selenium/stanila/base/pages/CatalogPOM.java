package com.coherent.training.selenium.stanila.base.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CatalogPOM extends BasePOM {
    @FindBy(xpath = "//span[contains(text(),'Электроника')]")
    private WebElement electronics;
    @FindBy(xpath = "//div[contains(text(),'Мобильные телефоны и аксессуары')]")
    private WebElement phonesAndAccessories;
    @FindBy(xpath = "//a[@href='https://catalog.onliner.by/mobile']//span[contains(text(),'Смартфоны')]")
    private WebElement smartphones;

    public CatalogPOM(WebDriver driver) {
        super(driver);
    }

    @Step("Click on the electronics")
    public CatalogPOM clickElectronics(){
        electronics.click();
        return new CatalogPOM(driver);
    }

    @Step("Click on the phones and accessories")
    public CatalogPOM clickPhonesAndAccessories(){
        phonesAndAccessories.click();
        return new CatalogPOM(driver);
    }

    @Step("Click on the smartphones")
    public ProductsPOM clickSmartphones(){
        smartphones.click();
        return new ProductsPOM(driver);
    }
}
