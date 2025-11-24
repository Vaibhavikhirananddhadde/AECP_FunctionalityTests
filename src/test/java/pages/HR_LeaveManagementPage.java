package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	
	@FindBy(xpath="//input[@placeholder='Search Leave Type']")
	WebElement txt_searchfield;
	
	@FindBy(xpath="//table[@class='align-items-center table-flush table']/tbody/tr[1]/td[2]")
	WebElement td_firstRowLeaveType;
	
	@FindBy(xpath="(//i[@class='fa-solid fa-eye'])[1]")
	WebElement icon_View;
	
	@FindBy(xpath="//h1[text()='Employee Leave Details']")
	WebElement pageHeader_employeeLeaveDetails;
	
	@FindBy(xpath="//table[@class='align-items-center table-flush table']/tbody/tr[1]//i[@class='fa-solid fa-trash text-danger']")
	WebElement icon_Delete;
	
	@FindBy(xpath="//div[text()='Are you sure you want to delete this leave application?']")
	WebElement deleteConfirmation;
	
	@FindBy(xpath="//button[text()='Yes']")
	WebElement btn_YES;
	
	@FindBy(xpath="//button[text()='No']")
	WebElement btn_NO;
	
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
		driver.get("https://aecp.aecearth.io/hr-admin/hr-management/leave3");
		Thread.sleep(3000);
		WebElement leaveEntry = driver.findElement(By.xpath("//table[@class='align-items-center table-flush table']/tbody/tr/td[2]"));
		Assert.assertEquals(leaveEntry.getText(), "Family Care Leave");
	}
	
	public void searchLeaveType(String leaveType) throws InterruptedException {
		txt_searchfield.sendKeys(leaveType);
		Thread.sleep(3000);
		Assert.assertEquals(td_firstRowLeaveType.getText().trim(), leaveType, "Search field is not working properly!");
	}
	
	public void viewLeaveDetails() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(icon_View));
		icon_View.click();
		wait.until(ExpectedConditions.visibilityOf(pageHeader_employeeLeaveDetails));
		Assert.assertTrue(pageHeader_employeeLeaveDetails.isDisplayed(), "Employee details page is not displayed!");
	}
	
	public void deleteLeave() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(icon_Delete));
		icon_Delete.click();
		wait.until(ExpectedConditions.visibilityOf(deleteConfirmation));
		Assert.assertTrue(deleteConfirmation.isDisplayed(), "Confirmation message is not displayed before deleting leave request!");
		btn_YES.click();
		driver.get("https://aecp.aecearth.io/hr-admin/hr-management/leave3");
		Thread.sleep(3000);
		WebElement LeaveAppliedBy = driver.findElement(By.xpath("//table[@class='align-items-center table-flush table']/tbody/tr[1]/td[1]"));
		Assert.assertNotEquals(LeaveAppliedBy.getText().trim(), "Sabari Raj S R", "Leave not deleted successfully!");
		
	}
	
	public void dontDeleteLeave() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(icon_Delete));
		icon_Delete.click();
		wait.until(ExpectedConditions.visibilityOf(deleteConfirmation));
		btn_NO.click();
		Assert.assertTrue(icon_Delete.isDisplayed(), "Leave request is deleted when clicked on 'NO' button!");
	}

}
