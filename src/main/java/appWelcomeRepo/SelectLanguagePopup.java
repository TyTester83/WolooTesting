package appWelcomeRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SelectLanguagePopup extends BasePage {

	public SelectLanguagePopup(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Select Language\"]")
	private WebElement languagePopup;

	@AndroidFindBy(accessibility = "English")
	private WebElement englishButton;

	@AndroidFindBy(accessibility = "हिंदी")
	private WebElement hindiButton;
	
	@AndroidFindBy(accessibility = "मराठी")
	private WebElement marathiButton;
	
	public WebElement getLanguagePopup() {
		return languagePopup;
	}

	public WebElement getEnglishButton() {
		return englishButton;
	}

	public WebElement getHindiButton() {
		return hindiButton;
	}

	public WebElement getMarathiButton() {
		return marathiButton;
	}

	private WebDriverWait waitUntilPopup() {
		WebDriverWait wait = waitWithTimeout(DEFAULT_WAIT);
		ExtentReportManager.logInfo("Waiting for language selection popup to be visible");
		wait.until(ExpectedConditions.visibilityOf(languagePopup));
		ExtentReportManager.logPass("Language selection popup is visible");
		return wait;
	}

	public void languageUIValidation() {
		waitUntilPopup();
		ExtentReportManager.logInfo("Validating language selection UI elements");

		Assert.assertTrue(englishButton.isDisplayed(), "English button is not displayed");
		ExtentReportManager.logPass("English button is visible");
		Assert.assertTrue(hindiButton.isDisplayed(), "Hindi button is not displayed");
		ExtentReportManager.logPass("Hindi button is visible");
		Assert.assertTrue(marathiButton.isDisplayed(), "Marathi button is not displayed");
		ExtentReportManager.logPass("Marathi button is visible");
	}
	
	public void chooseEnglishLanguage() {
		ExtentReportManager.logInfo("Selecting English language");
		WebDriverWait wait = waitUntilPopup();
		wait.until(ExpectedConditions.elementToBeClickable(englishButton)).click();
		ExtentReportManager.logPass("English language selected");
	}
}

