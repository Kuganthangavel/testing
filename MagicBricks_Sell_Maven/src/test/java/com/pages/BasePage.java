package com.pages;
 
 
import org.openqa.selenium.WebDriver;
 
import com.utils.ElementUtils;
 
public class BasePage {
	WebDriver driver;
	public ElementUtils elementUtils;
 
	public BasePage(WebDriver driver) {
		this.driver=driver;
		elementUtils=new ElementUtils(driver);
	}

 
	public void switchToNewWindow() {
	    String parent = driver.getWindowHandle();
	    for (String win : driver.getWindowHandles()) {
	        if (!win.equals(parent)) {
	            driver.switchTo().window(win);
	            break;
	        }
	    }
	}
}