package razorpayPaymentRepo;

import org.openqa.selenium.WebElement;

import Utilities.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CardsPage extends BasePage {

	public CardsPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Card\"]")
	private WebElement cardText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Add a new card\"]")
	private WebElement newcardText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Add a new card\"]/following-sibling::android.view.View/descendant::android.widget.EditText[1]")
	private WebElement cardNoTextfield;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Add a new card\"]/following-sibling::android.view.View/descendant::android.widget.EditText[2]")
	private WebElement monthExpiryTextfield;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Add a new card\"]/following-sibling::android.view.View/descendant::android.widget.EditText[3]")
	private WebElement cvvTextfield;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Save this card as per RBI guidelines\"]")
	private WebElement saveCardCheckbox;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id=\"bottom-cta\"]/android.view.View")
	private WebElement priceText;

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"Continue\"]")
	private WebElement continueButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"View Details\"]")
	private WebElement viewDetailsButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please enter a valid card number\"]")
	private WebElement cardErrorMsgText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please enter a valid expiry date\"]")
	private WebElement cardExpiryErrorMsgText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please enter a 3 digit cvv\"]")
	private WebElement cardCVVErrorMsgText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Add a new card\"]/following-sibling::android.view.View/descendant::android.widget.EditText[4]")
	private WebElement cardNameTextfield;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Please enter name on your card\"]")
	private WebElement nameErrorMsgText;

	public WebElement getCardText() {
		return cardText;
	}

	public WebElement getNewcardText() {
		return newcardText;
	}

	public WebElement getCardNoTextfield() {
		return cardNoTextfield;
	}

	public WebElement getMonthExpiryTextfield() {
		return monthExpiryTextfield;
	}

	public WebElement getCvvTextfield() {
		return cvvTextfield;
	}

	public WebElement getSaveCardCheckbox() {
		return saveCardCheckbox;
	}

	public WebElement getPriceText() {
		return priceText;
	}

	public WebElement getContinueButton() {
		return continueButton;
	}

	public WebElement getViewDetailsButton() {
		return viewDetailsButton;
	}

	public WebElement getCardErrorMsgText() {
		return cardErrorMsgText;
	}

	public WebElement getCardExpiryErrorMsgText() {
		return cardExpiryErrorMsgText;
	}

	public WebElement getCardCVVErrorMsgText() {
		return cardCVVErrorMsgText;
	}

	public WebElement getCardNameTextfield() {
		return cardNameTextfield;
	}

	public WebElement getNameErrorMsgText() {
		return nameErrorMsgText;
	}

}
