package supervisorReassignRepo;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.ExtentReportManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FacilitySearchPage {
	public AppiumDriver driver;

	public FacilitySearchPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement searchTextfield;

	public WebElement getSearchTextfield() {
		return searchTextfield;
	}
	
	public void searchFacilityByName(String facilityName)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(searchTextfield));
		searchTextfield.click();
		searchTextfield.clear();
		searchTextfield.sendKeys(facilityName);
		
		WebElement facility=driver.findElement(AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'"+facilityName+"')]"));
		Assert.assertTrue(facility.isDisplayed(),"NO data found");
		wait.until(ExpectedConditions.elementToBeClickable(facility)).click();
		ExtentReportManager.logPass("Searched Facility is visible"+facilityName, driver, false);
	}
}
