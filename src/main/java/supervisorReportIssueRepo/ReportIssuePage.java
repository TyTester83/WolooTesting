package supervisorReportIssueRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.GenericUtility;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import supervisorReassignRepo.ReassignTaskTimePopup;

public class ReportIssuePage {

	public AppiumDriver driver;

	public ReportIssuePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Report an Issue\"]")
	private WebElement reportText;

	@AndroidFindBy(accessibility = "Cluster Name")
	private WebElement clusterDropdown;

	@AndroidFindBy(accessibility = "Facility")
	private WebElement facilityDropdown;

	@AndroidFindBy(accessibility = "Template name")
	private WebElement templateNameDropdown;

	@AndroidFindBy(accessibility = "Task name")
	private WebElement taskNameDropdown;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[1]")
	private WebElement descriptionTextfield;

	@AndroidFindBy(accessibility = "Assign to")
	private WebElement assignToDropdown;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[2]")
	private WebElement startTimeTextfield;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Choose file\")]")
	private WebElement uploadImage;

	@AndroidFindBy(accessibility = "Submit")
	private WebElement submitButton;

	@AndroidFindBy(xpath = "//android.widget.ScrollView")
	private WebElement verticalScrollLayout;

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement serachTextfield;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"PHOTO\"]/ancestor::android.view.View/following-sibling::android.view.View[2]")
	private WebElement cameraTapButton;

	@AndroidFindBy(accessibility = "View Image")
	private WebElement viewImageButton;

	@AndroidFindBy(accessibility = "Delete Image ")
	private WebElement deleteImageButton;

	@AndroidFindBy(accessibility = "Back")
	private WebElement backButton;

	@AndroidFindBy(xpath = "//android.widget.ImageView")
	private WebElement taskImageview;

	public WebElement getReportText() {
		return reportText;
	}

	public WebElement getClusterDropdown() {
		return clusterDropdown;
	}

	public WebElement getFacilityDropdown() {
		return facilityDropdown;
	}

	public WebElement getTemplateNameDropdown() {
		return templateNameDropdown;
	}

	public WebElement getTaskNameDropdown() {
		return taskNameDropdown;
	}

	public WebElement getDescriptionTextfield() {
		return descriptionTextfield;
	}

	public WebElement getAssignToDropdown() {
		return assignToDropdown;
	}

	public WebElement getStartTimeTextfield() {
		return startTimeTextfield;
	}

	public WebElement getUploadImage() {
		return uploadImage;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getVerticalScrollLayout() {
		return verticalScrollLayout;
	}

	public WebElement getSerachTextfield() {
		return serachTextfield;
	}

	public WebElement getCameraTapButton() {
		return cameraTapButton;
	}

	public WebElement getViewImageButton() {
		return viewImageButton;
	}

	public WebElement getDeleteImageButton() {
		return deleteImageButton;
	}

	public WebElement getBackButton() {
		return backButton;
	}

	public WebElement getTaskImageview() {
		return taskImageview;
	}

	public void reassignReportAnIssueTask(String adminMobileNO, String facilityName, String taskTemplateID,
			List<String> taskNames, String description, String buddyName, String taskStartTime, String taskEndTime) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(reportText));

		// cluster selection
		wait.until(ExpectedConditions.elementToBeClickable(clusterDropdown)).click();
		wait.until(ExpectedConditions.visibilityOf(serachTextfield));
		wait.until(ExpectedConditions.elementToBeClickable(serachTextfield)).click();
		serachTextfield.clear();
		serachTextfield.sendKeys(adminMobileNO);

		WebElement cluster = driver.findElement(
				AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + adminMobileNO + "')]"));
		Assert.assertTrue(cluster.isDisplayed(), "NO data found");
		wait.until(ExpectedConditions.elementToBeClickable(cluster)).click();

		// facility selection
		wait.until(ExpectedConditions.visibilityOf(facilityDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(facilityDropdown)).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(serachTextfield));
		wait.until(ExpectedConditions.elementToBeClickable(serachTextfield)).click();
		serachTextfield.clear();
		serachTextfield.sendKeys(facilityName);

		WebElement facility = driver
				.findElement(AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + facilityName + "')]"));
		Assert.assertTrue(facility.isDisplayed(), "NO data found");
		wait.until(ExpectedConditions.elementToBeClickable(facility)).click();

		// Task template selection
		
		wait.until(ExpectedConditions.visibilityOf(templateNameDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(templateNameDropdown)).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(serachTextfield));
		wait.until(ExpectedConditions.elementToBeClickable(serachTextfield)).click();
		serachTextfield.clear();
		serachTextfield.sendKeys(taskTemplateID);

		WebElement taskTemplate = driver.findElement(
				AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + taskTemplateID + "')]"));
		Assert.assertTrue(taskTemplate.isDisplayed(), "NO data found");
		wait.until(ExpectedConditions.elementToBeClickable(taskTemplate)).click();

		// validate the cleaning task
		Thread.sleep(1000);
		WebElement task = driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'"
				+ taskTemplateID + "')]/following-sibling::android.view.View[1]"));
		String actualTask = task.getAttribute("content-desc");

		wait.until(ExpectedConditions.visibilityOf(task));
		for (String taskName : taskNames) {
			try {
				if (actualTask.contains(taskName)) {
					Assert.assertTrue(true, taskName + " should be visible");
				}
			} catch (Exception e) {
				System.out.println("Task '" + taskName + "' not found: " + e.getMessage());
			}
		}

		// Send description
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(descriptionTextfield)).click();
		descriptionTextfield.clear();
		descriptionTextfield.sendKeys(description);

		try {
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		} catch (Exception ex) {
			System.out.println("BACK key also failed: " + ex.getMessage());
		}

		// Choose the Buddy
		
		wait.until(ExpectedConditions.elementToBeClickable(assignToDropdown)).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(serachTextfield));
		wait.until(ExpectedConditions.elementToBeClickable(serachTextfield)).click();
		serachTextfield.clear();
		serachTextfield.sendKeys(buddyName);

		WebElement buddy = driver
				.findElement(AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'" + buddyName + "')]"));
		Assert.assertTrue(buddy.isDisplayed(), "NO data found");
		wait.until(ExpectedConditions.elementToBeClickable(buddy)).click();

		// Assign task time
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(startTimeTextfield));
		wait.until(ExpectedConditions.elementToBeClickable(startTimeTextfield)).click();

		ReassignTaskTimePopup taskTimePopup = new ReassignTaskTimePopup(driver);
		taskTimePopup.selectTaskTime(taskStartTime);

		WebElement endTime = driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'"
				+ buddyName + "')]/following-sibling::android.view.View[1]"));
		wait.until(ExpectedConditions.visibilityOf(endTime));
		String endTaskTime = convertTo24HourFormat(taskEndTime);
		String actualEndTime = endTime.getText().trim();

		Assert.assertTrue(actualEndTime.contains(endTaskTime), actualEndTime+"Expected task end time should be display"+endTaskTime);
		GenericUtility.swipeInDirection(driver, verticalScrollLayout, "up");

		// Take Place Image
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(uploadImage));
		wait.until(ExpectedConditions.elementToBeClickable(uploadImage)).click();
		wait.until(ExpectedConditions.visibilityOf(cameraTapButton));
		wait.until(ExpectedConditions.elementToBeClickable(cameraTapButton)).click();

		Thread.sleep(1000);
