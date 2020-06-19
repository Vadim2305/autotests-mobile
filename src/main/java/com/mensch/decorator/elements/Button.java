package com.mensch.decorator.elements;

public interface Button extends Element {
    void click();
    void clickInvisible();
    String getText();
    void waitAndClick();
}
