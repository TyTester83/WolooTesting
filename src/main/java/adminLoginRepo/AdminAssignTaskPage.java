package adminLoginRepo;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.GenericUtility;
import Utilities.SoftAssertValidationUtility;
import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AdminAssignTaskPage {

	public AppiumDriver driver;

	public AdminAssignTaskPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Assign\")]/parent::android.view.View")
	private WebElement assignTaskScreen;

	@AndroidFindBy(accessibility = "Assign\r\n" + "Tasks")
	private WebElement assignTaskText;

	@AndroidFindBy(accessibility = "Select Cleaning Tasks *")
	private WebElement cleaningTaskDropdown;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Done\"]")
	private WebElement taskDropdownlayout;

	@AndroidFindBy(accessibility = "Estimated Task Completion Time")
	private WebElement estimationText;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Shift Time\")]") 
	private WebElement shiftTimeLayout;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Schedule First Task\")]")
	private WebElement addTimingsLayout;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"The shift shall start at\")]")
	private WebElement shiftStartText;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Shift shall complete at\")]")
	private WebElement shiftEndText;

	@AndroidFindBy(accessibility = "Next")
	private WebElement nextButton;

	@AndroidFindBy(accessibility = "Please select Tasks")
	private WebElement taskErrorMsgText;

	@AndroidFindBy(accessibility = "Please select shift Timing")
	private WebElement shiftTimeErrorMsgText;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Please add Timing for tasks\")]")
	private WebElement addTimingErrorMsgText;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Buddy Name\")]")
	private WebElement existingBuddyNameText;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Start Shift Time\")]")
	private WebElement existingBuddyShiftTimeText;

	@AndroidFindBy(accessibility = "Submit")
	private WebElement submitButton;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Schedule First Task\")]/android.view.View/android.view.View")
	private WebElement taskTimingLayout;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Done\"]/android.view.View/android.view.View")
	private WebElement taskScrollOptionLayout;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Estimated Task Completion Time\"]/following-sibling::android.view.View[2]")
	private WebElement taskEstimationTimeText;

	@AndroidFindBy(accessibility = "The shift shall run round the clock for 24 hours.")
	private WebElement hrsShiftText;

	public WebElement getAssignTaskScreen() {
		return assignTaskScreen;
	}

	public WebElement getAssignTaskText() {
		return assignTaskText;
	}

	public WebElement getCleaningTaskDropdown() {
		return cleaningTaskDropdown;
	}

	public WebElement getTaskDropdownlayout() {
		return taskDropdownlayout;
	}

	public WebElement getEstimationText() {
		return estimationText;
	}

	public WebElement getShiftTimeLayout() {
		return shiftTimeLayout;
	}

	public WebElement getAddTimingsLayout() {
		return addTimingsLayout;
	}

	public WebElement getShiftStartText() {
		return shiftStartText;
	}

	public WebElement getShiftEndText() {
		return shiftEndText;
	}

	public WebElement getNextButton() {
		return nextButton;
	}

	public WebElement getShiftTimeErrorMsgText() {
		return shiftTimeErrorMsgText;
	}

	public WebElement getAddTimingErrorMsgText() {
		return addTimingErrorMsgText;
	}

	public WebElement getExistingBuddyNameText() {
		return existingBuddyNameText;
	}

	public WebElement getExistingBuddyShiftTimeText() {
		return existingBuddyShiftTimeText;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getTaskTimingLayout() {
		return taskTimingLayout;
	}

	public WebElement getTaskErrorMsgText() {
		return taskErrorMsgText;
	}

	public WebElement getTaskScrollOptionLayout() {
		return taskScrollOptionLayout;
	}

	public WebElement getTaskEstimationTimeText() {
		return taskEstimationTimeText;
	}

	public WebElement getHrsShiftText() {
		return hrsShiftText;
	}

	// Validate main UI elements
	public void assignTaskUIValidation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(cleaningTaskDropdown));

		ExtentReportManager.logInfo("Validating Assign Task screen elements");
		SoftAssertValidationUtility.verifyElementVisible(cleaningTaskDropdown,
				"Cleaning Task dropdown should be visible");
//		ExtentReportManager.logInfo("Verified cleaning task dropdown visibility");
		SoftAssertValidationUtility.verifyElementVisible(estimationText, "Estimation text should be visible");
//		ExtentReportManager.logInfo("Verified estimation text visibility");
		SoftAssertValidationUtility.verifyElementVisible(shiftTimeLayout, "Shift time layout should be visible");
		SoftAssertValidationUtility.verifyElementVisible(addTimingsLayout, "Add timings layout should be visible");
		SoftAssertValidationUtility.verifyElementVisible(hrsShiftText, "24 hrs text should be visible");
