package adminDashboardRepo;

import org.openqa.selenium.WebElement;

import Utilities.BasePage;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class EmptyDashboardPage extends BasePage {

	public EmptyDashboardPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(accessibility = "TASQ")
	private WebElement taskMasterText1;

	@AndroidFindBy(accessibility = "MASTER")
	private WebElement taskMasterText2;

	@AndroidFindBy(accessibility = "Monitor your facility’s hygiene with ")
	private WebElement tqmContentText1;

	@AndroidFindBy(accessibility = "Woloo's Smart Hygiene Technology Service.")
	private WebElement tqmContentText2;

	@AndroidFindBy(accessibility = "Explore")
	private WebElement tqmExploreButton;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Task Master is Woloo’s smart task automation\")]")
	private WebElement contentText1;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"It ensures that hygiene standards\")]")
	private WebElement contentText2;

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

	public WebElement getContentText1() {
		return contentText1;
	}

	public WebElement getContentText2() {
		return contentText2;
	}
	
	public void emptyDashboardUIValidation() {
		ExtentReportManager.logInfo("Validating Empty Dashboard UI elements");
		if (taskMasterText1.isDisplayed()) {
			ExtentReportManager.logPass("TASQ text is visible");
		} else {
			ExtentReportManager.logFail("TASQ text is not visible", driver);
		}
		if (taskMasterText2.isDisplayed()) {
			ExtentReportManager.logPass("MASTER text is visible");
		} else {
			ExtentReportManager.logFail("MASTER text is not visible", driver);
		}
		if (tqmContentText1.isDisplayed()) {
			ExtentReportManager.logPass("TQM content text 1 is visible");
		} else {
			ExtentReportManager.logFail("TQM content text 1 is not visible", driver);
		}
		if (tqmContentText2.isDisplayed()) {
			ExtentReportManager.logPass("TQM content text 2 is visible");
		} else {
			ExtentReportManager.logFail("TQM content text 2 is not visible", driver);
		}
		if (tqmExploreButton.isDisplayed()) {
			ExtentReportManager.logPass("Explore button is visible");
		} else {
			ExtentReportManager.logFail("Explore button is not visible", driver);
		}
		if (contentText1.isDisplayed()) {
			ExtentReportManager.logPass("Content text 1 is visible");
		} else {
			ExtentReportManager.logFail("Content text 1 is not visible", driver);
		}
		if (contentText2.isDisplayed()) {
			ExtentReportManager.logPass("Content text 2 is visible");
		} else {
			ExtentReportManager.logFail("Content text 2 is not visible", driver);
		}
	}
	
	public void clickExploreButton() {
		ExtentReportManager.logInfo("Clicking Explore button");
		gUtil.safeClick(tqmExploreButton, 2, driver);
		ExtentReportManager.logPass("Explore button clicked");
	}}
