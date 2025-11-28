package supervisorDashboardRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class JanitorDetailsPage extends BasePage {

	public JanitorDetailsPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(accessibility = "Back")
	private WebElement backButton;

	@AndroidFindBy(accessibility = "Janitor Details")
	private WebElement janitorDetailsText;

	@AndroidFindBy(accessibility = "History")
	private WebElement historyButton;

	@AndroidFindBy(accessibility = "Check In")
	private WebElement checkInText;

	@AndroidFindBy(accessibility = "Check Out")
	private WebElement checkOutText;

	@AndroidFindBy(accessibility = "Today")
	private WebElement todayButton;

	@AndroidFindBy(accessibility = "7 days")
	private WebElement sevenDaysButton;

	@AndroidFindBy(accessibility = "custom")
	private WebElement customButton;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\" Total Task\")]")
	private WebElement totalTaskText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Pending Task\"]/following-sibling::android.view.View[2]")
	private WebElement pendingTaskCountText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Rejected Task\"]/following-sibling::android.view.View[2]")
	private WebElement rejectedTaskCountText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Accpeted Task\"]/following-sibling::android.view.View[2]")
	private WebElement acceptedTaskCountText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\" Completed Task\"]/following-sibling::android.view.View[2]")
	private WebElement completedTaskCountText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Ongoing Task\"]/following-sibling::android.view.View[2]")
	private WebElement onGoingTaskCountText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Request for Closure Task\"]/following-sibling::android.view.View[2]")
	private WebElement closureTaskCountText;

	@AndroidFindBy(xpath = "//android.widget.HorizontalScrollView")
	private WebElement taskScrollLayout;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Check In\"]/following-sibling::android.view.View[2]")
	private WebElement checkInTimeText;

	@AndroidFindBy(xpath = "//android.widget.ScrollView")
	private WebElement verticalScrollLayout;
	
	public WebElement getVerticalScrollLayout() {
		return verticalScrollLayout;
	}

	public WebElement getBackButton() {
		return backButton;
	}

	public WebElement getJanitorDetailsText() {
		return janitorDetailsText;
	}

	public WebElement getHistoryButton() {
		return historyButton;
	}

	public WebElement getCheckInText() {
		return checkInText;
	}

	public WebElement getCheckOutText() {
		return checkOutText;
	}

	public WebElement getTodayButton() {
		return todayButton;
	}

	public WebElement getSevenDaysButton() {
		return sevenDaysButton;
	}

	public WebElement getCustomButton() {
		return customButton;
	}

	public WebElement getTotalTaskText() {
		return totalTaskText;
	}

	public WebElement getPendingTaskCountText() {
		return pendingTaskCountText;
	}

	public WebElement getRejectedTaskCountText() {
		return rejectedTaskCountText;
	}

	public WebElement getAcceptedTaskCountText() {
		return acceptedTaskCountText;
	}

	public WebElement getCompletedTaskCountText() {
		return completedTaskCountText;
	}

	public WebElement getOnGoingTaskCountText() {
		return onGoingTaskCountText;
	}

	public WebElement getClosureTaskCountText() {
		return closureTaskCountText;
	}

	public WebElement getTaskScrollLayout() {
		return taskScrollLayout;
	}
	
	public WebElement getCheckInTimeText() {
		return checkInTimeText;
	}

	public void janitorsDetailValidation(String buddyName, String mobileNo) {
		wait.until(ExpectedConditions.visibilityOf(janitorDetailsText));

		Assert.assertTrue(historyButton.isDisplayed(), "History icon should be visible");
		Assert.assertTrue(backButton.isDisplayed(), "back button should be visible");
		Assert.assertTrue(checkInText.isDisplayed(), "Check In text should be visible");
		Assert.assertTrue(checkOutText.isDisplayed(), "Check out text should be visible");

		WebElement actualMobileNo = driver
				.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + mobileNo + "')]"));
		Assert.assertTrue(actualMobileNo.isDisplayed(), "Valid Buddy mobile number should be displayed");

		WebElement actualBuddyName = driver
				.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + buddyName + "')]"));
		Assert.assertTrue(actualBuddyName.isDisplayed(), "Valid Buddy Name should be displayed");
		
		
	}
	public void janitorsTaskValidation()
	{
		wait.until(ExpectedConditions.visibilityOf(totalTaskText));
		
		String xpath="//android.view.View[@content-desc=\"Request for Closure Task\"]/following-sibling::android.view.View[1]";
		GenericUtility.swipeAndFindElementByDirection(driver, xpath, verticalScrollLayout, "up", 3);
		
		int completeTask = Integer.parseInt(completedTaskCountText.getAttribute("content-desc"));
		int acceptTask = Integer.parseInt(acceptedTaskCountText.getAttribute("content-desc"));
		int onGoingTask = Integer.parseInt(onGoingTaskCountText.getAttribute("content-desc"));
		int pendingTask = Integer.parseInt(pendingTaskCountText.getAttribute("content-desc"));
		int rejectedTask = Integer.parseInt(rejectedTaskCountText.getAttribute("content-desc"));
		int closureTask = Integer.parseInt(closureTaskCountText.getAttribute("content-desc"));

		int actualTotalTaskCount = completeTask + acceptTask + onGoingTask + pendingTask + rejectedTask + closureTask;
		String expectedTotalTaskCount = totalTaskText.getAttribute("content-desc");
		boolean actualEff = expectedTotalTaskCount.contains(String.valueOf(actualTotalTaskCount));
		Assert.assertTrue(actualEff,
				"Actual task count does not match. Expected: " + expectedTotalTaskCount + "but was: " + actualTotalTaskCount);
		 ExtentReportManager.logPass("Janitor Task validated successfully");
	}
	
	public void janitorsShiftTimeValidattion(String shiftTime)
	{
		wait.until(ExpectedConditions.visibilityOf(janitorDetailsText));
		String time12 = convertTo12HourFormat(shiftTime).toUpperCase();
		String actualShiftIn=checkInTimeText.getAttribute("content-desc").trim();
		System.out.println("<----------------->"+shiftTime);
		Assert.assertTrue(actualShiftIn.contains(time12), time12+" is not displayed");
		ExtentReportManager.logPass("Janitor Shift Time validated successfully");
		
	}
	public String convertTo12HourFormat(String time24) {
        try {
            // Input: 24-hour format (HH:mm)
            SimpleDateFormat inputFormat = new SimpleDateFormat("H:m:s");

            // Output: 12-hour format (hh:mm a)
            SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a");

            // Parse and convert
            Date date = inputFormat.parse(time24);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid time format";
        }
	}
}