//		ExtentReportManager.logInfo("Verified shift/time UI elements");
		//	     SoftAssertValidationUtility.verifyElementVisible(shiftStartText, "Shift start text should be visible");
		//	     SoftAssertValidationUtility.verifyElementVisible(shiftEndText, "Shift end text should be visible");
		SoftAssertValidationUtility.verifyElementVisible(nextButton, "Next button should be visible");
		ExtentReportManager.logPass("Assign Task screen validation completed", driver, false);

	}

	// Validate empty-field error messages
	public void taskScreenEmptyfieldValidation() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(cleaningTaskDropdown));

		ExtentReportManager.logInfo("Validating empty-field error messages on task screen");

		wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click(); // click Next without input
		GenericUtility.tapUsingCoordinatePercentage(driver, addTimingsLayout, 75.0, 50.0); // trigger validation
		Thread.sleep(1000);

		Assert.assertTrue(taskErrorMsgText.isDisplayed(), "Task error message should be displayed");
//		Assert.assertTrue(shiftTimeErrorMsgText.isDisplayed(), "Shift time error message should be displayed");
		Assert.assertTrue(addTimingErrorMsgText.isDisplayed(), "Add timing error message should be displayed");
		ExtentReportManager.logPass("Empty-field validation shown as expected", driver, false);
	}

	// Assign a single task for a buddy with one timing
	public void assignTaskforTaskbuddy(String facilityName, String facilityType, String taskName,
			String expectedTotalTime, String startTaskTime, String endTaskTime) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		ExtentReportManager.logInfo("Assigning task '" + taskName + "' to buddy at facility '" + facilityName + "'");

		// Select task and verify estimate
		scrollAndSelectTask(taskName);
		wait.until(ExpectedConditions.visibilityOf(taskEstimationTimeText));
		Assert.assertTrue(taskEstimationTimeText.getAttribute("content-desc").contains(expectedTotalTime),
				"Estimated time should match expected value");
		ExtentReportManager.logPass("Verified estimated time: " + expectedTotalTime);

		// Add one timing
		addTiming(startTaskTime);

		String taskTime1 = GenericUtility.convertToUnpaddedTime(startTaskTime);
		String taskTime2 = GenericUtility.convertToUnpaddedTime(endTaskTime);
		taskTimingValidation(facilityName, facilityType, taskName, taskTime1, taskTime2);
		ExtentReportManager.logPass("Task timing validated: " + taskTime1 + " - " + taskTime2);

		new GenericUtility().scrollElement(driver, shiftTimeLayout, "up");
		// pre-check submit visible
		wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
		// verify navigation to congrats
		ExtentReportManager.logPass("Submitted assigned task ", driver, false);
		new CongratulationPopup(driver).congratsUIvalidation();
	}

	// Change an existing shift timing
	public void changeShiftTiming(String facilityName, String facilityType, String taskName, String expectedTotalTime)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		ExtentReportManager.logInfo("Changing shift timing for task '" + taskName + "' at facility '" + facilityName + "'");

		// Delete old timing
		String oldHrs = "11", oldMins = "50", oldMer = "pm";
		String oldTime = oldHrs + ":" + oldMins + " " + oldMer.toUpperCase();

		WebElement oldTaskTime = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[contains(@content-desc,'"
				+ facilityName + "') and contains(@content-desc,'" + oldTime + "')]/parent::android.view.View"));
		wait.until(ExpectedConditions.visibilityOf(oldTaskTime));

		GenericUtility.tapUsingCoordinatePercentage(driver, oldTaskTime, 83.6, 50.0);
		ExtentReportManager.logInfo("Deleted old timing: " + oldTime);
		new TaskDeleteConfirmPopup(driver).tapYesButton();

		// Select new shift start
		GenericUtility.tapUsingCoordinatePercentage(driver, shiftTimeLayout, 78.8, 50.0);
		ShiftTimePage timePage = new ShiftTimePage(driver);
		timePage.shiftTimeUIValidation();
		String shifttime = "02:40 PM";
		timePage.selectShiftTime(shifttime);
		Assert.assertTrue(shiftStartText.getAttribute("content-desc").contains("2:30 PM"),
				"Shift start time should be updated");
		ExtentReportManager.logInfo("Shift time updated to: " + shifttime);

		// Re-select task and verify estimate
		scrollAndSelectTask(taskName);
		wait.until(ExpectedConditions.visibilityOf(taskEstimationTimeText));
		Assert.assertTrue(taskEstimationTimeText.getAttribute("content-desc").contains(expectedTotalTime),
				"Estimated time should match");

		// Re-add timing
		addTiming(oldTime);
