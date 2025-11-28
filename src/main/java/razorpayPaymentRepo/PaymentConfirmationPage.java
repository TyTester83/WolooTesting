package razorpayPaymentRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import Utilities.SoftAssertValidationUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PaymentConfirmationPage {

	public AppiumDriver driver;

	public PaymentConfirmationPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Welcome to Razorpay Software Private Ltd Bank\"]")
	private WebElement razorpayText;

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"Success\"]")
	private WebElement successButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"Failure\"]")
	private WebElement failureButton;

	@AndroidFindBy(xpath = "//android.widget.ImageView")
	private WebElement tickImage;

	@AndroidFindBy(accessibility = "Your TASKMASTER Facility is now active")
	private WebElement confirmText;

	@AndroidFindBy(accessibility = "Go to Home")
	private WebElement homeButton;

	public WebElement getRazorpayText() {
		return razorpayText;
	}

	public WebElement getSuccessButton() {
		return successButton;
	}

	public WebElement getFailureButton() {
		return failureButton;
	}

	public WebElement getTickImage() {
		return tickImage;
	}

	public WebElement getConfirmText() {
		return confirmText;
	}

	public WebElement getHomeButton() {
		return homeButton;
	}

	public void confirmationUIValidation() throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(razorpayText));
		
		SoftAssertValidationUtility.verifyElementVisible(successButton,"success button not visible");
		SoftAssertValidationUtility.verifyElementVisible(failureButton,"failure button not visible");
		
		wait.until(ExpectedConditions.elementToBeClickable(successButton)).click();
		wait.until(ExpectedConditions.visibilityOf(tickImage));
		
		SoftAssertValidationUtility.verifyElementVisible(confirmText,"confirmText not visible");
		SoftAssertValidationUtility.verifyElementVisible(homeButton,"home button not visible");
		
		GenericUtility gUtil=new GenericUtility();
		gUtil.safeClick(homeButton, 4,driver);
		ExtentReportManager.logPass("Payment UI validated", driver, false);
//		wait.until(ExpectedConditions.elementToBeClickable(homeButton)).click();
	}
	
}
