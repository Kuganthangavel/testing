package com.utils;

import java.io.File;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.io.FileHandler;

public class Screenshots {

    public static String takeScreenshot(WebDriver driver, String testName) {

        // Avoid NullPointerException

        if (driver == null) {

            System.out.println("⚠ Screenshot NOT taken because WebDriver is NULL");

            return null;

        }

        if (!(driver instanceof TakesScreenshot)) {

            System.out.println("⚠ Driver does not support taking screenshots");

            return null;

        }

        TakesScreenshot ts = (TakesScreenshot) driver;

        File src = ts.getScreenshotAs(OutputType.FILE);

        // Timestamp for uniqueness

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String folderPath = System.getProperty("user.dir") + "/target/ExtentReport/";

        // Ensure directory exists

        File folder = new File(folderPath);

        if (!folder.exists()) {

            folder.mkdirs();

        }

        String screenshotPath = folderPath + testName + "_" + timestamp + ".png";

        try {

            FileHandler.copy(src, new File(screenshotPath));

        } catch (IOException e) {

            e.printStackTrace();

        }

        return screenshotPath;

    }

}

 