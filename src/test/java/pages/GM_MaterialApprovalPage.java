package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class GM_MaterialApprovalPage extends BaseClass{
	
	private static final Logger logger = LogManager.getLogger(GM_MaterialApprovalPage.class);
	
	@FindBy(xpath="//input[@placeholder='Search Materials']")
	WebElement txt_searchfield;
	
	@FindBy(xpath="//tbody/tr[1]")
	WebElement firstRowTable;
	
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

}