//		taskTimingValidation(facilityName, facilityType, taskName, oldTime);

		new GenericUtility().scrollElement(driver, shiftTimeLayout, "up");
		submitButton.click();
		new CongratulationPopup(driver).congratsUIvalidation();
	}

	// Assign multiple timings for SAME task
	public void assignMultipleTaskTime(String facilityName, String facilityType, String taskName,
			String expectedTotalTime, List<String> timings, List<String> endTaskTimings, String taskBuddyType)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		ExtentReportManager.logInfo("Assigning multiple timings for task '" + taskName + "' (" + timings.size() + " slots)");
		scrollAndSelectTask(taskName);
		wait.until(ExpectedConditions.visibilityOf(taskEstimationTimeText));

		Assert.assertTrue(taskEstimationTimeText.getAttribute("content-desc").contains(expectedTotalTime),
				"Estimated time should match expected total");
		ExtentReportManager.logPass("Verified estimated time: " + expectedTotalTime);

		// Ensure both lists are of the same size
		if (timings.size() != endTaskTimings.size()) {
			ExtentReportManager.logFail("Start and end time lists are not equal length", driver);
			throw new IllegalArgumentException("Start and end time lists must be of equal length!");
		}

		for (int i = 0; i < timings.size(); i++) {
			String taskTime1 = GenericUtility.convertToUnpaddedTime(timings.get(i));
			String taskTime2 = GenericUtility.convertToUnpaddedTime(endTaskTimings.get(i));
			ExtentReportManager.logInfo("Adding timing slot " + (i + 1) + ": " + taskTime1 + " - " + taskTime2);

			addTiming(timings.get(i));

			if (taskBuddyType.equalsIgnoreCase("Existing")) {
				taskTimingValidation(facilityName, facilityType, taskName, taskTime1, taskTime2);
			} else {
				System.out.println("Task Slot " + (i + 1) + " => Start: " + taskTime1 + ", End: " + taskTime2);
				taskTimingValidation_New(taskName, taskTime1, taskTime2);
			}
			ExtentReportManager.logPass("Timing slot " + (i + 1) + " validated", driver, false);
		}

		new GenericUtility().scrollElement(driver, shiftTimeLayout, "up");

		if (taskBuddyType.equalsIgnoreCase("Existing")) {
			submitButton.click();
			ExtentReportManager.logPass("All multiple timings submitted for existing buddy", driver, false);
			new CongratulationPopup(driver).congratsUIvalidation();
		} else {
			nextButton.click();
			ExtentReportManager.logPass("All multiple timings completed, proceeded to next", driver, false);
		}

	}

	// Assign MULTIPLE tasks (combined estimate)
	public void assignMultipleTask(List<String> taskList, String taskTime) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		ExtentReportManager.logInfo("Assigning multiple tasks (" + taskList.size() + " tasks) with combined timing");

		cleaningTaskDropdown.click();
		wait.until(ExpectedConditions.visibilityOf(taskScrollOptionLayout));

		int taskCount = 0;
		for (String taskName : taskList) {
			String xpath = "//android.view.View[contains(@content-desc,'" + taskName + "')]/android.widget.CheckBox";
			if (GenericUtility.swipeAndFindElementByDirection(driver, xpath, taskScrollOptionLayout, "up", 3)) {
				WebElement taskOption = driver.findElement(AppiumBy.xpath(xpath));
				wait.until(ExpectedConditions.visibilityOf(taskOption));
				taskOption.click();
				ExtentReportManager.logInfo("Selected task: " + taskName);
				taskCount++;
			}
		}
		GenericUtility.tapUsingCoordinatePercentage(driver, taskDropdownlayout, 50.0, 93.0);

