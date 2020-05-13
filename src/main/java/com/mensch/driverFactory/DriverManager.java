package com.mensch.driverFactory;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    protected WebDriver driver;
    protected abstract void createDriver(String udid, String appiumPort);

    public void quitDriver() {
        if (null != driver) {
            try {
                driver.quit();
            } catch (Throwable ex) {
                System.out.println("Driver error preventing from Quitting.");
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
