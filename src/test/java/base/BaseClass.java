package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

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
		 public void setUp() throws Exception {
		     logger.info("========== Starting WebDriver setup ==========");

		     // Path to your config.properties (relative to project root)
		     String configPath = "/src/test/resources/configfiles/config.properties";

		     // 1) Read browser name from properties
		     launchBrowser(readProperty("browser", configPath), true);
		    

		     // 2) Read headless flag (optional)
		     String headlessProp = readProperty("headless", configPath);
		     boolean headless = Boolean.parseBoolean(headlessProp);
		     logger.info("Headless mode: " + headless);


		     // 4) Read base URL from config and open it
		     String baseUrl = readProperty("url", configPath);
		     logger.info("Navigating to URL: " + baseUrl);
		     driver.get(baseUrl);

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