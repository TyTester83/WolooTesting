package taskBuddyDashboardRepo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TaskPage {

	public AppiumDriver driver;

	public TaskPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Back\"]")
	private WebElement backButton;

	@AndroidFindBy(accessibility = "Task")
	private WebElement taskText;

	@AndroidFindBy(accessibility = "Submit")
	private WebElement submitButton;

	public WebElement getBackButton() {
		return backButton;
	}

	public WebElement getTaskText() {
		return taskText;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public void chooseAssignedTask(List<String> taskNames) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Wait for the task section to be visible
		wait.until(ExpectedConditions.visibilityOf(taskText));

		for (String taskName : taskNames) {
			try {

				WebElement taskOption = driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'"+taskName+"')]/preceding-sibling::android.view.View"));

				// Wait for the task option and click it
				wait.until(ExpectedConditions.visibilityOf(taskOption));
				if (taskOption.isDisplayed()) {
					taskOption.click();
				}
			} catch (Exception e) {
				System.out.println("Task '" + taskName + "' not found or not clickable: " + e.getMessage());
			}
		}

		// Submit after selecting tasks
		wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
		ExtentReportManager.logPass("Buddy Assigned Task Selected");
	}
}
