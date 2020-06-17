package com.mensch.sanity;

import com.mensch.base.TestBase;
import io.appium.java_client.MobileElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


@Log4j2
public class Mensch_Login extends TestBase {

    @Test(groups = {"sanity-include", "system-include", "Dev1"})
    public void testLogin() throws InterruptedException {

        log.info("testLogin");
        String emailXP = null;
        String passXP= null;
        String loginXP= null;
        String playBtnXP= null;



        emailXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.EditText";
        passXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.EditText";
        loginXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.Button";
        playBtnXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.Button[2]";


        MobileElement email = (MobileElement) driver.findElement(By.xpath(emailXP));
        email.sendKeys("vadim.po@mensch.io");

        MobileElement pass=(MobileElement) driver.findElement(By.xpath(passXP));
        pass.sendKeys("Raswd123");

        MobileElement login=(MobileElement) driver.findElement(By.xpath(loginXP));
        login.click();

        Thread.sleep(8000);

        MobileElement playBtn=(MobileElement)driver.findElement(By.xpath(playBtnXP));

    }

}