package com.mensch.decorator.elements;

import org.openqa.selenium.WebElement;

public interface Container extends Element {
	void init(WebElement wrappedElement);
}
