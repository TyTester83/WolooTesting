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
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.BaseClass;
import Utilities.GenericUtility;
import Utilities.ListenerImpUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
//@Listeners(ListenerImpUtility.class)
public class AdminTaskDeletionScenarioTest extends BaseClass {
	@Test(groups = "sanity", priority = 0)
	@Parameters("rowNumber")
	public void Tc01_assign_Delete_AssignTaskOfSameFacilityTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String taskName = data.get("Task Name");
		String startTaskTime = data.get("Task Time1");
		String endTaskTime = data.get("Task End Time1");
		String taskBuddyName = data.get("TaskBuddy Name");
		String restartTaskTime = data.get("Task Time1");
		String reendTaskTime = data.get("Task End Time1");
		String taskBuddyType = "Existing";
		String totalTaskTime = "25 min";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();

		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
		common.newFacilitypage().tapNewTaskButton();
		common.newTaskPopup().newTaskPopupUIValidation();
		common.newTaskPopup().tapTaskbuddy(taskBuddyType);

		common.existingTaskbuddyPopup().existingTaskBuddyUIValidation();
		common.existingTaskbuddyPopup().chooseTaskbuddy(taskBuddyName);
		common.facilityPage().chooseFacilityfromDropdown(facilityName, location, facilityType);
		common.taskPage().validateExistingTaskbuddy(taskBuddyName);

		String taskTime1 = GenericUtility.convertToUnpaddedTime(startTaskTime);
		String taskTime2 = GenericUtility.convertToUnpaddedTime(endTaskTime);

		common.taskPage().deleteTaskTimings(facilityName, taskTime1, taskTime2);
		common.taskPage().assignTaskDeletionAndTaskAssignForTaskbuddy(facilityName, facilityType, taskName,
				totalTaskTime, restartTaskTime, reendTaskTime);
//		try {
//			if (common.adminDashboard().getRenewLink().isDisplayed()) {
//				System.out.println("New user will not get Popup");
//			}
//
//		} catch (Exception e) {
//			common.premiumPopup().isPopupVisible();
//		}
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddyName);
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "sanity", priority = 1)
	@Parameters("rowNumber")
	public void Tc02_pendingTaskValidationTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String taskTime = data.get("Task Time1") + "-" + data.get("Task End Time1");
		String buddyName = data.get("TaskBuddy Name");
		String adminType = data.get("Admin Type");
		String registrationPts = "2000";

		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		common.pendingTaskTab().checkPendingTask(taskTime, buddyName);
		common.supervisorDashboardPage().getAccountImage().click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().validateSupervisorWolooPoints(registrationPts);
		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 2)
	@Parameters("rowNumber")
	public void Tc03_validateBuddyPendingTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time1") + "-" + data.get("Task End Time1");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().tapShiftInButton();
		common.buddyDashboardPage().taskBuddyEfficiencyCalculation();
		common.buddyPendingTaskTab().validatePendingTask(facilityName, taskTime);
		wait.until(ExpectedConditions.elementToBeClickable(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().buddyProfileUIValidation(taskbuddyMobNo, buddyName); // defect in the application
		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 3)
	@Parameters("rowNumber")
	public void Tc04_validationOf_PN_for_AcceptedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time1") + "-" + data.get("Task End Time1");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().tapLogout();
//		Assert.assertTrue(common.welcomePage().getLoginAsDropdown().isDisplayed(), "Logout is not happen");
		Thread.sleep(5000);

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
//		buddyDashboardPage.tapShiftInButton();
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

	@Test(groups = "sanity", priority = 4)
	@Parameters("rowNumber")
	public void Tc05_taskBuddyDashboardAcceptedTaskValidationTest(@Optional("1") String rowNumber)
			throws EncryptedDocumentException, IOException, InterruptedException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().taskBuddyEfficiencyCalculation();
		String actualAcceptedTaskCount = common.buddyDashboardPage().getAcceptedTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualAcceptedTaskCount.contains("1"), actualAcceptedTaskCount + "NOt updated");
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().tapLogout();
	}

	@Test(groups = "sanity", priority = 5)
	@Parameters("rowNumber")
	public void Tc06_taskbuddyAcceptedTask_UpdateInAdminDashboardTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddy = data.get("TaskBuddy Name");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();

		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddy);
		String actualAcceptedTaskCount = common.adminDashboard().getAcceptedTaskCountText().getAttribute("content-desc")
				.trim();
		Assert.assertTrue(actualAcceptedTaskCount.contains("1"), actualAcceptedTaskCount + "NOt updated");
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 6)
	@Parameters("rowNumber")
	public void Tc07_adminDeleteTaskbuddyAcceptedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String startTaskTime = data.get("Task Time1");
		String endTaskTime = data.get("Task End Time1");
		String buddyName = data.get("TaskBuddy Name");
		String taskBuddyType = "Existing";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();

		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
		common.newFacilitypage().tapNewTaskButton();
		common.newTaskPopup().newTaskPopupUIValidation();
		common.newTaskPopup().tapTaskbuddy(taskBuddyType);
		common.existingTaskbuddyPopup().existingTaskBuddyUIValidation();
		common.existingTaskbuddyPopup().chooseTaskbuddy(buddyName);

		common.facilityPage().chooseFacilityfromDropdown(facilityName, location, facilityType);
		common.taskPage().validateExistingTaskbuddy(buddyName);

		String taskTime1 = GenericUtility.convertToUnpaddedTime(startTaskTime);
		String taskTime2 = GenericUtility.convertToUnpaddedTime(endTaskTime);
		common.taskPage().deleteTaskTimings(facilityName, taskTime1, taskTime2);

