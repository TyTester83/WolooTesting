package TaskbuddyFlowTest;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.BaseClass;

public class TaskbuddyCompletedTaskTest extends BaseClass {

	@Test(groups = "sanity", priority = 1)
	@Parameters("rowNumber")
	public void Tc01_validateBuddyCompletedTaskTest(@Optional("1") String rowNumber)
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
}