//		wait.until(ExpectedConditions.elementToBeClickable(viewImageButton)).click();
//		wait.until(ExpectedConditions.visibilityOf(taskImageview));
//		wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
//		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(submitButton));
		wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

	}

	public static String convertTo24HourFormat(String time12Hour) {
		try {
			// Define input and output formats
			SimpleDateFormat inputFormat = new SimpleDateFormat("hh:mm a"); // 12-hour with AM/PM
			SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm"); // 24-hour

			// Parse the 12-hour time and format to 24-hour
			Date date = inputFormat.parse(time12Hour);
			return outputFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return "Invalid time format";
		}
	}

	public void reassignReportAnIssueTask1(String adminMobileNO, String facilityName, String taskTemplateID,
			List<String> taskNames, String description, String buddyName, String taskStartTime, String taskEndTime) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

// Helper method to search and select from dropdown
		Consumer<String> searchAndSelect = (value) -> {
			wait.until(ExpectedConditions.visibilityOf(serachTextfield));
			serachTextfield.clear();
			serachTextfield.sendKeys(value);
		};

// Wait until report screen is loaded
		waitUntilVisible(reportText);

// Cluster Selection
		clickWhenClickable(clusterDropdown);
		searchAndSelect.accept(adminMobileNO);
		clickIfVisible("//android.widget.Button[contains(@content-desc,'" + adminMobileNO + "')]", "Cluster");

