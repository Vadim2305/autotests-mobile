package com.mensch.decorator;


import com.mensch.decorator.elements.Container;
import com.mensch.decorator.elements.Element;
import com.mensch.decorator.elements.impl.DefaultElementFactory;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public class ExtendedFieldDecorator extends DefaultFieldDecorator {

	private final Factory elementFactory = new DefaultElementFactory();
	private final Factory containerFactory = new DefaultContainerFactory();

	public ExtendedFieldDecorator(ElementLocatorFactory factory) {
		super(factory);
	}

	@Override
	public Object decorate(final ClassLoader loader, final Field field) {
		if (Container.class.isAssignableFrom(field.getType())) {
			return decorateContainer(containerFactory, loader, field);
		}
		if (Element.class.isAssignableFrom(field.getType())) {
			return decorateElement(elementFactory, loader, field);
		}
		return super.decorate(loader, field);
	}

	private Object decorateElement(final Factory factory, final ClassLoader loader, final Field field) {
		final WebElement wrappedElement = proxyForLocator(loader, this.factory.createLocator(field));
		return createFactory(factory, field, wrappedElement);
	}

	private Object decorateContainer(final Factory factory, final ClassLoader loader, final Field field) {
		final WebElement wrappedElement = proxyForLocator(loader, this.factory.createLocator(field));
		final Object page = createFactory(factory, field, wrappedElement);

//		PageFactory.initElements(new ExtendedFieldDecorator(wrappedElement), page);

		return page;
	}

	private Object createFactory(Factory factory, Field field, WebElement wrappedElement) {
		return factory.create(field.getType(), wrappedElement);
	}
}
