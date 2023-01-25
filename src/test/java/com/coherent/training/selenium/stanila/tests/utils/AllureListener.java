package com.coherent.training.selenium.stanila.tests.utils;

import com.coherent.training.selenium.stanila.base.utils.drivers.DriverFactory;
import com.coherent.training.selenium.stanila.base.utils.ReadFile;
import com.coherent.training.selenium.stanila.tests.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AllureListener extends BaseTest implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult){
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveFailureScreenshot(WebDriver driver){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "NameOfMethod", type = "text/plain")
    public static String saveTextLog(String message){
        return message;
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("onStart method " + context.getName());
        context.setAttribute("WebDriver", DriverFactory.getDriver());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("onFinish method " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("onTestStart method " + getTestMethodName(result) + " start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("onTestSuccess method " + getTestMethodName(result) + " succeed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("onTestFailure method " + getTestMethodName(result) + " failed");

        saveFailureScreenshot(DriverFactory.getDriver());
        saveTextLog(result.getMethod().getConstructorOrMethod().getName());

        LocalDateTime dateTime = LocalDateTime.now();

        if (DriverFactory.getDriver().getClass() == ChromeDriver.class) {
            Allure.step("Type of the browser", () -> {
                Allure.attachment("Browser", "chrome");
                Allure.attachment("BrowserVersion", ReadFile.read("chromeVersion"));
                Allure.attachment("PlatformVersion", ReadFile.read("platformVersion"));
                Allure.attachment("Date and time", dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
            });
        } else if (DriverFactory.getDriver().getClass() == FirefoxDriver.class) {
            Allure.step("Type of the browser", () -> {
                Allure.attachment("Browser", "firefox");
                Allure.attachment("BrowserVersion", ReadFile.read("firefoxVersion"));
                Allure.attachment("PlatformVersion", ReadFile.read("firefoxPlatformVersion"));
                Allure.attachment("Date and time", dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
            });
        } else if (DriverFactory.getDriver() instanceof RemoteWebDriver) {
            Capabilities cap = ((RemoteWebDriver) DriverFactory.getDriver()).getCapabilities();
            Allure.step("Type of the browser", () -> {
                Allure.attachment("Browser", cap.getBrowserName());
                Allure.attachment("BrowserVersion", cap.getBrowserVersion());
                Allure.attachment("PlatformName", String.valueOf(cap.getPlatformName()));
                Allure.attachment("PlatformVersion", cap.getCapability("platformVersionRemote").toString());
                Allure.attachment("Date", dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
            });
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("On test skipped: "+getTestMethodName(result)+" skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but within success percentage: "+getTestMethodName(result));
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        System.out.println("Test failed with timeout: "+getTestMethodName(result));
    }
}
