package base;

import java.time.Duration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.BrowserOptions;
import utilities.UtilClass;

public class BaseClass extends UtilClass{
	
	protected static final Logger logger = LogManager.getLogger(BaseClass.class);

	 public static ExtentTest test;
		public static ExtentReports extent;
		
		
		 
		 @BeforeClass
		    public void setup() {
		        // Initialize the ExtentReports object
//		        extent =  SetupExtentReport.setupExtentReport();
		    }
		 
	 
	
	   @BeforeMethod
	    public void setUp() {
		   logger.info("========== Starting WebDriver setup ==========");

	        // Get ChromeOptions from our reusable class
	        driver = new ChromeDriver(BrowserOptions.getChromeOptions());

	        // implicit wait
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	        // open main URL
	        driver.get("https://aecp.aecearth.io/");
	        
	        logger.info("Browser launched and navigated to AECP URL");
	    }
	
	@AfterMethod
	public void tearDown() {
	    try {
	        if (driver != null) {
	            // Try to close gracefully first
	            try {
	            	 logger.info("Closing browser window...");
	                driver.close();
	            } catch (Exception e) {
	                logger.warn("Error closing browser: " + e.getMessage());
	            }
	            
	            // Then quit to end the session
	            logger.info("Quitting WebDriver session...");
                driver.quit();
                logger.info("Browser quit successfully.");
	        }
	    } catch (Exception e) {
	        logger.warn("Browser already gone: " + e.getMessage());
	    } finally {
	        driver = null; // Ensure driver is nullified
	    }
	}

	 
}