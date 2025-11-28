package supervisorDashboardRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Utilities.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ClusterPage extends BasePage {

	public ClusterPage(AppiumDriver driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Cluster\"]")
	private WebElement clusterText;
	
	@AndroidFindBy(xpath = "//android.widget.Button")
	private WebElement searchIconIMage;

	public WebElement getClusterText() {
		return clusterText;
	}

	public WebElement getSearchIconIMage() {
		return searchIconIMage;
	}
	
	public void clusterValidation(String adminMobNo)
	{
		wait.until(ExpectedConditions.visibilityOf(clusterText));
		WebElement cluster=driver.findElement(AppiumBy.xpath("//android.view.View[contains(@content-desc,'"+adminMobNo+"')]"));
		Assert.assertTrue(cluster.isDisplayed(), "Cluster should be visible");
		
		wait.until(ExpectedConditions.elementToBeClickable(cluster)).click();
	}
}
