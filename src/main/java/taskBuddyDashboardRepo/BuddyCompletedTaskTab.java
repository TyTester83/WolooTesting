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

public class BuddyCompletedTaskTab {

	public AppiumDriver driver;

	public BuddyCompletedTaskTab(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Completed Task\") and contains(@content-desc,\"Tab\")]")
	private WebElement completedTaskTab;

	@AndroidFindBy(xpath = "//android.view.View[@index=0 and contains(@content-desc,\"Customize Task\")]")
	private WebElement tasktemplateCard;

	@AndroidFindBy(xpath = "//android.widget.HorizontalScrollView")
	private WebElement scrollLayout;
	
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc,\"Regular Task\")]")
	private WebElement regularTaskTab;
	
	public void verifyCompletedTaskIsDisplayed(String facilityName, String taskTime) throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.elementToBeClickable(regularTaskTab)).click();
	    wait.until(ExpectedConditions.visibilityOf(scrollLayout));

	    String xpath = "//android.view.View[contains(@content-desc,\"Completed Task\")]";
	    boolean found = GenericUtility.swipeAndFindElementByDirection(driver, xpath, scrollLayout, "left", 3);

	    if (found) {
	        completedTaskTab.click();
	        Assert.assertTrue(completedTaskTab.isSelected(), "Completed task tab not selected");
	        Assert.assertTrue(tasktemplateCard.isDisplayed(), "Completed task Template is not present");
	    } else {
	        Assert.fail("Completed Task tab not found after swiping.");
	    }

	    boolean taskFound = false;

	    while (isElementPresent(tasktemplateCard)) {
	        try {
	            WebElement card = tasktemplateCard; // Or re-find element by locator if needed
	            String currentCard = card.getAttribute("content-desc").trim();

	            if (currentCard.contains(facilityName) && currentCard.contains(taskTime)) {
	                System.out.println("Task matched: " + currentCard);
	                taskFound = true;
	                Assert.assertTrue(card.isDisplayed(), "Expected task Template is not present");
	                ExtentReportManager.logPass("Completed task template is displayed :"+facilityName+taskTime, driver, false);
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
	        Assert.fail("Task not found in completed tasks: " + facilityName + " at " + taskTime);
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
