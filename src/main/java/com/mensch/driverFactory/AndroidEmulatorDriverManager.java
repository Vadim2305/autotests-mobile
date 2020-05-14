package com.mensch.driverFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.URL;

@Log4j2
public class AndroidEmulatorDriverManager extends DriverManager {

    @Override
    public void createDriver(String udid, String appiumPort) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("BROWSER_NAME", "Android_Emu");
        capabilities.setCapability("VERSION", "4.4.2");
        capabilities.setCapability("deviceName","Asus_M2_Emu");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("automationName", "UiAutomator1");
        capabilities.setCapability("udid", udid);
        //capabilities.setCapability("systemPort", "8201");
        capabilities.setCapability("appPackage", "com.android.calculator2");
        capabilities.setCapability("appActivity","com.android.calculator2.Calculator");

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:"+appiumPort+"/wd/hub"), capabilities);
        }
        catch (Exception e) {
            log.error("Driver error");
            Assert.fail();
        }
    }
}
