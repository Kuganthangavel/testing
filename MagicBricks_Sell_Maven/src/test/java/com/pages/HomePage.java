package com.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

public class HomePage extends BasePage {

	private WebDriver driver;
	private WebDriverWait wait;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// ---------- SELL ----------
	@FindBy(xpath = "//a[text()='Sell']")
	private WebElement sellElement;

	@FindBy(xpath = "(//a[text()='Find an Agent'])[3]")
	private WebElement findAgent;
	
	
	
	public void checkNewPageTitle() {
		String originalWindow = driver.getWindowHandle();

		// Wait until a new window appears
		new WebDriverWait(driver, Duration.ofSeconds(10))
		        .until(driver -> driver.getWindowHandles().size() > 1);

		// Switch to the new window
		for (String window : driver.getWindowHandles()) {
		    if (!window.equals(originalWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}
	}

	
	
	
	public void clickSell() {
//		wait.until(ExpectedConditions.elementToBeClickable(sellElement));
		elementUtils.clickOnElement(sellElement);
	}

	public void clickAgent() {
		elementUtils.clickOnElement(findAgent);
	}

	// ---------- SELL / RENT PACKAGES ----------
	@FindBy(xpath = "//a[normalize-space()='Sell / Rent Ad Packages']")
	private WebElement sellRentAdPackagesLink;

	@FindBy(css = "ul#userTypeList li[onclick*=\"userType('individual')\"]")
	private WebElement userTypeIndividual;

	@FindBy(css = "ul#userTypeList li[onclick*=\"userType('individual')\"] label")
	private WebElement userTypeIndividualLabel;

	@FindBy(id = "sell-rent-option")
	private WebElement sellRentOptionContainer;

	@FindBy(xpath = "//*[@id='sell-rent-option']//label[normalize-space()='Sell' or contains(.,'Sell')]")
	private WebElement sellOption;

	@FindBy(xpath = "//label[normalize-space()='Residential' or contains(.,'Residential')]")
	private WebElement residentialOption;

	@FindBy(xpath = "//input[@type='submit' and @value='Show Me Packages']")
	private WebElement showMePackagesBtn;

	public void openSellRentAdPackages() {
		wait.until(ExpectedConditions.elementToBeClickable(sellRentAdPackagesLink));
		elementUtils.clickOnElement(sellRentAdPackagesLink);
		switchToNewWindow();
	}	

	public void selectIndividual() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(userTypeIndividualLabel)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(userTypeIndividual)).click();
		}
	}
	public void selectSell() {
		wait.until(ExpectedConditions.elementToBeClickable(sellOption)).click();
	}

	public void selectResidential() {
		wait.until(ExpectedConditions.elementToBeClickable(residentialOption)).click();
	}

	public void clickShowMePackages() {
		elementUtils.clickOnElement(showMePackagesBtn);
	}

	public void completePackagesSelectionFlow() {
		selectIndividual();
		selectSell();
		selectResidential();
		clickShowMePackages();
	}

	// ---------- DEVELOPER LOUNGE ----------
	@FindBy(xpath = "//a[normalize-space()='Developer Lounge']")
	private WebElement developerLoungeLink;

	@FindBy(xpath = "//a[.//img[@alt='Mohit Goel']]")
	private WebElement mohitGoelAnchor;

	@FindBy(xpath = "//*[normalize-space()='Mohit Goel']")
	private WebElement mohitGoelText;

	public void openDeveloperLounge() {
		wait.until(ExpectedConditions.elementToBeClickable(developerLoungeLink));
		elementUtils.clickOnElement(developerLoungeLink);
		switchToNewWindow();
	}

	public void clickMohitGoel() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(mohitGoelAnchor)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(mohitGoelText)).click();
		}
	}

	// ---------- CALLBACK FORM ----------
	@FindBy(xpath = "//*[@value='Get a Callback']")
	private WebElement getCallbackBtn;
	
	@FindBy(xpath ="//*[@id='thankReq']/div/div/div/div/p[1]" )
	private WebElement thankYouMessage;
	
	@FindBy(xpath ="//*[@id='cityNameErr']" )
	private WebElement errorMessage;
	
	public String getThankyouMessage() {
		return elementUtils.getTextFromElement(thankYouMessage);
	}
	public String getErrorMessage() {
		return elementUtils.getTextFromElement(errorMessage);
	}
	

	public void submitEmptyCallbackRequest() {
		wait.until(ExpectedConditions.elementToBeClickable(getCallbackBtn));
		elementUtils.clickOnElement(getCallbackBtn);
	}

	// ---------- SALES ENQUIRY ----------
	@FindBy(xpath = "//a[normalize-space()='Sales Enquiry']")
	private WebElement salesEnquiryLink;

	@FindBy(xpath = "//a[normalize-space()='K']")
	private WebElement alphabetK;

	@FindBy(xpath = "//li[@id='k_row']")
	private WebElement kolkataRow;
	
	@FindBy(xpath = "//h2[text()='Magicbricks Branch Offices']")
	private WebElement branchOfficeText;
	
	public boolean checkranchOfficeText() {
		return elementUtils.isElementDisplayed(branchOfficeText);
	}

	public void openSalesEnquiry() {
		wait.until(ExpectedConditions.elementToBeClickable(salesEnquiryLink));
		elementUtils.clickOnElement(salesEnquiryLink);
		switchToNewWindow();
	}

	public void clickAlphabetK() {
		wait.until(ExpectedConditions.elementToBeClickable(alphabetK));
		elementUtils.clickOnElement(alphabetK);
	}

	public String[] getFullKolkataData() {
		wait.until(ExpectedConditions.visibilityOf(kolkataRow));

		String city = kolkataRow.findElement(By.xpath(".//div[@class='column_1']")).getText();
		String addr = kolkataRow.findElement(By.xpath(".//div[@class='column_2']")).getText();
		String phone = kolkataRow.findElement(By.xpath(".//div[@class='column_3']")).getText();

		String extra = "";
		try {
			extra = kolkataRow.findElement(By.xpath(".//div[@class='column_4']")).getText();
		} catch (Exception e) {
			extra = "N/A";
		}

		return new String[] { city, addr, phone, extra };
	}

	// ============================================================
	// ⭐⭐⭐ TESTCASE 06 : ASK A FREE QUESTION ⭐⭐⭐
	// ============================================================

	@FindBy(xpath = "//a[normalize-space()='Property Valuation']")
	private WebElement propertyValuationLink;

	@FindBy(id = "ask-advice-name")
	private WebElement askFreeName;

	@FindBy(xpath = "//li[@id='3327']")
	private WebElement askFreeCityInput;

	@FindBy(xpath = "//*[@id=\"ask-advice-city-input\"]")
	private WebElement clickOnLocation;

	@FindBy(xpath = "//li[@id='3327' and @name='Bangalore']")
	private WebElement chooseLocationBangalore;

	@FindBy(xpath = "//input[contains(@id,'mobile') or @name='ask-advice-mobile']")
	private WebElement askFreeMobile;

	@FindBy(xpath = "//*[@id='ask-advice-question']")
	private WebElement askFreeQuestion;
	
	@FindBy(id = "Btn_askFreeAdviceCTA24775")
	private WebElement askFreeSubmit;

	@FindBy(xpath = "//a[@class='mkt-place__action--btn prop-service__action--btn btn-red large ']")
	private WebElement verifyButton;

	
	
	
	public void openPropertyValuation() {
		wait.until(ExpectedConditions.elementToBeClickable(propertyValuationLink));
		elementUtils.clickOnElement(propertyValuationLink);
		switchToNewWindow();
	}

	public void fillAskFreeQuestionForm(String name, String city, String mobile, String question) {

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,600);");

		wait.until(ExpectedConditions.visibilityOf(askFreeName)).clear();
		askFreeName.sendKeys(name);

		// OPEN dropdown (No typing for city)
		wait.until(ExpectedConditions.elementToBeClickable(clickOnLocation)).click();
		wait.until(ExpectedConditions.elementToBeClickable(askFreeCityInput)).click();

		wait.until(ExpectedConditions.visibilityOf(askFreeMobile)).clear();
		askFreeMobile.sendKeys(mobile);

		wait.until(ExpectedConditions.elementToBeClickable(askFreeQuestion)).click();
		wait.until(ExpectedConditions.visibilityOf(askFreeQuestion)).clear();
		askFreeQuestion.sendKeys(question);
	}

	public void clickChooseLocation() {
		wait.until(ExpectedConditions.elementToBeClickable(chooseLocationBangalore));
		elementUtils.clickOnElement(chooseLocationBangalore);
	}
	
	public void clickOnVerifyButton() {
		
		elementUtils.clickOnElement(verifyButton);
	}

	public void submitAskFreeQuestion() {
//		wait.until(ExpectedConditions.elementToBeClickable(askFreeSubmit));
		elementUtils.clickOnElement(askFreeSubmit);
	}
	
	 
	    

	   

