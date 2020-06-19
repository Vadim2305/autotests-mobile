package com.mensch.decorator;

import org.openqa.selenium.WebElement;

public interface Factory<T> {

	<K extends T> K create(Class<K> elementClass, WebElement wrappedElement);
}
