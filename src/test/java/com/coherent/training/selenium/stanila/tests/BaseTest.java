package com.coherent.training.selenium.stanila.tests;

import com.coherent.training.selenium.stanila.base.utils.DriverFactory;
import com.coherent.training.selenium.stanila.base.utils.ReadFile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    @Parameters({"browser", "version", "platform", "urlSauce"})
    @BeforeMethod
    public void initialize(String browser, @Optional String version,
                           @Optional String platform, @Optional String urlSauce) {

        DriverFactory.setDriver(browser, version, platform, urlSauce);
        DriverFactory.getDriver().get(ReadFile.read("urlSiteTest"));
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.closeBrowser();
    }
}