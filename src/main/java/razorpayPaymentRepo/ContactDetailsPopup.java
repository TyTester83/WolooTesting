package razorpayPaymentRepo;

import org.openqa.selenium.WebElement;

import Utilities.BasePage;
import Utilities.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ContactDetailsPopup extends BasePage {

	public ContactDetailsPopup(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Edit contact details\"]")
	private WebElement contactDetailText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Enter mobile number to continue\"]")
	private WebElement enterMobNoText;

	@AndroidFindBy(className = "android.widget.EditText")
	private WebElement mobileNoTextfield;

	@AndroidFindBy(xpath = "(//android.widget.Button[@text=\"Continue\"])[2]")
	private WebElement continueButton;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id=\"overlay-backdrop\"]/android.view.View/android.widget.Button")
	private WebElement closeButton;

	public WebElement getContactDetailText() {
		return contactDetailText;
	}

	public WebElement getEnterMobNoText() {
		return enterMobNoText;
	}

	public WebElement getMobileNoTextfield() {
		return mobileNoTextfield;
	}

	public WebElement getContinueButton() {
		return continueButton;
	}

	public WebElement getCloseButton() {
		return closeButton;
	}

}
