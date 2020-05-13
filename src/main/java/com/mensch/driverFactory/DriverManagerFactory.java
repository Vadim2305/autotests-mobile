package com.mensch.driverFactory;

import com.mensch.def.Enums;

public class DriverManagerFactory {

    public static DriverManager getManager(Enums.BrowserType browser) {

        DriverManager driverManager;

        switch (browser) {
            case ANDROID_EMULATOR:
                driverManager = new AndroidEmulatorDriverManager();
                break;
            case ANDROID_MOBILE:
                driverManager = new AndroidDriverManager();
                break;
            case IOS_EMULATOR:
                driverManager = new IOSEmulatorDriverManager();
                break;
            case IOS_MOBILE:
                driverManager = new IOSDriverManager();
                break;
            default:
                driverManager = new AndroidEmulatorDriverManager();
                break;
        }
        return driverManager;

    }
}
