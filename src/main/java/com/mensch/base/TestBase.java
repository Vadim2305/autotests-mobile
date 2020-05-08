package com.mensch.base;

import com.mensch.def.Constants;
import com.mensch.def.Enums;
import com.mensch.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestBase {

    protected WebDriver driver;


    public void initDrv(ITestContext ctx, Method method, ITestResult testResult, String udid, String appiumPort){
        //Enums.BrowserType browser = Enums.BrowserType.ANDROID_MOBILE;
        Enums.BrowserType browser = Enums.BrowserType.ANDROID_EMULATOR;

        Portal portal = new Portal(Constants.DEFAULT_IMPLICIT_WAIT_INTERVAL);
        driver = portal.openDriver(browser, udid, appiumPort);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loadPage();
    }

    @BeforeMethod(onlyForGroups = { "Dev1" })
    public void noLogin1(ITestContext ctx, Method method, ITestResult testResult) {

        initDrv(ctx, method, testResult, "emulator-5554", "10001"); //("systemPort", "8201")
    }

    @BeforeMethod(onlyForGroups = { "Dev2" })
    public void noLogin2(ITestContext ctx, Method method, ITestResult testResult) {

        initDrv(ctx, method, testResult, "emulator-5556", "10002");
    }

    @AfterMethod(groups = { "system-include" })
    public void afterTest(ITestResult testResult, ITestContext ctx) {

        tearDown();

    }

    public void tearDown(){
        try {
            driver.quit();
        } catch (Throwable ex) {
            System.out.println("Driver error preventing from Quitting.");
            ex.printStackTrace();
        }
    }
}
