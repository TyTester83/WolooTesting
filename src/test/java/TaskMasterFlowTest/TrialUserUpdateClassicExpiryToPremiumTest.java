package TaskMasterFlowTest;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.BaseClass;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumBy;

public class TrialUserUpdateClassicExpiryToPremiumTest extends BaseClass{
	@Test(groups = "regression",priority=0)
	@Parameters({"rowNumber", "sheetName"})
	public void Tc01_newUserLoginTest(@Optional("1") String rowNumber, @Optional("Facility") String sheetName)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap(sheetName, row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");
		String taskName = data.get("Task Name");
		String shiftTime = data.get("Shift Time");
		String taskTime = data.get("Task Time1");
		String adminType = data.get("Admin Type");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String taskBuddy = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String pendingTaskCount = "1";

//		common.welcomePage().newUserLoginValidation(adminMobNo);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.adminDashboard().adminLogin(adminMobNo);

		common.explorePage().exploreUIvalidation();
		common.explorePage().tapTaskMasterExploreButton();
		common.getStartPage().getStartUIValidation();
		common.getStartPage().tapGetStartedButton();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityDetailsForm(facilityName, location, facilityType, otherFacilityName);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().scrollAndSelectTask(taskName);

//		GenericUtility.tapUsingCoordinatePercentage(driver, common.taskPage().getShiftTimeLayout(), 78.8, 50.0);
//		common.timePage().shiftTimeUIValidation();
//		common.timePage().selectShiftTime(shiftTime);
		Thread.sleep(1000);
		GenericUtility.tapUsingCoordinatePercentage(driver, common.taskPage().getAddTimingsLayout(), 75.0, 50.0);
		common.scheduleTaskTimingPage().scheduleTaskUIValidation();

		String[] parts = taskTime.split("[: ]");
		String hrs = parts[0];
		String mins = parts[1];
		String meridian = parts[2];

		common.scheduleTaskTimingPage().scrollAndFindHrsTiming(hrs);
		common.scheduleTaskTimingPage().scrollAndFindMinsTiming(mins);
		common.scheduleTaskTimingPage().scrollAndFindMeridinTiming(meridian.toLowerCase());
		common.scheduleTaskTimingPage().getAddButton().click();

		common.taskConfirmPopup().confirmPopupUIValidation();
		GenericUtility gu = new GenericUtility();
		gu.scrollElement(driver, common.taskPage().getShiftTimeLayout(), "up");

		wait.until(ExpectedConditions.visibilityOf(common.taskPage().getNextButton()));
		common.taskPage().getNextButton().click();

		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);

		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorDetails(adminType, supervisorName, supervisorMobNo);

		common.buddyPage().taskBuddyUIValidation();
		common.buddyPage().taskBuddyDetailsFill(taskBuddy, buddyMobNo, gender);
		common.congrats().congratsUIvalidation();

		common.adminDashboard().newUserDashboardUIValidation(facilityName, taskBuddy, adminType, pendingTaskCount);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}
	
	@Test
	@Parameters("rowNumber")
	public void Tc02_newUserRenewClassicExpiryToPremiumTest(@Optional("10") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddyName = data.get("TaskBuddy Name");
		String paymentBank = data.get("Bank");
		String taskMasterPlan = data.get("Subscription Plan");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		Thread.sleep(10000);

		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Products")));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTasqMasterTab())).click();
		common.facilityRenewPopup().tapPayNowButton();
		common.paynowPage().paynowScreenUIValidation();
		common.paynowPage().tapPaymentPlan(taskMasterPlan);

		common.paymentOptionsPage().getNetBankButton().click();
		common.netBankPage().tapPaymentBank(paymentBank);
		common.paymentConfirmPage().confirmationUIValidation();

		common.adminDashboard().navigateToFacilityByName(facilityName);
		common.iotDashboardPage().iotAirQualityUIValidation();
		common.iotDashboardPage().usageUIValidation();
		common.iotDashboardPage().taskAuditUIValidation();
		wait.until(ExpectedConditions.elementToBeClickable(common.iotDashboardPage().getTaskbuddyDropdown())).click();

		WebElement actualTaskbuddy = wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + taskBuddyName + "')]")));

		Assert.assertTrue(actualTaskbuddy.isDisplayed(), "TaskBuddy option not displayed");
		wait.until(ExpectedConditions.elementToBeClickable(actualTaskbuddy)).click();
		common.iotDashboardPage().iOtTaskEfficiencyCalculation();
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);

	}

}
