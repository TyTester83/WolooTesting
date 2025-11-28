package supervisorReassignRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExtentReportManager;
import Utilities.GenericUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TaskAssignConfirmPopup {

	public AppiumDriver driver;

	public TaskAssignConfirmPopup(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"Task Assign Successfully to janitor\")]")
	private WebElement confirmLayout;
	
	public void tapDoneButton() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(confirmLayout));
		Thread.sleep(1000);
		GenericUtility.tapUsingCoordinatePercentage(driver, confirmLayout, 50.0, 79.0);
		ExtentReportManager.logPass("Tapped Done button", driver, false);
	}

}
