package taskBuddyDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BuddyRejectedTaskTab {

	public AppiumDriver driver;

	public BuddyRejectedTaskTab(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Rejected\") and contains(@content-desc,\"Tab\")]")
	private WebElement rejectedTaskTab;

	@AndroidFindBy(xpath = "//android.view.View[@index=0 and contains(@content-desc,\"Customize Task\")]")
	private WebElement tasktemplateCard;

	@AndroidFindBy(xpath = "//android.widget.HorizontalScrollView")
	private WebElement scrollLayout;
	
	public WebElement getRejectedTaskTab() {
		return rejectedTaskTab;
	}

	public WebElement getTasktemplateCard() {
		return tasktemplateCard;
	}

	public WebElement getScrollLayout() {
		return scrollLayout;
	}

	public void validateRejectedTaskIsDisplayed(String facilityName, String taskTime) throws InterruptedException {	
		String xpath = "//android.view.View[contains(@content-desc,\"Rejected\") and contains(@content-desc,\"Tab\")]";
	    boolean found = GenericUtility.swipeAndFindElementByDirection(driver, xpath, scrollLayout, "left", 3);

	    if (found) {
	        rejectedTaskTab.click();
	        Assert.assertTrue(rejectedTaskTab.isSelected(), "Rejected task tab not selected");
	        Assert.assertTrue(tasktemplateCard.isDisplayed(), "Rejected task Template is not present");
	    } else {
	        Assert.fail("Rejected Task tab not found after swiping.");
	    }

		boolean taskFound = false;

		while (isElementPresent(tasktemplateCard)) {
			try {
				WebElement card = tasktemplateCard;
				String currentCard = card.getAttribute("content-desc").trim();

				if (currentCard.contains(facilityName) && currentCard.contains(taskTime)) {
					System.out.println("Task matched: " + currentCard);
					taskFound = true;
					Assert.assertTrue(tasktemplateCard.isDisplayed(), "Expected task Template is not present");
					ExtentReportManager.logPass("Rejected task template is Visible :"+facilityName+taskTime, driver, false);
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
