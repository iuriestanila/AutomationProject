package com.coherent.training.selenium.stanila.tests.utils;

import com.coherent.training.selenium.stanila.base.utils.DriverFactory;
import com.coherent.training.selenium.stanila.tests.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

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
        System.out.println("on start: "+context.getName());
        context.setAttribute("WebDriver", DriverFactory.getDriver());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("on finish: "+context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("On test start: "+result.getName()+" start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("On test success: "+ getTestMethodName(result)+" succeeded");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveFailureScreenshot(DriverFactory.getDriver());
        saveTextLog(result.getMethod().getConstructorOrMethod().getName());
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
