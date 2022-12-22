package com.coherent.training.selenium.stanila.base.utils;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    @SneakyThrows
    public static void setDriver()  {
        System.setProperty(ReadFile.read("driverChrome"),
                ReadFile.read("chromeDriverPath"));

        driver.set(new ChromeDriver());

    }

    public  static WebDriver getDriver(){
        return driver.get();
    }

    public  static void closeBrowser(){
        driver.get().close();
        driver.remove();
    }
}
