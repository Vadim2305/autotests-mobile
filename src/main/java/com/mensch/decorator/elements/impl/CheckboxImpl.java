package com.mensch.decorator.elements.impl;

import com.mensch.decorator.elements.Checkbox;
import com.mensch.utils.TestUtils;
import com.mensch.utils.WaitUtils;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class CheckboxImpl extends AbstractElement implements Checkbox {

	TestUtils testUtils;
	CheckboxImpl(final WebElement wrappedElement) {
		super(wrappedElement);
	}

	@Override
	public void click() {
		testUtils.highlightElement(wrappedElement);
		wrappedElement.click();
	}

	@Override
	public void clickInvisible() {
		testUtils.highlightElement(wrappedElement);
		testUtils.clickInvisible(wrappedElement);
	}

	@Override
	public String getText() {
		return wrappedElement.getText();
	}

	@Override
	public boolean isSelected() {
		testUtils.highlightElement(wrappedElement);
		return wrappedElement.isSelected();
	}
}
