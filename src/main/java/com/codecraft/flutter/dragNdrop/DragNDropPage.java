package com.codecraft.flutter.dragNdrop;

import com.codecraft.flutter.doubleTap.DoubleTapPageObjRepo;
import com.codecraft.flutter.utils.DriverFactory;
import com.codecraft.flutter.utils.FlutterBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class DragNDropPage extends FlutterBase {

    public DragNDropPageObjRepo dnd;

    public DragNDropPage(){
        super(DriverFactory.getDriverForFramework());
        dnd = new DragNDropPageObjRepo(driver);
    }


    public boolean reactDragAction() throws InterruptedException {
        DragGesture(dnd.reactIconElement, dnd.reactElement);
        Thread.sleep(1000);
        return !isVisisble(dnd.reactIconElement) && !isVisisble(dnd.reactElement);
    }

    public boolean pythonDragAction() throws InterruptedException {
        DragGesture(dnd.pythonIconElement, dnd.pythonElement);
        Thread.sleep(1000);
        return  !isVisisble(dnd.pythonIconElement) && !isVisisble(dnd.pythonElement);
    }

    public boolean phpDragAction() throws InterruptedException {
        DragGesture(dnd.phpIconElement, dnd.phpElement);
        Thread.sleep(1000);
        return  !isVisisble(dnd.phpIconElement) && !isVisisble(dnd.phpElement);
    }

    public boolean javaDragAction() throws InterruptedException {
        DragGesture(dnd.javaIconElement, dnd.javaElement);
        Thread.sleep(1000);
        return  !isVisisble(dnd.javaIconElement) && !isVisisble(dnd.javaElement);
    }

    public boolean nodeDragAction() throws InterruptedException {
        DragGesture(dnd.nodeIconElement, dnd.nodeElement);
        Thread.sleep(1000);
        return  !isVisisble(dnd.nodeIconElement) && !isVisisble(dnd.nodeElement);
    }
}
