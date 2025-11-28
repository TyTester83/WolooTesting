package adminDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utilities.BasePage;
import Utilities.SoftAssertValidationUtility;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AssignNewTaskPopup extends BasePage {

	public AssignNewTaskPopup(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(accessibility = "Do you want to assign a new Task Buddy or utilise your existing Task Buddy?")
	private WebElement taskbuddyText;

	@AndroidFindBy(accessibility = "Assign New Task Buddy")
	private WebElement assignNewTaskbuddyButton;

	@AndroidFindBy(accessibility = "Assign Existing Task Buddy")
	private WebElement assignExistingTaskbuddyButton;

	public WebElement getTaskbuddyText() {
		return taskbuddyText;
	}

	public WebElement getAssignNewTaskbuddyButton() {
		return assignNewTaskbuddyButton;
	}

	public WebElement getAssignExistingTaskbuddyButton() {
		return assignExistingTaskbuddyButton;
	}

	public void newTaskPopupUIValidation() {
		ExtentReportManager.logInfo("Validating Assign Task Buddy popup UI elements");
		wait.until(ExpectedConditions.visibilityOf(taskbuddyText));
		ExtentReportManager.logPass("Task buddy text is visible");

		SoftAssertValidationUtility.verifyElementVisible(assignNewTaskbuddyButton,assignNewTaskbuddyButton+"Element to be visible");
		ExtentReportManager.logPass("Assign New Task Buddy button is visible");
		SoftAssertValidationUtility.verifyElementVisible(assignExistingTaskbuddyButton,assignExistingTaskbuddyButton+"Element to be visible");
		ExtentReportManager.logPass("Assign Existing Task Buddy button is visible");
	}
	
	public void tapTaskbuddy(String taskbuddy)
	{
		ExtentReportManager.logInfo("Selecting task buddy option: " + taskbuddy);
		wait.until(ExpectedConditions.visibilityOf(taskbuddyText));
		if(taskbuddy.equalsIgnoreCase("New"))
		{
			gUtil.safeClick(assignNewTaskbuddyButton, 3, driver);
			ExtentReportManager.logPass("Clicked Assign New Task Buddy button");
		}
		else
		{
			gUtil.safeClick(assignExistingTaskbuddyButton, 3, driver);
			ExtentReportManager.logPass("Clicked Assign Existing Task Buddy button");
		}
	}
}
