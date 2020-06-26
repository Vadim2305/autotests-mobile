package com.mensch.decorator.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public interface Element {
    void init(WebElement wrappedElement);
    boolean isDisplayed();

    void waitAndClick(int timeout, TimeUnit timeUnit, WebDriver driver);

    void waitAndClick(WebDriver driver);
    void waitFor( WebDriver driver);
    void waitFor(int timeout, TimeUnit timeUnit, WebDriver driver);

}
