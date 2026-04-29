//package com.pages;
//
//import org.openqa.selenium.*;
//import java.io.File;
//import java.awt.*;
//import java.awt.datatransfer.StringSelection;
//import java.awt.event.KeyEvent;
//
//public class SellAgentPage extends BasePage {
//
//    // Mandatory details to reach media step
//    private final By propertyType = By.cssSelector("select[name*='propertyType'], input[name*='propertyType']");
//    private final By bedrooms     = By.cssSelector("select[name*='bedrooms'], input[name*='bedrooms']");
//    private final By locality     = By.cssSelector("input[name*='locality']");
//    private final By superArea    = By.cssSelector("input[name*='superArea']");
//    private final By price        = By.cssSelector("input[name*='price']");
//    private final By firmName     = By.cssSelector("input[name*='firm']");
//    private final By reraId       = By.cssSelector("input[name*='rera']");
//    private final By phone        = By.cssSelector("input[type='tel']");
//    private final By email        = By.cssSelector("input[type='email']");
//    private final By continueBtn  = By.xpath("//button[contains(.,'Continue') or contains(.,'Next')]");
//
//    // Media step
//    private final By uploadPhotosBtn = By.xpath("//button[contains(.,'Upload') and contains(.,'Photo')]");
//    private final By mediaThumbs     = By.cssSelector("[data-test*='media-thumb']");
//    private final By setCoverBtn     = By.xpath("//button[contains(.,'Set as Cover') or contains(.,'Cover')]");
//
//    public SellAgentPage(WebDriver driver) { super(driver); }
//
//    public SellAgentPage fillMandatoryAgentFields(String type, String beds, String loc, String area, String pr,
//                                                  String firm, String rera, String ph, String em) {
//        type(propertyType, type);
//        type(bedrooms, beds);
//        type(locality, loc);
//        type(superArea, area);
//        type(price, pr);
//        type(firmName, firm);
//        type(reraId, rera);
//        type(phone, ph);
//        type(email, em);
//        click(continueBtn);
//        return this;
//    }
//
//    public SellAgentPage uploadPhotosWithRobot(String baseDir, String... fileNames) {
//        click(uploadPhotosBtn); // opens native dialog
//        try {
//            Robot robot = new Robot();
//            StringSelection filesPath = new StringSelection(joinForOS(baseDir, fileNames));
//            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filesPath, null);
//
//            robot.delay(500);
//            robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_V);
//            robot.keyRelease(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_CONTROL);
//            robot.delay(300);
//            robot.keyPress(KeyEvent.VK_ENTER); robot.keyRelease(KeyEvent.VK_ENTER);
//        } catch (Exception e) {
//            throw new RuntimeException("Robot upload failed", e);
//        }
//        return this;
//    }
//
//    private String joinForOS(String base, String[] files) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < files.length; i++) {
//            if (i > 0) sb.append(File.pathSeparatorChar); // multi-select
//            sb.append(new File(base, files[i].trim()).getAbsolutePath());
//        }
//        return sb.toString();
//    }
//
//    public SellAgentPage setCoverAndReorderIfAvailable() {
//        try { click(setCoverBtn); } catch (Exception ignore) { /* Locator to be finalized on real DOM */ }
//        // Drag-and-drop reorder can be added here with concrete locators
//        return this;
//    }
//
//    public boolean allPhotosVisible() { return driver.findElements(mediaThumbs).size() >= 3; }
//}