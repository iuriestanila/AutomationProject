package com.coherent.training.selenium.stanila.base.pages;


import com.coherent.training.selenium.stanila.base.utils.Waits;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPOM extends BasePOM {
    public enum MenuItem {
        ELECTRONICS("Электроника"),
        PHONES_AND_ACCESSORIES("Мобильные телефоны"),
        SMARTPHONES("Смартфоны");

        private final String text;

        MenuItem(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    private static final String TOP_MENU_ITEM_TEMPLATE = "//span[contains(text(),'%s')]";
    private static final String LEFT_MENU_ITEM_TEMPLATE = "//div[contains(text(),'%s')]";
    private static final String CENTER_SUBMENU_ITEM_TEMPLATE = "//a[@href='https://catalog.onliner.by/mobile']//span[contains(text(),'%s')]";

    public CatalogPOM(WebDriver driver) {
        super(driver);
    }

    private By chooseMenuItem(MenuItem menuItem) {
        String locator = null;
        if (menuItem.equals(MenuItem.ELECTRONICS)) {
            locator = String.format(TOP_MENU_ITEM_TEMPLATE, menuItem.getText());
        } else if (menuItem.equals(MenuItem.PHONES_AND_ACCESSORIES)) {
            locator = String.format(LEFT_MENU_ITEM_TEMPLATE, menuItem.getText());
        } else if (menuItem.equals(MenuItem.SMARTPHONES)) {
            locator = String.format(CENTER_SUBMENU_ITEM_TEMPLATE, menuItem.getText());
        }
        By by = By.xpath(locator);
        Waits.isPresentBy(driver, by);
        return by;
    }


    @Step("Click on the electronics")
    public CatalogPOM clickElectronics() {
        driver.findElement(chooseMenuItem(MenuItem.ELECTRONICS)).click();
        return new CatalogPOM(driver);
    }

    @Step("Click on the phones and accessories")
    public CatalogPOM clickPhonesAndAccessories() {
        driver.findElement(chooseMenuItem(MenuItem.PHONES_AND_ACCESSORIES)).click();
        return new CatalogPOM(driver);
    }

    @Step("Click on the smartphones")
    public ProductsPOM clickSmartphones() {
       driver.findElement(chooseMenuItem(MenuItem.SMARTPHONES)).click();
        return new ProductsPOM(driver);
    }
}




