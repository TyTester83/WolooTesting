package adminDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class NewUserRenewPopup extends BasePage {

	public NewUserRenewPopup(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(accessibility = "Kindly renew your subscription from dashboard")
	private WebElement renewText;

	@AndroidFindBy(accessibility = "Go Back")
	private WebElement goBackButton;

	public WebElement getRenewText() {
		return renewText;
	}

	public WebElement getGoBackButton() {
		return goBackButton;
	}

	public void newUserWarningPopup() {
		ExtentReportManager.logInfo("Handling new user renewal warning popup");
		wait.until(ExpectedConditions.visibilityOf(renewText));
		ExtentReportManager.logPass("Renewal text is visible");
		if (renewText.isDisplayed()) {
			ExtentReportManager.logPass("Renewal warning popup displayed as expected");
		} else {
			ExtentReportManager.logFail("Renewal warning popup not displayed", driver);
		}
		Assert.assertEquals(renewText.isDisplayed(), true);
		gUtil.safeClick(goBackButton, 2, driver);
		ExtentReportManager.logPass("Go Back button clicked");
	}
	
	public void newUserRenewPopupUIValidation() {
		ExtentReportManager.logInfo("Validating New User Renew popup UI elements");
		if (renewText.isDisplayed()) {
			ExtentReportManager.logPass("Renew text is visible");
		} else {
			ExtentReportManager.logFail("Renew text is not visible", driver);
		}
		if (goBackButton.isDisplayed()) {
			ExtentReportManager.logPass("Go Back button is visible");
		} else {
			ExtentReportManager.logFail("Go Back button is not visible", driver);
		}
	}
}
