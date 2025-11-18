package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import base.BaseClass;

public class StoreManager_VendorManagementPage extends BaseClass{
	public StoreManager_VendorManagementPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Add stock from vendor to store
	@FindBy(xpath="//button[text()='Add Stock']") WebElement btn_AddStock;
	@FindBy(xpath="//button[text()='Add Single Stock']") WebElement btn_AddSingleStock;
	@FindBy(xpath="//select[@name='vendor_name']") WebElement dd_selectVendorName;
	@FindBy(xpath="//input[@id='product_name']") WebElement txt_productName;
	@FindBy(xpath="//input[@id='total_stock']") WebElement txt_totalStock;
	@FindBy(xpath="//input[@id='available_stock']") WebElement txt_currentStock;
	@FindBy(xpath="//input[@id='cost']") WebElement txt_enterCost;
	@FindBy(xpath="//input[@id='delivery_date']") WebElement txt_deliveryDate;
	@FindBy(xpath="//button[text()='Submit']") WebElement btn_Submit;
	@FindBy(xpath="(//table[@class='align-items-center table-flush table']/tbody/tr[last()])[1]") WebElement newRow_StockReport;
	@FindBy(xpath="(//table[@class='align-items-center table-flush table']/tbody/tr[1])[2]") WebElement newRow_VendorToStore;
	@FindBy(xpath="(//table[@class='align-items-center table-flush table'][1]/tbody/tr[last()]/td[3])[1]") WebElement currentStock_stockReport;
	@FindBy(xpath="(//table[@class='align-items-center table-flush table'][1]/tbody/tr[last()]/td[4])[1]") WebElement totalStock_stockReport;
	
	//Return to vendor from store
	@FindBy(xpath="//button[text()='Return to Vendor']") WebElement btn_returnToVendor;
	@FindBy(xpath="//button[text()='Add Single Return to Vendor']") WebElement btn_addSingleReturnToVendor;
	@FindBy(xpath="//select[@name='vendor_id']") WebElement dd_selectVendor;
	@FindBy(xpath="//select[@name='product_id']") WebElement dd_selectProduct;
	@FindBy(xpath="//input[@id='quantity_returned']") WebElement txt_quantityToReturn;
	@FindBy(xpath="//input[@id='return_date']") WebElement date_returnDate;
	@FindBy(xpath="//input[@id='return_reason']") WebElement txt_returnReason;
	@FindBy(xpath="//button[@class='btn btn-primary' and text()='Return to Vendor']") WebElement btn_returnToVendor_submit;
	@FindBy(xpath="((//table[@class='align-items-center table-flush table'])[2]/tbody/tr/td[3])[1]") WebElement QuantityDelivered_vendorToStore;
	@FindBy(xpath="((//table[@class='align-items-center table-flush table'])[3]/tbody/tr/td[3])[1]") WebElement QuantityReturned_storeToVendor;
	
	//Vendor material delivery
	@FindBy(xpath="//button[text()='Add Material Delivery']") WebElement btn_addMaterialDelivery;
	@FindBy(xpath="//button[text()='Add Single Material Delivery']") WebElement btn_addSingleMaterialDelivery;
	@FindBy(xpath="//div[@class='dropdown']//span[contains(text(),'Select Product')]") WebElement dd_SelectProductName;
	@FindBy(xpath="//input[@id='delivery_date']") WebElement date_deliveryDate;
	@FindBy(xpath="//select[@id='vendor_id']") WebElement dd_SelectVendor;
	@FindBy(xpath="//input[@id='quantity_delivered']") WebElement txt_enterQuantityDelivered;
	@FindBy(xpath="(//button[@class='dropdown-toggle btn btn-secondary' and text()='Select Project'])[3]") WebElement dd_selectProject;
	@FindBy(xpath="//input[@name='material_tracking_number']") WebElement txt_materialTrackingNumber;
	@FindBy(xpath="//input[@name='delivered_to']") WebElement txt_deliveredTo;
	@FindBy(xpath="//input[@name='cost']") WebElement txt_EnterCost;
	@FindBy(xpath="//button[text()='Submit']") WebElement btn_submit;
	@FindBy(xpath="(//table[@class='align-items-center table-flush table'])[4]/tbody/tr[last()]") WebElement newRow_materialDelivery;
	private By allProducts = By.xpath("//div[@class='dropdown-menu show']//button[@style='padding: 6px 12px; font-size: 13px;']");
	private By allProjects = By.xpath("//div[@class='dropdown-menu show']//button[@class='dropdown-item']");
	
	//Vendor Material return
	@FindBy(xpath="//button[text()='Add Return Materials']") WebElement btn_addVendorMaterialReturn;
	@FindBy(xpath="//button[text()='Add Single Return Material']") WebElement btn_addSingleReturnMaterial;
	@FindBy(xpath="(//button[contains(text(), 'Select Project')])[3]") WebElement dd_SelectProject;
	@FindBy(xpath="//div[@class='dropdown']//span[contains(text(),'Select Product')]") WebElement dd_selectProductName;
	@FindBy(xpath="//select[@name='material_tracking_number']") WebElement dd_materialTrackingNumber;
	@FindBy(xpath="//input[@id='return_date']") WebElement date_ReturnDate;
	@FindBy(xpath="//select[@name='returned_by']") WebElement dd_selectClient;
	@FindBy(xpath="//input[@name='returned_quantity']") WebElement txt_returnedQty;
	@FindBy(xpath="//button[text()='OK']") WebElement btn_OK;
	@FindBy(xpath="(//table[@class='align-items-center table-flush table'])[5]/tbody/tr[1]") WebElement newRow_MaterialReturn;
	
	

