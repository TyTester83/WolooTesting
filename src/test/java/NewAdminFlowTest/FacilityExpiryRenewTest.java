package NewAdminFlowTest;

import java.io.IOException;
import java.time.Duration;
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
import io.appium.java_client.AppiumBy;

public class FacilityExpiryRenewTest extends BaseClass {
	@Test(groups = "regression", priority = 1)
	@Parameters("rowNumber")
	public void Tc01_renewClassicFacilityExpiryTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddyName = data.get("TaskBuddy Name");
		String paymentBank = data.get("Bank");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();
		common.adminDashboard().navigateToFacilityByName(facilityName);
		common.facilityRenewPopup().tapFacilityRenewButton();
		common.premiumPage().tapClassicFacility(facilityName);

		common.paymentOptionsPage().paymentUIValidation();
		gUtil.safeClick(common.paymentOptionsPage().getNetBankButton(), 3, driver);
		common.netBankPage().tapPaymentBank(paymentBank);
		common.paymentConfirmPage().confirmationUIValidation();
		common.adminDashboard().navigateToFacilityByName(facilityName);
//		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTaskbuddyDropdown())).click();
		gUtil.safeClick(common.adminDashboard().getTaskbuddyDropdown(), 3, driver);
		Thread.sleep(2000);
		WebElement actualTaskbuddy = wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + taskBuddyName + "')]")));

		Assert.assertTrue(actualTaskbuddy.isDisplayed(), "TaskBuddy option not displayed");
//		wait.until(ExpectedConditions.elementToBeClickable(actualTaskbuddy)).click();
		gUtil.safeClick(actualTaskbuddy, 3, driver);
		common.adminDashboard().taskEfficiencyCalculation();
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "regression", priority = 2)
	@Parameters("rowNumber")
	public void Tc02_renewPremiumFacilityExpiryTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddyName = data.get("TaskBuddy Name");
		String paymentBank = data.get("Bank");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();
		common.adminDashboard().navigateToFacilityByName(facilityName);
		common.facilityRenewPopup().tapFacilityRenewButton();
		common.premiumPage().tapClassicFacility(facilityName);

		common.paymentOptionsPage().paymentUIValidation();
		wait.until(ExpectedConditions.elementToBeClickable(common.paymentOptionsPage().getNetBankButton())).click();
		common.netBankPage().tapPaymentBank(paymentBank);
		common.paymentConfirmPage().confirmationUIValidation();
		common.adminDashboard().navigateToFacilityByName(facilityName);

		common.iotDashboardPage().iotAirQualityUIValidation();
		common.iotDashboardPage().usageUIValidation();
		common.iotDashboardPage().taskAuditUIValidation();
//		wait.until(ExpectedConditions.elementToBeClickable(common.iotDashboardPage().getTaskbuddyDropdown())).click();
		gUtil.safeClick(common.iotDashboardPage().getTaskbuddyDropdown(), 3, driver);
		Thread.sleep(2000);
		WebElement actualTaskbuddy = wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + taskBuddyName + "')]")));

		Assert.assertTrue(actualTaskbuddy.isDisplayed(), "TaskBuddy option not displayed");
//		wait.until(ExpectedConditions.elementToBeClickable(actualTaskbuddy)).click();
		gUtil.safeClick(actualTaskbuddy, 2, driver);
		common.iotDashboardPage().iOtTaskEfficiencyCalculation();
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "regression", priority = 3)
	@Parameters("rowNumber")
	public void Tc03_newUserRenewClassicExpiryTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddyName = data.get("TaskBuddy Name");
		String paymentBank = data.get("Bank");
		String taskMasterPlan = data.get("Subscription Plan");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();
		common.facilityRenewPopup().tapPayNowButton();
		common.paynowPage().paynowScreenUIValidation();
		common.paynowPage().tapPaymentPlan(taskMasterPlan);

		common.paymentOptionsPage().getNetBankButton().click();
		common.netBankPage().tapPaymentBank(paymentBank);
		common.paymentConfirmPage().confirmationUIValidation();
		common.adminDashboard().navigateToFacilityByName(facilityName);
//		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getTaskbuddyDropdown())).click();
		gUtil.safeClick(common.adminDashboard().getTaskbuddyDropdown(), 3, driver);
		Thread.sleep(2000);
		WebElement actualTaskbuddy = wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + taskBuddyName + "')]")));

		Assert.assertTrue(actualTaskbuddy.isDisplayed(), "TaskBuddy option not displayed");
//		wait.until(ExpectedConditions.elementToBeClickable(actualTaskbuddy)).click();
		gUtil.safeClick(actualTaskbuddy, 2, driver);
		common.adminDashboard().taskEfficiencyCalculation();
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);

	}

	@Test(groups = "regression", priority = 4)
	@Parameters("rowNumber")
	public void Tc04_newUserRenewPremiumFacilityExpiryTest(@Optional("1") String rowNumber)
			throws EncryptedDocumentException, IOException, InterruptedException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String facilityName = data.get("Facility Name");
		String taskBuddyName = data.get("TaskBuddy Name");
		String paymentBank = data.get("Bank");
		String taskMasterPlan = data.get("Subscription Plan");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();
		common.facilityRenewPopup().tapPayNowButton();
		common.paynowPage().paynowScreenUIValidation();
		common.paynowPage().tapPaymentPlan(taskMasterPlan);

		common.paymentOptionsPage().getNetBankButton().click();
		common.netBankPage().tapPaymentBank(paymentBank);
		common.paymentConfirmPage().confirmationUIValidation();

		common.adminDashboard().navigateToFacilityByName(facilityName);
		common.iotDashboardPage().iotAirQualityUIValidation();
		common.iotDashboardPage().usageUIValidation();
		common.iotDashboardPage().taskAuditUIValidation();
//		wait.until(ExpectedConditions.elementToBeClickable(common.iotDashboardPage().getTaskbuddyDropdown())).click();
		gUtil.safeClick(common.iotDashboardPage().getTaskbuddyDropdown(), 3, driver);
		Thread.sleep(2000);
		WebElement actualTaskbuddy = wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + taskBuddyName + "')]")));

		Assert.assertTrue(actualTaskbuddy.isDisplayed(), "TaskBuddy option not displayed");
//		wait.until(ExpectedConditions.elementToBeClickable(actualTaskbuddy)).click();
		gUtil.safeClick(actualTaskbuddy, 2, driver);
		common.iotDashboardPage().iOtTaskEfficiencyCalculation();
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);
	}

}
