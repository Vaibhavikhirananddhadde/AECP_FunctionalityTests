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

}
