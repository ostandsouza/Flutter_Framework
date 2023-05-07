package com.codecraft.flutter.utils;

import io.appium.java_client.AppiumDriver;

public class DriverFactory {

    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

    public static AppiumDriver getDriverForFramework() {
        return driver.get();
    }

    public static void setDriverForFramework(AppiumDriver localdriver) {
        driver.set(localdriver);
    }
}
