package adminDashboardRepo;

import org.openqa.selenium.WebElement;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AdminLogoutPopup extends BasePage {

	public AdminLogoutPopup(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(accessibility = "Are you sure you want to logout?")
	private WebElement logoutText;

	@AndroidFindBy(accessibility = "No")
	private WebElement noButton;

	@AndroidFindBy(accessibility = "Yes")
	private WebElement yesButton;

	public WebElement getLogoutText() {
		return logoutText;
	}

	public WebElement getNoButton() {
		return noButton;
	}

	public WebElement getYesButton() {
		return yesButton;
	}

	public void tapYesButtontoLogout()
	{
//		ExtentReportManager.logInfo("Confirming logout by clicking Yes button");
		if (yesButton.isDisplayed()) {
			ExtentReportManager.logPass("Yes button is visible");
		} else {
			ExtentReportManager.logFail("Yes button is not visible", driver);
		}
		gUtil.safeClick(yesButton, 1, driver);
		ExtentReportManager.logPass("Yes button clicked to logout");
	}
	
	public void logoutPopupUIValidation() {
		ExtentReportManager.logInfo("Validating logout confirmation popup UI elements");
		if (logoutText.isDisplayed()) {
//			ExtentReportManager.logPass("Logout confirmation text is visible");
		} else {
			ExtentReportManager.logFail("Logout confirmation text is not visible", driver);
		}
		if (noButton.isDisplayed()) {
//			ExtentReportManager.logPass("No button is visible");
		} else {
			ExtentReportManager.logFail("No button is not visible", driver);
		}
		if (yesButton.isDisplayed()) {
//			ExtentReportManager.logPass("Yes button is visible");
		} else {
			ExtentReportManager.logFail("Yes button is not visible", driver);
		}
		ExtentReportManager.logInfo("Validated logout confirmation popup UI elements");
	}
	
	public void clickNoButton() {
		ExtentReportManager.logInfo("Clicking No button on logout confirmation popup");
		gUtil.safeClick(noButton, 1, driver);
		ExtentReportManager.logPass("No button clicked, logout cancelled");
	}
}
