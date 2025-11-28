package supervisorDashboardRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ObjectUtility.CommonUtility;
import Utilities.BasePage;
import Utilities.ExtentReportManager;
import Utilities.SoftAssertValidationUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SupervisorDashboardPage extends BasePage {

	public SupervisorDashboardPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Hello\")]")
	private WebElement supervisorNameText;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Pending task\")]")
	private WebElement pendingTaskTab;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Request for Closure\")]")
	private WebElement taskClosureTab;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Completed Task\")]")
	private WebElement completedTaskTab;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Rejected\")]")
	private WebElement rejectedTaskTab;

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc,\"Regular Task\")]")
	private WebElement regularTaskTab;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"IOT Task\")]")
	private WebElement iotTaskTab;

	@AndroidFindBy(accessibility = "Cluster")
	private WebElement clusterImage;

	@AndroidFindBy(accessibility = "Janitors")
	private WebElement janitorsImage;

	@AndroidFindBy(accessibility = "Report an Issue")
	private WebElement IssueImage;

	@AndroidFindBy(accessibility = "Account")
	private WebElement accountImage;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Cluster\"]/parent::android.view.View/preceding-sibling::android.widget.ImageView")
	private WebElement mainTaskImage;

	@AndroidFindBy(xpath = "//android.view.ImageView[contains(@content-desc,\"Hello\")]")
	private WebElement myselfNameText;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Monitor\"]")
	private WebElement monitorTab;

	@AndroidFindBy(accessibility = "Switch")
	private WebElement switchImage;

	@AndroidFindBy(accessibility = "TASQ MASTER")
	private WebElement tasqMasterTab;

	public WebElement getSupervisorNameText() {
		return supervisorNameText;
	}

	public WebElement getPendingTaskTab() {
		return pendingTaskTab;
	}

	public WebElement getTaskClosureTab() {
		return taskClosureTab;
	}

	public WebElement getCompletedTaskTab() {
		return completedTaskTab;
	}

	public WebElement getRejectedTaskTab() {
		return rejectedTaskTab;
	}

	public WebElement getRegularTaskTab() {
		return regularTaskTab;
	}

	public WebElement getIotTaskTab() {
		return iotTaskTab;
	}

	public WebElement getClusterImage() {
		return clusterImage;
	}

	public WebElement getJanitorsImage() {
		return janitorsImage;
	}

	public WebElement getIssueImage() {
		return IssueImage;
	}

	public WebElement getAccountImage() {
		return accountImage;
	}

	public WebElement getMainTaskImage() {
		return mainTaskImage;
	}

	public WebElement getMyselfNameText() {
		return myselfNameText;
	}

	public WebElement getMonitorTab() {
		return monitorTab;
	}

	public WebElement getSwitchImage() {
		return switchImage;
	}

	public WebElement getTasqMasterTab() {
		return tasqMasterTab;
	}

	public void dashboardUIValidation() {
		wait.until(ExpectedConditions.visibilityOf(monitorTab));

		SoftAssertValidationUtility.verifyElementVisible(pendingTaskTab, "PendingTask Tab should be visible.");
		SoftAssertValidationUtility.verifyElementVisible(taskClosureTab, "TaskClosure Tab should be visible.");
		SoftAssertValidationUtility.verifyElementVisible(completedTaskTab, "CompletedTask Tab should be visible.");
		SoftAssertValidationUtility.verifyElementVisible(regularTaskTab, "RegularTask Tab should be visible.");
		SoftAssertValidationUtility.verifyElementVisible(iotTaskTab, "IotTask Tab should be visible.");
		SoftAssertValidationUtility.verifyElementVisible(clusterImage, "Cluster Image should be visible.");
		SoftAssertValidationUtility.verifyElementVisible(janitorsImage, "Janitors Image should be visible.");
		SoftAssertValidationUtility.verifyElementVisible(accountImage, "Account Image should be visible.");
		ExtentReportManager.logPass("Supervisor Dashboard UI validated");
	}

	public void supervisorNameValidation(String supervisorName) {
		WebDriverWait shortWait = waitWithTimeout(Duration.ofSeconds(5));
		shortWait.until(ExpectedConditions.visibilityOf(monitorTab));

		try {
			boolean isDisplayed = isElementVisible(
					"//android.view.View[contains(@content-desc,'" + supervisorName + "')]", 1);
			if (!isDisplayed) {
				isDisplayed = isElementVisible(
						"//android.widget.ImageView[contains(@content-desc,'" + supervisorName + "')]", 5);
			}
			Assert.assertTrue(isDisplayed, "SupervisorName is not displayed (" + supervisorName + ")");
			ExtentReportManager.logPass("Supervisor Name validated" + supervisorName);
		} catch (Exception e) {
			Assert.fail("Validation failed due to exception: " + e.getMessage());
		}
	}

	public boolean isElementVisible(String xpath, int timeoutInSeconds) {
		try {
			WebDriverWait localWait = waitWithTimeout(Duration.ofSeconds(timeoutInSeconds));
			return localWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(xpath))).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void supervisorLoginValidation(String supervisorMobNo) throws InterruptedException {
		ExtentReportManager.logInfo("Logging in as Supervisor with mobile number: " + supervisorMobNo);
		CommonUtility common = new CommonUtility(driver);
		common.loginPage().login(supervisorMobNo);
		common.otpPage().otpUIvalidation();
		Thread.sleep(3000);
		common.otpPage().enterOTP("1234");

	}

	public void supervisorLogin(String supervisorMobNo, String supervisorName, String adminType)
			throws InterruptedException {
//		supervisorLoginValidation(supervisorMobNo, supervisorName);
//		try {WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		     shortWait.until(ExpectedConditions.visibilityOf(switchImage));
//			
//			boolean isSwitchVisible = isElementVisible("//android.widget.ImageView[@content-desc='Switch']", 2);
//			if (isSwitchVisible) {
//	            System.out.println("Switch icon visible — re-logging as supervisor.");
//	            shortWait.until(ExpectedConditions.visibilityOf(switchImage)).click();
//	            supervisorLoginValidation(supervisorMobNo, supervisorName);
//	        } else {
//	            System.out.println("Switch icon not visible — proceeding with supervisor validation.");
//	        }
//	        supervisorNameValidation(supervisorName);
//	        System.out.println("Supervisor " + supervisorName + " successfully validated.");
//		} catch (Exception e) {
//			System.out.println("SwitchImage is not present");
//		}
//		supervisorNameValidation(supervisorName);

		String normalizedAdmin = adminType.trim().toLowerCase();

		WebDriverWait localWait = waitWithTimeout(Duration.ofSeconds(15));
		CommonUtility common = new CommonUtility(driver);

		switch (normalizedAdmin) {
		case "myself":
			common.adminDashboard().adminLogin(supervisorMobNo);
			common.adminDashboard().switchStoreToDashboard();
			Thread.sleep(3000);
			localWait.until(ExpectedConditions.visibilityOfElementLocated(
					AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Switch\"]")));
			localWait.until(ExpectedConditions.elementToBeClickable(switchImage)).click();
			supervisorLoginValidation(supervisorMobNo);
			supervisorNameValidation(supervisorName);
			break;
		case "supervisor":
			supervisorLoginValidation(supervisorMobNo);
			supervisorNameValidation(supervisorName);
			break;
		default:
			System.out.println("Invalid admin type: " + adminType);
			return;
		}

	}
}
