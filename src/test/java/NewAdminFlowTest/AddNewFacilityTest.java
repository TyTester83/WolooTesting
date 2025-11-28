package NewAdminFlowTest;

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
import io.appium.java_client.AppiumBy;

public class AddNewFacilityTest extends BaseClass {
	@Test(groups = { "sanity", "regression" }, priority = 0)
	@Parameters("rowNumber")
	public void TC01_addNew_ClassicFacilityTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("AddNewFacility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");
		String taskName = data.get("Task Name");
		String shiftTime = data.get("Shift Time");
		String taskStartTime = data.get("Task Time1");
		String taskEndTime = data.get("Task End Time1");
		String adminType = data.get("Admin Type");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String taskBuddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String subscriptionPlan = data.get("Subscription Plan");
		String paymentBank = data.get("Bank");
		String taskEstimateTime = "25";
		String pendingTaskCount = "1";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();

		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
		gUtil.safeClick(common.newFacilitypage().getFacilityButton(), 3, driver);
//		wait.until(ExpectedConditions.elementToBeClickable(common.newFacilitypage().getFacilityButton())).click();
		common.paynowPage().paynowScreenUIValidation();
		common.paynowPage().tapPaymentPlan(subscriptionPlan);
		common.paymentOptionsPage().paymentUIValidation();
		gUtil.safeClick(common.paymentOptionsPage().getNetBankButton(), 3, driver);
		common.netBankPage().tapPaymentBank(paymentBank);
		common.paymentConfirmPage().confirmationUIValidation();
		common.newFacilitypage().addNewFacilityUIValidation();
		gUtil.safeClick(common.newFacilitypage().getFacilityButton(), 3, driver);
//		wait.until(ExpectedConditions.elementToBeClickable(common.newFacilitypage().getFacilityButton())).click();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityDetailsForm(facilityName, location, facilityType, otherFacilityName);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().assignTaskforNewTaskbuddy(facilityName, facilityType, taskName, taskEstimateTime, shiftTime,
				taskStartTime, taskEndTime);
		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);

		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorDetails(adminType, supervisorName, supervisorMobNo);

		common.buddyPage().taskBuddyUIValidation();
		common.buddyPage().taskBuddyDetailsFill(taskBuddyName, buddyMobNo, gender);
		common.congrats().congratsUIvalidation();
		common.adminDashboard().newFacilityDashboardUIValidation(facilityName, taskBuddyName, adminType,
				pendingTaskCount);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);

	}

	@Test(groups = { "sanity", "regression" }, priority = 1)
	@Parameters("rowNumber")
	public void TC02_addNew_PremiumFacilityTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber) + 1;
		Map<String, String> data = exUtil.readRowAsMap("AddNewFacility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");
		String taskName = data.get("Task Name");
		String shiftTime = data.get("Shift Time");
		String taskStartTime = data.get("Task Time1");
		String taskEndTime = data.get("Task End Time1");
		String adminType = data.get("Admin Type");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String taskBuddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String subscriptionPlan = data.get("Subscription Plan");
		String paymentBank = data.get("Bank");
		String taskEstimateTime = "25";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();

		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
		wait.until(ExpectedConditions.elementToBeClickable(common.newFacilitypage().getFacilityButton())).click();
		common.paynowPage().paynowScreenUIValidation();
		common.paynowPage().tapPaymentPlan(subscriptionPlan);
		common.paymentOptionsPage().paymentUIValidation();
		gUtil.safeClick(common.paymentOptionsPage().getNetBankButton(), 3, driver);
		common.netBankPage().tapPaymentBank(paymentBank);
		common.paymentConfirmPage().confirmationUIValidation();
		common.newFacilitypage().addNewFacilityUIValidation();
		wait.until(ExpectedConditions.elementToBeClickable(common.newFacilitypage().getFacilityButton())).click();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityDetailsForm(facilityName, location, facilityType, otherFacilityName);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().assignTaskforNewTaskbuddy(facilityName, facilityType, taskName, taskEstimateTime, shiftTime,
				taskStartTime, taskEndTime);
		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);

		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorDetails(adminType, supervisorName, supervisorMobNo);

		common.buddyPage().taskBuddyUIValidation();
		common.buddyPage().taskBuddyDetailsFill(taskBuddyName, buddyMobNo, gender);
		common.congrats().congratsUIvalidation();

		Thread.sleep(2000);
		common.iotDashboardPage().iotAirQualityUIValidation();
		common.iotDashboardPage().usageUIValidation();
		common.iotDashboardPage().taskAuditUIValidation();

//		wait.until(ExpectedConditions.elementToBeClickable(common.iotDashboardPage().getTaskbuddyDropdown())).click();

		gUtil.safeClick(common.iotDashboardPage().getTaskbuddyDropdown(), 3, driver);
		Thread.sleep(2000);
		WebElement actualTaskbuddy = wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + taskBuddyName + "')]")));

		Thread.sleep(2000);
		Assert.assertTrue(actualTaskbuddy.isDisplayed(), "TaskBuddy option not displayed");
		wait.until(ExpectedConditions.elementToBeClickable(actualTaskbuddy)).click();
		common.iotDashboardPage().iOtTaskEfficiencyCalculation();
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "regression", priority = 2)
	@Parameters("rowNumber")
	public void TC03_addNew_ClassicFacilityPaymentPersistentTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber) + 2;
		Map<String, String> data = exUtil.readRowAsMap("AddNewFacility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");
		String taskName = data.get("Task Name");
		String shiftTime = data.get("Shift Time");
		String taskStartTime = data.get("Task Time1");
		String taskEndTime = data.get("Task End Time1");
		String adminType = data.get("Admin Type");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String taskBuddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String subscriptionPlan = data.get("Subscription Plan");
		String paymentBank = data.get("Bank");
		String taskEstimateTime = "25";
		String pendingTaskCount = "1";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();

		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
		wait.until(ExpectedConditions.elementToBeClickable(common.newFacilitypage().getFacilityButton())).click();
		common.paynowPage().paynowScreenUIValidation();
		common.paynowPage().tapPaymentPlan(subscriptionPlan);
		common.paymentOptionsPage().paymentUIValidation();
		wait.until(ExpectedConditions.elementToBeClickable(common.paymentOptionsPage().getNetBankButton())).click();
		common.netBankPage().tapPaymentBank(paymentBank);
		common.paymentConfirmPage().confirmationUIValidation();

		wait.until(ExpectedConditions.visibilityOf(common.newFacilitypage().getFacilityButton()));
		gUtil.safeClick(common.newFacilitypage().getBackButton(), 2, driver);

		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(5000);
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();

		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
		wait.until(ExpectedConditions.elementToBeClickable(common.newFacilitypage().getFacilityButton())).click();

		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityDetailsForm(facilityName, location, facilityType, otherFacilityName);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().assignTaskforNewTaskbuddy(facilityName, facilityType, taskName, taskEstimateTime, shiftTime,
				taskStartTime, taskEndTime);
		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);

		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorDetails(adminType, supervisorName, supervisorMobNo);

		common.buddyPage().taskBuddyUIValidation();
		common.buddyPage().taskBuddyDetailsFill(taskBuddyName, buddyMobNo, gender);
		common.congrats().congratsUIvalidation();

		common.adminDashboard().newFacilityDashboardUIValidation(facilityName, taskBuddyName, adminType,
				pendingTaskCount);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);

	}

}