//		WebElement expFacilityEle = driver.findElement(AppiumBy.xpath(
//				"//android.widget.ImageView[contains(@content-desc,'" + facilityName + "') and contains(@content-desc,'"
//						+ taskTime1 + "') and contains(@content-desc,'" + taskTime2 + "')]/parent::android.view.View"));
//		Assert.assertTrue(expFacilityEle.isDisplayed(), "Task should be display");
		for (int i = 0; i < 5; i++) {
			try {
				((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
			} catch (Exception ex) {
				System.out.println("BACK key failed too: " + ex.getMessage());
			}
			Thread.sleep(500);
		}

//		try {
//			if (common.adminDashboard().getRenewLink().isDisplayed()) {
//				System.out.println("New user will not get Popup");
//			}
//
//		} catch (Exception e) {
//			common.premiumPopup().isPopupVisible();
//		}

		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 7)
	@Parameters("rowNumber")
	public void Tc08_taskbuddyAcceptedTaskUpdatedInSupervisors_JanitorsDetailsScreenTest(
			@Optional("1") String rowNumber) throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String buddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
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
		Assert.assertTrue(actualAcceptTaskCount.contains("1"),
				actualAcceptTaskCount + "Not displayed proper task count");

		wait.until(ExpectedConditions.elementToBeClickable(common.janitorDetailPage().getBackButton())).click();
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();

		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 8)
	@Parameters("rowNumber")
	public void Tc09_taskBuddyStartToWorkAcceptedTaskFlowTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time1") + "-" + data.get("Task End Time1");
		String taskName = data.get("Task Name");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);

		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().tapLogout();
