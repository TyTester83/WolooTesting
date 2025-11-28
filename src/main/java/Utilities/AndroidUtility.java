package Utilities;

import java.time.Duration;
import java.util.Arrays;
import java.util.Set;

import org.openqa.selenium.ScreenOrientation;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;

public class AndroidUtility implements MobileUtility {

	public AndroidDriver driver;

	public AndroidUtility(AppiumDriver driver) {
		this.driver = (AndroidDriver) driver;
	}

	@Override
	public void installApp(String path) {
		driver.installApp(path);
	}

	@Override
	public boolean isAppInstalled(String appPackage) {
		boolean result = driver.isAppInstalled(appPackage);
		System.out.println("Is App Installed: " + result);
		return result;
	}

	@Override
	public void deleteApp(String appPackage) {
		driver.removeApp(appPackage);
	}

	@Override
	public void activateApp(String packageName) {
		driver.activateApp(packageName);
	}

	@Override
	public void runInBackground(int time) {
		driver.runAppInBackground(Duration.ofSeconds(time));
	}

	@Override
	public void appStatus(String packageName) {
		ApplicationState status = driver.queryAppState(packageName);
		System.out.println("App Status: " + status.toString());
	}

	// Android-specific, included in common interface for simplicity
	public void currentActivity() {
		String activity = driver.currentActivity();
		System.out.println("Current Activity: " + activity);
	}

	@Override
	public void closeApp(String packageName) {
		try {
	        driver.terminateApp(packageName);   // graceful close
	    } catch (Exception e1) {
	        try {
	            driver.executeScript("mobile: shell",
	                ImmutableMap.of("command", "am", "args", Arrays.asList("force-stop", packageName)));
	        } catch (Exception e2) {
	            throw new RuntimeException("Unable to close app: " + packageName + " Error: " + e2.getMessage());
	        }
	    }
	}

	@Override
	public void notification() {
		driver.openNotifications();
	}

	@Override
	public void keyboard() {
		driver.hideKeyboard();
	}

	@Override
	public void data() {
		driver.toggleData();
	}

	@Override
	public void wifi() {
		driver.toggleWifi();
	}

	@Override
	public void airplaneMode() {
		driver.toggleAirplaneMode();
	}

	@Override
	public void portraitOrientation() {
		driver.rotate(ScreenOrientation.PORTRAIT);
	}

	@Override
	public void landscapeOrientation() {
		driver.rotate(ScreenOrientation.LANDSCAPE);
	}

	@Override
	public void setClipboard(String text) {
		driver.setClipboardText(text);
	}

	@Override
	public void getClipboard() {
		String txt = driver.getClipboardText();
		System.out.println("Clipboard Text: " + txt);
	}

	@Override
	public void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	@Override
	public void toast() {
		String msg = driver.findElement(AppiumBy.xpath("//android.widget.Toast[1]")).getText();
		System.out.println("Toast Message: " + msg);
	}

	@Override
	public void getContexts() {
		Set<String> contexts = driver.getContextHandles();
		System.out.println("Number of contexts: " + contexts.size());
		for (String ch : contexts) {
			System.out.println("Context: " + ch);
			if (ch.contains("WEBVIEW_")) {
				driver.context(ch);
				break;
			}
		}
	}
	@Override
	public void resetingapp()
	{
		driver.resetApp();
	}
	
	@Override
	public void closingapp()
	{
		driver.closeApp();
	}

	@Override
	public void launchingapp()
	{
		driver.launchApp();
	}
}
