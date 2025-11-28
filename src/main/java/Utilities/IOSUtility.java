package Utilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.ScreenOrientation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.ios.IOSDriver;

public class IOSUtility implements MobileUtility {
	IOSDriver driver;

    public IOSUtility(AppiumDriver driver) {
        this.driver = (IOSDriver) driver;
    }

    @Override
    public void installApp(String path) {
        driver.installApp(path);
    }

    @Override
    public boolean isAppInstalled(String bundleId) {
        boolean result = driver.isAppInstalled(bundleId);
        System.out.println("Is App Installed: " + result);
       return result;
    }

    @Override
    public void deleteApp(String bundleId) {
        driver.removeApp(bundleId);
    }

    @Override
    public void activateApp(String bundleId) {
        driver.activateApp(bundleId);
    }

    @Override
    public void runInBackground(int time) {
        driver.runAppInBackground(Duration.ofSeconds(time));
    }

    @Override
    public void appStatus(String bundleId) {
        ApplicationState status = driver.queryAppState(bundleId);
        System.out.println("App Status: " + status.toString());
    }

    // iOS does not support currentActivity; so omitted

    @Override
    public void closeApp(String bundleId) {
        driver.terminateApp(bundleId);
    }

    @Override
    public void notification() {
        System.out.println("openNotifications() not supported on iOS.");
    }

    @Override
    public void keyboard() {
        driver.hideKeyboard();
    }

    @Override
    public void data() {
        System.out.println("Toggle Data not supported on iOS.");
    }

    @Override
    public void wifi() {
        System.out.println("Toggle WiFi not supported on iOS.");
    }

    @Override
    public void airplaneMode() {
        System.out.println("Toggle Airplane Mode not supported on iOS.");
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
        System.out.println("Toast messages not supported on iOS.");
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
