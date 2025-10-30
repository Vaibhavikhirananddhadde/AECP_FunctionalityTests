package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class SEng_DashboardPage extends BaseClass{
	public SEng_DashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Site Resource Request']")
	WebElement navlink_SiteResourceRequest;
	
	@FindBy(xpath="//span[@class='avatar avatar-sm rounded-circle']/img[@alt='Profile']")
	WebElement lnk_greetingsProfile;
	
	@FindBy(xpath="(//span[text()='My profile'])[2]")
	WebElement btn_MyProfile;
	
	@FindBy(xpath="(//span[text()='Logout'])[2]")
	WebElement btn_Logout;
	
	public void clickOnResourceRequest() {
		navlink_SiteResourceRequest.click();
	}
	
	public void logout() {
		lnk_greetingsProfile.click();
		waitExplicit(btn_Logout);
		btn_Logout.click();
	}

}
