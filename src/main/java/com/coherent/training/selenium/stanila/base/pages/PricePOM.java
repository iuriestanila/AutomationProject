package com.coherent.training.selenium.stanila.base.pages;

import com.coherent.training.selenium.stanila.base.pojo.Phone;
import com.coherent.training.selenium.stanila.base.utils.JsExecutor;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PricePOM extends BasePOM {
    @FindBy(xpath = "//span[@class='b-main-navigation__text' and contains(text(),'Каталог')]")
    private WebElement catalog;

    @FindBy(xpath = "//span[contains(text(),'Электроника')]")
    private WebElement electronicItems;

    @FindBy(xpath = "//div[contains(text(),'Мобильные телефоны и аксессуары')]")
    private WebElement phonesAndAccessories;
    @FindBy(xpath = "//a[@href='https://catalog.onliner.by/mobile']//span//span[contains(text(),'Смартфоны')]")
    private WebElement smartphones;

    @FindBy(xpath = "//li//input[@type='checkbox' and @value='oneplus']")
    private WebElement checkBoxTypePhone;

    @FindBy(xpath = "//span[normalize-space()='2020']")
    private WebElement checkBoxYear;

    @FindBy(xpath = "//span[contains(text(),'Смартфон OnePlus Nord N10 5G (полуночный лед)')]")
    private WebElement phone1;
    @FindBy(xpath = "//span[contains(text(),'1109,02 р.')]")
    private WebElement pricePhone1;

    @FindBy(xpath = "//span[contains(text(),'Смартфон OnePlus Nord 12GB/256GB (серый оникс)')]")
    private WebElement phone2;
    @FindBy(xpath = "//span[contains(text(),'1300,00 р.')]")
    private WebElement ricePhone2;
    List<Phone> phones;

    public PricePOM(WebDriver driver) {
        super(driver);
    }

    @Step("Search for phones")
    public void search(){
        catalog.click();
        electronicItems.click();
        phonesAndAccessories.click();
        smartphones.click();
        JsExecutor.jsClick(checkBoxTypePhone,driver);
        JsExecutor.jsClick(checkBoxYear,driver);
    }

    @Step("Extract the name of the phone 1")
    public String namePhone1() {
        String text = phone1.getText();
        String[] elements = text.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 3; i++) {
            sb.append(elements[i]);
            sb.append(" ");
        }
        return new String(sb);
    }

    @Step("Extract the price of the phone 1")
    @SneakyThrows
    public double pricePhone1(){
        String value = pricePhone1.getText().replaceAll("[p.]", "");
        NumberFormat format = NumberFormat.getInstance(Locale.FRENCH);
        double price = format.parse(value).doubleValue();
        return price;
    }

    @Step("Extract the name of the phone 2")
    public String namePhone2() {
        String text = phone2.getText();
        String[] elements = text.split(" ");
        String name = String.join(" ",elements[1],elements[2]);
        return name;
    }

    @Step("Extract the price of the phone 2")
    @SneakyThrows
    public double pricePhone2(){
        String value = ricePhone2.getText().replaceAll("[p.]", "");
        NumberFormat format = NumberFormat.getInstance(Locale.FRENCH);
        double price = format.parse(value).doubleValue();
        return price;
    }

    @Step("Add the phones")
    public List<Phone> addPhones(){
        phones = new ArrayList<>();
        phones.add(new Phone(namePhone1(), pricePhone1()));
        phones.add(new Phone(namePhone2(), pricePhone2()));
        return phones;
    }

    @Step("Get the price from OnePlus N10")
    public double priceOnePlusN10(){
        double price = 0.0;

        for (Phone phone : phones){
            if("OnePlus Nord N10".equalsIgnoreCase(phone.getName())){
                price = phone.getPrice();
            }
        }
        return price;
    }

    @Step("Get the price from OnePlus")
    public double priceOnePlus(){
        double price = 0.0;

        for (Phone phone : phones){
            if("OnePlus Nord".equalsIgnoreCase(phone.getName())){
                price = phone.getPrice();
            }
        }
        return price;
    }
}
