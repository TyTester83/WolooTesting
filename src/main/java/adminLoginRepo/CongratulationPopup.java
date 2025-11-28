package adminLoginRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.GenericUtility;
import Utilities.SoftAssertValidationUtility;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CongratulationPopup {

	public AppiumDriver driver;

	public CongratulationPopup(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "Congratulations!")
	private WebElement congratsText;

	@AndroidFindBy(xpath = "//android.widget.ImageView")
	private WebElement congratsImage;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"You have assigned the Task\")]")
	private WebElement taskText;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Tasks scheduled before the current time will start\")]")
	private WebElement taskAlertText;

	@AndroidFindBy(accessibility = "Start Monitoring")
	private WebElement monitorButton;

	public WebElement getCongratsText() {
		return congratsText;
	}

	public WebElement getCongratsImage() {
		return congratsImage;
	}

	public WebElement getTaskText() {
		return taskText;
	}

	public WebElement getTaskAlertText() {
		return taskAlertText;
	}

	public WebElement getMonitorButton() {
		return monitorButton;
	}

	public void congratsUIvalidation() {
		ExtentReportManager.logInfo("Validating Congratulation popup UI elements");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(congratsText));
//		ExtentReportManager.logPass("Congratulations text is visible");
		GenericUtility gUtil=new GenericUtility();

		SoftAssertValidationUtility.verifyElementVisible(congratsImage, "Congrats image is not displayed.");
//		ExtentReportManager.logPass("Congrats image is visible");
		SoftAssertValidationUtility.verifyElementVisible(taskText, "Task text is not displayed.");
//		ExtentReportManager.logPass("Task text is visible");
		SoftAssertValidationUtility.verifyElementVisible(taskAlertText, "Task alert text is not displayed.");
//		ExtentReportManager.logPass("Task alert text is visible");
		SoftAssertValidationUtility.verifyElementVisible(monitorButton, "Monitor button is not displayed.");
//		ExtentReportManager.logPass("Monitor button is visible");

		gUtil.safeClick(monitorButton, 3, driver);
		ExtentReportManager.logPass("Clicked Monitor button");
	}
}
