package appWelcomeRepo;

import java.util.regex.Pattern;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import Utilities.SoftAssertValidationUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BasePage {

	public LoginPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView")
	private WebElement taskMasterLogo;

	@AndroidFindBy(accessibility = "Back")
	private WebElement backButton;

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement mobNoTextfield;

	@AndroidFindBy(accessibility = "Mobile number is required")
	private WebElement errorMsg1;

	@AndroidFindBy(accessibility = "Enter a valid 10-digit number")
	private WebElement errorMsg2;

	@AndroidFindBy(accessibility = "Send OTP")
	private WebElement sendOTPButton;

	@AndroidFindBy(accessibility = "Please read our")
	private WebElement readText;

	@AndroidFindBy(accessibility = "Privacy Policy")
	private WebElement policyLink;

	public WebElement getTaskMasterLogo() {
		return taskMasterLogo;
	}

	public WebElement getBackButton() {
		return backButton;
	}

	public WebElement getMobNoTextfield() {
		return mobNoTextfield;
	}

	public WebElement getErrorMsg1() {
		return errorMsg1;
	}

	public WebElement getErrorMsg2() {
		return errorMsg2;
	}

	public WebElement getSendOTPButton() {
		return sendOTPButton;
	}

	public WebElement getReadText() {
		return readText;
	}

	public WebElement getPolicyLink() {
		return policyLink;
	}

	public void loginUIValidation() {
		waitForVisibility(taskMasterLogo);

		ExtentReportManager.logInfo("Validating Login screen UI elements");

		
			SoftAssertValidationUtility.verifyElementVisible(taskMasterLogo,
					"TaskMaster logo should be visible on Login screen.");
//			ExtentReportManager.logInfo("TaskMaster logo is visible");
		

		
			SoftAssertValidationUtility.verifyElementVisible(mobNoTextfield,
					"Mobile number field should be visible on Login screen.");
//			ExtentReportManager.logInfo("Mobile number field is visible");
		

		
			SoftAssertValidationUtility.verifyElementVisible(sendOTPButton,
					"Send OTP button should be visible on Login screen.");
//			ExtentReportManager.logInfo("Send OTP button is visible");
		
		
			SoftAssertValidationUtility.verifyElementVisible(readText, "Info text should be visible on Login screen.");
//			ExtentReportManager.logInfo("Info text is visible");
		

		
			SoftAssertValidationUtility.verifyElementVisible(policyLink,
					"Privacy policy link should be visible on Login screen.");
//			ExtentReportManager.logInfo("Privacy policy link is visible");
			ExtentReportManager.logPass("Login UI validation completed", driver, false);


	}

	/**
	 * Enter mobile number into input field with validation and reporting.
	 */
	public void enterMobileNumber(String mobileNumber) {
		if (mobileNumber == null) {
			ExtentReportManager.logFail("Provided mobile number is null", driver);
			throw new IllegalArgumentException("mobileNumber must not be null");
		}

		String cleaned = mobileNumber.trim();
		if (!Pattern.matches("\\d{10}", cleaned)) {
			ExtentReportManager.logFail("Invalid mobile number format provided: " + mobileNumber, driver);
			throw new IllegalArgumentException("mobileNumber must be a 10 digit number: " + mobileNumber);
		}

//		ExtentReportManager.logInfo("Entering mobile number: " + cleaned);
		try {
			gUtil.safeSendKeys(mobNoTextfield, cleaned, 8, driver);
			ExtentReportManager.logPass("Entered mobile number into field", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("Failed to enter mobile number", e, driver);
			throw new RuntimeException("Failed to enter mobile number: " + e.getMessage(), e);
		}
	}

	/**
	 * Click Send OTP and report the action with post-click verification.
	 */
	public void clickSendOTP() {
//		ExtentReportManager.logInfo("Clicking Send OTP button");
		try {
			if (!gUtil.isElementVisible(sendOTPButton, driver)) {
				ExtentReportManager.logFail("Send OTP button is not visible — cannot click", driver);
				throw new IllegalStateException("Send OTP button not visible");
			}
			gUtil.safeClick(sendOTPButton, 2, driver);
			ExtentReportManager.logPass("Clicked Send OTP button", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("Failed to click Send OTP button", e, driver);
			throw new RuntimeException("Failed to click Send OTP button: " + e.getMessage(), e);
		}
	}

	/**
	 * Performs a combined login action (enter mobile + click send OTP) with reporting.
	 */
	public void login(String mobNo) {
//		ExtentReportManager.logInfo("Starting login flow");
		try {
			enterMobileNumber(mobNo);
			clickSendOTP();
			ExtentReportManager.logPass("Login flow executed (entered mobile and clicked Send OTP)", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("Login flow failed", e, driver);
			throw e;
		}
	}

	/**
	 * Returns first visible error message text if present, otherwise null.
	 */
	public String getVisibleErrorMessage() {
		try {
			if (gUtil.isElementVisible(errorMsg1, driver)) {
				String text = errorMsg1.getText();
				ExtentReportManager.logInfo("Visible error message found: " + text);
				return text;
			}
		} catch (NoSuchElementException e) {
			// ignore
		}

		try {
			if (gUtil.isElementVisible(errorMsg2, driver)) {
				String text = errorMsg2.getText();
				ExtentReportManager.logInfo("Visible error message found: " + text);
				return text;
			}
		} catch (NoSuchElementException e) {
			// ignore
		}

		ExtentReportManager.logInfo("No visible error message found on Login screen");
		return null;
	}
	
	private WebElement getCorporateName(String corporateName)
    {
        String xpath="//android.widget.Button[contains(@content-desc,'"+corporateName+"')]";
        return driver.findElement(AppiumBy.xpath(xpath));
    }
	
	public synchronized  void waitForInvisiblilityOfElement(WebElement element, String elementName) {
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
           System.out.println("fail");
        }
    }

}
