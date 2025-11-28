package adminLoginRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.GenericUtility;
import Utilities.SoftAssertValidationUtility;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class GetStartedPage {

	public AppiumDriver driver;

	public GetStartedPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "Welcome to")
	private WebElement welcomeText;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@index=1]")
	private WebElement taskmasterLogo;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@index=2]")
	private WebElement serviceImage;

	@AndroidFindBy(accessibility = "You’re there! Just a few more steps to get you started with your Smart Hygiene Journey.")
	private WebElement quotesText;

	@AndroidFindBy(accessibility = "Get Started")
	private WebElement getStartButton;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"The Task Master service of Woloo Smart Hygiene is a paid service. You are eligible for a free trial\")]")
	private WebElement freeTrialText;

	public WebElement getWelcomeText() {
		return welcomeText;
	}

	public WebElement getTaskmasterLogo() {
		return taskmasterLogo;
	}

	public WebElement getServiceImage() {
		return serviceImage;
	}

	public WebElement getQuotesText() {
		return quotesText;
	}

	public WebElement getGetStartButton() {
		return getStartButton;
	}

	public WebElement getFreeTrialText() {
		return freeTrialText;
	}

	public void getStartUIValidation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(welcomeText));

		ExtentReportManager.logInfo("Validating Get Started screen UI elements");

		SoftAssertValidationUtility.verifyElementVisible(welcomeText, "Text is not displayed");
//		ExtentReportManager.logInfo("Welcome text is visible");

		SoftAssertValidationUtility.verifyElementVisible(taskmasterLogo, "Logo is not displayed");
//		ExtentReportManager.logInfo("Taskmaster logo is visible");

		SoftAssertValidationUtility.verifyElementVisible(serviceImage, "service Images are not displayed ");
//		ExtentReportManager.logInfo("Service image is visible");

		SoftAssertValidationUtility.verifyElementVisible(quotesText, "guotText is not displayed");
//		ExtentReportManager.logInfo("Quotes text is visible");

		SoftAssertValidationUtility.verifyElementVisible(getStartButton, "StartButton is not displayed");
//		ExtentReportManager.logInfo("Get Started button is visible");

		SoftAssertValidationUtility.verifyElementVisible(freeTrialText, "Trial Text is not displayed");
//		ExtentReportManager.logInfo("Free trial text is visible");
		ExtentReportManager.logPass("Get Started screen UI elements validated", driver, false);

	}

	public void tapGetStartedButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(welcomeText));
		GenericUtility gUtil = new GenericUtility();
		ExtentReportManager.logInfo("Tapping Get Started button");
		try {
			if (!gUtil.isElementVisible(getStartButton, driver)) {
				ExtentReportManager.logFail("Get Started button is not visible — cannot tap", driver);
				throw new IllegalStateException("Get Started button not visible");
			}
			gUtil.safeClick(getStartButton, 2, driver);
			ExtentReportManager.logPass("Tapped Get Started button", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("Failed to tap Get Started button", e, driver);
			throw e;
		}
	}
}