//		String expectedEstimate = String.valueOf(taskCount * 15);
//		Assert.assertTrue(taskEstimationTimeText.getAttribute("content-desc").contains(expectedEstimate),
//				"Combined task estimate should match");
//		ExtentReportManager.logInfo("Verified combined estimate: " + expectedEstimate + " minutes for " + taskCount + " tasks");

		// Add ONE timing for all tasks

		addTiming(taskTime);
		new GenericUtility().scrollElement(driver, shiftTimeLayout, "up");
		submitButton.click();
		ExtentReportManager.logPass("Multiple tasks assigned and submitted with combined timing", driver, false);
		new CongratulationPopup(driver).congratsUIvalidation();
	}

	// Assign MULTIPLE tasks with DIFFERENT timings
	public void assignMultipleTaskDifferentTiming(String facilityName, String facilityType,
			Map<String, List<String>> taskTimeMap, String taskBuddyType) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		ExtentReportManager.logInfo("Assigning multiple tasks with different timings (" + taskTimeMap.size() + " tasks)");
		int totaltask = 1;
		int count = taskTimeMap.size();

		for (Map.Entry<String, List<String>> entry : taskTimeMap.entrySet()) {

			String taskName = entry.getKey();
			List<String> timeList = entry.getValue();

			// Defensive check
			if (timeList == null || timeList.size() < 2) {
				ExtentReportManager.logFail("Task " + taskName + " has missing timing data", driver);
				System.out.println("Skipping task " + taskName + " → Missing start/end time data");
				continue;
			}
			ExtentReportManager.logInfo("Processing task " + (totaltask) + "/" + count + ": " + taskName);

			String taskTime1 = GenericUtility.convertToUnpaddedTime(timeList.get(0));
			String taskTime2 = GenericUtility.convertToUnpaddedTime(timeList.get(1));
//		        String startTime = timeList.get(0);
//		        String endTime = timeList.get(1);

			GenericUtility gUtil = new GenericUtility();
			gUtil.safeClick(cleaningTaskDropdown, 2,driver);
//			wait.until(ExpectedConditions.visibilityOf(cleaningTaskDropdown));
//			wait.until(ExpectedConditions.elementToBeClickable(cleaningTaskDropdown)).click();
			wait.until(ExpectedConditions.visibilityOf(taskScrollOptionLayout));

			String taskXpath = "//android.view.View[contains(@content-desc,'" + taskName
					+ "')]/android.widget.CheckBox";

			if (GenericUtility.swipeAndFindElementByDirection(driver, taskXpath, taskScrollOptionLayout, "up", 3)) {
				WebElement taskOption = driver.findElement(AppiumBy.xpath(taskXpath));
				wait.until(ExpectedConditions.elementToBeClickable(taskOption)).click();
			}

			GenericUtility.tapUsingCoordinatePercentage(driver, taskDropdownlayout, 50.0, 93.0);

//		        Assert.assertTrue(taskEstimationTimeText.getAttribute("content-desc").contains(expectedEstimate),
//		                "Estimated time should match for " + taskName);

			// Add start time
			addTiming(timeList.get(0));
			if (taskBuddyType.equalsIgnoreCase("Existing")) {
				taskTimingValidation(facilityName, facilityType, taskName, taskTime1, taskTime2);
			}

			 else {
				System.out.println("Task Slot  => Start: " + taskTime1 + ", End: " + taskTime2);
				taskTimingValidation_New(taskName, taskTime1, taskTime2);
			}
			ExtentReportManager.logPass("Task timing validated: " + taskTime1 + " - " + taskTime2, driver, false);

			// Uncheck after validation
			if (totaltask != count) {
				ExtentReportManager.logInfo("Unchecking task " + taskName + " for next iteration");
				uncheckTaskOption(taskName);
				totaltask++;
			}
		}

		new GenericUtility().scrollElement(driver, shiftTimeLayout, "up");

		if (taskBuddyType.equalsIgnoreCase("Existing")) {
			submitButton.click();
			ExtentReportManager.logPass("All multiple tasks with different timings submitted for existing buddy", driver, false);
			new CongratulationPopup(driver).congratsUIvalidation();
		} else {
			nextButton.click();
			ExtentReportManager.logPass("All multiple tasks with different timings completed, proceeded to next", driver, false);
		}
	}

