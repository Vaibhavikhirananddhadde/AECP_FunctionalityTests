package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class PM_DashboardPage extends BaseClass{
	
	@FindBy(xpath="//a[text()='Resource Requisition']")
	WebElement navLink_ResourceRequisition;
	
	@FindBy(xpath="//span[@class='mb-0 text-sm font-weight-bold']")
	WebElement lnk_greetingsProfile;
	
	@FindBy(xpath="(//span[text()='My profile'])[2]")
	WebElement btn_MyProfile;
	
	@FindBy(xpath="(//span[text()='Logout'])[2]")
	WebElement btn_Logout;
	
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

}
