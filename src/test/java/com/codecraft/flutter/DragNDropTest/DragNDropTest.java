package com.codecraft.flutter.DragNDropTest;

import com.codecraft.flutter.Pages;
import com.codecraft.flutter.frameWork.TestBase;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DragNDropTest extends TestBase {


    @Test
    public void dragTestcase() throws InterruptedException {

        Pages.LandingPage().gotoDrag();

        Assert.assertTrue(Pages.DragNDropPage().reactDragAction());

        Assert.assertTrue(Pages.DragNDropPage().pythonDragAction());

        Assert.assertTrue(Pages.DragNDropPage().phpDragAction());

        Assert.assertTrue(Pages.DragNDropPage().nodeDragAction());

        Assert.assertTrue(Pages.DragNDropPage().javaDragAction());

    }

}
