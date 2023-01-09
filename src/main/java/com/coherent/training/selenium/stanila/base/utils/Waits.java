package com.coherent.training.selenium.stanila.base.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public class Waits {
    public static boolean isPresent(WebDriver driver, List<WebElement> elements) {
        Wait<WebDriver> wait = getFluentWait(driver);

        return wait.until(webDriver -> {
            boolean present = true;
            for (WebElement element : elements) {
                if (!element.isDisplayed()) {
                   present = false;
                    break;
                }
            }
            return present;
        });
    }

    public static Wait<WebDriver> getFluentWait(WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }
}
