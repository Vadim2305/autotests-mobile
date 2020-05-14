package com.mensch.sanity;

import com.mensch.base.Portal;
import com.mensch.base.TestBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import lombok.extern.log4j.Log4j2;

import java.net.MalformedURLException;
import java.net.URL;

//import org.openqa.selenium.remote.CapabilityType;

@Log4j2
public class Test_Calculator extends TestBase {

    @Test(groups = {"sanity-include", "system-include", "Dev1"})
    public void testCal() {

        log.info("test123");
        String twoId = null;
        String plusId= null;
        String fourId= null;
        String fourXP= null;
        String equalToId= null;
        String resultsId= null;


                twoId = "com.android.calculator2:id/digit_2";
                plusId = "com.android.calculator2:id/op_add";
                fourId = "com.android.calculator2:id/digit_4";
                fourXP = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.view.ViewGroup[1]/android.widget.Button[4]";
                equalToId = "com.android.calculator2:id/eq";
                resultsId= "com.android.calculator2:id/result";


        //MobileElement two = (MobileElement) driver.findElement(By.id("com.android.calculator2:id/digit_2"));
        MobileElement two = (MobileElement) driver.findElement(By.id(twoId));
        two.click();

        MobileElement plus=(MobileElement) driver.findElement(By.id(plusId));
        plus.click();

        //MobileElement four=(MobileElement) driver.findElement(By.id("com.asus.calculator:id/digit4"));
        MobileElement four=(MobileElement) driver.findElement(By.xpath(fourXP));
        four.click();

        MobileElement equalTo=(MobileElement)driver.findElement(By.id(equalToId));
        equalTo.click();

        //locate the edit box of the calculator by using By.tagName()
        MobileElement results=(MobileElement)driver.findElement(By.id(resultsId));
        //Check the calculated value on the edit box
        assert results.getText().equals("6"):"Actual value is : "+results.getText()+" did not match with expected value: 6";

    }

}