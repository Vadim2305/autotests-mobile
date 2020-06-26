package com.mensch.driverFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.URL;

@Log4j2
public class IOSDriverManager extends DriverManager {

    @Override
    public void createDriver(String udid, String appiumPort) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("BROWSER_NAME", "IOS");
        capabilities.setCapability("VERSION", "4.4.2");
        capabilities.setCapability("deviceName","Iphone");
        capabilities.setCapability("platformName","IOS");
        capabilities.setCapability("automationName", "UiAutomator1");
        //capabilities.setCapability("udid", "JBAXB765K838V56");
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("appPackage", "com.asus.calculator");
        capabilities.setCapability("appActivity","com.asus.calculator.Calculator");

        try {
            driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:"+appiumPort+"/wd/hub"), capabilities);
        }
        catch (Exception e) {
            log.error("Driver error");
            Assert.fail(e.toString());
        }
    }
}
