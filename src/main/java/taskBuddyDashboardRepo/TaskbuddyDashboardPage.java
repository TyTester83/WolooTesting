package taskBuddyDashboardRepo;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ObjectUtility.CommonUtility;
import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import Utilities.SoftAssertValidationUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TaskbuddyDashboardPage {

	public AppiumDriver driver;

	public TaskbuddyDashboardPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "My Dashboard")
	private WebElement dashboardText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"My Dashboard\"]/following-sibling::android.widget.ImageView[1]")
	private WebElement languageImage;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"My Dashboard\"]/following-sibling::android.widget.ImageView[1]")
	private WebElement profileIconImage;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Present\"]/preceding-sibling::android.view.View[1]")
	private WebElement buddyNameText;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Present\"]/preceding-sibling::android.view.View[2]")
	private WebElement locationText;

	@AndroidFindBy(accessibility = "Present")
	private WebElement presentText;

	@AndroidFindBy(accessibility = "IN")
	private WebElement shiftInButton;
	
	@AndroidFindBy(accessibility = "OUT")
	private WebElement shiftOutButton;
	
	@AndroidFindBy(accessibility = "Earn10 points every day you log in! ")
	private WebElement loginEarnPtsText;
	
	@AndroidFindBy(xpath  = "//android.view.View[@content-desc=\"Earn10 points every day you log in! \"]/preceding-sibling::android.widget.ImageView[1]")
	private WebElement CoinsLogoImage;

	@AndroidFindBy(accessibility = "Please Clock-In to see the list of task")
	private WebElement listTaskText;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"DashBoard\") and contains(@content-desc,\"Tab\")]")
	private WebElement dashboardTab;
	
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc,\"Regular Task\")]")
	private WebElement regularTaskTab;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"IOT Task\")]")
	private WebElement iotTaskTab;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"IN\"]/following-sibling::android.view.View[1]")
	private WebElement shiftInTimeText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"IN\"]/following-sibling::android.view.View[4]")
	private WebElement shiftInDateText;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Pending task\")]")
	private WebElement pendingTaskTab;
	
	@AndroidFindBy(accessibility = "My Performance Score")
	private WebElement performanceText;
	
	@AndroidFindBy(accessibility = "Today")
	private WebElement todayText;
	
	@AndroidFindBy(accessibility = "This week")
	private WebElement weekText;
	
	@AndroidFindBy(accessibility = "This month")
	private WebElement monthText;
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Task Buddy\") and contains(@content-desc,\"Efficiency\")]")
	private WebElement efficiencyChart;
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Efficiency\")]/following-sibling::android.view.View[1]")
	private WebElement completedTaskCountText;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Completed Task \"]/following-sibling::android.view.View[1]")
	private WebElement acceptedTaskCountText;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Accpeted Task \"]/following-sibling::android.view.View[1]")
	private WebElement ongoingTaskCountText;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"On Going  Task \"]/following-sibling::android.view.View[1]")
	private WebElement closureTaskCountText;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Request for Closure \"]/following-sibling::android.view.View[1]")
	private WebElement pendingTaskCountText;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Pending \"]/following-sibling::android.view.View[1]")
	private WebElement rejectedTaskCountText;
	
	@AndroidFindBy(accessibility =  "Complete tasks before time and earn 10 points")
	private WebElement completeTaskPtsText;
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[1]")
	private WebElement taskBuddyProfileImage;
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView")
	private WebElement dashboardScrollLayout;
	

	public WebElement getDashboardText() {
		return dashboardText;
	}

	public WebElement getLanguageImage() {
		return languageImage;
	}

	public WebElement getProfileIconImage() {
		return profileIconImage;
	}

	public WebElement getBuddyNameText() {
		return buddyNameText;
	}

	public WebElement getLocationText() {
		return locationText;
	}

	public WebElement getPresentText() {
		return presentText;
	}

	public WebElement getShiftInButton() {
		return shiftInButton;
	}

	public WebElement getShiftOutButton() {
		return shiftOutButton;
	}

	public WebElement getLoginEarnPtsText() {
		return loginEarnPtsText;
	}

	public WebElement getCoinsLogoImage() {
		return CoinsLogoImage;
	}

	public WebElement getListTaskText() {
		return listTaskText;
	}

	public WebElement getDashboardTab() {
		return dashboardTab;
	}

	public WebElement getRegularTaskTab() {
		return regularTaskTab;
	}

	public WebElement getIotTaskTab() {
		return iotTaskTab;
	}

	public WebElement getShiftInTimeText() {
		return shiftInTimeText;
	}

	public WebElement getShiftInDateText() {
		return shiftInDateText;
	}

	public WebElement getPendingTaskTab() {
		return pendingTaskTab;
	}

	public WebElement getPerformanceText() {
		return performanceText;
	}

	public WebElement getTodayText() {
		return todayText;
	}

	public WebElement getWeekText() {
		return weekText;
	}

	public WebElement getMonthText() {
		return monthText;
	}

	public WebElement getEfficiencyChart() {
		return efficiencyChart;
	}

	public WebElement getCompletedTaskCountText() {
		return completedTaskCountText;
	}

	public WebElement getAcceptedTaskCountText() {
		return acceptedTaskCountText;
	}

	public WebElement getOngoingTaskCountText() {
		return ongoingTaskCountText;
	}

	public WebElement getClosureTaskCountText() {
		return closureTaskCountText;
	}

	public WebElement getPendingTaskCountText() {
		return pendingTaskCountText;
	}

	public WebElement getRejectedTaskCountText() {
		return rejectedTaskCountText;
	}

	public WebElement getCompleteTaskPtsText() {
		return completeTaskPtsText;
	}

	public WebElement getTaskBuddyProfileImage() {
		return taskBuddyProfileImage;
	}

	public void dashboardUIValidation(String buddyName) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(dashboardText));

