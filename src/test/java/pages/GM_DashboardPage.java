package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class GM_DashboardPage extends BaseClass{
	
	@FindBy(xpath="//a[text()='Resource Approval']")
	WebElement navLink_ResourceApproval;
	
	@FindBy(xpath="//span[@class='mb-0 text-sm font-weight-bold']")
	WebElement lnk_greetingsProfile;
	
	@FindBy(xpath="(//span[text()='My profile'])[2]")
	WebElement btn_MyProfile;
	
	@FindBy(xpath="(//span[text()='Logout'])[2]")
	WebElement btn_Logout;
	
	@FindBy(xpath="//a[text()='Project']")
	WebElement navlink_Projects;
	
	
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

}
