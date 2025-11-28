package adminDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
 
public class AccountDeleteConfirmPopup  {

	public AppiumDriver driver;

	public AccountDeleteConfirmPopup(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "Are you sure you want to delete this user?")
	private WebElement confirmText;

	@AndroidFindBy(accessibility = "No")
	private WebElement noButton;

	@AndroidFindBy(accessibility = "Yes")
	private WebElement yesButton;

	public WebElement getConfirmText() {
		return confirmText;
	}

	public WebElement getNoButton() {
		return noButton;
	}

	public WebElement getYesButton() {
		return yesButton;
	}
	
	public void confirmPopupUIValidation() {
		ExtentReportManager.logInfo("Validating Account Delete Confirm Popup UI elements");
		if (confirmText.isDisplayed()) {
			ExtentReportManager.logPass("Confirmation text is visible");
		} else {
			ExtentReportManager.logFail("Confirmation text is not visible", null);
		}
		if (noButton.isDisplayed()) {
			ExtentReportManager.logPass("No button is visible");
		} else {
			ExtentReportManager.logFail("No button is not visible", null);
		}
		if (yesButton.isDisplayed()) {
			ExtentReportManager.logPass("Yes button is visible");
		} else {
			ExtentReportManager.logFail("Yes button is not visible", null);
		}
	}

	public void clickNoButton() {
		ExtentReportManager.logInfo("Clicking No button on Account Delete Confirm Popup");
		noButton.click();
		ExtentReportManager.logPass("No button clicked");
	}

	public void clickYesButton() {
		ExtentReportManager.logInfo("Clicking Yes button on Account Delete Confirm Popup");
		yesButton.click();
		ExtentReportManager.logPass("Yes button clicked");
	}

}
