package adminDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import Utilities.SoftAssertValidationUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AdminProfilePage extends BasePage {

	public AdminProfilePage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"woloo points\")]")
	private WebElement pointsText;

	@AndroidFindBy(accessibility = "Active Membership")
	private WebElement membershipText;

	@AndroidFindBy(accessibility = "Contact Us")
	private WebElement contactUsLink;

	@AndroidFindBy(accessibility = "About")
	private WebElement aboutLink;

	@AndroidFindBy(accessibility = "Terms of Use")
	private WebElement termsLink;

	@AndroidFindBy(accessibility = "Log out")
	private WebElement logoutButton;

	@AndroidFindBy(accessibility = "Delete User")
	private WebElement deleteButton;

	public WebElement getPointsText() {
		return pointsText;
	}

	public WebElement getMembershipText() {
		return membershipText;
	}

	public WebElement getContactUsLink() {
		return contactUsLink;
	}

	public WebElement getAboutLink() {
		return aboutLink;
	}

	public WebElement getTermsLink() {
		return termsLink;
	}

	public WebElement getLogoutButton() {
		return logoutButton;
	}

	public WebElement getDeleteButton() {
		return deleteButton;
	}

	public void logout() {
		ExtentReportManager.logInfo("Initiating logout from Admin Profile Page");
		wait.until(ExpectedConditions.visibilityOf(logoutButton));
//		ExtentReportManager.logPass("Logout button is visible");
		gUtil.safeClick(logoutButton, 4, driver);
		ExtentReportManager.logPass("Logout button clicked");

		AdminLogoutPopup popup = new AdminLogoutPopup(driver);
		popup.logoutPopupUIValidation();
		popup.tapYesButtontoLogout();
		ExtentReportManager.logPass("Logout confirmed successfully");
	}

	public void validateWolooPoints(String expectedPts) throws InterruptedException {
		ExtentReportManager.logInfo("Validating Woloo points. Expected: " + expectedPts);
		wait.until(ExpectedConditions.visibilityOf(logoutButton));
//		ExtentReportManager.logPass("Logout button is visible");
		Thread.sleep(4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				AppiumBy.xpath("//android.view.View[contains(@content-desc,\"woloo points\")]")));
//		ExtentReportManager.logPass("Woloo points element is present");
		String actualPoints = pointsText.getAttribute("Content-desc");
		ExtentReportManager.logInfo("Actual Woloo points: " + actualPoints);
		boolean validatePoints = actualPoints.contains(expectedPts);
		if (validatePoints) {
			ExtentReportManager
					.logPass("Woloo points validation passed. Expected: " + expectedPts + ", Found: " + actualPoints);
		} else {
			ExtentReportManager.logFail(
					"Woloo points validation failed. Expected: " + expectedPts + ", Found: " + actualPoints, driver);
		}
		Assert.assertTrue(validatePoints, "Not a valid woloo points");
	}

	public void adminProfileUIValidation() {
		ExtentReportManager.logInfo("Validating Admin Profile Page UI elements");

		SoftAssertValidationUtility.verifyElementVisible(pointsText, " PointsText is not displayed");
//		ExtentReportManager.logPass("Woloo points text is visible");

		SoftAssertValidationUtility.verifyElementVisible(membershipText, " Active Membership text is visible");
//		ExtentReportManager.logPass("Active Membership text is visible");

		SoftAssertValidationUtility.verifyElementVisible(contactUsLink, "Contact Us link is visible");
//		ExtentReportManager.logPass("Contact Us link is visible");

		SoftAssertValidationUtility.verifyElementVisible(aboutLink, "About link is visible");
//		ExtentReportManager.logPass("About link is visible");

		SoftAssertValidationUtility.verifyElementVisible(termsLink, "Terms of Use link is visible");
//		ExtentReportManager.logPass("Terms of Use link is visible");

		SoftAssertValidationUtility.verifyElementVisible(logoutButton, "Logout button is visible");
//		ExtentReportManager.logPass("Logout button is visible");

		SoftAssertValidationUtility.verifyElementVisible(deleteButton, "Delete User button is visible");
		ExtentReportManager.logPass("Admin profile UI validated");

	}

	public void clickContactUsLink() {
		ExtentReportManager.logInfo("Clicking Contact Us link");
		gUtil.safeClick(contactUsLink, 2, driver);
		ExtentReportManager.logPass("Contact Us link clicked");
	}

	public void clickAboutLink() {
		ExtentReportManager.logInfo("Clicking About link");
		gUtil.safeClick(aboutLink, 2, driver);
		ExtentReportManager.logPass("About link clicked");
	}

	public void clickTermsLink() {
		ExtentReportManager.logInfo("Clicking Terms of Use link");
		gUtil.safeClick(termsLink, 2, driver);
		ExtentReportManager.logPass("Terms of Use link clicked");
	}

	public void clickDeleteButton() {
		ExtentReportManager.logInfo("Clicking Delete User button");
		gUtil.safeClick(deleteButton, 2, driver);
		ExtentReportManager.logPass("Delete User button clicked");
	}
}
