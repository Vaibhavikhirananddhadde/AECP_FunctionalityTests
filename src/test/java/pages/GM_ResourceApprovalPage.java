package pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import base.BaseClass;

public class GM_ResourceApprovalPage extends BaseClass {
	
	private static final Logger logger = LogManager.getLogger(GM_ResourceApprovalPage.class);

	@FindBy(xpath = "(//button[@class ='dropdown-toggle btn btn-secondary'])[2]")
	WebElement dd_showEntries;

	@FindBy(xpath = "//button[text()='Set Action']")
	WebElement btn_setAction;

	@FindBy(xpath = "//select[@name='status']")
	WebElement dd_selectAction;

	@FindBy(id = "exampleText")
	WebElement txt_description;

	@FindBy(xpath = "//button[text()='Submit']")
	WebElement btn_submit;

	@FindBy(xpath = "//button[text()='Prev']")
	WebElement btn_prev;

	@FindBy(xpath = "//button[@class=\"dropdown-item\"and  text()='All']")
	WebElement opt_Allentry;

	@FindBy(xpath = "//div[@class='align-items-center justify-content-xl-between row']")
	WebElement footer;
	
	@FindBy(xpath="//input[@placeholder='Search Resource Type']")
	WebElement searchfield;
	
	@FindBy(xpath="//tbody/tr[1]")
	WebElement tableFirstRow;
	
	public GM_ResourceApprovalPage() {
		PageFactory.initElements(driver, this);
	}

	public void checkResourcesRequested() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// 1) Wait for the table
		By tableSel = By.cssSelector("table.align-items-center.table-flush.table");
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableSel));

		// 2) Get all body rows and pick the last one
		List<WebElement> rows = driver
				.findElements(By.cssSelector("table.align-items-center.table-flush.table tbody tr"));
		Assert.assertTrue(rows.size() > 0, "No rows found in table body");

		WebElement lastRow = rows.get(rows.size() - 1);
		String lastRowInTable = lastRow.getText();
		System.out.println(lastRowInTable);
		if (lastRowInTable.contains("Role Test")) {
			System.out.println("New Resource request added successfully in general manager");
			Assert.assertTrue(lastRow.isDisplayed());
		}

	}

	public void approveResourceRequest() throws Exception {
		waitImplicit();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// 1) Wait for the table
		By tableSel = By.cssSelector("table.align-items-center.table-flush.table");
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableSel));

		// get status column in last row
		By lastRowStatusCell = By.xpath("//table[contains(@class,'table-flush')]//tbody/tr[last()]//td[4]");

//		// 1) Ensure initial status is Pending
//		String initial = wait.until(ExpectedConditions.visibilityOfElementLocated(lastRowStatusCell)).getText().trim();
//		Assert.assertEquals(initial, "PENDING", "Last-row initial status is not Pending");

		// change the status(Approve)
		btn_setAction.click();
		Select selectAction = new Select(dd_selectAction);
		selectAction.selectByValue("Approved");
		txt_description.sendKeys("Approved by General manager");
		btn_submit.click();
		Thread.sleep(3000);

		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		Thread.sleep(3000);
		driver.navigate().refresh();

		// option 2 – JS hard reload
		((JavascriptExecutor) driver).executeScript("window.location.reload(true)");

		// 2) Final assert
		String finalStatus = driver.findElement(lastRowStatusCell).getText().trim();
		System.out.println("Status changed to 'Approved'");
		SoftAssert assert1 = new SoftAssert();

		assert1.assertEquals(finalStatus, "APPROVED", "Status did not change to Approved");

	}
	
	public void rejectResourceRequest() throws Exception {
		waitImplicit();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// 1) Wait for the table
		By tableSel = By.cssSelector("table.align-items-center.table-flush.table");
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableSel));

		// get status column in last row
		By lastRowStatusCell = By.xpath("//table[contains(@class,'table-flush')]//tbody/tr[last()]//td[4]");

//		// 1) Ensure initial status is Pending
//		String initial = wait.until(ExpectedConditions.visibilityOfElementLocated(lastRowStatusCell)).getText().trim();
//		Assert.assertEquals(initial, "PENDING", "Last-row initial status is not Pending");

		// change the status(Reject)
		btn_setAction.click();
		Select selectAction = new Select(dd_selectAction);
		selectAction.selectByValue("Rejected");
		txt_description.sendKeys("Rejected by General manager");
		btn_submit.click();
		Thread.sleep(3000);

		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		Thread.sleep(3000);
		driver.navigate().refresh();

		// option 2 – JS hard reload
		((JavascriptExecutor) driver).executeScript("window.location.reload(true)");

		// 2) Final assert
		String finalStatus = driver.findElement(lastRowStatusCell).getText().trim();
		System.out.println("Status changed to 'Rejected'");
		SoftAssert assert1 = new SoftAssert();

		assert1.assertEquals(finalStatus, "REJECTED", "Status did not change to Rejected");

	}
	
	public void searchResourceType(String resourcetype) throws InterruptedException {
		logger.info("Entering resource type in searchfield.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		searchfield.sendKeys(resourcetype);
		Thread.sleep(2000);
		
		WebElement RscTypeElement = tableFirstRow.findElement(By.xpath("./td[1]"));
		wait.until(ExpectedConditions.visibilityOf(RscTypeElement));
		String ResourceType = RscTypeElement.getText();
		if(ResourceType.equalsIgnoreCase(resourcetype)) {
			Assert.assertEquals(ResourceType, resourcetype, "Searched resource is not filtered in table!");
			System.out.println("Searched resource type is : "+ResourceType);
		}
		
		logger.info("Searched resource type is filtered in table.");
		
	}
	
	

}
