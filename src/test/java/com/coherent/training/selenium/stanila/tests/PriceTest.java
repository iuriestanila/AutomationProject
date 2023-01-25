package com.coherent.training.selenium.stanila.tests;

import com.coherent.training.selenium.stanila.base.pages.MainPOM;
import com.coherent.training.selenium.stanila.base.pages.ProductsPOM;
import com.coherent.training.selenium.stanila.base.pojo.Phone;
import com.coherent.training.selenium.stanila.base.utils.drivers.DriverFactory;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PriceTest extends BaseTest{
    private WebDriver driver;
    private MainPOM mainPOM;
    private ProductsPOM productsPOM;
    private List<Phone> filledList;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        mainPOM = new MainPOM(driver);
        productsPOM = new ProductsPOM(driver);
    }

    @Test()
    @Description("Compare the prices of 2 phones")
    public void testPrices() {

        mainPOM.clickCatalog()
                .clickElectronics()
                .clickPhonesAndAccessories()
                .clickSmartphones()
                .choosePhoneType()
                .chooseYear();

        addPhones(productsPOM.getNamePhones(), productsPOM.getPricePhones());

        log.info("Comparing prices of phones: " + filledList.get(0).getName() + " and " + filledList.get(1).getName());
        log.info("Price of " + filledList.get(0).getName() + ": " + filledList.get(0).getPrice());
        log.info("Price of " + filledList.get(1).getName() + ": " + filledList.get(1).getPrice());

        System.out.println("Comparing prices of phones: " + filledList.get(0).getName() + " and " + filledList.get(1).getName());
        System.out.println("Price of " + filledList.get(0).getName() + ": " + filledList.get(0).getPrice());
        System.out.println("Price of " + filledList.get(1).getName() + ": " + filledList.get(1).getPrice());

        Assert.assertNotEquals(filledList.get(0).getName()+" price: "+filledList.get(0).getPrice(),
                             filledList.get(1).getName()+" price: "+filledList.get(1).getPrice(),
                             "Prices are equal");
    }

    private List<Phone> addPhones(List<WebElement> namePhones, List<WebElement> pricePhones) {
        filledList = new ArrayList<>();
        for (int i = 0; i < pricePhones.size(); i++) {
            Phone phone = new Phone();
            phone.setName(namePhones.get(i).getText());
            phone.setPrice(pricePhones.get(i).getText());
            filledList.add(phone);
        }
        return filledList;
    }
}
