package razorpayPaymentRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ContactDetailsPage extends BasePage {

	public ContactDetailsPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.Image[@text=\"Logo\"]")
	private WebElement wolooLogoImage;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Total Amount\"]")
	private WebElement totalAmountText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Contact details\"]")
	private WebElement contactDetailsText;

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement mobNoTextfield;

	public WebElement getMobNoTextfield() {
		return mobNoTextfield;
	}

	public void setMobNoTextfield(WebElement mobNoTextfield) {
		this.mobNoTextfield = mobNoTextfield;
	}

	public WebElement getWolooLogoImage() {
		return wolooLogoImage;
	}

	public WebElement getTotalAmountText() {
		return totalAmountText;
	}

	public WebElement getContactDetailsText() {
		return contactDetailsText;
	}

	public WebElement getContinueButton() {
		return continueButton;
	}

	public WebElement getScrollLayout() {
		return scrollLayout;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"Continue\"]")
	private WebElement continueButton;

	@AndroidFindBy(id = "scroll-container")
	private WebElement scrollLayout;

	public void validateContactDetails() {
		GenericUtility gUtil = new GenericUtility();

		try {
//		wait.until(ExpectedConditions.visibilityOf(mobNoTextfield));
//		wait.until(ExpectedConditions.elementToBeClickable(mobNoTextfield)).click();
			gUtil.safeClick(mobNoTextfield, 2, driver);
			try {
				WebElement mobileNoElement = driver.findElement(AppiumBy.xpath("//android.widget.EditText"));
				if (mobileNoElement.isDisplayed()) {
					mobileNoElement.sendKeys("7975433720");
					ExtentReportManager.logPass("Mobile number entered", driver, false);
				}
			} catch (Exception e) {

				String xpath = "//android.widget.EditText";

				boolean found = GenericUtility.swipeAndFindElementByDirection(driver, xpath, scrollLayout, "up", 3);

				WebElement mobileNoElement = driver.findElement(AppiumBy.xpath(xpath));
				if (!found) {
					Assert.fail("Textfield  not found after swiping.");
				} else {
					mobileNoElement.sendKeys("7975433720");
					ExtentReportManager.logPass("Mobile number entered", driver, false);

				}
			}

		} catch (Exception e) {
			System.out.println("exception handled");
		}
	}

}
