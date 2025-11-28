package SupervisorFlowTest;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
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
import Utilities.GenericUtility;
import Utilities.ListenerImpUtility;

@Listeners(ListenerImpUtility.class)
public class TaskReassignFlowTest extends BaseClass {
	@Test
	@Parameters("rowNumber")
	public void Tc01_reassignPendingTasktoSametaskBuddyTest(@Optional("0") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String supervisorMobNo = data.get("Supervisor Mobile No");
		String supervisorName = data.get("Supervisor Name");
		String taskTime = data.get("Task Time1") + "-" + data.get("Task End Time1");
		String buddyName = data.get("TaskBuddy Name");
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String taskReassignTime = data.get("Task Time1");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		common.pendingTaskTab().checkPendingTask(taskTime, buddyName);

		wait.until(ExpectedConditions.elementToBeClickable(common.pendingTaskTab().getReassignButton())).click();
		common.searchJanitorsPage().tapReassignByName_MobileNo(adminMobNo, facilityName, buddyName, taskbuddyMobNo);
		common.schedulePage().reassignTasktoJanitor(taskReassignTime);
		common.pendingTaskTab().reAssignTaskValidation(facilityName, taskReassignTime, buddyName);
		common.supervisorDashboardPage().getAccountImage().click();

		common.accountPage().tapLogout();
		Thread.sleep(1000);
	}

	@Test
	@Parameters("rowNumber")
	public void Tc02_reassignPendingTasktoDifferenttaskBuddyTest(@Optional("0") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String supervisorMobNo = data.get("Supervisor Mobile No");
		String supervisorName = data.get("Supervisor Name");
		String taskTime = data.get("Task Time1") + "-" + data.get("Task End Time1");
		String buddyName = data.get("TaskBuddy Name");
		String diffBuddyName = data.get("TaskBuddy Name");
		String diffTaskbuddyMobNo = data.get("Buddy Mobile No");
		String taskReassignTime = data.get("Task Time1");
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		common.pendingTaskTab().checkPendingTask(taskTime, buddyName);

		wait.until(ExpectedConditions.elementToBeClickable(common.pendingTaskTab().getReassignButton())).click();
		common.searchJanitorsPage().tapReassignByName_MobileNo(adminMobNo, facilityName, diffBuddyName,
				diffTaskbuddyMobNo);
		common.schedulePage().reassignTasktoJanitor(taskReassignTime);
		common.pendingTaskTab().reAssignTaskValidation(facilityName, taskReassignTime, diffBuddyName);
		common.supervisorDashboardPage().getAccountImage().click();

		common.accountPage().tapLogout();
		Thread.sleep(1000);
	}

	@Test
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

	@Test
	@Parameters("rowNumber")
	public void Tc04_reassignReportedIssueTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String supervisorMobNo = data.get("Supervisor Mobile");
		String supervisorName = data.get("Supervisor Name");
		String buddyName = data.get("TaskBuddy Name");
		String facilityName = data.get("Facility Name");
		String taskName = data.get("Task Name");
		String taskStartTime = data.get("Task Time2");
		String taskEndTime = data.get("Task End Time2");
		String taskTemplateID = "91FAB2";
		String description = "Not properly cleaned";
		String adminType = data.get("Admin Type");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		common.supervisorDashboardPage().supervisorLogin(supervisorMobNo, supervisorName, adminType);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getIssueImage())).click();

		List<String> taskNames = new ArrayList<>();
		taskNames.add(taskName);
		common.issuePage().reassignReportAnIssueTask(adminMobNo, facilityName, taskTemplateID, taskNames, description,
				buddyName, taskStartTime, taskEndTime);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(common.supervisorDashboardPage().getAccountImage())).click();

		common.accountPage().tapLogout();
		Thread.sleep(1000);

	}
}