// validate timing presence
	public void taskTimingValidation(String facilityName, String facilityType, String taskName, String startTaskTime,
			String endTaskTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		ExtentReportManager.logInfo("Validating task timing: " + startTaskTime + " - " + endTaskTime + " for " + taskName);
		wait.until(ExpectedConditions.visibilityOf(taskTimingLayout));

		String xpath = "//android.widget.ImageView[contains(@content-desc,'" + facilityName
				+ "') and contains(@content-desc,'" + startTaskTime + "') and contains(@content-desc,'" + endTaskTime
				+ "')]";
		if (GenericUtility.swipeAndFindElementByDirection(driver, xpath, taskTimingLayout, "up", 3)) {
			WebElement expfacilityEle = driver.findElement(AppiumBy.xpath(xpath));
			Assert.assertEquals(true, expfacilityEle.isDisplayed());

			expfacilityEle.click();
			String xpath1 = xpath + "/parent::android.view.View[contains(@content-desc,'" + taskName + "')]";

			if (GenericUtility.swipeAndFindElementByDirection(driver, xpath1, taskTimingLayout, "up", 3)) {
				WebElement actualTaskEle = driver.findElement(AppiumBy.xpath(xpath1));
				Assert.assertTrue(actualTaskEle.isDisplayed(),
						"Task element is not visible after selecting facility block: " + taskName);
				expfacilityEle.click();
			} else {
				ExtentReportManager.logFail("Task element not found for: " + taskName + " at time: " + startTaskTime, driver);
				Assert.fail("Task element not found for: " + taskName + " at time: " + startTaskTime);
			}
		} else {
			ExtentReportManager.logFail("Failed to locate facility-time element via swipe: " + facilityName + " - " + startTaskTime, driver);
			Assert.fail("Failed to locate facility-time element via swipe: " + facilityName + " - " + startTaskTime);
		}
		ExtentReportManager.logPass("Task timing validation passed for " + taskName, driver, false);
	}

	// open drop-down, scroll & select task
	public void scrollAndSelectTask(String taskName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		ExtentReportManager.logInfo("Opening cleaning task dropdown to select task: " + taskName);
		wait.until(ExpectedConditions.visibilityOf(cleaningTaskDropdown));
		GenericUtility gUtil=new GenericUtility();
		gUtil.safeClick(cleaningTaskDropdown, 2, driver);
//		wait.until(ExpectedConditions.elementToBeClickable(cleaningTaskDropdown)).click();
		wait.until(ExpectedConditions.visibilityOf(taskScrollOptionLayout));

		String taskXpath = "//android.view.View[contains(@content-desc,'" + taskName + "')]/android.widget.CheckBox";
		if (GenericUtility.swipeAndFindElementByDirection(driver, taskXpath, taskScrollOptionLayout, "up", 3)) {
			WebElement taskOption = driver.findElement(AppiumBy.xpath(taskXpath));
			wait.until(ExpectedConditions.visibilityOf(taskOption));
			taskOption.click();
			ExtentReportManager.logPass("Selected task option: " + taskName);
		} else {
			ExtentReportManager.logFail("Task option not found: " + taskName, driver);
			throw new IllegalStateException("Task option not found: " + taskName);
		}
		GenericUtility.tapUsingCoordinatePercentage(driver, taskDropdownlayout, 50.0, 93.0);
		ExtentReportManager.logPass("Closed task dropdown after selection");
	}

	// common timing adding routine
	public void addTiming(String time) throws InterruptedException {
		Thread.sleep(1000);
		ExtentReportManager.logInfo("Adding timing: " + time);
		String[] parts = time.split("[: ]");
		String hrs = parts[0];
		String mins = parts[1];
		String meridian = parts[2];

		String xpath = "//android.view.View[contains(@content-desc,\"Schedule First Task *\")]/android.view.View";
		List<WebElement> elements = driver.findElements(AppiumBy.xpath(xpath));
		if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
			GenericUtility.tapUsingCoordinatePercentage(driver, addTimingsLayout, 75.0, 10.0);
		} else {
			GenericUtility.tapUsingCoordinatePercentage(driver, addTimingsLayout, 75.0, 50.0);
		}

		ScheduleTaskTimingsPage timingPage = new ScheduleTaskTimingsPage(driver);
		timingPage.scheduleTaskUIValidation();

		timingPage.scrollAndFindHrsTiming(hrs);
		timingPage.scrollAndFindMinsTiming(mins);
		timingPage.scrollAndFindMeridinTiming(meridian.toLowerCase());
		timingPage.getAddButton().click();
		new TaskTimingConfirmPopup(driver).confirmPopupUIValidation();
		ExtentReportManager.logPass("Added Task timing: " + time, driver, false);
	}

	// unCheck the task options
	public void uncheckTaskOption(String taskName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		ExtentReportManager.logInfo("Unchecking task option: " + taskName);
		driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'" + taskName + "')]")).click();
		wait.until(ExpectedConditions.visibilityOf(taskScrollOptionLayout));

		String options1 = "//android.view.View[contains(@content-desc,'" + taskName + "')]/android.widget.CheckBox";

		if (GenericUtility.swipeAndFindElementByDirection(driver, options1, taskScrollOptionLayout, "up", 3)) {
			WebElement taskoptions = driver.findElement(AppiumBy.xpath(options1));
			wait.until(ExpectedConditions.visibilityOf(taskoptions));
			taskoptions.click();
		}
		GenericUtility.tapUsingCoordinatePercentage(driver, taskDropdownlayout, 50.0, 93.0);
		ExtentReportManager.logPass("Task unchecked: " + taskName, driver, false);
	}

	// existing task buddy valiadtion
	public void validateExistingTaskbuddy(String buddyName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		ExtentReportManager.logInfo("Validating existing task buddy: " + buddyName);
		wait.until(ExpectedConditions.visibilityOf(existingBuddyNameText));
		Assert.assertTrue(existingBuddyNameText.getAttribute("content-desc").contains(buddyName),
				"Expected buddy name not found: " + buddyName);
		ExtentReportManager.logPass("Buddy validation passed: " + buddyName, driver, false);
	}

	public void assignTaskforNewTaskbuddy(String facilityName, String facilityType, String taskName,
			String expectedTotalTime, String shiftTime, String startTaskTime, String endTaskTime)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		ExtentReportManager.logInfo("Assigning task '" + taskName + "' to new buddy from " + startTaskTime + " to " + endTaskTime);

		// Select task and verify estimate
		scrollAndSelectTask(taskName);
		wait.until(ExpectedConditions.visibilityOf(taskEstimationTimeText));
		Assert.assertTrue(taskEstimationTimeText.getAttribute("content-desc").contains(expectedTotalTime),
				"Estimated time should match expected value");

		Thread.sleep(1000);
		// add shift time
