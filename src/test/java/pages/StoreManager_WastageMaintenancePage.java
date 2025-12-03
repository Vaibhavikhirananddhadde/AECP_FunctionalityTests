package pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class StoreManager_WastageMaintenancePage extends BaseClass{
	
	private static final Logger logger = LogManager.getLogger(StoreManager_WastageMaintenancePage.class);
	
	public StoreManager_WastageMaintenancePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()='Add New Waste']") WebElement btn_AddNewWaste;
	@FindBy(id="wasteCheckbox") WebElement chkbox_Damaged;
	@FindBy(id="replaceCheckbox") WebElement chkbox_Replaced;
	@FindBy(id="input-waste-item-name") WebElement txt_wasteItemName;
	@FindBy(xpath="//input[@id='input-waste-image']") WebElement upload_image;
	@FindBy(xpath="//button[text()='Submit']") WebElement btn_Submit;
	@FindBy(xpath="//button[text()='OK']") WebElement btn_OK;
	@FindBy(xpath="//input[@id='input-replace-item-name']") WebElement txt_replaceItemName;
	@FindBy(xpath="//input[@name='costSpent']") WebElement txt_costSpent;
	@FindBy(xpath="//input[@name='replaceImage']") WebElement upload_replacedItemImage;
	@FindBy(xpath="//input[@name='revenue']") WebElement txt_revenue;
	@FindBy(xpath="//input[@name='newreplaceImage']") WebElement upload_newReplacedItemImage;
	
	public void addDamagedWaste() {
		
		logger.info("Clicking on Add new waste button");
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		btn_AddNewWaste.click();
		
		logger.info("Clicking on Damaged checkbox");
		chkbox_Damaged.click();
		
		logger.info("Entering waste item name");
		txt_wasteItemName.sendKeys("Damaged item X");
		
		logger.info("Uploading image document");
		upload_image.sendKeys("/Users/admin/Downloads/Gemini_Generated_Image_khbncukhbncukhbn.png");
		
		logger.info("Clicking on Submit button");
		btn_Submit.click();
		
		//validation
		wait.until(ExpectedConditions.visibilityOf(btn_OK));
		Assert.assertTrue(btn_OK.isDisplayed(), "Damaged item is not added successfully!");
		
		logger.info("Clicking on OK button");
		btn_OK.click();
	
	}
	
	public void addReplacedWaste() {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		btn_AddNewWaste.click();
		chkbox_Replaced.click();
		txt_replaceItemName.sendKeys("Test Product");
		txt_costSpent.sendKeys("200");
		upload_replacedItemImage.sendKeys("/Users/admin/Downloads/Gemini_Generated_Image_khbncukhbncukhbn.png");
		txt_revenue.sendKeys("100");
		upload_newReplacedItemImage.sendKeys("/Users/admin/Downloads/Gemini_Generated_Image_khbncukhbncukhbn.png");
		btn_Submit.click();
		wait.until(ExpectedConditions.visibilityOf(btn_OK));
		Assert.assertTrue(btn_OK.isDisplayed(), "Replaced item is not added successfully!");
		btn_OK.click();
	}

}
