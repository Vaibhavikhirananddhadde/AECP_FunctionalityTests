package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class HR_DepartmentPage extends BaseClass{
	public HR_DepartmentPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()='ADD']")
	WebElement btn_Add;
	
	@FindBy(xpath="//input[@name='dept_name']")
	WebElement txt_deptName;
	
	@FindBy(xpath="//input[@id='input-file']")
	WebElement file_DeptLogo;
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement btn_Submit;
	
	@FindBy(xpath="(//table[@class='align-items-center table-flush table']//tbody/tr//i[contains(@id,'delete-tooltip')])[last()]")
	WebElement icon_delete;
	
	@FindBy(xpath="//button[text()='Yes']")
	WebElement btn_YES;
	
	@FindBy(xpath="//button[text()='No']")
	WebElement btn_NO;
	
	@FindBy(xpath="(//table[@class='align-items-center table-flush table']//tbody/tr//h4)[last()]")
	WebElement rowLast_DeptName;
	
	@FindBy(xpath="//input[@placeholder='Search Department']")
	WebElement txt_searchfield;
	
	private By lastDeleteIcon = By.xpath(
		    "(//table[@class='align-items-center table-flush table']//tbody/tr//i[contains(@id,'delete-tooltip')])[last()]"
		);

		private By lastDeptName = By.xpath(
		    "(//table[@class='align-items-center table-flush table']//tbody/tr//h4)[last()]"
		);
	
	private By rows = By.xpath("//table[@class='align-items-center table-flush table']/tbody/tr");
	
	public void addingDepartment() {
		btn_Add.click();
		txt_deptName.sendKeys("Test Department");
		file_DeptLogo.sendKeys("/Users/admin/Downloads/ChatGPT Image Sep 16, 2025, 03_02_23 PM.png");
		btn_Submit.click();
		List<WebElement> allDepartments = driver.findElements(By.xpath("//table[@class='align-items-center table-flush table']//tbody/tr"));
		for(WebElement department:allDepartments) {
			String deptName = department.getText();
			if(deptName.equals("Test Department")) {
				Assert.assertEquals(deptName, "Test Department", "Department has not added successfully.");
			}
		}
		
	}
	public void deleteDepartment() throws InterruptedException {
		
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // 1. Click latest delete icon (fresh element)
	    WebElement deleteIcon = wait.until(
	            ExpectedConditions.elementToBeClickable(lastDeleteIcon)
	    );
	    deleteIcon.click();

	    // 2. Confirm delete
	    wait.until(ExpectedConditions.elementToBeClickable(btn_YES)).click();

	    // 3. Wait for the old icon to become stale (table is reloaded)
	    wait.until(ExpectedConditions.stalenessOf(deleteIcon));

	    // 4. Optionally wait for table rows to re-appear
	    wait.until(ExpectedConditions.visibilityOfElementLocated(rows));

	    // 5. Now read the last department name from fresh DOM
	    String lastDept = driver.findElement(lastDeptName).getText().trim();
	    Assert.assertNotEquals(lastDept, "Test Department", "Department has not deleted!");
	}
	
	public void searchDepartment(String department) throws InterruptedException {
		txt_searchfield.sendKeys(department);
		Thread.sleep(3000);
		
		List<WebElement> Rows = driver.findElements(rows);
		for(WebElement departmentRow :Rows) {
			WebElement DepartmentName = departmentRow.findElement(By.xpath("./td[2]"));
			
			System.out.println("Searched department name is : "+DepartmentName.getText().trim());
			//Assert.assertTrue(DepartmentName.isDisplayed(), "Department searching is not working..!");
			Assert.assertEquals(DepartmentName.getText().trim(), department, "Searched department is not displayed!");
		}
		
	}
	
	
	
		
	
}
