//package com.setup;
//package com.setup.BaseSteps;
//
//import io.github.bonigarcia.wdm.WebDriverManager; // optional
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//import java.time.Duration;
//
//public class SetupDriver {
//    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
//
//    public static void initDriver(String browser) {
//        WebDriver driver;
//        switch (browser.toLowerCase()) {
//            case "firefox":
//                WebDriverManager.firefoxdriver().setup(); // remove this line if using Selenium Manager
//                driver = new FirefoxDriver();
//                break;
//            case "edge":
//                WebDriverManager.edgedriver().setup(); // remove for Selenium Manager
//                driver = new EdgeDriver();
//                break;
//            case "chrome":
//            default:
//                WebDriverManager.chromedriver().setup(); // remove for Selenium Manager
//                ChromeOptions options = new ChromeOptions();
//                // options.addArguments("--headless=new"); // enable for headless runs/CI
//                options.addArguments("--remote-allow-origins=*");
//                driver = new ChromeDriver(options); // Selenium Manager works if you remove WebDriverManager lines
//        }
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        tlDriver.set(driver);
//    }
//
//    public static WebDriver getDriver() { return tlDriver.get(); }
//
//    public static void quitDriver() {
//        WebDriver d = tlDriver.get();
//        if (d != null) { d.quit(); tlDriver.remove(); }
//    }
//}