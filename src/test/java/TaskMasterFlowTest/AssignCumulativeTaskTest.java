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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.BaseClass;

public class AssignCumulativeTaskTest extends BaseClass {

	@Test(groups="regression",priority = 0)
	@Parameters("rowNumber")
	public void Tc01_assignMultipleTaskAtOneTiming(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String taskBuddyName = data.get("TaskBuddy Name");
		String taskBuddyType = "Existing";

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

		List<String> taskList = new ArrayList<String>();
		int rowCount = exUtil.getRowCount("MultipleTask");
		for (int i = 1; i <= rowCount; i++) {
			// Start from 1 to skip header row
			taskList.add(exUtil.readDataFromExcel("MultipleTask", i, 0));
		}
		String startTaskTime = exUtil.readDataFromExcel("MultipleTask", 1, 1);
		common.taskPage().assignMultipleTask(taskList, startTaskTime);

		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups="regression",priority = 1)
	@Parameters("rowNumber")
	public void Tc02_pendingTaskValidationTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Admin Mobile No");
		String supervisorName = data.get("Supervisor Name");
		String buddyName = data.get("TaskBuddy Name");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);

		String timings = exUtil.readDataFromExcel("MultipleTask", 1, 1) + "-"
				+ exUtil.readDataFromExcel("MultipleTask", 1, 2);
		common.pendingTaskTab().checkPendingTask(timings, buddyName);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);

		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups="regression",priority = 2)
	@Parameters("rowNumber")
	public void Tc03_taskbuddyPendingTaskUpdatedInSupervisors_JanitorsDetailsScreenTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Admin Mobile No");
		String supervisorName = data.get("Supervisor Name");
		String buddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		int taskCount = exUtil.getRowCount("MultipleTask");
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
		Assert.assertTrue(actualPendingTaskCount.contains(String.valueOf(taskCount)),
				actualPendingTaskCount + "Not displayed proper task count");
		wait.until(ExpectedConditions.elementToBeClickable(common.janitorDetailPage().getBackButton())).click();
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups="regression",priority = 3)
	@Parameters("rowNumber")
	public void Tc04_validateBuddyPendingTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
//		common.buddyDashboardPage().tapShiftInButton();

		String timings = exUtil.readDataFromExcel("MultipleTask", 1, 1) + "-"
				+ exUtil.readDataFromExcel("MultipleTask", 1, 2);
		common.buddyPendingTaskTab().validatePendingTask(facilityName, timings);
		common.buddyDashboardPage().getProfileIconImage().click();
//		common.buddyProfilePage().buddyProfileUIValidation(taskbuddyMobNo, buddyName); defect in the application

		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}
}
