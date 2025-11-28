package taskBuddyDashboardRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class OnGoingTab {

	public AppiumDriver driver;

	public OnGoingTab(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Ongoing\") and contains(@content-desc,\"Tab\")]")
	private WebElement ongoingTaskTab;
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Customize Task\")]/following-sibling::android.view.View[2]")
	private WebElement rightSwipeButton;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Customize Task\")]/following-sibling::android.view.View[1]")
	private WebElement leftSwipeButton;

	@AndroidFindBy(xpath = "//android.view.View[@index=0 and contains(@content-desc,\"Customize Task\")]")
	private WebElement tasktemplateCard;

	@AndroidFindBy(xpath = "//android.view.View[@index=0 and contains(@content-desc,\"Customize Task\")]/android.widget.ImageView[@content-desc=\"Request for Closure\"]")
	private WebElement requestClosureButton;

	public WebElement getOngoingTaskTab() {
		return ongoingTaskTab;
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

	public WebElement getRequestClosureButton() {
		return requestClosureButton;
	}
	public void tapRequestClosureButton(String facilityName, String taskTime) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(ongoingTaskTab));
		ongoingTaskTab.click();
		Assert.assertTrue(ongoingTaskTab.isSelected(), "Accepted task tab not selected");
		Assert.assertTrue(tasktemplateCard.isDisplayed(), "Accepted task Template is not present");
		boolean taskFound = false;
	
		while (isElementPresent(tasktemplateCard)) {
			try {
				WebElement card = tasktemplateCard;
				String currentCard = card.getAttribute("content-desc").trim();

				if (currentCard.contains(facilityName) && currentCard.contains(taskTime)) {
					System.out.println("Task matched: " + currentCard);
					taskFound = true;
					Assert.assertTrue(tasktemplateCard.isDisplayed(), "Expected task Template is not present");
					requestClosureButton.click();
					ExtentReportManager.logPass("Tapped Closure button from Ongoing task template :"+facilityName+taskTime, driver, false);
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
	public void validateOngoingTaskIsVisible(String facilityName, String taskTime) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(ongoingTaskTab));
		Assert.assertTrue(ongoingTaskTab.isSelected(), "Accepted task tab not selected");
		Assert.assertTrue(tasktemplateCard.isDisplayed(), "Accepted task Template is not present");
		boolean taskFound = false;
	
		while (isElementPresent(tasktemplateCard)) {
			try {
				WebElement card = tasktemplateCard;
				String currentCard = card.getAttribute("content-desc").trim();

				if (currentCard.contains(facilityName) && currentCard.contains(taskTime)) {
					System.out.println("Task matched: " + currentCard);
					taskFound = true;
					Assert.assertTrue(tasktemplateCard.isDisplayed(), "Expected task Template is not present");
					ExtentReportManager.logPass("Valid Ongoing task template is visible :"+facilityName+taskTime, driver, false);
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
