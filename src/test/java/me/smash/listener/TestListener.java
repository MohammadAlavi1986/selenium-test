package me.smash.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {

  @Override
  public void onTestStart(ITestResult result) {
    Reporter.log("onTestStart: " + result.getName());
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    Reporter.log("onTestSuccess: " + result.getName());
  }

  @Override
  public void onTestFailure(ITestResult result) {
    Reporter.log("onTestFailure: " + result.getName());
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    Reporter.log("onTestSkipped: " + result.getName());
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    Reporter.log("onTestFailedButWithinSuccessPercentage: " + result.getName());
  }

  @Override
  public void onStart(ITestContext context) {
    Reporter.log("onStart: " + context.getName());
  }

  @Override
  public void onFinish(ITestContext context) {
    Reporter.log("onFinish: " + context.getName());
  }
}
