package com.coherent.training.selenium.stanila.base.utils.drivers;

import com.coherent.training.selenium.stanila.base.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Local {
    public static WebDriver createDriver(String browser){

        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty(Constants.CHROME_DRIVER, Constants.CHROME_DRIVER_PATH);
            return new ChromeDriver();
        } else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty(Constants.FIREFOX_DRIVER, Constants.FIREFOX_DRIVER_PATH);
            return new FirefoxDriver();
        } else {
            throw new RuntimeException("Wrong browser name");
        }
    }
}
