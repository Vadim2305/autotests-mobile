package com.mensch.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.annotations.Test;


public class Calculator {
    WebDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException{
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability("VERSION", "4.4.2");
        capabilities.setCapability("deviceName","Asus_M2");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("automationName", "UiAutomator1");


        //capabilities.setCapability("appPackage", "com.android.calculator2");
        //capabilities.setCapability("appActivity","com.asus.calculator.Calculator");

        capabilities.setCapability("appPackage", "com.asus.calculator");
        capabilities.setCapability("appActivity","com.asus.calculator.Calculator");
//Create RemoteWebDriver instance and connect to the Appium server
        //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
        //driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        AndroidDriver<AndroidElement> driver = null;
        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCal() throws Exception {
        //locate the Text on the calculator by using By.name()
        WebElement two=driver.findElement(By.id("com.asus.calculator:id/digit2"));
        two.click();
        WebElement plus=driver.findElement(By.id("com.asus.calculator:id/plus"));
        plus.click();
        WebElement four=driver.findElement(By.id("com.asus.calculator:id/digit4"));
        four.click();
        WebElement equalTo=driver.findElement(By.id("com.asus.calculator:id/equal"));
        equalTo.click();
        //locate the edit box of the calculator by using By.tagName()
        WebElement results=driver.findElement(By.tagName("EditText"));
        //Check the calculated value on the edit box
        assert results.getText().equals("6"):"Actual value is : "+results.getText()+" did not match with expected value: 6";

    }

    @AfterClass
    public void teardown(){
        //close the app
        driver.quit();
    }
}