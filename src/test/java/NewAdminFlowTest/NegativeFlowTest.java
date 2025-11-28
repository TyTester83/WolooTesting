package NewAdminFlowTest;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Utilities.BaseClass;
import Utilities.ListenerImpUtility;
import io.appium.java_client.AppiumBy;

@Listeners(ListenerImpUtility.class)
public class NegativeFlowTest extends BaseClass {

	@Test(groups = {"regression" }, priority = 1, enabled=false)
	public void Tc01_validateEmptyLoginTest() {
		common.loginPage().loginUIValidation();
		common.loginPage().getSendOTPButton().click();
		Assert.assertTrue(common.loginPage().getErrorMsg1().isDisplayed(), "Message Not displayed");
	}

	@Test(groups = {"regression" }, priority = 2, enabled=false)
	public void Tc02_validateInvalidMobileNoTest()
			throws InterruptedException, EncryptedDocumentException, IOException {

		Map<String, String> data = exUtil.readRowAsMap("NegativeLogin", 1);
		String adminMobNo = data.get("Admin Mobile No");

		common.loginPage().loginUIValidation();
		gUtil.safeSendKeys(common.loginPage().getMobNoTextfield(), adminMobNo, 4, driver);
		Assert.assertTrue(common.loginPage().getErrorMsg2().isDisplayed(), "Message Not displayed");
	}

	@Test(groups = {"regression" }, priority = 3, enabled=false)
	public void Tc03_validateMobileNoDigitsTest() throws InterruptedException, Exception, IOException {
		Map<String, String> data = exUtil.readRowAsMap("NegativeLogin", 2);
		String adminMobNo = data.get("Admin Mobile No");

		common.loginPage().loginUIValidation();
		gUtil.safeSendKeys(common.loginPage().getMobNoTextfield(), adminMobNo, 4, driver);
		Assert.assertTrue(common.loginPage().getErrorMsg2().isDisplayed(), "Message Not displayed");
	}

	@Test(groups = {"regression" }, priority = 4, enabled=false)
	public void Tc04_verifyInvalidOTPTest() throws InterruptedException, EncryptedDocumentException, IOException {
		Map<String, String> data = exUtil.readRowAsMap("Facility", 5);
		String adminMobNo = data.get("Admin Mobile No");

		common.loginPage().loginUIValidation();
		common.loginPage().login(adminMobNo);
		common.otpPage().otpUIvalidation();
		common.otpPage().enterOTP("1235");
		Thread.sleep(1000);
		Assert.assertTrue(common.otpPage().getSubmitButton().isDisplayed(), "Error message not displayed");

	}

	@Test(groups = {"regression" }, priority = 5)
	public void Tc05_validateEmptyFacilityFieldTest()
			throws InterruptedException, EncryptedDocumentException, IOException {
		Map<String, String> data = exUtil.readRowAsMap("Facility", 5);
		String adminMobNo = data.get("Admin Mobile No");
		common.adminDashboard().adminLogin(adminMobNo);
		
		common.explorePage().exploreUIvalidation();
		common.explorePage().tapTaskMasterExploreButton();
		common.getStartPage().getStartUIValidation();
		common.getStartPage().tapGetStartedButton();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityEmptyFieldErrorMessageValidation();

	}

	@Test(groups = {"regression" }, priority = 6)
	public void Tc06_withoutEnteringLocationTapNextButtonTest()
			throws InterruptedException, EncryptedDocumentException, IOException {

		Map<String, String> data = exUtil.readRowAsMap("Facility", 5);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");

		try {
			WebElement LoginTextfield = driver.findElement(AppiumBy.xpath("//android.widget.EditText"));

			if (LoginTextfield.isDisplayed()) {
				System.out.println("Login screen is displayed. Running login flow...");
				common.adminDashboard().adminLogin(adminMobNo);
			} else {
				System.out.println("Admin button not displayed. Skipping login.");
			}

		} catch (Exception e) {
			System.out.println("Login screen not found or already logged in. Proceeding to Explore.");
		}

		common.explorePage().exploreUIvalidation();
		common.explorePage().tapTaskMasterExploreButton();
		common.getStartPage().getStartUIValidation();
		common.getStartPage().tapGetStartedButton();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().tapNextButtonWithoutLocation(facilityName, facilityType, otherFacilityName);

	}

	@Test(groups = {"regression" }, priority = 7)
	public void Tc07_assignTaskEmptyFieldValidationTest()
			throws InterruptedException, EncryptedDocumentException, IOException {
		Map<String, String> data = exUtil.readRowAsMap("Facility", 5);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");

		try {
			WebElement LoginTextfield = driver.findElement(AppiumBy.xpath("//android.widget.EditText"));

			if (LoginTextfield.isDisplayed()) {
				System.out.println("Login screen is displayed. Running login flow...");
				common.adminDashboard().adminLogin(adminMobNo);
			} else {
				System.out.println("Admin button not displayed. Skipping login.");
			}

		} catch (Exception e) {
			System.out.println("Login screen not found or already logged in. Proceeding to Explore.");
		}

		common.explorePage().exploreUIvalidation();
		common.explorePage().tapTaskMasterExploreButton();
		common.getStartPage().getStartUIValidation();
		common.getStartPage().tapGetStartedButton();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityDetailsForm(facilityName, location, facilityType, otherFacilityName);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().taskScreenEmptyfieldValidation();

	}

