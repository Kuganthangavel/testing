package com.listeners;
import java.lang.reflect.Field;
import org.openqa.selenium.WebDriver;
 
import org.testng.ITestContext;
 
import org.testng.ITestListener;
 
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
 
import com.aventstack.extentreports.ExtentTest;
 
import com.aventstack.extentreports.Status;
import com.utils.ExtentManager;
 
import com.utils.Screenshots;
public class MyListeners implements ITestListener {
    private ExtentReports extentReport;
 
    private ExtentTest extentTest;
    @Override
 
    public void onStart(ITestContext context) {
 
        extentReport = ExtentManager.generateExtentReport();
 
    }
    @Override
 
    public void onTestStart(ITestResult result) {
 
        extentTest = extentReport.createTest(result.getName());
 
    }
    @Override
 
    public void onTestSuccess(ITestResult result) {
 
        extentTest.log(Status.PASS, result.getName() + " passed.");
 
    }
    @Override
 
    public void onTestFailure(ITestResult result) {
 
        extentTest.log(Status.FAIL, result.getName() + " failed.");
 
        extentTest.log(Status.FAIL, result.getThrowable());
        WebDriver driver = getDriverFromTest(result);
        // Capture screenshot only if driver was found
 
        String screenshotPath = Screenshots.takeScreenshot(driver, result.getName());
 
        if (screenshotPath != null) {
 
            try {
 
                extentTest.addScreenCaptureFromPath(screenshotPath);
 
            } catch (Exception e) {
 
                e.printStackTrace();
 
            }
 
        }
 
    }
    @Override
 
    public void onTestSkipped(ITestResult result) {
 
        extentTest.log(Status.SKIP, result.getName() + " skipped.");
 
    }
    @Override
 
    public void onFinish(ITestContext context) {
 
        extentReport.flush();
 
    }
    // -----------------------------------------------------
 
    // Helper Method: Safely Extract WebDriver Using Reflection
 
    // -----------------------------------------------------
 
    private WebDriver getDriverFromTest(ITestResult result) {
 
        Object testInstance = result.getInstance();
 
        Class<?> clazz = testInstance.getClass();
        while (clazz != null) {
 
            try {
 
                Field field = clazz.getDeclaredField("driver");
 
                field.setAccessible(true);
 
                Object driverObj = field.get(testInstance);
                if (driverObj instanceof WebDriver) {
 
                    return (WebDriver) driverObj;
 
                }
 
                return null;
            } catch (NoSuchFieldException e) {
 
                clazz = clazz.getSuperclass();   // check parent class
 
            } catch (Exception e) {
 
                e.printStackTrace();
 
                return null;
 
            }
 
        }
        System.out.println("⚠ Could NOT find WebDriver field 'driver' in test class hierarchy.");
 
        return null;
 
    }
 
}