package com.codecraft.flutter.testNG;

import com.codecraft.flutter.frameWork.TestBase;
import io.qameta.allure.Attachment;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.IMethodInstance;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

/*
    @desc      This class is uses
                a)IMethodInterceptor to add priority to testcases at run time
                b)IAnnotationTransformer to add retry label at run time to all tc
                c)ITestListener will added logs and screenshots to allure report.
                d)Updates zephyr test cycle based on flag
    @author    Ostan Dsouza
    @Date      02/06/2020
 */

public class TestListener extends TestBase implements INewListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);


    private  String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Failure in method {0}", type = "image/png")
    public void takeScreenShotAddOn() throws WebDriverException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        File scrFile = ((TakesScreenshot) getDriverForFramework()).getScreenshotAs(OutputType.FILE);
        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
            File destFile = new File((String)   "./Screenshots/" + formater.format(calendar.getTime()) + ".png");
            FileUtils.copyFile(scrFile, destFile);
            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Attachment(value = "Failure in method {0}", type = "image/png")
    public byte[] takeScreenShot() throws WebDriverException {
        byte[] src = ((TakesScreenshot) getDriverForFramework()).getScreenshotAs(OutputType.BYTES);
        Reporter.log("<a title ='click to download image' href='" + src.toString() + "' download>" +
                " <img src='" + src.toString() + "' height='100' width='100' /> </a>");
        return src;
    }



    @Attachment(value = "{0}", type = "text/plain")
    public  String saveTextLogs(String message) {
        return message;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.debug("Test Method Failed " + getTestMethodName(iTestResult));

        //Allure ScreenShotRobot and SaveTestLog

        try
        {
            takeScreenShot();
            logger.info("Screenshot captured for test case: " + getTestMethodName(iTestResult));
        } catch (Exception e) {
            logger.error("Failed to capture screenshot "+e);
        }


        saveTextLogs(getTestMethodName(iTestResult) + " failed and screenshot taken!");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.info("Skipped Test: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test Case Passed: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info("Test Case Started: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.info("Test Failed within success percentage: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onStart(ITestContext iTestResult) {
        logger.info("Started: ");
    }

    @Override
    public void onFinish(ITestContext iTestResult) {
        System.out.println("Finished: ");
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        Comparator<IMethodInstance> comparator = new Comparator<IMethodInstance>() {
            private int getLineNo(IMethodInstance mi) {
                int result = 0;

                String methodName = mi.getMethod().getConstructorOrMethod().getMethod().getName();
                String className  = mi.getMethod().getConstructorOrMethod().getDeclaringClass().getCanonicalName();
                ClassPool pool    = ClassPool.getDefault();

                try {
                    CtClass cc        = pool.get(className);
                    CtMethod ctMethod = cc.getDeclaredMethod(methodName);
                    result            = ctMethod.getMethodInfo().getLineNumber(0);
                } catch (NotFoundException | javassist.NotFoundException e) {
                    e.printStackTrace();
                }

                return result;
            }

            public int compare(IMethodInstance m1, IMethodInstance m2) {
                return getLineNo(m1) - getLineNo(m2);
            }
        };

        IMethodInstance[] array = methods.toArray(new IMethodInstance[methods.size()]);
        Arrays.sort(array, comparator);
        return Arrays.asList(array);
    }
}