package adminLoginRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.GenericUtility;
import Utilities.SoftAssertValidationUtility;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ExplorePage {

	public AppiumDriver driver;

	public ExplorePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@index=0]")
	private WebElement taskmasterLogo;

	@AndroidFindBy(accessibility = "TASQ")
	private WebElement taskMasterText1;

	@AndroidFindBy(accessibility = "MASTER")
	private WebElement taskMasterText2;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Monitor your facility’s hygiene\")]")
	private WebElement tqmContentText1;

	@AndroidFindBy(accessibility = "Woloo's Smart Hygiene Technology Service.")
	private WebElement tqmContentText2;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Explore\"])[1]") // (xpath =
																							// "//android.view.View[@content-desc=\"B2B\"]/preceding-sibling::android.widget.ImageView[@content-desc=\"Explore\"]")
	private WebElement tqmExploreButton;

	@AndroidFindBy(accessibility = "B2B")
	private WebElement b2bText1;

	@AndroidFindBy(accessibility = "Store")
	private WebElement b2bText2;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Buy trusted products\")]")
	private WebElement b2bContentText1;

	@AndroidFindBy(accessibility = "through Woloo and collect rewards!.")
	private WebElement b2bContentText2;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Explore\"])[2]") // "//android.view.View[@content-desc=\"B2B\"]/following-sibling::android.widget.ImageView[@content-desc=\"Explore\"]")
	private WebElement b2bExploreButton;

	public WebElement getTaskmasterLogo() {
		return taskmasterLogo;
	}

	public WebElement getTaskMasterText1() {
		return taskMasterText1;
	}

	public WebElement getTaskMasterText2() {
		return taskMasterText2;
	}

	public WebElement getTqmContentText1() {
		return tqmContentText1;
	}

	public WebElement getTqmContentText2() {
		return tqmContentText2;
	}

	public WebElement getTqmExploreButton() {
		return tqmExploreButton;
	}

	public WebElement getB2bText1() {
		return b2bText1;
	}

	public WebElement getB2bText2() {
		return b2bText2;
	}

	public WebElement getB2bContentText1() {
		return b2bContentText1;
	}

	public WebElement getB2bContentText2() {
		return b2bContentText2;
	}

	public WebElement getB2bExploreButton() {
		return b2bExploreButton;
	}

	public void exploreUIvalidation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				AppiumBy.xpath("(//android.widget.ImageView[@content-desc=\"Explore\"])[1]")));

		ExtentReportManager.logInfo("Validating Explore screen UI elements");

		SoftAssertValidationUtility.verifyElementVisible(taskMasterText1, "TaskMaster title text 1 should be visible.");
		ExtentReportManager.logInfo("TaskMaster title text 1 is visible");

		SoftAssertValidationUtility.verifyElementVisible(taskMasterText2, "TaskMaster title text 2 should be visible.");
		ExtentReportManager.logInfo("TaskMaster title text 2 is visible");

		SoftAssertValidationUtility.verifyElementVisible(tqmContentText1, "TQM content text 1 should be visible.");
		ExtentReportManager.logInfo("TQM content text 1 is visible");

//    SoftAssertValidationUtility.verifyElementVisible(tqmContentText2, "TQM content text 2 should be visible."); removed in latest b2b store

		SoftAssertValidationUtility.verifyElementVisible(tqmExploreButton,
				"TaskMaster Explore button should be visible.");
		ExtentReportManager.logInfo("TaskMaster Explore button is visible");

		// B2B Dashboard
//    SoftAssertValidationUtility.verifyElementVisible(b2bText1, "B2B text 1 should be visible."); Changed the name as Store in new build

		SoftAssertValidationUtility.verifyElementVisible(b2bText2, "B2B text 2 should be visible.");
		ExtentReportManager.logInfo("B2B text 2 is visible");

		SoftAssertValidationUtility.verifyElementVisible(b2bContentText1, "B2B content text 1 should be visible.");
		ExtentReportManager.logInfo("B2B content text 1 is visible");

		SoftAssertValidationUtility.verifyElementVisible(b2bExploreButton, "B2B Explore button should be visible.");
		ExtentReportManager.logInfo("B2B Explore button is visible");

		SoftAssertValidationUtility.verifyElementVisible(tqmContentText1, "TQM content text 1 should be visible.");
		ExtentReportManager.logInfo("TQM content text 1 is visible");

//    SoftAssertValidationUtility.verifyElementVisible(tqmContentText2, "TQM content text 2 should be visible.");	   
		SoftAssertValidationUtility.verifyElementVisible(tqmExploreButton,
				"TaskMaster Explore button should be visible.");
		ExtentReportManager.logPass("TaskMaster Explore button is visible", driver, false);

	}

	public void tapTaskMasterExploreButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // click TOOm effort

		wait.until(ExpectedConditions.visibilityOf(taskmasterLogo));
		GenericUtility gUtil = new GenericUtility();
		ExtentReportManager.logInfo("Tapping TaskMaster Explore button");
		try {
			if (!gUtil.isElementVisible(tqmExploreButton, driver)) {
				ExtentReportManager.logFail("TaskMaster Explore button is not visible — cannot tap", driver);
				throw new IllegalStateException("TaskMaster Explore button not visible");
			}
			gUtil.safeClick(tqmExploreButton, 2, driver);
			ExtentReportManager.logPass("Tapped TaskMaster Explore button", driver, false);
		} catch (Exception e) {
			ExtentReportManager.logError("Failed to tap TaskMaster Explore button", e, driver);
			throw e;
		}
		
	}
}
