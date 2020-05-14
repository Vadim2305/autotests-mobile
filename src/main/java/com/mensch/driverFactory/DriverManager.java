package com.mensch.driverFactory;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public abstract class DriverManager {

    protected WebDriver driver;
    protected abstract void createDriver(String udid, String appiumPort);

    public void quitDriver() {
        if (null != driver) {
            try {
                driver.quit();
            } catch (Throwable ex) {
                log.error("Driver error preventing from Quitting.");
                ex.printStackTrace();
            }
            driver = null;
        }

    }

    public WebDriver getDriver(String udid, String appiumPort) {
        if (null == driver) {
            createDriver(udid, appiumPort);
        }
        return driver;
    }
}