	@Test(groups = { "regression" }, priority = 8)
	public void Tc08_validateChooseAdminErrorMessageTest()
			throws InterruptedException, EncryptedDocumentException, IOException {

		Map<String, String> data = exUtil.readRowAsMap("Facility", 5);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");
		String taskName = data.get("Task Name");
		String shiftTime = data.get("Shift Time");
		String startTaskTime = data.get("Task Time1");
		String endTaskTime = data.get("Task End Time1");
		String expectedTaskTime = "25";

		try {
			WebElement LoginTextfield = driver.findElement(AppiumBy.xpath("//android.widget.EditText"));

			if (LoginTextfield.isDisplayed()) {
				System.out.println("Login screen is displayed. Running login flow...");
				common.adminDashboard().adminLogin(adminMobNo);
			} else {
				System.out.println("Admin button not displayed. Skipping login.");
			}

		} catch (Exception e) {
			System.out.println("Login screen not found or already logged in. Proceeding to Explore.");
		}

		common.explorePage().exploreUIvalidation();
		common.explorePage().tapTaskMasterExploreButton();
		common.getStartPage().getStartUIValidation();
		common.getStartPage().tapGetStartedButton();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityDetailsForm(facilityName, location, facilityType, otherFacilityName);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().assignTaskforNewTaskbuddy(facilityName, facilityType, taskName, expectedTaskTime, shiftTime, startTaskTime, endTaskTime);
		
		common.adminPage().validateChooseAdminErrorMessage();
	}

	@Test(groups = {"regression" }, priority = 9)
	public void Tc09_assignSupervisorEmptyFieldValidationTest()
			throws InterruptedException, EncryptedDocumentException, IOException {

		Map<String, String> data = exUtil.readRowAsMap("Facility", 5);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");
		String taskName = data.get("Task Name");
		String shiftTime = data.get("Shift Time");
		String adminType = data.get("Admin Type");
		String startTaskTime = data.get("Task Time1");
		String endTaskTime = data.get("Task End Time1");
		String expectedTaskTime = "25";

		try {
			WebElement LoginTextfield = driver.findElement(AppiumBy.xpath("//android.widget.EditText"));

			if (LoginTextfield.isDisplayed()) {
				System.out.println("Login screen is displayed. Running login flow...");
				common.adminDashboard().adminLogin(adminMobNo);
			} else {
				System.out.println("Admin button not displayed. Skipping login.");
			}

		} catch (Exception e) {
			System.out.println("Login screen not found or already logged in. Proceeding to Explore.");
		}

		common.explorePage().exploreUIvalidation();
		common.explorePage().tapTaskMasterExploreButton();
		common.getStartPage().getStartUIValidation();
		common.getStartPage().tapGetStartedButton();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityDetailsForm(facilityName, location, facilityType, otherFacilityName);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().assignTaskforNewTaskbuddy(facilityName, facilityType, taskName, expectedTaskTime, shiftTime, startTaskTime, endTaskTime);
		

		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);

		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorErrorMessageValidation(adminType);
	}

	@Test(groups = {"regression" }, priority = 10)
	public void Tc10_taskbuddyEmptyFieldvalidationTest() throws Exception {

		Map<String, String> data = exUtil.readRowAsMap("Facility", 5);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String location = data.get("Location");
		String facilityType = data.get("Facility Type");
		String otherFacilityName = data.get("Other Facility Name");
		String taskName = data.get("Task Name");
		String shiftTime = data.get("Shift Time");
		String startTaskTime = data.get("Task Time1");
		String endTaskTime = data.get("Task End Time1");
		String expectedTaskTime = "25";
		String adminType = data.get("Admin Type");
		String supervisorName = data.get("Supervisor Name");
		String supervisorMobNo = data.get("Supervisor Mobile");

		try {
			WebElement LoginTextfield = driver.findElement(AppiumBy.xpath("//android.widget.EditText"));

			if (LoginTextfield.isDisplayed()) {
				System.out.println("Login screen is displayed. Running login flow...");
				common.adminDashboard().adminLogin(adminMobNo);
			} else {
				System.out.println("Admin button not displayed. Skipping login.");
			}

		} catch (Exception e) {
			System.out.println("Login screen not found or already logged in. Proceeding to Explore.");
		}

		common.explorePage().exploreUIvalidation();
		common.explorePage().tapTaskMasterExploreButton();
		common.getStartPage().getStartUIValidation();
		common.getStartPage().tapGetStartedButton();
		common.facilityPage().facilityUIvalidation();
		common.facilityPage().facilityDetailsForm(facilityName, location, facilityType, otherFacilityName);
		common.taskPage().assignTaskUIValidation();
		common.taskPage().assignTaskforNewTaskbuddy(facilityName, facilityType, taskName, expectedTaskTime, shiftTime, startTaskTime, endTaskTime);
		

		common.adminPage().chooseAdminUIValidation();
		common.adminPage().chooseAdmin(adminType);

		common.supervisorPage().supervisorUIValidation(adminType);
		common.supervisorPage().supervisorDetails(adminType, supervisorName, supervisorMobNo);

		common.buddyPage().taskBuddyUIValidation();
		common.buddyPage().taskBuddyErrorMessageValidation();

	}
}
