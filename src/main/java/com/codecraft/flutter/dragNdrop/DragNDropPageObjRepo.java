package com.codecraft.flutter.dragNdrop;

import com.codecraft.flutter.utils.FlutterObject;
import com.codecraft.flutter.utils.kotlin.finder.FlutterElement;
import io.appium.java_client.AppiumDriver;

public class DragNDropPageObjRepo extends FlutterObject {

    public AppiumDriver driver;

    public DragNDropPageObjRepo(AppiumDriver driver){
        super(driver);
        this.driver=driver;
    }

    protected FlutterElement reactIconElement = find.bySemanticsLabel("ireactjs");

    protected FlutterElement reactElement = find.bySemanticsLabel("reactjs");

    protected FlutterElement pythonIconElement = find.bySemanticsLabel("ipython");

    protected FlutterElement pythonElement = find.bySemanticsLabel("python");

    protected FlutterElement phpIconElement = find.bySemanticsLabel("iphp");

    protected FlutterElement phpElement = find.bySemanticsLabel("php");

    protected FlutterElement javaIconElement = find.bySemanticsLabel("iJava");

    protected FlutterElement javaElement = find.bySemanticsLabel("Java");

    protected FlutterElement nodeIconElement = find.bySemanticsLabel("inode");

    protected FlutterElement nodeElement = find.bySemanticsLabel("node");
}
