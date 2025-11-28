package adminLoginRepo;

import java.time.Duration;
import java.util.NoSuchElementException;

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

public class ScheduleTaskTimingsPage {

	public AppiumDriver driver;

	public ScheduleTaskTimingsPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "Schedule Task *")
	private WebElement scheduleTaskText;

	@AndroidFindBy(accessibility = "Add")
	private WebElement addButton;

	@AndroidFindBy(accessibility = "Select Time when shift start ")
	private WebElement errorMsg;

	@AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"Add\"]/preceding-sibling::android.view.View)[1]//android.view.View[2]")
	private WebElement hrsText;

	@AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"Add\"]/preceding-sibling::android.view.View)[2]//android.view.View[2]")
	private WebElement minsText;

	@AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"Add\"]/preceding-sibling::android.view.View)[3]//android.view.View[2]")
	private WebElement meridinText;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Add\"]/preceding-sibling::android.view.View[1]/android.view.View")
	private WebElement hrsScrollLayout;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Add\"]/preceding-sibling::android.view.View[2]/android.view.View")
	private WebElement minsScrollLayout;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Add\"]/preceding-sibling::android.view.View[3]/android.view.View")
	private WebElement meridinScrollLayout;

	public WebElement getScheduleTaskText() {
		return scheduleTaskText;
	}

	public WebElement getAddButton() {
		return addButton;
	}

	public WebElement getErrorMsg() {
		return errorMsg;
	}

	public void scheduleTaskUIValidation() {
		ExtentReportManager.logInfo("Validating Schedule Task UI elements");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(scheduleTaskText));
//		ExtentReportManager.logInfo("Schedule Task text is visible");

		SoftAssertValidationUtility.verifyElementVisible(scheduleTaskText,scheduleTaskText+"Text should be visible");
//		ExtentReportManager.logInfo("Schedule Task text verified");
		SoftAssertValidationUtility.verifyElementVisible(addButton,addButton+"Button should be visible");
//		ExtentReportManager.logInfo("Add button is visible");
		ExtentReportManager.logPass("Schedule Task UI elements validated", driver, false);
		
	}

	public void scrollAndFindHrsTiming(String expectedHourStr) throws InterruptedException {
		ExtentReportManager.logInfo("Scrolling to find hour: " + expectedHourStr);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(addButton));

		GenericUtility gu = new GenericUtility(); // contains scrollElement()

		int expectedHour = Integer.parseInt(expectedHourStr);
		int maxAttempts = 12; // since there are only 12 values

		for (int i = 0; i < maxAttempts; i++) {
			try {
				WebElement hourElement = hrsText; // visible center hour element
				String currentHourStr = hourElement.getAttribute("content-desc").trim();
				int currentHour = Integer.parseInt(currentHourStr);

//				ExtentReportManager.logInfo("Current Hour: " + currentHour + " | Target: " + expectedHour);

				if (currentHour == expectedHour) {
					ExtentReportManager.logPass("Target hour found: " + expectedHour);
					break;
				}

				String direction = getScrollDirectionForHour(currentHour, expectedHour);
				gu.scrollElement(driver, hourElement, direction);
				Thread.sleep(500); // wait for UI to update

			} catch (Exception e) {
				Thread.sleep(500);
			}
		}
	}

	public void scrollAndFindMinsTiming(String expectMins) throws InterruptedException {
		ExtentReportManager.logInfo("Scrolling to find minute: " + expectMins);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(addButton));

		GenericUtility gu = new GenericUtility(); // Utility class with scrollElement()

		int expectedMinute = Integer.parseInt(expectMins);
		int maxAttempts = 60;

		for (int i = 0; i < maxAttempts; i++) {
			try {
				WebElement minsElement = minsText; // This is the wheel picker element for minutes
				String currentMinsStr = minsElement.getAttribute("content-desc").trim();
				int currentMinute = Integer.parseInt(currentMinsStr);

//				ExtentReportManager.logInfo("Current Minute: " + currentMinute + " | Target: " + expectedMinute);

				if (currentMinute == expectedMinute) {
					ExtentReportManager.logPass("Target minute found: " + expectedMinute);
					break;
				}

				String direction = getScrollDirectionForMinutes(currentMinute, expectedMinute);
				gu.scrollElement(driver, minsElement, direction);
				Thread.sleep(500); // wait for UI to update

			} catch (Exception e) {
				Thread.sleep(500); // in case of stale element or other minor issues
			}
		}
	}

	public void scrollAndFindMeridinTiming(String expectmerdian) {
		ExtentReportManager.logInfo("Scrolling to find meridian: " + expectmerdian);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(addButton));

		GenericUtility gu = new GenericUtility();

		try {
			wait.until(ExpectedConditions.visibilityOf(meridinText));
			String current = meridinText.getAttribute("content-desc").trim();
			ExtentReportManager.logInfo("Current meridian: " + current + " | Target: " + expectmerdian);
			if (current.equalsIgnoreCase("am") && expectmerdian.equalsIgnoreCase("pm")) {
				gu.scrollElement(driver, meridinText, "up");
				ExtentReportManager.logPass("Scrolled up to PM");
			} else if (current.equalsIgnoreCase("pm") && expectmerdian.equalsIgnoreCase("am")) {
				gu.scrollElement(driver, meridinText, "down");
				ExtentReportManager.logPass("Scrolled down to AM");
			} else {
				ExtentReportManager.logPass("Meridian already matching: " + current);
			}

		} catch (NoSuchElementException e) {
			ExtentReportManager.logFail("Meridian element not found after scroll attempt", driver);
		} catch (Exception e) {
			ExtentReportManager.logFail("Unexpected error occurred: " + e.getMessage(), driver);
		}

	}

	public String getScrollDirectionForHour(int currentHour, int targetHour) {
		currentHour = (currentHour == 0) ? 12 : currentHour;
		targetHour = (targetHour == 0) ? 12 : targetHour;

		int up = (targetHour - currentHour + 12) % 12;
		int down = (currentHour - targetHour + 12) % 12;

		return (up <= down) ? "up" : "down";
	}

	public String getScrollDirectionForMinutes(int current, int target) {
		int up = (target - current + 60) % 60;
		int down = (current - target + 60) % 60;

		return (up <= down) ? "up" : "down";
	}
}
