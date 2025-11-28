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
import Utilities.GenericUtility;
import io.appium.java_client.AppiumBy;

public class TaskBuddyRejectedTaskReassignAndTaskCompletionTest extends BaseClass {
	@Test(groups = "regression",priority=0)
	@Parameters("rowNumber")
	public void Tc01_validationOf_PN_for_RejectedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time2") + "-" + data.get("Task End Time2");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().tapLogout();
		Assert.assertTrue(common.welcomePage().getLoginAsDropdown().isDisplayed(), "Logout is not happen");
		Thread.sleep(2000);

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
//		common.buddyDashboardPage().tapShiftInButton();

		common.buddyPendingTaskTab().rejectPendingTask(facilityName, taskTime);
		wait.until(ExpectedConditions.visibilityOf(common.buddyRejectedTab().getRejectedTaskTab()));
		common.buddyRejectedTab().validateRejectedTaskIsDisplayed(facilityName, taskTime);

		Thread.sleep(2000);
		utility.notification();
		WebElement taskRejectedEle = driver.findElement(AppiumBy.xpath(
				"//android.widget.TextView[@resource-id=\"android:id/title\" and contains(@text,\"TASK REJECTED BY\") and contains(@text,'"
						+ buddyName + "')]"));

		Assert.assertTrue(taskRejectedEle.isDisplayed(), "PN is not generated for rejected task ");
		wait.until(ExpectedConditions.visibilityOf(taskRejectedEle));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(taskRejectedEle)).click();

		wait.until(ExpectedConditions.visibilityOf(common.buddyRejectedTab().getRejectedTaskTab()));
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();

		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups = "regression",priority=1)
	@Parameters("rowNumber")
	public void Tc02_supervisorValidateTaskbuddyRejectedTask_UpdatedInJanitorsDetailScreenTest(
			@Optional("1") String rowNumber) throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Admin Mobile No");
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
		String actualRejectedTaskCount = common.janitorDetailPage().getRejectedTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualRejectedTaskCount.contains(taskCount),
				actualRejectedTaskCount + "Not displayed proper task count");

		wait.until(ExpectedConditions.elementToBeClickable(common.janitorDetailPage().getBackButton())).click();
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();

		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups = "regression",priority=2)
	@Parameters("rowNumber")
	public void Tc03_supervisorReassignBuddyRejectedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time2") + "-" + data.get("Task End Time2");
		String taskReassignTime = data.get("Task Time2");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);

		common.supevisorRejectedTab().reassignBuddyRejectedTask(buddyName, taskTime);
		common.searchJanitorsPage().tapReassignByName_MobileNo(adminMobNo, facilityName, buddyName, taskbuddyMobNo);
		common.schedulePage().reassignTasktoJanitor(taskReassignTime);
		wait.until(ExpectedConditions.visibilityOf(common.supevisorRejectedTab().getRejectedTaskTab()));

		String xpath = "//android.view.View[contains(@content-desc,\"Pending task\")]";
		if (GenericUtility.swipeAndFindElementByDirection(driver, xpath,
				common.supevisorRejectedTab().getScrollLayout(), "right", 3)) {
			wait.until(ExpectedConditions.elementToBeClickable(common.pendingTaskTab().getPendingTaskTab())).click();
		}

		common.pendingTaskTab().reAssignTaskValidation(facilityName, taskReassignTime, buddyName);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();

		common.accountPage().tapLogout();
		Thread.sleep(1000);
	}

	@Test(groups = "regression",priority=3)
	@Parameters("rowNumber")
	public void Tc04_supervisorReassignTask_UpdateInAdminDashboardTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddy = data.get("TaskBuddy Name");
		String taskCount = "1";

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		Thread.sleep(10000);

		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Products")));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTasqMasterTab())).click();
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		common.adminDashboard().taskbuddyValidation(facilityName, taskBuddy);

		String actualPendingTaskCount = common.adminDashboard().getPendingTaskCountText().getAttribute("content-desc")
				.trim();
		Assert.assertTrue(actualPendingTaskCount.contains(taskCount), actualPendingTaskCount + "NOt updated");
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "regression",priority=4)
	@Parameters("rowNumber")
	public void Tc05_buddyRejectedTask_Reassign_TaskCompletionTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time2") + "-" + data.get("Task End Time2");
		String taskName = data.get("Task Name2");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
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
		wait.until(ExpectedConditions.elementToBeClickable(taskAcceptEle)).click();

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
		wait.until(ExpectedConditions.elementToBeClickable(taskClosureRequestEle)).click();

		wait.until(ExpectedConditions.visibilityOf(common.buddyRequestForClosureTab().getRequestforClosureTab()));
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();

		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups = "regression",priority=5)
	@Parameters("rowNumber")
	public void Tc06_approveBuddyRequestedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Admin Mobile No");
		String supervisorName = data.get("Supervisor Name");
		String taskTime = data.get("Task Time2") + "-" + data.get("Task End Time2");
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

	@Test(groups = "regression",priority=6)
	@Parameters("rowNumber")
	public void Tc07_completedTaskUpdatedInSupervisors_JanitorsDetailScreenTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Admin Mobile No");
		String supervisorName = data.get("Supervisor Name");
		String buddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String taskCount = "2";
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

	@Test(groups = "regression",priority=7)
	@Parameters("rowNumber")
	public void Tc08_validateBuddyCompletedTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time2") + "-" + data.get("Task End Time2");

		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyCompletedTaskTab().verifyCompletedTaskIsDisplayed(facilityName, taskTime);
		common.buddyDashboardPage().getProfileIconImage().click();
		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups = "regression",priority=8)
	@Parameters("rowNumber")
	public void Tc09_validateCompletedTaskUpdatedInAdminDashboardTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddy = data.get("TaskBuddy Name");
		String taskCount = "2";

		common.adminDashboard().adminLogin(adminMobNo);
		Thread.sleep(10000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Store")));
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
