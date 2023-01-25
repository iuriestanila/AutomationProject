package com.coherent.training.selenium.stanila.tests;

import com.coherent.training.selenium.stanila.base.utils.drivers.DriverFactory;
import com.coherent.training.selenium.stanila.base.utils.ReadFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
  public static Logger log = LogManager.getLogger();
    @BeforeMethod
    public void initialize() {

        String browser = System.getProperty("browser","chrome");
        String version;
        String platform;

        String remoteURL = System.getProperty("remote","");
        boolean runLocal = true;

        if(browser.equalsIgnoreCase("chrome")){
            version = ReadFile.read("chromeVersion");
            platform = ReadFile.read("platformVersion");
        } else if (browser.equalsIgnoreCase("firefox")){
            version = ReadFile.read("firefoxVersion");
            platform = ReadFile.read("platformVersion");
        } else {
            throw new RuntimeException("Wrong browser name");
        }

        if(!remoteURL.isEmpty()){
            runLocal = false;
        }

        DriverFactory.setDriver(browser, version, platform, remoteURL, runLocal);

        DriverFactory.getDriver().get(ReadFile.read("urlSiteTest"));
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.closeBrowser();
    }
}