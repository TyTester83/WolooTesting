package supervisorDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PendingTaskTab extends BasePage {

	public PendingTaskTab(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Pending task\")]")
	private WebElement pendingTaskTab;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Customize Task\")]/following-sibling::android.view.View[2]")
	private WebElement rightSwipeButton;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Customize Task\")]/following-sibling::android.view.View[1]")
	private WebElement leftSwipeButton;

	@AndroidFindBy(xpath = "//android.view.View[@index=0 and contains(@content-desc,\"Customize Task\")]")
	private WebElement tasktemplateCard;

	@AndroidFindBy(xpath = "//android.view.View[@index=0 and contains(@content-desc,\"Customize Task\")]/android.view.View")
	private WebElement reassignButton;

	public WebElement getPendingTaskTab() {
		return pendingTaskTab;
	}

	public WebElement getRightSwipeButton() {
		return rightSwipeButton;
	}

	public WebElement getLeftSwipeButton() {
		return leftSwipeButton;
	}

	public WebElement getTasktemplateCard() {
		return tasktemplateCard;
	}

	public WebElement getReassignButton() {
		return reassignButton;
	}

	public boolean isElementPresent(WebElement ele) {
		try {
			return ele.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void reAssignTaskValidation(String facilityName, String taskTime, String buddyName)
			throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(pendingTaskTab));
		Assert.assertTrue(pendingTaskTab.isSelected(), "Pending task tab not selected");
		Assert.assertTrue(tasktemplateCard.isDisplayed(), "Pending task Template is not present");
		boolean taskFound = false;

		while (isElementPresent(tasktemplateCard)) {
			try {
				WebElement card = tasktemplateCard;
				String currentCard = card.getAttribute("content-desc").trim();

				if (currentCard.contains(taskTime) && currentCard.contains(buddyName)) {
					System.out.println("Task matched: " + currentCard);
					taskFound = true;
					Assert.assertTrue(tasktemplateCard.isDisplayed(),
							"Expected reassign Pending task Template is not present");
					ExtentReportManager.logPass("Reassign task template is visible'"+facilityName+"','"+buddyName+"','"+taskTime);
					break;
				} else {
					WebElement swipeBtn = rightSwipeButton;
					if (swipeBtn.isDisplayed()) {
						swipeBtn.click();
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

	public void checkPendingTask(String taskTime, String buddyName) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(pendingTaskTab));
		Assert.assertTrue(pendingTaskTab.isSelected(), "Pending task tab not selected");
		Assert.assertTrue(tasktemplateCard.isDisplayed(), "Pending task Template is not present");
		boolean taskFound = false;
		while (isElementPresent(tasktemplateCard)) {
			try {
				WebElement card = tasktemplateCard;
				String currentCard = card.getAttribute("content-desc").trim();

				if (currentCard.contains(taskTime) && currentCard.contains(buddyName)) {
					System.out.println("Task matched: " + currentCard);
					taskFound = true;
					Assert.assertTrue(tasktemplateCard.isDisplayed(), "Pending task should be displayed");
					ExtentReportManager.logPass("Pending task template is visible for '"+buddyName+"','"+taskTime);
					break;
				} else {
					WebElement swipeBtn = rightSwipeButton;
					if (swipeBtn.isDisplayed()) {
						swipeBtn.click();
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

	public void tapPendingTaskReassignButton(String taskTime, String buddyName) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(pendingTaskTab));
		Assert.assertTrue(pendingTaskTab.isSelected(), "Pending task tab not selected");
		Assert.assertTrue(tasktemplateCard.isDisplayed(), "Pending task Template is not present");
		boolean taskFound = false;
		while (isElementPresent(tasktemplateCard)) {
			try {
				WebElement card = tasktemplateCard;
				String currentCard = card.getAttribute("content-desc").trim();

				if (currentCard.contains(taskTime) && currentCard.contains(buddyName)) {
					System.out.println("Task matched: " + currentCard);
					taskFound = true;
					Assert.assertTrue(tasktemplateCard.isDisplayed(), "Pending task should be displayed");
					reassignButton.click();
					ExtentReportManager.logPass("Tapped Pending task Reassign button for '"+buddyName+"','"+taskTime);
					break;
				} else {
					WebElement swipeBtn = rightSwipeButton;
					if (swipeBtn.isDisplayed()) {
						swipeBtn.click();
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
}
