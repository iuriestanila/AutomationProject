package com.coherent.training.selenium.stanila.base.utils;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    @SneakyThrows
    public static void setDriver(String browser, String version,
                                 String platform, String urlSauce) {

        if (urlSauce != null) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", browser);
            caps.setCapability("browserVersion", version);
            caps.setCapability("platformName", platform);

            driver.set(new RemoteWebDriver(new URL("hubGridURL"), caps));

        } else {
            switch (browser) {
                case "chrome":
                    System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
                    System.setProperty(Constants.CHROME_DRIVER, Constants.CHROME_DRIVER_PATH);
                    driver.set(new ChromeDriver());
                    break;
                case "firefox":
                    System.setProperty(Constants.FIREFOX_DRIVER, Constants.FIREFOX_DRIVER_PATH);
                    driver.set(new FirefoxDriver());
                    break;
            }
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
