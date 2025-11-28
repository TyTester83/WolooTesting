package adminDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PaynowPage extends BasePage {

	public PaynowPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.ScrollView")
	private WebElement payNowlayout;

	@AndroidFindBy(accessibility = " Upgrade your Tasq master service")
	private WebElement upgradeText;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"Avail STINQGUARD\")]")
	private WebElement stinqGuardPlanImage;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"Avail TASQMASTER\")]")
	private WebElement tasqmasterPlanImage;

	@AndroidFindBy(accessibility = "Pay Now")
	private WebElement payNowButton;

	@AndroidFindBy(accessibility = "Please select a plan")
	private WebElement errorMsgText;

	public WebElement getPayNowlayout() {
		return payNowlayout;
	}

	public WebElement getUpgradeText() {
		return upgradeText;
	}

	public WebElement getStinqGuardPlanImage() {
		return stinqGuardPlanImage;
	}

	public WebElement getTasqmasterPlanImage() {
		return tasqmasterPlanImage;
	}

	public WebElement getPayNowButton() {
		return payNowButton;
	}

	public WebElement getErrorMsgText() {
		return errorMsgText;
	}

	public void paynowScreenUIValidation() {
		ExtentReportManager.logInfo("Validating Pay Now screen UI elements");
		wait.until(ExpectedConditions.visibilityOf(upgradeText));
		ExtentReportManager.logPass("Upgrade text is visible");

		if (stinqGuardPlanImage.isDisplayed()) {
			ExtentReportManager.logPass("STINQGUARD plan image is visible");
			Assert.assertEquals(true, stinqGuardPlanImage.isDisplayed());
		} else {
			ExtentReportManager.logFail("STINQGUARD plan image is not visible", driver);
		}
		
		if (tasqmasterPlanImage.isDisplayed()) {
			ExtentReportManager.logPass("TASQMASTER plan image is visible");
			Assert.assertEquals(true, tasqmasterPlanImage.isDisplayed());
		} else {
			ExtentReportManager.logFail("TASQMASTER plan image is not visible", driver);
		}
		

		gUtil.scrollElement(driver, stinqGuardPlanImage, "up");
		ExtentReportManager.logPass("Scrolled up to view Pay Now button");

		wait.until(ExpectedConditions.visibilityOf(payNowButton));
		if (payNowButton.isDisplayed()) {
			ExtentReportManager.logPass("Pay Now button is visible");
			Assert.assertEquals(true, payNowButton.isDisplayed());
		} else {
			ExtentReportManager.logFail("Pay Now button is not visible", driver);
		}
		
	}

	public void validatePaymentPlanErrorMessage() {
		ExtentReportManager.logInfo("Clicking Pay Now without selecting a plan to validate error message");
		gUtil.safeClick(payNowButton, 2, driver);
		ExtentReportManager.logPass("Pay Now button clicked");
		if (errorMsgText.isDisplayed()) {
			ExtentReportManager.logPass("Error message displayed as expected: 'Please select a plan'");
		} else {
			ExtentReportManager.logFail("Error message not displayed when expected", driver);
		}
		Assert.assertEquals(true, errorMsgText.isDisplayed());
	}

	public void tapPaymentPlan(String plan) throws InterruptedException {
		ExtentReportManager.logInfo("Selecting payment plan: " + plan);
		wait.until(ExpectedConditions.visibilityOf(stinqGuardPlanImage));
		ExtentReportManager.logPass("Plan images are visible");

		gUtil.scrollElement(driver, stinqGuardPlanImage, "up");
		ExtentReportManager.logPass("Scrolled to view payment plans");
		wait.until(ExpectedConditions.visibilityOf(payNowButton));

		switch (plan.trim().toLowerCase()) {
	    case "tasqmaster":
	        wait.until(ExpectedConditions.elementToBeClickable(tasqmasterPlanImage)).click();
	        ExtentReportManager.logPass("TASQMASTER plan selected");
	        break;

	    case "stinqguard":
	        wait.until(ExpectedConditions.elementToBeClickable(stinqGuardPlanImage)).click();
	        ExtentReportManager.logPass("STINQGUARD plan selected");
	        break;

	    default:
	        ExtentReportManager.logFail("Unknown plan: " + plan, driver);
	        throw new IllegalArgumentException("Unknown plan: " + plan);
	}
		gUtil.safeClick(payNowButton, 2, driver);
		ExtentReportManager.logPass("Pay Now button clicked for plan: " + plan);
	}
}
