package com.mensch.base;

import com.mensch.def.Constants;
import com.mensch.def.Enums;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;


import java.net.MalformedURLException;
import java.net.URL;

public class Portal {

    WebDriver driver;

    private final long timeoutSeconds;

    Portal(long timeout) {
        this.timeoutSeconds = timeout;
    }

    public WebDriver openDriver(Enums.BrowserType browser, String udid, String appiumPort) {

        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();

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
                capabilities.setCapability("udid", udid);
                //capabilities.setCapability("systemPort", "8201");
                capabilities.setCapability("appPackage", "com.android.calculator2");
                capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
                break;
        }

        //Create RemoteWebDriver instance and connect to the Appium server
        //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
        //driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:"+appiumPort+"/wd/hub"), capabilities);
        }
        catch (Exception e) {
            System.out.println("Driver error");
            Assert.fail();
        }
        return driver;
    }

    public void tearDown(){
        try {
            driver.quit();
        } catch (Throwable ex) {
            System.out.println("Driver error preventing from Quitting.");
            ex.printStackTrace();
        }

    }


    public long getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public WebDriver getDriver() {
        return driver;
    }


}
