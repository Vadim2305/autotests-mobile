package com.mensch.sanity;

import com.mensch.base.Portal;
import com.mensch.base.TestBase;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

//import org.openqa.selenium.remote.CapabilityType;


public class Test_Calculator_2 extends TestBase {

    @Test(groups = {"sanity-include", "system-include", "Dev2"})
    public void testCal() {

        String twoId = null;
        String threeId = null;
        String plusId= null;
        String fourId= null;
        String fourXP= null;
        String equalToId= null;
        String resultsId= null;


/*        switch (Portal.INSTANCE.getBrowser()) {
            case ANDROID_MOBILE:
                twoId = "com.asus.calculator:id/digit2";
                threeId = "com.asus.calculator:id/digit3";
                plusId = "com.asus.calculator:id/plus";
                fourId = "com.asus.calculator:id/digit4";
                fourXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.view.ViewGroup[2]/android.widget.Button[9]";
                equalToId = "com.asus.calculator:id/equal";
                resultsId= "com.asus.calculator:id/resultEditTextID";
                break;
            case ANDROID_EMULATOR:*/
                twoId = "com.android.calculator2:id/digit_2";
                threeId = "com.android.calculator2:id/digit_3";
                plusId = "com.android.calculator2:id/op_add";
                fourId = "com.android.calculator2:id/digit_4";
                fourXP = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.view.ViewGroup[1]/android.widget.Button[4]";
                equalToId = "com.android.calculator2:id/eq";
                resultsId= "com.android.calculator2:id/result";



        //MobileElement two = (MobileElement) driver.findElement(By.id("com.android.calculator2:id/digit_2"));
        MobileElement three = (MobileElement) driver.findElement(By.id(threeId));
        three.click();

        MobileElement plus=(MobileElement) driver.findElement(By.id(plusId));
        plus.click();

        //MobileElement four=(MobileElement) driver.findElement(By.id("com.asus.calculator:id/digit4"));
        MobileElement four=(MobileElement)driver.findElement(By.id(fourId));
        four.click();

        MobileElement equalTo=(MobileElement)driver.findElement(By.id(equalToId));
        equalTo.click();

        //locate the edit box of the calculator by using By.tagName()
        MobileElement results=(MobileElement)driver.findElement(By.id(resultsId));
        //Check the calculated value on the edit box
        assert results.getText().equals("7"):"Actual value is : "+results.getText()+" did not match with expected value: 7";

    }

}