package com.codecraft.flutter.testNG;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestNGMethod;

import java.util.List;

public class SuiteCreationListener implements ISuiteListener {



        private static long testCount = 0 ;
        private static List<ITestNGMethod> testMethods = null;

        public void setTestCount(long testCount){
            this.testCount = testCount;
        }

        public long getTestCount(){
            return testCount;
        }

        @Override
        public void onStart( ISuite suite){
            testMethods  = suite.getAllMethods();
            this.testCount=testMethods.size();

        }

        @Override
        public void onFinish( ISuite suite){

        }
}
