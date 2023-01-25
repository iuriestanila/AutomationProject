package com.coherent.training.selenium.stanila.base.utils.drivers;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Remote {
    @SneakyThrows
    public static WebDriver createDriver(String browser, String version,
                                         String platform, String remoteURL) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", browser);
        caps.setCapability("browserVersion", version);
        caps.setCapability("platformName", platform);
        caps.setCapability("remote",remoteURL);

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(caps);
            return new RemoteWebDriver(new URL(remoteURL), chromeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.merge(caps);
            return new RemoteWebDriver(new URL(remoteURL), firefoxOptions);
        } else {
            throw new RuntimeException("Wrong browser name");
        }
    }
}
