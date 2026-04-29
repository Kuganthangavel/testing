package com.pages;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
 
public class LoginPage extends BasePage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="emailOrMobile")
	private WebElement mobileTextBox;
 
	
	@FindBy(xpath="//a[@class='mb-login__drop-cta']")
	private WebElement loginOrSignUpButton;
	@FindBy(id="btnStep1")
	private WebElement nextButton;
	@FindBy(xpath="(//button[@class='m-login__btn m-login__btn--primary m-login__btn--block'])[6]")
	private WebElement continueButton;

	public void enterMobileNumber(String number) {
		elementUtils.enterTextIntoElement(mobileTextBox, number);
	}
	public void clickOnLoginOrSignUpButton() {
		elementUtils.clickOnElement(loginOrSignUpButton);
	}
	public void clickOnNextButton() {
		elementUtils.clickOnElement(nextButton);
	}
	public void clickOnContinueButton() {
		elementUtils.clickOnElement(continueButton);
	}
 
	
}