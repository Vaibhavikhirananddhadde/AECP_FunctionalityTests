package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.BaseClass;

public class SEng_ResourceRequestPage extends BaseClass{
	public SEng_ResourceRequestPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@class='btn btn-secondary']")
	WebElement btn_RequestResource;
	
	@FindBy(xpath="//select[@name='p_id']")
	WebElement dd_ProjectName;
	
	@FindBy(xpath="//select[@name='g_m_id']")
	WebElement dd_GeneralManager;
	
	@FindBy(name="role")
	WebElement txt_role;
	
	@FindBy(id="yourNumberField")
	WebElement txt_Quantity;
	
	@FindBy(xpath="//div[@role='textbox']")
	WebElement txt_remark;
	
	@FindBy(xpath="//input[@id='input-file']")
	WebElement file_uploadFile;
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement btn_Submit;
	
	public void requestResorce() throws Exception {
		waitImplicit();
		btn_RequestResource.click();
		Select projectName = new Select(dd_ProjectName);
		projectName.selectByVisibleText("Test Project");
		
		Select generalManager = new Select(dd_GeneralManager);
		generalManager.selectByVisibleText("Ashwini Satti[General Manager]");
		
		txt_role.sendKeys("Role Test");
		txt_Quantity.sendKeys("2");
		txt_remark.sendKeys("Enter remark");
		scrolltoview(file_uploadFile);
		waitExplicit(file_uploadFile);
		file_uploadFile.sendKeys("/Users/admin/Downloads/WhatsApp Image 2025-09-08 at 4.35.34 PM.jpeg");
		waitExplicit(btn_Submit);
		btn_Submit.submit();
		Thread.sleep(3000);
	}
	
	

}
