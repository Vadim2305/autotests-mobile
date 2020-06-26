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


public class WaitUtils {

	TestUtils testUtils;

	private WebDriver driver;
	private int maxRetries=3;

	public final String ALL_SPINNERS = "//*[@id='loading-bar' or @class='bar-wrapper' or @class='splash' or @class='spinner-path' or @class='spinner-small' or @class='splash hideaway']";

	public WaitUtils(WebDriver driver) {
		this.driver = driver;
	}

	public static void waitForSec (long sec) {
		TestUtils.logTime("waitForSec",
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

	public static void waitFor(String xp, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		waitFor(By.xpath(xp), timeOut, timeUnit, driver);
	}

	public static void waitFor(String xp, WebDriver driver) {
		waitFor(By.xpath(xp), Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitFor(final By by, WebDriver driver) {
		waitFor(by, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	/**
	 *
	 * @param by
	 * @param timeOut
	 * @param timeUnit
	 * Differs with waitForClickable: does not check for element.isEnabled(), only for element.isDisplayed()
	 */
	public static void waitFor(final By by, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime(by.toString(),
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.until(new Function<WebDriver, WebElement>() {
							@Override
							public WebElement apply(WebDriver driver) {
								WebElement element = driver.findElement(by);
								//TestUtils.highlightElement(element, driver);
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
	public static void waitFor(WebElement element, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime("WaitFor",
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

	public static void waitForAnimationFinish(final String xPath, WebDriver driver) {
		waitForAnimationFinish(xPath, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitForAnimationFinish(final String xPath, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		waitForAnimationFinish(By.xpath(xPath), driver);
	}

	public static void waitForAnimationFinish(final By by, WebDriver driver) {
		waitForAnimationFinish(by, Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS, driver);
	}

	public static void waitForAnimationFinish(final By by, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime(by.toString(),
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.until(steadinessOfElementLocated(by)));
	}


	public static void waitForNewTab(int expectedTabsCount, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime("waitForNewTab",
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
	public static int waitForClickableOneFromList(final By[] bys, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime(bys.toString(),
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
		return (TestUtils.logTime("Decoding list", () -> {
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


	public static void waitForClickable(final By by, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime(by.toString(),
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.until(new Function<WebDriver, WebElement>() {
					@Override
					public WebElement apply(WebDriver driver) {
						WebElement element = driver.findElement(by);
						//TestUtils.highlightElement(element, driver);
						return (element != null && element.isDisplayed() && element.isEnabled()) ? element : null;
					}
				}));
	}

	public static void waitForClickable(final By by, int timeOut, WebDriver driver) {
		waitForClickable(by, timeOut, TimeUnit.SECONDS, driver);
	}

	public static void waitForClickable(final By by, WebDriver driver) {
		waitForClickable(by, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitForClickable(String xpath, WebDriver driver) {
		waitForClickable(By.xpath(xpath), driver);
	}

	public static void waitForClickable(String xpath, int timeOut, WebDriver driver) {
		waitForClickable(By.xpath(xpath), timeOut, driver);
	}

	public static void waitForClickable(String xpath, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		waitForClickable(By.xpath(xpath), timeOut, timeUnit, driver);
	}

	public static void waitAndClick(String xpath, WebDriver driver){
		waitAndClick(By.xpath(xpath), Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitAndClick(By by, WebDriver driver){
		waitAndClick(by, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitAndClick(By by, int timeOut, WebDriver driver){
		waitAndClick(by, timeOut, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitAndClick(String xpath, int timeOut, WebDriver driver){
		waitAndClick(By.xpath(xpath), timeOut, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitAndClick(final WebElement webElement, int timeOut, WebDriver driver){
		waitAndClick(webElement, timeOut, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitAndClick(final WebElement webElement, WebDriver driver){
		waitAndClick(webElement, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitAndClick(final WebElement webElement, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime("waitAndClick",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.until(new Function<WebDriver, WebElement>() {
							@Override
							public WebElement apply(WebDriver driver) {
								//TestUtils.highlightElement(webElement, driver);
								webElement.click();
								return (webElement);
							}
						}));
	}

	public static void waitAndClick(final By by, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime(by.toString(),
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, WebElement>() {
							@Override
							public WebElement apply(WebDriver driver) {
								WebElement element = driver.findElement(by);
								//TestUtils.highlightElement(element, driver);
								element.click();
								return (element);
							}
						}));
	}

	public static void waitAndClickInvisible(String xpath, WebDriver driver){
		waitAndClickInvisible(By.xpath(xpath), Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitAndClickInvisible(String xpath, int timeOut, WebDriver driver){
		waitAndClickInvisible(By.xpath(xpath), timeOut, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitAndClickInvisible(WebElement element, WebDriver driver){
		waitAndClickInvisible(element, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitAndClickInvisible(WebElement element, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime("",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, WebElement>() {
							@Override
							public WebElement apply(WebDriver driver) {
								TestUtils.clickInvisible(element, driver);
								return (element);
							}
						}));
	}


	public static void waitAndClickInvisible(final By by, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime(by.toString(),
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, WebElement>() {
							@Override
							public WebElement apply(WebDriver driver) {
								WebElement element = driver.findElement(by);
								TestUtils.clickInvisible(element, driver);
								return (element);
							}
						}));
	}

	public static void waitAndType(final By by, String text, WebDriver driver) {
		waitAndType(by, text, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}
	public static void waitAndType(final By by, String text, int timeout, WebDriver driver) {
		waitAndType(by, text, timeout, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitAndType(final TextField element, String text, WebDriver driver) {
		waitAndType(element, text, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitAndType(final By by, String text, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime(by.toString(),
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, WebElement>() {
							@Override
							public WebElement apply(WebDriver driver) {
								WebElement element = driver.findElement(by);
								//TestUtils.highlightElement(element, driver);
								element.clear();
								element.sendKeys(text);
								return (element);
							}
						}));
	}

	public static void waitAndType(final TextField element, String text, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime("waitAndType",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, TextField>() {
							@Override
							public TextField apply(WebDriver driver) {
								element.clearAndType(text, driver);
								return (element);
							}
						}));
	}

	public static void waitAndExpand(final By by, final By optionsBy, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime("waitAndType",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, Boolean>() {
							@Override
							public Boolean apply(WebDriver driver) {
								WebElement element = driver.findElement(by);
								//TestUtils.highlightElement(element, driver);
								element.click();
								List<WebElement>options = driver.findElements(optionsBy);
								return (options.size() != 0);
							}
						}));
	}

	public static void waitAndExpand(final String xPath, final String optionsXPath, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		waitAndExpand(By.xpath(xPath), By.xpath(optionsXPath), timeOut, timeUnit, driver);
	}

	public static void waitAndExpand(final String xPath, final String optionsXPath, WebDriver driver) {
		waitAndExpand(By.xpath(xPath), By.xpath(optionsXPath), Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}


	public static void waitAndClickExpandWithAnimation(final By by, final By optionsBy, WebDriver driver) {
		waitAndClick(by, driver);
		waitForAnimationFinish(optionsBy, driver);
		waitAndClick(optionsBy, driver);
	}

	public static void waitAndClickExpandWithAnimation(final String xPath, final String optionsXPath, WebDriver driver) {
		waitAndClickExpandWithAnimation(By.xpath(xPath), By.xpath(optionsXPath), driver);
	}



	private static String getXPath(WebElement webElement, WebDriver driver) {
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

	public static void waitTextAppears(String xp, WebDriver driver) {
		waitTextAppears(By.xpath(xp), Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitTextAppears(final By by, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime("WaitTextAppears",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, Boolean>() {
							@Override
							public Boolean apply(WebDriver driver) {
								WebElement element = driver.findElement(by);
								//TestUtils.highlightElement(element, driver);
								return !element.getText().isEmpty();
							}
						}));
	}

	public static void waitItemDelete(String xp, int sizeExpected, WebDriver driver) {
		waitItemDelete(By.xpath(xp), sizeExpected, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitItemDelete(final By by, int sizeExpected, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime("checkItemDeleted",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, Boolean>() {
							@Override
							public Boolean apply(WebDriver driver) {
								List<WebElement> elements = driver.findElements(by);
								return (elements.size() == sizeExpected);
							}
						}));
	}

	public static void waitAllItems(String xp, WebDriver driver) {
		waitAllItems(By.xpath(xp), 10, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitAllItems(String xp, long deltaRecheck, WebDriver driver) {
		waitAllItems(By.xpath(xp), deltaRecheck, Constants.DEFAULT_TIMEOUT, Constants.DEFAULT_TIMEOUT_UNIT, driver);
	}

	public static void waitAllItems(final By by, long deltaRecheck, int timeOut, TimeUnit timeUnit, WebDriver driver) {
		TestUtils.logTime("checkAllItems",
				() -> new FluentWait<>(driver)
						.withTimeout(timeOut, timeUnit)
						.pollingEvery(Constants.DEFAULT_POLLING_INTERVAL, Constants.DEFAULT_POLLING_UNIT)
						.ignoring(WebDriverException.class)
						.ignoring(NoSuchElementException.class)
						.until(new Function<WebDriver, Boolean>() {
							@Override
							public Boolean apply(WebDriver driver) {
								List<WebElement> elementsBefore = driver.findElements(by);
								waitForSec(deltaRecheck);
								List<WebElement> elementsAfter = driver.findElements(by);
								return (elementsAfter.size() - elementsBefore.size() == 0);
							}
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
