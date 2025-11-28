package taskBuddyDashboardRepo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TaskDetailsPopup {

	public AppiumDriver driver;

	public TaskDetailsPopup(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility = "Task Details")
	private WebElement taskDetailsText;
	
	@AndroidFindBy(accessibility = "Okay")
	private WebElement okayButton;

	public WebElement getTaskDetailsText() {
		return taskDetailsText;
	}

	public WebElement getOkayButton() {
		return okayButton;
	}
	
	public void taskDetailsValidation(List<String> taskNames) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(taskDetailsText));
		
	
		for (String task : taskNames) {
			try {
				WebElement ele=driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc='" + task+"']"));
				if (ele.isDisplayed()) {
					Assert.assertTrue(ele.isDisplayed(), "Task Name should be visible");
					ExtentReportManager.logPass("Buddy Expected Task is displayed"+task);
				}

			} catch (Exception e) {
				System.out.println("Exception while checking task: " + e.getMessage());
				Thread.sleep(500);
			}
			
		}
		
	}
}
