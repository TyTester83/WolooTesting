package iotDashboardRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import Utilities.SoftAssertValidationUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class IOTDashboardPage extends BasePage {

	public IOTDashboardPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(accessibility = "Air Quality vs Usage")
	private WebElement airqualityText;

	@AndroidFindBy(accessibility = "Today")
	private WebElement todayText;

	@AndroidFindBy(accessibility = "7 days")
	private WebElement sevendayText;

	@AndroidFindBy(accessibility = "Check Status")
	private WebElement checkStatusButton;

	@AndroidFindBy(accessibility = "Usage Report")
	private WebElement usageText;

	@AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"S\"])[1]")
	private WebElement satText;

	@AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"S\"])[2]")
	private WebElement sunText;

	@AndroidFindBy(accessibility = "M")
	private WebElement monText;

	@AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"T\"])[1]")
	private WebElement tuesText;

	@AndroidFindBy(accessibility = "W")
	private WebElement wedText;

	@AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"T\"])[2]")
	private WebElement thurText;

	@AndroidFindBy(accessibility = "F")
	private WebElement friText;

	@AndroidFindBy(accessibility = "Task Audit")
	private WebElement taskAuditText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Task Audit\"]/following-sibling::android.widget.Button")
	private WebElement taskbuddyDropdown;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Efficiency\")]")
	private WebElement buddyEfficiencyChart;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Efficiency\")]/following-sibling::android.view.View[1]") 
	private WebElement completedTaskCountText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Completed Task \"]/following-sibling::android.view.View[1]") 
	private WebElement acceptedTaskCountText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Accpeted Task \"]/following-sibling::android.view.View[1]") 
	private WebElement onGoingTaskCountText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"On Going  Task \"]/following-sibling::android.view.View[1]") 
	private WebElement closureTaskCountText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Request for Closure \"]/following-sibling::android.view.View[1]") 
	private WebElement pendingTaskCountText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Pending \"]/following-sibling::android.view.View[1]") 
	private WebElement rejectedTaskCountText;

	@AndroidFindBy(accessibility = "Facility Performance")
	private WebElement performanceText;

	@AndroidFindBy(accessibility = "Air Quality Level")
	private WebElement airQualityLevelText;

	@AndroidFindBy(accessibility = "Overall performance")
	private WebElement allPerformanceText;

	@AndroidFindBy(accessibility = "Average AQL across all facilities")
	private WebElement avgAirLevelText;

	@AndroidFindBy(accessibility = "Facilities")
	private WebElement faclitiesText;

	@AndroidFindBy(accessibility = "Watchlist")
	private WebElement watchlistText;

	@AndroidFindBy(accessibility = "Daily Average")
	private WebElement dayAvgText;

	@AndroidFindBy(accessibility = "Alerts & Notifications")
	private WebElement alertsText;

	@AndroidFindBy(accessibility = "Date & Time")
	private WebElement dateTimeText;

	@AndroidFindBy(accessibility = "Condition")
	private WebElement conditionText;

	@AndroidFindBy(accessibility = "Building")
	private WebElement buildingText;

	@AndroidFindBy(accessibility = "Check Status")
	private WebElement alertStatusButton;

	@AndroidFindBy(accessibility = "Air Quality")
	private WebElement airQualityIndicationText;

	@AndroidFindBy(accessibility = "Usage")
	private WebElement usageIndicationText;

	@AndroidFindBy(accessibility = "Threshold (50 ppm)")
	private WebElement thresholdText;

	@AndroidFindBy(xpath = "(//android.widget.ScrollView)[2]")
	private WebElement scrollView;

	public WebElement getSevendayText() {
		return sevendayText;
	}

	public WebElement getAirqualityText() {
		return airqualityText;
	}

	public WebElement getTodayText() {
		return todayText;
	}

	public WebElement getCheckStatusButton() {
		return checkStatusButton;
	}

	public WebElement getUsageText() {
		return usageText;
	}

	public WebElement getSatText() {
		return satText;
	}

	public WebElement getSunText() {
		return sunText;
	}

	public WebElement getMonText() {
		return monText;
	}

	public WebElement getTuesText() {
		return tuesText;
	}

	public WebElement getWedText() {
		return wedText;
	}

	public WebElement getThurText() {
		return thurText;
	}

	public WebElement getFriText() {
		return friText;
	}

	public WebElement getTaskAuditText() {
		return taskAuditText;
	}

	public WebElement getTaskbuddyDropdown() {
		return taskbuddyDropdown;
	}

	public WebElement getBuddyEfficiencyChart() {
		return buddyEfficiencyChart;
	}

	public WebElement getCompletedTaskCountText() {
		return completedTaskCountText;
	}

	public WebElement getAcceptedTaskCountText() {
		return acceptedTaskCountText;
	}

	public WebElement getOnGoingTaskCountText() {
		return onGoingTaskCountText;
	}

	public WebElement getPendingTaskCountText() {
		return pendingTaskCountText;
	}

	public WebElement getClosureTaskCountText() {
		return closureTaskCountText;
	}

	public WebElement getRejectedTaskCountText() {
		return rejectedTaskCountText;
	}

	public WebElement getPerformanceText() {
		return performanceText;
	}

	public WebElement getAirQualityLevelText() {
		return airQualityLevelText;
	}

	public WebElement getAllPerformanceText() {
		return allPerformanceText;
	}

	public WebElement getAvgAirLevelText() {
		return avgAirLevelText;
	}

	public WebElement getFaclitiesText() {
		return faclitiesText;
	}

	public WebElement getWatchlistText() {
		return watchlistText;
	}

	public WebElement getDayAvgText() {
		return dayAvgText;
	}

	public WebElement getAlertsText() {
		return alertsText;
	}

	public WebElement getDateTimeText() {
		return dateTimeText;
	}

	public WebElement getConditionText() {
		return conditionText;
	}

	public WebElement getBuildingText() {
		return buildingText;
	}

	public WebElement getAlertStatusButton() {
		return alertStatusButton;
	}

	public void iotAirQualityUIValidation() {
		wait.until(ExpectedConditions.visibilityOf(todayText));
		
		SoftAssertValidationUtility.verifyElementVisible(todayText," elemnt is not displayed");
		SoftAssertValidationUtility.verifyElementVisible(sevendayText," elemnt is not displayed");
		SoftAssertValidationUtility.verifyElementVisible(checkStatusButton," elemnt is not displayed");
		ExtentReportManager.logPass("AirQuality Ui validated", driver, false);
	
	}

	public void usageUIValidation() {
		wait.until(ExpectedConditions.visibilityOf(todayText));
		GenericUtility.swipeAndFindElementByDirection(driver, "//android.view.View[@content-desc=\"Usage Report\"]",
				scrollView, "up", 3);
		SoftAssertValidationUtility.verifyElementVisible(usageText," elemnt is not displayed");
		ExtentReportManager.logPass("Usage Ui validated", driver, false);
	}

	public void taskAuditUIValidation() {
		wait.until(ExpectedConditions.visibilityOf(usageText));
		GenericUtility.swipeAndFindElementByDirection(driver, "//android.view.View[@content-desc=\"Task Audit\"]",
				scrollView, "up", 3);
		SoftAssertValidationUtility.verifyElementVisible(taskAuditText,"Task Audit Text should be display");
		ExtentReportManager.logPass("TaskDropdwon visible", driver, false);
	}

	public void facilityPerformanceUIValidation() {
		GenericUtility.swipeAndFindElementByDirection(driver,
				"//android.view.View[@content-desc=\"Alerts & Notifications\"]", scrollView, "up", 3);
		SoftAssertValidationUtility.verifyElementVisible(performanceText, "Facility performance Text should be display");
		SoftAssertValidationUtility.verifyElementVisible(airQualityLevelText, "Air quality level text should be display");
		SoftAssertValidationUtility.verifyElementVisible(faclitiesText, "Facility average Text should be display");
		SoftAssertValidationUtility.verifyElementVisible(faclitiesText, "Air quality level Alert text should be display");
		ExtentReportManager.logPass("Facility performance Ui validated", driver, false);
	}

	public void iotDashboardUIValidation() {
		wait.until(ExpectedConditions.visibilityOf(todayText));
		iotAirQualityUIValidation();
		usageUIValidation();
		taskAuditUIValidation();
		facilityPerformanceUIValidation();
		ExtentReportManager.logPass("iotDashboard Ui validated", driver, false);
	}

	public void iOtTaskEfficiencyCalculation() throws InterruptedException {
		WebDriverWait efficiencyWait = waitWithTimeout(Duration.ofSeconds(20));
		efficiencyWait.until(ExpectedConditions.visibilityOf(buddyEfficiencyChart));

		String actualEfficiency = buddyEfficiencyChart.getAttribute("content-desc");
		Thread.sleep(1000);
//		String pendingTaskxpath="//android.view.View[@content-desc=\"Pending Task :\"]/following-sibling::android.view.View[1]";
		String rejectedTaskxpath = "//android.view.View[@content-desc=\"Rejected \"]";
		GenericUtility.swipeAndFindElementByDirection(driver, rejectedTaskxpath, scrollView, "up", 3);
		efficiencyWait.until(ExpectedConditions.visibilityOf(completedTaskCountText));

		int completeTask = Integer.parseInt(completedTaskCountText.getAttribute("content-desc"));
		int acceptTask = Integer.parseInt(acceptedTaskCountText.getAttribute("content-desc"));
		int onGoingTask = Integer.parseInt(onGoingTaskCountText.getAttribute("content-desc"));
		int pendingTask = Integer.parseInt(pendingTaskCountText.getAttribute("content-desc"));
		int closureTask = Integer.parseInt(closureTaskCountText.getAttribute("content-desc"));
		int rejectedTask = Integer.parseInt(rejectedTaskCountText.getAttribute("content-desc"));

		int totalTaskCount = completeTask + acceptTask + onGoingTask + pendingTask + closureTask + rejectedTask;
		int expectedEfficiencyPercent = (int) Math.round(((double) completeTask / totalTaskCount) * 100);
		boolean actualEff = actualEfficiency.contains(String.valueOf(expectedEfficiencyPercent));
		Assert.assertTrue(actualEff,
				"Efficiency does not match. Expected: " + expectedEfficiencyPercent + "% but was: " + actualEfficiency);

		ExtentReportManager.logPass("Taskbuddy Efficiency validated", driver, false);
	}
}
