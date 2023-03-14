package com.coherent.training.selenium.stanila.tests;

import com.coherent.training.selenium.stanila.base.utils.drivers.DriverFactory;
import com.coherent.training.selenium.stanila.base.utils.ReadFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    @BeforeMethod
    public void initialize() {
        String browser = ReadFile.read("browser");
        String driverType = ReadFile.read("driver.type");
        String remoteURL = ReadFile.read("grid.url");
        String platform = ReadFile.read("platform.version");

        DriverFactory.setDriver(browser, platform, remoteURL, driverType);

        DriverFactory.getDriver().get(ReadFile.read("urlSiteTest"));
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.closeBrowser();
    }
}