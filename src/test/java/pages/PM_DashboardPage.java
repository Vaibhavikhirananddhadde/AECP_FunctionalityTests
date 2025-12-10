package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class PM_DashboardPage extends BaseClass{
	
	@FindBy(xpath="//a[text()='Resource Requisition']")WebElement navLink_ResourceRequisition;
	
	@FindBy(xpath="//span[@class='mb-0 text-sm font-weight-bold']")WebElement lnk_greetingsProfile;
	
	@FindBy(xpath="(//span[text()='My profile'])[2]")WebElement btn_MyProfile;
	
	@FindBy(xpath="(//span[text()='Logout'])[2]")WebElement btn_Logout;
	
	@FindBy(xpath="//li[@class='nav-item']//a[text()='Project']")WebElement navlink_Projects;
	
	@FindBy(xpath="//h1[text()='Project Details']") WebElement header_Project;
	
	@FindBy(xpath="//a[text()='Task']") WebElement navlink_Task;
	
	@FindBy(xpath="//a[text()='Material Requisition']") WebElement navlink_MaterialRequisition;
	
	@FindBy(xpath="//a[text()='Site Engineer']") WebElement navlink_SiteEngineer;
	
	@FindBy(xpath="//a[text()='Budget']") WebElement navlink_Budget;
	
	@FindBy(xpath="//a[text()='Task Report']") WebElement navlink_TaskReport;
	
	@FindBy(xpath="//a[text()='Leave Management']") WebElement navlink_LeaveManagement;
	
	@FindBy(xpath="//h1[normalize-space()='Project Details']") WebElement pageHeader_Project;
	
	@FindBy(xpath="//h2[normalize-space()='Your Task']") WebElement pageHeader_Task;
	
	@FindBy(xpath="//h1[normalize-space()='Materials Details']") WebElement pageHeader_MaterialRequisition;
	
	@FindBy(xpath="//h1[normalize-space()='Employee Details']") WebElement pageHeader_SiteEngineer;
	
	@FindBy(xpath="//h1[normalize-space()='Budget']") WebElement pageHeader_Budget;
	
	@FindBy(xpath="//h1[normalize-space()='Leave Management Table']") WebElement pageHeader_LeaveManagement;
	
	@FindBy(xpath="//h3[normalize-space()='Task Details']") WebElement pageHeader_TaskReport;
	
	@FindBy(xpath="//h1[normalize-space()='Request Resource']") WebElement pageHeader_ResourceRequisition;
	
	public PM_DashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void click_ResourceRequisition() {
		navLink_ResourceRequisition.click();
		
	}
	
	public void logout() {
		lnk_greetingsProfile.click();
		waitExplicit(btn_Logout);
		btn_Logout.click();
	}
	
	public void checkKPIcardsDisplayed() {
		List<WebElement> KPIcards = driver.findElements(By.xpath("//div[@class='row']/div[@class='col-lg-6 col-xl-3']//div[@class='h3 mb-0 card-title']"));
		for(WebElement card:KPIcards) {
			String cardTitle = card.getText();
			System.out.println(cardTitle);
			Assert.assertTrue(card.isDisplayed(), "KPI cards are displayed");
		}
	}
	
	public void clickOnProjects() {
		navlink_Projects.click();
		Assert.assertTrue(header_Project.isDisplayed(), "Project details page header is not displayed!");
	}
	
	public void clickOnTask() {
		navlink_Task.click();
	}
	
	public void clickOnMaterialRequisition() {
		navlink_MaterialRequisition.click();
		String exp_URL="https://aecp.aecearth.io/project-admin/project-management/materials";
		String act_URL=driver.getCurrentUrl();
		Assert.assertEquals(exp_URL, act_URL, "Materials page is not displayed.");
	}
	
	public void clickOnSiteEngineer() {
		navlink_SiteEngineer.click();
	}
	
	public void clickOnBudget() {
		navlink_Budget.click();
		String exp_URL="https://aecp.aecearth.io/project-admin/project-management/budget";
		String act_URL=driver.getCurrentUrl();
		Assert.assertEquals(act_URL, exp_URL, "Budget page is not displayed!");
	}
	
	public void clickOnTaskReport() {
		navlink_TaskReport.click();
		String exp_URL="https://aecp.aecearth.io/project-admin/project-management/Report";
		String act_URL=driver.getCurrentUrl();
		Assert.assertEquals(act_URL, exp_URL, "Task Report page is not displayed!");
	}
	
	public void clickOnLeaveManagement() {
		navlink_LeaveManagement.click();
	}
	
	public void checkPageHeaderDisplayed() {
		navlink_Projects.click();
		Assert.assertTrue(navlink_Projects.isDisplayed(), "Page header is not displayed for "+navlink_Projects.getText()+" page");
		
		navlink_Task.click();
		Assert.assertTrue(pageHeader_Task.isDisplayed(), "Page header is not displayed for "+navlink_Task.getText()+" page");
		
		navlink_MaterialRequisition.click();
		Assert.assertTrue(pageHeader_MaterialRequisition.isDisplayed(), "Page header is not displayed for "+navlink_MaterialRequisition.getText());
		
		navlink_SiteEngineer.click();
		Assert.assertTrue(pageHeader_SiteEngineer.isDisplayed(), "Page header is not displayed for "+navlink_SiteEngineer.getText());
		
		navlink_LeaveManagement.click();
		Assert.assertTrue(pageHeader_LeaveManagement.isDisplayed(),"Page header is not displayed for "+navlink_LeaveManagement.getText());
		
		navlink_TaskReport.click();
		Assert.assertTrue(pageHeader_TaskReport.isDisplayed(), "Page header is not displayed for "+navlink_TaskReport.getText());
		
		navLink_ResourceRequisition.click();
		Assert.assertTrue(pageHeader_ResourceRequisition.isDisplayed(), "Page header is not displayed for "+navLink_ResourceRequisition.getText());
		
		navlink_Budget.click();
		Assert.assertTrue(pageHeader_Budget.isDisplayed(), "Page header is not displayed for "+navlink_Budget.getText());
	}

}
