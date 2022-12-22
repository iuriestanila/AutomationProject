package com.coherent.training.selenium.stanila.base.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePOM {
    public WebDriver driver;

    public BasePOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
