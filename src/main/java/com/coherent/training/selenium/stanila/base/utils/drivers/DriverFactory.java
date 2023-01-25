package com.coherent.training.selenium.stanila.base.utils.drivers;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    @SneakyThrows
    public static void setDriver(String browser, String version,
                                 String platform, String remoteURL, boolean runLocal) {

        if(!runLocal){
            driver.set(Remote.createDriver(browser,version,platform, remoteURL));
        } else {
            driver.set(Local.createDriver(browser));
        }
        }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeBrowser() {
        driver.get().close();
        driver.remove();
    }
}

