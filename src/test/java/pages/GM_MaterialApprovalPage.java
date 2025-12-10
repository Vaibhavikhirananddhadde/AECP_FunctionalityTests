package pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class GM_MaterialApprovalPage extends BaseClass{
	
	private static final Logger logger = LogManager.getLogger(GM_MaterialApprovalPage.class);
	
	@FindBy(xpath="//input[@placeholder='Search Materials']")
	WebElement txt_searchfield;
	
	@FindBy(xpath="//td[@class='text-center']")
	WebElement searchInvalid_msg;
	
	@FindBy(xpath="//tbody/tr[1]")
	WebElement firstRowTable;
	
	@FindBy(xpath="//table/tbody/tr[1]/td[10]//i[@class='fa-solid fa-eye']")
	WebElement icon_viewMaterialDetails;
	
	@FindBy(xpath="//span[contains(text(), 'Multiple Materials View')]")
	WebElement multipleMaterial_model;
	
	@FindBy(xpath="//h5[normalize-space()='Material View']")
	WebElement singleMaterial_model;
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Set Action'])[1]")
	WebElement btn_setAction;
	
	@FindBy(xpath="(//select[@id='input-status'])[1]")
	WebElement dd_status;
	
	@FindBy(xpath="//p[normalize-space()='Status updated successfully!']")
	WebElement success_statuschange;
	
	@FindBy(xpath="//button[normalize-space()='OK']")
	WebElement btn_OK;
	
	@FindBy(xpath="//textarea[@id='exampleText']")
	WebElement txt_description;
	
	@FindBy(xpath="//button[normalize-space()='Submit']")
	WebElement btn_submit;
	
	public GM_MaterialApprovalPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void searchMaterialRequests(String materialName) throws InterruptedException {
		logger.info("Entering Material Name");
		txt_searchfield.sendKeys(materialName);
		Thread.sleep(2000);
		
		WebElement MaterialNameElement = firstRowTable.findElement(By.xpath("./td[3]"));
		String MaterialName = MaterialNameElement.getText();
		if(MaterialName.equalsIgnoreCase(materialName)) {
			System.out.println("Searched Material Name is : "+ MaterialName);
			Assert.assertEquals(MaterialName, materialName, "Material name is not filtered in the table!");
		}
		
	}
	
	public void searchInvalidMaterial(String InvalidMaterial) throws InterruptedException {
		logger.info("Entering invalid material name");
		try {
		txt_searchfield.sendKeys(InvalidMaterial);
		Thread.sleep(2000);
		
		logger.info("Checking proper message is displayed");
		String messageInvalid = searchInvalid_msg.getText();
		System.out.println("Displayed message is : "+messageInvalid);
		
		Assert.assertTrue(messageInvalid.toLowerCase().contains("no"));
		logger.info("Invalid message validated.");
		}
		catch(TimeoutException e) {
			
			logger.error("Table still has data - filter not working!");
			Assert.fail("Searchfield - expected 'no materials available'  but table has data");
		}
		
	}
	
	public void viewMaterialDetails() throws InterruptedException {
		logger.info("Clicking on 'View' icon.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(icon_viewMaterialDetails));
		icon_viewMaterialDetails.click();
		Thread.sleep(2000);
		
		logger.info("Checking material details");
		
		Assert.assertTrue(singleMaterial_model.isDisplayed() || multipleMaterial_model.isDisplayed(), "Material details page is not displayed!");
		
	    logger.info("Verified material details");
	}
	
	public void approveMaterialRequest() {
		logger.info("Clicking on 'Set Action'  button");
		btn_setAction.click();
		
		logger.info("Selecting action Accept");
		Select status = new Select(dd_status);
		status.selectByVisibleText("Accept");
		
		logger.info("Entering description");
		txt_description.sendKeys("Testing....");
		
		logger.info("Clicking on submit button");
		btn_submit.click();
		
		logger.info("Verifying success message");
		Assert.assertTrue(success_statuschange.isDisplayed(), "Success message not displayed after status change!");
		
		btn_OK.click();
		logger.info("Clicked on OK button");
	}
	
	public void rejectMaterialRequest() {
		logger.info("Clicking on 'Set Action'  button");
		btn_setAction.click();
		
		logger.info("Selecting action Accept");
		Select status = new Select(dd_status);
		status.selectByVisibleText("Reject");
		
		logger.info("Entering description");
		txt_description.sendKeys("Testing....");
		
		logger.info("Clicking on submit button");
		btn_submit.click();
		
		logger.info("Verifying success message");
		Assert.assertTrue(success_statuschange.isDisplayed(), "Success message not displayed after status change!");
		
		btn_OK.click();
		logger.info("Clicked on OK button");
	}

}
