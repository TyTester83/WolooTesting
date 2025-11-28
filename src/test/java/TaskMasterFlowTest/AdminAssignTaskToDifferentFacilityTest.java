package TaskMasterFlowTest;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
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

public class AdminAssignTaskToDifferentFacilityTest extends BaseClass {
	@Test(groups = "regression",priority = 0)
	@Parameters("rowNumber")
	public void Tc01_addNewTaskforDifferentFacilityTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		Map<String, String> newFacilityData = exUtil.readRowAsMap("AddNewFacility", row);
		String taskName = newFacilityData.get("Task Name");
		String startTaskTime = newFacilityData.get("Task Time2");
		String taskBuddyName = newFacilityData.get("TaskBuddy Name");
		String endTaskTime = newFacilityData.get("Task End Time2");
		String taskBuddyType = "Existing";
		String totalTaskTime = "25 min";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		Thread.sleep(10000);

		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Store")));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTasqMasterTab())).click();
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));

//		common.premiumPopup().isPopupVisible();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getAddFacilityButton())).click();
		common.newFacilitypage().addNewFacilityUIValidation();
		common.newFacilitypage().tapNewTaskButton();
		common.newTaskPopup().newTaskPopupUIValidation();
		common.newTaskPopup().tapTaskbuddy(taskBuddyType);
		common.existingTaskbuddyPopup().existingTaskBuddyUIValidation();
		common.existingTaskbuddyPopup().chooseTaskbuddy(taskBuddyName);
		common.facilityPage().chooseFacilityfromDropdown(facilityName, location, facilityType);
		common.taskPage().validateExistingTaskbuddy(taskBuddyName);
		common.taskPage().assignTaskforTaskbuddy(facilityName, facilityType, taskName, totalTaskTime, startTaskTime,
				endTaskTime);
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddyName);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(priority = 1)
	@Parameters("rowNumber")
	public void Tc02_pendingTaskValidationTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Admin Mobile No");
		String supervisorName = data.get("Supervisor Name");
		Map<String, String> newFacilityData = exUtil.readRowAsMap("AddNewFacility", row);
		String taskTime = newFacilityData.get("Task Time2") + "-" + data.get("Task End Time2");
		String buddyName = newFacilityData.get("TaskBuddy Name");
		String adminType = data.get("Admin Type");
		
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		common.pendingTaskTab().checkPendingTask(taskTime, buddyName);

		common.supervisorDashboardPage().getAccountImage().click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(priority = 2)
	@Parameters("rowNumber")
	public void Tc03_shiftInTimingValidationTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		Map<String, String> newFacilityData = exUtil.readRowAsMap("AddNewFacility", row);
		String buddyName = newFacilityData.get("TaskBuddy Name");
		String taskbuddyMobNo = newFacilityData.get("Buddy Mobile No");
		String adminType = data.get("Admin Type");

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		String shiftStartTime = common.buddyDashboardPage().tapShiftInButton();
		common.buddyDashboardPage().getProfileIconImage().click();
		common.buddyProfilePage().tapLogout();
		Thread.sleep(5000);
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		common.supervisorDashboardPage().getClusterImage().click();
		common.clusterPage().clusterValidation(adminMobNo);

		common.janitorListPage().janitorsClusterValidation(buddyName, taskbuddyMobNo, "Present");
		common.janitorDetailPage().janitorsDetailValidation(buddyName, taskbuddyMobNo);
		common.janitorDetailPage().janitorsShiftTimeValidattion(shiftStartTime);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(common.janitorDetailPage().getBackButton())).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(common.janitorListPage().getBackButton())).click();
		wait.until(ExpectedConditions.visibilityOf(common.clusterPage().getClusterText()));
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();

		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(priority = 3)
	@Parameters("rowNumber")
	public void Tc04_validateBuddyPendingTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String facilityName = data.get("Facility Name");
		Map<String, String> newFacilityData = exUtil.readRowAsMap("AddNewFacility", row);
		String taskTime = newFacilityData.get("Task Time2") + "-" + data.get("Task End Time2");
		String buddyName = newFacilityData.get("TaskBuddy Name");
		String taskbuddyMobNo = newFacilityData.get("Buddy Mobile No");

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
//		common.buddyDashboardPage().tapShiftInButton();

		common.buddyPendingTaskTab().validatePendingTask(facilityName, taskTime);
		common.buddyDashboardPage().getProfileIconImage().click();
