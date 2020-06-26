package com.mensch.decorator.elements.impl;

import com.mensch.def.Constants;
import com.mensch.decorator.elements.Element;
import com.mensch.base.Portal;
import com.mensch.utils.TestUtils;
import com.mensch.utils.WaitUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

abstract class AbstractElement implements Element {

    WebElement wrappedElement;

    @Override
    public final void init(final WebElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }

    AbstractElement(final WebElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }

    @Override
    public boolean isDisplayed() {
        //TestUtils.highlightElement(wrappedElement, driver);
        return wrappedElement.isDisplayed();
    }

    @Override
    public void waitAndClick(int timeout, TimeUnit timeUnit, WebDriver driver) {
        WaitUtils.waitAndClick(wrappedElement, timeout, timeUnit, driver);
    }

    @Override
    public void waitAndClick(WebDriver driver) {
        waitAndClick(Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
    }

    @Override
    public void waitFor(WebDriver driver) {
        waitFor(Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
    }

    @Override
    public void waitFor(int timeout, TimeUnit timeUnit, WebDriver driver) {
        WaitUtils.waitFor(wrappedElement, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
    }

}
