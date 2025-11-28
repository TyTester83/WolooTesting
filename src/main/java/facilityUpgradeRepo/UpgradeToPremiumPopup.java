package facilityUpgradeRepo;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.GenericUtility;
import adminDashboardRepo.AdminDashboardPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class UpgradeToPremiumPopup extends BasePage {

	public UpgradeToPremiumPopup(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Upgrade to Premium\")]")
	private WebElement upgradePopupLayout;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Upgrade to Premium\")]/android.widget.ImageView[2]")
	private WebElement premiumIconImage;

	public WebElement getUpgradePopupLayout() {
		return upgradePopupLayout;
	}

	public WebElement getPremiumIconImage() {
		return premiumIconImage;
	}

	public void isPopupVisible() {
		WebDriverWait localWait = waitWithTimeout(Duration.ofSeconds(10));
		try {
			localWait.until(ExpectedConditions.visibilityOf(premiumIconImage));
			if (premiumIconImage.isDisplayed()) {
				Assert.assertEquals(premiumIconImage.isDisplayed(), true);
				try {
					((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
				} catch (Exception ex) {
					System.out.println("BACK key failed too: " + ex.getMessage());
				}
			}
		} catch (TimeoutException e) {
			System.out.println("No premium popup appeared within wait time.");
		} catch (Exception e) {
			System.out.println("There is no premium popup ");
		}
	}

	public void tapPremiumPopup(String facilityName) {
		WebDriverWait localWait = waitWithTimeout(Duration.ofSeconds(10));
		try {
			localWait.until(ExpectedConditions.visibilityOf(premiumIconImage));
			if (premiumIconImage.isDisplayed()) {
				GenericUtility.tapUsingCoordinatePercentage(driver, upgradePopupLayout, 50.0, 74.4);
				return;
			}
		} catch (TimeoutException e) {
			System.out.println("No premium popup appeared within wait time.");
		} catch (Exception e) {
			System.out.println("There is no premium popup ");
		}
		// Navigate to the facility screen if popup was not shown
		try {
			AdminDashboardPage adminDashboard=new AdminDashboardPage(driver);
			adminDashboard.navigateToFacilityByName(facilityName);
			
			localWait.until(ExpectedConditions.visibilityOf(premiumIconImage));
			if (premiumIconImage.isDisplayed()) {
				GenericUtility.tapUsingCoordinatePercentage(driver, upgradePopupLayout, 50.0, 74.4);
			}
		} catch (TimeoutException e) {
			System.out.println("No premium popup appeared within wait time.");
		} catch (Exception e) {
			System.out.println("There is no premium popup ");
		}
	}
}
