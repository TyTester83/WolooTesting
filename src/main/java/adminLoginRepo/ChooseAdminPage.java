package adminLoginRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.GenericUtility;
import Utilities.SoftAssertValidationUtility;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ChooseAdminPage {

	public AppiumDriver driver;

	public ChooseAdminPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Choose Admin\")]")
	private WebElement chooseAdminlayout;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"Monitor\")]")
	private WebElement monitorYourselfTextview;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Monitor\")]/android.widget.ImageView[2]")
	private WebElement yourselfTickIcon;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"Assign\")]")
	private WebElement supervisorTextview;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Supervisor\")]/android.widget.ImageView[2]")
	private WebElement supervisorTickIcon;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Please select a supervisor option\")]")
	private WebElement errorMsgText;

	public WebElement getChooseAdminlayout() {
		return chooseAdminlayout;
	}

	public WebElement getMonitorYourselfTextview() {
		return monitorYourselfTextview;
	}

	public WebElement getYourselfTickIcon() {
		return yourselfTickIcon;
	}

	public WebElement getSupervisorTextview() {
		return supervisorTextview;
	}

	public WebElement getSupervisorTickIcon() {
		return supervisorTickIcon;
	}

	public WebElement getErrorMsgText() {
		return errorMsgText;
	}

	public void chooseAdminUIValidation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(monitorYourselfTextview));

		ExtentReportManager.logInfo("Validating Choose Admin screen elements");
		SoftAssertValidationUtility.verifyElementVisible(monitorYourselfTextview,
				"'Monitor Yourself' option is not displayed.");
		ExtentReportManager.logInfo("Verified 'Monitor Yourself' option visibility");
		SoftAssertValidationUtility.verifyElementVisible(supervisorTextview, "'Supervisor' option is not displayed.");
		ExtentReportManager.logPass("Choose Admin screen validation completed", driver, false);

	}

	public void chooseAdmin(String admin) throws InterruptedException {
		String normalizedAdmin = admin.trim().toLowerCase();
		ExtentReportManager.logInfo("Choosing admin option: " + normalizedAdmin);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		GenericUtility gUtil = new GenericUtility();
		switch (normalizedAdmin) {
		case "myself":
			// pre-check visibility
			if (!gUtil.isElementVisible(monitorYourselfTextview, driver)) {
				ExtentReportManager.logFail("'Monitor Yourself' option not visible", driver);
				throw new IllegalStateException("'Monitor Yourself' option not visible");
			}
			wait.until(ExpectedConditions.elementToBeClickable(monitorYourselfTextview)).click();
			wait.until(ExpectedConditions.visibilityOf(yourselfTickIcon));
			Assert.assertTrue(yourselfTickIcon.isDisplayed(), "TickIcon not visible");
			ExtentReportManager.logPass("Selected 'Monitor Yourself' option", driver, false);
			break;
		case "supervisor":
			// pre-check visibility
			if (!gUtil.isElementVisible(supervisorTextview, driver)) {
				ExtentReportManager.logFail("'Supervisor' option not visible", driver);
				throw new IllegalStateException("'Supervisor' option not visible");
			}
			
			wait.until(ExpectedConditions.elementToBeClickable(supervisorTextview)).click();
			wait.until(ExpectedConditions.visibilityOf(supervisorTickIcon));
			Assert.assertTrue(supervisorTickIcon.isDisplayed(), "TickIcon not visible");
			ExtentReportManager.logPass("Selected 'Supervisor' option", driver, false);
			break;
		default:
			ExtentReportManager.logFail("Invalid admin type: " + admin, driver);
			System.out.println("Invalid admin type: " + admin);
			return;
		}

		// Tap on "Continue" button by coordinate percentage
		ExtentReportManager.logInfo("Tapping Continue button via coordinates");
		GenericUtility.tapUsingCoordinatePercentage(driver, chooseAdminlayout, 50.0, 82.0);
		ExtentReportManager.logPass("Clicked Continue button and proceeded to next screen", driver, false);
	}

	public void validateChooseAdminErrorMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ExtentReportManager.logInfo("Validating error message when no admin option selected");
		wait.until(ExpectedConditions.visibilityOf(monitorYourselfTextview));

		GenericUtility.tapUsingCoordinatePercentage(driver, chooseAdminlayout, 50.0, 82.0);
		ExtentReportManager.logInfo("Tapped Continue without selecting admin option");

		Assert.assertTrue(errorMsgText.isDisplayed(),
				"Error message is not displayed when no admin option is selected.");
		ExtentReportManager.logPass("Error message validation passed", driver, false);
	}
}
