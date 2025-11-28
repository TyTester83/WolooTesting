package SupervisorFlowTest;

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

public class SupervisorValidatePendingTaskTest extends BaseClass {

	@Test(groups = "sanity", priority = 1)
	@Parameters("rowNumber")
	public void Tc01_pendingTaskValidationTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile No");
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
	public void Tc02_taskbuddyPendingTaskUpdatedInSupervisors_JanitorsDetailsScreenTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String supervisorMobNo = data.get("Supervisor Mobile No");
		String supervisorName = data.get("Supervisor Name");
		String buddyName = data.get("TaskBuddy Name");
		String buddyMobNo = data.get("Buddy Mobile No");
		String gender = data.get("Gender");
		String taskCount = data.get("TaskCount");
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
}
