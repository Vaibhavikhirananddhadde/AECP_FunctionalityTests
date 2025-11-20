package utilities;

import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserOptions {
    public static ChromeOptions getChromeOptions() {

        ChromeOptions options = new ChromeOptions();

        // --- Common & useful arguments ---
        options.addArguments("--start-maximized");                    // maximize window
        options.addArguments("--disable-notifications");              // disable notifications
        options.addArguments("--disable-popup-blocking");             // disable popups
        options.addArguments("--disable-infobars");                   // remove "Chrome is automated"
        options.addArguments("--disable-extensions");                 // disable extensions
        options.addArguments("--remote-allow-origins=*");            // fix CDP version issues

        // --- Stability fixes for automation ---
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        // --- Accept SSL certificates ---
        options.setAcceptInsecureCerts(true);

        // --- Headless mode (optional) ---
        // options.addArguments("--headless=new"); // enable if running in CI/CD

        return options;
    }


}