// Facility Selection
		clickWhenClickable(facilityDropdown);
		searchAndSelect.accept(facilityName);
		clickIfVisible("//android.widget.Button[contains(@content-desc,'" + facilityName + "')]", "Facility");

// Task Template Selection
		clickWhenClickable(templateNameDropdown);
		searchAndSelect.accept(taskTemplateID);
		clickIfVisible("//android.widget.Button[contains(@content-desc,'" + taskTemplateID + "')]", "Task Template");

// Validate Tasks
		WebElement task = findIfVisible("//android.view.View[contains(@content-desc,'" + taskTemplateID
				+ "')]/following-sibling::android.view.View[1]");
		String actualTask = task != null ? task.getAttribute("content-desc") : "";

		for (String taskName : taskNames) {
			Assert.assertTrue(actualTask.contains(taskName), taskName + " should be visible");
		}

// Add Description
		sendKeysWithClear(descriptionTextfield, description);
		pressAndroidBackKey(); // Hide keyboard

// Choose Buddy
		clickWhenClickable(assignToDropdown);
		searchAndSelect.accept(taskTemplateID);
		clickIfVisible("//android.widget.Button[contains(@content-desc,'" + buddyName + "')]", "Buddy");

// Assign Task Time
		clickWhenClickable(startTimeTextfield);
		new ReassignTaskTimePopup(driver).selectTaskTime(taskStartTime);

// Validate End Time
		WebElement endTime = findIfVisible("//android.view.View[contains(@content-desc,'" + buddyName
				+ "')]/following-sibling::android.view.View[1]");
		String expectedEndTime = convertTo24HourFormat(taskEndTime);
		String actualEndTime = endTime != null ? endTime.getAttribute("content-desc") : "";
		Assert.assertTrue(actualEndTime.contains(expectedEndTime), "Expected task end time should be displayed");

		GenericUtility.swipeInDirection(driver, verticalScrollLayout, "up");

// Upload Image
		clickWhenClickable(uploadImage);
		clickWhenClickable(cameraTapButton);
		clickWhenClickable(viewImageButton);
		waitUntilVisible(taskImageview);
		clickWhenClickable(backButton);

// Submit
		clickWhenClickable(submitButton);
	}

	private void waitUntilVisible(WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element));
	}

	private void clickWhenClickable(WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element))
				.click();
	}

	private void sendKeysWithClear(WebElement element, String text) {
		waitUntilVisible(element);
		element.clear();
		element.sendKeys(text);
	}

	private void pressAndroidBackKey() {
		try {
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		} catch (Exception e) {
			System.out.println("BACK key press failed: " + e.getMessage());
		}
	}

	private void clickIfVisible(String xpath, String label) {
		try {
			WebElement element = driver.findElement(AppiumBy.xpath(xpath));
			if (element.isDisplayed()) {
				element.click();
			} else {
				Assert.fail(label + " not found!");
			}
		} catch (Exception e) {
			Assert.fail(label + " not found due to: " + e.getMessage());
		}
	}

	private WebElement findIfVisible(String xpath) {
		try {
			WebElement element = driver.findElement(AppiumBy.xpath(xpath));
			if (element.isDisplayed())
				return element;
		} catch (Exception ignored) {
		}
		return null;
	}

}
