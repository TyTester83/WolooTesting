package supervisorDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CompletedTaskTab extends BasePage {

	public CompletedTaskTab(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Completed Task\") and contains(@content-desc,\"Tab\")]")
	private WebElement completedTaskTab;

	@AndroidFindBy(xpath = "//android.view.View[@index=0 and contains(@content-desc,\"Customize Task\")]")
	private WebElement tasktemplateCard;

	public WebElement getCompletedTaskTab() {
		return completedTaskTab;
	}

	public WebElement getTasktemplateCard() {
		return tasktemplateCard;
	}

	public void validateCompletedTaskIsDisplayed(String buddyName, String taskTime) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(completedTaskTab));
		completedTaskTab.click();
		Assert.assertTrue(completedTaskTab.isSelected(), "Completed task tab not selected");
		Assert.assertTrue(tasktemplateCard.isDisplayed(), "Completed task Template is not present");
		boolean taskFound = false;

		while (isElementPresent(tasktemplateCard)) {
			try {
				WebElement card = tasktemplateCard;
				String currentCard = card.getAttribute("content-desc").trim();

				if (currentCard.contains(buddyName) && currentCard.contains(taskTime)) {
					System.out.println("Task matched: " + currentCard);
					taskFound = true;
					Assert.assertTrue(tasktemplateCard.isDisplayed(), "Expected task Template is not present");
					ExtentReportManager.logPass("CompletedTask is displayed with '" + taskTime + "'and '"+buddyName);
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
