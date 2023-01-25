package com.coherent.training.selenium.stanila.base.pages;

import com.coherent.training.selenium.stanila.base.utils.JsExecutor;
import com.coherent.training.selenium.stanila.base.utils.Waits;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPOM extends BasePOM{
    @FindBy(xpath = "//li//input[@type='checkbox' and @value='oneplus']")
    private WebElement checkBoxPhoneType;
    @FindBy(xpath = "//span[normalize-space()='2020']")
    private WebElement checkBoxYear;

    @FindBy(xpath = "//span[@data-bind='html: product.extended_name || product.full_name']")
    private List<WebElement> namePhones;

    @FindBy(xpath = "//span[@data-bind=\"html: $root.format.minPrice($data.prices, 'BYN')\"]")
    private List<WebElement> pricePhones;

    public ProductsPOM(WebDriver driver) {
        super(driver);
    }

    @SneakyThrows
    @Step("Choose a phone type")
    public ProductsPOM choosePhoneType(){
        JsExecutor.jsClick(checkBoxPhoneType,driver);
        return new ProductsPOM(driver);
    }
    @Step("Choose a year production of the phone")
    public void chooseYear(){
        JsExecutor.jsClick(checkBoxYear,driver);
    }


    @SneakyThrows
    public List<WebElement> getNamePhones() {
        Waits.isPresentElements(driver, namePhones);
        return namePhones;
    }

    public List<WebElement> getPricePhones() {
        return pricePhones;
    }
}