//		Assert.assertTrue(common.welcomePage().getLoginAsDropdown().isDisplayed(), "Logout is not happen");
		Thread.sleep(5000);

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyAcceptedTab().tapStartTaskButton(facilityName, taskTime);
		common.selfiPage().takeImageBeforeCleaning();

		List<String> tasks = new ArrayList<>();
		tasks.add(taskName);
		common.buddyTaskPage().chooseAssignedTask(tasks);
		common.buddyOngoingTab().validateOngoingTaskIsVisible(facilityName, taskTime);
		common.buddyDashboardPage().getProfileIconImage().click();
		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 9)
	@Parameters("rowNumber")
	public void Tc10_taskBuddyDashboardOngoingTaskValidationTest(@Optional("1") String rowNumber)
			throws EncryptedDocumentException, IOException, InterruptedException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().taskBuddyEfficiencyCalculation();
		String actualOngoingTaskCount = common.buddyDashboardPage().getOngoingTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualOngoingTaskCount.contains("1"), actualOngoingTaskCount + "NOt updated");
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().tapLogout();
	}

	@Test(groups = "sanity", priority = 10)
	@Parameters("rowNumber")
	public void Tc11_taskbuddyOngoingTaskUpdateInAdminDashboardTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddy = data.get("TaskBuddy Name");

		common.adminDashboard().adminLogin(adminMobNo);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().switchStoreToDashboard();
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddy);
		String actualOngoingTaskCount = common.adminDashboard().getOnGoingTaskCountText().getAttribute("content-desc")
				.trim();
		Assert.assertTrue(actualOngoingTaskCount.contains("1"), actualOngoingTaskCount + "NOt updated");
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 11)
	@Parameters("rowNumber")
	public void Tc12_adminDeleteTaskbuddyOngoingTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String startTaskTime = data.get("Task Time1");
		String endTaskTime = data.get("Task End Time1");
		String buddyName = data.get("TaskBuddy Name");
		String taskBuddyType = "Existing";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();

		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
		common.newFacilitypage().tapNewTaskButton();
		common.newTaskPopup().newTaskPopupUIValidation();
		common.newTaskPopup().tapTaskbuddy(taskBuddyType);

		common.existingTaskbuddyPopup().existingTaskBuddyUIValidation();
		common.existingTaskbuddyPopup().chooseTaskbuddy(buddyName);
		common.facilityPage().chooseFacilityfromDropdown(facilityName, location, facilityType);
		common.taskPage().validateExistingTaskbuddy(buddyName);

		String taskTime1 = GenericUtility.convertToUnpaddedTime(startTaskTime);
		String taskTime2 = GenericUtility.convertToUnpaddedTime(endTaskTime);
		common.taskPage().deleteTaskTimings(facilityName, taskTime1, taskTime2);

		WebElement expFacilityEle = driver.findElement(AppiumBy.xpath(
				"//android.widget.ImageView[contains(@content-desc,'" + facilityName + "') and contains(@content-desc,'"
						+ taskTime1 + "') and contains(@content-desc,'" + taskTime2 + "')]/parent::android.view.View"));
		Assert.assertTrue(expFacilityEle.isDisplayed(), "Task should be display");
		for (int i = 0; i < 5; i++) {
			try {
				((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
			} catch (Exception ex) {
				System.out.println("BACK key failed too: " + ex.getMessage());
			}
			Thread.sleep(500);
		}
//		try {
//			if (common.adminDashboard().getRenewLink().isDisplayed()) {
//				System.out.println("New user will not get Popup");
//			}
//
//		} catch (Exception e) {
//			common.premiumPopup().isPopupVisible();
//		}

		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 12)
	@Parameters("rowNumber")
	public void Tc13_taskbuddyOngoingTaskUpdatedInSupervisors_JanitorsDetailsScreenTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String buddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String taskCount = "1";
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getJanitorsImage()))
				.click();

		common.janitorListPage().janitorsListValidation(buddyName, buddyMobNo, gender);
		common.janitorDetailPage().janitorsDetailValidation(buddyName, buddyMobNo);
		common.janitorDetailPage().janitorsTaskValidation();
		String actualOngoingTaskCount = common.janitorDetailPage().getOnGoingTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualOngoingTaskCount.contains(taskCount),
				actualOngoingTaskCount + "Not displayed proper task count");

		wait.until(ExpectedConditions.elementToBeClickable(common.janitorDetailPage().getBackButton())).click();
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 13)
	@Parameters("rowNumber")
	public void Tc14_validationOf_PN_for_RequestforClosureTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time1") + "-" + data.get("Task End Time1");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().tapLogout();
