package com.mensch.utils;

import com.mensch.base.TestBase;
import com.mensch.def.Constants;
import com.mensch.def.Enums;
import com.mensch.driverFactory.DriverManagerFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;


import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TestUtils {

	private WebDriver driver;

	public TestUtils(WebDriver driver) {
		this.driver = driver;
	}

	private TestUtils() {
		throw new AssertionError("Suppress default constructor for noninstantiability");
	}

	public static WebDriver getDriverFromElement(WebElement element) {
		WebDriver driver = null;
		try {
			Field f = element.getClass().getDeclaredField("parent");
			f.setAccessible(true);
			Object o = f.get(element);
			if ( o instanceof WebDriver) {
				driver = (WebDriver)o;
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return driver;
	}

	public static List<WebElement> findElements(final By by, WebDriver driver) {
		return findElements(by, Constants.DEFAULT_TIMEOUT, driver);
	}

	public static List<WebElement> findElements(final By by, long timeoutSeconds, WebDriver driver) {
		driver.findElements(by);
		List<WebElement> list = logTime(by.toString(), () -> driver.findElements(by));

		//Highlight
		if (driver != null) {
			for (WebElement elem : list) {
				//highlightElement(elem, driver);
			}
		}
		return list;
	}


	public static void dragAndDropToWebelement(WebElement sourceElement, WebElement targetElement, WebDriver driver) {
		new Actions(driver).dragAndDrop(sourceElement, targetElement).perform();
	}

	public static void dragAndDropByCoordinates(WebElement sourceElement, int xOffset, int yOffset, WebDriver driver) {
		new Actions(driver).dragAndDropBy(sourceElement, xOffset, yOffset).perform();
	}


	public static WebElement findElement(final By by, WebDriver driver) {
        WebElement elem = logTime(by.toString(), () -> driver.findElement(by));
		//highlightElement(elem, driver);
		return elem;
	}

	public static void clickInvisible(final By by, WebDriver driver) {
		logTime(by.toString(), () -> clickInvisible(findElement(by, driver), driver));
	}

	public static Void clickInvisible(final WebElement webElement, WebDriver driver) {
		//highlightElement(webElement, driver);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", webElement);
		return null;
	}

	public static void clearAndType(WebElement webElement, String text) {
		webElement.clear();
		webElement.sendKeys(text);
	}

	public static void enterStartMinutes(WebElement webElement, WebDriver driver){
		for(int i=0; i<11; i++){
			clickInvisible(webElement, driver);
		}
	}

	public static void enterEndMinutes(WebElement webElement, WebDriver driver){
		for(int i=0; i<12; i++){
			clickInvisible(webElement, driver);
		}
	}


	public static void highlightElement(WebElement webElement, WebDriver driver) {

		//Highlight
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", webElement);
		}
	}

	public static void clear(WebElement webElement) {
		webElement.clear();
	}

	public static WebElement getParentNode(WebElement node, WebDriver driver) {
		return (WebElement) ((JavascriptExecutor)driver).executeScript("return arguments[0].parentNode;", node);
	}


	/**
	 * For debugging purposes
	 */
	public static <T, E extends Exception> T logTime(String desc, QueryUtilsExecutor<T, E> executor) throws E {
		long startTime = System.nanoTime();
		T result = executor.execute();
		long time = System.nanoTime() - startTime;
		System.out.println(String.format(Locale.FRANCE, "%s\t-\t%,10d * 10^(-6) seconds\t\t( %s )", getMethodName(4), time / 1000, desc));
		return result;
	}

	public interface QueryUtilsExecutor<T, E extends Exception> {
		T execute() throws E;
	}

	private static String getMethodName(final int depth) {
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName1 = ste[Math.min(depth, ste.length - 1)].getMethodName();
		if (methodName1.equals("waitForAndHide")) {
			methodName1 = ste[Math.min(depth + 1, ste.length - 1)].getMethodName();
		}
		String methodName2 = ste[Math.min(depth - 1, ste.length - 2)].getMethodName();
		return String.format("%35s\t-\t%20s", methodName1, methodName2);
	}

	public static void scrollToTop(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
	}

	public static void scrollToBottom(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void scrollToElement(WebElement element, WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}


	public static void scrollToElement(String xpath, WebDriver driver) {

		WebElement element = findElement(By.xpath(xpath), driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public static void scrollToRightElement(String xpath, WebDriver driver) {

		WebElement element = findElement(By.xpath(xpath), driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft = arguments[0].offsetWidth",element);
	}

	public static void setWindowSize(int width, int height, WebDriver driver) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	public static void setWindowMaxSize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public static void setWindowMaxSizeParameters(WebDriver driver) {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int width = (int) toolkit.getScreenSize().getWidth();
		int height = (int) toolkit.getScreenSize().getHeight();

		System.out.println("Max resolution: "+width+" X "+height);

		driver.manage().window().setSize(new Dimension(width, height));


	}

}
