package com.coherent.training.selenium.stanila.base.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPOM extends BasePOM{
    @FindBy(xpath = "//span[@class='b-main-navigation__text' and contains(text(),'Каталог')]")
    private WebElement catalog;

    public MainPOM(WebDriver driver) {
        super(driver);
    }

    @Step("Click on the catalog")
    public CatalogPOM clickCatalog(){
        catalog.click();
        return new CatalogPOM(driver);
    }
}
