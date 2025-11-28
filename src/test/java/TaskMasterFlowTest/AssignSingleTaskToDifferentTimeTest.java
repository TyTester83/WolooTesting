package TaskMasterFlowTest;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.BaseClass;
import Utilities.ListenerImpUtility;

//@Listeners(ListenerImpUtility.class)
public class AssignSingleTaskToDifferentTimeTest extends BaseClass {
	@Test(groups = "sanity",priority = 0)
	@Parameters("rowNumber")
	public void Tc01_assignSingleTaskForDifferentTimeTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("AddNewTaskbuddy", row);
		Map<String, String> taskTime = exUtil.readRowAsMap("TaskTime", 2);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String taskName = taskTime.get("Task Name");
		String taskBuddyName = data.get("TaskBuddy Name");
		String taskBuddyType = "Existing";
		String totalTaskTime = "20 min";
		int totalTaskCount = (taskTime.size() - 1)/2;

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();

//		try {
//			if (common.adminDashboard().getRenewLink().isDisplayed()) {
//				System.out.println("New user will not get Popup");
//			}
//
//		} catch (Exception e) {
//			common.premiumPopup().isPopupVisible();
//		}

//		Thread.sleep(3000);
//		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getAddFacilityButton())).click();
		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
		common.newFacilitypage().tapNewTaskButton();
		common.newTaskPopup().newTaskPopupUIValidation();
		common.newTaskPopup().tapTaskbuddy(taskBuddyType);

		common.existingTaskbuddyPopup().existingTaskBuddyUIValidation();
		common.existingTaskbuddyPopup().chooseTaskbuddy(taskBuddyName);
		common.facilityPage().chooseFacilityfromDropdown(facilityName, location, facilityType);
		common.taskPage().validateExistingTaskbuddy(taskBuddyName);

		List<String> startTasktimings = new ArrayList<String>();
		List<String> endTasktimings = new ArrayList<String>();
		for (int i = 1; i <= totalTaskCount; i++) {
			startTasktimings.add(taskTime.get("Task start Time" + String.valueOf(i)));
			endTasktimings.add(taskTime.get("Task End Time" + String.valueOf(i)));
		}
		common.taskPage().assignMultipleTaskTime(facilityName, facilityType,  taskName, totalTaskTime, startTasktimings,
				endTasktimings,taskBuddyType);

		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
//		try {
//			if (common.adminDashboard().getRenewLink().isDisplayed()) {
//				System.out.println("New user will not get Popup");
//			}
//
//		} catch (Exception e) {
//			common.premiumPopup().isPopupVisible();
//		}
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddyName);
//		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getProfileTab())).click();
		gUtil.safeClick(common.adminDashboard().getProfileTab(), 2, driver);
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "sanity",priority = 1)
	@Parameters("rowNumber")
	public void Tc02_pendingTaskValidationTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("AddNewTaskbuddy", row);
		Map<String, String> taskTime = exUtil.readRowAsMap("TaskTime", 2);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String buddyName = data.get("TaskBuddy Name");
		int totalTaskCount = (taskTime.size() - 1)/2;
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);

		for (int i = 1; i <= totalTaskCount; i++) {
			String timings = taskTime.get("Task start Time" + String.valueOf(i)) + "-"
					+ taskTime.get("Task End Time" + String.valueOf(i));

			common.pendingTaskTab().checkPendingTask(timings, buddyName);
		}

//		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		gUtil.safeClick(common.supervisorDashboardPage().getAccountImage(), 2, driver);
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);

		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups="regression",priority = 2)
	@Parameters("rowNumber")
	public void Tc03_taskbuddyPendingTaskUpdatedInSupervisors_JanitorsDetailsScreenTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("AddNewTaskbuddy", row);
		Map<String, String> taskTime = exUtil.readRowAsMap("TaskTime", 2);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String buddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		int totalTaskCount = ((taskTime.size() - 1)/2)+1;
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getJanitorsImage()))
				.click();

		common.janitorListPage().janitorsListValidation(buddyName, buddyMobNo, gender);
		common.janitorDetailPage().janitorsDetailValidation(buddyName, buddyMobNo);
		common.janitorDetailPage().janitorsTaskValidation();
		String actualPendingTaskCount = common.janitorDetailPage().getPendingTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualPendingTaskCount.contains(String.valueOf(totalTaskCount)),
				actualPendingTaskCount + "Not displayed proper task count");

		wait.until(ExpectedConditions.elementToBeClickable(common.janitorDetailPage().getBackButton())).click();
//		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		gUtil.safeClick(common.supervisorDashboardPage().getAccountImage(), 2, driver);
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups={"sanity","regression"},priority = 3)
	@Parameters("rowNumber")
	public void Tc04_validateBuddyPendingTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("AddNewTaskbuddy", row);
		Map<String, String> taskTime = exUtil.readRowAsMap("TaskTime", 2);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		int totalTaskCount = (taskTime.size() - 1)/2;

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().tapShiftInButton();

		for (int i = 1; i <= totalTaskCount; i++) {
			String timings = taskTime.get("Task start Time" + String.valueOf(i)) + "-"
					+ taskTime.get("Task End Time" + String.valueOf(i));
			common.buddyPendingTaskTab().validatePendingTask(facilityName, timings);

		}
		gUtil.safeClick(common.buddyDashboardPage().getProfileIconImage(), 2, driver);
		common.buddyProfilePage().buddyProfileUIValidation(taskbuddyMobNo, buddyName); //defect in the application

		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}
	
}
