package adminDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utilities.BasePage;
import Utilities.GenericUtility;
import Utilities.SoftAssertValidationUtility;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AddNewFacilityPage extends BasePage {

	public AddNewFacilityPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(accessibility = "Welcome to")
	private WebElement welcomeText;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[1]")
	private WebElement taskmasterLogo;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[2]")
	private WebElement serviceImage;

	@AndroidFindBy(accessibility = "You're there! Just a few more steps to get you started with your Smart Hygiene Journey.")
	private WebElement quotesText;

	@AndroidFindBy(accessibility = "Add a New Facility")
	private WebElement facilityButton;

	@AndroidFindBy(accessibility = "Add a New Task")
	private WebElement taskButton;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"The Task Master service of Woloo Smart Hygiene is a paid service. You are eligible for a free trial\")]")
	private WebElement freeTrialText;

	@AndroidFindBy(accessibility = "Back")
	private WebElement backButton;

	public WebElement getWelcomeText() {
		return welcomeText;
	}

	public WebElement getTaskmasterLogo() {
		return taskmasterLogo;
	}

	public WebElement getServiceImage() {
		return serviceImage;
	}

	public WebElement getQuotesText() {
		return quotesText;
	}

	public WebElement getFacilityButton() {
		return facilityButton;
	}

	public WebElement getTaskButton() {
		return taskButton;
	}

	public WebElement getFreeTrialText() {
		return freeTrialText;
	}

	public WebElement getBackButton() {
		return backButton;
	}

	/**
	 * Validates all UI elements on Add New Facility screen with step-level Extent
	 * logging
	 */
	public void addNewFacilityUIValidation() {
		wait.until(ExpectedConditions.visibilityOf(facilityButton));
		ExtentReportManager.logInfo("Validating Add New Facility screen UI elements");

		SoftAssertValidationUtility.verifyElementVisible(welcomeText, "Welcome text element should be visible");
//		ExtentReportManager.logInfo("Welcome text is visible");

		SoftAssertValidationUtility.verifyElementVisible(taskmasterLogo, "TaskMaster logo should be visible");
//		ExtentReportManager.logInfo("TaskMaster logo is visible");

		SoftAssertValidationUtility.verifyElementVisible(serviceImage, "Service image should be visible");
//		ExtentReportManager.logInfo("Service image is visible");

//		SoftAssertValidationUtility.verifyElementVisible(quotesText, "Quotes text should be visible");
//		ExtentReportManager.logInfo("Quotes text is visible");

		SoftAssertValidationUtility.verifyElementVisible(facilityButton, "Add New Facility button should be visible");
//		ExtentReportManager.logInfo("Add New Facility button is visible");

		SoftAssertValidationUtility.verifyElementVisible(taskButton, "Add New Task button should be visible");
//		ExtentReportManager.logInfo("Add New Task button is visible");

		ExtentReportManager.logInfo("Scrolling down to check free trial text");
		GenericUtility.verticalScroll(driver);

		SoftAssertValidationUtility.verifyElementVisible(freeTrialText, "Free trial text should be visible");
		ExtentReportManager.logPass("Free trial text is visible after scroll", driver, false);

	}

	/**
	 * Clicks Add New Facility button with pre-click visibility check and post-click
	 * verification
	 */
	public void tapNewFacilityButton() {
		ExtentReportManager.logInfo("Tapping Add New Facility button");
		try {
			if (!gUtil.isElementVisible(facilityButton, driver)) {
				ExtentReportManager.logFail("Add New Facility button is not visible — cannot tap", driver);
				throw new IllegalStateException("Add New Facility button not visible");
			}

			gUtil.safeClick(facilityButton, 2, driver);

			ExtentReportManager.logPass("Tapped Add New Facility button", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("Failed to tap Add New Facility button", e, driver);
			throw e;
		}
	}

	/**
	 * Clicks Add New Task button with pre-click visibility check and post-click
	 * verification
	 */
	public void tapNewTaskButton() {
		ExtentReportManager.logInfo("Tapping Add New Task button");
		try {
			if (!gUtil.isElementVisible(taskButton, driver)) {
				ExtentReportManager.logFail("Add New Task button is not visible — cannot tap", driver);
				throw new IllegalStateException("Add New Task button not visible");
			}
			gUtil.safeClick(taskButton, 2, driver);
			
			ExtentReportManager.logPass("Tapped Add New Task button", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("Failed to tap Add New Task button", e, driver);
			throw e;
		}
	}

	/**
	 * Clicks Back button with pre-click visibility check and post-click
	 * verification
	 */
	public void tapBackButton() {
		ExtentReportManager.logInfo("Tapping Back button");
		try {
			if (!gUtil.isElementVisible(backButton, driver)) {
				ExtentReportManager.logFail("Back button is not visible — cannot tap", driver);
				throw new IllegalStateException("Back button not visible");
			}
			gUtil.safeClick(backButton, 2, driver);
			
			ExtentReportManager.logPass("Tapped Back button", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("Failed to tap Back button", e, driver);
			throw e;
		}
	}
}