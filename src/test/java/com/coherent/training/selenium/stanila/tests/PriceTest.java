package com.coherent.training.selenium.stanila.tests;

import com.coherent.training.selenium.stanila.base.pages.PricePOM;
import com.coherent.training.selenium.stanila.base.utils.DriverFactory;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PriceTest extends BaseTest{

    @Test
    @Description("Compare the prices of 2 phones")
    public void testPrices() {
        WebDriver driver = DriverFactory.getDriver();
        PricePOM pricePOM = new PricePOM(driver);

        pricePOM.search();
        pricePOM.addPhones();

        Assert.assertNotEquals(pricePOM.priceOnePlusN10(),pricePOM.priceOnePlus());


    }
}
