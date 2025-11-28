package razorpayPaymentRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utilities.BasePage;
import Utilities.SoftAssertValidationUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RazorpayPage extends BasePage {

	public RazorpayPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"Go back\"]")
	private WebElement backButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Woloo\"]")
	private WebElement wolooText;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id=\"merchant-identity\"]/android.view.View")
	private WebElement merchantLoginText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Payment Options\"]")
	private WebElement paymentOptText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"All Payment Options\"]")
	private WebElement allPaymentText;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text=\"UPI googlepay phonepe popclubapp paytm\"]")
	private WebElement upiButton;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text=\"UPI googlepay phonepe popclubapp paytm\"]")
	private WebElement cardsButton;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text=\"EMI snapmint zestmoney\"]")
	private WebElement emiButton;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text=\"Netbanking ICIC KKBK UTIB BARB_R\"]")
	private WebElement netBankButton;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text=\"Wallet amazonpay phonepe mobikwik airtelmoney\"]")
	private WebElement walletButton;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text=\"Pay Later lazypay icic epaylater kkbk\"]")
	private WebElement payLaterButton;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id=\"bottom-cta\"]/android.view.View")
	private WebElement priceText;

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"View Details\"]")
	private WebElement viewDetailsButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"Continue\"]")
	private WebElement continueButton;

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,\"Using as\")]")
	private WebElement mobileNoChangeButton;
	
	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement mobNoTextfield;

	public WebElement getBackButton() {
		return backButton;
	}

	public WebElement getWolooText() {
		return wolooText;
	}

	public WebElement getMerchantLoginText() {
		return merchantLoginText;
	}

	public WebElement getPaymentOptText() {
		return paymentOptText;
	}

	public WebElement getAllPaymentText() {
		return allPaymentText;
	}

	public WebElement getUpiButton() {
		return upiButton;
	}

	public WebElement getCardsButton() {
		return cardsButton;
	}

	public WebElement getEmiButton() {
		return emiButton;
	}

	public WebElement getNetBankButton() {
		return netBankButton;
	}

	public WebElement getWalletButton() {
		return walletButton;
	}

	public WebElement getPayLaterButton() {
		return payLaterButton;
	}

	public WebElement getPriceText() {
		return priceText;
	}

	public WebElement getViewDetailsButton() {
		return viewDetailsButton;
	}

	public WebElement getContinueButton() {
		return continueButton;
	}

	public WebElement getMobileNoChangeButton() {
		return mobileNoChangeButton;
	}

	public WebElement getMobNoTextfield() {
		return mobNoTextfield;
	}

	public void paymentUIValidation()
	{
		try
		{
			if(mobNoTextfield.isDisplayed())
			{
				ContactDetailsPage razorContactPage=new ContactDetailsPage(driver);
				razorContactPage.validateContactDetails();
			}
		}catch (Exception e) {
			System.out.println("There is no mobileNumber textfield");
		}
		
		wait.until(ExpectedConditions.visibilityOf(wolooText));
//		SoftAssertValidationUtility.verifyElementVisible(backButton," elemnt is not displayed");
//		SoftAssertValidationUtility.verifyElementVisible(merchantLoginText," elemnt is not displayed");
//		SoftAssertValidationUtility.verifyElementVisible(upiButton," elemnt is not displayed");
//		SoftAssertValidationUtility.verifyElementVisible(cardsButton," elemnt is not displayed");
//		SoftAssertValidationUtility.verifyElementVisible(emiButton," elemnt is not displayed");
		SoftAssertValidationUtility.verifyElementVisible(netBankButton," elemnt is not displayed");
//		SoftAssertValidationUtility.verifyElementVisible(walletButton," elemnt is not displayed");
//		SoftAssertValidationUtility.verifyElementVisible(payLaterButton," elemnt is not displayed");
//		SoftAssertValidationUtility.verifyElementVisible(priceText," elemnt is not displayed");
//		SoftAssertValidationUtility.verifyElementVisible(continueButton," elemnt is not displayed");
	
		
	}
	
}