//		GenericUtility.tapUsingCoordinatePercentage(driver, shiftTimeLayout, 78.8, 50.0);
//
//		ShiftTimePage timePage = new ShiftTimePage(driver);
//		timePage.shiftTimeUIValidation();
//		timePage.selectShiftTime(shiftTime);

		// Add one timing
		addTiming(startTaskTime);
		String taskTime1 = GenericUtility.convertToUnpaddedTime(startTaskTime);
		String taskTime2 = GenericUtility.convertToUnpaddedTime(endTaskTime);
//		String xpath = "//android.widget.ImageView[contains(@content-desc,'" + taskTime1+ "') and contains(@content-desc,'" + taskTime2 + "')]";
//		isAssignTaskVisible(facilityName, xpath, taskTime1, taskTime2);
		taskTimingValidation_New(taskName, taskTime1, taskTime2);

//		new GenericUtility().scrollElement(driver, shiftTimeLayout, "up");
//		wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
		
		scrollAndTapNextButton();

	}

	public void isAssignTaskVisible(String facilityName, String xpath, String startTaskTime, String endTaskTiming) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		ExtentReportManager.logInfo("Checking if task visible: " + facilityName + " at " + startTaskTime);
		wait.until(ExpectedConditions.visibilityOf(taskTimingLayout));

		try {
			WebElement oldTaskTime = wait.until(ExpectedConditions
					.visibilityOfElementLocated(AppiumBy.xpath(xpath + "/parent::android.view.View")));
			Assert.assertTrue(oldTaskTime.isDisplayed(), "Task element is not displayed directly");
		} catch (Exception e) {
			WebElement taskTimingElementLayout = driver.findElement(AppiumBy.xpath(
					"//android.view.View[contains(@content-desc,\"Schedule First Task *\")]/android.view.View/android.view.View"));
			boolean isFound = GenericUtility.swipeAndFindElementByDirection(driver, xpath, taskTimingElementLayout,
					"up", 3);
			if (isFound) {
				WebElement expFacilityEle = driver.findElement(AppiumBy.xpath(xpath));
				Assert.assertTrue(expFacilityEle.isDisplayed(), "Task found after swipe but not visible");
				ExtentReportManager.logPass("Task found after swipe and is visible", driver, false);
			} else {
				ExtentReportManager.logFail("Task not found even after swiping for facility: " + facilityName + " and time: " + startTaskTime + endTaskTiming, driver);
				Assert.fail("Task not found even after swiping for facility: " + facilityName + " and time: "
						+ startTaskTime + endTaskTiming);
			}
		}
	}

	public void deleteTaskTimings(String facilityName, String startTaskTime, String endTaskTiming) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		ExtentReportManager.logInfo("Deleting task timing: " + startTaskTime + " - " + endTaskTiming + " for " + facilityName);
		wait.until(ExpectedConditions.visibilityOf(taskTimingLayout));
		String xpath = "//android.widget.ImageView[contains(@content-desc,'" + facilityName
				+ "') and contains(@content-desc,'" + startTaskTime + "') and contains(@content-desc,'" + endTaskTiming
				+ "')]";
		isAssignTaskVisible(facilityName, xpath, startTaskTime, endTaskTiming);
		WebElement expFacilityEle = driver
				.findElement(AppiumBy.xpath("//android.widget.ImageView[contains(@content-desc,'" + facilityName
						+ "') and contains(@content-desc,'" + startTaskTime + "') and contains(@content-desc,'"
						+ endTaskTiming + "')]/parent::android.view.View"));
		GenericUtility.tapUsingCoordinatePercentage(driver, expFacilityEle, 83.6, 50.0);
		new TaskDeleteConfirmPopup(driver).tapYesButton();
		ExtentReportManager.logPass("Task timing Deleted successfully: " + startTaskTime + " - " + endTaskTiming + " for " + facilityName);
	}

	public void taskTimingValidation_New(String taskName, String startTaskTime, String endTaskTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		ExtentReportManager.logInfo("Validating task timing (new): " + startTaskTime + " - " + endTaskTime + " for " + taskName);
		wait.until(ExpectedConditions.visibilityOf(taskTimingLayout));

		String xpath = "//android.widget.ImageView[contains(@content-desc,'" + startTaskTime
				+ "') and contains(@content-desc,'" + endTaskTime + "')]";
		if (GenericUtility.swipeAndFindElementByDirection(driver, xpath, taskTimingLayout, "up", 3)) {
			WebElement expfacilityEle = driver.findElement(AppiumBy.xpath(xpath));
//			Assert.assertEquals(true, expfacilityEle.isDisplayed());

			expfacilityEle.click();
			String xpath1 = xpath + "/parent::android.view.View[contains(@content-desc,'" + taskName + "')]";

			if (GenericUtility.swipeAndFindElementByDirection(driver, xpath1, taskTimingLayout, "up", 3)) {
				WebElement actualTaskEle = driver.findElement(AppiumBy.xpath(xpath1));
				Assert.assertTrue(actualTaskEle.isDisplayed(),
						"Task element is not visible after selecting facility block: " + taskName);
				expfacilityEle.click();
			} else {
				ExtentReportManager.logFail("Task element not found for: " + taskName + " at time: " + startTaskTime, driver);
				Assert.fail("Task element not found for: " + taskName + " at time: " + startTaskTime);
			}
		} else {
			ExtentReportManager.logFail("Failed to locate facility-time element via swipe: " + startTaskTime, driver);
			Assert.fail("Failed to locate facility-time element via swipe: " + startTaskTime);
		}
		ExtentReportManager.logPass("New task timing validation passed for " + taskName, driver, false);
	}

	public void deleteTaskTimingsForNewTaskbuddy(String facilityName, String startTaskTime, String endTaskTiming) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		ExtentReportManager.logInfo("Deleting task timing for new buddy: " + startTaskTime + " - " + endTaskTiming);
		wait.until(ExpectedConditions.visibilityOf(taskTimingLayout));
		String xpath = "//android.widget.ImageView[contains(@content-desc,'" + startTaskTime
				+ "') and contains(@content-desc,'" + endTaskTiming + "')]";
		isAssignTaskVisible(facilityName, xpath, startTaskTime, endTaskTiming);
		WebElement expFacilityEle = driver
				.findElement(AppiumBy.xpath("//android.widget.ImageView[contains(@content-desc,'" + startTaskTime
						+ "') and contains(@content-desc,'" + endTaskTiming + "')]/parent::android.view.View"));
		GenericUtility.tapUsingCoordinatePercentage(driver, expFacilityEle, 83.6, 50.0);
		new TaskDeleteConfirmPopup(driver).tapYesButton();
		ExtentReportManager.logPass("Task timing Deleted successfully: " + startTaskTime + " - " + endTaskTiming + " for " + facilityName);

	}

	public void assignTaskDeletionForNewTaskbuddy(String facilityName, String facilityType, String taskName,
			String expectedTotalTime, String taskBuddyType, String startTaskTime, String endTaskTime)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		ExtentReportManager.logInfo("Assigning task with deletion flow for " + taskBuddyType + " buddy: " + taskName);

		// Select task and verify estimate
		scrollAndSelectTask(taskName);
		wait.until(ExpectedConditions.visibilityOf(taskEstimationTimeText));
		Assert.assertTrue(taskEstimationTimeText.getAttribute("content-desc").contains(expectedTotalTime),
				"Estimated time should match expected value");

		// add shift time
		Thread.sleep(1000);
