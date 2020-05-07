package com.mensch.base;

import com.mensch.page.LoginPage;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestBase {

    private PageManager manager;

    public PageManager getManager() {
        return manager;
    }

    public void createManager() {
        manager = PageManager.getInstance();
    }

    @BeforeMethod(groups = { "system-include" })
    public void noLogin(ITestContext ctx, Method method, ITestResult testResult) {

        createManager();
        LoginPage loginPage = new LoginPage(Portal.INSTANCE.getDriver());
        loginPage.loadPage();
    }

    @AfterMethod(groups = { "system-include" })
    public void afterTest(ITestResult testResult, ITestContext ctx) {

        Portal.INSTANCE.tearDown();

    }
}
