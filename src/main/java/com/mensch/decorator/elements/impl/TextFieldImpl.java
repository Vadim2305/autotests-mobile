package com.mensch.decorator.elements.impl;

import com.mensch.decorator.elements.TextField;
import com.mensch.utils.TestUtils;
import com.mensch.utils.WaitUtils;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

class TextFieldImpl extends AbstractElement implements TextField {

	TestUtils testUtils;
	WaitUtils waitUtils;
	TextFieldImpl(final WebElement wrappedElement) {
		super(wrappedElement);
	}

	@Override
	public void clear() {
		elementClear();
	}

	@Override
	public void clearAndType(final String text) {
		elementClear();
		sendKeys(text);
	}

	@Override
	public String getAttribute(String field) {
		testUtils.highlightElement(wrappedElement);
		return wrappedElement.getAttribute(field);
	}

	@Override
	public String getText() {
		testUtils.highlightElement(wrappedElement);
		return wrappedElement.getText();
	}

	@Override
	public String getClassName() {
		return wrappedElement.getAttribute("class");
	}

	private void elementClear() {
		testUtils.highlightElement(wrappedElement);
		wrappedElement.clear();
	}

	private void sendKeys(final String text) {
		wrappedElement.sendKeys(text);
	}

	public void waitAndType (final String text) {
		waitUtils.waitAndType(this, text);
	}

	public void waitAndType (final String text, int timeOut, TimeUnit timeUnit) {
		waitUtils.waitAndType(this, text, timeOut, timeUnit);
	}
}
