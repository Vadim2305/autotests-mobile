package com.mensch.decorator.elements.impl;

import com.mensch.decorator.elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

class ButtonImpl extends AbstractElement implements Button {

    ButtonImpl(final WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public void click() {
        //TestUtils.highlightElement(wrappedElement);
        wrappedElement.click();
    }

    @Override
    public void clickInvisible() {

    }

    @Override
    public String getText() {
        //TestUtils.highlightElement(wrappedElement);
        return wrappedElement.getText();
    }

}
