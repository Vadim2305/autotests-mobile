package com.mensch.decorator.elements;

import java.util.concurrent.TimeUnit;

public interface Element {
    boolean isDisplayed();
    void waitAndClick(int timeout, TimeUnit timeUnit);
    void waitAndClick();
    void waitFor();
    void waitFor(int timeout, TimeUnit timeUnit);

}
