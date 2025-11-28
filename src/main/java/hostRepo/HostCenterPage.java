package hostRepo;

import org.openqa.selenium.WebElement;

import Utilities.BasePage;
import Utilities.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HostCenterPage extends BasePage {

	public HostCenterPage(AppiumDriver driver) {
		super(driver);
	}
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[1]")
	private WebElement wahImage;
	
	@AndroidFindBy(accessibility = "750-850")
	private WebElement scoreText;

	@AndroidFindBy(accessibility = "Total No. of Walk-in's")
	private WebElement walkinsText;
	
	@AndroidFindBy(accessibility = "last 1 hr")
	private WebElement oneHrText;
	
	@AndroidFindBy(accessibility = "last 3 hr")
	private WebElement threeHrText;
	
	@AndroidFindBy(accessibility = "last 6 hr")
	private WebElement sixHrText;
	
	@AndroidFindBy(xpath = "//android.view.View[@index=6]")
	private WebElement oneHrCountText;
	
	@AndroidFindBy(accessibility = "//android.view.View[@index=8]")
	private WebElement threeHrCountText;
	
	@AndroidFindBy(accessibility = "//android.view.View[@index=10]")
	private WebElement sixHrCountText;
	
	@AndroidFindBy(accessibility = "//android.view.View[@index=7]")
	private WebElement oneHrPercentText;
	
	@AndroidFindBy(accessibility = "//android.view.View[@index=9]")
	private WebElement threeHrPercentText;
	
	@AndroidFindBy(accessibility = "//android.view.View[@index=11]")
	private WebElement sixHrPercentText;
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Woloo Points\")][1]")
	private WebElement pointsText;
	
	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.ImageView[2]")
	private WebElement coinsImage;
	
	@AndroidFindBy(accessibility = "You have Woloo Points that are ready to be redeemed")
	private WebElement redeemContentText;
	
	@AndroidFindBy(accessibility = "Redeem Now")
	private WebElement redeemButton;
	
	@AndroidFindBy(accessibility = "Host Center")
	private WebElement hostCenterTab;
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Hello\")]")
	private WebElement hostNameText;
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Renew it Now\")]")
	private WebElement renewLink;

	public WebElement getWahImage() {
		return wahImage;
	}

	public WebElement getScoreText() {
		return scoreText;
	}

	public WebElement getWalkinsText() {
		return walkinsText;
	}

	public WebElement getOneHrText() {
		return oneHrText;
	}

	public WebElement getThreeHrText() {
		return threeHrText;
	}

	public WebElement getSixHrText() {
		return sixHrText;
	}

	public WebElement getOneHrCountText() {
		return oneHrCountText;
	}

	public WebElement getThreeHrCountText() {
		return threeHrCountText;
	}

	public WebElement getSixHrCountText() {
		return sixHrCountText;
	}

	public WebElement getOneHrPercentText() {
		return oneHrPercentText;
	}

	public WebElement getThreeHrPercentText() {
		return threeHrPercentText;
	}

	public WebElement getSixHrPercentText() {
		return sixHrPercentText;
	}

	public WebElement getPointsText() {
		return pointsText;
	}

	public WebElement getCoinsImage() {
		return coinsImage;
	}

	public WebElement getRedeemContentText() {
		return redeemContentText;
	}

	public WebElement getRedeemButton() {
		return redeemButton;
	}
	
	public WebElement getHostCenterTab() {
		return hostCenterTab;
	}

	public WebElement getHostNameText() {
		return hostNameText;
	}

	public WebElement getRenewLink() {
		return renewLink;
	}
}
