package supervisorDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SupervisorRequestForClosureTab extends BasePage {

	public SupervisorRequestForClosureTab(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Request for Closure\") and contains(@content-desc,\"Tab\")]")
	private WebElement requestforClosureTab;

	@AndroidFindBy(xpath = "//android.view.View[@index=0 and contains(@content-desc,\"Customize Task\")]")
	private WebElement tasktemplateCard;
	
	@AndroidFindBy(xpath = "//android.view.View[@index=0 and contains(@content-desc,\"Customize Task\")]/android.view.View")
	private WebElement taskDetailsButton;

	public WebElement getRequestforClosureTab() {
		return requestforClosureTab;
	}

	public WebElement getTasktemplateCard() {
		return tasktemplateCard;
	}

	public WebElement getTaskDetailsButton() {
		return taskDetailsButton;
	}

	public void tapRequestedTaskDetailsButton(String buddyName, String taskTime) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(requestforClosureTab));
		requestforClosureTab.click();
		Assert.assertTrue(requestforClosureTab.isSelected(), "Requested task tab not selected");
		Assert.assertTrue(tasktemplateCard.isDisplayed(), "Requested task Template is not present");
		boolean taskFound = false;

		while (isElementPresent(tasktemplateCard)) {
			try {
				WebElement card = tasktemplateCard;
				String currentCard = card.getAttribute("content-desc").trim();

				if (currentCard.contains(buddyName) && currentCard.contains(taskTime)) {
					System.out.println("Task matched: " + currentCard);
					taskFound = true;
					Assert.assertTrue(tasktemplateCard.isDisplayed(), "Expected task Template is not present");
					taskDetailsButton.click();
					ExtentReportManager.logPass("Taskbuddy Closure Task template is visible"+buddyName+taskTime);
					break;
				} else {
					WebElement swipeBtn = tasktemplateCard;
					if (swipeBtn.isDisplayed()) {
						GenericUtility.swipeHorizontally(driver, swipeBtn);
						Thread.sleep(500); // allow time for UI to refresh
					} else {
						System.out.println("Right swipe button disappeared.");
						break;
					}
				}

			} catch (Exception e) {
				System.out.println("Exception while checking card: " + e.getMessage());
				Thread.sleep(500);
			}
		}

		if (!taskFound) {
			System.out.println("Task not found while template was present.");
		}
	}

	public boolean isElementPresent(WebElement ele) {
		try {
			return ele.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
