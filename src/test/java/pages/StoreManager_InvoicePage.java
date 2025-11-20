package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import base.BaseClass;

public class StoreManager_InvoicePage extends BaseClass{
	public StoreManager_InvoicePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()='Add Invoice']") WebElement btn_addInvoice;
	@FindBy(xpath="//select[@name='project_name']") WebElement dd_selectProject;
	@FindBy(id="input-client-name") WebElement txt_clientName;
	@FindBy(id="input-client-address") WebElement txt_clientAddress;
	@FindBy(id="input-client-email") WebElement txt_clientEmail;
	@FindBy(name="client_phone") WebElement txt_ClientPhone;
	@FindBy(name="invoice_date") WebElement date_invoiceDate;
	@FindBy(name="invoice_due_date") WebElement date_invoiceDueDate;
	@FindBy(xpath="//select[@id='input-invoice-for']") WebElement dd_invoiceFor;
	@FindBy(id="input-tax-rate") WebElement txt_taxRate;
	@FindBy(name="service_name") WebElement txt_serviceName;
	@FindBy(name="unit_cost") WebElement txt_price;
	@FindBy(name="quantity") WebElement txt_quantity;
	@FindBy(xpath="//button[text()='Add']") WebElement btn_add;
	@FindBy(xpath="//button[text()='Submit']") WebElement btn_submit;
	@FindBy(xpath="//button[@class='dropdown-toggle btn btn-secondary']") WebElement showEntries;
	private By Entries = By.xpath("//div[@class='dropdown-menu show']/button");
	@FindBy(xpath="(//table[@class='align-items-center table-flush table']/tbody/tr[last()]/td)[2]") WebElement newRow_SentTo;
	
	public void addInvoice() throws InterruptedException {
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		btn_addInvoice.click();
		Select project = new Select(dd_selectProject);
		project.selectByVisibleText("Test Project1");
		txt_clientName.sendKeys("client X");
		txt_clientAddress.sendKeys("Address xyz");
		txt_clientEmail.sendKeys("test@example.com");
		txt_ClientPhone.sendKeys("1230981673");
		date_invoiceDate.sendKeys("17/11/2025");
		date_invoiceDueDate.sendKeys("18/11/2025");
		Select invoiceFor = new Select(dd_invoiceFor);
		invoiceFor.selectByVisibleText("Materials");
		txt_taxRate.sendKeys("20");
		txt_serviceName.sendKeys("service 1");
		txt_price.sendKeys("1000");
		txt_quantity.sendKeys("10");
		btn_submit.click();
		
		driver.get("https://aecp.aecearth.io/store-admin/store-management/Invoice4");
		
		showEntries.click();
		List<WebElement> AllEntries = driver.findElements(Entries);
		for(WebElement entry:AllEntries) {
			if(entry.getText().equals("All")) {
				entry.click();
				break;
			}
		}
		
		String sentToName = newRow_SentTo.getText();
		Assert.assertEquals(sentToName, "client X", "invoice is not getting added!");

	}

}
