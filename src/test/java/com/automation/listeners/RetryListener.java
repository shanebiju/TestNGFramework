package com.automation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {
    int failureCount=0;
    int MAX_LIMIT=3;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(failureCount<MAX_LIMIT){
            failureCount++;
            return true;
        }
        return false;
    }
}
