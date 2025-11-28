package adminLoginRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExtentReportManager;
import Utilities.SoftAssertValidationUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TaskTimingConfirmPopup {

	public AppiumDriver driver;

	public TaskTimingConfirmPopup(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView")
	private WebElement tickImage;

	@AndroidFindBy(accessibility = "Task timing has been saved successfully")
	private WebElement taskConfirmMsgText;

	@AndroidFindBy(accessibility = "Okay, Thanks!")
	private WebElement okButton;

	public WebElement getTickImage() {
		return tickImage;
	}

	public WebElement getTaskConfirmMsgText() {
		return taskConfirmMsgText;
	}

	public WebElement getOkButton() {
		return okButton;
	}

	public void confirmPopupUIValidation()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(taskConfirmMsgText));
		ExtentReportManager.logInfo("validating Popup Ui ");
		SoftAssertValidationUtility.verifyElementVisible(tickImage, tickImage+ "image should be visible");
		SoftAssertValidationUtility.verifyElementVisible(taskConfirmMsgText, taskConfirmMsgText+ "Text should be visible");
		SoftAssertValidationUtility.verifyElementVisible(okButton, okButton+ "image should be visible");
		
		wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
		ExtentReportManager.logPass("Accepted task confirm Popup");
	}
}
