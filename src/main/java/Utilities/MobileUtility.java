package Utilities;

public interface MobileUtility {
	
	void installApp(String path);

    boolean isAppInstalled(String appId);

    void deleteApp(String appId);

    void activateApp(String appId);

    void runInBackground(int time);

    void appStatus(String appId);

    void closeApp(String appId);

    void notification();

    void keyboard();

    void data();

    void wifi();

    void airplaneMode();

    void portraitOrientation();

    void landscapeOrientation();

    void setClipboard(String text);

    void getClipboard();

    void implicitWait(int time);

    void toast();

    void getContexts();
    void resetingapp();
    void closingapp();
    void launchingapp();

}
