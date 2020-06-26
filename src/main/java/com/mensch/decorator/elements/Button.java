package com.mensch.decorator.elements;

import org.openqa.selenium.WebDriver;

public interface Button extends Element {
    void click();
    void clickInvisible(WebDriver driver);
    String getText();
}
