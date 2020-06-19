package com.mensch.sanity;

import com.mensch.base.TestBase;
import com.mensch.decorator.elements.Button;
import com.mensch.decorator.elements.TextField;
import com.mensch.utils.WaitUtils;
import io.appium.java_client.MobileElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;


@Log4j2
public class Mensch_Login extends TestBase {

    WaitUtils waitUtils;
    static final String emailXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.EditText";
    static final String passXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.EditText";
    static final String loginXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.Button";
    static final String playBtnXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.Button[2]";

    @FindBy(xpath = emailXP)
    public static TextField emailField;

    @FindBy(xpath = passXP)
    public static TextField passField;

    @FindBy(xpath = loginXP)
    public static Button loginBtn;



    @Test(groups = {"sanity-include", "system-include", "Dev1"})
    public void testLogin() throws InterruptedException {

        log.info("testLogin");

        MobileElement email = (MobileElement) driver.findElement(By.xpath(emailXP));
        email.sendKeys("vadim.po@mensch.io");

        MobileElement pass=(MobileElement) driver.findElement(By.xpath(passXP));
        pass.sendKeys("Raswd123");

        MobileElement login=(MobileElement) driver.findElement(By.xpath(loginXP));
        login.click();

/*        emailField.waitAndType("vadim.po@mensch.io");
        passField.waitAndType("Raswd123");
        loginBtn.waitAndClick();*/

        Thread.sleep(8000);

        MobileElement playBtn=(MobileElement)driver.findElement(By.xpath(playBtnXP));

    }

}