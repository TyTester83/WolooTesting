package adminDashboardRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ObjectUtility.CommonUtility;
import Utilities.BasePage;
import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import Utilities.SoftAssertValidationUtility;
import facilityUpgradeRepo.UpgradeToPremiumPopup;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AdminDashboardPage extends BasePage {

	public AdminDashboardPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"Hello\")]")
	private WebElement adminNameText;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Your Free Subscription shall end\")]")
	private WebElement freeTrialText;

	@AndroidFindBy(accessibility = "Renew it Now")
	private WebElement renewLink;

	@AndroidFindBy(accessibility = "Dashboard Overview")
	private WebElement adminDashboardText;

	@AndroidFindBy(accessibility = "Switch")
	private WebElement switchImage;

	@AndroidFindBy(accessibility = "Add Facility/Task")
	private WebElement addFacilityButton;

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

	@AndroidFindBy(accessibility = "Profile")
	private WebElement profileTab;

	@AndroidFindBy(accessibility = "TASQ MASTER")
	private WebElement tasqMasterTab;

	@AndroidFindBy(accessibility = "Products")
	private WebElement productsTab;

	@AndroidFindBy(xpath = "//android.widget.HorizontalScrollView")
	private WebElement facilityMultiContainer;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[4]")
	private WebElement facilitySingleContainer;

	@AndroidFindBy(xpath = "//android.widget.ScrollView")
	private WebElement classicScrollLayout;

	public WebElement getAdminNameText() {
		return adminNameText;
	}

	public WebElement getFreeTrialText() {
		return freeTrialText;
	}

	public WebElement getRenewLink() {
		return renewLink;
	}

	public WebElement getAdminDashboardText() {
		return adminDashboardText;
	}

	public WebElement getSwitchImage() {
		return switchImage;
	}

	public WebElement getAddFacilityButton() {
		return addFacilityButton;
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

	public WebElement getProfileTab() {
		return profileTab;
	}

	public WebElement getTasqMasterTab() {
		return tasqMasterTab;
	}

	public WebElement getProductsTab() {
		return productsTab;
	}

	public WebElement getFacilityMultiContainer() {
		return facilityMultiContainer;
	}

	public WebElement getFacilitySingleContainer() {
		return facilitySingleContainer;
	}

	public WebElement getClassicScrollLayout() {
		return classicScrollLayout;
	}

	public WebElement getClosureTaskCountText() {
		return closureTaskCountText;
	}

	public WebElement getRejectedTaskCountText() {
		return rejectedTaskCountText;
	}

//	public UpgradeToPremiumPopup premiumPopup = new UpgradeToPremiumPopup(driver);

	public void facilityValidation(String facilityName, String taskBuddyName) throws InterruptedException {
		ExtentReportManager.logInfo("Validating facility and task buddy on Admin Dashboard");
		wait.until(ExpectedConditions.visibilityOf(adminDashboardText));

		WebElement actualFacility = driver
				.findElement(AppiumBy.xpath("//android.view.View[@content-desc='" + facilityName + "']"));
		if (actualFacility.isDisplayed()) {
			ExtentReportManager.logPass("Facility '" + facilityName + "' is visible");
		} else {
			ExtentReportManager.logFail("Facility '" + facilityName + "' is not visible", driver);
		}

//		UpgradeToPremiumPopup premiumPopup = new UpgradeToPremiumPopup(driver);
//		premiumPopup.isPopupVisible();

		wait.until(ExpectedConditions.elementToBeClickable(taskbuddyDropdown));
		taskbuddyDropdown.click();
		ExtentReportManager.logPass("Task buddy dropdown clicked");
		WebElement actualTaskbuddy = driver.findElement(
				AppiumBy.xpath("//android.widget.Button[contains(@content-desc='" + taskBuddyName + "')]"));
		wait.until(ExpectedConditions.elementToBeClickable(actualTaskbuddy));

		if (actualTaskbuddy.isDisplayed()) {
			ExtentReportManager.logPass("Task buddy '" + taskBuddyName + "' is visible");
		} else {
			ExtentReportManager.logFail("Task buddy '" + taskBuddyName + "' is not visible", driver);
		}
		taskEfficiencyCalculation();
	}

	public void taskbuddyValidation(String facilityName, String taskBuddyName) throws InterruptedException {
	    ExtentReportManager.logInfo("Validating task buddy for facility: " + facilityName);
	    wait.until(ExpectedConditions.visibilityOf(adminDashboardText));
	    Thread.sleep(2000);

	    String xpath = "//android.view.View[contains(@content-desc,'" + facilityName + "')]";
	    WebElement facilityElement = null;

	    try {
	        facilityElement = driver.findElement(AppiumBy.xpath(xpath));
	    } catch (Exception e) {
	        ExtentReportManager.logInfo("Facility not visible, trying to swipe...");

	        boolean found = GenericUtility.swipeAndFindElementByDirection(driver, xpath, facilityMultiContainer, "left", 3);

	        if (!found) {
	            found = GenericUtility.swipeAndFindElementByDirection(driver, xpath, facilitySingleContainer, "left", 3);
	        }

	        if (!found) {
	            ExtentReportManager.logFail(xpath, driver);
	            Assert.fail("Facility not found even after swiping");
	        }

	        // re-locate after swipe
	        facilityElement = driver.findElement(AppiumBy.xpath(xpath));
	    }

	    gUtil.safeClick(facilityElement, 3, driver);
	    ExtentReportManager.logPass("Facility '" + facilityName + "' clicked");

	    wait.until(ExpectedConditions.elementToBeClickable(taskbuddyDropdown));
	    gUtil.safeClick(taskbuddyDropdown, 3, driver);
	    ExtentReportManager.logPass("Task buddy dropdown clicked");
	    Thread.sleep(2000);

	    String buddyXpath = "//android.widget.Button[contains(@content-desc,'" + taskBuddyName + "')]";
	    WebElement actualTaskbuddy = driver.findElement(AppiumBy.xpath(buddyXpath));
	    gUtil.safeClick(actualTaskbuddy, 2, driver);
	    ExtentReportManager.logPass("Task buddy '" + taskBuddyName + "' selected");

	    taskEfficiencyCalculation();
	}


	public void taskEfficiencyCalculation() throws InterruptedException {
		ExtentReportManager.logInfo("Calculating task efficiency");
		WebDriverWait localWait = waitWithTimeout(Duration.ofSeconds(20));
		localWait.until(ExpectedConditions.visibilityOf(buddyEfficiencyChart));

		String actualEfficiency = buddyEfficiencyChart.getAttribute("content-desc");
		Thread.sleep(2000);
		String rejectedTaskxpath = "//android.view.View[@content-desc=\"Rejected \"]";
		GenericUtility.swipeAndFindElementByDirection(driver, rejectedTaskxpath, classicScrollLayout, "up", 3);
		localWait.until(ExpectedConditions.visibilityOf(rejectedTaskCountText));

		int completeTask = Integer.parseInt(completedTaskCountText.getAttribute("content-desc"));
		int acceptTask = Integer.parseInt(acceptedTaskCountText.getAttribute("content-desc"));
		int onGoingTask = Integer.parseInt(onGoingTaskCountText.getAttribute("content-desc"));
		int pendingTask = Integer.parseInt(pendingTaskCountText.getAttribute("content-desc"));
		int closureTask = Integer.parseInt(closureTaskCountText.getAttribute("content-desc"));
		int rejectedTask = Integer.parseInt(rejectedTaskCountText.getAttribute("content-desc"));

		int totalTaskCount = completeTask + acceptTask + onGoingTask + pendingTask + closureTask + rejectedTask;
		int expectedEfficiencyPercent = (int) Math.round(((double) completeTask / totalTaskCount) * 100);
		boolean actualEff = actualEfficiency.contains(String.valueOf(expectedEfficiencyPercent));
		if (actualEff) {
			ExtentReportManager.logPass(
					"Efficiency matches. Expected: " + expectedEfficiencyPercent + "% and found: " + actualEfficiency);
		} else {
			ExtentReportManager.logFail("Efficiency does not match. Expected: " + expectedEfficiencyPercent
					+ "% but was: " + actualEfficiency, driver);
		}
		Assert.assertTrue(actualEff,
				"Efficiency does not match. Expected: " + expectedEfficiencyPercent + "% but was: " + actualEfficiency);
	}

	public void navigateToFacilityByName(String facilityName) {
		ExtentReportManager.logInfo("Navigating to facility by name: " + facilityName);
		try {
			// Try to directly find the facility element first
			WebElement facilityElement = driver
					.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + facilityName
							+ "')]/parent::android.view.View[contains(@content-desc,\"Tab\")]"));

			if (facilityElement.isDisplayed()) {
				facilityElement.click();
				ExtentReportManager.logPass("Facility '" + facilityName + "' found and clicked directly");
				Thread.sleep(1000);
				Assert.assertTrue(facilityElement.isSelected(), "Facility should be selected");
				return;
			}

		} catch (Exception e) {
			ExtentReportManager.logWarning("Facility not visible directly. Trying to swipe and find...");
		}

		// If direct find fails, try swipe search
		String xpath = "//android.view.View[contains(@content-desc,'" + facilityName
				+ "')]/parent::android.view.View[contains(@content-desc,\"Tab\")]";
		boolean found = GenericUtility.swipeAndFindElementByDirection(driver, xpath, facilityMultiContainer, "left", 3);

		if (!found) {
			ExtentReportManager.logFail("Facility '" + facilityName + "' not found after swiping", driver);
			Assert.fail("Facility '" + facilityName + "' not found after swiping.");
		} else {
			WebElement actualfacility = driver.findElement(AppiumBy.xpath(xpath));
			actualfacility.click();
			ExtentReportManager.logPass("Facility '" + facilityName + "' found after swiping and clicked");
			Assert.assertTrue(actualfacility.isSelected(), "Facility should be selected");
		}
	}

	public void newUserDashboardUIValidation(String facilityName, String taskBuddy, String admin,
			String pendingTaskCount) throws InterruptedException {
		ExtentReportManager.logInfo("Validating new user dashboard UI");
		wait.until(ExpectedConditions.visibilityOf(adminDashboardText));
		Thread.sleep(2000);
		SoftAssertValidationUtility.verifyElementVisible(adminNameText, " Hello text is not displayed");
		ExtentReportManager.logPass("Admin name text is visible");
		SoftAssertValidationUtility.verifyElementVisible(freeTrialText, " Free Trial text is not displayed");
		ExtentReportManager.logPass("Free trial text is visible");
		SoftAssertValidationUtility.verifyElementVisible(renewLink, " Renew Link is not displayed");
		ExtentReportManager.logPass("Renew link is visible");

		try {
			if (admin.contains("Myself")) {
				Assert.assertTrue(switchImage.isDisplayed(),
						" Switch ICon is not displayed for " + admin + "Supervisor");
				ExtentReportManager.logPass("Switch icon is visible for admin: " + admin);
			}
		} catch (Exception e) {
			ExtentReportManager.logWarning("There is no switch Icon for " + admin);
		}

		gUtil.safeClick(taskbuddyDropdown, 3, driver);
		ExtentReportManager.logPass("Task buddy dropdown clicked");

		Thread.sleep(2000);
		WebElement actualTaskbuddy = driver
				.findElement(AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + taskBuddy + "')]"));
		gUtil.safeClick(actualTaskbuddy, 2, driver);
		ExtentReportManager.logPass("Task buddy '" + taskBuddy + "' is visible and clicked");

		taskEfficiencyCalculation();

		wait.until(ExpectedConditions.visibilityOf(pendingTaskCountText));
		String taskNo = pendingTaskCountText.getAttribute("content-desc");
		boolean actualCount = taskNo.contains(pendingTaskCount);
		if (actualCount) {
			ExtentReportManager.logPass("Pending task count matches: " + pendingTaskCount);
		} else {
			ExtentReportManager.logFail(
					"Pending task count does not match. Expected: " + pendingTaskCount + ", Found: " + taskNo, driver);
		}
		Assert.assertEquals(actualCount, true, taskNo + "This is not expected Output");
	}

	public void adminLogin(String adminMobNo) throws InterruptedException {
		ExtentReportManager.logInfo("Logging in as admin with mobile number: " + adminMobNo);
		CommonUtility common = new CommonUtility(driver);
		common.loginPage().login(adminMobNo);
		ExtentReportManager.logPass("Admin login submitted");
		common.otpPage().enterOTP("1234");
		ExtentReportManager.logPass("OTP entered for admin login");
	}

	public void newFacilityDashboardUIValidation(String facilityName, String taskBuddyName, String admin,
			String pendingTaskCount) throws InterruptedException {
		ExtentReportManager.logInfo("Validating new facility dashboard UI");
		wait.until(ExpectedConditions.visibilityOf(adminDashboardText));
		Thread.sleep(2000);
		try {
			if (admin.contains("Myself")) {
				Assert.assertTrue(switchImage.isDisplayed(),
						" Switch ICon is not displayed for " + admin + "Supervisor");
				ExtentReportManager.logPass("Switch icon is visible for admin: " + admin);
			}
		} catch (Exception e) {
			ExtentReportManager.logWarning("There is no switch Icon for " + admin);
		}

		gUtil.safeClick(taskbuddyDropdown, 3, driver);
		ExtentReportManager.logPass("Task buddy dropdown clicked");

		Thread.sleep(2000);
		WebElement actualTaskbuddy = wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + taskBuddyName + "')]")));

		gUtil.safeClick(actualTaskbuddy, 3, driver);
		ExtentReportManager.logPass("Task buddy '" + taskBuddyName + "' is visible and clicked");

		taskEfficiencyCalculation();

		wait.until(ExpectedConditions.visibilityOf(pendingTaskCountText));
		String taskNo = pendingTaskCountText.getAttribute("content-desc");
		boolean actualCount = taskNo.contains(pendingTaskCount);
		if (actualCount) {
			ExtentReportManager.logPass("Pending task count matches: " + pendingTaskCount);
		} else {
			ExtentReportManager.logFail(
					"Pending task count does not match. Expected: " + pendingTaskCount + ", Found: " + taskNo, driver);
		}
		Assert.assertEquals(actualCount, true);
	}

	public void switchStoreToDashboard() throws InterruptedException {
		ExtentReportManager.logInfo("Switching store to dashboard");
		CommonUtility common = new CommonUtility(driver);
		Thread.sleep(12000);
		// ExtentReportManager.logInfo("All element launch <---------------------->");
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Store")));
		ExtentReportManager.logPass("Store element is present");

		gUtil.safeClick(tasqMasterTab, 2, driver);
		ExtentReportManager.logPass("Tasq Master tab clicked");

//		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTasqMasterTab())).click();
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		ExtentReportManager.logPass("Admin Dashboard text is visible");

	}
}
