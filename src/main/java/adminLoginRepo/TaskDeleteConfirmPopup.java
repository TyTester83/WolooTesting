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

public class TaskDeleteConfirmPopup {

	public AppiumDriver driver;

	public TaskDeleteConfirmPopup(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Are you sure you want to delete the current time?\"]")
	private WebElement deleteText;

	@AndroidFindBy(accessibility = "No")
	private WebElement noButton;

	@AndroidFindBy(accessibility = "Yes")
	private WebElement yesButton;

	public WebElement getDeleteText() {
		return deleteText;
	}

	public WebElement getNoButton() {
		return noButton;
	}

	public WebElement getYesButton() {
		return yesButton;
	}
	
	public void tapYesButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(deleteText));

		SoftAssertValidationUtility.verifyElementVisible(deleteText,"Text should be visible");
		SoftAssertValidationUtility.verifyElementVisible(noButton,"No button should be visible");
		SoftAssertValidationUtility.verifyElementVisible(yesButton, "Yes button should be visible");
		
		yesButton.click();
		ExtentReportManager.logPass("Task Delete popup Ui validated");
	}
}
