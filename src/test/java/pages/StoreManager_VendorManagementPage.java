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
	@FindBy(xpath="//button[text()='OK']") WebElement btn_OK_matDel;
	@FindBy(xpath="(//table[@class='align-items-center table-flush table'])[4]/tbody/tr[last()]") WebElement newRow_materialDelivery;
	//private By allProducts = By.xpath("//div[@class='dropdown-menu show']//button[@style='padding: 6px 12px; font-size: 13px;']");
	private By allProjects = By.xpath("//div[@class='dropdown-menu show']//button[@class='dropdown-item']");
	
	//Vendor Material return
	//@FindBy(xpath="//button[text()='Add Return Materials']") WebElement btn_addVendorMaterialReturn;
	By btn_addVendorMaterialReturnBy =By.xpath("//button[text()='Add Return Materials']");
	//@FindBy(xpath="//button[text()='Add Single Return Material']") WebElement btn_addSingleReturnMaterial;
	By btn_addSingleReturnMaterialBy = By.xpath("//button[text()='Add Single Return Material']");
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
        Thread.sleep(3000);
	    // 2. Open "Add Single Material Delivery" modal
	    By addSingleDeliveryBy = By.xpath("//button[normalize-space()='Add Single Material Delivery']");
	    WebElement addSingleBtn = wait.until(ExpectedConditions.elementToBeClickable(addSingleDeliveryBy));
	    addSingleBtn.click();
	    Thread.sleep(3000);
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
	   

	    // ========== 8. Remaining fields ==========
	    WebElement trackNo = modal.findElement(By.xpath(".//input[@name='material_tracking_number']"));
	    trackNo.sendKeys(randomNumber(6));
	   
	    WebElement deliveredTo = modal.findElement(By.xpath(".//input[@name='delivered_to']"));
	    deliveredTo.sendKeys("Ashwini");
	    
	    WebElement costField = modal.findElement(By.xpath(".//input[@name='cost']"));
	    costField.sendKeys("1000");
	   
	    // ========== 9. Submit ==========
	    WebElement submitBtn = modal.findElement(By.xpath(".//button[normalize-space()='Submit']"));
	    submitBtn.click();
	    
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
	    
	    wait.until(ExpectedConditions.visibilityOf(btn_OK_matDel));
	    btn_OK_matDel.click();

	    // ========== 10. Verify new row in Material Delivery table ==========
	    wait.until(ExpectedConditions.visibilityOf(newRow_materialDelivery));
	    Assert.assertTrue(newRow_materialDelivery.isDisplayed(),
	            "New material delivery row is not visible in the table");
	    System.out.println("Newly added row in 'material delivery' table is "
	            + newRow_materialDelivery.getText().trim());
	}


	
	
	// Vendor Material Return if the product is not needed in project
	// Vendor Material Return if the product is not needed in project
	public void VendorMaterialReturn(String productName, int qty) throws InterruptedException {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // 🔹 0. Make sure any previous modal is gone
	    try {
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                By.xpath("//div[contains(@class,'modal') and contains(@class,'show')]")
	        ));
	        System.out.println("No visible modal, safe to click 'Add Return Materials'.");
	    } catch (Exception e) {
	        System.out.println("Modal invisibility wait timed out, continuing anyway.");
	    }

	    // 🔹 1. Scroll page down so 'Add Return Materials' comes into view
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	    Thread.sleep(500); // small pause so layout settles

	    By addReturnMaterialsBtnBy = By.xpath("//button[normalize-space()='Add Return Materials']");

	    // 🔹 2. Click 'Add Return Materials' with retry + JS fallback
	    for (int i = 0; i < 3; i++) {
	        try {
	            WebElement addReturnBtn = wait.until(
	                    ExpectedConditions.elementToBeClickable(addReturnMaterialsBtnBy)
	            );
	            // Try normal click first
	            try {
	                addReturnBtn.click();
	            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
	                System.out.println("Normal click intercepted, using JS click for Add Return Materials");
	                js.executeScript("arguments[0].click();", addReturnBtn);
	            }
	            break; // success, exit retry loop
	        } catch (org.openqa.selenium.StaleElementReferenceException e) {
	            System.out.println("Stale on 'Add Return Materials' click, retry " + (i + 1));
	            if (i == 2) {
	                throw e; // after 3 tries, give up
	            }
	        }
	    }

	    // 🔹 3. Open "Add Single Return Material"
	    By addSingleReturnBy = By.xpath("//button[normalize-space()='Add Single Return Material']");
	    WebElement addSingleReturnBtn = wait.until(
	            ExpectedConditions.elementToBeClickable(addSingleReturnBy)
	    );
	    addSingleReturnBtn.click();

	    // 🔹 4. Select Project
	    By projectDropdownBy = By.xpath("(//button[contains(text(),'Select Project')])[3]");
	    WebElement projectDropdown = wait.until(
	            ExpectedConditions.elementToBeClickable(projectDropdownBy)
	    );
	    projectDropdown.click();

	    List<WebElement> projects = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                    By.xpath("//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button")
	            )
	    );
	    for (WebElement proj : projects) {
	        String txt = proj.getText().trim();
	        if (txt.split("\\s*\\(")[0].trim().equalsIgnoreCase("Test Project1")) {
	            proj.click();
	            break;
	        }
	    }

	    // 🔹 5. Select Product (strip “(1 vendor)” etc.)
	    dd_selectProductName.click();
	    List<WebElement> products = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                    By.xpath("//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//button")
	            )
	    );

	    System.out.println("------ Product dropdown options (Return Material) ------");
	    for (WebElement product : products) {
	        String full = product.getText().trim();
	        System.out.println("Option: '" + full + "'");
	        String nameOnly = full.split("\\s*\\(")[0].trim();
	        if (nameOnly.equalsIgnoreCase(productName)) {
	            product.click();
	            break;
	        }
	    }

	    // 🔹 6. Vendor, tracking no, date, client, qty
	    Select vendor = new Select(dd_selectVendor);
	    vendor.selectByVisibleText("Vendor Y");

	    Select trackId = new Select(dd_materialTrackingNumber);
	    trackId.selectByIndex(1); // or selectByVisibleText("1412") if constant

	    date_ReturnDate.sendKeys("15/11/2025");

	    Select client = new Select(dd_selectClient);
	    client.selectByVisibleText("Ashwini");

	    txt_returnedQty.sendKeys(String.valueOf(qty));

	    btn_Submit.click();

	    // 🔹 7. Handle success OK button (fresh locator)
	    By okBtnBy = By.xpath("//button[normalize-space()='OK']");
	    WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(okBtnBy));
	    okBtn.click();

	    // 🔹 8. Back to main page, verify latest row
	    driver.get("https://aecp.aecearth.io/store-admin/store-management/Vendormanagement");
	    wait.until(ExpectedConditions.visibilityOf(newRow_MaterialReturn));
	    Assert.assertTrue(newRow_MaterialReturn.isDisplayed());
	    System.out.println("Material returned row is \n"
	            + newRow_MaterialReturn.getText().trim());
	}

	
	// --- Helper: parse integer safely ---
	private int parseInt(String text) {
	    return Integer.parseInt(text.trim());
	}

	/**
	 * 1) Stock Report – table[1]
	 * Row: th | Product | CurrentStock | TotalStock | Cost
	 * Product  -> td[1]
	 * Qty we care (CurrentStock) -> td[2]
	 */
	public int getStockReportQty(String productName) {
	    String lower = productName.toLowerCase();

	    String xpath =
	        "(//table[@class='align-items-center table-flush table'])[1]"
	      + "//tbody/tr[translate(td[1], 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '"
	      + lower + "']/td[2]";

	    String qtyText = driver.findElement(By.xpath(xpath)).getText();
	    return parseInt(qtyText);
	}

	/**
	 * 2) Vendor → Store – table[2]
	 * Row: th | Vendor | Product | Qty | Date | ...
	 * Product -> td[2]
	 * Qty     -> td[3]
	 */
	public int getVendorToStoreQty(String productName) {
	   

	    String xpath =
	        "(//table[@class='align-items-center table-flush table'])[2]"
	      + "//tbody/tr[translate(td[2], 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '"
	      + productName + "']/td[3]";

	    String qtyText = driver.findElement(By.xpath(xpath)).getText();
	    return parseInt(qtyText);
	}

	/**
	 * 3) Store → Vendor – table[3]
	 * Row: th | Vendor | Product | QtyReturned | Date | Reason | ...
	 * Product      -> td[2]
	 * QtyReturned  -> td[3]
	 */
	public int getStoreToVendorQty(String productName) {
	    String lower = productName.toLowerCase();

	    String xpath =
	        "(//table[@class='align-items-center table-flush table'])[3]"
	      + "//tbody/tr[translate(td[2], 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '"
	      + lower + "']/td[3]";

	    String qtyText = driver.findElement(By.xpath(xpath)).getText();
	    return parseInt(qtyText);
	}

	/**
	 * 4) Vendor Material Delivery – table[4]
	 * Row: th | Product | QtyDelivered | Date | Reason | DeliveredTo | Project | Tracking | Cost
	 * Product      -> td[1]
	 * QtyDelivered -> td[2]
	 */
	public int getVendorMaterialDeliveryQty(String productName) {
	    String lower = productName.toLowerCase();

	    String xpath =
	        "(//table[@class='align-items-center table-flush table'])[4]"
	      + "//tbody/tr[translate(td[1], 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '"
	      + lower + "']/td[2]";

	    String qtyText = driver.findElement(By.xpath(xpath)).getText();
	    return parseInt(qtyText);
	}

	/**
	 * 5) Vendor Material Return – table[5]
	 * Row: th | Product | QtyReturned | ??? | Date | Vendor | Project | Tracking | ReturnedBy
	 * Product      -> td[1]
	 * QtyReturned  -> td[2]
	 */
	public int getVendorMaterialReturnQty(String productName) {
	    String lower = productName.toLowerCase();

	    String xpath =
	        "(//table[@class='align-items-center table-flush table'])[5]"
	      + "//tbody/tr[translate(td[1], 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '"
	      + lower + "']/td[2]";

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
