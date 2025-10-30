package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import base.BaseClass;

public class HR_LeaveManagementPage extends BaseClass{
	public HR_LeaveManagementPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()='Apply Leave']")
	WebElement btn_applyLeave;
	
	@FindBy(xpath="//select[@name='leave_type']")
	WebElement dd_LeaveType;
	
	@FindBy(id="input-starting")
	WebElement date_start;
	
	@FindBy(id="input-ending")
	WebElement date_end;
	
	@FindBy(id="input-description")
	WebElement txt_description;
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement btn_Submit;
	
	public void applyLeave() throws InterruptedException {
		btn_applyLeave.click();
		waitExplicit(dd_LeaveType);
		Select leaveTypes = new Select(dd_LeaveType);
		leaveTypes.selectByVisibleText("Family Care Leave");
		waitExplicit(date_start);
		date_start.sendKeys("30/10/2025");
		waitExplicit(date_end);
		date_end.sendKeys("02/11/2025");
		txt_description.sendKeys("Enter Description");
		btn_Submit.click();
		Thread.sleep(3000);
		
		WebElement leaveEntry = driver.findElement(By.xpath("//table[@class='align-items-center table-flush table']/tbody/tr/td[2]"));
		Assert.assertEquals(leaveEntry.getText(), "Family Care Leave");
	}

}
