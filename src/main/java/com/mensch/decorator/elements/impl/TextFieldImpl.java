package com.mensch.decorator.elements.impl;

import com.mensch.decorator.elements.TextField;
import com.mensch.utils.TestUtils;
import com.mensch.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;

import java.util.concurrent.TimeUnit;

class TextFieldImpl extends AbstractElement implements TextField {

	TextFieldImpl(final WebElement wrappedElement) {
		super(wrappedElement);
	}

	@Override
	public void clear(WebDriver driver) {
		elementClear(driver);
	}

	@Override
	public void clearAndType(final String text, WebDriver driver) {
		elementClear(driver);
		sendKeys(text);
	}

	@Override
	public String getAttribute(String field, WebDriver driver) {
		//TestUtils.highlightElement(wrappedElement, driver);
		return wrappedElement.getAttribute(field);
	}

	@Override
	public String getText(WebDriver driver) {
		//TestUtils.highlightElement(wrappedElement, driver);
		return wrappedElement.getText();
	}

	@Override
	public String getClassName() {
		return wrappedElement.getAttribute("class");
	}

	private void elementClear(WebDriver driver) {
		//TestUtils.highlightElement(wrappedElement, driver);
		wrappedElement.clear();
	}

	private void sendKeys(final String text) {
		wrappedElement.sendKeys(text);
	}

	public void waitAndType (final String text, WebDriver driver) {
		WaitUtils.waitAndType(this, text, driver);
	}

}
