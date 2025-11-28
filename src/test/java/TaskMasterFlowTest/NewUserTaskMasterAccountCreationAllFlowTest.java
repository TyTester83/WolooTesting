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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.BaseClass;

//@Listeners(ListenerImpUtility.class)
public class NewUserTaskMasterAccountCreationAllFlowTest extends BaseClass {
	@Test(groups = "sanity", priority = 0)
	@Parameters("rowNumber")
	public void Tc01_newUserLogin_AssignSingleTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");
		String taskName = data.get("Task Name");
		String shiftTime = data.get("Shift Time");
		String startTaskTime = data.get("Task Time1");
		String endTaskTime = data.get("Task End Time1");
		String adminType = data.get("Admin Type");
		String supervisorName =data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String taskBuddy = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String expectTotalTime = "25";
		String registrationPoints = "5200";
		String pendingTaskCount = "1";

//		common.welcomePage().newUserLoginValidation(adminMobNo);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.explorePage().exploreUIvalidation();
		common.explorePage().tapTaskMasterExploreButton();
		common.getStartPage().getStartUIValidation();
		common.getStartPage().tapGetStartedButton();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityDetailsForm(facilityName, location, facilityType, otherFacilityName);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().assignTaskforNewTaskbuddy(facilityName, facilityType, taskName, expectTotalTime, shiftTime,
				startTaskTime, endTaskTime);

		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);

		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorDetails(adminType, supervisorName, supervisorMobNo);

		common.buddyPage().taskBuddyUIValidation();
		common.buddyPage().taskBuddyDetailsFill(taskBuddy, buddyMobNo, gender);
		common.congrats().congratsUIvalidation();

		common.adminDashboard().newUserDashboardUIValidation(facilityName, taskBuddy, adminType, pendingTaskCount);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().validateWolooPoints(registrationPoints);
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "sanity", priority = 1,enabled=false)
	@Parameters("rowNumber")
	public void Tc02_newUserLogin_AssignSingleTask_differentTimeTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber) + 1;
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		Map<String, String> taskTime = exUtil.readRowAsMap("TaskTime", 1);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");
		String taskName = taskTime.get("Task Name");
		String adminType = data.get("Admin Type");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String taskBuddy = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String registrationPoints = "5200";
		String taskBuddyType = "New";
		String totalTaskTime = "30";
		int totalTaskCount = (taskTime.size() - 1) / 2;

//		common.welcomePage().newUserLoginValidation(adminMobNo);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.explorePage().exploreUIvalidation();
		common.explorePage().tapTaskMasterExploreButton();
		common.getStartPage().getStartUIValidation();
		common.getStartPage().tapGetStartedButton();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityDetailsForm(facilityName, location, facilityType, otherFacilityName);
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
		common.buddyPage().taskBuddyDetailsFill(taskBuddy, buddyMobNo, gender);
		common.congrats().congratsUIvalidation();

		common.adminDashboard().newUserDashboardUIValidation(facilityName, taskBuddy, adminType,
				String.valueOf(totalTaskCount));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().validateWolooPoints(registrationPoints);
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "sanity", priority = 2)
	@Parameters("rowNumber")
	public void Tc03_newUserLogin_AssignMultipleTask_differentTimeTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber) + 2;
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");
//		String taskName = data.get("Task Name");
		String adminType = data.get("Admin Type");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String taskBuddy = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String registrationPoints = "5200";
		String taskBuddyType = "New";

		Map<String, List<String>> taskTimeMap = new HashMap<>();
		int rowCount = exUtil.getRowCount("MultipleTask&Time");
		System.out.println(rowCount + "---------------------------------------");

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

//		common.welcomePage().newUserLoginValidation(adminMobNo);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.explorePage().exploreUIvalidation();
		common.explorePage().tapTaskMasterExploreButton();
		common.getStartPage().getStartUIValidation();
		common.getStartPage().tapGetStartedButton();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityDetailsForm(facilityName, location, facilityType, otherFacilityName);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().assignMultipleTaskDifferentTiming(facilityName, facilityType, taskTimeMap, taskBuddyType);
		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);

		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorDetails(adminType, supervisorName, supervisorMobNo);

		common.buddyPage().taskBuddyUIValidation();
		common.buddyPage().taskBuddyDetailsFill(taskBuddy, buddyMobNo, gender);
		common.congrats().congratsUIvalidation();

		common.adminDashboard().newUserDashboardUIValidation(facilityName, taskBuddy, adminType,
				String.valueOf(rowCount));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().validateWolooPoints(registrationPoints);
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "sanity", priority = 3)
	@Parameters("rowNumber")
	public void Tc04_newUserLogin_AssignSingleTask_Delete_AssignTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber) + 3;
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");
		String taskName = data.get("Task Name");
		String startTaskTime = data.get("Task Time1");
		String endTaskTime = data.get("Task End Time1");
		String adminType = data.get("Admin Type");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String taskBuddy = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String expectTotalTime = "25";
		String registrationPoints = "5200";
		String pendingTaskCount = "1";
		String taskBuddyType = "New";

//		common.welcomePage().newUserLoginValidation(adminMobNo);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.explorePage().exploreUIvalidation();
		common.explorePage().tapTaskMasterExploreButton();
		common.getStartPage().getStartUIValidation();
		common.getStartPage().tapGetStartedButton();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityDetailsForm(facilityName, location, facilityType, otherFacilityName);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().assignTaskDeletionForNewTaskbuddy(facilityName, facilityType, taskName, expectTotalTime,
				taskBuddyType, startTaskTime, endTaskTime);
		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);

		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorDetails(adminType, supervisorName, supervisorMobNo);

		common.buddyPage().taskBuddyUIValidation();
		common.buddyPage().taskBuddyDetailsFill(taskBuddy, buddyMobNo, gender);
		common.congrats().congratsUIvalidation();

		common.adminDashboard().newUserDashboardUIValidation(facilityName, taskBuddy, adminType, pendingTaskCount);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().validateWolooPoints(registrationPoints);
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "sanity", priority = 4)
	@Parameters("rowNumber")
	public void Tc05_newUserLogin_ExistingTaskbuddyMobileNoValidationTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber) + 4;
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");
		String taskName = data.get("Task Name");
		String startTaskTime = data.get("Task Time1");
		String endTaskTime = data.get("Task End Time1");
		String adminType = data.get("Admin Type");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String taskBuddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String expectTotalTime = "25";
		String registrationPoints = "5200";
		String pendingTaskCount = "1";
		String shiftTime = data.get("Shift Time");

//		common.welcomePage().newUserLoginValidation(adminMobNo);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.explorePage().exploreUIvalidation();
		common.explorePage().tapTaskMasterExploreButton();
		common.getStartPage().getStartUIValidation();
		common.getStartPage().tapGetStartedButton();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityDetailsForm(facilityName, location, facilityType, otherFacilityName);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().assignTaskforNewTaskbuddy(facilityName, facilityType, taskName, expectTotalTime, shiftTime,
				startTaskTime, endTaskTime);
		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);

		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorDetails(adminType, supervisorName, supervisorMobNo);

		common.buddyPage().taskBuddyUIValidation();
		common.buddyPage().existingTaskBuddyMobileNoValidation(taskBuddyName, adminMobNo, gender, buddyMobNo);
		common.congrats().congratsUIvalidation();

		common.adminDashboard().newUserDashboardUIValidation(facilityName, taskBuddyName, adminType, pendingTaskCount);
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().validateWolooPoints(registrationPoints);
		common.profilePage().logout();
	
	}

}
