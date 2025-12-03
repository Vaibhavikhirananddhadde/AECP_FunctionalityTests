package pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import base.BaseClass;

public class GM_DashboardPage extends BaseClass{
	private static final Logger logger = LogManager.getLogger(GM_DashboardPage.class);
	
	@FindBy(xpath="//a[text()='Resource Approval']")WebElement navLink_ResourceApproval;
	@FindBy(xpath="//h1[normalize-space()='Resource Details']") WebElement title_ResourceApproval;
	
	@FindBy(xpath="//span[@class='mb-0 text-sm font-weight-bold']")WebElement lnk_greetingsProfile;
	
	@FindBy(xpath="(//span[text()='My profile'])[2]")WebElement btn_MyProfile;
	
	@FindBy(xpath="(//span[text()='Logout'])[2]")WebElement btn_Logout;
	
	@FindBy(xpath="//a[text()='Project']") WebElement navlink_Projects;
	
	@FindBy(xpath="//a[text()='Leave Management']") WebElement navlink_leaveManagement;
	@FindBy(xpath="//h1[normalize-space()='Leave Management Table']") WebElement title_LeaveManagement;
	
	@FindBy(xpath="//a[text()='Client']") WebElement navlink_client;
	@FindBy(xpath="//h1[normalize-space()='Client Details']") WebElement title_clientDetails;
	
	@FindBy(xpath="//a[text()='Project']") WebElement navlink_project;
	@FindBy(xpath="//h1[normalize-space()='Project Details']") WebElement title_project;
	
	@FindBy(xpath="//a[text()='Budget']") WebElement navlink_Budget;
	@FindBy(xpath="//h1[normalize-space()='Budget Details']") WebElement title_budget;
	
	@FindBy(xpath="//a[text()='Department']") WebElement navlink_department;
	@FindBy(xpath="//h1[normalize-space()='Department Details']") WebElement title_department;
	
	@FindBy(xpath="//div[@class='header-body']//div[@class='col-lg-6 col-xl-4']") List<WebElement> KPI_cards;
	
	@FindBy(xpath="//a[normalize-space()='Employee']") WebElement navlink_Employee;
	@FindBy(xpath="//h1[normalize-space()='Employee Details']") WebElement title_Employee;
	
	@FindBy(xpath="//a[normalize-space()='Material Approval']") WebElement navlink_MaterialApproval;
	@FindBy(xpath="//h1[normalize-space()='Material Details']") WebElement title_materialApproval;
	
	public GM_DashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnResourceApproval() {
		logger.info("Clicking on Resource Approval from side bar.");
		jsClickOn(navLink_ResourceApproval);
	}

	public void logout() {
		logger.info("Clicking on profile");
		lnk_greetingsProfile.click();
		waitExplicit(btn_Logout);
		
		logger.info("Clicking on logout button");
		btn_Logout.click();
	}
	
	public void clickOnProjects() {
		logger.info("Clicking on Projects from side bar");
		navlink_Projects.click();
	}
	
	public void clickOnLeaveManagement() {
		logger.info("Clickin on Leave management from side bar");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement leaveLink = wait.until(ExpectedConditions.elementToBeClickable(navlink_leaveManagement));
        leaveLink.click();
		
	}
	
	public void clickOnClient() {
		logger.info("Clicking on client from side bar");
		navlink_client.click();
	}
	
	public void clickOnProject() {
		logger.info("Clicking on project from side bar");
		navlink_project.click();
	}
	
	public void clickOnBudget() {
		logger.info("Clicking on budget from side bar");
		navlink_Budget.click();
	}
	
	public void clickOnDepartment() {
		logger.info("Clicking on department from side bar");
		navlink_department.click();
		String exp_URL = "https://aecp.aecearth.io/general-admin/general-management/department";
		String act_URL=driver.getCurrentUrl();
		Assert.assertEquals(act_URL, exp_URL, "Department page is not displayed!");
	}
	
	public void cardsDisplayed() {
		for(WebElement card:KPI_cards) {
			WebElement cardTitle= card.findElement(By.xpath(".//div[@class='h4 mb-0 card-title']"));
			WebElement cardCount = card.findElement(By.xpath(".//span[@class='h2 font-weight-bold  ml-3']"));
			
			System.out.println("The Number of "+cardTitle.getText()+" is : "+cardCount.getText());
			Assert.assertTrue(card.isDisplayed(), "Card is not displayed!..");
		}
	}
	
	public void clickOnEmployee() {
		logger.info("Clicking on Employee from side bar");
		navlink_Employee.click();
	}
	
	public void clickOnMaterialApproval() {
		logger.info("Clicking on Material Approval from side bar");
		navlink_MaterialApproval.click();
	}
	
	public void check_headerDisplayed() {
		SoftAssert soft = new SoftAssert();
		logger.info("Check page header is displayed for client page");
		navlink_client.click();
		soft.assertTrue(title_clientDetails.isDisplayed(), "Page header is not present for "+navlink_client.getText());
		
		logger.info("Check page header is displayed for project page");
		navlink_project.click();
		soft.assertTrue(title_project.isDisplayed(), "Page header is not present for "+navlink_project.getText());
		
		logger.info("Check page header is displayed for employee page");
		navlink_Employee.click();
		soft.assertTrue(title_Employee.isDisplayed(), "Page header is not present for "+navlink_Employee.getText());
		
		logger.info("Check page header is displayed for department page");
		navlink_department.click();
		soft.assertTrue(title_department.isDisplayed(), "Page header is not present for "+navlink_department.getText());
		
		logger.info("Check page header is displayed for Budget page");
		navlink_Budget.click();
		soft.assertTrue(title_budget.isDisplayed(), "Page header is not present for "+navlink_Budget.getText());

		logger.info("Check page header is displayed for Material Approval page");
		navlink_MaterialApproval.click();
		soft.assertTrue(title_materialApproval.isDisplayed(), "Page header is not present for "+navlink_MaterialApproval.getText());
		
		logger.info("Check page header is displayed for Resource Approval page");
		navLink_ResourceApproval.click();
		soft.assertTrue(title_ResourceApproval.isDisplayed(), "Page header is not present for "+navLink_ResourceApproval);
		
		logger.info("Check page header is displayed for Leave Management page");
		navlink_leaveManagement.click();
		soft.assertTrue(title_LeaveManagement.isDisplayed(), "Page header is not present for "+navlink_leaveManagement);
		
	}
	
	public boolean isElementPresent(WebElement element) {
	    try {
	        return element.isDisplayed();
	    } catch (NoSuchElementException | StaleElementReferenceException e) {
	        return false;
	    } catch (Exception e) {
	        return false;
	    }
	}


	
	
	
}

