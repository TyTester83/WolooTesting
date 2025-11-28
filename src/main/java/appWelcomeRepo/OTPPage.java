package appWelcomeRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utilities.BasePage;
import Utilities.SoftAssertValidationUtility;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class OTPPage extends BasePage {

	public OTPPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@index=0]")
	private WebElement taskmasterLogo;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"Please enter the verification code\")]")
	private WebElement editMobNoIcon;

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement otpTextfield;

	@AndroidFindBy(accessibility = "Resend OTP")
	private WebElement resendOTPButton;

	@AndroidFindBy(accessibility = "Submit")
	private WebElement submitButton;

	@AndroidFindBy(accessibility = "Didn’t receive OTP code?")
	private WebElement otpCodeText;

	public WebElement getTaskmasterLogo() {
		return taskmasterLogo;
	}

	public WebElement getEditMobNoIcon() {
		return editMobNoIcon;
	}

	public WebElement getOtpTextfield() {
		return otpTextfield;
	}

	public WebElement getResendOTPButton() {
		return resendOTPButton;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getOtpCodeText() {
		return otpCodeText;
	}

	public void otpUIvalidation() {
		wait.until(ExpectedConditions.visibilityOf(submitButton));
		ExtentReportManager.logInfo("Validating OTP screen UI elements");

		SoftAssertValidationUtility.verifyElementVisible(taskmasterLogo,
				"TaskMaster logo should be visible on OTP screen.");
//		ExtentReportManager.logInfo("TaskMaster logo is visible");

		SoftAssertValidationUtility.verifyElementVisible(editMobNoIcon, "Edit mobile number icon should be visible.");
//		ExtentReportManager.logInfo("Edit mobile number icon is visible");

		SoftAssertValidationUtility.verifyElementVisible(otpTextfield, "OTP text field should be visible.");
//		ExtentReportManager.logInfo("OTP text field is visible");

		SoftAssertValidationUtility.verifyElementVisible(submitButton, "Submit button should be visible.");
//		ExtentReportManager.logInfo("Submit button is visible");

		SoftAssertValidationUtility.verifyElementVisible(otpCodeText, "Instructional OTP text should be visible.");
//		ExtentReportManager.logInfo("Instructional OTP text is visible");
		ExtentReportManager.logPass("OTP UI validation completed", driver, false);
	}

	public void enterOTP(String otp) {
//		ExtentReportManager.logInfo("Verifying Submit button visible before entering OTP");
		try {
			if (!gUtil.isElementVisible(submitButton, driver)) {
				ExtentReportManager.logFail("Submit button is not visible — cannot proceed to enter OTP", driver);
				throw new IllegalStateException("Submit button not visible on OTP page");
			}
			ExtentReportManager.logPass("Submit button is visible before entering OTP", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("Submit button visibility check failed", e, driver);
			throw e;
		}

		// Verify OTP text field is present before attempting to enter OTP (critical
		// control)
//		ExtentReportManager.logInfo("Verifying OTP text field visible before entering OTP");
		try {
			if (!gUtil.isElementVisible(otpTextfield, driver)) {
				ExtentReportManager.logFail("OTP text field is not visible — cannot proceed to enter OTP", driver);
				throw new IllegalStateException("OTP text field not visible on OTP page");
			}
			ExtentReportManager.logPass("OTP text field is visible before entering OTP", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("OTP text field visibility check failed", e, driver);
			throw e;
		}

//		ExtentReportManager.logInfo("Entering OTP: " + (otp == null ? "null" : "[REACTED]"));
		try {
			gUtil.safeSendKeys(otpTextfield, otp, 3, driver);
			ExtentReportManager.logPass("Entered OTP into field", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("Failed to enter OTP", e, driver);
			throw new RuntimeException("Failed to enter OTP: " + e.getMessage(), e);
		}

//		ExtentReportManager.logInfo("Clicking Submit button on OTP page");
		try {
			if (!gUtil.isElementVisible(submitButton, driver)) {
				ExtentReportManager.logFail("Submit button is not visible before click", driver);
				throw new IllegalStateException("Submit button not visible for final click");
			}
			gUtil.safeClick(submitButton, 3, driver);

			ExtentReportManager.logPass("Clicked Submit button", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("Failed to click Submit button", e, driver);
			throw new RuntimeException("Failed to click Submit button: " + e.getMessage(), e);
		}
	}

}
