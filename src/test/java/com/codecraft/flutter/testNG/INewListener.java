package com.codecraft.flutter.testNG;

import org.testng.IAnnotationTransformer;
import org.testng.IMethodInterceptor;
import org.testng.ITestListener;

/*
    @desc      This interface will extend all listeners that are used in project
    @author    Ostan Dsouza
    @Date      20/03/2020
 */

public interface INewListener extends ITestListener, IAnnotationTransformer, IMethodInterceptor {
}
