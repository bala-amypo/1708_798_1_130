package com.example.demo.listener;

public class TestResultListener {
    // Simple placeholder class without TestNG dependencies
    public void onTestSuccess(String testName) {
        System.out.println("Test PASSED: " + testName);
    }

    public void onTestFailure(String testName, Throwable throwable) {
        System.out.println("Test FAILED: " + testName);
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }
}