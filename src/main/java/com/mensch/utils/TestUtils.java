package com.mensch.utils;

import com.mensch.base.TestBase;
import com.mensch.def.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;


import java.awt.*;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TestUtils extends TestBase {

	private TestUtils() {
		throw new AssertionError("Suppress default constructor for noninstantiability");
	}


	public List<WebElement> findElements(final By by) {
		return findElements(by, Constants.DEFAULT_TIMEOUT);
	}

	public List<WebElement> findElements(final By by, long timeoutSeconds) {
		driver.findElements(by);
		List<WebElement> list = logTime(by.toString(), () -> driver.findElements(by));

		//Highlight
		if (driver != null) {
			for (WebElement elem : list) {
				highlightElement(elem);
			}
		}
		return list;
	}


	public void dragAndDropToWebelement(WebElement sourceElement, WebElement targetElement) {
		new Actions(driver).dragAndDrop(sourceElement, targetElement).perform();
	}

	public void dragAndDropByCoordinates(WebElement sourceElement, int xOffset, int yOffset) {
		new Actions(driver).dragAndDropBy(sourceElement, xOffset, yOffset).perform();
	}


	public WebElement findElement(final By by) {
        WebElement elem = logTime(by.toString(), () -> driver.findElement(by));
		highlightElement(elem);
		return elem;
	}

	public void clickInvisible(final By by) {
		logTime(by.toString(), () -> clickInvisible(findElement(by)));
	}

	public  Void clickInvisible(final WebElement webElement) {
		highlightElement(webElement);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", webElement);
		return null;
	}

	public void clearAndType(WebElement webElement, String text) {
		webElement.clear();
		webElement.sendKeys(text);
	}

	public void enterStartMinutes(WebElement webElement){
		for(int i=0; i<11; i++){
			clickInvisible(webElement);
		}
	}

	public void enterEndMinutes(WebElement webElement){
		for(int i=0; i<12; i++){
			clickInvisible(webElement);
		}
	}


	public void highlightElement(WebElement webElement) {
		//Highlight
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", webElement);
		}
	}

	public void clear(WebElement webElement) {
		webElement.clear();
	}

	public WebElement getParentNode(WebElement node) {
		return (WebElement) ((JavascriptExecutor)driver).executeScript("return arguments[0].parentNode;", node);
	}



	/**
	 * For debugging purposes
	 */
	public <T, E extends Exception> T logTime(String desc, QueryUtilsExecutor<T, E> executor) throws E {
		long startTime = System.nanoTime();
		T result = executor.execute();
		long time = System.nanoTime() - startTime;
		System.out.println(String.format(Locale.FRANCE, "%s\t-\t%,10d * 10^(-6) seconds\t\t( %s )", getMethodName(4), time / 1000, desc));
		return result;
	}

	public interface QueryUtilsExecutor<T, E extends Exception> {
		T execute() throws E;
	}

	private String getMethodName(final int depth) {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName1 = ste[Math.min(depth, ste.length - 1)].getMethodName();
		if (methodName1.equals("waitForAndHide")) {
			methodName1 = ste[Math.min(depth + 1, ste.length - 1)].getMethodName();
		}
		String methodName2 = ste[Math.min(depth - 1, ste.length - 2)].getMethodName();
		return String.format("%35s\t-\t%20s", methodName1, methodName2);
	}

	public void scrollToTop() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
	}

	public void scrollToBottom() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}


	public void scrollToElement(String xpath) {

		WebElement element = findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollToRightElement(String xpath) {

		WebElement element = findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft = arguments[0].offsetWidth",element);
	}

	public void setWindowSize(int width, int height) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	public void setWindowMaxSize() {
		driver.manage().window().maximize();
	}

	public void setWindowMaxSizeParameters() {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int width = (int) toolkit.getScreenSize().getWidth();
		int height = (int) toolkit.getScreenSize().getHeight();

		System.out.println("Max resolution: "+width+" X "+height);

		driver.manage().window().setSize(new Dimension(width, height));


	}

}
