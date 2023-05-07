package com.codecraft.flutter.testNG;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/*
    @desc      This class is uses IRetryAnalyzer for retry logic after test failures
    @author    Ostan Dsouza
    @Date      21/10/2019
 */

public class RetryAnalyzer implements IRetryAnalyzer{

    int counter = 0;
    int retryLimit;
    String retry_Limit = System.getProperty("RetryCount");

    public boolean retry(ITestResult result) {
        if(retry_Limit == null){
            retryLimit = 0;
        }
        else{
            retryLimit = Integer.parseInt(retry_Limit);     //change retry count here
        }
        if(counter < retryLimit){
            counter++;
            return true;
        }
        return false;
    }

}
