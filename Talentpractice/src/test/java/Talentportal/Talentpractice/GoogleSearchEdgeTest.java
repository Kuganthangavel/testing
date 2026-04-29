package Talentportal.Talentpractice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleSearchEdgeTest {

    private WebDriver driver;
    private WebDriverWait wait;

    // Common locators and page values
    private final String GOOGLE_URL = "https://www.google.com";
    private final By SEARCH_BOX = By.xpath("//input[@name='q']");
    private final By IMAGE_TAB = By.xpath("//a[contains(@href, 'tbm=isch') or contains(., 'Images')]");

    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void openGoogleSearchAndClickImages() {
        openUrl(GOOGLE_URL);
        type(SEARCH_BOX, "saruk khan");
        submit(SEARCH_BOX);
        click(IMAGE_TAB);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Generic helper methods for the template
    private void openUrl(String url) {
        driver.get(url);
    }

    private WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    private void type(By locator, String text) {
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

    private void submit(By locator) {
        WebElement element = find(locator);
        element.sendKeys(Keys.ENTER);
    }
}
