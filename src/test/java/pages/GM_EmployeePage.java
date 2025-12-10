package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class GM_EmployeePage extends BaseClass {

	private static final Logger logger = LogManager.getLogger(GM_EmployeePage.class);

	@FindBy(xpath = "//input[@placeholder='Search Employee Role']")
	WebElement txt_searchfield;
	
	@FindBy(xpath="//td[normalize-space()='No employee found']")
	WebElement invalidSearchMsg;

	@FindBy(xpath = "//tbody/tr/td[3]")
	List<WebElement> empNames_table;

	@FindBy(xpath = "//tbody/tr[1]/td[3]")
	WebElement firstRow_name;

	public GM_EmployeePage() {
		PageFactory.initElements(driver, this);
	}

	public void searchEmployee(String EmployeeName) {
		logger.info("Entering Employee name in searchfield");
		txt_searchfield.sendKeys(EmployeeName);

		logger.info("Checking if searched employee is filtered in the table.");
		String empName = firstRow_name.getText();
		if (empName.equalsIgnoreCase(EmployeeName)) {
			Assert.assertEquals(empName, EmployeeName, "Searched employee is not displayed in first row!");
			logger.info("Searched employee name is filtered in the table successfully.");
		}
	}
	
	public void searchInvalidEmployee(String InvalidEmployee) {
		logger.info("Entering invalid employee name in searchfield");
	   try {
		txt_searchfield.sendKeys(InvalidEmployee);
		
		logger.info("Checking proper message is displayed when no matching data is found.");
		String invaliMsg = invalidSearchMsg.getText();
		System.out.println("Displayed message is : "+invaliMsg);
		Assert.assertTrue(invaliMsg.toLowerCase().contains("no"), "Invalid search message is not displayed!");
		logger.info("Validated invalid search message.");
	   }
	   catch(TimeoutException e) {
		   logger.error("Table still has some data - filter not working!");
		   Assert.fail("Seacrhfield - expected 'No employee found' messsage but table showing some data!");
	   }
		
		
	}

}