//		Assert.assertTrue(common.welcomePage().getLoginAsDropdown().isDisplayed(), "Logout is not happen");
		Thread.sleep(5000);

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		wait.until(ExpectedConditions.elementToBeClickable(common.buddyDashboardPage().getRegularTaskTab())).click();
		wait.until(ExpectedConditions.elementToBeClickable(common.buddyOngoingTab().getOngoingTaskTab())).click();
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

	@Test(groups = "sanity", priority = 14)
	@Parameters("rowNumber")
	public void Tc15_taskBuddyDashboardClosureTaskValidationTest(@Optional("1") String rowNumber)
			throws EncryptedDocumentException, IOException, InterruptedException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().taskBuddyEfficiencyCalculation();
		String actualClosureTaskCount = common.buddyDashboardPage().getClosureTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualClosureTaskCount.contains("1"), actualClosureTaskCount + "NOt updated");
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().tapLogout();
	}

	@Test(groups = "sanity", priority = 15)
	@Parameters("rowNumber")
	public void Tc16_validateClosureTaskIsUpdatedInSupervisors_JanitorsDetailScreenTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String buddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
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

	@Test(groups = "sanity", priority = 16)
	@Parameters("rowNumber")
	public void Tc17_taskbuddyClosureTaskUpdateInAdminDashboardTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddy = data.get("TaskBuddy Name");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddy);
		String actualClosureTaskCount = common.adminDashboard().getClosureTaskCountText().getAttribute("content-desc")
				.trim();
		Assert.assertTrue(actualClosureTaskCount.contains("1"), actualClosureTaskCount + "NOt updated");
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 17)
	@Parameters("rowNumber")
	public void Tc18_verifyAdminDelete_BuddyRequestForClosureTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String startTaskTime = data.get("Task Time1");
		String endTaskTime = data.get("Task End Time1");
		String buddyName = data.get("TaskBuddy Name");
		String taskBuddyType = "Existing";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();

		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
		common.newFacilitypage().tapNewTaskButton();
		common.newTaskPopup().newTaskPopupUIValidation();
		common.newTaskPopup().tapTaskbuddy(taskBuddyType);

		common.existingTaskbuddyPopup().existingTaskBuddyUIValidation();
		common.existingTaskbuddyPopup().chooseTaskbuddy(buddyName);
		common.facilityPage().chooseFacilityfromDropdown(facilityName, location, facilityType);
		common.taskPage().validateExistingTaskbuddy(buddyName);

		String taskTime1 = GenericUtility.convertToUnpaddedTime(startTaskTime);
		String taskTime2 = GenericUtility.convertToUnpaddedTime(endTaskTime);
		common.taskPage().deleteTaskTimings(facilityName, taskTime1, taskTime2);

		WebElement expFacilityEle = driver.findElement(AppiumBy.xpath(
				"//android.widget.ImageView[contains(@content-desc,'" + facilityName + "') and contains(@content-desc,'"
						+ taskTime1 + "') and contains(@content-desc,'" + taskTime2 + "')]/parent::android.view.View"));
		Assert.assertTrue(expFacilityEle.isDisplayed(), "Task should be display");
		for (int i = 0; i < 5; i++) {
			try {
				((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
			} catch (Exception ex) {
				System.out.println("BACK key failed too: " + ex.getMessage());
			}
			Thread.sleep(500);
		}
//		try {
//			if (common.adminDashboard().getRenewLink().isDisplayed()) {
//				System.out.println("New user will not get Popup");
//			}
//
//		} catch (Exception e) {
//			common.premiumPopup().isPopupVisible();
//		}

		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 18)
	@Parameters("rowNumber")
	public void Tc19_approveBuddyRequestedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String taskTime = data.get("Task Time1");
		String buddyName = data.get("TaskBuddy Name");
		String taskName = data.get("Task Name");
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

	@Test(groups = "sanity", priority = 19)
	@Parameters("rowNumber")
	public void Tc20_completedTaskUpdatedInSupervisors_JanitorsDetailScreenTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String buddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
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

	@Test(groups = "sanity", priority = 20)
	@Parameters("rowNumber")
	public void Tc21_validateBuddyCompletedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time1");

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyCompletedTaskTab().verifyCompletedTaskIsDisplayed(facilityName, taskTime);
		common.buddyDashboardPage().getProfileIconImage().click();
		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups = "sanity", priority = 21)
	@Parameters("rowNumber")
	public void Tc22_validateCompletedTaskUpdatedInAdminDashboardTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddy = data.get("TaskBuddy Name");
		String taskCount = "1";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddy);

		String actualCompletedTaskCount = common.adminDashboard().getCompletedTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualCompletedTaskCount.contains(taskCount), actualCompletedTaskCount + "NOt updated");
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);
	}
}
