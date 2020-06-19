package com.mensch.decorator.elements;

import java.util.concurrent.TimeUnit;

public interface TextField extends Element {

    void clear();

    String getAttribute(String field);

    String getText();

    void clearAndType(String text);

    String getClassName();

    void waitAndType(String text);

    void waitAndType(String text, int timeout, TimeUnit timeUnit);
}
