package com.mensch.decorator.elements;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public interface TextField extends Element {

    void clear(WebDriver driver);

    String getAttribute(String field, WebDriver driver);

    String getText(WebDriver driver);

    void clearAndType(String text, WebDriver driver);

    String getClassName();

    void waitAndType(String text, WebDriver driver);

}
