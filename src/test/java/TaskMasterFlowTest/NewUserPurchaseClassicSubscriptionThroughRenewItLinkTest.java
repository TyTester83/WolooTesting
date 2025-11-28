package TaskMasterFlowTest;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class NewUserPurchaseClassicSubscriptionThroughRenewItLinkTest extends BaseClass {
	@Test(groups = "sanity",priority = 0)
	@Parameters("rowNumber")
	public void Tc01_newUserTrytoPurchasingNewFacilityTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();
		gUtil.safeClick(common.adminDashboard().getAddFacilityButton(), 4, driver);
		common.newFacilitypage().addNewFacilityUIValidation();
		wait.until(ExpectedConditions.elementToBeClickable(common.newFacilitypage().getFacilityButton())).click();
		common.newUserRenewPopup().newUserWarningPopup();
		wait.until(ExpectedConditions.visibilityOf(common.newFacilitypage().getFacilityButton()));
		gUtil.safeClick(common.newFacilitypage().getBackButton(), 2, driver);
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();
		common.profilePage().logout();
		Thread.sleep(1000);
	}

	@Test(groups = "sanity",priority = 1)
	@Parameters("rowNumber")
	public void Tc02_newUser_Tap_RenewItLink_Purchasing_ClassicSubscriptionTest(@Optional("1") String rowNumber)
			throws InterruptedException, EncryptedDocumentException, IOException {
		int row = Integer.parseInt(rowNumber);
		Map<String, String> data = exUtil.readRowAsMap("Facility", row);
		String adminMobNo = data.get("Admin Mobile No");
		String subscriptionPlan = data.get("Subscription Plan");
		String paymentBank = data.get("Bank");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		common.adminDashboard().adminLogin(adminMobNo);
		common.adminDashboard().switchStoreToDashboard();
		gUtil.safeClick(common.adminDashboard().getRenewLink(), 3, driver);

		common.paynowPage().tapPaymentPlan(subscriptionPlan);
		common.paymentOptionsPage().paymentUIValidation();
		gUtil.safeClick(common.paymentOptionsPage().getNetBankButton(), 3, driver);
//		wait.until(ExpectedConditions.elementToBeClickable(common.paymentOptionsPage().getNetBankButton())).click();
		common.netBankPage().tapPaymentBank(paymentBank);
		common.paymentConfirmPage().confirmationUIValidation();
		wait.until(ExpectedConditions.visibilityOf(common.adminDashboard().getAdminDashboardText()));
//		common.premiumPopup().isPopupVisible();
		wait.until(ExpectedConditions.elementToBeClickable(common.adminDashboard().getProfileTab())).click();

		common.profilePage().logout();
		Thread.sleep(1000);
	}

}
