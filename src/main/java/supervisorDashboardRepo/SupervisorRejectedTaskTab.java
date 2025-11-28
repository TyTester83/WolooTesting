package supervisorDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SupervisorRejectedTaskTab extends BasePage {

	public SupervisorRejectedTaskTab(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Rejected\") and contains(@content-desc,\"Tab\")]")
	private WebElement rejectedTaskTab;

	@AndroidFindBy(xpath = "//android.view.View[@index=0 and contains(@content-desc,\"Customize Task\")]")
	private WebElement tasktemplateCard;

	@AndroidFindBy(xpath = "//android.widget.HorizontalScrollView")
	private WebElement scrollLayout;
	
	@AndroidFindBy(xpath = "//android.view.View[@index=0 and contains(@content-desc,\"Customize Task\")]/android.view.View")
	private WebElement reassignButton;
	
	public WebElement getRejectedTaskTab() {
		return rejectedTaskTab;
	}

	public WebElement getTasktemplateCard() {
		return tasktemplateCard;
	}

	public WebElement getScrollLayout() {
		return scrollLayout;
	}

	public WebElement getReassignButton() {
		return reassignButton;
	}

	public void reassignBuddyRejectedTask(String buddyName, String taskTime) throws InterruptedException {
		String xpath="//android.view.View[contains(@content-desc,\"Rejected\") and contains(@content-desc,\"Tab\")]";
		if(GenericUtility.swipeAndFindElementByDirection(driver, xpath, scrollLayout, "left", 3))
		{
			wait.until(ExpectedConditions.elementToBeClickable(rejectedTaskTab)).click();	
		}
			Assert.assertTrue(rejectedTaskTab.isSelected(), "Rejected task tab not selected");
			Assert.assertTrue(tasktemplateCard.isDisplayed(), "Rejected task Template is not present");
		boolean taskFound = false;

		while (isElementPresent(tasktemplateCard)) {
			try {
				WebElement card = tasktemplateCard;
				String currentCard = card.getAttribute("content-desc").trim();

				if (currentCard.contains(buddyName) && currentCard.contains(taskTime)) {
					System.out.println("Task matched: " + currentCard);
					taskFound = true;
					reassignButton.click();
					ExtentReportManager.logPass("Reassign Task template is visible");
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
	public void validateRejectedTaskIsVisible(String buddyName, String taskTime) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(rejectedTaskTab));
		
		Assert.assertTrue(rejectedTaskTab.isSelected(), "Rejected task tab not selected");
		Assert.assertTrue(tasktemplateCard.isDisplayed(), "Rejected task Template is not present");
		
		boolean taskFound = false;

		while (isElementPresent(tasktemplateCard)) {
			try {
				WebElement card = tasktemplateCard;
				String currentCard = card.getAttribute("content-desc").trim();

				if (currentCard.contains(buddyName) && currentCard.contains(taskTime)) {
					System.out.println("Task matched: " + currentCard);
					taskFound = true;
					Assert.assertTrue(card.isDisplayed(), currentCard+"Not matching");
					ExtentReportManager.logPass("Rejected Task template is visible"+buddyName+taskTime);
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
}
