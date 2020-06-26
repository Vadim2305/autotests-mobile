package com.mensch.decorator.elements.impl;

import com.mensch.decorator.elements.Checkbox;
import com.mensch.utils.TestUtils;
import com.mensch.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class CheckboxImpl extends AbstractElement implements Checkbox {

	CheckboxImpl(final WebElement wrappedElement) {
		super(wrappedElement);
	}

	@Override
	public void click() {
		//WebDriver driver = (WebDriver)wrappedElement;
		//testUtils.highlightElement(wrappedElement, driver);
		wrappedElement.click();
	}

	@Override
	public void clickInvisible(WebDriver driver) {
		//WebDriver driver = (WebDriver)wrappedElement;
		//testUtils.highlightElement(wrappedElement, driver);
		TestUtils.clickInvisible(wrappedElement, driver);
	}

	@Override
	public String getText() {
		return wrappedElement.getText();
	}

	@Override
	public boolean isSelected() {
		//WebDriver driver = (WebDriver)wrappedElement;
		//testUtils.highlightElement(wrappedElement, driver);
		return wrappedElement.isSelected();
	}
}
