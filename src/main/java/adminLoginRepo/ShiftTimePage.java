package adminLoginRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ShiftTimePage {

	public AppiumDriver driver;

	public ShiftTimePage(AppiumDriver driver) {
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

	public WebElement getSelectTimeText() {
		return selectTimeText;
	}

	public WebElement getCancelButton() {
		return cancelButton;
	}

	public WebElement getOkButton() {
		return okButton;
	}

	public WebElement getSwitchTimeIcon() {
		return switchTimeIcon;
	}

	public WebElement getHrsTextfield() {
		return hrsTextfield;
	}

	public WebElement getMinTextfield() {
		return minTextfield;
	}

	public WebElement getAmRadioButton() {
		return amRadioButton;
	}

	public WebElement getPmRadioButton() {
		return pmRadioButton;
	}

	public void shiftTimeUIValidation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(selectTimeText));

		Assert.assertEquals(true, selectTimeText.isDisplayed());
		Assert.assertEquals(true, switchTimeIcon.isDisplayed());
		Assert.assertEquals(true, cancelButton.isDisplayed());
		Assert.assertEquals(true, okButton.isDisplayed());

	}

	public void selectShiftTime(String time) {
		String[] parts = time.split("[: ]");
		String hrs = parts[0];
		String mins = parts[1];
		String meridian = parts[2];
		switchTimeIcon.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(hrsTextfield));

		Assert.assertEquals(true, hrsTextfield.isDisplayed());
		Assert.assertEquals(true, minTextfield.isDisplayed());
		Assert.assertEquals(true, amRadioButton.isDisplayed());
		Assert.assertEquals(true, pmRadioButton.isDisplayed());
		hrsTextfield.click();
		hrsTextfield.clear();
		hrsTextfield.sendKeys(hrs);

		minTextfield.click();
		minTextfield.clear();
		minTextfield.sendKeys(mins);
		if (meridian.equalsIgnoreCase("am")) {
			amRadioButton.click();
		} else {
			pmRadioButton.click();
		}

		okButton.click();
	}
}
