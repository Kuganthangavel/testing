package com.utils;
 
import java.io.File;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
public class ExtentManager {
 
	public static ExtentReports generateExtentReport() {
 
		ExtentReports extentReports = new ExtentReports();
 
		String reportPath = System.getProperty("user.dir") + "\\target\\ExtentReport\\extentReport.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(new File(reportPath));
 
		// FIX: Directly use config() — ExtentSparkReporterConfig class not needed
		sparkReporter.config().setDocumentTitle("MagicBricks_SellAgent");
		sparkReporter.config().setReportName("TN Test Results");
 
		extentReports.attachReporter(sparkReporter);
 
		extentReports.setSystemInfo("Username", "Kugan");
		extentReports.setSystemInfo("Selenium Version", "4.38.0");
		extentReports.setSystemInfo("Operating System", "Windows 11");
 
		return extentReports;
	}
}