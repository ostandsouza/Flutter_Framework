package com.codecraft.flutter.forms;

import com.codecraft.flutter.utils.Base;
import com.codecraft.flutter.utils.DriverFactory;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.time.Duration;

public class FormsNativePage extends Base {
    public FormsNativePageObjRepo fnc;

    private static final Logger logger = LogManager.getLogger(FormsNativePage.class.getName());

    public FormsNativePage() {
        super(DriverFactory.getDriverForFramework());
        fnc = new FormsNativePageObjRepo(driver);
    }

    @Step(value = "Selecting the date...{method}")
    public void dateSelection(){
        switchContext("NATIVE_APP").manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        fnc.switchToInputElement.click();
        fnc.editElement.sendKeys("01/21/1992");
        fnc.okElement.click();
        switchContext("FLUTTER");
    }
}
