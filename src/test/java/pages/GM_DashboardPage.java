package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class GM_DashboardPage extends BaseClass{
	
	@FindBy(xpath="//a[text()='Resource Approval']")WebElement navLink_ResourceApproval;
	
	@FindBy(xpath="//span[@class='mb-0 text-sm font-weight-bold']")WebElement lnk_greetingsProfile;
	
	@FindBy(xpath="(//span[text()='My profile'])[2]")WebElement btn_MyProfile;
	
	@FindBy(xpath="(//span[text()='Logout'])[2]")WebElement btn_Logout;
	
	@FindBy(xpath="//a[text()='Project']") WebElement navlink_Projects;
	
	@FindBy(xpath="//a[text()='Leave Management']") WebElement navlink_leaveManagement;
	
	@FindBy(xpath="//a[text()='Client']") WebElement navlink_client;
	
	@FindBy(xpath="//a[text()='Project']") WebElement navlink_project;
	
	@FindBy(xpath="//a[text()='Budget']") WebElement navlink_Budget;
	
	@FindBy(xpath="//a[text()='Department']") WebElement navlink_department;
	
	@FindBy(xpath="//div[@class='header-body']//div[@class='col-lg-6 col-xl-4']") List<WebElement> KPI_cards;
	
	
	public GM_DashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnResourceApproval() {
		jsClickOn(navLink_ResourceApproval);
	}

	public void logout() {
		lnk_greetingsProfile.click();
		waitExplicit(btn_Logout);
		btn_Logout.click();
	}
	
	public void clickOnProjects() {
		navlink_Projects.click();
	}
	
	public void clickOnLeaveManagement() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement leaveLink = wait.until(ExpectedConditions.elementToBeClickable(navlink_leaveManagement));
        leaveLink.click();
		
	}
	
	public void clickOnClient() {
		navlink_client.click();
	}
	
	public void clickOnProject() {
		navlink_project.click();
	}
	
	public void clickOnBudget() {
		navlink_Budget.click();
	}
	
	public void clickOnDepartment() {
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

	
	
	
}

