package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class GM_LeaveManagementPage extends BaseClass{
	private static final Logger logger = LogManager.getLogger(GM_LeaveManagementPage.class);
	
	@FindBy(xpath="//button[text()='OK']")
	WebElement btn_successfulOK;
	
	@FindBy(xpath="//p[text()='Leave status updated to Approved successfully!']")
	WebElement msg_SuccessApprove;
	
	@FindBy(xpath="//p[text()='Leave status updated to Rejected successfully!']")
	WebElement msg_SuccessReject;
	
	@FindBy(xpath="//table[@class='align-items-center table-flush table']/tbody/tr[1]/td[7]")
	WebElement btn_Action;
	
	@FindBy(xpath="(//button[text()='Approved'])[1]")
	WebElement btn_Approved;
	
	@FindBy(xpath="(//button[text()='Rejected'])[1]")
	WebElement btn_Rejected;
	
	@FindBy(xpath="//table[@class='align-items-center table-flush table']/tbody/tr[1]/td[5]")
	WebElement firstRowStatus;
	
	@FindBy(xpath="//table[@class='align-items-center table-flush table']/tbody/tr[1]/td[1]")
	WebElement firstRowName;
	
	@FindBy(xpath="//input[@placeholder='Search Leave Type']")
	WebElement txt_searchfield;
	
	@FindBy(xpath="//td[normalize-space()='No leave applications found for HR employees']")
	WebElement msg_invalidSearch;
	
	@FindBy(xpath="//tbody/tr[1]")
	WebElement firstRowTable;
	
	public GM_LeaveManagementPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void approveHRLeave() {
		if(firstRowName.getText().equals("Sabari Raj S R")) {
	      if(!firstRowStatus.getText().equals("APPROVED")) {
	    	  btn_Action.click();
	    	  btn_Approved.click();
	    	  Assert.assertTrue(btn_successfulOK.isDisplayed());
	    	  btn_successfulOK.click();
	    	  Assert.assertTrue(msg_SuccessApprove.isDisplayed());
	    	  System.out.println(msg_SuccessApprove.getText());
	      }
		}	
	}
	
	public void rejectHRLeave() {
		if(firstRowName.getText().equals("Sabari Raj S R")) {
	      if(!firstRowStatus.getText().equals("REJECTED")) {
	    	  btn_Action.click();
	    	  btn_Rejected.click();
	    	  Assert.assertTrue(btn_successfulOK.isDisplayed());
	    	  btn_successfulOK.click();
	    	  Assert.assertTrue(msg_SuccessReject.isDisplayed());
	    	  System.out.println(msg_SuccessReject.getText());
	      }
		}	
	}
	
	public void leaveRequestsDisplayed() {
		waitExplicit(firstRowName);	
		if(firstRowName.getText().equals("Sabari Raj S R")) {
			Assert.assertEquals(firstRowName.getText(), "Sabari Raj S R");
			System.out.println("Leave requested by HR displayed in GM panel");
		}
	}
	
	public void searchLeaveType(String leavetype) throws InterruptedException {
		logger.info("Entering Leave type in the searchfield.");
		txt_searchfield.sendKeys(leavetype);
		Thread.sleep(2000);
		
		WebElement LeaveTypeElement = firstRowTable.findElement(By.xpath("./td[2]"));
		String LeaveType = LeaveTypeElement.getText();
		if(LeaveType.equalsIgnoreCase(leavetype)) {
			System.out.println("Searched Leave type is : "+leavetype);
			Assert.assertEquals(LeaveType, leavetype, "Leave request is not filtered in the table!");
		}else
		{
			System.out.println(firstRowTable.getText());
		}
		
	}
	
	public void searchLeaveTypeInvalid(String InvalidType) throws InterruptedException {
		logger.info("Entering Invalid Leave type in the searchfield.");
		try {
		txt_searchfield.sendKeys(InvalidType);
		Thread.sleep(2000);
		
		logger.info("Checking proper message is displayed or not");
		String invalidSearchMsg = msg_invalidSearch.getText();
		 System.out.println("Displayed message: " + invalidSearchMsg);
		 
		//Validation
		 Assert.assertTrue(
				 invalidSearchMsg.toLowerCase().contains("no") || invalidSearchMsg.toLowerCase().contains("record"),
				 "Invalid search message is not displayed!");
		 
		 logger.info("Invalid search message validated successfully.");
		}
		catch(TimeoutException e){
			logger.error("Table still has data – filter not working!", e);
			Assert.fail("Search failed – expected 'No records found' message but table shows data.");
		}
		
	}

}
