package com.mensch.base;

import com.mensch.def.Constants;
import com.mensch.def.Enums;
import com.mensch.driverFactory.DriverManager;
import com.mensch.driverFactory.DriverManagerFactory;
import com.mensch.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;

public class TestBase {

    protected WebDriver driver;
    DriverManager driverManager;


    @BeforeTest
    public void beforeTest() {
        driverManager = DriverManagerFactory.getManager(Enums.BrowserType.ANDROID_EMULATOR);
    }

    @AfterMethod
    public void afterMethod() {
        driverManager.quitDriver();
    }

    @BeforeMethod(onlyForGroups = { "Dev1" })
    public void noLogin1(ITestContext ctx, Method method, ITestResult testResult) {

        driver = driverManager.getDriver("emulator-5554", "10001");  //("systemPort", "8201")

    }

    @BeforeMethod(onlyForGroups = { "Dev2" })
    public void noLogin2(ITestContext ctx, Method method, ITestResult testResult) {

        driver = driverManager.getDriver("emulator-5556", "10002");

    }

    @AfterMethod(groups = { "system-include" })
    public void afterTest(ITestResult testResult, ITestContext ctx) {

        driverManager.quitDriver();

    }


}
