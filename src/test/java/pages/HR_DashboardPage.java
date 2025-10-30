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
	

}
