package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	

	// Vendor Material Delivery for project
	// Vendor Material Delivery for project
	public void vendorMaterialDelivery(String productName, int qty) throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // 1. Open "Vendor Material Delivery" main section
	    By addMaterialBtnBy = By.xpath("//button[normalize-space()='Add Material Delivery']");
	    WebElement addDeliveryBtn = wait.until(ExpectedConditions.elementToBeClickable(addMaterialBtnBy));
	    scrolltoview(addDeliveryBtn);
	    addDeliveryBtn.click();

	    // 2. Open "Add Single Material Delivery" modal
	    By addSingleDeliveryBy = By.xpath("//button[normalize-space()='Add Single Material Delivery']");
	    WebElement addSingleBtn = wait.until(ExpectedConditions.elementToBeClickable(addSingleDeliveryBy));
	    addSingleBtn.click();

	    // 3. Active modal
	    By modalBy = By.xpath("//div[contains(@class,'modal') and contains(@class,'show')]//div[contains(@class,'modal-content')]");
	    WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modalBy));

	    // ========== 4. Select Product (inside modal) ==========
	    WebElement productDropdownBtn = modal.findElement(
	            By.xpath(".//button[contains(@class,'dropdown-toggle') and .//span[contains(normalize-space(),'Select Product')]]")
	    );
	    productDropdownBtn.click();

	    // Options under the opened dropdown
	    List<WebElement> products = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                    By.xpath("//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button")
	            )
	    );

	    boolean productFound = false;
	    System.out.println("------ Product dropdown options ------");
	    for (WebElement prod : products) {
	        String fullText = prod.getText().trim();
	        System.out.println("Option: '" + fullText + "'");

	        // ✅ Strip ANY trailing " ( ... )" part: works for "(Available: 100)" AND "(1 vendor)"
	        String optionName = fullText.split("\\s*\\(")[0].trim();

	        if (optionName.equalsIgnoreCase(productName)) {
	            prod.click();
	            productFound = true;
	            break;
	        }
	    }

	    if (!productFound) {
	        throw new RuntimeException("Product '" + productName + "' not found in Material Delivery dropdown");
	    }
	    
	    // ========== 6. Delivery Date ==========
	    WebElement deliveryDateInput = modal.findElement(By.xpath(".//input[@id='delivery_date']"));
	    deliveryDateInput.sendKeys("28/11/2025");

	    
	    
	    // ========== 5. Vendor dropdown (inside modal, by id) ==========
	    By vendorBy = By.id("vendor_id");
	    WebElement vendorDropdown = modal.findElement(vendorBy);
	    System.out.println("Vendor dropdown found. Enabled? " + vendorDropdown.isEnabled());

	    // If your UI enables vendor after product, you can wait for that:
	    wait.until(driver1 -> {
	        WebElement el = modal.findElement(vendorBy);
	        boolean enabled = el.isEnabled();
	        System.out.println("Vendor enabled now? " + enabled);
	        return true; // <- keep going even if it stays disabled (business rule)
	    });

	    if (vendorDropdown.isEnabled()) {
	        Select vendorSelect = new Select(vendorDropdown);
	        vendorSelect.selectByVisibleText("Vendor Y");
	    } else {
	        System.out.println("Vendor dropdown is disabled; using default selected vendor.");
	    }

	   
	 // ========== 4b. Select Project (using existing PageObject locators) ==========
	   // dd_selectProject.click();
	    JavascriptExecutor js= (JavascriptExecutor) driver;
	   js.executeScript("arguments[0].click();", dd_selectProject);


	    List<WebElement> projects = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(allProjects)
	    );

	    boolean projectFound = false;
	    System.out.println("------ Project dropdown options ------");
	    for (WebElement proj : projects) {
	        String fullText = proj.getText().trim();
	        System.out.println("Project option: '" + fullText + "'");

	        // Strip anything in brackets, just like we did for products
	        String optionName = fullText.split("\\s*\\(")[0].trim();

	        if (optionName.equalsIgnoreCase("Test Project1")) {  // or use a parameter
	            proj.click();
	            projectFound = true;
	            break;
	        }
	    }

	    if (!projectFound) {
	        throw new RuntimeException("Project 'Test Project1' not found in Select Project dropdown");
	    }
	    
	    // ========== 7. Quantity field (inside modal by id) ==========
	    By qtyBy = By.id("quantity_delivered");
	    WebElement qtyField = modal.findElement(qtyBy);

	    // wait until quantity becomes enabled (after product/vendor logic)
	    wait.until(driver1 -> {
	        WebElement el = modal.findElement(qtyBy);
	        boolean enabled = el.isEnabled();
	        System.out.println("Quantity enabled? " + enabled);
	        return enabled;   // <-- only continue when it's really enabled
	    });

	    scrolltoview(qtyField);
	    qtyField.clear();
	    qtyField.sendKeys(String.valueOf(qty));
	    Thread.sleep(3000);

	    // ========== 8. Remaining fields ==========
	    WebElement trackNo = modal.findElement(By.xpath(".//input[@name='material_tracking_number']"));
	    trackNo.sendKeys("141256");
	    Thread.sleep(3000);
	    WebElement deliveredTo = modal.findElement(By.xpath(".//input[@name='delivered_to']"));
	    deliveredTo.sendKeys("Ashwini");
	    Thread.sleep(3000);
	    WebElement costField = modal.findElement(By.xpath(".//input[@name='cost']"));
	    costField.sendKeys("1000");
	    Thread.sleep(3000);
	    // ========== 9. Submit ==========
	    WebElement submitBtn = modal.findElement(By.xpath(".//button[normalize-space()='Submit']"));
	    submitBtn.click();
	    Thread.sleep(3000);
	 // ---- Handle validation alert if it appears ----
	    try {
	        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(3));
	        org.openqa.selenium.Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
	        String alertText = alert.getText();
	        System.out.println("Validation alert after Material Delivery: " + alertText);
	        alert.accept();

	        // Fail the test cleanly with a readable reason
	        Assert.fail("Material Delivery failed with validation alert: " + alertText);
	    } catch (org.openqa.selenium.TimeoutException e) {
	        // No alert -> success path, continue
	    }

	    // ========== 10. Verify new row in Material Delivery table ==========
	    wait.until(ExpectedConditions.visibilityOf(newRow_materialDelivery));
	    Assert.assertTrue(newRow_materialDelivery.isDisplayed(),
	            "New material delivery row is not visible in the table");
	    System.out.println("Newly added row in 'material delivery' table is "
	            + newRow_materialDelivery.getText().trim());
	}


	
	
	// Vendor Material Return if the product is not needed in project
	public void VendorMaterialReturn(String productName, int qty) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // 🔹 1. Make sure any previous modal (Material Delivery) is closed
	    try {
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                By.cssSelector("div.modal.fade.show")));
	        System.out.println("No visible modal, safe to click 'Add Return Materials'.");
	    } catch (org.openqa.selenium.TimeoutException e) {
	        System.out.println("Modal did not disappear in time; trying to proceed anyway.");
	    }

	    // 🔹 2. Now click "Add Return Materials"
	    scrolltoview(btn_addVendorMaterialReturn);
	    wait.until(ExpectedConditions.elementToBeClickable(btn_addVendorMaterialReturn)).click();

	    wait.until(ExpectedConditions.elementToBeClickable(btn_addSingleReturnMaterial)).click();

	    // 🔹 3. Select Project (reuse existing locators)
	    dd_SelectProject.click();
	    List<WebElement> projects = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(allProjects)
	    );

	    for (WebElement project : projects) {
	        String pText = project.getText().trim();
	        if (pText.equalsIgnoreCase("Test Project1")) {
	            project.click();
	            break;
	        }
	    }

	    // 🔹 4. Select Product
	    dd_selectProductName.click();
	    List<WebElement> products = driver.findElements(allProducts);
	    boolean found = false;
	    System.out.println("------ Product dropdown options (Return Material) ------");
	    for (WebElement product : products) {
	        String fullText = product.getText().trim();
	        System.out.println("Option: '" + fullText + "'");
	        String optionName = fullText.split("\\s*\\(")[0].trim(); // strip "(Available:...)" etc.

	        if (optionName.equalsIgnoreCase(productName)) {
	            product.click();
	            found = true;
	            break;
	        }
	    }

	    if (!found) {
	        throw new RuntimeException("Product '" + productName + "' not present in Return Materials dropdown");
	    }

	    // 🔹 5. Vendor, tracking no, date, client, qty
	    Select vendor = new Select(dd_selectVendor);
	    vendor.selectByVisibleText("Vendor Y");

	    Select trackId = new Select(dd_materialTrackingNumber);
	    trackId.selectByIndex(1); // or selectByVisibleText(...) if you know it

	    date_ReturnDate.sendKeys("2025-11-15"); // use yyyy-MM-dd if backend expects that
	    Select client = new Select(dd_selectClient);
	    client.selectByVisibleText("Ashwini");
	    txt_returnedQty.sendKeys(String.valueOf(qty));

	    btn_Submit.click();
	    btn_OK.click();

	    // 🔹 6. Go back to Vendor Management and verify row
	    driver.get("https://aecp.aecearth.io/store-admin/store-management/Vendormanagement");
	    wait.until(ExpectedConditions.visibilityOf(newRow_MaterialReturn));
	    Assert.assertTrue(newRow_MaterialReturn.isDisplayed());
	    System.out.println("Material returned row is \n" + newRow_MaterialReturn.getText().trim());
	}
	
	
	
	// --- Helper: parse integer safely ---
	private int parseInt(String text) {
	    return Integer.parseInt(text.trim());
	}

	// 1) Stock Report: table[1], product name in td[3], current stock in td[4] (adjust index if needed)
	public int getStockReportQty(String productName) {
	    String xpath = "(//table[@class='align-items-center table-flush table'])[1]" +
	            "//tbody/tr[td[3][normalize-space()='" + productName + "']]/td[4]";
	    String qtyText = driver.findElement(By.xpath(xpath)).getText();
	    return parseInt(qtyText);
	}

	// 2) Vendor To Store: table[2], product name td[3], quantity received td[4]
	public int getVendorToStoreQty(String productName) {
	    String xpath = "(//table[@class='align-items-center table-flush table'])[2]" +
	            "//tbody/tr[td[3][normalize-space()='" + productName + "']]/td[4]";
	    String qtyText = driver.findElement(By.xpath(xpath)).getText();
	    return parseInt(qtyText);
	}

	// 3) Store To Vendor: table[3], product name td[3], quantity returned td[4]
	public int getStoreToVendorQty(String productName) {
	    String xpath = "(//table[@class='align-items-center table-flush table'])[3]" +
	            "//tbody/tr[td[3][normalize-space()='" + productName + "']]/td[4]";
	    String qtyText = driver.findElement(By.xpath(xpath)).getText();
	    return parseInt(qtyText);
	}

	// 4) Vendor Material Delivery: table[4], product in td[2], quantity delivered in td[3]
	public int getVendorMaterialDeliveryQty(String productName) {
	    String xpath = "(//table[@class='align-items-center table-flush table'])[4]" +
	            "//tbody/tr[td[2][normalize-space()='" + productName + "']]/td[3]";
	    String qtyText = driver.findElement(By.xpath(xpath)).getText();
	    return parseInt(qtyText);
	}

	// 5) Vendor Material Return: table[5], product in td[2], quantity returned in td[3]
	public int getVendorMaterialReturnQty(String productName) {
	    String xpath = "(//table[@class='align-items-center table-flush table'])[5]" +
	            "//tbody/tr[td[2][normalize-space()='" + productName + "']]/td[3]";
	    String qtyText = driver.findElement(By.xpath(xpath)).getText();
	    return parseInt(qtyText);
	}
	
	public void addSingleStock(String productName, int qty) throws InterruptedException {
	    scrolltoview(btn_AddStock);
	    btn_AddStock.click();
	    btn_AddSingleStock.click();

	    Select vendorName = new Select(dd_selectVendorName);
	    vendorName.selectByVisibleText("Vendor Y");

	    txt_productName.sendKeys(productName);
	    txt_totalStock.sendKeys(String.valueOf(qty));
	    txt_currentStock.sendKeys(String.valueOf(qty));
	    txt_enterCost.sendKeys("5000");
	    txt_deliveryDate.sendKeys("15/11/2025");
	    btn_Submit.click();

	    driver.get("https://aecp.aecearth.io/store-admin/store-management/Vendormanagement");
	    Thread.sleep(3000);
	}

	// Return to vendor – use same product name
	public void returnToVendor(String productName, int qty) {
	    scrolltoview(btn_returnToVendor);
	    btn_returnToVendor.click();
	    btn_addSingleReturnToVendor.click();

	    Select vendor = new Select(dd_selectVendor);
	    vendor.selectByVisibleText("Vendor Y");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(dd_selectProduct));

	    Select productSelect = new Select(dd_selectProduct);
	    List<WebElement> options = productSelect.getOptions();

	    boolean found = false;
	    System.out.println("------ Product dropdown options ------");
	    for (WebElement opt : options) {
	        String fullText = opt.getText().trim();
	        System.out.println("Option: '" + fullText + "'");

	        // Take only the part before " (Available:"
	        String optionName = fullText.split("\\s*\\(Available:")[0].trim();

	        if (optionName.equalsIgnoreCase(productName)) {   // <-- case-insensitive, name only
	            opt.click();
	            found = true;
	            break;
	        }
	    }

	    if (!found) {
	        throw new RuntimeException("Product '" + productName + "' not present in Return To Vendor dropdown");
	    }

	    txt_quantityToReturn.sendKeys(String.valueOf(qty));
	    date_returnDate.sendKeys("15/11/2025");
	    txt_returnReason.sendKeys("No need anymore");
	    btn_returnToVendor_submit.click();
	}


	
	

}
