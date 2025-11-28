package Utilities;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;

public class UtilityClassObject {
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

	public static ExtentTest getTest() {
		return test.get();
	}

	public static void setTest(ExtentTest actualTest) {
		test.set(actualTest);
	}

	public static AppiumDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(AppiumDriver actualDriver) {
		driver.set(actualDriver);
	}

}
