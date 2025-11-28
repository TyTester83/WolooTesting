package razorpayPaymentRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class UPIPage {

	public AppiumDriver driver;

	public UPIPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"UPI\"]")
	private WebElement upiText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Pay with UPI ID / Number\"]")
	private WebElement paywithText;

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement upiTextfield;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id=\"bottom-cta\"]/android.view.View")
	private WebElement paymentText;

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"Continue\"]")
	private WebElement continueButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"View Details\"]")
	private WebElement viewDetailsButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please enter a UPI ID/ Number\"]")
	private WebElement errorMsgText;

	public WebElement getUpiText() {
		return upiText;
	}

	public WebElement getPaywithText() {
		return paywithText;
	}

	public WebElement getUpiTextfield() {
		return upiTextfield;
	}

	public WebElement getPaymentText() {
		return paymentText;
	}

	public WebElement getContinueButton() {
		return continueButton;
	}

	public WebElement getViewDetailsButton() {
		return viewDetailsButton;
	}

	public WebElement getErrorMsgText() {
		return errorMsgText;
	}

}
