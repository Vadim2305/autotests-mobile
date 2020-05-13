package com.mensch.driverFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.URL;

public class AndroidDriverManager extends DriverManager {

    @Override
    public void createDriver(String udid, String appiumPort) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability("VERSION", "4.4.2");
        capabilities.setCapability("deviceName","Asus_M2");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("automationName", "UiAutomator1");
        //capabilities.setCapability("udid", "JBAXB765K838V56");
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("appPackage", "com.asus.calculator");
        capabilities.setCapability("appActivity","com.asus.calculator.Calculator");

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:"+appiumPort+"/wd/hub"), capabilities);
        }
        catch (Exception e) {
            System.out.println("Driver error");
            Assert.fail();
        }
    }
}
