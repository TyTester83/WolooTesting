package adminDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utilities.BasePage;
import Utilities.GenericUtility;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FacilityRenewPopup extends BasePage {

	public FacilityRenewPopup(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"please Renew subscription\")]")
	private WebElement renewPopupLayout;
	
	@AndroidFindBy(accessibility = "Your TASKMASTER Trial Period has expired. Kindly pay to Continue")
	private WebElement trailText;
	
	@AndroidFindBy(accessibility = "Pay Now")
	private WebElement payNowButton;

	public WebElement getRenewPopupLayout() {
		return renewPopupLayout;
	}

	public WebElement getTrailText() {
		return trailText;
	}

	public WebElement getPayNowButton() {
		return payNowButton;
	}

	public void tapFacilityRenewButton()
	{
		ExtentReportManager.logInfo("Tapping Facility Renew popup layout");
		wait.until(ExpectedConditions.visibilityOf(renewPopupLayout));
		ExtentReportManager.logPass("Renew popup layout is visible");
		GenericUtility.tapUsingCoordinatePercentage(driver, renewPopupLayout, 50.0, 49.0);
		ExtentReportManager.logPass("Facility renew popup tapped");
	}

	public void tapPayNowButton()
	{
		ExtentReportManager.logInfo("Clicking Pay Now button on Facility Renew popup");
		wait.until(ExpectedConditions.visibilityOf(trailText));
		ExtentReportManager.logPass("Trial text is visible");
		wait.until(ExpectedConditions.elementToBeClickable(payNowButton)).click();
		ExtentReportManager.logPass("Pay Now button clicked");
	}
	
	public void facilityRenewPopupUIValidation() {
		ExtentReportManager.logInfo("Validating Facility Renew popup UI elements");
		wait.until(ExpectedConditions.visibilityOf(renewPopupLayout));
		ExtentReportManager.logPass("Renew popup layout is visible");
		if (trailText.isDisplayed()) {
			ExtentReportManager.logPass("Trial text is visible");
		} else {
			ExtentReportManager.logFail("Trial text is not visible", driver);
		}
		if (payNowButton.isDisplayed()) {
			ExtentReportManager.logPass("Pay Now button is visible");
		} else {
			ExtentReportManager.logFail("Pay Now button is not visible", driver);
		}
	}

}