//		GenericUtility.tapUsingCoordinatePercentage(driver, shiftTimeLayout, 78.8, 50.0);
//
//		ShiftTimePage timePage = new ShiftTimePage(driver);
//		timePage.shiftTimeUIValidation();
//		timePage.selectShiftTime(shiftTime);

		// Add one timing
		addTiming(startTaskTime);
		String taskTime1 = GenericUtility.convertToUnpaddedTime(startTaskTime);
		String taskTime2 = GenericUtility.convertToUnpaddedTime(endTaskTime);
		Thread.sleep(1000);
		ExtentReportManager.logInfo("Added initial timing: " + taskTime1 + " - " + taskTime2);

		if (taskBuddyType.equalsIgnoreCase("Existing")) {
			// delete task
			deleteTaskTimings(facilityName, taskTime1, taskTime2);
			Thread.sleep(1000);
			ExtentReportManager.logInfo("Deleted existing timing, now adding new timing");

			// Add one timing
			addTiming(startTaskTime);
			taskTimingValidation(facilityName, facilityType, taskName, taskTime1, taskTime2);
			new GenericUtility().scrollElement(driver, shiftTimeLayout, "up");
			wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
			ExtentReportManager.logPass("Task deletion and re-assignment completed for existing buddy", driver, false);
			new CongratulationPopup(driver).congratsUIvalidation();
		}

		else {
			// delete task
			deleteTaskTimingsForNewTaskbuddy(facilityName, taskTime1, taskTime2);
			Thread.sleep(1000);
			ExtentReportManager.logInfo("Deleted new buddy timing, now adding new timing");

			// Add one timing
			addTiming(startTaskTime);
			taskTimingValidation_New(taskName, taskTime1, taskTime2);
			new GenericUtility().scrollElement(driver, shiftTimeLayout, "up");
			wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
			ExtentReportManager.logPass("Task deletion and re-assignment completed for new buddy", driver, false);
		}
	}

	public void assignTaskDeletionAndTaskAssignForTaskbuddy(String facilityName, String facilityType, String taskName,
			String expectedTotalTime, String startTaskTime, String endTaskTime) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		ExtentReportManager.logInfo("Executing task deletion and new assignment for: " + taskName);

		// Delete task time
		String taskTime1 = GenericUtility.convertToUnpaddedTime(startTaskTime);
		String taskTime2 = GenericUtility.convertToUnpaddedTime(endTaskTime);
		Thread.sleep(1000);
		ExtentReportManager.logInfo("Timing to delete/reassign: " + taskTime1 + " - " + taskTime2);

		// Add task
		scrollAndSelectTask(taskName);
		wait.until(ExpectedConditions.visibilityOf(taskEstimationTimeText));
		Assert.assertTrue(taskEstimationTimeText.getAttribute("content-desc").contains(expectedTotalTime),
				"Estimated time should match expected value");

		addTiming(startTaskTime);
		taskTimingValidation(facilityName, facilityType, taskName, taskTime1, taskTime2);
		ExtentReportManager.logPass("Task timing re-validated");

		new GenericUtility().scrollElement(driver, shiftTimeLayout, "up");
		wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
		ExtentReportManager.logPass("Task deletion and re-assignment completed successfully", driver, false);
		new CongratulationPopup(driver).congratsUIvalidation();
	}
	
	public void scrollAndTapNextButton()
	{
//		gUtil.scrollElement(driver, common.taskPage().getShiftTimeLayout(), "up");

		GenericUtility gUtil = new GenericUtility();
		try {
			// Try to directly find the facility element first
			WebElement nextElement = driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Next\"]"));

			if (nextElement.isDisplayed()) {
//				wait.until(ExpectedConditions.visibilityOf(nextElement)).click();
//				Thread.sleep(1000);
				gUtil.safeClick(nextElement, 1, driver);
				ExtentReportManager.logPass("Next button tapped");
			}

		} catch (Exception e) {
			String nextElementXpath = "//android.view.View[@content-desc=\"Next\"]";
			boolean found = GenericUtility.swipeAndFindElementByDirection(driver, nextElementXpath, assignTaskScreen,
					"up", 3);
			if (!found) {
				Assert.fail("Element not found after swiping.");
			} else {

				WebElement nextElement = driver
						.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Next\"]"));
				gUtil.safeClick(nextElement, 1, driver);
				ExtentReportManager.logPass("Next button tapped");
			}
		}
	}
}
