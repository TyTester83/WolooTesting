package TaskMasterFlowTest;

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

//@Listeners(ListenerImpUtility.class)
public class AddNewTaskbuddyAllFlowTest extends BaseClass {

	@Test(groups = { "sanity", "regression" }, priority = 0)
	@Parameters("rowNumber")
	public void Tc01_addNewTaskbuddyForClassicFacilityTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("AddNewTaskbuddy", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
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
		String taskEstimateTime = "25";
		String taskBuddyType = "New";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();

		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
		common.newFacilitypage().tapNewTaskButton();
		common.newTaskPopup().newTaskPopupUIValidation();
		common.newTaskPopup().tapTaskbuddy(taskBuddyType);
		common.facilityPage().chooseFacilityfromDropdown(facilityName, location, facilityType);
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
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddyName);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = { "sanity", "regression" }, priority = 1)
	@Parameters("rowNumber")
	public void Tc02_addNewTaskbuddy_SingleTask_MultipleTaskTimeTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber) + 1;
		Map<String, String> data = exUtil.readRowAsMap("AddNewTaskbuddy", row);
		Map<String, String> taskTime = exUtil.readRowAsMap("TaskTime", 1);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String taskName = taskTime.get("Task Name");
//		String shiftTime = data.get("Shift Time");
		String adminType = data.get("Admin Type");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String taskBuddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String taskBuddyType = "New";
		String totalTaskTime = "30 min";
		int totalTaskCount = (taskTime.size() - 1) / 2;

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
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
		common.facilityPage().chooseFacilityfromDropdown(facilityName, location, facilityType);
		common.taskPage().assignTaskUIValidation();

		List<String> startTasktimings = new ArrayList<String>();
		List<String> endTasktimings = new ArrayList<String>();
		for (int i = 1; i <= totalTaskCount; i++) {
			startTasktimings.add(taskTime.get("Task start Time" + String.valueOf(i)));
			endTasktimings.add(taskTime.get("Task End Time" + String.valueOf(i)));
		}
		common.taskPage().assignMultipleTaskTime(facilityName, facilityType, taskName, totalTaskTime, startTasktimings,
				endTasktimings, taskBuddyType);
		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);
		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorDetails(adminType, supervisorName, supervisorMobNo);
		common.buddyPage().taskBuddyUIValidation();
		common.buddyPage().taskBuddyDetailsFill(taskBuddyName, buddyMobNo, gender);
		common.congrats().congratsUIvalidation();
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddyName);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = { "sanity", "regression" }, priority = 2)
	@Parameters("rowNumber")
	public void Tc03_addNewTaskbuddy_MultipleTask_differentTaskTimeTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber) + 2;
		Map<String, String> data = exUtil.readRowAsMap("AddNewTaskbuddy", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
//		String shiftTime = data.get("Shift Time");
		String adminType = data.get("Admin Type");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String taskBuddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String taskBuddyType = "New";

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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();
		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
		common.newFacilitypage().tapNewTaskButton();
		common.newTaskPopup().newTaskPopupUIValidation();
		common.newTaskPopup().tapTaskbuddy(taskBuddyType);
		common.facilityPage().chooseFacilityfromDropdown(facilityName, location, facilityType);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().assignMultipleTaskDifferentTiming(facilityName, facilityType, taskTimeMap, taskBuddyType);
		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);
		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorDetails(adminType, supervisorName, supervisorMobNo);
		common.buddyPage().taskBuddyUIValidation();
		common.buddyPage().taskBuddyDetailsFill(taskBuddyName, buddyMobNo, gender);
		common.congrats().congratsUIvalidation();
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddyName);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = { "sanity", "regression" }, priority = 3)
	@Parameters("rowNumber")
	public void Tc04_addNewTaskbuddyTaskDeletionFlowTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber) + 3;
		Map<String, String> data = exUtil.readRowAsMap("AddNewTaskbuddy", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String taskName = data.get("Task Name");
//		String shiftTime = data.get("Shift Time");
		String taskStartTime = data.get("Task Time1");
		String taskEndTime = data.get("Task End Time1");
		String adminType = data.get("Admin Type");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String taskBuddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String taskEstimateTime = "25 min";
		String taskBuddyType = "New";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
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
		common.facilityPage().chooseFacilityfromDropdown(facilityName, location, facilityType);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().assignTaskDeletionForNewTaskbuddy(facilityName, facilityType, taskName, taskEstimateTime,
				taskBuddyType, taskStartTime, taskEndTime);
		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);
		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorDetails(adminType, supervisorName, supervisorMobNo);
		common.buddyPage().taskBuddyUIValidation();
		common.buddyPage().taskBuddyDetailsFill(taskBuddyName, buddyMobNo, gender);
		common.congrats().congratsUIvalidation();
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddyName);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = { "sanity", "regression" }, priority = 4)
	@Parameters("rowNumber")
	public void Tc05_addNewTaskbuddyFlow_ExistingTaskbuddyMobileNoValidationTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber) + 4;
		Map<String, String> data = exUtil.readRowAsMap("AddNewTaskbuddy", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
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
		String taskEstimateTime = "25";
		String taskBuddyType = "New";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
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
		common.facilityPage().chooseFacilityfromDropdown(facilityName, location, facilityType);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().assignTaskforNewTaskbuddy(facilityName, facilityType, taskName, taskEstimateTime, shiftTime,
				taskStartTime, taskEndTime);
		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);
		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorDetails(adminType, supervisorName, supervisorMobNo);
		common.buddyPage().taskBuddyUIValidation();
		common.buddyPage().existingTaskBuddyMobileNoValidation(taskBuddyName, adminMobNo, gender, buddyMobNo);
		common.congrats().congratsUIvalidation();
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddyName);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}

}
