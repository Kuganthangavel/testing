package com.utils;
 
import java.time.Duration;
 
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class ElementUtils {
	WebDriver driver;
	
	public ElementUtils(WebDriver driver) {
		this.driver=driver;
	}
	
	public void clickOnElement(WebElement element) {
		if(element.isDisplayed() && element.isEnabled()) {
			element.click();
		}
	}
	
	public void enterTextIntoElement(WebElement element , String text) {
		if(element.isDisplayed() && element.isEnabled()) {
			element.clear();
			element.sendKeys(text);
		}
	}
	
	public String getTextFromElement(WebElement element) {
		String text = "";
		if(element.isDisplayed()) {
			text = element.getText();
		}
		return text;
	}
	
	public boolean isElementDisplayed(WebElement element) {
		boolean displayed=false;
		try {
			displayed=element.isDisplayed();
		}catch(NoSuchElementException e) {
			displayed=false;
		}
		return displayed;
	}
	
	public void waitForElement(WebElement element) {
    	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
    }
}