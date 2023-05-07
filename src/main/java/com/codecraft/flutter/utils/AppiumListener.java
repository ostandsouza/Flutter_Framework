package com.codecraft.flutter.utils;

/*
    @desc      This class is uses AppiumWebDriverEventListener for logging event to dashboard
    @author    Ostan Dsouza
    @Date      26/02/2023
 */

import io.appium.java_client.proxy.MethodCallListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

public class AppiumListener implements MethodCallListener {

    private static final Logger logger = LogManager.getLogger(AppiumListener.class.getName());

    @Override
    public Object onError(Object obj, Method method, Object[] args, Throwable e) throws Throwable {
        logger.info("Error occurred in the method "+method.getName()+" from class "+method.getDeclaringClass());
        logger.debug("Exception occurred during execution: " + e);
        return MethodCallListener.super.onError(obj,method,args,e);
    }
};
