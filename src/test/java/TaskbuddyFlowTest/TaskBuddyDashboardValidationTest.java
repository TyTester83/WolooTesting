package TaskbuddyFlowTest;

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

public class TaskBuddyDashboardValidationTest extends BaseClass{
	
	@Test(groups = "regression", priority = 1)
	@Parameters("rowNumber")
	public void TC_01taskBuddyDashboardUIValidationtTest(@Optional("1") String rowNumber) throws EncryptedDocumentException, IOException, InterruptedException
	{
		
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().tapShiftInButton();
		common.buddyDashboardPage().taskBuddyPerformanceScoreUIValidation();
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyDashboardPage().getProfileIconImage().click();
		common.buddyProfilePage().tapLogout();
	}
	
	@Test(groups = "regression", priority = 2)
	@Parameters("rowNumber")
	public void TC_02taskBuddyProfileUIValidationtTest(@Optional("1") String rowNumber) throws EncryptedDocumentException, IOException, InterruptedException
	{
		
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().buddyProfileUIValidation(taskbuddyMobNo, buddyName);
		common.buddyProfilePage().tapLogout();
	}
	
	@Test(groups = "regression", priority = 3)
	@Parameters("rowNumber")
	public void TC_03taskBuddyRegisterationWolooPointsValidationtTest(@Optional("1") String rowNumber) throws EncryptedDocumentException, IOException, InterruptedException
	{
		
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String registrationPts="2000";
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().buddyProfileUIValidation(taskbuddyMobNo, buddyName);
		common.buddyProfilePage().wolooPointValidation(registrationPts);
		common.buddyProfilePage().tapLogout();
	}
	
	@Test(groups = "regression", priority = 4)
	@Parameters("rowNumber")
	public void TC_04taskBuddyShiftLoginWolooPointsValidationtTest(@Optional("1") String rowNumber) throws EncryptedDocumentException, IOException, InterruptedException
	{
		
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String registrationPts="2010";
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().tapShiftInButton();
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().buddyProfileUIValidation(taskbuddyMobNo, buddyName);
		common.buddyProfilePage().wolooPointValidation(registrationPts);
		common.buddyProfilePage().tapLogout();
	}
	
	@Test(groups = "regression", priority = 5)
	@Parameters("rowNumber")
	public void TC_05taskBuddyDashboardPendingTaskValidationTest(@Optional("1") String rowNumber) throws EncryptedDocumentException, IOException, InterruptedException
	{
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		String pendingTaskCount = data.get("TaskCount");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().tapShiftInButton();
		common.buddyDashboardPage().taskBuddyEfficiencyCalculation();
		String actualPendingTaskCount = common.buddyDashboardPage().getPendingTaskCountText().getAttribute("content-desc")
				.trim();
		Assert.assertTrue(actualPendingTaskCount.contains(pendingTaskCount), actualPendingTaskCount + "NOt updated");
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().tapLogout();
	}
	
	@Test(groups = "regression", priority = 6)
	@Parameters("rowNumber")
	public void TC_06taskBuddyDashboardAcceptedTaskValidationTest(@Optional("1") String rowNumber) throws EncryptedDocumentException, IOException, InterruptedException
	{
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().taskBuddyEfficiencyCalculation();
		String actualAcceptedTaskCount = common.buddyDashboardPage().getAcceptedTaskCountText().getAttribute("content-desc")
				.trim();
		Assert.assertTrue(actualAcceptedTaskCount.contains("1"), actualAcceptedTaskCount + "NOt updated");
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().tapLogout();
	}
	
	@Test(groups = "regression", priority = 7)
	@Parameters("rowNumber")
	public void TC_07taskBuddyDashboardOngoingTaskValidationTest(@Optional("1") String rowNumber) throws EncryptedDocumentException, IOException, InterruptedException
	{
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().taskBuddyEfficiencyCalculation();
		String actualOngoingTaskCount = common.buddyDashboardPage().getOngoingTaskCountText().getAttribute("content-desc")
				.trim();
		Assert.assertTrue(actualOngoingTaskCount.contains("1"), actualOngoingTaskCount + "NOt updated");
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().tapLogout();
	}
	
	@Test(groups = "regression", priority = 8)
	@Parameters("rowNumber")
	public void TC_08taskBuddyDashboardClosureTaskValidationTest(@Optional("1") String rowNumber) throws EncryptedDocumentException, IOException, InterruptedException
	{
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().taskBuddyEfficiencyCalculation();
		String actualClosureTaskCount = common.buddyDashboardPage().getClosureTaskCountText().getAttribute("content-desc")
				.trim();
		Assert.assertTrue(actualClosureTaskCount.contains("1"), actualClosureTaskCount + "NOt updated");
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().tapLogout();
	}

	@Test(groups = "regression", priority = 9)
	@Parameters("rowNumber")
	public void TC_09taskBuddyDashboardCompletedTaskValidationTest(@Optional("1") String rowNumber) throws EncryptedDocumentException, IOException, InterruptedException
	{
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().taskBuddyEfficiencyCalculation();
		String actualCompletedTaskCount = common.buddyDashboardPage().getCompletedTaskCountText().getAttribute("content-desc")
				.trim();
		Assert.assertTrue(actualCompletedTaskCount.contains("1"), actualCompletedTaskCount + "NOt updated");
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().tapLogout();
	}
	
	@Test(groups = "regression", priority = 10)
	@Parameters("rowNumber")
	public void TC_10taskBuddyDashboardRejectedTaskValidationTest(@Optional("1") String rowNumber) throws EncryptedDocumentException, IOException, InterruptedException
	{
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String taskbuddyMobNo = data.get("Buddy Mobile No");
		String buddyName = data.get("TaskBuddy Name");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		common.buddyDashboardPage().taskbuddyLogin(taskbuddyMobNo, buddyName);
		common.buddyDashboardPage().taskBuddyEfficiencyCalculation();
		String actualRejectedTaskCount = common.buddyDashboardPage().getRejectedTaskCountText().getAttribute("content-desc")
				.trim();
		Assert.assertTrue(actualRejectedTaskCount.contains("1"), actualRejectedTaskCount + "Not updated");
		wait.until(ExpectedConditions.visibilityOf(common.buddyDashboardPage().getProfileIconImage())).click();
		common.buddyProfilePage().tapLogout();
	}
}
