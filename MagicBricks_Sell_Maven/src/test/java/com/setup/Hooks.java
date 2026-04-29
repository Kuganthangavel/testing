package com.setup; 
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
 
 
public class Hooks extends BaseStep{

	public WebDriver driver;
	Properties property;



//	public void clouser() {
//
//		driver.quit();
//
//	}



	@Parameters("browser")          //for crossBrowsing
	@BeforeMethod()
	public void setUp(String browser) {      //for crossBrowsing
//	public void setUp() {
		driver=openBrowserAndApplication(browser);    
		//for crossBrowsing
//		driver = openBrowserAndApplication(driver);
	}
//	public WebDriver openBrowserAndApplication() {
//		 
//        property = PropertyReader.loadPropertiesFile();
// 
//        String browser = property.getProperty("browser").trim();
// 
//        switch (browser.toLowerCase()) {
// 
//        case "edge":
//
//            driver = new EdgeDriver();
//
//            break;
// 
//        case "chrome":
//
//            driver = new ChromeDriver();
//
//            break;
// 
//        case "safari":
//
//            driver = new SafariDriver();
//
//            break;
// 
//        default:
//
//            System.out.println("❌ Invalid browser in config. Launching Chrome by default.");
//
//            driver = new ChromeDriver();
//
//        }
// 
//        driver.manage().window().maximize();
//
////        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
// 
//        driver.get(property.getProperty("baseUrl").trim());
// 
//        return driver;
//
//    }

}

 