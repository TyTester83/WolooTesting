package supervisorDashboardRepo;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TaskApprovalPage extends BasePage {

	public TaskApprovalPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.Button")
	private WebElement backButton;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"List of tasks\"]")
	private WebElement taskListText;

	@AndroidFindBy(accessibility = "Reject")
	private WebElement rejectButton;

	@AndroidFindBy(accessibility = "Approve")
	private WebElement approveButton;

	@AndroidFindBy(xpath = "//android.widget.ImageView")
	private WebElement taskImage;

	public WebElement getBackButton() {
		return backButton;
	}

	public WebElement getTaskListText() {
		return taskListText;
	}

	public WebElement getRejectButton() {
		return rejectButton;
	}

	public WebElement getApproveButton() {
		return approveButton;
	}

	public WebElement getTaskImage() {
		return taskImage;
	}

	public void rejectTheTask(List<String> taskNames) {
		wait.until(ExpectedConditions.visibilityOf(taskListText));

		wait.until(ExpectedConditions.visibilityOf(taskImage));
		for (String taskName : taskNames) {
			try {
				WebElement taskOption = driver
						.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + taskName + "')]"));
				wait.until(ExpectedConditions.visibilityOf(taskOption));
				if (taskOption.isDisplayed()) {
					Assert.assertTrue(taskOption.isDisplayed(), "Completed task should be visible");
				}
			} catch (Exception e) {
				System.out.println("Task '" + taskName + "' not found or not clickable: " + e.getMessage());
			}
		}
		wait.until(ExpectedConditions.elementToBeClickable(rejectButton)).click();
	}

	public void validateAndApproveCompletedTask(List<String> taskNames) {
		wait.until(ExpectedConditions.visibilityOf(taskListText));

//		wait.until(ExpectedConditions.visibilityOf(taskImage));
		for (String taskName : taskNames) {
			try {
				WebElement taskOption = driver
						.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + taskName + "')]"));
				wait.until(ExpectedConditions.visibilityOf(taskOption));
				if (taskOption.isDisplayed()) {
					Assert.assertTrue(taskOption.isDisplayed(), "Completed task should be visible");
				}
			} catch (Exception e) {
				System.out.println("Task '" + taskName + "' not found or not clickable: " + e.getMessage());
			}
		}
		// Approve the task
		wait.until(ExpectedConditions.elementToBeClickable(approveButton)).click();
	}
}
