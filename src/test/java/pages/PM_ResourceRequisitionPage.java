package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class PM_ResourceRequisitionPage extends BaseClass{
	
	@FindBy(xpath="//button[text()='Request']")
	WebElement btn_Request;
	
	@FindBy(name = "p_id")
	WebElement dd_ProjectName;
	
	@FindBy(name="g_m_id")
	WebElement dd_GeneralManager;
	
	@FindBy(name="role")
	WebElement txt_Role;
	
	@FindBy(id="yourNumberField")
	WebElement txt_quantity;
	
	@FindBy(xpath="//div[@role='textbox']")
	WebElement txt_Remark;
	
	@FindBy(xpath="//input[@name='files']")
	WebElement uploadFile;
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement btn_Submit;
	
	@FindBy(xpath="(//button[@class ='dropdown-toggle btn btn-secondary'])[2]")
	WebElement dd_showEntries;
	
	@FindBy(xpath="//button[@class=\"dropdown-item\"and  text()='All']")
	WebElement opt_Allentry;

	@FindBy(xpath="//div[@class='align-items-center justify-content-xl-between row']")
	WebElement footer;
	
	public PM_ResourceRequisitionPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void requestResource() throws Exception {
		btn_Request.click();
		waitExplicit(dd_ProjectName);
		
		//Select projectName
		Select select = new Select(dd_ProjectName);
		select.selectByVisibleText("Test Project1");
		
	
		//Select general manager
		Select select1 = new Select(dd_GeneralManager);
		select1.selectByValue("MI450003");
		
		waitExplicit(txt_Role);
		txt_Role.sendKeys("Role test");
		waitExplicit(txt_quantity);
		txt_quantity.sendKeys("20");
		waitExplicit(txt_Remark);
		txt_Remark.sendKeys("Testing enter remark");
		waitExplicit(uploadFile);
		uploadFile.sendKeys("/Users/admin/Downloads/WhatsApp Image 2025-09-08 at 4.35.34 PM.jpeg");
		scrollDown();
		waitExplicit(btn_Submit);
		btn_Submit.click();
		waitImplicit();
		
	}
	
	public void checkStatus() throws Exception {
		waitExplicit(dd_showEntries);
		driver.navigate().refresh();
		dd_showEntries.click();
		opt_Allentry.click();
		
		scrolltoview(footer);
		// 1) Wait for the table
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		By tableSel = By.cssSelector("table.align-items-center.table-flush.table");
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableSel));

		//get status column in last row
		 WebElement lastRowStatusCell = driver.findElement(By.xpath("(//table[contains(@class,'table-flush')]//tbody/tr)[last()]//td[4]"));
		String status = lastRowStatusCell.getText();
		Assert.assertEquals(status, "APPROVED", "Status not changed");

	}

}
