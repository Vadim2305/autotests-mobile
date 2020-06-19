package com.mensch.utils;

import com.google.common.base.Function;
import com.mensch.base.TestBase;
import com.mensch.decorator.elements.TextField;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.mensch.def.Constants;


public class WaitUtils extends TestBase {

	TestUtils testUtils;

	private int maxRetries=3;

	public final String ALL_SPINNERS = "//*[@id='loading-bar' or @class='bar-wrapper' or @class='splash' or @class='spinner-path' or @class='spinner-small' or @class='splash hideaway']";



	public void waitForSec (long sec) {
		testUtils.logTime("waitForSec",
				() -> {
					long seconds = sec * 1000;
					try {
						Thread.sleep(seconds);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return null;
				});
	}

	public void waitFor(String xp, int timeOut, TimeUnit timeUnit) {
		waitFor(By.xpath(xp), timeOut, timeUnit);
	}

	public void waitFor(String xp) {
		waitFor(By.xpath(xp), Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitFor(final By by) {
		waitFor(by, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	/**
	 *
	 * @param by
	 * @param timeOut
	 * @param timeUnit
	 * Differs with waitForClickable: does not check for element.isEnabled(), only for element.isDisplayed()
	 */
	public void waitFor(final By by, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime(by.toString(),
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.until(new Function<WebDriver, WebElement>() {
							@Override
							public WebElement apply(WebDriver driver) {
								WebElement element = driver.findElement(by);
								testUtils.highlightElement(element);
								return (element != null && element.isDisplayed()) ? element : null;
							}
						}));
	}

	/**
	 *
	 * @param element
	 * @param timeOut
	 * @param timeUnit
	 * Differs with waitForClickable: does not check for element.isEnabled(), only for element.isDisplayed()
	 */
	public void waitFor(WebElement element, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime("WaitFor",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.until(new Function<WebDriver, WebElement>() {
							@Override
							public WebElement apply(WebDriver driver) {
								return (element != null && element.isDisplayed()) ? element : null;
							}
						}));
	}

	public void waitForAnimationFinish(final String xPath) {
		waitForAnimationFinish(xPath, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitForAnimationFinish(final String xPath, int timeOut, TimeUnit timeUnit) {
		waitForAnimationFinish(By.xpath(xPath));
	}

	public void waitForAnimationFinish(final By by) {
		waitForAnimationFinish(by, Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
	}

	public void waitForAnimationFinish(final By by, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime(by.toString(),
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.until(steadinessOfElementLocated(by)));
	}


	public void waitForNewTab(int expectedTabsCount, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime("waitForNewTab",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.until(new Function<WebDriver, Boolean>() {
							@Override
							public Boolean apply(WebDriver driver) {
								ArrayList tabs = new ArrayList(driver.getWindowHandles());
								return (tabs.size() == expectedTabsCount);
							}
						}));
	}

	/**
	 *
	 * @param bys
	 * @param timeOut
	 * @param timeUnit
	 * @return index of found element in the list. In case no element was found = returns -1.
	 */
	public int waitForClickableOneFromList(final By[] bys, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime(bys.toString(),
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.until(new Function<WebDriver, WebElement>() {
							@Override
							public WebElement apply(WebDriver driver) {
								int i = 0;
								boolean found = false;
								WebElement element = null;
								while (i < bys.length && ! found) {
									try {
										element = driver.findElement(bys[i]);
										found = true;
									}
									catch (WebDriverException e) {
										i++;
									}
								}
								return (element != null && element.isDisplayed() && element.isEnabled()) ? element : null;
							}
						}));
		return (testUtils.logTime("Decoding list", () -> {
			boolean found = false;
			int i = 0;
			while (i < bys.length && !found) {
				try {
					driver.findElement(bys[i]);
					found = true;
				} catch (WebDriverException e) {
					i++;
				}
			}
			if (! found)
				i = -1;
			return i;
		})
		);

	}


	public void waitForClickable(final By by, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime(by.toString(),
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.until(new Function<WebDriver, WebElement>() {
					@Override
					public WebElement apply(WebDriver driver) {
						WebElement element = driver.findElement(by);
						testUtils.highlightElement(element);
						return (element != null && element.isDisplayed() && element.isEnabled()) ? element : null;
					}
				}));
	}

	public void waitForClickable(final By by, int timeOut) {
		waitForClickable(by, timeOut, TimeUnit.SECONDS);
	}

	public void waitForClickable(final By by) {
		waitForClickable(by, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitForClickable(String xpath) {
		waitForClickable(By.xpath(xpath));
	}

	public void waitForClickable(String xpath, int timeOut) {
		waitForClickable(By.xpath(xpath), timeOut);
	}

	public void waitForClickable(String xpath, int timeOut, TimeUnit timeUnit) {
		waitForClickable(By.xpath(xpath), timeOut, timeUnit);
	}

	public void waitAndClick(String xpath){
		waitAndClick(By.xpath(xpath), Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitAndClick(By by){
		waitAndClick(by, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitAndClick(By by, int timeOut){
		waitAndClick(by, timeOut, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitAndClick(String xpath, int timeOut){
		waitAndClick(By.xpath(xpath), timeOut, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitAndClick(final WebElement webElement, int timeOut){
		waitAndClick(webElement, timeOut, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitAndClick(final WebElement webElement){
		waitAndClick(webElement, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitAndClick(final WebElement webElement, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime("waitAndClick",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.until((Function<WebDriver, WebElement>) driver -> {
							testUtils.highlightElement(webElement);
							webElement.click();
							return (webElement);
						}));
	}

	public void waitAndClick(final By by, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime(by.toString(),
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, WebElement>() {
							@Override
							public WebElement apply(WebDriver driver) {
								WebElement element = driver.findElement(by);
								testUtils.highlightElement(element);
								element.click();
								return (element);
							}
						}));
	}

	public void waitAndClickInvisible(String xpath){
		waitAndClickInvisible(By.xpath(xpath), Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitAndClickInvisible(String xpath, int timeOut){
		waitAndClickInvisible(By.xpath(xpath), timeOut, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitAndClickInvisible(WebElement element){
		waitAndClickInvisible(element, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitAndClickInvisible(WebElement element, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime("",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, WebElement>() {
							@Override
							public WebElement apply(WebDriver driver) {
								testUtils.clickInvisible(element);
								return (element);
							}
						}));
	}


	public void waitAndClickInvisible(final By by, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime(by.toString(),
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, WebElement>() {
							@Override
							public WebElement apply(WebDriver driver) {
								WebElement element = driver.findElement(by);
								testUtils.clickInvisible(element);
								return (element);
							}
						}));
	}

	public void waitAndType(final By by, String text) {
		waitAndType(by, text, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}
	public void waitAndType(final By by, String text, int timeout) {
		waitAndType(by, text, timeout, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitAndType(final TextField element, String text) {
		waitAndType(element, text, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitAndType(final By by, String text, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime(by.toString(),
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, WebElement>() {
							@Override
							public WebElement apply(WebDriver driver) {
								WebElement element = driver.findElement(by);
								testUtils.highlightElement(element);
								element.clear();
								element.sendKeys(text);
								return (element);
							}
						}));
	}

	public void waitAndType(final TextField element, String text, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime("waitAndType",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, TextField>() {
							@Override
							public TextField apply(WebDriver driver) {
								element.clearAndType(text);
								return (element);
							}
						}));
	}

	public void waitAndExpand(final By by, final By optionsBy, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime("waitAndType",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, Boolean>() {
							@Override
							public Boolean apply(WebDriver driver) {
								WebElement element = driver.findElement(by);
								testUtils.highlightElement(element);
								element.click();
								List<WebElement>options = driver.findElements(optionsBy);
								return (options.size() != 0);
							}
						}));
	}

	public void waitAndExpand(final String xPath, final String optionsXPath, int timeOut, TimeUnit timeUnit) {
		waitAndExpand(By.xpath(xPath), By.xpath(optionsXPath), timeOut, timeUnit);
	}

	public void waitAndExpand(final String xPath, final String optionsXPath) {
		waitAndExpand(By.xpath(xPath), By.xpath(optionsXPath), Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}


	public void waitAndClickExpandWithAnimation(final By by, final By optionsBy) {
		waitAndClick(by);
		waitForAnimationFinish(optionsBy);
		waitAndClick(optionsBy);
	}

	public void waitAndClickExpandWithAnimation(final String xPath, final String optionsXPath) {
		waitAndClickExpandWithAnimation(By.xpath(xPath), By.xpath(optionsXPath));
	}



	private String getXPath(WebElement webElement) {
		String js = "function getPathTo(node) {" +
				"  var stack = [];" +
				"  while(node.parentNode !== null) {" +
				"    stack.unshift(node.tagName);" +
				"    node = node.parentNode;" +
				"  }" +
				"  return stack.join('/');" +
				"}" +
				"return getPathTo(arguments[0]);";
		return ((JavascriptExecutor) driver).executeScript(js, webElement).toString().toLowerCase();
	}

	public void waitTextAppears(String xp) {
		waitTextAppears(By.xpath(xp), Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitTextAppears(final By by, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime("WaitTextAppears",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until((Function<WebDriver, Boolean>) driver -> {
							WebElement element = driver.findElement(by);
							testUtils.highlightElement(element);
							return !element.getText().isEmpty();
						}));
	}

	public void waitItemDelete(String xp, int sizeExpected) {
		waitItemDelete(By.xpath(xp), sizeExpected, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitItemDelete(final By by, int sizeExpected, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime("checkItemDeleted",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until((Function<WebDriver, Boolean>) driver -> {
							List<WebElement> elements = driver.findElements(by);
							return (elements.size()==sizeExpected);
						}));
	}

	public void waitAllItems(String xp) {
		waitAllItems(By.xpath(xp), 10, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitAllItems(String xp, long deltaRecheck) {
		waitAllItems(By.xpath(xp), deltaRecheck, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT);
	}

	public void waitAllItems(final By by, long deltaRecheck, int timeOut, TimeUnit timeUnit) {
		testUtils.logTime("checkAllItems",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until((Function<WebDriver, Boolean>) driver -> {
							List<WebElement> elementsBefore = driver.findElements(by);
							waitForSec(deltaRecheck);
							List<WebElement> elementsAfter = driver.findElements(by);
							return (elementsAfter.size()-elementsBefore.size()==0);
						}));
	}



	public static ExpectedCondition<WebElement> steadinessOfElementLocated(final By locator) {
		return new ExpectedCondition<WebElement>() {

			private WebElement _element = null;
			private Point _location = null;

			@Override
			public WebElement apply(WebDriver driver) {
				if(_element == null) {
					try {
						_element = driver.findElement(locator);
					} catch (NoSuchElementException e) {
						return null;
					}
				}

				try {
					if(_element.isDisplayed()){
						Point location = _element.getLocation();
						if(location.equals(_location)/* && isOnTop(_element)*/) {
							return _element;
						}
						_location = location;
					}
				} catch (StaleElementReferenceException e) {
					_element = null;
				}

				return null;
			}

			@Override
			public String toString() {
				return "steadiness of element located by " + locator;
			}
		};
	}

}
