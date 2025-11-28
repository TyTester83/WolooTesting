package taskBuddyDashboardRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TaskClosurePopup {

	public AppiumDriver driver;

	public TaskClosurePopup(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Do you want to Submit this task?\"]")
	private WebElement taskConfirmText;
	
	@AndroidFindBy(accessibility = "Cancel")
	private WebElement cancelButton;
	
	@AndroidFindBy(accessibility = "Yes")
	private WebElement yesButton;

	public WebElement getTaskConfirmText() {
		return taskConfirmText;
	}

	public WebElement getCancelButton() {
		return cancelButton;
	}

	public WebElement getYesButton() {
		return yesButton;
	}
	 
	public void tapYestoConfirmTaskCompletion()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(taskConfirmText));
		wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
		ExtentReportManager.logPass("Buddy Tapped the Task Closure confirm Button");
	}
}
