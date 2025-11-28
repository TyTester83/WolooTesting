package NewAdminFlowTest;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.BaseClass;
import Utilities.ListenerImpUtility;

@Listeners(ListenerImpUtility.class)
public class AddNewTaskforExistingTaskbuddyTest extends BaseClass {
	@Test(groups = "regression",priority = 1)
	@Parameters("rowNumber")
	public void Tc01_changeShiftTimingTest(@Optional("9") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String taskName = data.get("Task Name");
		String taskBuddyName = data.get("TaskBuddy Name");
		String taskBuddyType = "Existing";
		String estimatedTaskTime = "25";

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
		common.taskPage().changeShiftTiming(facilityName, facilityType, taskName, estimatedTaskTime);
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddyName);
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "regression",priority = 2)
	@Parameters("rowNumber")
	public void Tc02_addNewTaskforSameFacilityTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String taskName = data.get("Task Name");
		String startTaskTime = data.get("Task Time2");
		String taskBuddyName = data.get("TaskBuddy Name");
		String endTaskTime = data.get("Task End Time2");
		String taskBuddyType = "Existing";
		String totalTaskTime = "25 min";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();

		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
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
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "sanity",priority = 3)
	@Parameters("rowNumber")
	public void Tc03_assignSingleTaskForDifferentTimeTest(@Optional("1") String rowNumber)
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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

		List<String> startTasktimings = new ArrayList<String>();
		List<String> endTasktimings = new ArrayList<String>();
		for (int i = 1; i <= totalTaskCount; i++) {
			startTasktimings.add(taskTime.get("Task start Time" + String.valueOf(i)));
			endTasktimings.add(taskTime.get("Task End Time" + String.valueOf(i)));
		}
		common.taskPage().assignMultipleTaskTime(facilityName, facilityType,  taskName, totalTaskTime, startTasktimings,
				endTasktimings,taskBuddyType);

		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddyName);
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}
	@Test(groups="regression",priority = 4)
	@Parameters("rowNumber")
	public void Tc04_assignMultipleTaskAtOneTiming(@Optional("1") String rowNumber)
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

	@Test(groups={"sanity","regression"},priority = 5)
	@Parameters("rowNumber")
	public void Tc05_assignMultipleTaskAtDifferentTiming(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String taskBuddyName = data.get("TaskBuddy Name");
		String taskBuddyType = "Existing";
//		String totalTaskTime = "30 min";

		Map<String, List<String>> taskTimeMap = new HashMap<>();
		int rowCount = exUtil.getRowCount("MultipleTask&Time");

		for (int i = 1; i <= rowCount; i++) {
		    String taskName = exUtil.readDataFromExcel("MultipleTask&Time", i, 0);
		    String startTasktime = exUtil.readDataFromExcel("MultipleTask&Time", i, 1);
		    String endTasktime = exUtil.readDataFromExcel("MultipleTask&Time", i, 2);

		    // Combine start and end time as a pair
		    List<String> timePair = new ArrayList<>();
		    timePair.add(startTasktime);
		    timePair.add(endTasktime);

		    taskTimeMap.put(taskName, timePair);
		}

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
		common.taskPage().assignMultipleTaskDifferentTiming(facilityName, facilityType, taskTimeMap,taskBuddyType);
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddyName);
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}
}
