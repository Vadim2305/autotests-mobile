package com.mensch.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
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

    enum BrowserType {ANDROID_MOBILE, ANDROID_EMULATOR, IOS_MOBILE, IOS_EMULATOR}

    //BrowserType browser = BrowserType.ANDROID_MOBILE;
    BrowserType browser = BrowserType.ANDROID_EMULATOR;

    @BeforeClass
    public void setUp() throws MalformedURLException{


        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("BROWSER_NAME", "Android");

        switch (browser){
            case ANDROID_MOBILE:
                capabilities.setCapability("BROWSER_NAME", "Android");
                capabilities.setCapability("VERSION", "4.4.2");
                capabilities.setCapability("deviceName","Asus_M2");
                capabilities.setCapability("platformName","Android");
                capabilities.setCapability("automationName", "UiAutomator1");
                capabilities.setCapability("udid", "JBAXB765K838V56");
                capabilities.setCapability("appPackage", "com.asus.calculator");
                capabilities.setCapability("appActivity","com.asus.calculator.Calculator");
                break;
            case ANDROID_EMULATOR:
                capabilities.setCapability("BROWSER_NAME", "Android_Emu");
                capabilities.setCapability("VERSION", "4.4.2");
                capabilities.setCapability("deviceName","Asus_M2_Emu");
                capabilities.setCapability("platformName","Android");
                capabilities.setCapability("automationName", "UiAutomator1");
                capabilities.setCapability("udid", "emulator-5554");
                capabilities.setCapability("appPackage", "com.android.calculator2");
                capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
                break;
        }

        //Create RemoteWebDriver instance and connect to the Appium server
        //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
        //driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @Test
    public void testCal() {

        String twoId = null;
        String plusId= null;
        String fourId= null;
        String fourXP= null;
        String equalToId= null;
        String resultsId= null;



        switch (browser) {
            case ANDROID_MOBILE:
                twoId = "com.asus.calculator:id/digit2";
                plusId = "com.asus.calculator:id/plus";
                fourId = "com.asus.calculator:id/digit4";
                fourXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.view.ViewGroup[2]/android.widget.Button[9]";
                equalToId = "com.asus.calculator:id/equal";
                resultsId= "com.asus.calculator:id/resultEditTextID";
                break;
            case ANDROID_EMULATOR:
                twoId = "com.android.calculator2:id/digit_2";
                plusId = "com.android.calculator2:id/op_add";
                fourId = "com.android.calculator2:id/digit_4";
                fourXP = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.view.ViewGroup[1]/android.widget.Button[4]";
                equalToId = "com.android.calculator2:id/eq";
                resultsId= "com.android.calculator2:id/result";
                break;
        }

        //MobileElement two = (MobileElement) driver.findElement(By.id("com.android.calculator2:id/digit_2"));
        MobileElement two = (MobileElement) driver.findElement(By.id(twoId));
        two.click();

        MobileElement plus=(MobileElement) driver.findElement(By.id(plusId));
        plus.click();

        //MobileElement four=(MobileElement) driver.findElement(By.id("com.asus.calculator:id/digit4"));
        MobileElement four=(MobileElement) driver.findElement(By.xpath(fourXP));
        four.click();

        MobileElement equalTo=(MobileElement)driver.findElement(By.id(equalToId));
        equalTo.click();

        //locate the edit box of the calculator by using By.tagName()
        MobileElement results=(MobileElement)driver.findElement(By.id(resultsId));
        //Check the calculated value on the edit box
        assert results.getText().equals("6"):"Actual value is : "+results.getText()+" did not match with expected value: 6";

    }

    @AfterClass
    public void teardown(){
        //close the app
        driver.quit();
    }
}