//		common.buddyProfilePage().buddyProfileUIValidation(taskbuddyMobNo, buddyName); defect in the application
		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(priority = 4)
	@Parameters("rowNumber")
	public void Tc05_validationOf_PN_for_AcceptedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String facilityName = data.get("Facility Name");
		Map<String, String> newFacilityData = exUtil.readRowAsMap("AddNewFacility", row);
		String taskbuddyMobNo = newFacilityData.get("Buddy Mobile No");
		String buddyName = newFacilityData.get("TaskBuddy Name");
		String taskTime = newFacilityData.get("Task Time2") + "-" + data.get("Task End Time2");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().tapLogout();
		Assert.assertTrue(common.welcomePage().getLoginAsDropdown().isDisplayed(), "Logout is not happen");
		Thread.sleep(2000);
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
//		common.buddyDashboardPage().tapShiftInButton();
		common.buddyPendingTaskTab().acceptPendingTask(facilityName, taskTime);

		Thread.sleep(2000);
		utility.notification();
		WebElement taskAcceptEle = driver.findElement(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"android:id/title\" and contains(@text,\"TASK ACCEPTED BY\") and contains(@text,'"
						+ buddyName + "')]"));

		Assert.assertTrue(taskAcceptEle.isDisplayed(), "PN is not generated for accepted task ");
		wait.until(ExpectedConditions.visibilityOf(taskAcceptEle));
		Thread.sleep(1000);
		taskAcceptEle.click();

		common.buddyAcceptedTab().getAcceptedTaskTab().isDisplayed();
		common.buddyDashboardPage().getProfileIconImage().click();
		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(priority = 5)
	@Parameters("rowNumber")
	public void Tc06_taskbuddyAcceptedTask_UpdateInAdminDashboardTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		Map<String, String> newFacilityData = exUtil.readRowAsMap("AddNewFacility", row);
		String taskBuddy = newFacilityData.get("TaskBuddy Name");
		String taskCount = "1";

		common.adminDashboard().adminLogin(adminMobNo);
		Thread.sleep(10000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Products")));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTasqMasterTab())).click();
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddy);

		String actualAcceptedTaskCount = common.adminDashboard().getAcceptedTaskCountText().getAttribute("content-desc")
				.trim();
		Assert.assertTrue(actualAcceptedTaskCount.contains(taskCount), actualAcceptedTaskCount + "Not updated");
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(priority = 6)
	@Parameters("rowNumber")
	public void Tc07_taskbuddyAcceptedTaskUpdatedInSupervisors_JanitorsDetailsScreenTest(
			@Optional("1") String rowNumber) throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Admin Mobile No");
		String supervisorName = data.get("Supervisor Name");
		Map<String, String> newFacilityData = exUtil.readRowAsMap("AddNewFacility", row);
		String buddyName = newFacilityData.get("TaskBuddy Name");
		String buddyMobNo = newFacilityData.get("Buddy Mobile No");
		String gender = newFacilityData.get("Gender");
		String taskCount = "1";
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getJanitorsImage()))
				.click();

		common.janitorListPage().janitorsListValidation(buddyName, buddyMobNo, gender);
		common.janitorDetailPage().janitorsDetailValidation(buddyName, buddyMobNo);
		common.janitorDetailPage().janitorsTaskValidation();
		String actualAcceptTaskCount = common.janitorDetailPage().getAcceptedTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualAcceptTaskCount.contains(taskCount),
				actualAcceptTaskCount + "Not displayed proper task count");

		wait.until(ExpectedConditions.elementToBeClickable(common.janitorDetailPage().getBackButton())).click();
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(priority = 7)
	@Parameters("rowNumber")
	public void Tc08_validationOf_PN_for_RequestforClosureTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String facilityName = data.get("Facility Name");
		Map<String, String> newFacilityData = exUtil.readRowAsMap("AddNewFacility", row);
		String taskbuddyMobNo = newFacilityData.get("Buddy Mobile No");
		String buddyName = newFacilityData.get("TaskBuddy Name");
		String taskTime = newFacilityData.get("Task Time2") + "-" + data.get("Task End Time2");
		String taskName = newFacilityData.get("Task Name");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().tapLogout();
		Assert.assertTrue(common.welcomePage().getLoginAsDropdown().isDisplayed(), "Logout is not happen");
		Thread.sleep(2000);

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		wait.until(ExpectedConditions.elementToBeClickable(common.buddyAcceptedTab().getAcceptedTaskTab())).click();
		common.buddyAcceptedTab().tapStartTaskButton(facilityName, taskTime);
		common.selfiPage().takeImageBeforeCleaning();
		List<String> tasks = new ArrayList<>();
		tasks.add(taskName);
		common.buddyTaskPage().chooseAssignedTask(tasks);
		common.buddyOngoingTab().tapRequestClosureButton(facilityName, taskTime);
		common.taskPhotoPage().completedTaskImage();
		common.closurePopup().tapYestoConfirmTaskCompletion();
		common.buddyRequestForClosureTab().requestedTaskTemplate(facilityName, taskTime);

		Thread.sleep(2000);
		utility.notification();
		WebElement taskClosureRequestEle = driver.findElement(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"android:id/title\" and contains(@text,\"TASK REQUESTED FOR CLOSURE BY\") and contains(@text,'"
						+ buddyName + "')]"));

		Assert.assertTrue(taskClosureRequestEle.isDisplayed(), "PN is not generated for Closure task ");
		wait.until(ExpectedConditions.visibilityOf(taskClosureRequestEle));
		Thread.sleep(1000);
		taskClosureRequestEle.click();
		wait.until(ExpectedConditions.visibilityOf(common.buddyRequestForClosureTab().getRequestforClosureTab()));
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(priority = 8)
	@Parameters("rowNumber")
	public void Tc09_validateClosureTaskIsUpdatedInSupervisors_JanitorsDetailScreenTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Admin Mobile No");
		String supervisorName = data.get("Supervisor Name");
		Map<String, String> newFacilityData = exUtil.readRowAsMap("AddNewFacility", row);
		String buddyName = newFacilityData.get("TaskBuddy Name");
		String buddyMobNo = newFacilityData.get("Buddy Mobile No");
		String gender = newFacilityData.get("Gender");
		String taskCount = "1";
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getJanitorsImage()))
				.click();

		common.janitorListPage().janitorsListValidation(buddyName, buddyMobNo, gender);
		common.janitorDetailPage().janitorsDetailValidation(buddyName, buddyMobNo);
		common.janitorDetailPage().janitorsTaskValidation();
		String actualRequestForClosureTaskCount = common.janitorDetailPage().getClosureTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualRequestForClosureTaskCount.contains(taskCount),
				actualRequestForClosureTaskCount + "Not displayed proper task count");

		wait.until(ExpectedConditions.elementToBeClickable(common.janitorDetailPage().getBackButton())).click();
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().tapLogout();
		Thread.sleep(1000);
	}

	@Test(priority = 9)
	@Parameters("rowNumber")
	public void Tc10_approveBuddyRequestedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Admin Mobile No");
		String supervisorName = data.get("Supervisor Name");
		Map<String, String> newFacilityData = exUtil.readRowAsMap("AddNewFacility", row);
		String taskTime = newFacilityData.get("Task Time2") + "-" + data.get("Task End Time2");
		String buddyName = newFacilityData.get("TaskBuddy Name");
		String taskName = newFacilityData.get("Task Name");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		common.taskClosureTab().tapRequestedTaskDetailsButton(buddyName, taskTime);

		List<String> taskNames = new ArrayList<String>();
		taskNames.add(taskName);
		common.approvalPage().validateAndApproveCompletedTask(taskNames);
		common.completedTaskTab().validateCompletedTaskIsDisplayed(buddyName, taskTime);
		Thread.sleep(2000);
		utility.notification();
		WebElement taskApprovedEle = driver.findElement(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"android:id/title\" and contains(@text,\"Task Approved by Supervisor\")]"));

		Assert.assertTrue(taskApprovedEle.isDisplayed(), "PN is not generated for rejected task ");
		wait.until(ExpectedConditions.visibilityOf(taskApprovedEle));
		Thread.sleep(1000);
		taskApprovedEle.click();
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().tapLogout();
		Thread.sleep(1000);
	}

	@Test(priority = 10)
	@Parameters("rowNumber")
	public void Tc11_completedTaskUpdatedInSupervisors_JanitorsDetailScreenTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Admin Mobile No");
		String supervisorName = data.get("Supervisor Name");
		Map<String, String> newFacilityData = exUtil.readRowAsMap("AddNewFacility", row);
		String buddyName = newFacilityData.get("TaskBuddy Name");
		String buddyMobNo = newFacilityData.get("Buddy Mobile No");
		String gender = newFacilityData.get("Gender");
		String taskCount = "1";
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getJanitorsImage()))
				.click();

		common.janitorListPage().janitorsListValidation(buddyName, buddyMobNo, gender);
		common.janitorDetailPage().janitorsDetailValidation(buddyName, buddyMobNo);
		common.janitorDetailPage().janitorsTaskValidation();
		String actualCompletedTaskCount = common.janitorDetailPage().getCompletedTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualCompletedTaskCount.contains(taskCount),
				actualCompletedTaskCount + "Not displayed proper task count");

		wait.until(ExpectedConditions.elementToBeClickable(common.janitorDetailPage().getBackButton())).click();
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().tapLogout();
		Thread.sleep(1000);
	}

	@Test(priority = 11)
	@Parameters("rowNumber")
	public void Tc12_validateBuddyCompletedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String facilityName = data.get("Facility Name");
		Map<String, String> newFacilityData = exUtil.readRowAsMap("AddNewFacility", row);
		String taskbuddyMobNo = newFacilityData.get("Buddy Mobile No");
		String buddyName = newFacilityData.get("TaskBuddy Name");
		String taskTime = newFacilityData.get("Task Time2") + "-" + data.get("Task End Time2");

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyCompletedTaskTab().verifyCompletedTaskIsDisplayed(facilityName, taskTime);
		common.buddyDashboardPage().getProfileIconImage().click();
		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(priority = 12)
	@Parameters("rowNumber")
	public void Tc13_validateCompletedTaskUpdatedInAdminDashboardTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		Map<String, String> newFacilityData = exUtil.readRowAsMap("AddNewFacility", row);
		String taskBuddy = newFacilityData.get("TaskBuddy Name");
		String taskCount = "1";

		common.adminDashboard().adminLogin(adminMobNo);
		Thread.sleep(10000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Products")));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTasqMasterTab())).click();
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddy);

		String actualCompletedTaskCount = common.adminDashboard().getCompletedTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualCompletedTaskCount.contains(taskCount), actualCompletedTaskCount + "NOt updated");
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}

}
