package com.mensch.decorator.elements.impl;

import com.mensch.decorator.Factory;
import com.mensch.decorator.elements.Element;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;

import static java.text.MessageFormat.format;

public class DefaultElementFactory implements Factory<Element> {

    @Override
    public <K extends Element> K create(Class<K> elementClass, WebElement wrappedElement) {
        try {
            return findImplementationFor(elementClass)
                    .getDeclaredConstructor(WebElement.class)
                    .newInstance(wrappedElement);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private <E extends Element> Class<? extends E> findImplementationFor(final Class<E> elementClass) {
        try {
            return (Class<? extends E>) Class.forName(format("{0}.{1}Impl", getClass().getPackage().getName(), elementClass.getSimpleName()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

