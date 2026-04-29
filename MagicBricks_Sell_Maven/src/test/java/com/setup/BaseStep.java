package com.setup;
 
 
import java.time.Duration;

import java.util.Properties;
 
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Parameters;

import com.parameter.PropertyReader;
 
public class BaseStep {
 
    public static WebDriver driver;

    public Properties property;
    
  @Parameters("browser")           //for crossBrowsing
  public WebDriver openBrowserAndApplication(String browser) {           //for crossBrowsing
 
 
//    public WebDriver openBrowserAndApplication(WebDriver driver) {
 
        property = PropertyReader.loadPropertiesFile();
 
//        String browser = property.getProperty("browser").trim();
 
        switch (browser.toLowerCase()) {
 
        case "edge":

            driver = new EdgeDriver();

            break;
 
        case "chrome":

            driver = new ChromeDriver();

            break;
 
        case "safari":

            driver = new SafariDriver();

            break;
 
        default:

            System.out.println("❌ Invalid browser in config. Launching Chrome by default.");

            driver = new ChromeDriver();

        }
 
        driver.manage().window().maximize();

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
 
        driver.get(property.getProperty("baseUrl"));
 
        return driver;

    }

}
 
 