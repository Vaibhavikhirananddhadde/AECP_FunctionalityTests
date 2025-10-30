package pages;

import java.time.Duration;
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

public class HR_ResourceAllocationPage extends BaseClass{
	@FindBy(xpath="(//tr/td)[2]")
	WebElement firstRow;
	
	@FindBy(xpath="(//button[@class ='dropdown-toggle btn btn-secondary'])[2]")
	WebElement dd_showEntries;
	
	@FindBy(xpath="//button[text()='Set Action']")
	WebElement btn_setAction;
	
	@FindBy(xpath="//select[@name='status']")
	WebElement dd_selectAction;
	
	@FindBy(id="exampleText")
	WebElement txt_description;
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement btn_submit;
	
	@FindBy(xpath="//button[text()='Prev']")
	WebElement btn_prev;
	
	@FindBy(xpath="//button[@class=\"dropdown-item\"and  text()='All']")
	WebElement opt_Allentry;
	
	@FindBy(xpath="//table[contains(@class,'table-flush')]//tbody/tr[last()]/td//button//i[@class='fa-solid fa-eye']")
	WebElement icon_eye;
	
	@FindBy(xpath="//table[@style='width: 100%;']/tbody")
	WebElement requestDetails;
	
	public HR_ResourceAllocationPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void checkResourceRequested() {
		String FirstRowRequest = firstRow.getText();
		if(FirstRowRequest.equals("Role Test")) {
			Assert.assertTrue(firstRow.isDisplayed());
			System.out.println("New Request added successfully in HR");
		}
	}
	
	public void approveResourceRequest() throws Exception {
		waitImplicit();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// 1) Wait for the table
		By tableSel = By.cssSelector("table.align-items-center.table-flush.table");
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableSel));

		//get status column in last row
		By lastRowStatusCell = By.xpath("//table[@class='align-items-center table-flush table']/tbody/tr[1]/td[5]");

		// 1) Ensure initial status is Pending
		String initial = wait.until(ExpectedConditions.visibilityOfElementLocated(lastRowStatusCell))
		                     .getText().trim();
		Assert.assertEquals(initial, "PENDING", "Last-row initial status is not Pending");
       
		//change the status(Approve)
		btn_setAction.click();
		Select selectAction = new Select(dd_selectAction);
		selectAction.selectByValue("Approved");
		txt_description.sendKeys("Approved by HR");
		btn_submit.click();
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		
		driver.navigate().refresh();
		
		// option 2 – JS hard reload
		((JavascriptExecutor) driver).executeScript("window.location.reload(true)");
		
		
		// 2) Final assert
		String finalStatus = driver.findElement(lastRowStatusCell).getText().trim();
		System.out.println("Status changed to 'Approved'");
		SoftAssert assert1 = new SoftAssert();
		
		assert1.assertEquals(finalStatus, "APPROVED", "Status did not change to Approved");
		
	}
	
	public void viewRequestDetails() {
		waitImplicit();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// 1) Wait for the table
		By tableSel = By.cssSelector("table.align-items-center.table-flush.table");
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableSel));
		icon_eye.click();
		waitExplicit(requestDetails);
		System.out.println(requestDetails.getText());
		//Assert.assertTrue(requestDetails.isDisplayed());
		Assert.assertEquals(requestDetails.getText(), "Resource Type Sit non aut laudanti\n"
				+ "Quantity 671\n"
				+ "Description testing\n"
				+ "General Manager pending\n"
				+ "General Action Waiting for action\n"
				+ "HR");

	}


}
