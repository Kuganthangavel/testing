package com.test;

import com.setup.Hooks;
import com.pages.HomePage;
import com.parameter.DataProviders;
import com.parameter.ExcelReader;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SellAgentTests extends Hooks {

	private HomePage home;

	@Test(priority = 1)
	public void tc01_navigateAndSelectRole() throws InterruptedException {
		home = new HomePage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		home.clickSell();
		home.clickAgent();
		home.checkNewPageTitle();

		Thread.sleep(5000);
		Assert.assertTrue(driver.getCurrentUrl().contains("agent-in-Bangalore"));

	}

	@Test(priority = 2)
	public void tc02_openPackages_and_select_Individual_Sell_Residential_then_show() {
		home = new HomePage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		home.clickSell();
		home.openSellRentAdPackages();
		home.completePackagesSelectionFlow();
		Assert.assertTrue(driver.getCurrentUrl().contains("SELL&ptype=Residential"), "Expected packages page.");
	}

	@Test(priority = 3)
	public void tc03_openDeveloperLounge_and_click_MohitGoel() {
		home = new HomePage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		home.clickSell();
		home.openDeveloperLounge();
//        home.clickMohitGoel();
		Assert.assertTrue(driver.getCurrentUrl().contains("brand-store"));
	}

	@Test(priority = 4)
	public void tc04_submit_empty_callback_request() {

		home = new HomePage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		home.clickSell();
		home.openSellRentAdPackages();
		home.submitEmptyCallbackRequest();
		Assert.assertEquals(home.getErrorMessage(), "Compulsory field. Cannot be blank.");
		;
	}

	@Test(priority = 5)
	public void tc05_show_full_kolkata_data() throws InterruptedException {

		home = new HomePage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		home.clickSell();
		home.openSalesEnquiry();
		home.clickAlphabetK();
		Assert.assertTrue(home.checkranchOfficeText());
	}

	@Test(priority = 6)
	public void tc06_propertyValue_askFreeQuestion_form() throws InterruptedException {

		home = new HomePage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		home.clickSell();
		home.openPropertyValuation();
		Map<String, String> kv = com.parameter.ExcelReader.readEntityValueSheet("Sheet1");
		Assert.assertTrue(
				java.util.stream.Stream.of("Name", "City", "Mobile", "Question")
						.allMatch(k -> kv.containsKey(k) && kv.get(k) != null && !kv.get(k).trim().isEmpty()),
				"Excel missing/blank among required keys. Loaded: " + kv);
		home.fillAskFreeQuestionForm(kv.get("Name"), kv.get("City"), kv.get("Mobile"), kv.get("Question"));
		home.submitAskFreeQuestion();
		Thread.sleep(15000);
		home.clickOnVerifyButton();

	}

	// ---------- testcase07 ----------
	@Test(priority = 7, dataProvider = "callbackData", dataProviderClass = DataProviders.class)
	public void tc07_submit_callback_request_with_data(String name, String mobile, String email, String city,
			String message) throws InterruptedException {
		home = new HomePage(driver);
		home.clickSell();
		home.openSellRentAdPackages();
		home.nameInput(name);
		home.mobileInput(mobile);
		home.emailInput(email);
		home.enterCityName(city);
		home.messageTextarea(message);
		home.clickCheckBox();
		home.submitCallbackRequest();
		Thread.sleep(2000);
		Assert.assertEquals(home.getThankyouMessage(), "Thank you for\n"
				+ "submitting your details.");
	}
}