	//Vendor to store
	public void addSingleStock(int qty) throws InterruptedException {
		scrolltoview(btn_AddStock);
		btn_AddStock.click();
		btn_AddSingleStock.click();
		Select vendorName = new Select(dd_selectVendorName);
		vendorName.selectByVisibleText("Vendor Y");
		txt_productName.sendKeys("Product5");
		txt_totalStock.sendKeys(String.valueOf(qty));
		txt_currentStock.sendKeys(String.valueOf(qty));
		txt_enterCost.sendKeys("5000");
		txt_deliveryDate.sendKeys("15/11/2025");
		btn_Submit.click();
		driver.get("https://aecp.aecearth.io/store-admin/store-management/Vendormanagement");
		Thread.sleep(3000);
		Assert.assertTrue(newRow_VendorToStore.isDisplayed());
		System.out.println("Add stock from vendor to store: \n"+newRow_VendorToStore.getText().trim());
		//Vendor delivers products 100 those products will be appeared in Store stock report in current quantity column.
	}
	
	
	//Store to vendor
	public void returnToVendor(int qty) {
		scrolltoview(btn_returnToVendor);
		btn_returnToVendor.click();
		btn_addSingleReturnToVendor.click();
		
		Select vendor = new Select(dd_selectVendor);
		vendor.selectByVisibleText("Vendor Y");
		
		Select product = new Select(dd_selectProduct);
		product.selectByValue("344");
		
		txt_quantityToReturn.sendKeys(String.valueOf(qty));
		date_returnDate.sendKeys("15/11/2025");
		txt_returnReason.sendKeys("No need anymore");
		btn_returnToVendor_submit.click();
		System.out.println("Quantity returned from store to vendor is: "+QuantityReturned_storeToVendor.getText());
		//from store 50 products are returned to vendor
		
	}
	
	//Vendor Material Delivery for project
	public void vendorMaterialDelivery(int qty) {
		scrolltoview(btn_addMaterialDelivery);
		btn_addMaterialDelivery.click();
		btn_addSingleMaterialDelivery.click();
		
		dd_SelectProductName.click();
		List<WebElement> products = driver.findElements(allProducts);
		for(WebElement product: products) {
			if(product.getText().equals("Product5")) {
				product.click();
			}
		}
		date_deliveryDate.sendKeys("15/11/2025");
		Select vendor = new Select(dd_SelectVendor);
		vendor.selectByVisibleText("Vendor Y");
		txt_enterQuantityDelivered.sendKeys(String.valueOf(qty));
		
		dd_selectProject.click();
		List<WebElement> projects= driver.findElements(allProjects);
		for(WebElement project:projects) {
			if(project.getText().equals("Test Project1")) {
				project.click();
			}
		}
		txt_materialTrackingNumber.sendKeys("141256");
		txt_deliveredTo.sendKeys("Ashwini");
		txt_EnterCost.sendKeys("1000");
		btn_submit.click();
		Assert.assertTrue(newRow_materialDelivery.isDisplayed());
		System.out.println("Newly added row in 'material delivery' table is "+newRow_materialDelivery.getText().trim());
	}
	
	//Vendor Material Return if the product is not needed in project
	public void VendorMaterialReturn(int qty) {
		scrolltoview(btn_addVendorMaterialReturn);
		btn_addVendorMaterialReturn.click();
		btn_addSingleReturnMaterial.click();
		
		dd_SelectProject.click();
		List<WebElement> projects= driver.findElements(allProjects);
		for(WebElement project:projects) {
			if(project.getText().equals("Test Project1")) {
				project.click();
			}
		}
		
		dd_selectProductName.click();
		List<WebElement> products = driver.findElements(allProducts);
		for(WebElement product: products) {
			if(product.getText().equals("Product5")) {
				product.click();
			}
		}
		
		Select vendor = new Select(dd_selectVendor);
		vendor.selectByVisibleText("Vendor Y");
		
		Select trackId = new Select(dd_materialTrackingNumber);
		trackId.selectByVisibleText("1412");
		
		date_ReturnDate.sendKeys("15/11/2025");
		Select client = new Select(dd_selectClient);
		client.selectByVisibleText("Ashwini");
		txt_returnedQty.sendKeys(String.valueOf(qty));
		btn_Submit.click();
		btn_OK.click();
		driver.get("https://aecp.aecearth.io/store-admin/store-management/Vendormanagement");
		Assert.assertTrue(newRow_MaterialReturn.isDisplayed());
		System.out.println("Material returned row is \n" +newRow_MaterialReturn.getText().trim());
	}
	
	

}