//	    public void fillCallbackForm(String name, String mobile, String email, String message) {
//	        type(nameInput, name);
//	        type(mobileInput, mobile);
//	        type(emailInput, email);
//	        type(messageTextarea, message);
//	    }
	    @FindBy(xpath="//input[@id='userName']")
		WebElement nameInput;
	    @FindBy(xpath="//input[@id='phoneNumber']")
		WebElement mobileInput;
	    @FindBy(xpath="//input[@name='email']")
		WebElement emailInput;
	    @FindBy(xpath="//*[@id='query']")
		WebElement messageTextarea;
	    @FindBy(xpath="//input[@id='keyword']")
		WebElement cityInput;
	    @FindBy(xpath="//*[@id='requestInfoForm']/div[7]/div[1]/label")
		WebElement checkBox;
	    
	    public void nameInput(String name) throws InterruptedException {
	        elementUtils.enterTextIntoElement(nameInput, name);
	    }
	    public void mobileInput(String mobile) throws InterruptedException {
	        elementUtils.enterTextIntoElement(mobileInput, mobile);
	    }
	    public void emailInput(String email) throws InterruptedException {
	        elementUtils.enterTextIntoElement(emailInput, email);
	    }
	    public void messageTextarea(String message) throws InterruptedException {
	        elementUtils.enterTextIntoElement(messageTextarea, message);
	    }
	    public void enterCityName(String message) throws InterruptedException {
	        elementUtils.enterTextIntoElement(cityInput, message);
	    }
	    public void clickCheckBox() throws InterruptedException {
	    	elementUtils.clickOnElement(checkBox);
	    }
	    

	@FindBy(xpath="//input[@id='callBackSubmit']")
	WebElement submitBtn;
	    public void submitCallbackRequest() {
//	        driver.findElement(submitBtn).click();
	    	elementUtils.clickOnElement(submitBtn);
	    }
}