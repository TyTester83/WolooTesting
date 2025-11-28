package supervisorReassignRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class JanitorSchedulePage {

	public AppiumDriver driver;

	public JanitorSchedulePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "Back")
	private WebElement backButton;
	
	@AndroidFindBy(accessibility = "Janitor Schedule")
	private WebElement janitorScheduleText;

	@AndroidFindBy(accessibility = "Tasks Allocation")
	private WebElement taslAllocationText;
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView")
	private WebElement taskScrollLayout;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Task name\"]")
	private WebElement taskNameText;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Start Time\"]//following-sibling::android.widget.EditText")
	private WebElement startTimeTextfield;
	
	@AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"Assign Tasks\"])[2]")
	private WebElement assignTaskButton;

	
	public void reassignTasktoJanitor(String taskTime) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(janitorScheduleText));
		
		wait.until(ExpectedConditions.elementToBeClickable(startTimeTextfield)).click();
		ReassignTaskTimePopup taskTimePopup=new ReassignTaskTimePopup(driver);
		taskTimePopup.selectTaskTime(taskTime);
		wait.until(ExpectedConditions.elementToBeClickable(assignTaskButton)).click();
		
		TaskAssignConfirmPopup taskConfirmPopup=new TaskAssignConfirmPopup(driver);
		taskConfirmPopup.tapDoneButton();
		ExtentReportManager.logPass("Reassigned task to Janitor for this time "+taskTime, driver, false);
	}
}
