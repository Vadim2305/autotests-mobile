package com.mensch.decorator.elements.impl;

import com.mensch.def.Constants;
import com.mensch.decorator.elements.Element;
import com.mensch.base.Portal;
import com.mensch.utils.TestUtils;
import com.mensch.utils.WaitUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

abstract class AbstractElement implements Element {

    TestUtils testUtils;
    WaitUtils waitUtils;
    final WebElement wrappedElement;

    AbstractElement(final WebElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }

    @Override
    public boolean isDisplayed() {
        testUtils.highlightElement(wrappedElement);
        return wrappedElement.isDisplayed();
    }

    @Override
    public void waitAndClick(int timeout, TimeUnit timeUnit) {
        waitUtils.waitAndClick(wrappedElement, timeout, timeUnit);
    }

    @Override
    public void waitAndClick() {
        waitAndClick(Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
    }

    @Override
    public void waitFor() {
        waitFor(Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
    }

    @Override
    public void waitFor(int timeout, TimeUnit timeUnit) {
        waitUtils.waitFor(wrappedElement, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
    }

}