//		SoftAssertValidationUtility.verifyElementVisible(languageImage, "language icon should be visible");
		SoftAssertValidationUtility.verifyElementVisible(profileIconImage, profileIconImage+"Profile icon should be visible");
		SoftAssertValidationUtility.verifyElementVisible(shiftInButton, shiftInButton+"ShiftIN button should be visible");
		SoftAssertValidationUtility.verifyElementVisible(shiftOutButton, shiftOutButton+"ShiftOUT button should be visible");
		SoftAssertValidationUtility.verifyElementVisible(dashboardTab, dashboardTab+"dashboardTab should be visible");
		SoftAssertValidationUtility.verifyElementVisible(regularTaskTab, regularTaskTab+"Regualr task tab should be visible");
		SoftAssertValidationUtility.verifyElementVisible(iotTaskTab, iotTaskTab+"IOT task tab should be visible");
		SoftAssertValidationUtility.verifyElementVisible(loginEarnPtsText, loginEarnPtsText +"IOT task tab should be visible");
		SoftAssertValidationUtility.verifyElementVisible(CoinsLogoImage, CoinsLogoImage +"coins logo should be visible");
//		SoftAssertValidationUtility.verifyElementVisible(taskBuddyProfileImage, taskBuddyProfileImage +"profile Image should be visible");

		WebElement taskbuddyNameEle = driver
				.findElement(AppiumBy.xpath("//android.view.View[@content-desc='" + buddyName + "']"));
		Assert.assertTrue(taskbuddyNameEle.isDisplayed(), "Buddy Name is not displayed");
		ExtentReportManager.logPass("Buddy Dashboard UI validated");
		
	}

	public String tapShiftInButton() {
	    String shiftTime = "";
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(dashboardText));
	    try {
	        if (listTaskText.isDisplayed()) {

	            wait.until(ExpectedConditions.visibilityOf(dashboardText));
	            wait.until(ExpectedConditions.elementToBeClickable(shiftInButton)).click();
	            ExtentReportManager.logPass("Buddy Shift In button Tapped");
	            handlePermissions();
	            Thread.sleep(3000);
	            
	            shiftTime = shiftInTimeText.getAttribute("content-desc");
	            System.out.println(shiftTime+"-------------->Shift In time");
	            compareElementDateWithCurrentDate(shiftInDateText);
	        } else {
	            System.out.println("List task text not visible. Possibly already shifted in or UI not ready.");
	        }
	    } catch (Exception e) {
	        System.out.println("Exception during tapShiftInButton: Shift might already be started.");
	        e.printStackTrace();  // For better debugging
	        ExtentReportManager.logPass("Buddy Shift already started");
	    }
	    return shiftTime;
	}

	public void compareElementDateWithCurrentDate(WebElement element) {
		try {
			// Step 1: Get the date from the element
			String elementDateStr = element.getAttribute("content-desc").trim(); // e.g., "23-07-2025"

			// Step 2: Define date format
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

			// Step 3: Parse the element date
			Date elementDate = sdf.parse(elementDateStr);

			// Step 4: Get current date (only date part)
			String currentDateStr = sdf.format(new Date());
			Date currentDate = sdf.parse(currentDateStr);

			// Step 5: Compare dates
			Assert.assertTrue(elementDate.equals(currentDate), "Current date is not match with element date");

		} catch (Exception e) {
			System.out.println("Date comparison failed: " + e.getMessage());
		}
	}
	
	public void taskbuddyLogin(String taskbuddyMobNo, String buddyName) throws InterruptedException
	{
		CommonUtility common= new CommonUtility(driver);
		common.loginPage().login(taskbuddyMobNo);
		common.otpPage().otpUIvalidation();
		Thread.sleep(4000);
		common.otpPage().enterOTP("1234");
		try
		{
			 handlePermissions();
		}
		catch (Exception e) {
			System.out.println("there is no permission");
		}
		
//		dashboardUIValidation(buddyName);
//		regularTaskTab.click();
	}
	public void taskBuddyPerformanceScoreUIValidation()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(dashboardText));
		
		SoftAssertValidationUtility.verifyElementVisible(performanceText, performanceText+"performance score text should be visible");
		SoftAssertValidationUtility.verifyElementVisible(todayText, todayText+" today text should be visible");
		SoftAssertValidationUtility.verifyElementVisible(weekText, weekText+" today text should be visible");
		SoftAssertValidationUtility.verifyElementVisible(monthText, monthText+" today text should be visible");
		
		String efficiencyChartElement="//android.view.View[contains(@content-desc,\"Task Buddy\") and contains(@content-desc,\"Efficiency\")]";
		GenericUtility.swipeAndFindElementByDirection(driver, efficiencyChartElement, dashboardScrollLayout, "up", 3);
		SoftAssertValidationUtility.verifyElementVisible(efficiencyChart, efficiencyChart+" today text should be visible");
		
		String rejectedTaskElement="//android.view.View[@content-desc=\"Rejected \"]";
		GenericUtility.swipeAndFindElementByDirection(driver, rejectedTaskElement, dashboardScrollLayout, "up", 3);
		SoftAssertValidationUtility.verifyElementVisible(rejectedTaskCountText, rejectedTaskCountText+" today text should be visible");
		ExtentReportManager.logPass("Buddy Performance Chart UI validated");
		
	}
	
	public void taskBuddyEfficiencyCalculation() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(efficiencyChart));

		String actualEfficiency = efficiencyChart.getAttribute("content-desc");
		Thread.sleep(1000);
		String pendingTaskxpath="//android.view.View[@content-desc=\"Request for Closure \"]/following-sibling::android.view.View[1]";
		GenericUtility.swipeAndFindElementByDirection(driver, pendingTaskxpath, dashboardScrollLayout, "up", 3);
		wait.until(ExpectedConditions.visibilityOf(completedTaskCountText));

		int completeTask = Integer.parseInt(completedTaskCountText.getAttribute("content-desc"));
		int acceptTask = Integer.parseInt(acceptedTaskCountText.getAttribute("content-desc"));
		int onGoingTask = Integer.parseInt(ongoingTaskCountText.getAttribute("content-desc"));
		int pendingTask = Integer.parseInt(pendingTaskCountText.getAttribute("content-desc"));
		int closureTask = Integer.parseInt(closureTaskCountText.getAttribute("content-desc"));
		int rejectedTask = Integer.parseInt(rejectedTaskCountText.getAttribute("content-desc"));

		int totalTaskCount = completeTask + acceptTask + onGoingTask + pendingTask+closureTask+rejectedTask;
		int expectedEfficiencyPercent = (int) Math.round(((double) completeTask / totalTaskCount) * 100);
		boolean actualEff = actualEfficiency.contains(String.valueOf(expectedEfficiencyPercent));
		Assert.assertTrue(actualEff,
				"Efficiency does not match. Expected: " + expectedEfficiencyPercent + "% but was: " + actualEfficiency);
		ExtentReportManager.logPass("Buddy Performance Chart Efficiency validated");

	}
	
	private void handlePermissions() {
		if (driver instanceof AndroidDriver) {
			String[] allowButtons = { "com.android.permissioncontroller:id/permission_allow_button",
					"android:id/button1",
					"com.android.permissioncontroller:id/permission_allow_foreground_only_button" };
			
			for (String id : allowButtons) {
				try {
//					String id="com.android.permissioncontroller:id/permission_allow_foreground_only_button";
					WebElement btn = driver.findElement(AppiumBy.id(id));
					if (btn.isDisplayed()) {
						btn.click();
						Thread.sleep(500);
					}
				} catch (Exception ignored) {
				}
			}
		}
	}
}
