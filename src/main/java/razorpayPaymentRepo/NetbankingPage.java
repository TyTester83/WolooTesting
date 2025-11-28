package razorpayPaymentRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NetbankingPage {
	public AppiumDriver driver;

	public NetbankingPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"ICICI ICICI\"]")
	private WebElement iciciButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"Kotak Kotak\"]")
	private WebElement kotakButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"Axis Axis\"]")
	private WebElement axisButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"BOB BOB\"]")
	private WebElement bobButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"Canara Bank Canara Bank\"]")
	private WebElement canaraButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"More Banks\"]")
	private WebElement moreButton;

	public WebElement getIciciButton() {
		return iciciButton;
	}

	public WebElement getKotakButton() {
		return kotakButton;
	}

	public WebElement getAxisButton() {
		return axisButton;
	}

	public WebElement getBobButton() {
		return bobButton;
	}

	public WebElement getCanaraButton() {
		return canaraButton;
	}

	public WebElement getMoreButton() {
		return moreButton;
	}

	public void tapPaymentBank(String optionBank)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(iciciButton));
		
		if(optionBank.equalsIgnoreCase("ICICI"))
		{
			iciciButton.click();
		}
		else if(optionBank.equalsIgnoreCase("Kotak"))
		{
			kotakButton.click();
		}
		else if(optionBank.equalsIgnoreCase("axis"))
		{
			axisButton.click();
		}
		else
		{
			driver.findElement(AppiumBy.xpath("//android.widget.Button[contains(@text,'"+optionBank+"')]")).click();
		}
		ExtentReportManager.logPass("Payment Bank Selected"+optionBank);
	}
}
