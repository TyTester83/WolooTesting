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

public class ClusterSearchPage {

	public AppiumDriver driver;

	public ClusterSearchPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement searchTextfield;

	public WebElement getSearchTextfield() {
		return searchTextfield;
	}
	public void searchClusterByMobileNo(String clusterNumber)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(searchTextfield));
		searchTextfield.click();
		searchTextfield.clear();
		searchTextfield.sendKeys(clusterNumber);
		
		WebElement cluster=driver.findElement(AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'"+clusterNumber+"')]"));
		Assert.assertTrue(cluster.isDisplayed(),"NO data found");
		wait.until(ExpectedConditions.elementToBeClickable(cluster)).click();
		ExtentReportManager.logPass("Searched Cluster is visible"+clusterNumber, driver, false);
		
	}
	
}
