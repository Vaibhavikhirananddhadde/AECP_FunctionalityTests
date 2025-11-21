package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class HR_DashboardPage extends BaseClass{
	
	@FindBy(xpath="(//div[@class='d-flex justify-content-between align-items-center'])[1]")
	WebElement card_TotalEmployees;
	
	@FindBy(xpath="(//div[@class='d-flex justify-content-between align-items-center'])[2]")
	WebElement card_OnTime;
	
	@FindBy(xpath="(//div[@class='d-flex justify-content-between align-items-center'])[3]")
	WebElement card_LateToday;
	
	@FindBy(xpath="(//div[@class='d-flex justify-content-between align-items-center'])[4]")
	WebElement card_LeaveRequests;
	
	@FindBy(xpath="//a[text()='Resource Allocation']")
	WebElement navLink_ResourceAllocation;
	
	@FindBy(xpath="//a[text()='Leave Management']")
	WebElement navLink_LeaveManagement;
	
	@FindBy(xpath="//span[@class='mb-0 text-sm font-weight-bold']")
	WebElement lnk_greetingsProfile;
	
	@FindBy(xpath="(//span[text()='My profile'])[2]")
	WebElement btn_MyProfile;
	
	@FindBy(xpath="(//span[text()='Logout'])[2]")
	WebElement btn_Logout;
	
	@FindBy(xpath="//a[text()='Attendance']")
	WebElement navLink_Attendance;
	
	@FindBy(xpath="//span[text()='HR Management']")
	WebElement navLink_HRmanagement;
	
	@FindBy(xpath="//span[text()='Employees']")
	WebElement navLink_Employee;
	
	@FindBy(xpath="//span[text()='Department']")
	WebElement navLink_Department;
	
	@FindBy(xpath="//span[text()='Role']")
	WebElement navLink_Role;
	
	public HR_DashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void cardsDisplayed() {
		Assert.assertTrue(card_TotalEmployees.isDisplayed(), "Total Employees card is not displayed");
		System.out.println(card_TotalEmployees.getText());
		Assert.assertTrue(card_OnTime.isDisplayed(), "Total Employees card is not displayed");
		System.out.println(card_OnTime.getText());
		Assert.assertTrue(card_LateToday.isDisplayed(), "Total Employees card is not displayed");
		System.out.println(card_LateToday.getText());
		Assert.assertTrue(card_LeaveRequests.isDisplayed(), "Total Employees card is not displayed");
		System.out.println(card_LeaveRequests.getText());
	}
	
	public void clickOnResourceAllocation() {
		navLink_ResourceAllocation.click();
	}
	
	public void clickOnLeaveManagement() {
		navLink_LeaveManagement.click();
	}
	
	public void logout() {
		lnk_greetingsProfile.click();
		waitExplicit(btn_Logout);
		btn_Logout.click();
	}
	
	public void clickOnAttendance() {
		navLink_Attendance.click();
	}
	
	public void clickOnEmployee() {
		navLink_HRmanagement.click();
		navLink_Employee.click();
	}
	
	public void clickOnDepartment() {
		navLink_HRmanagement.click();
		navLink_Department.click();
	}
	
	public void clickOnRole() {
		navLink_HRmanagement.click();
		navLink_Role.click();
	}
	
	
}
