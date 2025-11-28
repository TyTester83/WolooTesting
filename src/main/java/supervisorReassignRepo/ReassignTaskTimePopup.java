package supervisorReassignRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ReassignTaskTimePopup {
	public AppiumDriver driver;

	public ReassignTaskTimePopup(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "Select Time")
	private WebElement selectTimeText;

	@AndroidFindBy(accessibility = "Cancel")
	private WebElement cancelButton;

	@AndroidFindBy(accessibility = "OK")
	private WebElement okButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Cancel\"]/preceding-sibling::android.widget.Button")
	private WebElement switchTimeIcon;

	@AndroidFindBy(xpath = "//android.widget.EditText[1]")
	private WebElement hrsTextfield;

	@AndroidFindBy(xpath = "//android.widget.EditText[2]")
	private WebElement minTextfield;

	@AndroidFindBy(accessibility = "AM")
	private WebElement amRadioButton;

	@AndroidFindBy(accessibility = "PM")
	private WebElement pmRadioButton;

	

	public void taskTimeUIValidation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(selectTimeText));

		Assert.assertEquals(true, selectTimeText.isDisplayed());
		Assert.assertEquals(true, switchTimeIcon.isDisplayed());
		Assert.assertEquals(true, cancelButton.isDisplayed());
		Assert.assertEquals(true, okButton.isDisplayed());
		ExtentReportManager.logPass("Task Time UI validated", driver, false);

	}

	public void selectTaskTime(String taskTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(switchTimeIcon));
		wait.until(ExpectedConditions.elementToBeClickable(switchTimeIcon)).click();
		
		wait.until(ExpectedConditions.visibilityOf(hrsTextfield));

		String []parts=taskTime.split(" ");
		String merdian=parts[1];
		String []time=parts[0].split(":");
		String hrs=time[0];
		String mins=time[1];
		hrsTextfield.click();
		hrsTextfield.clear();
		hrsTextfield.sendKeys(hrs);

		minTextfield.click();
		minTextfield.clear();
		minTextfield.sendKeys(mins);
		if (merdian.equalsIgnoreCase("am")) {
			amRadioButton.click();
		} else {
			pmRadioButton.click();
		}

		wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
		ExtentReportManager.logPass("Task Time Added", driver, false);
	}

	
}
