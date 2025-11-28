package TaskMasterFlowTest;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.BaseClass;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumBy;

public class SupervisorReassign_RejectedOfClosureTaskTest extends BaseClass {
	@Test(groups = "regression",priority = 0)
	@Parameters("rowNumber")
	public void Tc01_reassignSupervisorRejectedTaskTest(@Optional("1") String rowNumber)
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
		common.pendingTaskTab().reAssignTaskValidation(facilityName, taskTime, buddyName);
		common.supervisorDashboardPage().getAccountImage().click();

		common.accountPage().tapLogout();
		Thread.sleep(1000);
	}

	@Test(groups = "regression",priority = 1)
	@Parameters("rowNumber")
	public void Tc02_taskbuddyPendingTaskUpdatedInSupervisors_JanitorsDetailsScreenTest(@Optional("1") String rowNumber)
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
		String actualPendingTaskCount = common.janitorDetailPage().getPendingTaskCountText()
				.getAttribute("content-desc").trim();
		Assert.assertTrue(actualPendingTaskCount.contains(taskCount),
				actualPendingTaskCount + "Not displayed proper task count");
		wait.until(ExpectedConditions.elementToBeClickable(common.janitorDetailPage().getBackButton())).click();
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();
		common.accountPage().supervisorAccountUIValidation(supervisorMobNo, supervisorName);
		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}

	@Test(groups = "regression",priority = 2)
	@Parameters("rowNumber")
	public void Tc03_supervisorReassignTask_UpdateInAdminDashboardTest(@Optional("1") String rowNumber)
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

		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Store")));
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

	@Test(groups = "regression",priority = 3)
	@Parameters("rowNumber")
	public void Tc04_validateBuddyPendingTaskTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskTime = data.get("Task Time2") + "-" + data.get("Task End Time2");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
//		buddyDashboardPage.tapShiftInButton();

		common.buddyPendingTaskTab().validatePendingTask(facilityName, taskTime);
		wait.until(ExpectedConditions.elementToBeClickable(common.buddyDashboardPage().getProfileIconImage())).click();
//		common.buddyProfilePage().buddyProfileUIValidation(taskbuddyMobNo, buddyName); defect in the application

		common.buddyProfilePage().tapLogout();
		Thread.sleep(1000);
	}
}
